from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import Controller, OVSSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel, info

import os

class FatTree(Topo):
    CoreSwitchList = []
    AggSwitchList = []
    EdgeSwitchList = []
    HostList = []
    sNum = 0

    def __init__(self, k=4, **opts):
        self.pod = k
        self.iCoreLayerSwitch = (k/2)**2
        self.iAggLayerSwitch = k*k/2
        self.iEdgeLayerSwitch = k*k/2
        self.density = k/2
        self.iHost = self.iEdgeLayerSwitch * self.density

        Topo.__init__(self, **opts)

        self.createTopo()
        info("Finished topology creation!\n")

        self.createLink()
        info("Finished adding links!\n")


    def createTopo(self):
        self.createCoreLayerSwitch(self.iCoreLayerSwitch)
        self.createAggLayerSwitch(self.iAggLayerSwitch)
        self.createEdgeLayerSwitch(self.iEdgeLayerSwitch)
        self.createHost(self.iHost)


    #def _addSwitch(self, number, level, switch_list):
    def _addSwitch(self, number, switch_list):
        for x in xrange(1, number+1):
            self.sNum += 1
            switch_list.append(self.addSwitch('s%s' % (self.sNum)))

    def createCoreLayerSwitch(self, number):
        info("Create Core Layer\n")
        #self._addSwitch(number, 'c', self.CoreSwitchList)
        self._addSwitch(number, self.CoreSwitchList)

    def createAggLayerSwitch(self, number):
        info("Create Aggregator Layer\n")
        #self._addSwitch(number, 'a', self.AggSwitchList)
        self._addSwitch(number, self.AggSwitchList)

    def createEdgeLayerSwitch(self, number):
        info("Create Edge Layer\n")
        #self._addSwitch(number, 'e', self.EdgeSwitchList)
        self._addSwitch(number, self.EdgeSwitchList)


    def createHost(self, number):
        info("Create Host\n")
        for x in xrange(1, number+1):
            self.HostList.append(self.addHost('h%s' % x))


    def createLink(self):       
        end = self.pod/2
		
        info("Add link core to aggregator\n")
        for x in xrange(0, self.iAggLayerSwitch, end):
            for i in xrange(0, end):
                for j in xrange(0, end):
                    self.addLink(self.CoreSwitchList[i*end+j], self.AggSwitchList[x+i])

        info("Add link aggregator to edge\n")
        for x in xrange(0, self.iAggLayerSwitch, end):
            for i in xrange(0, end):
                for j in xrange(0, end):
                    self.addLink(self.AggSwitchList[x+i], self.EdgeSwitchList[x+j])
        
        info("Add link edge to host\n")
        for x in xrange(0, self.iEdgeLayerSwitch):
            for i in xrange(0, self.density):
                self.addLink(self.EdgeSwitchList[x], self.HostList[self.density * x + i])

topos = {'fat_tree': (lambda k: FatTree(k))}