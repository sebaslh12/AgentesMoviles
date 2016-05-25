using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Linq;
using System.Net;
using System.Text;
using System.IO;
using RestSharp;

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
        #region Messages
        public static List<Message> GetMessages(int userId)
        {
            string url = "http://localhost:8191/";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Add(
               new MediaTypeWithQualityHeaderValue("application/json"));

            HttpResponseMessage response = client.GetAsync("rest/messages/" + userId.ToString()+"/3").Result;
            if (response.IsSuccessStatusCode)
            {
                var messages = response.Content.ReadAsAsync<IEnumerable<Message>>().Result;
                return messages.ToList();
            }
            else
            {
                List<Message> failed = new List<Message>();
                return failed;
            }
        }


        public static void PostMessages(int userId, string text)
        {
            string url = "http://localhost:8191/";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(url);
            Message mtemp = new Message(3,userId,text);
            client.PostAsJsonAsync("rest/messages/",mtemp).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());

        }
        #endregion
        #region Files
        public static List<File> GetFiles(int userID) {
            string url = "http://localhost:8191/";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Add(
               new MediaTypeWithQualityHeaderValue("application/json"));

            HttpResponseMessage response = client.GetAsync("rest/shared_files/3/" + userID.ToString()).Result;
            if (response.IsSuccessStatusCode)
            {
                var Files = response.Content.ReadAsAsync<IEnumerable<File>>().Result;
                return Files.ToList();
            }
            else
            {
                List<File> failed = new List<File>();
                return failed;
            }
        }
        //File id
        public static void DownloadFile(int id,string name)
        {
            using (var client = new WebClient())
            {
                client.DownloadFile("http://localhost:8191/rest/files/"+id.ToString(), name);
            }
        }
        public static bool PostFile(string Path, int id)
        {
            var client = new RestClient("http://localhost:8191");
            var upload = new RestRequest("rest/files/3/" + id.ToString(), Method.POST);
            bool rest = false;
            upload.AddFile("file", Path);
            var result = client.ExecuteAsync(upload, (response) =>
            {
                if (response.StatusCode == HttpStatusCode.OK)
                {
                    rest = true;
                }
                else
                {
                    rest = false;
                }
            });
            return rest;
        }    
    }
    #endregion
}
