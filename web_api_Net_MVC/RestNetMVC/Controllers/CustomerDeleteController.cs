using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using RestNetMVC.Services;

namespace RestNetMVC.Controllers
{
    public class CustomerDeleteController : ApiController
    {
        // inject the dependency for the IRegistrationService  
        private readonly IRegistrationService service;

        //inject dependency 
        public CustomerDeleteController(IRegistrationService service)
        {
            this.service = service;
        }

        [Route("customer/remove/{regdNum}")]
        [HttpDelete]
        public String DeleteCustomer(String regdNum)
        {
            Console.WriteLine("In DeleteCustomer");
            return service.Remove(regdNum);
        }
    }
}
