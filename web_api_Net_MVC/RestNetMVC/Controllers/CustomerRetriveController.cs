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
    public class CustomerRetriveController : ApiController
    {
        // inject the dependency for the IRegistrationService  
        private readonly IRegistrationService service;

        //inject dependency 
        public CustomerRetriveController(IRegistrationService service)
        {
            this.service = service;
        }

        [Route("customer/get_all_customers")]
        [HttpGet]
        public List<Customer> GetAllCustomers()
        {
            return this.service.getAllCustomers();
        }
    }
}
