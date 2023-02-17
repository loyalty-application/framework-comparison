package models

import "mime/multipart"

type TransactionFile struct {
	File *multipart.FileHeader `form:"file" binding:"required"`
}

type TransactionObj struct {
	Id              int     `json:"id"`
	TransactionId   string  `json:"transaction_id"`
	Merchant        string  `json:"merchant"`
	MCC             string  `json:"mcc"`
	Currency        string  `json:"currency"`
	Amount          float32 `json:"amount"`
	TransactionDate string  `json:"transaction_date"`
	CardId          string  `json:"card_id"`
	CardPan         string  `json:"card_pan"`
	CardType        string  `json:"card_type"`
}

type TransactionRow struct { // Our example struct, you can use "-" to ignore a field
	Id              int     `csv:"id"`
	TransactionId   string  `csv:"transaction_id"`
	Merchant        string  `csv:"merchant"`
	MCC             string  `csv:"mcc"`
	Currency        string  `csv:"currency"`
	Amount          float32 `csv:"amount"`
	TransactionDate string  `csv:"transaction_date"`
	CardId          string  `csv:"card_id"`
	CardPan         string  `csv:"card_pan"`
	CardType        string  `csv:"card_type"`
}
