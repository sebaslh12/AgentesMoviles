using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public static class DB
    {        
        //DB connection, static is the same in the entire app
        private static SQLiteConnection dbcon = new SQLiteConnection(@"Data Source=C:\Users\Sebas\Documents\U\Moviles\ProyectoMoviles\ProyectoMoviles\bin\Debug\Messages.db");

        public static void createTable() {
            //SQLiteCommand create = new SQLiteCommand("CREATE TABLE IF NOT EXISTS Message_Table(id INTEGER PRIMARY KEY autoincrement, to INTEGER, from INTEGER, text TEXT)", dbcon);
            //create.ExecuteNonQuery();    
            dbcon.Open();
            var command = dbcon.CreateCommand();
            command.CommandText = "CREATE TABLE IF NOT EXISTS Message_Table(Mto int, Mfrom int, text varchar(300))";
            command.ExecuteNonQuery();
            dbcon.Close();
        }

        public static void insertMessages(Message m){
            dbcon.Open();
            SQLiteCommand insert = new SQLiteCommand("INSERT INTO Message_Table(Mto,Mfrom,text) values (@MTO,@MFROM,@MTEXT)", dbcon);
            insert.Parameters.AddWithValue("MTO",m.to.ToString());
            insert.Parameters.AddWithValue("MFROM",m.from.ToString());
            insert.Parameters.AddWithValue("MTEXT",m.text);
            try{
                insert.ExecuteNonQuery();
            }
            catch (Exception ex)
            {

                throw new Exception(ex.Message);
            }
            dbcon.Close();            
        }

        public static List<string> findMessages(int from)
        {
            dbcon.Open();
            List<string> QueryResult = new List<string>();
            SQLiteCommand select = new SQLiteCommand("SELECT* FROM Message_Table WHERE Mfrom = ? and Mto = 3", dbcon);
            select.Parameters.AddWithValue("MFROM", from.ToString());
            //Iterator
            SQLiteDataReader reader = select.ExecuteReader();
            while (reader.Read())
            {
                QueryResult.Add(reader.GetString(2));
            }
            dbcon.Close();
            return QueryResult;

        }

    }
}
