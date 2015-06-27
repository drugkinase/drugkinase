
root = exports ? this

# Help with the placement of nodes
RadialPlacement = () ->
  # stores the key -> location values
  values = d3.map()
  # how much to separate each location by
  increment = 20
  # how large to make the layout
  radius = 200
  # where the center of the layout should be
  center = {"x":0, "y":0}
  # what angle to start at
  start = -120
  current = start

  # Given an center point, angle, and radius length,
  # return a radial position for that angle
  radialLocation = (center, angle, radius) ->
    x = (center.x + radius * Math.cos(angle * Math.PI / 180))
    y = (center.y + radius * Math.sin(angle * Math.PI / 180))
    {"x":x,"y":y}

  # Main entry point for RadialPlacement
  # Returns location for a particular key,
  # creating a new location if necessary.
  placement = (key) ->
    value = values.get(key)
    if !values.has(key)
      value = place(key)
    value

  # Gets a new location for input key
  place = (key) ->
    value = radialLocation(center, current, radius)
    values.set(key,value)
    current += increment
    value

   # Given a set of keys, perform some 
  # magic to create a two ringed radial layout.
  # Expects radius, increment, and center to be set.
  # If there are a small number of keys, just make
  # one circle.
  setKeys = (keys) ->
    # start with an empty values
    values = d3.map()
  
    # number of keys to go in first circle
    firstCircleCount = 360 / increment

    # if we don't have enough keys, modify increment
    # so that they all fit in one circle
    if keys.length < firstCircleCount
      increment = 360 / keys.length

    # set locations for inner circle
    firstCircleKeys = keys.slice(0,firstCircleCount)
    firstCircleKeys.forEach (k) -> place(k)

    # set locations for outer circle
    secondCircleKeys = keys.slice(firstCircleCount)

    # setup outer circle
    radius = radius + radius / 1.8
    increment = 360 / secondCircleKeys.length

    secondCircleKeys.forEach (k) -> place(k)

  placement.keys = (_) ->
    if !arguments.length
      return d3.keys(values)
    setKeys(_)
    placement

  placement.center = (_) ->
    if !arguments.length
      return center
    center = _
    placement

  placement.radius = (_) ->
    if !arguments.length
      return radius
    radius = _
    placement

  placement.start = (_) ->
    if !arguments.length
      return start
    start = _
    current = start
    placement

  placement.increment = (_) ->
    if !arguments.length
      return increment
    increment = _
    placement

  return placement

