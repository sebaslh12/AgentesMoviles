namespace ProyectoMoviles.Model
{    
    public class Message
    {
        public int id { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public string text { get; set; }
        public string date { get; set; }

        public Message(int from, int to, string text)
        {
            this.from = from;
            this.to = to;
            this.text = text;
        }
    }
}
