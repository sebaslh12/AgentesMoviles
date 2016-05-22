using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class AppMoviles
    {
        public List<Contact> Contacts { get; set; }

        public AppMoviles() {
            this.Contacts = ServicesConsume.GetContactList(3);
        }
    }
}