Network = () ->
  # variables we want to access
  # in multiple places of Network
  width = 960
  height = 750
  # allData will store the unfiltered data
  allData = []
  curLinksData = []
  curNodesData = []
  linkedByIndex = {}
  # these will hold the svg groups for
  # accessing the nodes and links display
  nodesG = null
  linksG = null
  # these will point to the circles and lines
  # of the nodes and links
  node = null
  link = null
  # variables to refect the current settings
  # of the visualization
  layout = "force"
  filter = "all"
  sort = "songs"
  # groupCenters will store our radial layout for
  # the group by type layout.
  groupCenters = null

  # our force directed layout
  force = d3.layout.force()
  # color function used to color nodes
  nodeColors = d3.scale.category20()
  # tooltip used to display details
  tooltip = Tooltip("vis-tooltip", 230)

  # charge used in type layout
  charge = (node) -> -Math.pow(node.radius, 2.0) / 2

  # Starting point for network visualization
  # Initializes visualization and starts force layout
  network = (selection, data) ->
    # format our data
    allData = setupData(data)

    # create our svg and groups
    vis = d3.select(selection).append("svg")
      .attr("width", width)
      .attr("height", height)
    linksG = vis.append("g").attr("id", "links")
    nodesG = vis.append("g").attr("id", "nodes")

    # setup the size of the force environment
    force.size([width, height])

    setLayout("force")
    setFilter("all")

    # perform rendering and start force layout
    update()

  # The update() function performs the bulk of the
  # work to setup our visualization based on the
  # current layout/sort/filter.
  #
  # update() is called everytime a parameter changes
  # and the network needs to be reset.
  update = () ->
    # filter data to show based on current filter settings.
    curNodesData = filterNodes(allData.nodes)
    curLinksData = filterLinks(allData.links, curNodesData)

    # sort nodes based on current sort and update centers for
    # radial layout
    if layout == "radial"
      types = sortedtypes(curNodesData, curLinksData)
      updateCenters(types)

    # reset nodes in force layout
    force.nodes(curNodesData)

    # enter / exit for nodes
    updateNodes()

    # always show links in force layout
    if layout == "force"
      force.links(curLinksData)
      updateLinks()
    else
      # reset links so they do not interfere with
      # other layouts. updateLinks() will be called when
      # force is done animating.
      force.links([])
      # if present, remove them from svg 
      if link
        link.data([]).exit().remove()
        link = null

    # start me up!
    force.start()

  # Public function to switch between layouts
  network.toggleLayout = (newLayout) ->
    force.stop()
    setLayout(newLayout)
    update()

  # Public function to switch between filter options
  network.toggleFilter = (newFilter) ->
    force.stop()
    setFilter(newFilter)
    update()

  # Public function to switch between sort options
  network.toggleSort = (newSort) ->
    force.stop()
    setSort(newSort)
    update()

  # Public function to update highlighted nodes
  # from search
  network.updateSearch = (searchTerm) ->
    searchRegEx = new RegExp(searchTerm.toLowerCase())
    node.each (d) ->
      element = d3.select(this)
      match = d.id.toLowerCase().search(searchRegEx)
      if searchTerm.length > 0 and match >= 0
        element.style("fill", "#F38630")
          .style("stroke-width", 2.0)
          .style("stroke", "#555")
        d.searched = true
      else
        d.searched = false
        element.style("fill", (d) -> nodeColors(d.id))
          .style("stroke-width", 1.0)

  network.updateData = (newData) ->
    allData = setupData(newData)
    link.remove()
    node.remove()
    update()

  # called once to clean up raw data and switch links to
  # point to node instances
  # Returns modified data
  setupData = (data) ->
    # initialize circle radius scale
    countExtent = d3.extent(data.nodes, (d) -> d.size)
    circleRadius = d3.scale.sqrt().range([3, 12]).domain(countExtent)

    data.nodes.forEach (n) ->
      # set initial x/y to values within the width/height
      # of the visualization
      n.x = randomnumber=Math.floor(Math.random()*width)
      n.y = randomnumber=Math.floor(Math.random()*height)
      # add radius to the node so we can use it later
      n.radius = circleRadius(n.size)

    # id's -> node objects
    nodesMap  = mapNodes(data.nodes)

    # switch links to point to node objects instead of id's
    data.links.forEach (l) ->
      l.source = nodesMap.get(l.source)
      l.target = nodesMap.get(l.target)

      # linkedByIndex is used for link sorting
      linkedByIndex["#{l.source.id},#{l.target.id}"] = 1

    data

  # Helper function to map node id's to node objects.
  # Returns d3.map of ids -> nodes
  mapNodes = (nodes) ->
    nodesMap = d3.map()
    nodes.forEach (n) ->
      nodesMap.set(n.id, n)
    nodesMap

  # Helper function that returns an associative array
  # with counts of unique attr in nodes
  # attr is value stored in node, like 'type'
  nodeCounts = (nodes, attr) ->
    counts = {}
    nodes.forEach (d) ->
      counts[d[attr]] ?= 0
      counts[d[attr]] += 1
    counts

  # Given two nodes a and b, returns true if
  # there is a link between them.
  # Uses linkedByIndex initialized in setupData
  neighboring = (a, b) ->
    linkedByIndex[a.id + "," + b.id] or
      linkedByIndex[b.id + "," + a.id]

  # Removes nodes from input array
  # based on current filter setting.
  # Returns array of nodes
  filterNodes = (allNodes) ->
    filteredNodes = allNodes
    if filter == "top5" or filter == "top10" or filter == "top15"
      counts = allNodes.map((d) -> d.count).sort(d3.ascending)
      cutoff1 = d3.quantile(counts, 0.1)
      filteredNodes = allNodes.filter (n) ->
        if filter == "top5"
          n.count <= 5
        else if filter == "top10"
          n.count <= 10
        else if filter == "top15"
          n.count <= 15
          
    filteredNodes

  # Returns array of types sorted based on
  # current sorting method.
  sortedtypes = (nodes,links) ->
    types = []
    if sort == "links"
      counts = {}
      links.forEach (l) ->
        counts[l.source.type] ?= 0
        counts[l.source.type] += 1
        counts[l.target.type] ?= 0
        counts[l.target.type] += 1
      # add any missing types that dont have any links
      nodes.forEach (n) ->
        counts[n.type] ?= 0

      # sort based on counts
      types = d3.entries(counts).sort (a,b) ->
        b.value - a.value
      # get just names
      types = types.map (v) -> v.key
    else
      # sort types by song count
      counts = nodeCounts(nodes, "type")
      types = d3.entries(counts).sort (a,b) ->
        b.value - a.value
      types = types.map (v) -> v.key

    types

  updateCenters = (types) ->
    if layout == "radial"
      groupCenters = RadialPlacement().center({"x":width/2, "y":height / 2 - 100})
        .radius(300).increment(18).keys(types)

  # Removes links from allLinks whose
  # source or target is not present in curNodes
  # Returns array of links
  filterLinks = (allLinks, curNodes) ->
    curNodes = mapNodes(curNodes)
    allLinks.filter (l) ->
      curNodes.get(l.source.id) and curNodes.get(l.target.id)

  drawNodes = () ->
    curNodesDataP = (c for c in curNodesData when c.type == 'protein')
    nodeP = nodesG.selectAll("circle.node")
      .data(curNodesDataP, (d) -> d.id)
    nodeP.enter().append("circle")
      .attr("class", "node")
      .attr("cx", (d) -> d.x)
      .attr("cy", (d) -> d.y)
      .attr("r", 15)
      .attr("rx", 20)
      .attr("ry", 10)
      .style("fill", (d) -> nodeColors(d.id))
      .style("stroke", (d) -> strokeFor(d))
      .style("stroke-width", 1.0)
      .call(force.drag)
    curNodesDataC = (c for c in curNodesData when c.type == 'chemical')
    nodeC = nodesG.selectAll("ellipse.node")
      .data(curNodesDataC, (d) -> d.id)
    nodeC.enter().append("ellipse")
      .attr("class", "node")
      .attr("cx", (d) -> d.x)
      .attr("cy", (d) -> d.y)
      .attr("r", 15)
      .attr("rx", 20)
      .attr("ry", 10)
      .style("fill", (d) -> nodeColors(d.id))
      .style("stroke", (d) -> strokeFor(d))
      .style("stroke-width", 1.0)
      .call(force.drag)

  # enter/exit display for nodes
  updateNodes = () ->    
    #svg.on("click", hideDetails)
    drawNodes()
    node = nodesG.selectAll("*.node")
      .data(curNodesData, (d) -> d.id)
    node.on("mouseover", showDetails)
      #.on("mouseover", showDetails)
      #.on("mouseout", hideDetails)
      .on("click", jumpToPage)
    node.exit().remove()
        
  
  jumpToPage = (d,i) -> 
  	window.parent.location.href = "../similarSearch?ID=" + d.id
  
  # enter/exit display for links
  updateLinks = () ->
    link = linksG.selectAll("line.link")
      .data(curLinksData, (d) -> "#{d.source.id}_#{d.target.id}")
    link.enter().append("line")
      .attr("class", "link")
      .attr("stroke", "#ddd")
      .attr("stroke-opacity", 0.8)
      .attr("x1", (d) -> d.source.x)
      .attr("y1", (d) -> d.source.y)
      .attr("x2", (d) -> d.target.x)
      .attr("y2", (d) -> d.target.y)
      .style("stroke-width", (d) -> d.weight * 3)
      
    link.on("mouseover", showDetailsLink)
      .on("mouseout", hideDetailsLink)
      
    link.exit().remove()

  # switches force to new layout parameters
  setLayout = (newLayout) ->
    layout = newLayout
    if layout == "force"
      force.on("tick", forceTick)
        .charge(-300)
        .linkDistance(250)
    else if layout == "radial"
      force.on("tick", radialTick)
        .charge(charge)

  # switches filter option to new filter
  setFilter = (newFilter) ->
    filter = newFilter

  # switches sort option to new sort
  setSort = (newSort) ->
    sort = newSort

  # tick function for force directed layout
  forceTick = (e) ->
    node
      .attr("cx", (d) -> d.x)
      .attr("cy", (d) -> d.y)

    link
      .attr("x1", (d) -> d.source.x)
      .attr("y1", (d) -> d.source.y)
      .attr("x2", (d) -> d.target.x)
      .attr("y2", (d) -> d.target.y)

  # tick function for radial layout
  radialTick = (e) ->
    node.each(moveToRadialLayout(e.alpha))

    node
      .attr("cx", (d) -> d.x)
      .attr("cy", (d) -> d.y)

    if e.alpha < 0.03
      force.stop()
      updateLinks()

  # Adjusts x/y for each node to
  # push them towards appropriate location.
  # Uses alpha to dampen effect over time.
  moveToRadialLayout = (alpha) ->
    k = alpha * 0.1
    (d) ->
      centerNode = groupCenters(d.name)
      d.x += (centerNode.x - d.x) * k
      d.y += (centerNode.y - d.y) * k


  # Helper function that returns stroke color for
  # particular node.
  strokeFor = (d) ->
    d3.rgb(nodeColors(d.name)).darker().toString()

  format = (a, b) ->
    ret = '<p class="main" style="text-align: left;"><strong>' + a
    ret += '</strong>&nbsp;&nbsp;&nbsp;&nbsp;' + b
    ret += '</span></p>'
    
  jumpUniprot = (id) -> 
    ret = '<a href="http://www.uniprot.org/uniprot/' + id + '" target="_blank">' + id + '</a>'
  jumpPubchem = (id) -> 
    ret = '<a href="http://pubchem.ncbi.nlm.nih.gov/compound/' + id + '" target="_blank">' + id + '</a>'
    
  # Mouseover tooltip function#################################show node details
  showDetails = (d,i) ->
    if d.type == 'protein'
      content = format("UniProt ID", jumpUniprot(d.id))
      content += '<hr class="tooltip-hr">'
      content += format("Name", d.name)
      content += '<hr class="tooltip-hr">'
      content += format("UniProtKB", d.uniprotKB)
      content += '<hr class="tooltip-hr">'
      content += format("Organism", d.organism)
    else
      content = format("PubChem ID", jumpPubchem(d.id))
      content += '<hr class="tooltip-hr">'
      content += format("Name", d.name)
      content += '<hr class="tooltip-hr">'
      content += format("Molecular Weight", d.molecularWeight)
      content += '<hr class="tooltip-hr">'
      content += format("SMILES", d.smilesString)
    tooltip.showTooltip(content,d3.event)
    # higlight connected links
    if link
      link.attr("stroke", (l) ->
        if l.source == d or l.target == d then "#555" else "#ddd"
      )
        .attr("stroke-opacity", (l) ->
          if l.source == d or l.target == d then 1.0 else 0.5
        )
        .style("stroke", (l) ->
          if l.source == d or l.target == d then "#555" else "#ddd")

      # link.each (l) ->
      #   if l.source == d or l.target == d
      #     d3.select(this).attr("stroke", "#555")

    # highlight neighboring nodes
    # watch out - don't mess with node if search is currently matching
    node.style("stroke", (n) ->
      if (n.searched or neighboring(d, n)) then "#555" else strokeFor(n))
      .style("stroke-width", (n) ->
        if (n.searched or neighboring(d, n)) then 2.0 else 1.0)
  
    # highlight the node being moused over
    d3.select(this).style("stroke","black")
      #.style("stroke-width", 2.0)
      
  # Mouseover tooltip function Link################################show link details
  showDetailsLink = (d,i) ->
    if d.attribute == 'CPI'
      content = format("Integrative", d.combined_score)
      content += '<hr class="tooltip-hr">'
      content += format("Kiba", d.kiba)
      content += '<hr class="tooltip-hr">'
      content += format("Drugbank", d.drugbank)
      content += '<hr class="tooltip-hr">'
      content += format("STITCH", d.stitch)
      content += '<hr class="tooltip-hr">'
      content += format("DThybrid", d.dthybrid)
      content += '<hr class="tooltip-hr">'
      content += format("KBMF", d.kbmf)
      content += '<hr class="tooltip-hr">'
      content += format("MinHash", d.minhash)
      content += '<hr class="tooltip-hr">'
      content += format("RLS", d.rls)
      content += '<hr class="tooltip-hr">'
      content += format("SVM", d.svm)
    else
      content = format("Substructure", d.sub_structure)
    tooltip.showTooltip(content,d3.event)
  
  	# highlight connected nodes
    if node
      node.attr("stroke", (n) ->
        if d.source == n or d.target == n then "#555" else strokeFor(n)
      )
        .style("stroke-width", (n) ->
          if d.source == n or d.target == n then 2.0 else 1.0
        )
  	
    # highlight the link being moused over
    d3.select(this).style("stroke","#555")
      #.style("stroke-width", 2.0)
