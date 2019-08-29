package io.microservices.beanvalidation;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;*/
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import io.microservices.beanvalidation.service.OnCreate;
import io.microservices.beanvalidation.service.OnUpdate;

//@Entity
public class InputWithCustomValidatorData {

  //@Id
  //@GeneratedValue
  @NotNull(groups = OnUpdate.class)
  @Null(groups = OnCreate.class)
  private Long id;

  @Min(groups={OnCreate.class,OnUpdate.class},value=1)/*This will not work in case of common validation if no group is mentioned in @Validate annotation on calling method. In this case calling method is validateForCreate*/
  @Max(groups={OnUpdate.class},value=10)/*This will not work in case of common validation if no group is mentioned in @Validate annotation on calling method. In this case calling method is validateForCreate*/
  //@Min(value=1)/* do not add groups if common validation for all groups is required. Also calling method should not contain group in @Validate annotation. In this case calling method is validateForCreate*/
  //@Max(value=10)/* do not add groups if common validation for all groups is required. Also calling method should not contain group in @Validate annotation. In this case calling method is validateForCreate*/  
  //@Column
  private int numberBetweenOneAndTen;

  @IpAddress(groups={OnUpdate.class}) //will work only on OnUpdate group
  //@IpAddress /* do not add groups if common validation for all groups is required. Also calling method should not contain group in @Validate annotation. In this case calling method is validateForCreate*/
  //@Column
  private String ipAddress;

  @Valid
  private InputData inputd;
  
  public int getNumberBetweenOneAndTen() {
    return numberBetweenOneAndTen;
  }

  public void setNumberBetweenOneAndTen(int numberBetweenOneAndTen) {
    this.numberBetweenOneAndTen = numberBetweenOneAndTen;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

public InputData getInputd() {
	return inputd;
}

public void setInputd(InputData inputd) {
	this.inputd = inputd;
}
}
