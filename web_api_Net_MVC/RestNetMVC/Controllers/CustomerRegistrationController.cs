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
    public class CustomerRegistrationController : ApiController
    {
        // inject the dependency for the IRegistrationService  
        private readonly IRegistrationService service;

        public CustomerRegistrationController(IRegistrationService service)
        {
            this.service = service;
        }

        [Route("customer/register")]
        [HttpPost]
        public HttpCustomer RegisterCustomer(Customer customerRegd)
        {
            Console.WriteLine("In registerCustomer");
            HttpCustomer cusRegReply = new HttpCustomer();

            this.service.Add(customerRegd);
            cusRegReply.Name = customerRegd.Name;
            cusRegReply.Age = customerRegd.Age;
            cusRegReply.RegistrationNumber = customerRegd.RegistrationNumber;
            cusRegReply.RegistrationStatus = "Successful";

            return cusRegReply;
        }
    }
}
