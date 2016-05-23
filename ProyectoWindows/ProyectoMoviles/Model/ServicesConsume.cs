using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Linq;
using System.Net;
using System.Text;
using System.IO;

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

        public static List<File> GetFiles(int userID) {
            string url = "http://localhost:8191/";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(url);
            client.DefaultRequestHeaders.Accept.Add(
               new MediaTypeWithQualityHeaderValue("application/json"));

            HttpResponseMessage response = client.GetAsync("rest/shared_files/" + userID.ToString() + "/3").Result;
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
                client.DownloadFile("http://localhost:8191/rest/files"+id.ToString(), name);
            }
        }
        public static void PostFile(string Path, int id)
        {
            //var formContent = new FormUrlEncodedContent(new[]
            //{
            //    new KeyValuePair<string, string>("file", Path)
            //});

            //var myHttpClient = new HttpClient();
            //var response = myHttpClient.PostAsync("http://localhost:8191/rest/files/1" + id.ToString(), formContent);

            WebRequest webRequest = WebRequest.Create(new Uri("http://localhost:8191/rest/files/1" + id.ToString()));

            webRequest.ContentType = "application/x-www-form-urlencoded";
            webRequest.Method = "PUT";
            byte[] bytes = Encoding.ASCII.GetBytes(Path);

            Stream os = null;
            try
            { // send the Post
                webRequest.ContentLength = bytes.Length;   //Count bytes to send
                os = webRequest.GetRequestStream();
                os.Write(bytes, 0, bytes.Length);         //Send it
            }
            catch (WebException ex)
            {

            }
            finally
            {
                if (os != null)
                {
                    os.Close();
                }
            }

            try
            { // get the response
                WebResponse webResponse = webRequest.GetResponse();

                StreamReader sr = new StreamReader(webResponse.GetResponseStream());
            }
            catch (WebException ex)
            {
            }

            //string url = "http://localhost:8191/";
            //HttpClient client = new HttpClient();
            //client.BaseAddress = new Uri(url);
            //client.PostAsJsonAsync("/rest/files/1" + id.ToString(), Path).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());

        }
    }
}
