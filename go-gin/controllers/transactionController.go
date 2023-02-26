package controllers

import (
	"net/http"
	"os"

	"github.com/framework-comparison/framework-comparison/models"
	"github.com/gin-gonic/gin"
	"github.com/gocarina/gocsv"
)

func UploadTransactionFile(c *gin.Context) {

	var fileObject models.TransactionFile

	if err := c.ShouldBind(&fileObject); err != nil {
		c.String(http.StatusBadRequest, "Bad Request")

	}

	// save csv file
	filename := "./uploads/" + fileObject.File.Filename
	err := c.SaveUploadedFile(fileObject.File, filename)
	if err != nil {
		c.String(http.StatusInternalServerError, "Failed to upload file"+err.Error())
		return
	}

	transactionFile, err := os.OpenFile(filename, os.O_RDWR|os.O_CREATE, os.ModePerm)
	if err != nil {
		panic(err)
	}
	defer transactionFile.Close()

	transactions := []*models.TransactionRow{}
	if err := gocsv.UnmarshalFile(transactionFile, &transactions); err != nil { // Load clients from file
		panic(err)
	}

	// sum up all the values by merchant A
	var totalTransactionAmountByA float32 = 0
	for _, transaction := range transactions {
		if transaction.Merchant == "A" {
			totalTransactionAmountByA += transaction.Amount
		}
	}

	// repond with id
	c.JSON(http.StatusOK, gin.H{
		"total": totalTransactionAmountByA,
	})
}

type TransactionObjList struct {
	Transactions []models.TransactionObj `json:"transactions"`
}

func CreateTransaction(c *gin.Context) {
	data := new(TransactionObjList)
	err := c.BindJSON(data)
	if err != nil {
		c.String(http.StatusInternalServerError, "Bad Request,"+err.Error())
		return
	}

	// iterate over all transactions and output the result
	var totalTransactionAmountByA float32 = 0
	for _, transaction := range data.Transactions {
		if transaction.Merchant == "A" {
			totalTransactionAmountByA += transaction.Amount
		}
	}

	// return it
	c.JSON(200, gin.H{
		"total": totalTransactionAmountByA,
	})

}
