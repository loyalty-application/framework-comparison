public class Transaction
{
    public int id { get; set; }
    public string? transaction_id { get; set; }
    public string? merchant { get; set; }
    public string? mcc { get; set; }
    public string? currency { get; set; }
    public double amount { get; set; }
    public string? transaction_date { get; set; }
    public string? card_id { get; set; }
    public string? card_pan { get; set; }
    public string? card_type { get; set; }
}
