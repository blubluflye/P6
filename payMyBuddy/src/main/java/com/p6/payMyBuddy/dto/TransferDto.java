package com.p6.payMyBuddy.dto;

import lombok.Builder;

@Builder
public record TransferDto(String username, float money, String description) {}
