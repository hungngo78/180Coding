using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RestNetMVC.Models;

namespace RestNetMVC.Services
{
    public interface IRegistrationService
    {
        void Add(Customer cus);

        String Remove(String registrationNumber);

        List<Customer> getAllCustomers();

        String UpdateCustomer(Customer cus);
    }
}