###################################################################
  # Mouseout function
  hideDetails = (d,i) ->
    tooltip.hideTooltip()
    # watch out - don't mess with node if search is currently matching
    node.style("stroke", (n) -> if !n.searched then strokeFor(n) else "#555")
      .style("stroke-width", (n) -> if !n.searched then 1.0 else 2.0)
    if link
      link.attr("stroke", "#ddd")
        .attr("stroke-opacity", 0.8)
        .style("stroke","#ddd")
		
  hideDetailsLink = (d,i) ->
    tooltip.hideTooltip()
    link.attr("stroke", "#ddd")
        .attr("stroke-opacity", 0.8)
        .style("stroke","#ddd")
    # watch out - don't mess with node if search is currently matching
    if node
	    node.style("stroke", (n) -> if !n.searched then strokeFor(n) else "#555")
	      .style("stroke-width", (n) -> if !n.searched then 1.0 else 2.0)
      
        
  # Final act of Network() function is to return the inner 'network()' function.
  return network

# Activate selector button
activate = (group, link) ->
  d3.selectAll("##{group} a").classed("active", false)
  d3.select("##{group} ##{link}").classed("active", true)

$ ->
  myNetwork = Network()

  d3.selectAll("#layouts a").on "click", (d) ->
    newLayout = d3.select(this).attr("id")
    activate("layouts", newLayout)
    myNetwork.toggleLayout(newLayout)

  d3.selectAll("#filters a").on "click", (d) ->
    newFilter = d3.select(this).attr("id")
    activate("filters", newFilter)
    myNetwork.toggleFilter(newFilter)

  d3.selectAll("#sorts a").on "click", (d) ->
    newSort = d3.select(this).attr("id")
    activate("sorts", newSort)
    myNetwork.toggleSort(newSort)

  $("#score_select").on "change", (e) ->
    songFile = $(this).val()
    d3.json "data/#{songFile}", (json) ->
      myNetwork.updateData(json)
  
  $("#search").keyup () ->
    searchTerm = $(this).val()
    myNetwork.updateSearch(searchTerm)

#parent.curId is the js id
  d3.json "data/data_CPI_" + parent.curId + "_combined_score.json", (json) ->
    myNetwork("#vis_cpi", json)
