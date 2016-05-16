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
        public MessageCommand MessageC { get; set; }
        public FilesCommand FilesC { get; set; }

        public Contact(int id, string name, string username)
        {
            this.userId = id;
            this.nombre = name;
            this.userName = username;
            this.MessageC = new MessageCommand();
            this.FilesC = new FilesCommand();
        }
    }
}
