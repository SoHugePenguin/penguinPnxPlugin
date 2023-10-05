package com.penguin.pojo;

import lombok.Data;

@Data
public class BuildInfo {
    String location;
    String block;
    double max_x;
    double max_y;
    double max_z;

    public BuildInfo() {
    }
}
