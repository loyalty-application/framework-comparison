using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using asp_dot_net.Models;
using System.Net;

namespace asp_dot_net.Controllers;

[ApiController]
public class TransactionController : ControllerBase

{
    [HttpPost("/transaction")]
    public HttpResponseMessage createTransaction(HttpRequestMessage request)
    {
        var output = request.Content.ReadFromJsonAsync<String, String>();

        if (transactions == null)
        {
            return new HttpResponseMessage(System.Net.HttpStatusCode.BadRequest);
        }

        double total = 0;
        foreach (Transaction item in transactions)
        {
            if (item.merchant != null && item.merchant.Equals("A"))
            {
                total += item.amount;
            }

        }

        return request.CreateResponse(HttpStatusCode.OK, )
    }

    [HttpGet("/")]
    public String home()
    {

        return "Hello";
    }
}
