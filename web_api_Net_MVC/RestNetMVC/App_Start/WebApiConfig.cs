using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Web.Mvc;
using RestNetMVC.Services;
using Unity;
using Unity.AspNet.WebApi;
using Unity.Lifetime;

namespace RestNetMVC
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // create the dependencies by registering all your components with the container here
            var container = new UnityContainer();
            container.RegisterType<IRegistrationService, RegistrationService>(new HierarchicalLifetimeManager());          
            //DependencyResolver.SetResolver(new UnityResolver(container));
            //DependencyResolver.SetResolver(new UnityDependencyResolver(container));
            GlobalConfiguration.Configuration.DependencyResolver = new UnityDependencyResolver(container);

            // Web API configuration and services

            // Web API routes
            config.MapHttpAttributeRoutes();
            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );
        }
    }
}
