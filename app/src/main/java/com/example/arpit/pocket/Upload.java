package com.example.arpit.pocket;

public class Upload {

    private String name;
    private String imageUrl;
    private String purchaseDate;
    private String returnDate;
    private String description;
    private String spinnerItem;


    public Upload(){

    }

    public Upload(String name, String imageurl, String purchase_date, String return_date, String spinner_item, String Description){

        if(name.trim().equals("")){
            name="No Name";
        }

        this.name = name;
        this.imageUrl = imageurl;
        this.purchaseDate = purchase_date;
        this.returnDate = return_date;
        this.spinnerItem = spinner_item;
        this.description = Description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpinnerItem() {
        return spinnerItem;
    }

    public void setSpinnerItem(String spinnerItem) {
        this.spinnerItem = spinnerItem;
    }

//    public String getmName(){
//        return mName;
//    }
//
//    public void setMname(String name){
//        mName = name;
//    }

//
//    public String getimageUrl(){
//        return mImageUrl;
//    }
//
//    public void setimageUrl(String ImageUrl){
//        mImageUrl = ImageUrl;
//    }
//
//
//    public String getPurchaseDate(){
//        return mPurchase_date;
//    }
//
//    public void setPurchaseDate(String purchase_date){
//        mPurchase_date = purchase_date;
//    }
//
//
//    public String getReturnDate(){
//        return mReturn_date;
//    }
//
//    public void setReturnDate(String return_date){
//        mReturn_date = return_date;
//    }
//
//
//    public String getSpinnerItem(){
//        return mSpinnerItem;
//    }
//
//    public void setSpinnerItem(String spinner_item){
//        mSpinnerItem = spinner_item;
//    }
//
//
//    public String getDescription(){
//        return mDescription;
//    }
//
//    public void setDescription(String description){
//        mDescription = description;
//    }


}
