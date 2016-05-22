using ProyectoMoviles.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace ProyectoMoviles.Model
{
    public class MessagesPageCommand : ICommand
    {
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            Contact c = (Contact)parameter;
            Messages MessagePage = new Messages(c);
            App.Current.MainWindow = MessagePage;
            MessagePage.Show();
        }
    }
}
