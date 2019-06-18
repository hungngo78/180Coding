using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using RestNetMVC.Models;
using RestNetMVC.Services;

namespace RestNetMVC.Controllers
{
    public class CustomerUpdateController : ApiController
    {
        // inject the dependency for the IRegistrationService  
        private readonly IRegistrationService service;

        public CustomerUpdateController(IRegistrationService service)
        {
            this.service = service;
        }


        [Route("customer/update")]
        [HttpPut]
        public String UpdateCustomer(Customer cusn)
        {
            Console.WriteLine("In updateCustomer");
            return service.UpdateCustomer(cusn);
        }
    }
}
