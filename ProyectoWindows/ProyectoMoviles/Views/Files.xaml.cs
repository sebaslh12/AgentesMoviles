﻿using System;
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
        public Files(int ID)
        {
            UserId = ID;
            InitializeComponent();
            InitializeComponent();
        }
    }
}
