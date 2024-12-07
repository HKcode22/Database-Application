package dto;

public class UserResponseDTO {
    private int userId;
    private Integer customerId;

    public UserResponseDTO(int userId, Integer customerId) {
        this.userId = userId;
        this.customerId = customerId;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}

