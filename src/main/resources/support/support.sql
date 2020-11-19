select  d.id, d.create_date,
        concat_ws(' ', u.firstname, u.lastname) as user,
        d.object, d.adress, d.powcur, d.powdec,
        s.code, v.code, d.load1c, d.rewrite
		from demand as d
		left join volt as v on d.volt_id = v.id
		left join safe s on d.safe_id = s.id
        left join user_entity as u on d.user_id = u.id
		where d.load1c=1 or d.rewrite=1;