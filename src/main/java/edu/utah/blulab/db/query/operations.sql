CREATE TABLE `EpicMappedModifier` (
	`Id` int(9) NOT NULL AUTO_INCREMENT,
	`Type` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
    `Regex` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
    `Direction` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
    `Lex` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Epic_label` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Epic_code` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Standard_code` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Standard_label` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Standard_code_system` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	`Epic_code_system` varchar(255) COLLATE utf8mb4_unicode_ci NULL,
	 PRIMARY KEY (`Id`)
)
