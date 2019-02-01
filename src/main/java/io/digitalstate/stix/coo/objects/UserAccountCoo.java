package io.digitalstate.stix.coo.objects;

import java.time.Instant;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.AccountTypes;

/**
 * user-account
 * <p>
 * The User Account Object represents an instance of any type of user account,
 * including but not limited to operating system, device, messaging service, and
 * social media platform accounts.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("user-account")
@JsonPropertyOrder({ "type", "extensions","user_id", "account_login", "account_type", "display_name",
		"is_service_account", "is_privileged", "can_escalate_privs", "is_disabled", "account_created",
		"account_expires", "password_last_changed", "account_first_login", "account_last_login" })
public interface UserAccountCoo extends CyberObservableObject {

	@JsonProperty("user_id")
	@JsonPropertyDescription("Specifies the identifier of the account.")
	@NotNull
	String getUserId();

	@JsonProperty("account_login")
	@JsonPropertyDescription("Specifies the account login string, used in cases where the user_id property specifies something other than what a user would type when they login.")
	Optional<String> getAccountLogin();

	@JsonProperty("account_type")
	@JsonPropertyDescription("Specifies the type of the account. This is an open vocabulary and values SHOULD come from the account-type-ov vocabulary.")
	@Vocab(AccountTypes.class)
	Optional<String> getAccountType();

	@JsonProperty("display_name")
	@JsonPropertyDescription("Specifies the display name of the account, to be shown in user interfaces, if applicable.")
	Optional<String> getDisplayName();

	@JsonProperty("is_service_account")
	@JsonPropertyDescription("Indicates that the account is associated with a network service or system process (daemon), not a specific individual.")
	Optional<Boolean> isServiceAccount();

	@JsonProperty("is_privileged")
	@JsonPropertyDescription("Specifies that the account has elevated privileges (i.e., in the case of root on Unix or the Windows Administrator account).")
	Optional<Boolean> isPrivileged();

	@JsonProperty("can_escalate_privs")
	@JsonPropertyDescription("Specifies that the account has the ability to escalate privileges (i.e., in the case of sudo on Unix or a Windows Domain Admin account).")
	Optional<Boolean> isCanEscalatePrivs();

	@JsonProperty("is_disabled")
	@JsonPropertyDescription("Specifies if the account is disabled.")
	Optional<Boolean> isDisabled();

	@JsonProperty("account_created")
	@JsonPropertyDescription("Specifies when the account was created.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getAccountCreated();

	@JsonProperty("account_expires")
	@JsonPropertyDescription("Specifies the expiration date of the account.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getAccountExpires();

	@JsonProperty("password_last_changed")
	@JsonPropertyDescription("Specifies when the account password was last changed.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getPasswordLastChanged();

	@JsonProperty("account_first_login")
	@JsonPropertyDescription("Specifies when the account was first accessed.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getAccountFirstLogin();

	@JsonProperty("account_last_login")
	@JsonPropertyDescription("Specifies when the account was last accessed.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getAccountLastLogin();

}