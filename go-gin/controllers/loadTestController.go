package controllers

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

func UploadCSVFile(c *gin.Context) {
	// get the parameter
	id := c.Param("id")

	// do some processing here
	response := "your id was %s"
	response = fmt.Sprintf(response, id)

	// repond with id
	c.JSON(http.StatusOK, gin.H{
		"message": response,
	})
}
