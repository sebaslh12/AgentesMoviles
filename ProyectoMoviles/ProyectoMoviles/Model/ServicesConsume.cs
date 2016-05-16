using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Linq;

namespace ProyectoMoviles.Model
{
    public static class ServicesConsume
    {
        public static List<Contact> GetContactList(int userId)
        {
            string url = "http://localhost:8191/";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Add(
               new MediaTypeWithQualityHeaderValue("application/json"));

            HttpResponseMessage response = client.GetAsync("rest/contacts/" + userId.ToString()).Result;
            if (response.IsSuccessStatusCode)
            {
                var contacts = response.Content.ReadAsAsync<IEnumerable<Contact>>().Result;
                return contacts.ToList();
            }
            else
            {
                List<Contact> failed = new List<Contact>();
                return failed;
            }
        }
    }
}
