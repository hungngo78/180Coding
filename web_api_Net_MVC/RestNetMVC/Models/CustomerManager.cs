using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RestNetMVC.Models
{
    public class CustomerManager
    {
        private List<Customer> customerList;
        static CustomerManager cusMan = null;

        private CustomerManager()
        {
            customerList = new List<Customer>();
        }
        public static CustomerManager getInstance()
        {
            if (cusMan == null)
            {
                cusMan = new CustomerManager();
                return cusMan;
            }
            else
            {
                return cusMan;
            }
        }

        public void AddCustomer(Customer cus)
        {
            customerList.Add(cus);
        }

        public String RemoveCustomer(String registrationNumber)
        {
            for (int i = 0; i < customerList.Count; i++)
            {
                Customer cus = customerList.ElementAt(i);
                if (cus.RegistrationNumber.Equals(registrationNumber))
                {
                    customerList.RemoveAt(i);
                    return "Delete successful";
                }
            }

            return "Delete un-successful";
        }

        public List<Customer> getAllCustomers()
        {
            return customerList;
        }

        public String UpdateCustomer(Customer _customer)
        {
            for (int i = 0; i < customerList.Count; i++)
            {
                Customer cus = customerList.ElementAt(i);
                if (cus.RegistrationNumber.Equals(_customer.RegistrationNumber))
                {
                    //update by the new record
                    customerList[i] = _customer;
                    return "Update successful";
                }
            }

            return "Update un-successful";
        }
    }
}