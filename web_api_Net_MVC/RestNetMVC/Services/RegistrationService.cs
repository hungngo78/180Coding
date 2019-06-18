using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using RestNetMVC.Models;

namespace RestNetMVC.Services
{
    public class RegistrationService : IRegistrationService
    {

        public void Add(Customer cus)
        {
            CustomerManager.getInstance().AddCustomer(cus);
        }

        public String Remove(String registrationNumber)
        {
            return CustomerManager.getInstance().RemoveCustomer(registrationNumber);            
        }

        public List<Customer> getAllCustomers()
        {
            return CustomerManager.getInstance().getAllCustomers();           
        }

        public String UpdateCustomer(Customer cus)
        {
            return CustomerManager.getInstance().UpdateCustomer(cus);      
        }
    }
}