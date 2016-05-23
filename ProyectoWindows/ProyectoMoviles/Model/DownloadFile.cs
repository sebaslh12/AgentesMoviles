using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace ProyectoMoviles.Model
{
    public class DownloadFile : ICommand
    {
        public event EventHandler CanExecuteChanged;

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            File f = (File)parameter;
            ServicesConsume.DownloadFile(f.id, f.name);
        }
    }
}
