package tech.cognity.almacen.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="infracciones")
public class Infraccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true,nullable = false,length = 8)
	private String dni;
	
	@Column(unique = true,nullable = false,length = 100)
	private String tipo_infraccion;
	
	@Column(unique = true,nullable = false,length = 20)
	private String ubicacion;
	
	@Column(unique = true,nullable = false,length = 255)
	private String descripcion;
	
    @Column(nullable = false)
    private Integer monto_multa;
    
    @Column(unique = true,nullable = false,length = 20)
	private String estado;
	
	@Column(name="created_at", nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateAt;
	
	@Column(name="activo", nullable = false)
	private Boolean activo;
}
