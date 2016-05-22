using ProyectoMoviles.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ProyectoMoviles.Views
{
    /// <summary>
    /// Interaction logic for Messages.xaml
    /// </summary>
    public partial class Messages : Window
    {
        private MessageApp MessagesContext { get; set; }
        private int UserId { get; set; }
        private Contact ContactInfo { get; set; }
        public Messages(Contact c)
        {
            this.ContactInfo = c;
            MessagesContext = new MessageApp(c.userId,c.userName);
            this.DataContext = MessagesContext;
            InitializeComponent();
        }

        private void SendMessage(object sender, RoutedEventArgs e)
        {
            string toSend = MBody.Text;
            if (toSend != "")
            {
                ServicesConsume.PostMessages(ContactInfo.userId, toSend);
                Message tmp = new Message(ContactInfo.userId,3,toSend);
                List<Message> List = new List<Message>();
                List.Add(tmp);
                MessagesContext.insertMessages(List);
                MBody.Text = "";
            }
            else
            {
                MessageBox.Show("No puedes enviar un mensaje vacío");
            }

        }
    }
}
