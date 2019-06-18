using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceModel;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;
using soapWCFServiceLibrary;

namespace WindowsService1
{
    partial class MyWCFsoapWinSer : ServiceBase
    {
        public ServiceHost serviceHost = null;

        public MyWCFsoapWinSer()
        {
            //InitializeComponent();
            ServiceName = "Service3";
        }

        public static void Main()
        {
            ServiceBase.Run(new MyWCFsoapWinSer());
        }

        protected override void OnStart(string[] args)
        {
            if (serviceHost != null)
            {
                serviceHost.Close();
            }
            serviceHost = new ServiceHost(typeof(Service3));                 

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
