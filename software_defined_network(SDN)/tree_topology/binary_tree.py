from mininet.topo import Topo

class BinaryTree(Topo):

    #def __init__(self, d):
     #   self.depth = d


    def build(self):
        host1 = self.addHost('h1')
        host2 = self.addHost('h2')
        host3 = self.addHost('h3')
        host4 = self.addHost('h4')

        switch1 = self.addSwitch('s1')
        switch2 = self.addSwitch('s2')
        switch3 = self.addSwitch('s3')

        self.addLink(switch2, switch1)
        self.addLink(switch3, switch1)
        self.addLink(host1, switch2)
        self.addLink(host2, switch2)
        self.addLink(host3, switch3)
        self.addLink(host4, switch3)


topos = {'binarytree': (lambda: BinaryTree())}
#topos = {'binarytree': (lambda d: BinaryTree(d))}
