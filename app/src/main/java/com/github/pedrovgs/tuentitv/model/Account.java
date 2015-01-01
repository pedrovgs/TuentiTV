/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.model;

/**
 * Class created to represent user accounts. One user account user name and user avatar photo url.
 * This immutable object is going to be used from the UI layer and from the Accounts object to
 * return mocked data.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Account {

  private final String name;
  private final String avatarUrl;

  public Account(String name, String avatarUrl) {
    this.name = name;
    this.avatarUrl = avatarUrl;
  }

  public String getName() {
    return name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }
}
