// APIDTO represents the complete API json response
// It is composed of a UserDTO and SupportDTO

import java.util.ArrayList;

public class APIDTO {
    int page;
    int per_page;
    int total;
    int total_pages;
    ArrayList<UserDTO> data = new ArrayList<UserDTO>();
    SupportDTO support;
}
