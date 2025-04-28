package com.unitedpets;

public class Pet {
    private String petname, breed, gender, health1, health3, location, ownername, ownermail;
    private Long contactno;
    private int age;
    private String photoName;

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }


    public String getpetname()
    { 
    	return petname; 
    	}
    public void setpetname(String petname) { this.petname = petname; }


    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
	public String getHealth1() {
		return health1;
	}
	public void setHealth1(String health1) {
		this.health1 = health1;
	}
	public String getHealth3() {
		return health3;
	}
	public void setHealth3(String health3) {
		this.health3 = health3;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public Long getContactno() {
		return contactno;
	}
	public void setContactno(Long contactno) {
		this.contactno = contactno;
	}
	public String getOwnermail() {
		return ownermail;
	}
	public void setOwnermail(String ownermail) {
		this.ownermail = ownermail;
	}


	

}