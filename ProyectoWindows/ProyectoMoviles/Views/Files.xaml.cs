using Microsoft.Win32;
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
    /// Interaction logic for Files.xaml
    /// </summary>
    public partial class Files : Window
    {
        private int UserId { get; set; }
        private FilesApp FilesContext { get; set; }
        public Files(Contact c)
        {
            FilesContext = new FilesApp(c.userId,c.userName);
            UserId = c.userId;
            this.DataContext = FilesContext;
            InitializeComponent();
        }

        private void OpenFile(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == true)
                if(ServicesConsume.PostFile(openFileDialog.FileName, this.UserId))
                {
                    FilesContext.updateFiles(this.UserId);
                }            
        }
    }
}
