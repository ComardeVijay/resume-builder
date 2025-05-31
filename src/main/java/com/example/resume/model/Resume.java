package com.example.resume.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Resume {
    private String name;
    private String email;
    private String phone;
    private String summary;

    private List<String> skills = new ArrayList<>();
    private List<String> education = new ArrayList<>();
    private List<String> experience = new ArrayList<>();
    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}

	public List<String> getExperience() {
		return experience;
	}

	public void setExperience(List<String> experience) {
		this.experience = experience;
	}

	public Resume(String name, String email, String phone, String summary,
                  List<String> skills, List<String> education, List<String> experience) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
        this.skills = skills != null ? skills : new ArrayList<>();
        this.education = education != null ? education : new ArrayList<>();
        this.experience = experience != null ? experience : new ArrayList<>();
    }
	
    
    public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", summary='" + summary + '\'' +
                ", skills=" + skills +
                ", education=" + education +
                ", experience=" + experience +
                '}';
    }
}
