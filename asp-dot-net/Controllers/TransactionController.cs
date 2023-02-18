using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using System.Linq;
using System.Net;
using System.Net.Http;
using Newtonsoft.Json.Linq;
using System.Web;
using System.IO;
using System.Numerics;

namespace asp_dot_net.Controllers;

[ApiController]
[Route("/api")]
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

    [HttpGet("/")]
    public String home()
    {

        return "Hello";
    }
}
