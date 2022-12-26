package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class JWT_Request {

	private String email;
    private String password;
    
    public JWT_Request() {}

    public JWT_Request(String email, String password) 
    {
        this.email = email;
        this.password = password;
    }
	
}
