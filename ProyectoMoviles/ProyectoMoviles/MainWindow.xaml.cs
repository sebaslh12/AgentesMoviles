using ProyectoMoviles.Model;
using ProyectoMoviles.Views;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ProyectoMoviles
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public AppMoviles App{ get; set; }
        public MainWindow()
        {
            App = new AppMoviles();
            this.DataContext = App;
            InitializeComponent();
        }

        public void MessageView(object sender, RoutedEventArgs e) {
            //Contact c = sender as Contact;
            
            //Contact c =  (Contact)Users.SelectedItems[0];
            //Messages MessagePage = new Messages(c.userId);
            //App.Current.MainWindow = MessagePage;
            //this.Close();
            //MessagePage.Show();
        }
    }
}
