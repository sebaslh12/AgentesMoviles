using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class FilesApp : INotifyPropertyChange
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private List<File> _Files{ get; set; }
        public string Name { get; set; }
        public int ID;
        public List<File> Files
        {
            get { return _Files; }
            set
            {
                _Files = value;
                if (PropertyChanged != null)
                {
                    PropertyChanged(this, new PropertyChangedEventArgs("Files"));
                }
            }
        }

        public FilesApp(int id, string name)
        {
            this.ID = id;
            this.Name = name;
            this.Files = ServicesConsume.GetFiles(id);
        }

    }
}
