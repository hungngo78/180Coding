using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceProcess;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using RestWCFServiceLibrary;

namespace RestWCFWinService
{
    public partial class MyRestWCFRestWinSer : ServiceBase
    {
        public ServiceHost serviceHost = null;

        public MyRestWCFRestWinSer()
        {
            //ServiceName = "SignInService";
            ServiceName = "Service1";
            //ServiceName = "TweetService";            
        }
        public static void Main()
        {
            ServiceBase.Run(new MyRestWCFRestWinSer());
        }

        // Start the Windows service.
        protected override void OnStart(string[] args)
        {
            if (serviceHost != null)
            {
                serviceHost.Close();
            }
            serviceHost = new ServiceHost(typeof(Service1));
            //serviceHost = new ServiceHost(typeof(TweetService));       

            // Open the ServiceHostBase to create listeners and start 
            // listening for messages.
            serviceHost.Open();
        }
        protected override void OnStop()
        {
            if (serviceHost != null)
            {
                serviceHost.Close();
                serviceHost = null;
            }
        }
    }
}
