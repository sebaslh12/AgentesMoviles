using ProyectoMoviles.Views;
using System;
using System.Windows.Input;

namespace ProyectoMoviles.Model
{
    public class FilesPageCommand : ICommand
    {
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            Contact c = (Contact)parameter;
            Files FilesPage = new Files(c.userId);
            App.Current.MainWindow = FilesPage;
            FilesPage.Show();
        }
    }
}
