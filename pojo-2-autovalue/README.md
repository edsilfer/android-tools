# POJO 2 Autovalue
## Introduction
Sometimes when dealing with legacy systems in Java language it might be useful to add imutability feature to certain classes. This taks can quickly become boring and, for being predictable and repetitive, it is an excelent candidate for being automated. This repository provides a script that, given a path containing a set of POJO classes, convert then to Autovalue classes.

## How it works?
1. Clone the repository and navigate to the downloaded folder;
2. Run the command: ```python pojo2autovalue.py path_to_pojo_classes```;
3. Checkout the result classes on repository_path/output;

### Sample converted class

**input**
```java
package com.edsilfer.android.domain.entity;


import java.util.Date;

public class Address {

    private String streetAddress1;
    private String neighborhood;
    private City city;
    private String zipCode;

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

```

**output**
```java
package com.edsilfer.android.domain.entity;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class JsonAddress {

    @Nullable @SerializedName("streetAddress1") public abstract String streetAddress1();
	@Nullable @SerializedName("neighborhood") public abstract String neighborhood();
	@Nullable @SerializedName("city") public abstract City city();
	@Nullable @SerializedName("zipCode") public abstract String zipCode();

    public static TypeAdapter<JsonAddress> typeAdapter(Gson gson) {
        return new AutoValue_JsonAddress.GsonTypeAdapter(gson);
    }

}
```

## Customization
In order to perform customations on the output file, edit the file at ```resources/AutovalueClassTemplate.txt```;

# License
Copyright 2017 Edgar da Silva Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
