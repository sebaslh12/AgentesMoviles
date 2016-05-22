using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    class MessageApp : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private List<string> _Messages { get; set; }
        public string Name { get; set; }
        public int ID;
        public List<string> Messages
        {
            get { return _Messages; }
            set
            {
                _Messages = value;
                if(PropertyChanged != null)
                {
                    PropertyChanged(this, new PropertyChangedEventArgs("Messages"));
                }
            }
        }
        public MessageApp(int id, string name)
        {
            this.Messages = new List<string>();
            this.Name = name;
            List<Message> MessageService = ServicesConsume.GetMessages(id);
            DB.createTable();
            this.insertMessages(MessageService);
            this.ID= id;
            this.messagesFrom(id);
        }

        public void messagesFrom(int from)
        {
            this.Messages = DB.findMessages(from);
        }

        public void insertMessages(List<Message> messages)
        {
            foreach (Message m in messages)
            {
                DB.insertMessages(m);
            }
            messagesFrom(this.ID);
        }
    }
}
