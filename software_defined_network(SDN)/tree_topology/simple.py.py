from mininet.topo import Topo
  
class Simple(Topo):

    def build(self):
        host1 = self.addHost('h1')
        host2 = self.addHost('h2')

        switch = self.addSwitch('s3')

        self.addLink(host1, switch)
        self.addLink(host2, switch)


topos = {'simple': (lambda: Simple())}
