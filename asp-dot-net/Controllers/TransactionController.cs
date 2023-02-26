using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;
using Microsoft.Extensions.Hosting.Internal;
using System.Formats.Asn1;
using System.Diagnostics;

namespace asp_dot_net.Controllers;

[ApiController]
public class TransactionController : ControllerBase

{
    [HttpPost]
    [Route("/transactions")]
    public IActionResult createTransactions([FromBody] JObject transaction)
    {
        try
        {
            double sum = 0;
            JArray jArray = transaction["transactions"] as JArray;
            foreach (JObject item in jArray)
            {
                if (item["merchant"].ToString() == "A")
                {
                    string amt2 = item["amount"].ToString();
                    double amt = Convert.ToDouble(item["amount"].ToString());
                    sum += amt;
                }
            }

            return Ok(sum);
        }
        catch (Exception e)
        {
            return BadRequest("Fail to calculate amount!");
        }
    }

    [HttpPost]
    [Route("/transactions/file")]
    [DisableRequestSizeLimit]
    public async Task<IActionResult> createTransactionsWithFile(IFormFile file)
    {
        double sum = 0;

        try
        {
            Console.WriteLine(file.ToString());
            using var memoryStream = new MemoryStream(new byte[file.Length]);
            await file.CopyToAsync(memoryStream);
            memoryStream.Position = 0;

            using (var fileStream = file.OpenReadStream())
            using (var reader = new StreamReader(fileStream))
            {
                string row;
                int x = 0;
                while ((row = reader.ReadLine()) != null)
                {
                    if (x++ > 0)
                    {
                        string[] cols = row.Split(",");
                        Console.WriteLine(cols[2]);
                        if (cols[2] == "A")
                        {
                            double amt = Convert.ToDouble(cols[5].ToString());
                            sum += amt;
                        }
                    }
                }
            }
        }
        catch
        {
            return BadRequest();
        }
        return Ok(sum);
    }

    [HttpGet("/")]
    public String home()
    {

        return "Hello";
    }
}
