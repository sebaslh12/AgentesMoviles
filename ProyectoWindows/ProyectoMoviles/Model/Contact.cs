using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class Contact
    {
        public int userId { get; set; }
        public string userName { get; set; }
        public string nombre { get; set; }
        public MessagesPageCommand MessageC { get; set; }
        public FilesPageCommand FilesC { get; set; }

        public Contact(int id, string name, string username)
        {
            this.userId = id;
            this.nombre = name;
            this.userName = username;
            //Commands to track the contacta that has been selected
            this.MessageC = new MessagesPageCommand();
            this.FilesC = new FilesPageCommand();
        }
    }
}
