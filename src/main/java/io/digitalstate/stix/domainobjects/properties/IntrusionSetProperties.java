package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.vocabularies.AttackMotivations;
import io.digitalstate.stix.vocabularies.AttackResourceLevels;
import io.digitalstate.stix.vocabularies.StixVocabulary;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "attributed_to",
        "targets", "uses", "description", "aliases",
        "first_seen", "last_seen", "goals", "resource_level",
        "primary_motivation", "secondary_motivation"})
public abstract class IntrusionSetProperties extends CommonProperties{

    @JsonInclude(NON_NULL)
    private String name = null;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<String> aliases = new LinkedHashSet<>();

    @JsonProperty("first_seen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime firstSeen;

    @JsonProperty("last_seen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime lastSeen;

    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<String> goals = new LinkedHashSet<>();

    @JsonProperty("resource_level")
    @JsonInclude(NON_NULL)
    private String resourceLevel = null;

    @JsonProperty("primary_motivation")
    @JsonInclude(NON_NULL)
    private String primaryMotivation = null;

    @JsonProperty("secondary_motivation")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<String> secondaryMotivations = new LinkedHashSet<>();

    // Vocabulary Instances
    private StixVocabulary attackResourceLevelsVocab = new AttackResourceLevels();
    private StixVocabulary attackMotivationVocabulary = new AttackMotivations();


    //
    // Relationships
    //

    private LinkedHashSet<Relation<Relationship>> attributedTo = new LinkedHashSet<>();
    private LinkedHashSet<Relation<Relationship>> targets = new LinkedHashSet<>();
    private LinkedHashSet<Relation<Relationship>> uses = new LinkedHashSet<>();

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedHashSet<String> getAliases() {
        return aliases;
    }

    public void setAliases(LinkedHashSet<String> aliases) {
        this.aliases = aliases;
    }

    public ZonedDateTime getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(ZonedDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(ZonedDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public LinkedHashSet<String> getGoals() {
        return goals;
    }

    public void setGoals(LinkedHashSet<String> goals) {
        this.goals = goals;
    }

    public String getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(String resourceLevel) {
        if (!resourceLevel.isEmpty() && getAttackResourceLevelsVocab().vocabularyContains(resourceLevel)){
            this.resourceLevel = resourceLevel;
        } else {
            throw new IllegalArgumentException("resourceLevel is not a valid Attack Resource Level");
        }
    }

    public String getPrimaryMotivation() {
        return primaryMotivation;
    }

    public void setPrimaryMotivation(String primaryMotivation) {
        if (!primaryMotivation.isEmpty() && getAttackMotivationVocabulary().vocabularyContains(primaryMotivation)){
            this.primaryMotivation = primaryMotivation;
        } else {
            throw new IllegalArgumentException("primaryMotivation is not a valid Attack Motivation");
        }
    }

    public LinkedHashSet<String> getSecondaryMotivations() {
        return secondaryMotivations;
    }

    public void setSecondaryMotivations(LinkedHashSet<String> secondaryMotivations) {
        if (!secondaryMotivations.isEmpty() && getAttackMotivationVocabulary().vocabularyContains(primaryMotivation)){
            this.secondaryMotivations = secondaryMotivations;
        } else {
            throw new IllegalArgumentException("one or more values in secondaryMotivations is not a valid Attack Motivation");
        }
    }

    // Vocabularies

    public StixVocabulary getAttackResourceLevelsVocab() {
        return attackResourceLevelsVocab;
    }

    public void setAttackResourceLevelsVocab(StixVocabulary attackResourceLevelsVocab) {
        Objects.requireNonNull(attackResourceLevelsVocab, "attackResourceLevelsVocab cannot be null");
        this.attackResourceLevelsVocab = attackResourceLevelsVocab;
    }

    public StixVocabulary getAttackMotivationVocabulary() {
        return attackMotivationVocabulary;
    }

    public void setAttackMotivationVocabulary(StixVocabulary attackMotivationVocabulary) {
        Objects.requireNonNull(attackMotivationVocabulary, "attackMotivationVocabulary cannot be null");
        this.attackMotivationVocabulary = attackMotivationVocabulary;
    }


    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<Relation<Relationship>> getAttributedTo() {
        return attributedTo;
    }

    public void setAttributedTo(LinkedHashSet<Relation<Relationship>> attributedTo) {
        RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                attributedTo, ThreatActor.class);

        this.attributedTo = attributedTo;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<Relationship>> getTargets() {
        return targets;
    }

    public void setTargets(LinkedHashSet<Relation<Relationship>> targets) {
        RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                targets, Identity.class, Vulnerability.class);

        this.targets = targets;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<Relationship>> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<Relation<Relationship>> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, AttackPattern.class, Malware.class, Tool.class);

        this.uses = uses;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        getAttributedTo().forEach(relation->{
            if (relation.hasObject()){
                bundleObjects.add(relation.getObject());
            }
        });

        getTargets().forEach(relation->{
            if (relation.hasObject()){
                bundleObjects.add(relation.getObject());
            }
        });

        getUses().forEach(relation->{
            if (relation.hasObject()){
                bundleObjects.add(relation.getObject());
            }
        });

        return bundleObjects;
    }

    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

    }
}
