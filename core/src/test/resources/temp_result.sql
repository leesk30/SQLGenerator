----------->
SELECT
    reverse(r_f217f9.d_current_week) as te_a636d3 
FROM
    db1.date_dim AS r_f217f9 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_c3de1a.hd_buy_potential as te_8a725f 
FROM
    db1.household_demographics AS r_c3de1a 
WHERE
    r_c3de1a.hd_vehicle_count BETWEEN 14 AND r_c3de1a.hd_dep_count + 73 
ORDER BY
    1 DESC;
----------->
SELECT
    r_914bec.c_first_shipto_date_sk as te_1ebfad 
FROM
    db1.customer AS r_914bec 
WHERE
    r_914bec.c_birth_year > 42 
    OR r_914bec.c_first_sales_date_sk = 93 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_564cbf.d_date as te_49b20b,
    make_timestamp(r_fb1d5e.sr_hdemo_sk,
    r_564cbf.d_year,
    r_564cbf.d_fy_quarter_seq,
    r_564cbf.d_qoy,
    r_fb1d5e.sr_customer_sk,
    r_563ca3.t_time_sk) as te_444c45,
    r_563ca3.t_sub_shift as te_a0e430 
FROM
    db1.store_returns AS r_fb1d5e 
RIGHT JOIN
    db1.date_dim AS r_564cbf 
        ON r_fb1d5e.sr_customer_sk > r_564cbf.d_first_dom,
    db1.income_band AS r_14b191 
LEFT JOIN
    db1.time_dim AS r_563ca3 
        ON r_14b191.ib_lower_bound <= r_563ca3.t_hour 
WHERE
    r_fb1d5e.sr_return_quantity <= r_563ca3.t_time_sk 
ORDER BY
    1 DESC, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    chr(r_453ab0.r_reason_sk) as te_9d9480 
FROM
    db1.store AS r_14f088 
LEFT JOIN
    db1.reason AS r_453ab0 
        ON r_14f088.s_floor_space <= r_453ab0.r_reason_sk 
WHERE
    r_453ab0.r_reason_id LIKE 'sUgDDX' 
    OR r_14f088.s_rec_start_date = r_14f088.s_rec_end_date 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 78;
----------->
SELECT
    r_264ac5.wp_rec_end_date as te_ab2d62 
FROM
    db1.reason AS r_efeaba 
LEFT JOIN
    db1.web_page AS r_264ac5 
        ON r_efeaba.r_reason_sk = r_264ac5.wp_max_ad_count 
WHERE
    r_264ac5.wp_image_count > 95 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_e53bde.te_10f4b3 * r_6c2c42.s_division_id as te_768f47 
FROM
    (SELECT
        r_3d50c6.c_last_name as te_e3ede9,
        r_2b6f6d.ca_street_type as te_100379,
        r_3d50c6.c_first_sales_date_sk + 29 as te_10f4b3 
    FROM
        db1.customer_address AS r_2b6f6d,
        db1.customer AS r_3d50c6 
    WHERE
        r_2b6f6d.ca_suite_number ILIKE r_3d50c6.c_preferred_cust_flag 
    GROUP BY
        3, 2, 1) AS r_e53bde 
INNER JOIN
    db1.store AS r_6c2c42 
        ON r_e53bde.te_e3ede9 NOT LIKE r_6c2c42.s_hours 
ORDER BY
    1 DESC;
----------->
SELECT
    r_0ba68c.w_country as te_caace2,
    current_timestamp() as te_f7627f 
FROM
    db1.store AS r_34674e,
    db1.warehouse AS r_0ba68c 
INNER JOIN
    (
        SELECT
            r_90da22.cc_call_center_id as te_66f3d5 
        FROM
            db1.web_site AS r_3a5af8 
        LEFT JOIN
            db1.call_center AS r_90da22 
                ON r_3a5af8.web_city LIKE r_90da22.cc_state 
        WHERE
            r_90da22.cc_company_name ILIKE 'hv95G6'
    ) AS r_cfc955 
        ON r_0ba68c.w_county LIKE r_cfc955.te_66f3d5 
WHERE
    r_34674e.s_store_sk >= r_0ba68c.w_warehouse_sk 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_af3cc2.d_date as te_2c023d,
    bigint(positive(r_af3cc2.d_quarter_seq * 78.48698396D)) as te_32457b 
FROM
    db1.income_band AS r_47463d,
    db1.customer AS r_4cd0dc 
LEFT JOIN
    db1.date_dim AS r_af3cc2 
        ON r_4cd0dc.c_birth_country NOT ILIKE r_af3cc2.d_current_month 
WHERE
    r_47463d.ib_upper_bound = r_4cd0dc.c_first_sales_date_sk 
ORDER BY
    1 NULLS LAST, 2;
----------->
SELECT
    r_a2fc6d.te_66d878 as te_84bf5e 
FROM
    db1.item AS r_a40f5d 
RIGHT JOIN
    (
        SELECT
            r_104e61.cc_rec_start_date as te_66d878,
            r_600b30.c_preferred_cust_flag as te_6e3e2e,
            chr(r_600b30.c_birth_month) as te_d0c9b4 
        FROM
            db1.call_center AS r_104e61 
        LEFT JOIN
            db1.time_dim AS r_56d6ad 
                ON r_104e61.cc_mkt_id >= r_56d6ad.t_hour,
            db1.store_returns AS r_18beba 
        INNER JOIN
            db1.customer AS r_600b30 
                ON r_18beba.sr_store_credit = r_600b30.c_current_hdemo_sk 
        WHERE
            NOT r_56d6ad.t_time != r_600b30.c_current_hdemo_sk 
            OR r_104e61.cc_county > 'GIHlp' 
            OR r_18beba.web_county <= 21.32228264 
            AND r_104e61.cc_call_center_id NOT LIKE r_600b30.c_customer_id 
        ORDER BY
            1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 ASC NULLS FIRST
    ) AS r_a2fc6d 
        ON r_a40f5d.i_rec_end_date = r_a2fc6d.te_66d878 
ORDER BY
    1 ASC;
----------->
SELECT
    r_3666c7.web_tax_percentage as te_442751,
    r_3666c7.web_gmt_offset as te_b4f58b,
    10 - r_3666c7.web_company_id + r_3666c7.web_gmt_offset as te_e1e35e 
FROM
    db1.web_page AS r_d8947e 
RIGHT JOIN
    db1.time_dim AS r_cef6d0 
        ON r_d8947e.wp_web_page_id ILIKE r_cef6d0.t_meal_time,
    db1.web_site AS r_3666c7 
WHERE
    r_d8947e.wp_rec_end_date != r_3666c7.web_rec_end_date 
    AND r_3666c7.web_market_manager NOT ILIKE ((SELECT
        r_6c6832.upn_8eba61 || r_d6fad1.s_state as te_593135 
    FROM
        (SELECT
            * 
        FROM
            db1.item UNPIVOT INCLUDE NULLS ((up_c491d2,
            up_eea810,
            up_a3dbfe,
            up_2c127c,
            up_88fa84) FOR upn_8eba61 IN ((i_item_id,
            i_brand,
            i_class_id,
            i_rec_end_date,
            i_wholesale_cost) AS UPF_9a5d1d))) AS r_6c6832,
        (SELECT
            r_d39b27.s_rec_start_date as te_3361a6,
            r_d39b27.s_tax_percentage as te_e39eb1 
        FROM
            db1.ship_mode AS r_997eb2 
        INNER JOIN
            db1.reason AS r_1fef2f 
                ON r_997eb2.sm_contract LIKE r_1fef2f.r_reason_id,
            db1.ship_mode AS r_3e7556 
        LEFT JOIN
            db1.store AS r_d39b27 
                ON r_3e7556.sm_ship_mode_id NOT LIKE r_d39b27.s_state 
        WHERE
            r_1fef2f.r_reason_desc != r_3e7556.sm_ship_mode_id 
        ORDER BY
            1, 2 DESC NULLS FIRST) AS r_e4ea0f 
    INNER JOIN
        db1.store AS r_d6fad1 
            ON r_e4ea0f.te_e39eb1 = r_d6fad1.s_tax_percentage 
    WHERE
        r_6c6832.i_rec_start_date != r_e4ea0f.te_3361a6 
        AND r_d6fad1.s_state LIKE 'G7' 
    ORDER BY
        1 NULLS LAST 
    LIMIT 1)) 
    OR r_d8947e.wp_web_page_sk < 84 
    AND r_3666c7.web_mkt_class > '4elB' 
    AND r_3666c7.web_class ILIKE 'elBy7I' ORDER BY
        1 ASC, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
(
    SELECT
        r_900419.sr_fee as te_e52433,
        TIMESTAMP'2024-10-11 03:53:39.639' as te_7b73f8,
        r_ab5ab7.cc_call_center_id as te_e446ec 
    FROM
        db1.call_center AS r_ab5ab7,
        db1.store_returns AS r_900419 
    RIGHT JOIN
        db1.household_demographics AS r_66267d 
            ON r_900419.sr_store_sk > r_66267d.hd_demo_sk 
    WHERE
        r_ab5ab7.cc_gmt_offset != r_900419.sr_refunded_cash 
        OR r_ab5ab7.cc_market_manager NOT ILIKE 'IG7' 
    GROUP BY
        1, 3, 2 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS LAST
) EXCEPT  (SELECT
    r_329836.w_gmt_offset as te_c6030a, make_timestamp(hash(r_329836.w_county), hash(r_329836.w_county, now()), month(current_date()), hash(r_329836.w_warehouse_sk), ascii(r_329836.w_country), e()) as te_777c12, r_329836.w_county as te_0555a5 
FROM
    db1.ship_mode AS r_165a27,
    db1.warehouse AS r_329836 
WHERE
    r_165a27.sm_ship_mode_id != r_329836.w_city 
    OR r_329836.w_gmt_offset BETWEEN 38.59937173 AND 38.59937173 
    OR r_329836.w_warehouse_sq_ft > r_329836.w_warehouse_sk 
    AND r_329836.w_county LIKE 'C0lez' 
    OR r_329836.w_warehouse_sq_ft = r_165a27.sm_ship_mode_sk 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC NULLS LAST) 
ORDER BY
1 DESC NULLS LAST, 2 NULLS LAST, 3 NULLS FIRST;
----------->
WITH CTE_318d76(te_c33f53, te_f56e42, te_f3d865) AS (SELECT
    hash(r_ff056f.s_rec_start_date,
    false) / (r_ff056f.s_closed_date_sk + 57) as te_c33f53,
    date(now()) as te_f56e42,
    r_30f2ad.ca_street_type as te_f3d865 
FROM
    db1.customer_address AS r_30f2ad 
INNER JOIN
    db1.store AS r_ff056f 
        ON r_30f2ad.ca_address_id NOT LIKE r_ff056f.s_store_name,
    db1.store_returns AS r_b0b4ca 
LEFT JOIN
    db1.income_band AS r_949c71 
        ON r_b0b4ca.sr_ticket_number >= r_949c71.ib_lower_bound 
WHERE
    r_ff056f.s_floor_space != r_949c71.ib_lower_bound 
GROUP BY
    3, 1, 2) SELECT
    now() as te_928589 
FROM
    CTE_318d76 AS r_44d55e 
INNER JOIN
    db1.reason AS r_7dcaf7 
        ON r_44d55e.te_f3d865 NOT LIKE r_7dcaf7.r_reason_desc 
WHERE
    r_44d55e.te_c33f53 IN (
        SELECT
            31.22281714D as te_98dcd7 
        FROM
            db1.store AS r_0da54d 
        WHERE
            r_0da54d.s_store_id LIKE 'v' 
            AND r_0da54d.s_rec_end_date < r_0da54d.s_rec_start_date 
            AND r_0da54d.s_street_type NOT ILIKE 'b2Gv7RXpR' 
            AND r_0da54d.s_market_manager LIKE 'x6kg7x2SW4' 
        ORDER BY
            1 DESC
    ) 
    OR r_7dcaf7.r_reason_id ILIKE r_7dcaf7.r_reason_desc 
    AND r_7dcaf7.r_reason_sk < 95 
    AND r_7dcaf7.r_reason_sk >= 20 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_203479.t_minute as te_eceefb 
FROM
    db1.time_dim AS r_203479 
WHERE
    r_203479.t_shift NOT LIKE 'RXpR' 
    OR r_203479.t_hour = 81 
    OR r_203479.t_am_pm >= r_203479.t_shift 
    OR r_203479.t_time > 19 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    timestamp_millis(r_41c9d4.s_market_id) as te_16593d 
FROM
    db1.income_band AS r_e41314 
RIGHT JOIN
    db1.store AS r_41c9d4 
        ON r_e41314.ib_lower_bound != r_41c9d4.s_floor_space 
WHERE
    r_41c9d4.s_tax_percentage != r_41c9d4.s_gmt_offset 
    OR r_41c9d4.s_street_number NOT ILIKE 'lez' 
ORDER BY
    1;
----------->
SELECT
    r_020555.c_birth_day as te_250f3b,
    to_char(r_020555.c_birth_year,
    '999') as te_6da6fd,
    r_020555.c_first_sales_date_sk as te_300d51 
FROM
    db1.ship_mode AS r_152ae0 
RIGHT JOIN
    db1.warehouse AS r_11879b 
        ON r_152ae0.sm_contract ILIKE r_11879b.w_street_type,
    db1.customer AS r_020555 
WHERE
    r_152ae0.sm_carrier NOT LIKE r_020555.c_last_name 
    AND (
        NOT r_11879b.w_county LIKE r_020555.c_customer_id 
        OR r_11879b.w_warehouse_sk != 24 
        OR r_020555.c_customer_id LIKE r_020555.c_preferred_cust_flag
    ) 
ORDER BY
    1 DESC, 2, 3 NULLS LAST 
LIMIT 82;
----------->
SELECT
    r_a8e192.s_store_id as te_748220,
    r_9d40b0.hd_demo_sk as te_8f3ddd,
    r_a8e192.s_store_id as te_4df1b0 
FROM
    db1.item AS r_7e06a7 
RIGHT JOIN
    db1.store AS r_a8e192 
        ON r_7e06a7.i_item_sk < r_a8e192.s_division_id,
    db1.household_demographics AS r_9d40b0 
WHERE
    r_a8e192.s_number_employees > r_9d40b0.hd_vehicle_count 
    OR r_a8e192.s_division_id = 61 
    OR r_7e06a7.i_formulation NOT ILIKE r_7e06a7.i_item_desc 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_921bff.web_tax_percentage as te_427994 
FROM
    db1.customer_demographics AS r_078770 
LEFT JOIN
    db1.web_site AS r_921bff 
        ON r_078770.cd_demo_sk < r_921bff.web_close_date_sk 
WHERE
    r_921bff.web_rec_end_date BETWEEN r_921bff.web_rec_start_date AND DATE'2024-10-11' 
    AND r_921bff.web_company_name NOT ILIKE r_921bff.web_city 
    OR r_921bff.web_rec_end_date >= r_921bff.web_rec_start_date 
ORDER BY
    1 DESC;
----------->
SELECT
    r_725955.w_county as te_2aa599,
    r_725955.w_suite_number as te_c21a40,
    substring(r_725955.w_zip,
    r_23d45c.sr_customer_sk) as te_4a576c 
FROM
    (SELECT
        r_dc2e87.wp_link_count as te_4d6610 
    FROM
        (SELECT
            btrim(r_586318.wp_autogen_flag) as te_cdc481 
        FROM
            db1.web_page AS r_586318 
        ORDER BY
            1) AS r_38a1ae 
    LEFT JOIN
        db1.web_page AS r_dc2e87 
            ON r_38a1ae.te_cdc481 NOT ILIKE r_dc2e87.wp_url 
    WHERE
        r_dc2e87.wp_url ILIKE 'BRGC0l' 
    GROUP BY
        1 
    ORDER BY
        1 DESC NULLS LAST) AS r_53a6b7 
    INNER JOIN
        db1.warehouse AS r_725955 
            ON r_53a6b7.te_4d6610 = r_725955.w_gmt_offset,
        db1.store_returns AS r_23d45c 
    WHERE
        NOT r_725955.w_gmt_offset <= r_23d45c.sr_fee 
        AND r_725955.w_warehouse_sq_ft != 4 
        OR r_23d45c.sr_ticket_number >= r_23d45c.sr_cdemo_sk 
        AND r_725955.w_county NOT ILIKE 'iz' 
        AND r_23d45c.sr_item_sk = r_725955.w_warehouse_sk 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST 
    OFFSET 41;
----------->
SELECT
    r_5e5511.cc_manager as te_f0ac44,
    r_5e5511.cc_tax_percentage as te_a1997f,
    r_68fafb.te_dcfe35 as te_aebc2c 
FROM
    db1.ship_mode AS r_296543,
    db1.call_center AS r_5e5511 
INNER JOIN
    (
        SELECT
            r_8e9aa9.s_rec_start_date as te_6b3ff4,
            timestamp(r_8e9aa9.s_rec_end_date) as te_dcfe35,
            r_8e9aa9.s_tax_percentage as te_66349f 
        FROM
            (SELECT
                * 
            FROM
                db1.store PIVOT(max(s_street_name) AS pa_5e828e FOR s_division_id IN ((20) AS pf_4288d9,
                (75) AS pf_0c347d,
                (55) AS pf_3191b4,
                (64) AS pf_b89f51))) AS r_8e9aa9,
            db1.time_dim AS r_deb2a1 
        LEFT JOIN
            db1.income_band AS r_d0b0fa 
                ON r_deb2a1.t_second != r_d0b0fa.ib_income_band_sk 
        WHERE
            r_8e9aa9.s_company_id = 54 
            AND r_8e9aa9.s_gmt_offset != 23.77712646 
            AND r_8e9aa9.s_country ILIKE 'QFr' 
            AND NOT r_deb2a1.t_second != 23 
            OR r_8e9aa9.s_country ILIKE 'pRQiizz' 
        ORDER BY
            1, 2 ASC, 3 
        LIMIT 88) AS r_68fafb 
            ON r_5e5511.cc_rec_end_date < r_68fafb.te_6b3ff4 WHERE
                r_296543.sm_contract ILIKE r_5e5511.cc_suite_number 
                OR r_5e5511.cc_manager ILIKE 'g7' 
                OR r_5e5511.cc_country ILIKE 'IvXUulstnt' 
                AND r_5e5511.cc_sq_ft > 99 
        ORDER BY
            1, 2 ASC NULLS LAST, 3 NULLS LAST;
----------->
(
    SELECT
        r_29ead0.cd_demo_sk as te_27577a 
    FROM
        (SELECT
            * 
        FROM
            db1.customer_demographics UNPIVOT ((up_221b4f,
            up_388c76) FOR upn_24fe72 IN ((cd_gender,
            cd_dep_college_count) AS UPF_918550,
            (cd_marital_status,
            cd_dep_employed_count) AS UPF_cc4a4c,
            (cd_credit_rating,
            cd_dep_count) AS UPF_ee7650,
            (cd_education_status,
            cd_purchase_estimate) AS UPF_a0da7b))) AS r_29ead0 
    ORDER BY
        1) 
    UNION
    ALL (
        WITH CTE_96100f(te_6c88dc, te_f8427a) AS (SELECT
            r_0d6c51.hd_vehicle_count as te_6c88dc,
            string(mod(r_0d6c51.hd_demo_sk,
            r_0d6c51.hd_vehicle_count - 61)) as te_f8427a 
        FROM
            db1.warehouse AS r_4ae12b,
            db1.item AS r_e6a347 
        LEFT JOIN
            db1.household_demographics AS r_0d6c51 
                ON r_e6a347.i_category_id > r_0d6c51.hd_income_band_sk 
        WHERE
            r_4ae12b.w_country NOT ILIKE r_e6a347.i_category), CTE_cc83bb(te_045347, te_e00811, te_b885f6) AS (SELECT
            r_9026ed.ca_gmt_offset as te_045347,
            r_0c3446.ib_income_band_sk as te_e00811,
            r_0c3446.ib_lower_bound as te_b885f6 
        FROM
            db1.income_band AS r_0c3446 
        LEFT JOIN
            db1.reason AS r_d5922a 
                ON r_0c3446.ib_upper_bound < r_d5922a.r_reason_sk,
            db1.customer_address AS r_9026ed 
        RIGHT JOIN
            (SELECT
                * 
            FROM
                db1.ship_mode PIVOT(max(sm_type) AS pa_c0f4b1 FOR sm_contract IN (('ir98hv9') AS pf_5ccfaa,
                ('XX4QHZgG') AS pf_ca7b5a,
                ('Gv7RXpR') AS pf_58f9ef))) AS r_a3e2ba 
                ON r_9026ed.ca_county NOT ILIKE r_a3e2ba.pf_ca7b5a 
        WHERE
            r_d5922a.r_reason_id NOT ILIKE r_a3e2ba.sm_ship_mode_id 
            OR r_a3e2ba.sm_ship_mode_sk != r_d5922a.r_reason_sk 
        ORDER BY
            1, 2, 3 NULLS FIRST) SELECT
                r_fd0be7.te_b885f6 as te_b18333 
            FROM
                CTE_96100f AS r_ff448b,
                CTE_cc83bb AS r_fd0be7 
            WHERE
                r_ff448b.te_6c88dc < r_fd0be7.te_045347 
                OR (
                    NOT r_ff448b.te_f8427a NOT ILIKE 'ir98hv95' 
                    AND (
                        NOT r_fd0be7.te_b885f6 BETWEEN 53 AND r_fd0be7.te_e00811 
                        AND r_fd0be7.te_b885f6 = r_fd0be7.te_e00811
                    )
                ) 
            GROUP BY
                1 
            ORDER BY
                1 ASC NULLS LAST) 
        ORDER BY
            1 DESC 
        LIMIT 64;
----------->
SELECT
    r_bf4186.cc_rec_end_date as te_b4464d 
FROM
    db1.call_center AS r_bf4186 
INNER JOIN
    (
        SELECT
            r_2cfdb8.web_company_name || r_2cfdb8.web_street_name as te_307ea0 
        FROM
            db1.web_site AS r_2cfdb8 
        RIGHT JOIN
            db1.time_dim AS r_4ae426 
                ON r_2cfdb8.web_company_name LIKE r_4ae426.t_time_id 
        WHERE
            r_2cfdb8.web_street_name LIKE 'QFrTU' 
            OR NOT r_2cfdb8.web_rec_end_date != r_2cfdb8.web_rec_start_date 
        ORDER BY
            1 DESC NULLS LAST
    ) AS r_2af874 
        ON r_bf4186.cc_division_name NOT LIKE r_2af874.te_307ea0 
WHERE
    r_bf4186.cc_suite_number ILIKE '8hv95G6sU' 
ORDER BY
    1 ASC;
----------->
SELECT
    r_ea5a41.i_rec_end_date as te_8484b1,
    r_ea5a41.i_rec_end_date as te_99ec73 
FROM
    (SELECT
        * 
    FROM
        db1.customer_demographics PIVOT(sum(cd_dep_count) AS pa_5c9808 FOR cd_credit_rating IN (('izz') AS pf_e6f5ee,
        ('Uul') AS pf_7c8c68,
        ('x') AS pf_f2d737))) AS r_c4bc68,
    db1.item AS r_ea5a41 
WHERE
    r_c4bc68.cd_purchase_estimate >= r_ea5a41.i_manufact_id 
ORDER BY
    1 ASC NULLS LAST, 2;
----------->
SELECT
    try_add(DATE'2024-03-26',
    r_9b9295.t_time_sk) as te_ba07d7 
FROM
    db1.time_dim AS r_9d6842 
RIGHT JOIN
    db1.time_dim AS r_9b9295 
        ON r_9d6842.t_minute != r_9b9295.t_second 
WHERE
    r_9d6842.t_am_pm NOT LIKE r_9d6842.t_shift 
ORDER BY
    1 DESC 
LIMIT 55;
----------->
SELECT
    r_aa8c4e.w_gmt_offset as te_58da7a 
FROM
    (WITH CTE_f91895(te_83c271,
    te_30ddec,
    te_ce5ef7) AS (SELECT
        hash(r_d869af.ca_location_type,
        TIMESTAMP'2024-10-11 11:11:07.581') as te_83c271,
        r_3aa8ea.web_rec_start_date as te_30ddec,
        r_3aa8ea.web_gmt_offset as te_ce5ef7 
    FROM
        db1.web_site AS r_3aa8ea,
        db1.store AS r_f876cf 
    INNER JOIN
        db1.customer_address AS r_d869af 
            ON r_f876cf.s_number_employees <= r_d869af.ca_address_sk 
    WHERE
        r_3aa8ea.web_market_manager NOT LIKE r_d869af.ca_street_name 
    ORDER BY
        1 DESC NULLS FIRST, 2 NULLS LAST, 3 DESC NULLS LAST), CTE_eb730c(te_5a231f) AS (SELECT
        r_f18851.ib_income_band_sk as te_5a231f 
    FROM
        db1.income_band AS r_f18851 
    WHERE
        r_f18851.ib_income_band_sk IN (SELECT
            r_bdf9f4.wp_web_page_sk as te_ac0646 
        FROM
            db1.web_page AS r_bdf9f4 
        RIGHT JOIN
            db1.income_band AS r_daf5e6 
                ON r_bdf9f4.wp_customer_sk < r_daf5e6.ib_income_band_sk 
        WHERE
            r_bdf9f4.wp_rec_end_date >= r_bdf9f4.wp_rec_start_date 
        ORDER BY
            1 DESC NULLS FIRST) 
    ORDER BY
        1 DESC) SELECT
            r_d7778e.te_ce5ef7 as te_cf60cf 
        FROM
            CTE_f91895 AS r_d7778e 
        INNER JOIN
            CTE_eb730c AS r_bc7159 
                ON r_d7778e.te_83c271 != r_bc7159.te_5a231f 
        WHERE
            r_bc7159.te_5a231f < r_d7778e.te_83c271 
        ORDER BY
            1 ASC NULLS FIRST 
        LIMIT 11) AS r_c406bd RIGHT JOIN
        db1.warehouse AS r_aa8c4e 
            ON r_c406bd.te_cf60cf >= r_aa8c4e.w_warehouse_sq_ft 
    WHERE
        r_aa8c4e.w_county NOT LIKE '7ir9' 
        OR r_aa8c4e.w_suite_number NOT ILIKE ((SELECT
            r_63f7b5.ca_street_name as te_87e031 
        FROM
            db1.time_dim AS r_b711c2,
            db1.household_demographics AS r_c12e09 
        LEFT JOIN
            db1.customer_address AS r_63f7b5 
                ON r_c12e09.hd_vehicle_count = r_63f7b5.ca_address_sk 
        WHERE
            r_b711c2.t_time_id LIKE r_63f7b5.ca_county 
            AND NOT r_63f7b5.ca_address_sk != 80 
        ORDER BY
            1 ASC NULLS FIRST 
        LIMIT 1)) 
    OR r_aa8c4e.w_county LIKE 'zmSRI' 
    AND r_aa8c4e.w_city ILIKE 'ez' ORDER BY
        1 ASC NULLS FIRST;
----------->
SELECT
    hash(r_596971.te_a037b6,
    r_596971.te_0796a1) as te_dba70f,
    unix_timestamp() as te_0dc547 
FROM
    db1.ship_mode AS r_e24350,
    (SELECT
        date_add(r_11b94f.cc_rec_start_date,
        hash(current_timestamp(),
        false)) as te_0796a1,
        cos(r_11b94f.cc_employees) as te_a037b6,
        unix_timestamp() as te_778d9c 
    FROM
        db1.call_center AS r_11b94f,
        db1.web_site AS r_968054 
    RIGHT JOIN
        (
            SELECT
                * 
            FROM
                db1.customer_address UNPIVOT INCLUDE NULLS ((up_31895d,
                up_b2d6fc,
                up_82f9f8,
                up_1aa65d) FOR upn_3ef37b IN ((ca_gmt_offset,
                ca_street_type,
                ca_county,
                ca_address_sk) AS UPF_9bf939))
        ) AS r_c9d86c 
            ON r_968054.web_gmt_offset != r_c9d86c.up_31895d 
    WHERE
        r_11b94f.cc_mkt_class >= r_968054.web_market_manager 
        OR r_11b94f.cc_call_center_sk < 42 
        AND r_c9d86c.ca_zip LIKE r_968054.web_state 
        AND r_11b94f.cc_tax_percentage = r_968054.web_gmt_offset 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST) AS r_596971 
    WHERE
        r_e24350.sm_ship_mode_sk != r_596971.te_a037b6 
        AND r_e24350.sm_contract LIKE 'mSR' 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_266f38.ca_city as te_f7e034,
    try_add(r_266f38.ca_county,
    r_a27409.sr_customer_sk) as te_a6e959,
    r_a27409.sr_return_amt as te_8e4cd6 
FROM
    (SELECT
        chr(r_c70a9f.wp_customer_sk) as te_c8dcba,
        r_c70a9f.wp_image_count as te_80f361 
    FROM
        (SELECT
            * 
        FROM
            db1.ship_mode UNPIVOT ((up_4cda61,
            up_c8e006) FOR upn_9da98a IN ((sm_carrier,
            sm_ship_mode_sk) AS UPF_d8d597))) AS r_75b521,
        db1.time_dim AS r_b8584b 
    INNER JOIN
        db1.web_page AS r_c70a9f 
            ON r_b8584b.t_am_pm = r_c70a9f.wp_type 
    WHERE
        r_75b521.up_c8e006 < r_c70a9f.wp_customer_sk 
    ORDER BY
        1 ASC NULLS FIRST, 2 ASC NULLS LAST) AS r_f07159 
    INNER JOIN
        db1.customer_address AS r_266f38 
            ON r_f07159.te_80f361 < r_266f38.ca_address_sk,
        db1.store_returns AS r_a27409 
    WHERE
        r_266f38.ca_gmt_offset < r_a27409.sr_return_amt 
    ORDER BY
        1 DESC NULLS FIRST, 2 NULLS FIRST, 3 NULLS LAST 
    LIMIT 88;
----------->
SELECT
    r_bb7863.s_store_id as te_860590,
    19 / r_bb7863.s_floor_space + r_bb7863.s_company_id as te_4856d4 
FROM
    db1.call_center AS r_e038e8 
LEFT JOIN
    db1.web_site AS r_e622ef 
        ON r_e038e8.cc_class ILIKE r_e622ef.web_manager,
    db1.store AS r_bb7863 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.call_center UNPIVOT INCLUDE NULLS ((up_218c5a,
            up_3708f5,
            up_c305cc,
            up_f44dc0,
            up_91ff2d) FOR upn_b8fb0b IN ((cc_rec_start_date,
            cc_zip,
            cc_gmt_offset,
            cc_call_center_id,
            cc_employees) AS UPF_576aa1))
    ) AS r_073146 
        ON r_bb7863.s_rec_start_date <= r_073146.cc_rec_end_date 
WHERE
    r_e038e8.cc_company_name LIKE r_bb7863.s_market_manager 
    OR (
        NOT r_e622ef.web_mkt_class >= r_e622ef.web_street_type 
        OR r_073146.cc_mkt_id BETWEEN 61 AND 61 
        AND r_e038e8.cc_call_center_sk >= 89 
        AND r_e622ef.web_rec_start_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11'
    ) 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
WITH CTE_b08d11(te_e5d70e, te_d459f6) AS (SELECT
    r_a16b2f.web_rec_start_date as te_e5d70e,
    now() as te_d459f6 
FROM
    db1.customer_demographics AS r_f710f3,
    db1.web_site AS r_a16b2f 
INNER JOIN
    db1.customer_address AS r_737189 
        ON r_a16b2f.web_manager LIKE r_737189.ca_location_type 
WHERE
    r_f710f3.cd_purchase_estimate != r_a16b2f.web_close_date_sk 
    AND r_a16b2f.web_company_id <= 21 
ORDER BY
    1 NULLS LAST, 2 DESC 
OFFSET 67), CTE_f60515(te_5acb3b) AS (SELECT
bigint(mod(r_829c69.s_division_id, 17.54688086D)) as te_5acb3b FROM
    db1.store AS r_829c69 
LEFT JOIN
    db1.time_dim AS r_ce3c5d 
        ON r_829c69.s_closed_date_sk != r_ce3c5d.t_time 
WHERE
    r_829c69.s_county NOT ILIKE '2kGk' 
    AND (NOT r_829c69.s_closed_date_sk <= 88 
    AND r_829c69.s_hours ILIKE 'kg7x2SW') 
ORDER BY
    1 NULLS LAST) SELECT
    r_276b31.te_d459f6 as te_4d4cd7, r_276b31.te_d459f6 as te_868595 
FROM
    CTE_b08d11 AS r_276b31,
    CTE_f60515 AS r_9978d8 
INNER JOIN
    CTE_f60515 AS r_222c0c 
        ON r_9978d8.te_5acb3b > r_222c0c.te_5acb3b 
WHERE
    r_276b31.te_d459f6 < timestamp_seconds(r_9978d8.te_5acb3b) 
    OR r_276b31.te_d459f6 > TIMESTAMP'2024-10-11 07:47:53.724' 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_5d1d61.i_item_desc as te_dff2aa,
    r_75093e.wp_web_page_id as te_fdc39f 
FROM
    db1.store_returns AS r_cc9b68 
RIGHT JOIN
    db1.web_page AS r_75093e 
        ON r_cc9b68.sr_returned_date_sk <= r_75093e.wp_image_count,
    db1.item AS r_5d1d61 
WHERE
    r_75093e.wp_type NOT LIKE r_5d1d61.i_item_desc 
    AND r_5d1d61.i_wholesale_cost < 75.60533022 
    OR r_cc9b68.sr_ticket_number = 78 
    AND r_75093e.wp_web_page_id LIKE 'Vb' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    (r_8fcb2a.i_manufact_id - 39) * r_8fcb2a.i_wholesale_cost as te_70fbf0,
    r_72847a.te_a2db0e as te_2b742e,
    r_8fcb2a.i_color as te_536731 
FROM
    db1.item AS r_8fcb2a,
    (SELECT
        r_b383a3.r_reason_sk + 3.84396442D as te_a2db0e 
    FROM
        db1.reason AS r_b383a3 
    WHERE
        EXISTS (
            SELECT
                chr(75.39149248D / r_e07c3c.pf_1d3e22) as te_5b1ece,
                r_f68f20.wp_image_count as te_7fb0c5,
                r_1d82ab.ca_address_sk as te_1909bd 
            FROM
                db1.web_page AS r_f68f20 
            RIGHT JOIN
                db1.customer_address AS r_1d82ab 
                    ON r_f68f20.wp_char_count != r_1d82ab.ca_address_sk,
                (SELECT
                    * 
                FROM
                    db1.income_band PIVOT(min(ib_upper_bound) AS pa_24a780 FOR ib_income_band_sk IN ((51) AS pf_27a2ff,
                    (64) AS pf_1d3e22,
                    (71) AS pf_3fea83,
                    (47) AS pf_ae5270))) AS r_e07c3c 
            LEFT JOIN
                db1.date_dim AS r_af8fea 
                    ON r_e07c3c.pf_1d3e22 >= r_af8fea.d_week_seq 
            WHERE
                r_f68f20.wp_rec_start_date = r_af8fea.d_date 
                AND r_af8fea.d_month_seq >= 47 
                OR r_f68f20.wp_rec_end_date < r_f68f20.wp_rec_start_date 
                AND r_af8fea.d_date BETWEEN r_af8fea.d_date AND DATE'2024-03-25' 
            ORDER BY
                1, 2 ASC, 3 DESC) 
                AND r_b383a3.r_reason_sk < 59 
                AND r_b383a3.r_reason_desc LIKE 'ir98hv95G6' 
            ORDER BY
                1 DESC NULLS FIRST
        ) AS r_72847a 
    INNER JOIN
        db1.ship_mode AS r_9936c5 
            ON chr(r_72847a.te_a2db0e) ILIKE r_9936c5.sm_type 
    WHERE
        r_8fcb2a.i_manager_id <= r_72847a.te_a2db0e 
        OR r_8fcb2a.i_manufact_id < 57 
        OR r_8fcb2a.i_wholesale_cost <= r_8fcb2a.i_current_price 
        AND r_8fcb2a.i_rec_start_date <= r_8fcb2a.i_rec_end_date 
        OR NOT r_8fcb2a.i_manufact_id != 70 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC;
----------->
WITH CTE_a127a3(te_652eb0) AS (SELECT
    r_e1dad8.s_gmt_offset as te_652eb0 
FROM
    db1.store AS r_e1dad8 
ORDER BY
    1 DESC NULLS FIRST) (
    SELECT
        r_ab8804.sm_ship_mode_sk as te_f4473c 
    FROM
        db1.ship_mode AS r_ab8804 
    WHERE
        r_ab8804.sm_code LIKE r_ab8804.sm_carrier 
        OR NOT r_ab8804.sm_ship_mode_id NOT LIKE 'lstnt' 
        OR r_ab8804.sm_carrier NOT LIKE r_ab8804.sm_contract 
    ORDER BY
        1 ASC
) EXCEPT ALL SELECT
    r_ea57d9.r_reason_sk as te_fac0bb 
FROM
    db1.reason AS r_ea57d9 
WHERE
    r_ea57d9.r_reason_desc NOT LIKE r_ea57d9.r_reason_id 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    decimal(unix_timestamp()) as te_312117 
FROM
    db1.customer_address AS r_18a370 
WHERE
    r_18a370.ca_street_name < '7i' 
    AND r_18a370.ca_address_id LIKE '8' 
    AND (
        r_18a370.ca_city, r_18a370.ca_street_number
    ) IN (
        SELECT
            hex(r_bd779e.cc_open_date_sk) as te_4d6420,
            chr(sin(r_5e415e.te_5c6acf)) as te_7ff469 
        FROM
            db1.income_band AS r_253746 
        INNER JOIN
            (
                SELECT
                    hex(unix_timestamp()) as te_dcef70,
                    r_b10764.s_gmt_offset as te_5c6acf,
                    r_48e4c3.cd_purchase_estimate as te_865bea 
                FROM
                    db1.customer_demographics AS r_48e4c3,
                    db1.store AS r_b10764 
                RIGHT JOIN
                    db1.customer_demographics AS r_1f15f9 
                        ON r_b10764.s_closed_date_sk < r_1f15f9.cd_purchase_estimate 
                WHERE
                    r_48e4c3.cd_dep_employed_count <= r_b10764.s_floor_space 
                    OR r_b10764.s_tax_percentage != r_b10764.s_gmt_offset 
                ORDER BY
                    1, 2 DESC NULLS LAST, 3
            ) AS r_5e415e 
                ON r_253746.ib_lower_bound <= r_5e415e.te_865bea,
            db1.call_center AS r_bd779e 
        WHERE
            r_5e415e.te_dcef70 LIKE r_bd779e.cc_zip 
            OR r_bd779e.cc_county ILIKE '4elBy' 
            AND r_bd779e.cc_county NOT ILIKE r_bd779e.cc_street_name 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST) 
            OR r_18a370.ca_location_type ILIKE 'm' 
        ORDER BY
            1 ASC NULLS FIRST;
----------->
SELECT
    r_677433.cd_credit_rating as te_d18ecf 
FROM
    db1.customer_demographics AS r_677433 
LEFT JOIN
    db1.time_dim AS r_2077b1 
        ON r_677433.cd_dep_count < r_2077b1.t_time_sk 
WHERE
    r_677433.cd_dep_college_count = 28 
    OR r_2077b1.t_meal_time NOT LIKE r_2077b1.t_shift 
    AND r_2077b1.t_hour != r_677433.cd_dep_college_count 
ORDER BY
    1;
----------->
SELECT
    r_f22500.r_reason_id as te_1255a3,
    r_f22500.r_reason_id as te_63cbc1 
FROM
    db1.reason AS r_f22500,
    (SELECT
        hash(r_95369e.te_86b166,
        r_95369e.te_b7fa32) as te_1a93f4 
    FROM
        (SELECT
            timestamp_millis(r_1a62a4.sr_store_sk) as te_b7fa32,
            e() as te_86b166 
        FROM
            db1.time_dim AS r_201f6c 
        INNER JOIN
            db1.store AS r_9ef418 
                ON r_201f6c.t_time_id NOT LIKE r_9ef418.s_hours,
            db1.store_returns AS r_1a62a4 
        INNER JOIN
            (
                SELECT
                    r_00c831.hd_buy_potential as te_18866a,
                    r_7c97b5.hd_buy_potential as te_7d862d,
                    r_00c831.hd_income_band_sk as te_b47e24 
                FROM
                    db1.household_demographics AS r_00c831,
                    (WITH CTE_f1474e(te_a14f38) AS (SELECT
                        negative(r_935925.w_gmt_offset) as te_a14f38 
                    FROM
                        db1.warehouse AS r_935925 
                    ORDER BY
                        1 DESC) SELECT
                        hash(r_bfafd9.te_a14f38, true) as te_b7073e 
                    FROM
                        CTE_f1474e AS r_bfafd9 
                    LEFT JOIN
                        (
                            (
                                SELECT
                                    r_1dd5e6.i_container as te_ab199c 
                                FROM
                                    db1.customer_address AS r_4853de,
                                    (SELECT
                                        * 
                                    FROM
                                        db1.item PIVOT(stddev(i_item_sk) AS pa_e18a87 FOR i_item_desc IN (('r98hv95') AS pf_b03161,
                                        ('QFrT') AS pf_b22692,
                                        ('98hv95G6') AS pf_3cb83e,
                                        ('ZgGI') AS pf_e6a778,
                                        ('lB') AS pf_2293d2,
                                        ('lp') AS pf_aba6b2))) AS r_1dd5e6 
                                WHERE
                                    r_4853de.ca_address_sk <= r_1dd5e6.i_current_price 
                                    AND r_4853de.ca_address_sk > r_1dd5e6.i_category_id 
                                    OR r_1dd5e6.pf_e6a778 BETWEEN r_1dd5e6.pf_3cb83e AND 31.03997558D 
                                    AND r_1dd5e6.i_rec_start_date < r_1dd5e6.i_rec_end_date 
                                    OR r_1dd5e6.i_class ILIKE 'C0lezmSR' 
                                ORDER BY
                                    1 DESC NULLS FIRST) 
                                UNION
                                (
                                    SELECT
                                        r_6d324e.web_suite_number as te_34dfa4 
                                    FROM
                                        db1.web_site AS r_6d324e 
                                    WHERE
                                        r_6d324e.web_rec_start_date >= DATE'2024-10-11' 
                                    ORDER BY
                                        1 DESC NULLS LAST
                                ) 
                            ORDER BY
                                1 DESC NULLS FIRST) AS r_de0314 
                                    ON chr(r_bfafd9.te_a14f38) NOT LIKE r_de0314.te_ab199c 
                            WHERE
                                r_bfafd9.te_a14f38 <= 84.12177942 
                                AND r_de0314.te_ab199c NOT LIKE 'UOVbB' 
                            ORDER BY
                                1 DESC NULLS FIRST
                        ) AS r_e2eaf4 
                    INNER JOIN
                        db1.household_demographics AS r_7c97b5 
                            ON r_e2eaf4.te_b7073e != r_7c97b5.hd_income_band_sk 
                    WHERE
                        NOT r_00c831.hd_dep_count >= r_7c97b5.hd_income_band_sk 
                        AND r_7c97b5.hd_buy_potential NOT LIKE r_00c831.hd_buy_potential 
                        AND (
                            NOT r_00c831.hd_vehicle_count = r_7c97b5.hd_vehicle_count 
                            AND r_7c97b5.hd_demo_sk >= 92
                        ) 
                    ORDER BY
                        1 NULLS LAST, 2 DESC NULLS LAST, 3 ASC) AS r_8993ed 
                            ON r_1a62a4.sr_return_ship_cost != r_8993ed.te_b47e24 
                    WHERE
                        r_9ef418.s_tax_percentage != r_1a62a4.sr_customer_sk 
                        AND r_9ef418.s_rec_end_date < DATE'2024-03-26' 
                    ORDER BY
                        1 NULLS FIRST, 2 ASC NULLS FIRST) AS r_95369e 
                    ORDER BY
                        1 DESC NULLS LAST) AS r_5d93e0 
                WHERE
                    r_f22500.r_reason_sk != r_5d93e0.te_1a93f4 
                    AND r_f22500.r_reason_sk < r_5d93e0.te_1a93f4 
                    OR r_f22500.r_reason_desc NOT ILIKE r_f22500.r_reason_id 
                    OR r_f22500.r_reason_sk = 91 
                    OR r_f22500.r_reason_sk = 6 
                ORDER BY
                    1 DESC, 2 DESC;
----------->
SELECT
    abs(r_cb57e4.d_first_dom / 1.95333138D) * r_7efb3d.w_gmt_offset as te_22e45f,
    r_cb57e4.d_date as te_3a4ffd,
    r_cb57e4.d_date as te_a23c82 
FROM
    db1.date_dim AS r_cb57e4,
    db1.warehouse AS r_7efb3d 
RIGHT JOIN
    db1.income_band AS r_8bbf3b 
        ON r_7efb3d.w_warehouse_sq_ft > r_8bbf3b.ib_income_band_sk 
WHERE
    r_cb57e4.d_current_week LIKE r_7efb3d.w_county 
    AND r_cb57e4.d_day_name NOT ILIKE 'IvXU' 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_aae3cb.w_warehouse_sq_ft as te_b49b0f 
FROM
    (SELECT
        * 
    FROM
        db1.warehouse PIVOT(max(w_county) AS pa_feb1b2 FOR w_state IN (('LQFrT') AS pf_4c649b,
        ('zz2kGk') AS pf_5791ae,
        ('98h') AS pf_ce949b,
        ('Hl') AS pf_1edd5e,
        ('X') AS pf_75c557,
        ('RIvX') AS pf_c3de27))) AS r_aae3cb 
WHERE
    r_aae3cb.w_suite_number = 'Hlpx6kg7x2' 
    AND r_aae3cb.w_warehouse_sq_ft > r_aae3cb.w_warehouse_sk 
    AND r_aae3cb.w_city NOT LIKE 'nt' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_96a4cb.i_rec_end_date as te_ed52d4 
FROM
    db1.item AS r_96a4cb 
RIGHT JOIN
    db1.call_center AS r_3361af 
        ON r_96a4cb.i_class_id <= r_3361af.cc_closed_date_sk 
WHERE
    r_3361af.cc_zip LIKE '8hv95G6s' 
    AND r_96a4cb.i_rec_start_date <= DATE'2024-10-11' 
    AND r_3361af.cc_state != 'Hlp' 
    AND r_96a4cb.i_color ILIKE '2SW4' 
ORDER BY
    1 ASC;
----------->
SELECT
    r_e65615.c_first_sales_date_sk as te_966d2a 
FROM
    (SELECT
        * 
    FROM
        db1.item UNPIVOT INCLUDE NULLS ((up_ba3f58,
        up_e830cd,
        up_098e72,
        up_805d39,
        up_2ac651) FOR upn_6e6fe5 IN ((i_container,
        i_item_id,
        i_rec_end_date,
        i_wholesale_cost,
        i_manager_id) AS UPF_7d4d72))) AS r_302fc8 
RIGHT JOIN
    db1.customer AS r_e65615 
        ON r_302fc8.up_2ac651 > r_e65615.c_customer_sk 
WHERE
    r_302fc8.up_805d39 != 75.83049487 
    OR r_e65615.c_salutation NOT ILIKE r_e65615.c_birth_country 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    chr(r_c472cd.i_current_price) as te_c7fe39 
FROM
    db1.item AS r_c472cd 
RIGHT JOIN
    db1.income_band AS r_26df85 
        ON r_c472cd.i_category_id <= r_26df85.ib_lower_bound 
WHERE
    r_c472cd.i_class ILIKE 'mSRIv' 
    AND r_c472cd.i_rec_end_date = r_c472cd.i_rec_start_date 
    OR r_c472cd.i_current_price <= r_c472cd.i_wholesale_cost 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    year(current_timestamp()) as te_a812ef,
    r_812d6e.i_rec_start_date as te_ed27c4,
    unix_timestamp() as te_2a2161 
FROM
    db1.warehouse AS r_d304b8,
    db1.item AS r_812d6e 
WHERE
    r_d304b8.w_warehouse_sq_ft != r_812d6e.i_brand_id 
    OR r_d304b8.w_country NOT ILIKE '98hv95' 
    AND r_812d6e.i_item_sk <= 27 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_330b23.cd_education_status as te_1b184e 
FROM
    db1.store AS r_c8523d 
INNER JOIN
    db1.customer_demographics AS r_330b23 
        ON r_c8523d.s_county <= r_330b23.cd_marital_status 
WHERE
    r_c8523d.s_street_name LIKE 'RXpRQ' 
    OR r_330b23.cd_marital_status ILIKE r_c8523d.s_store_id 
ORDER BY
    1 
OFFSET 27;
----------->
SELECT
    r_4d64d8.s_market_desc as te_de0969,
    reflect('java.util.UUID',
    'randomUUID') as te_11f2c1,
    r_4d64d8.s_store_id as te_715775 
FROM
    db1.store AS r_4d64d8 
INNER JOIN
    db1.income_band AS r_13dae8 
        ON r_4d64d8.s_division_id = r_13dae8.ib_income_band_sk,
    db1.ship_mode AS r_698f5b 
WHERE
    r_4d64d8.s_county NOT LIKE r_698f5b.sm_code 
    OR r_13dae8.ib_lower_bound >= r_4d64d8.s_division_id 
    OR r_4d64d8.s_market_manager LIKE 'ir98hv95' 
    AND r_4d64d8.s_country NOT LIKE 'hv' 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    hash(r_3d65af.c_first_name,
    TIMESTAMP'2024-10-11 16:56:26.569') as te_f94ac3 
FROM
    db1.customer AS r_3d65af 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 92;
----------->
SELECT
    r_168dbc.cd_gender as te_2f1494,
    r_f420b7.s_floor_space as te_bdc004 
FROM
    db1.store AS r_f420b7,
    db1.customer_demographics AS r_168dbc 
WHERE
    r_f420b7.s_tax_percentage != r_168dbc.cd_demo_sk 
    OR r_f420b7.s_gmt_offset >= r_f420b7.s_tax_percentage 
    OR r_f420b7.s_market_id = r_168dbc.cd_dep_count 
    OR r_f420b7.s_store_sk <= r_168dbc.cd_dep_employed_count 
ORDER BY
    1 DESC, 2 ASC;
----------->
SELECT
    to_char(r_9977b6.d_last_dom,
    '999') as te_53a681 
FROM
    db1.date_dim AS r_9977b6 
RIGHT JOIN
    db1.warehouse AS r_e7117c 
        ON r_9977b6.d_following_holiday NOT ILIKE r_e7117c.w_street_type 
WHERE
    r_e7117c.w_gmt_offset = 83.76163636 
    AND r_9977b6.d_current_week LIKE r_9977b6.d_current_day 
ORDER BY
    1 DESC;
----------->
SELECT
    sin(56 + r_fff6a0.i_category_id) as te_a877b2,
    r_6698b9.t_am_pm as te_a0f2d5 
FROM
    db1.income_band AS r_8ac82c 
LEFT JOIN
    db1.item AS r_fff6a0 
        ON r_8ac82c.ib_lower_bound > r_fff6a0.i_item_sk,
    (SELECT
        * 
    FROM
        db1.time_dim PIVOT(max(t_meal_time) AS pa_b9c8ba FOR t_time IN ((99) AS pf_3b165e,
        (90) AS pf_e1d0e7))) AS r_6698b9 
INNER JOIN
    (
        SELECT
            r_ef70de.i_rec_start_date as te_1a5945,
            r_ef70de.i_current_price as te_48e7d9,
            r_2c48cf.te_165864 as te_7e1ef9 
        FROM
            (SELECT
                r_ed83aa.wp_web_page_sk as te_b58626,
                r_ed83aa.wp_link_count as te_e4d9d0 
            FROM
                db1.web_page AS r_ed83aa 
            LEFT JOIN
                db1.reason AS r_dd0a20 
                    ON r_ed83aa.wp_type ILIKE r_dd0a20.r_reason_desc,
                db1.income_band AS r_8c302b 
            WHERE
                r_ed83aa.wp_char_count = r_8c302b.ib_lower_bound 
                AND r_ed83aa.wp_web_page_id NOT ILIKE '7ir' 
                OR r_ed83aa.wp_rec_end_date = DATE'2024-10-11' 
            ORDER BY
                1 NULLS LAST, 2 NULLS LAST) AS r_cce6dc 
        RIGHT JOIN
            db1.time_dim AS r_c69394 
                ON r_cce6dc.te_b58626 != r_c69394.t_minute,
            db1.item AS r_ef70de 
        RIGHT JOIN
            (
                SELECT
                    r_5c8072.d_dow as te_00b86d,
                    r_5603ff.web_rec_end_date as te_165864,
                    date_add(r_5603ff.web_rec_end_date,
                    r_5c8072.d_dom) as te_87c51f 
                FROM
                    db1.date_dim AS r_5c8072 
                INNER JOIN
                    db1.warehouse AS r_6af25c 
                        ON r_5c8072.d_dom != r_6af25c.w_warehouse_sq_ft,
                    db1.web_site AS r_5603ff 
                WHERE
                    r_5c8072.d_qoy >= r_5603ff.web_close_date_sk 
                    AND r_5603ff.web_tax_percentage < r_5603ff.web_gmt_offset 
                    AND r_5c8072.d_last_dom < 23 
                    OR r_6af25c.w_street_name NOT LIKE 'gDDXX4' 
                ORDER BY
                    1 DESC, 2 DESC NULLS LAST, 3 NULLS LAST
            ) AS r_2c48cf 
                ON r_ef70de.i_manager_id != r_2c48cf.te_00b86d 
        WHERE
            r_c69394.t_hour = r_2c48cf.te_00b86d 
            OR r_ef70de.i_wholesale_cost < r_ef70de.i_current_price 
            AND r_c69394.t_shift LIKE r_ef70de.i_category 
            OR r_ef70de.i_rec_end_date = r_ef70de.i_rec_start_date 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC NULLS FIRST, 3 NULLS LAST) AS r_d48ce5 
                ON chr(character_length(r_6698b9.pf_3b165e)) NOT LIKE chr(hash(r_d48ce5.te_1a5945)) 
        WHERE
            r_fff6a0.i_rec_end_date < r_d48ce5.te_7e1ef9 
            OR r_8ac82c.ib_upper_bound BETWEEN 15 AND 15 
            OR r_6698b9.pf_e1d0e7 > r_6698b9.t_time_id 
            AND r_8ac82c.ib_income_band_sk < 15 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    85.18785945D / r_338416.sr_store_sk * r_338416.sr_return_amt_inc_tax as te_e2f1cb 
FROM
    db1.store_returns AS r_338416 
RIGHT JOIN
    db1.web_page AS r_1e94db 
        ON r_338416.sr_return_time_sk >= r_1e94db.wp_access_date_sk 
WHERE
    r_338416.sr_return_amt_inc_tax = 48.64394909 
    AND r_1e94db.wp_url LIKE r_1e94db.wp_type 
    AND r_1e94db.wp_rec_start_date < DATE'2024-03-26' 
ORDER BY
    1;
----------->
SELECT
    unix_seconds(now()) as te_77e74b 
FROM
    db1.call_center AS r_2728b2 
INNER JOIN
    db1.reason AS r_29071a 
        ON r_2728b2.cc_closed_date_sk <= r_29071a.r_reason_sk 
WHERE
    r_2728b2.cc_gmt_offset != 75.81193659 
    OR r_2728b2.cc_gmt_offset = r_2728b2.cc_tax_percentage 
    AND r_2728b2.cc_mkt_class ILIKE 'v7RXpRQii' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_6871ba.d_current_week as te_409a96,
    r_6871ba.d_current_quarter as te_830c7f,
    r_6871ba.d_fy_week_seq as te_b0614d 
FROM
    db1.date_dim AS r_6871ba,
    db1.date_dim AS r_efe7e6 
WHERE
    r_6871ba.d_weekend LIKE r_efe7e6.d_current_quarter 
    OR r_6871ba.d_day_name ILIKE 'gDDXX' 
    AND r_6871ba.d_date < r_efe7e6.d_date 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    try_add(r_552f2b.wp_char_count,
    42) as te_a7a268 
FROM
    db1.web_page AS r_552f2b 
WHERE
    r_552f2b.wp_link_count BETWEEN 15 AND r_552f2b.wp_image_count / 73 
    AND r_552f2b.wp_rec_start_date != DATE'2024-03-25' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_03df52.i_item_desc as te_b03818,
    r_1fd019.s_company_name as te_91a2b1,
    r_3ed41c.w_county as te_b4ef10 
FROM
    db1.store AS r_1fd019,
    db1.item AS r_03df52 
LEFT JOIN
    db1.warehouse AS r_3ed41c 
        ON r_03df52.i_units NOT LIKE 'r98hv95' 
WHERE
    r_1fd019.s_market_manager NOT ILIKE r_03df52.i_size 
    AND r_3ed41c.w_warehouse_sk < 57 
    OR r_3ed41c.w_warehouse_id ILIKE 'zm' 
    OR r_1fd019.s_market_manager LIKE r_3ed41c.w_street_type 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_d6615b.w_zip as te_c0c1a6 
FROM
    db1.warehouse AS r_d6615b 
WHERE
    r_d6615b.w_warehouse_name NOT ILIKE 'pRQii' 
    AND r_d6615b.w_warehouse_sk BETWEEN r_d6615b.w_gmt_offset / 14 AND 32 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_2278bf.c_birth_country as te_391a01 
FROM
    db1.customer AS r_2278bf 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_8159b4.t_hour as te_d3167d,
    r_8159b4.t_sub_shift as te_d23e4d 
FROM
    db1.ship_mode AS r_53c013 
RIGHT JOIN
    db1.time_dim AS r_8159b4 
        ON r_53c013.sm_ship_mode_id ILIKE r_8159b4.t_sub_shift,
    db1.household_demographics AS r_8462e5 
WHERE
    r_53c013.sm_ship_mode_sk = r_8462e5.hd_income_band_sk 
    OR r_8159b4.t_time_id IS NULL 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    try_add(r_472749.cc_rec_start_date,
    r_5df930.wp_link_count) as te_67a7e8 
FROM
    db1.call_center AS r_472749 
LEFT JOIN
    db1.web_page AS r_5df930 
        ON r_472749.cc_rec_end_date >= r_5df930.wp_rec_end_date 
WHERE
    r_472749.cc_mkt_class ILIKE '4QHZg' 
    AND r_5df930.wp_max_ad_count < 85 
    OR r_472749.cc_hours NOT ILIKE 'DCMaLQFr' 
ORDER BY
    1;
----------->
SELECT
    r_9edc1d.cc_employees as te_4a6f8e,
    current_timestamp() as te_4b123d 
FROM
    (SELECT
        hex(r_1e9bad.hd_vehicle_count) as te_0182de 
    FROM
        db1.household_demographics AS r_1e9bad 
    ORDER BY
        1 DESC NULLS LAST) AS r_9eac3f, db1.web_page AS r_6915e3 
INNER JOIN
    db1.call_center AS r_9edc1d 
        ON r_6915e3.wp_url ILIKE r_9edc1d.cc_mkt_desc 
WHERE
    (
        NOT r_9eac3f.te_0182de ILIKE r_9edc1d.cc_mkt_class 
        AND (
            NOT r_6915e3.wp_creation_date_sk BETWEEN 39 AND 39 
            AND r_9edc1d.cc_rec_end_date <= DATE'2024-03-26' 
            OR r_9edc1d.cc_rec_start_date < DATE'2024-10-11'
        )
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_2f0848.web_site_sk as te_e04f5f 
FROM
    db1.store AS r_4df70d 
INNER JOIN
    db1.web_site AS r_2f0848 
        ON r_4df70d.s_rec_end_date > r_2f0848.web_rec_start_date 
WHERE
    r_4df70d.s_company_id < 15 
    OR r_4df70d.s_city NOT IN (
        SELECT
            r_4d145d.t_sub_shift as te_b870e0 
        FROM
            (SELECT
                r_84e3ab.t_meal_time as te_1e3468,
                r_84e3ab.t_am_pm as te_a5e752 
            FROM
                db1.income_band AS r_59c166,
                db1.household_demographics AS r_d3c204 
            RIGHT JOIN
                db1.time_dim AS r_84e3ab 
                    ON r_d3c204.hd_buy_potential >= r_84e3ab.t_shift 
            WHERE
                r_59c166.ib_lower_bound != r_84e3ab.t_hour 
                AND (
                    NOT (NOT r_84e3ab.t_minute > 3 
                    OR r_d3c204.hd_vehicle_count IN (SELECT
                        r_22ef6c.cd_purchase_estimate as te_9c0b1b 
                    FROM
                        db1.customer_demographics AS r_22ef6c 
                    WHERE
                        r_22ef6c.cd_credit_rating NOT LIKE 'tb2G' 
                        AND r_22ef6c.cd_education_status NOT ILIKE r_22ef6c.cd_gender 
                        AND r_22ef6c.cd_dep_college_count >= 16))
                ) 
            ORDER BY
                1 DESC, 2 DESC NULLS LAST) AS r_03a429 
            INNER JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.time_dim PIVOT(sum(t_second) AS pa_d15b9d FOR t_shift IN (('gGIHlpx6kg') AS pf_f9603a,
                        ('7i') AS pf_e8b2d9,
                        ('v7RXpRQ') AS pf_755687))
                ) AS r_4d145d 
                    ON r_03a429.te_1e3468 LIKE r_4d145d.t_time_id,
                db1.date_dim AS r_330ab5 
            WHERE
                r_4d145d.t_meal_time = r_330ab5.d_current_day 
                OR r_4d145d.t_minute != 93 
            ORDER BY
                1) 
            ORDER BY
                1 DESC NULLS LAST;
----------->
WITH CTE_d17249(te_d84b01, te_baca99) AS (SELECT
    68.49544213D + r_c18730.d_last_dom as te_d84b01,
    r_c18730.d_last_dom as te_baca99 
FROM
    db1.customer_address AS r_bcd3f7 
INNER JOIN
    db1.date_dim AS r_c18730 
        ON r_bcd3f7.ca_location_type ILIKE r_c18730.d_following_holiday,
    (SELECT
        r_9e5f3e.s_rec_end_date as te_68f2cd 
    FROM
        db1.store AS r_9e5f3e 
    WHERE
        r_9e5f3e.s_division_id > 75 
        AND r_9e5f3e.s_hours LIKE '7RX' 
    ORDER BY
        1 DESC) AS r_67c739 
WHERE
    r_c18730.d_date < r_67c739.te_68f2cd 
    OR r_c18730.d_date <= r_67c739.te_68f2cd 
    AND r_c18730.d_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC NULLS FIRST) SELECT
        r_b09de3.i_item_sk as te_2294c7, r_b09de3.i_manufact_id as te_ca290d, r_b09de3.i_color as te_3cf7bd 
    FROM
        db1.item AS r_b09de3,
        CTE_d17249 AS r_0e9f52 
    WHERE
        r_b09de3.i_item_sk > r_0e9f52.te_d84b01 
    ORDER BY
        1 NULLS LAST, 2 NULLS FIRST, 3 DESC 
    LIMIT 84;
----------->
SELECT
    r_2d549d.ca_address_sk as te_3b603b 
FROM
    db1.household_demographics AS r_359c94 
RIGHT JOIN
    db1.customer_address AS r_2d549d 
        ON r_359c94.hd_buy_potential NOT LIKE r_2d549d.ca_address_id 
WHERE
    r_2d549d.ca_street_name NOT ILIKE '6kg' 
    AND r_2d549d.ca_location_type NOT LIKE 'LQFr' 
    OR r_359c94.hd_dep_count >= r_359c94.hd_demo_sk 
    OR r_2d549d.ca_suite_number IS NULL 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_0c32d8.te_62cbfc as te_97950c,
    r_102db9.web_rec_start_date as te_654a8f,
    r_102db9.web_zip as te_3ae218 
FROM
    db1.web_site AS r_102db9,
    (SELECT
        r_6e5da9.wp_customer_sk as te_62cbfc,
        r_f721ac.ca_city as te_b4b1fe 
    FROM
        (SELECT
            r_7199d2.web_rec_start_date as te_7289c6,
            r_b6ac6e.i_manufact_id as te_6aca16 
        FROM
            db1.customer AS r_9ff8dd 
        RIGHT JOIN
            db1.item AS r_b6ac6e 
                ON r_9ff8dd.c_login LIKE r_b6ac6e.i_category,
            db1.web_site AS r_7199d2 
        WHERE
            r_b6ac6e.i_rec_end_date = r_7199d2.web_rec_end_date 
            OR r_b6ac6e.i_item_sk > 79 
            AND r_7199d2.web_state ILIKE r_b6ac6e.i_item_id 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_de92ed 
    LEFT JOIN
        db1.customer_address AS r_f721ac 
            ON r_de92ed.te_6aca16 <= r_f721ac.ca_address_sk,
        db1.web_page AS r_6e5da9 
    WHERE
        r_f721ac.ca_street_number ILIKE r_6e5da9.wp_type 
        OR r_f721ac.ca_gmt_offset > 42.07360537 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST) AS r_0c32d8 
    WHERE
        r_102db9.web_site_id ILIKE r_0c32d8.te_b4b1fe 
        AND r_102db9.web_rec_start_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
        OR r_102db9.web_tax_percentage != r_102db9.web_gmt_offset 
    ORDER BY
        1 DESC, 2 ASC NULLS LAST, 3 ASC NULLS FIRST 
    LIMIT 80 OFFSET 90;
----------->
SELECT
    reverse(r_540a00.web_mkt_class) as te_a82a5c 
FROM
    db1.date_dim AS r_313d91 
LEFT JOIN
    db1.web_site AS r_540a00 
        ON r_313d91.d_weekend ILIKE r_540a00.web_site_id 
WHERE
    r_540a00.web_rec_end_date = r_313d91.d_date 
ORDER BY
    1 DESC;
----------->
SELECT
    r_dbe440.c_email_address as te_90b1cd,
    r_dbe440.c_customer_id as te_36258b 
FROM
    db1.customer AS r_dbe440,
    (SELECT
        * 
    FROM
        db1.time_dim UNPIVOT EXCLUDE NULLS ((up_f9a913,
        up_0a3b1e) FOR upn_45a347 IN ((t_meal_time,
        t_time_sk) AS UPF_068d27,
        (t_shift,
        t_time) AS UPF_16b43b,
        (t_sub_shift,
        t_minute) AS UPF_d1593b,
        (t_am_pm,
        t_hour) AS UPF_43a17b,
        (t_time_id,
        t_second) AS UPF_2423a7))) AS r_aca0d4 
RIGHT JOIN
    db1.household_demographics AS r_965e6f 
        ON r_aca0d4.upn_45a347 = r_965e6f.hd_buy_potential 
WHERE
    r_dbe440.c_first_shipto_date_sk >= r_965e6f.hd_vehicle_count 
    AND r_aca0d4.up_0a3b1e >= 24 
ORDER BY
    1 DESC, 2 NULLS LAST;
----------->
WITH CTE_ff0f1c(te_1abf4e) AS (SELECT
    reflect('java.util.UUID',
    'randomUUID') as te_1abf4e 
FROM
    db1.reason AS r_b489bd 
INNER JOIN
    (SELECT
        chr(r_5c1c0e.r_reason_sk) || chr(r_06cb2d.te_dbf50f) as te_1b20ab,
        r_5c1c0e.r_reason_id as te_91b70a 
    FROM
        (SELECT
            r_808475.te_429aeb * r_808475.te_e02d8d as te_dbf50f 
        FROM
            ((SELECT
                TIMESTAMP'2024-03-25 23:02:15.037' as te_d13f92,
                r_ed3b78.s_tax_percentage as te_429aeb,
                r_1b6447.t_minute + 18 as te_e02d8d 
            FROM
                db1.warehouse AS r_89dbbf,
                db1.time_dim AS r_1b6447 
            INNER JOIN
                (SELECT
                    * 
                FROM
                    db1.store UNPIVOT INCLUDE NULLS ((up_d74a23,
                    up_eb6246,
                    up_311952,
                    up_f6c925,
                    up_6f71db) FOR upn_6e95a0 IN ((s_rec_start_date,
                    s_store_sk,
                    s_store_id,
                    s_street_number,
                    s_gmt_offset) AS UPF_8db9ff))) AS r_ed3b78 
                    ON r_1b6447.t_time != r_ed3b78.s_closed_date_sk 
            WHERE
                (NOT r_89dbbf.w_county NOT LIKE r_ed3b78.s_company_name 
                AND r_1b6447.t_sub_shift LIKE '7RXp') 
            ORDER BY
                1 DESC, 2 NULLS LAST, 3 ASC NULLS LAST) 
            UNION
            ALL (SELECT
                r_c2bb70.te_4a5398 as te_266d50,
                r_c8b790.i_wholesale_cost as te_302b39,
                unix_timestamp() as te_c50622 
            FROM
                (SELECT
                    timestamp_millis(r_35ddb9.c_first_sales_date_sk) as te_4a5398 
                FROM
                    db1.customer AS r_35ddb9 
                WHERE
                    r_35ddb9.c_email_address ILIKE r_35ddb9.c_login 
                    AND (NOT r_35ddb9.c_current_cdemo_sk = 92 
                    AND r_35ddb9.c_customer_id LIKE r_35ddb9.c_preferred_cust_flag) 
                ORDER BY
                    1 DESC NULLS LAST 
                LIMIT 55) AS r_c2bb70 INNER JOIN
                db1.web_site AS r_8e8b09 
                    ON date(r_c2bb70.te_4a5398) != r_8e8b09.web_rec_start_date,
                db1.item AS r_c8b790 
            LEFT JOIN
                ((SELECT
                    hash(r_1e04ea.s_state,
                    r_1e04ea.s_rec_end_date) as te_bbfa69 
                FROM
                    db1.store AS r_d16af7 
                LEFT JOIN
                    db1.store AS r_1e04ea 
                        ON r_d16af7.s_rec_end_date != r_1e04ea.s_rec_start_date 
                WHERE
                    r_1e04ea.s_division_id <= 26 
                    OR r_d16af7.s_market_id < r_d16af7.s_closed_date_sk 
                    OR r_1e04ea.s_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
                    AND r_1e04ea.s_store_id LIKE '0lez' 
                ORDER BY
                    1 NULLS FIRST) MINUS ALL SELECT
                    r_f7b048.i_item_sk as te_1082ce 
                FROM
                    db1.store_returns AS r_f306a8 
                INNER JOIN
                    (SELECT
                        r_3e97a1.wp_image_count as te_a4d1b6 
                    FROM
                        db1.web_page AS r_3e97a1 
                    WHERE
                        r_3e97a1.wp_image_count > 56 
                    ORDER BY
                        1 NULLS LAST) AS r_1a4f40 
                        ON r_f306a8.sr_return_tax >= r_1a4f40.te_a4d1b6,
                    db1.item AS r_f7b048 
                WHERE
                    r_f306a8.sr_return_quantity = r_f7b048.i_class_id 
                ORDER BY
                    1 DESC NULLS LAST) AS r_295716 
                        ON r_c8b790.i_category_id < r_295716.te_bbfa69 
                WHERE
                    r_8e8b09.web_close_date_sk != r_c8b790.i_manufact_id 
                    OR NOT r_c8b790.i_container ILIKE r_c8b790.i_category 
                ORDER BY
                    1 NULLS LAST, 2 NULLS LAST, 3 DESC) 
            ORDER BY
                1 NULLS LAST, 2 NULLS FIRST, 3 ASC NULLS LAST) AS r_808475 
            INNER JOIN
                db1.customer_address AS r_f3f981 
                    ON r_808475.te_429aeb <= r_f3f981.ca_gmt_offset 
            WHERE
                r_f3f981.ca_country NOT LIKE 'rT' 
            ORDER BY
                1 DESC NULLS FIRST 
            LIMIT 2) AS r_06cb2d, db1.reason AS r_5c1c0e WHERE
                chr(r_06cb2d.te_dbf50f) ILIKE r_5c1c0e.r_reason_desc 
            ORDER BY
                1 NULLS FIRST, 2 DESC NULLS LAST 
            OFFSET 24) AS r_b58286 
                ON r_b489bd.r_reason_desc NOT ILIKE r_b58286.te_1b20ab WHERE
                    r_b489bd.r_reason_desc LIKE r_b489bd.r_reason_id 
                    AND r_b489bd.r_reason_id ILIKE r_b489bd.r_reason_desc 
                    AND r_b58286.te_91b70a ILIKE 't' 
            ORDER BY
                1 NULLS FIRST), CTE_ac8143(te_0d5403) AS (SELECT
                r_938648.w_county as te_0d5403 
            FROM
                db1.warehouse AS r_938648 
            ORDER BY
                1 DESC NULLS LAST 
            LIMIT 7) SELECT
            r_15ed52.te_1abf4e as te_13c21d FROM
                CTE_ff0f1c AS r_15ed52 
            WHERE
                r_15ed52.te_1abf4e ILIKE r_15ed52.te_1abf4e 
                AND r_15ed52.te_1abf4e IN (
                    SELECT
                        r_81477d.r_reason_id as te_b0ff7d 
                    FROM
                        db1.reason AS r_81477d 
                    WHERE
                        r_81477d.r_reason_id ILIKE r_81477d.r_reason_desc 
                        AND r_81477d.r_reason_id ILIKE 'iizz2kGk4' 
                        OR r_81477d.r_reason_sk != 42 
                    ORDER BY
                        1 DESC NULLS FIRST
                ) 
            ORDER BY
                1 DESC NULLS FIRST;
----------->
SELECT
    r_72a817.i_item_id as te_228ed1 
FROM
    (SELECT
        * 
    FROM
        db1.item PIVOT(avg(i_manufact_id) AS pa_e65e73 FOR i_rec_start_date IN ((DATE'2024-03-25') AS pf_8ec01d,
        (DATE'2024-10-11') AS pf_fc5c63,
        (DATE'2024-03-26') AS pf_bd0093,
        (DATE'2024-10-11') AS pf_64c90a,
        (DATE'2024-03-25') AS pf_040baf,
        (DATE'2024-03-25') AS pf_8f4f3d))) AS r_72a817 
RIGHT JOIN
    db1.ship_mode AS r_920c41 
        ON r_72a817.i_manager_id = r_920c41.sm_ship_mode_sk 
WHERE
    r_72a817.i_units NOT ILIKE 'RXp' 
    OR r_920c41.sm_contract NOT ILIKE 'HZgGI' 
    AND r_72a817.pf_bd0093 < 20.95478131D 
ORDER BY
    1 ASC;
----------->
SELECT
    r_5c78b1.cc_rec_start_date as te_43f324,
    r_ba7179.ib_lower_bound as te_64e342,
    r_da63bc.ca_street_number as te_7d7493 
FROM
    db1.income_band AS r_ba7179 
INNER JOIN
    db1.customer_address AS r_da63bc 
        ON r_ba7179.ib_upper_bound < r_da63bc.ca_address_sk,
    db1.call_center AS r_5c78b1 
WHERE
    r_ba7179.ib_lower_bound = r_5c78b1.cc_closed_date_sk 
ORDER BY
    1 DESC, 2 ASC, 3 ASC NULLS LAST 
OFFSET 86;
----------->
SELECT
    r_298a85.w_county as te_db4c34,
    mod(r_d01112.ib_income_band_sk,
    34.68720238D) as te_1cfa3a 
FROM
    db1.income_band AS r_d01112 
RIGHT JOIN
    db1.store_returns AS r_63311a 
        ON r_d01112.ib_lower_bound > r_63311a.sr_customer_sk,
    db1.warehouse AS r_298a85 
WHERE
    r_63311a.sr_return_tax < r_298a85.w_warehouse_sq_ft 
    AND r_63311a.sr_fee >= r_63311a.sr_refunded_cash 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_66d468.t_time_id as te_5bcf3f 
FROM
    db1.time_dim AS r_66d468 
LEFT JOIN
    db1.store_returns AS r_b274d2 
        ON r_66d468.t_second = r_b274d2.sr_refunded_cash 
WHERE
    (
        NOT r_b274d2.sr_fee < 84.43277749 
        AND r_b274d2.sr_return_amt IN (
            SELECT
                r_1c120f.w_warehouse_sk * r_1c120f.w_gmt_offset as te_95b7ed 
            FROM
                db1.income_band AS r_97cf2f 
            INNER JOIN
                db1.warehouse AS r_1c120f 
                    ON r_97cf2f.ib_income_band_sk = r_1c120f.w_warehouse_sk 
            WHERE
                r_1c120f.w_street_type LIKE r_1c120f.w_zip 
                OR r_1c120f.w_warehouse_sk = r_97cf2f.ib_income_band_sk 
                AND NOT r_1c120f.w_street_number NOT LIKE 'bBRGC' 
            ORDER BY
                1 NULLS LAST
        ) 
        AND r_66d468.t_am_pm NOT ILIKE 'r98' 
        AND r_b274d2.sr_net_loss >= r_b274d2.sr_return_amt_inc_tax
    ) 
ORDER BY
    1 NULLS FIRST 
LIMIT 81;
----------->
SELECT
    try_add(r_8c905d.ib_upper_bound,
    r_8c905d.ib_upper_bound) as te_f3d4d9 
FROM
    db1.income_band AS r_8c905d 
WHERE
    r_8c905d.ib_income_band_sk >= 4 
    AND r_8c905d.ib_lower_bound > 25 
    AND r_8c905d.ib_income_band_sk > r_8c905d.ib_upper_bound 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_f18cc9.t_sub_shift as te_36f93a,
    r_f18cc9.t_hour as te_5b0753,
    r_f18cc9.t_time_sk as te_907a54 
FROM
    db1.ship_mode AS r_3efd84,
    db1.time_dim AS r_f18cc9 
WHERE
    r_3efd84.sm_type <= r_f18cc9.t_am_pm 
    AND r_3efd84.sm_carrier ILIKE 'XUuls' 
    OR r_f18cc9.t_shift >= 'rTUO' 
    OR r_f18cc9.t_hour > 50 
    AND r_f18cc9.t_minute BETWEEN r_f18cc9.t_time_sk * 54 AND 17 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3;
----------->
SELECT
    chr(r_6075e5.sr_return_tax) as te_52816c,
    r_63f28a.cd_gender as te_23cc11,
    chr(r_6075e5.sr_returned_date_sk) as te_9e7c2e 
FROM
    db1.customer AS r_ac5274 
INNER JOIN
    db1.store_returns AS r_6075e5 
        ON r_ac5274.c_last_review_date_sk < r_6075e5.sr_customer_sk,
    db1.customer_demographics AS r_63f28a 
WHERE
    r_ac5274.c_customer_id < r_63f28a.cd_credit_rating 
    AND r_ac5274.c_birth_country NOT LIKE 'lB' 
    OR r_6075e5.sr_store_sk > 20 
    OR r_6075e5.sr_item_sk BETWEEN 81 AND r_63f28a.cd_demo_sk - 23 
    AND EXISTS (
        SELECT
            r_fc43c8.s_rec_start_date as te_bf871d,
            r_fc43c8.s_street_name as te_136ce7,
            r_0b2c34.web_state as te_07de40 
        FROM
            db1.household_demographics AS r_82652b 
        LEFT JOIN
            db1.web_site AS r_0b2c34 
                ON r_0b2c34.web_site_sk > 70,
            db1.store AS r_fc43c8 
        WHERE
            r_0b2c34.web_rec_start_date != r_fc43c8.s_rec_start_date 
            AND r_82652b.hd_dep_count <= 84 
            OR r_0b2c34.web_zip NOT LIKE r_0b2c34.web_market_manager 
            AND r_fc43c8.s_state NOT LIKE 'kGk4' 
        ORDER BY
            1 ASC NULLS FIRST, 2 DESC NULLS LAST, 3 NULLS LAST
    ) 
ORDER BY
    1, 2 DESC, 3 ASC NULLS LAST;
----------->
SELECT
    add_months(make_date(r_90c1c5.hd_vehicle_count,
    r_90c1c5.hd_income_band_sk,
    r_90c1c5.hd_demo_sk),
    bigint(r_90c1c5.hd_dep_count)) as te_8cfa85 
FROM
    db1.household_demographics AS r_90c1c5 
WHERE
    r_90c1c5.hd_buy_potential ILIKE 'mSRIvXU' 
    OR r_90c1c5.hd_dep_count < 7 
    OR r_90c1c5.hd_vehicle_count > r_90c1c5.hd_income_band_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_01b3ac.web_gmt_offset as te_a9ab2a 
FROM
    db1.web_site AS r_01b3ac 
INNER JOIN
    db1.date_dim AS r_6c2d67 
        ON r_01b3ac.web_open_date_sk < r_6c2d67.d_dom 
WHERE
    r_6c2d67.d_month_seq > 41 
    OR r_01b3ac.web_rec_start_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    OR r_01b3ac.web_company_name NOT LIKE 'tntb' 
    OR r_01b3ac.web_open_date_sk <= 48 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_c100a2.web_gmt_offset as te_f76343,
    r_c100a2.web_rec_end_date as te_ccb2e2,
    string(r_ec3431.pf_6a1544) as te_1b6c5e 
FROM
    db1.web_site AS r_c100a2,
    (SELECT
        r_a535a6.cd_dep_college_count as te_73abc5 
    FROM
        db1.customer_demographics AS r_a535a6 
    WHERE
        r_a535a6.cd_dep_college_count <= 5 
        OR r_a535a6.cd_marital_status ILIKE 'G6sUg' 
        OR r_a535a6.cd_demo_sk > 71 
    ORDER BY
        1 DESC 
    OFFSET 51) AS r_fbcfde INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.household_demographics PIVOT(max(hd_dep_count) AS pa_b56c79 FOR hd_income_band_sk IN ((48) AS pf_19614b,
            (60) AS pf_6a1544,
            (11) AS pf_f4a673,
            (66) AS pf_4838d9,
            (98) AS pf_57aa32))
    ) AS r_ec3431 
        ON r_fbcfde.te_73abc5 != r_ec3431.pf_19614b 
WHERE
    r_c100a2.web_close_date_sk > r_ec3431.pf_6a1544 
    OR r_c100a2.web_mkt_desc NOT ILIKE 'RQ' 
    AND r_c100a2.web_gmt_offset IS NULL 
    OR r_c100a2.web_street_name NOT LIKE 'GIHl' 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST 
LIMIT 58;
----------->
SELECT
    r_140420.sm_type as te_4fc434 
FROM
    db1.household_demographics AS r_1fcacf 
INNER JOIN
    db1.ship_mode AS r_140420 
        ON r_1fcacf.hd_buy_potential LIKE r_140420.sm_contract 
WHERE
    r_140420.sm_code LIKE 'RGC0lezm' 
    AND r_140420.sm_code LIKE 'k4' 
    OR r_140420.sm_contract NOT ILIKE '0lezmSR' 
    OR r_1fcacf.hd_dep_count < 83 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    pi() as te_760089,
    chr(r_5597a6.cd_demo_sk) as te_749243,
    r_eb2104.wp_rec_end_date as te_cd8bf2 
FROM
    db1.web_page AS r_eb2104,
    db1.customer_demographics AS r_17b3db 
RIGHT JOIN
    db1.customer_demographics AS r_5597a6 
        ON r_17b3db.cd_dep_count >= r_5597a6.cd_demo_sk 
WHERE
    r_eb2104.wp_max_ad_count > r_17b3db.cd_dep_employed_count 
    AND (
        NOT r_17b3db.cd_marital_status NOT ILIKE 'l' 
        OR r_17b3db.cd_dep_count < 11 
        AND (
            NOT r_17b3db.cd_dep_count >= r_eb2104.wp_access_date_sk 
            OR r_eb2104.wp_rec_start_date < r_eb2104.wp_rec_end_date
        )
    ) 
ORDER BY
    1 ASC, 2 DESC NULLS FIRST, 3 NULLS LAST;
----------->
SELECT
    r_57a61e.cc_gmt_offset as te_8dbe82 
FROM
    db1.call_center AS r_57a61e 
INNER JOIN
    db1.customer_demographics AS r_3be198 
        ON r_57a61e.cc_open_date_sk >= r_3be198.cd_dep_employed_count 
WHERE
    r_57a61e.cc_employees != 70 
    OR r_57a61e.cc_rec_start_date < r_57a61e.cc_rec_end_date 
    OR r_57a61e.cc_call_center_sk <= 66 
    OR r_57a61e.cc_market_manager <= 'C0lezmSRIv' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_f558ee.cc_tax_percentage as te_8ea923,
    r_13cf9b.w_county as te_968fdd 
FROM
    db1.call_center AS r_f558ee 
LEFT JOIN
    db1.customer_demographics AS r_820c06 
        ON r_f558ee.cc_tax_percentage > r_820c06.cd_dep_count,
    db1.warehouse AS r_13cf9b 
RIGHT JOIN
    db1.time_dim AS r_3b06d6 
        ON r_13cf9b.w_street_number ILIKE r_3b06d6.t_meal_time 
WHERE
    r_f558ee.cc_zip NOT ILIKE r_13cf9b.w_warehouse_id 
    AND r_f558ee.cc_market_manager NOT ILIKE 'pR' 
    AND r_3b06d6.t_shift NOT ILIKE 'Gk4elBy7IG' 
    AND r_3b06d6.t_time_sk BETWEEN 23 AND 23 
    OR r_3b06d6.t_am_pm = r_f558ee.cc_street_type 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST;
----------->
WITH CTE_00009a(te_ff78ba, te_c8b56b, te_1e9258) AS (SELECT
    r_30286c.r_reason_sk as te_ff78ba,
    r_778264.i_manufact_id as te_c8b56b,
    r_778264.i_current_price as te_1e9258 
FROM
    db1.item AS r_778264,
    db1.reason AS r_30286c 
WHERE
    r_778264.i_category_id = r_30286c.r_reason_sk 
    OR r_778264.i_category NOT ILIKE 'RXpRQiizz2'), CTE_18173c(te_46be56, te_83db98, te_b3b049) AS (SELECT
    r_22cbaa.cc_gmt_offset as te_46be56,
    r_22cbaa.cc_gmt_offset as te_83db98,
    66 * r_8e52ca.hd_vehicle_count as te_b3b049 
FROM
    db1.income_band AS r_7582f6 
RIGHT JOIN
    db1.call_center AS r_22cbaa 
        ON r_7582f6.ib_lower_bound = r_22cbaa.cc_closed_date_sk,
    db1.customer_demographics AS r_e9bc16 
RIGHT JOIN
    db1.household_demographics AS r_8e52ca 
        ON r_e9bc16.cd_dep_count <= r_8e52ca.hd_demo_sk 
WHERE
    r_22cbaa.cc_call_center_id ILIKE r_e9bc16.cd_credit_rating 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 ASC) SELECT
    r_45c367.te_1e9258 as te_f90385 
FROM
    CTE_00009a AS r_45c367 
WHERE
    r_45c367.te_1e9258 != 79.50243588 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_318315.t_hour as te_aea619,
    r_4cf3f8.cd_gender as te_55c490 
FROM
    db1.time_dim AS r_318315 
RIGHT JOIN
    db1.income_band AS r_f7733c 
        ON r_318315.t_hour != r_f7733c.ib_upper_bound,
    db1.customer_demographics AS r_8cb364 
LEFT JOIN
    db1.customer_demographics AS r_4cf3f8 
        ON r_8cb364.cd_purchase_estimate = r_4cf3f8.cd_purchase_estimate 
WHERE
    r_318315.t_sub_shift ILIKE r_8cb364.cd_education_status 
    OR r_8cb364.cd_marital_status NOT LIKE r_4cf3f8.cd_credit_rating 
    OR r_f7733c.ib_lower_bound >= 90 
ORDER BY
    1 ASC, 2 NULLS LAST;
----------->
SELECT
    r_717237.w_street_number as te_9b8311,
    r_4908a3.ca_county as te_a00bd1,
    r_717237.w_street_number as te_f96713 
FROM
    db1.customer_address AS r_4908a3,
    db1.income_band AS r_0ef714 
INNER JOIN
    db1.warehouse AS r_717237 
        ON r_0ef714.ib_income_band_sk = r_717237.w_warehouse_sk 
WHERE
    r_4908a3.ca_city NOT ILIKE r_717237.w_city 
    OR r_717237.w_street_number LIKE '7' 
    AND (
        NOT r_4908a3.ca_county ILIKE 's' 
        AND r_4908a3.ca_location_type ILIKE r_4908a3.ca_zip
    ) 
ORDER BY
    1, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    btrim(r_278158.s_store_id) as te_16a775 
FROM
    (SELECT
        r_bb9581.d_current_quarter as te_1ff125,
        r_66a473.w_gmt_offset as te_60ae70 
    FROM
        db1.warehouse AS r_66a473 
    RIGHT JOIN
        db1.date_dim AS r_bb9581 
            ON r_66a473.w_state NOT ILIKE r_bb9581.d_quarter_name,
        db1.warehouse AS r_3de3bf 
    WHERE
        r_66a473.w_country NOT ILIKE r_3de3bf.w_country 
        AND r_66a473.w_county IS NULL 
        AND r_bb9581.d_current_month NOT ILIKE 'lBy' 
    ORDER BY
        1 DESC NULLS FIRST, 2 ASC 
    OFFSET 39) AS r_d30b03 RIGHT JOIN
    db1.store AS r_278158 
        ON r_d30b03.te_1ff125 NOT LIKE r_278158.s_store_id 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_1a8bf7.d_quarter_seq as te_c0c4f8,
    r_c3a45f.cc_rec_start_date as te_0999b6,
    r_de4e34.i_wholesale_cost as te_96776a 
FROM
    db1.date_dim AS r_1a8bf7,
    db1.call_center AS r_c3a45f 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.item PIVOT(max(i_formulation) AS pa_f7e944 FOR i_units IN (('iizz2kGk') AS pf_dcc844,
            ('GC0lezmS') AS pf_c86b5c,
            ('pRQ') AS pf_8f3cc9,
            ('lBy7I') AS pf_3de02a,
            ('RGC0l') AS pf_5573db))
    ) AS r_de4e34 
        ON r_c3a45f.cc_tax_percentage = r_de4e34.i_current_price 
WHERE
    r_1a8bf7.d_date = r_c3a45f.cc_rec_start_date 
    OR r_de4e34.i_manufact_id BETWEEN r_de4e34.i_manufact_id AND 59 
    AND (
        NOT r_de4e34.i_class LIKE '2kGk4elBy7' 
        AND r_c3a45f.cc_gmt_offset != 30.17811266 
        OR r_de4e34.i_rec_end_date > DATE'2024-03-25'
    ) 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_2d843a.web_tax_percentage as te_3df886 
FROM
    db1.web_site AS r_2d843a 
WHERE
    r_2d843a.web_name NOT LIKE 'GIH' 
    AND r_2d843a.web_tax_percentage = r_2d843a.web_gmt_offset 
    AND r_2d843a.web_rec_start_date = r_2d843a.web_rec_end_date 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    e() as te_7ec091,
    r_074dc8.r_reason_sk as te_584144 
FROM
    db1.reason AS r_074dc8,
    db1.income_band AS r_2621a3 
WHERE
    r_074dc8.r_reason_sk = r_2621a3.ib_upper_bound 
    AND r_074dc8.r_reason_desc NOT LIKE '8hv95G6s' 
    AND r_2621a3.ib_lower_bound < 90 
    OR r_2621a3.ib_lower_bound = 22 
    OR r_074dc8.r_reason_id < 'GIHlpx6kg' 
ORDER BY
    1 DESC, 2 DESC NULLS FIRST 
OFFSET 17;
----------->
SELECT
    r_df0a14.sr_return_amt as te_40b597,
    r_df0a14.web_county as te_9fff24,
    r_df0a14.sr_fee as te_9f0e1f 
FROM
    db1.store_returns AS r_df0a14,
    (SELECT
        make_timestamp(r_c6fb35.s_store_sk,
        r_9e81e7.s_store_sk,
        r_8dc96c.d_moy,
        r_c6fb35.s_floor_space,
        r_c6fb35.s_division_id,
        r_c6fb35.s_number_employees) as te_03d43e,
        r_9e81e7.s_market_id as te_4c06a7,
        r_c6fb35.s_rec_start_date as te_6c63d9 
    FROM
        (SELECT
            r_c1cfb7.r_reason_sk as te_0ea4dd 
        FROM
            db1.reason AS r_c1cfb7 
        WHERE
            r_c1cfb7.r_reason_sk != 30 
            AND r_c1cfb7.r_reason_desc LIKE 'BRGC0l' 
            OR r_c1cfb7.r_reason_id NOT ILIKE r_c1cfb7.r_reason_desc 
            AND r_c1cfb7.r_reason_desc ILIKE r_c1cfb7.r_reason_id 
        ORDER BY
            1 NULLS LAST) AS r_877c02 
    RIGHT JOIN
        db1.date_dim AS r_8dc96c 
            ON r_877c02.te_0ea4dd <= r_8dc96c.d_same_day_lq,
        db1.store AS r_c6fb35 
    LEFT JOIN
        db1.store AS r_9e81e7 
            ON r_c6fb35.s_rec_start_date >= r_9e81e7.s_rec_end_date 
    WHERE
        r_8dc96c.d_date < r_9e81e7.s_rec_start_date 
        OR r_8dc96c.d_last_dom > 45 
        AND r_c6fb35.s_store_name ILIKE '4elBy7I' 
        AND r_c6fb35.s_tax_percentage >= 31.18528089 
    ORDER BY
        1 ASC, 2 ASC NULLS LAST, 3) AS r_59fc78 
    INNER JOIN
        (
            SELECT
                r_ec9502.wp_web_page_id as te_23a17d,
                r_ec9502.wp_image_count as te_ce235e,
                r_34320e.ca_address_id as te_9e3482 
            FROM
                db1.customer AS r_97e747 
            LEFT JOIN
                db1.customer_address AS r_34320e 
                    ON r_97e747.c_salutation NOT ILIKE r_34320e.ca_street_number,
                db1.web_page AS r_ec9502 
            WHERE
                r_97e747.c_first_sales_date_sk > r_ec9502.wp_max_ad_count 
                AND r_34320e.ca_country NOT LIKE 'Q' 
                AND r_97e747.c_last_review_date_sk < r_97e747.c_current_cdemo_sk 
                OR r_34320e.ca_zip LIKE 'z' 
                OR r_ec9502.wp_rec_end_date = r_ec9502.wp_rec_start_date 
            ORDER BY
                1 DESC NULLS FIRST, 2 NULLS FIRST, 3 DESC NULLS LAST
        ) AS r_0229ba 
            ON r_59fc78.te_4c06a7 > r_0229ba.te_ce235e 
    WHERE
        r_df0a14.sr_return_amt_inc_tax != r_0229ba.te_ce235e 
        OR NOT r_df0a14.sr_net_loss != 99.55896342 
    ORDER BY
        1 DESC NULLS FIRST, 2 ASC NULLS FIRST, 3 ASC;
----------->
SELECT
    make_date(r_75cb00.d_same_day_lq,
    r_75cb00.d_fy_week_seq,
    r_fab17c.s_floor_space) as te_4b86e1,
    r_75cb00.d_same_day_ly as te_3ac5aa,
    string(true) as te_ad7c88 
FROM
    db1.date_dim AS r_75cb00,
    db1.store AS r_fab17c 
WHERE
    r_75cb00.d_date = r_fab17c.s_rec_end_date 
    OR r_fab17c.s_manager ILIKE 'v' 
    AND r_75cb00.d_following_holiday NOT ILIKE 'b2Gv7' 
    AND r_fab17c.s_geography_class LIKE 'UO' 
    AND r_75cb00.d_current_day NOT LIKE '4' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST;
----------->
WITH CTE_8c4c21(te_f0d5ae) AS (SELECT
    r_0273c3.i_category_id as te_f0d5ae 
FROM
    db1.warehouse AS r_6644cc 
INNER JOIN
    db1.item AS r_0273c3 
        ON r_6644cc.w_warehouse_sk >= r_0273c3.i_item_sk 
WHERE
    r_0273c3.i_product_name LIKE 'C0lezm' 
    AND r_0273c3.i_formulation ILIKE r_0273c3.i_category 
    AND r_6644cc.w_country > r_0273c3.i_manufact 
    OR r_0273c3.i_class LIKE 'hv9') SELECT
    r_825eb6.te_f0d5ae * 1.61990016 as te_1b78a9 
FROM
    CTE_8c4c21 AS r_825eb6 
WHERE
    r_825eb6.te_f0d5ae > 24 
    OR r_825eb6.te_f0d5ae = r_825eb6.te_f0d5ae 
    AND r_825eb6.te_f0d5ae = 26 
    OR r_825eb6.te_f0d5ae >= 70 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_dde479.wp_rec_start_date as te_49471f 
FROM
    db1.warehouse AS r_cc4f7d 
RIGHT JOIN
    db1.web_page AS r_dde479 
        ON r_cc4f7d.w_warehouse_sk >= r_dde479.wp_customer_sk 
WHERE
    r_dde479.wp_link_count > 0 
    AND r_dde479.wp_image_count = 37 
    AND r_dde479.wp_type NOT LIKE 'aLQFrTUOV' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_7b1b9d.hd_vehicle_count as te_03df7b,
    r_3761b1.hd_income_band_sk as te_526cb0 
FROM
    db1.household_demographics AS r_3761b1,
    db1.household_demographics AS r_7b1b9d 
WHERE
    r_3761b1.hd_income_band_sk = r_7b1b9d.hd_income_band_sk 
    OR r_7b1b9d.hd_demo_sk BETWEEN 31 AND 31 
    AND r_7b1b9d.hd_income_band_sk != 41 
    OR r_7b1b9d.hd_income_band_sk < r_7b1b9d.hd_demo_sk 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST 
LIMIT 86;
----------->
SELECT
    r_2126b2.t_time as te_e0be79,
    r_2126b2.t_hour as te_62b7bb,
    r_2126b2.t_shift as te_310b11 
FROM
    db1.ship_mode AS r_e9261a,
    db1.time_dim AS r_2126b2 
WHERE
    r_e9261a.sm_ship_mode_id ILIKE r_2126b2.t_meal_time 
    OR r_2126b2.t_meal_time NOT ILIKE 'lBy7IG7i' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_177945.wp_web_page_id as te_502a16 
FROM
    db1.web_page AS r_177945 
WHERE
    r_177945.wp_access_date_sk != 39 
    AND r_177945.wp_autogen_flag NOT IN (
        SELECT
            chr((r_6f1c19.te_159c2f + 26.1621399D) * 29.36333972D * r_60f792.w_warehouse_sq_ft) as te_7e0116 
        FROM
            (SELECT
                r_8070a5.pf_70a76b as te_159c2f 
            FROM
                (SELECT
                    * 
                FROM
                    db1.customer_address UNPIVOT INCLUDE NULLS ((up_d4ecc0,
                    up_9b2325,
                    up_953daa,
                    up_d93b9b) FOR upn_01febb IN ((ca_gmt_offset,
                    ca_address_sk,
                    ca_address_id,
                    ca_country) AS UPF_28998d))) AS r_6aa3ea 
            INNER JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.household_demographics PIVOT(count(hd_dep_count) AS pa_807ef6 FOR hd_income_band_sk IN ((38) AS pf_70a76b,
                        (0) AS pf_25041c,
                        (77) AS pf_cc0903))
                ) AS r_8070a5 
                    ON r_6aa3ea.up_d93b9b NOT LIKE r_8070a5.hd_buy_potential 
            WHERE
                r_6aa3ea.ca_county LIKE 'pRQiizz2k' 
            ORDER BY
                1 DESC 
            LIMIT 46) AS r_6f1c19 INNER JOIN
                db1.warehouse AS r_60f792 
                    ON r_6f1c19.te_159c2f <= bigint(r_60f792.w_warehouse_sk),
                (SELECT
                    r_fd3d20.web_gmt_offset as te_990415,
                    positive(positive(negative(r_fd3d20.web_site_sk - 84))) as te_4bc8ba 
                FROM
                    db1.web_site AS r_fd3d20 
                INNER JOIN
                    db1.time_dim AS r_b65aed 
                        ON r_fd3d20.web_name NOT ILIKE r_b65aed.t_time_id,
                    db1.warehouse AS r_a2d77d 
                WHERE
                    r_fd3d20.web_gmt_offset = r_a2d77d.w_gmt_offset 
                    AND r_fd3d20.web_company_name NOT ILIKE 'kg7x2S' 
                    OR r_fd3d20.web_street_name ILIKE '2k' 
                    AND r_b65aed.t_meal_time ILIKE 'C0lezmSRIv' 
                    AND r_fd3d20.web_street_name ILIKE 'SRI' 
                ORDER BY
                    1 DESC NULLS LAST, 2 DESC NULLS FIRST) AS r_0214c0 
            WHERE
                r_6f1c19.te_159c2f != r_0214c0.te_4bc8ba 
            ORDER BY
                1) 
                OR r_177945.wp_autogen_flag NOT ILIKE 'ulstn' 
                AND r_177945.wp_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
            ORDER BY
                1 ASC NULLS LAST;
----------->
SELECT
    r_aede08.web_street_type as te_4bd67b,
    r_aede08.web_rec_end_date as te_aeabb5,
    r_86d603.c_birth_year as te_4490d9 
FROM
    (SELECT
        * 
    FROM
        db1.customer PIVOT(min(c_last_name) AS pa_b68acb FOR c_first_name IN (('y7IG7ir98') AS pf_a98799,
        ('6kg7x2') AS pf_6aa730,
        ('Gv7RX') AS pf_9e6c0a,
        ('X4QHZgGI') AS pf_bf910e))) AS r_86d603 
LEFT JOIN
    db1.income_band AS r_cf2369 
        ON r_86d603.c_customer_sk <= r_cf2369.ib_upper_bound,
    db1.web_site AS r_aede08 
WHERE
    r_86d603.c_preferred_cust_flag LIKE r_aede08.web_site_id 
    AND r_86d603.pf_9e6c0a LIKE 'vXUul' 
    AND NOT r_aede08.web_rec_start_date < DATE'2024-10-11' 
    AND r_aede08.web_gmt_offset < 77.97531846 
    OR r_aede08.web_close_date_sk > 81 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_0d931e.c_last_name as te_e5212b,
    r_4214d8.ca_gmt_offset as te_ffd203 
FROM
    db1.customer AS r_0d931e,
    db1.customer_address AS r_4214d8 
WHERE
    r_0d931e.c_preferred_cust_flag NOT LIKE r_4214d8.ca_suite_number 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    'Uu' || r_2f27d8.r_reason_desc as te_8f43ab,
    bigint(double(r_ff8a57.sr_item_sk)) as te_dace89 
FROM
    db1.reason AS r_2f27d8,
    db1.store_returns AS r_ff8a57 
WHERE
    r_2f27d8.r_reason_sk >= r_ff8a57.sr_reason_sk 
    AND r_ff8a57.sr_return_tax != (
        (
            SELECT
                r_880de1.cc_tax_percentage as te_6a3879 
            FROM
                db1.call_center AS r_880de1,
                db1.ship_mode AS r_3ae094 
            RIGHT JOIN
                db1.customer_address AS r_406944 
                    ON r_3ae094.sm_ship_mode_sk >= r_406944.ca_address_sk 
            WHERE
                r_880de1.cc_call_center_id NOT ILIKE r_406944.ca_street_type 
            ORDER BY
                1 DESC NULLS FIRST 
            LIMIT 1
    )
) 
AND r_ff8a57.sr_return_time_sk > r_ff8a57.sr_store_sk ORDER BY
    1 ASC, 2 DESC NULLS LAST 
LIMIT 74;
----------->
SELECT
    r_9ec05c.d_week_seq as te_6cba20 
FROM
    db1.date_dim AS r_9ec05c 
RIGHT JOIN
    db1.warehouse AS r_40ecfa 
        ON r_9ec05c.d_current_year NOT ILIKE r_40ecfa.w_county 
WHERE
    r_40ecfa.w_warehouse_sk >= 29 
    AND r_40ecfa.w_zip LIKE r_40ecfa.w_warehouse_name 
    AND r_40ecfa.w_warehouse_sk = 51 
    AND r_9ec05c.d_following_holiday NOT ILIKE r_9ec05c.d_current_quarter 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    mod(r_4297d4.s_floor_space,
    11) / r_4297d4.s_gmt_offset as te_b9ce64 
FROM
    db1.store AS r_0e6508 
RIGHT JOIN
    db1.store AS r_4297d4 
        ON r_0e6508.s_market_manager ILIKE r_4297d4.s_county 
WHERE
    r_4297d4.s_street_number NOT LIKE 'rTUOV' 
    OR r_4297d4.s_gmt_offset = r_4297d4.s_tax_percentage 
    AND r_0e6508.s_floor_space > (
        (
            SELECT
                r_dcce25.i_manufact_id as te_317e51 
            FROM
                db1.customer_demographics AS r_cbf93f 
            LEFT JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.item PIVOT(max(i_item_id) AS pa_082ddb FOR i_brand_id IN ((70) AS pf_bfcfed,
                        (89) AS pf_06b095,
                        (68) AS pf_82baa7,
                        (56) AS pf_40c67c,
                        (92) AS pf_0486a4))
                ) AS r_dcce25 
                    ON r_cbf93f.cd_gender NOT LIKE r_dcce25.i_product_name 
            WHERE
                r_dcce25.i_current_price = r_dcce25.i_wholesale_cost 
                AND r_dcce25.i_manager_id >= 85 
                AND r_cbf93f.cd_demo_sk > 65 
            ORDER BY
                1 NULLS LAST 
            LIMIT 1)
    ) 
    OR r_0e6508.s_hours ILIKE r_4297d4.s_zip ORDER BY
        1 DESC NULLS LAST;
----------->
SELECT
    r_3479ff.cc_rec_end_date as te_f8a4b6,
    r_04a9f9.ca_address_id as te_195024,
    r_3479ff.cc_rec_end_date as te_b3deae 
FROM
    db1.call_center AS r_3479ff,
    db1.customer_address AS r_04a9f9 
INNER JOIN
    (
        SELECT
            r_921fff.sm_ship_mode_sk as te_6c2b19 
        FROM
            db1.ship_mode AS r_921fff 
        WHERE
            r_921fff.sm_contract LIKE r_921fff.sm_ship_mode_id 
        ORDER BY
            1 ASC
    ) AS r_c89b37 
        ON r_04a9f9.ca_address_sk >= r_c89b37.te_6c2b19 
WHERE
    r_3479ff.cc_open_date_sk >= r_c89b37.te_6c2b19 
    AND r_3479ff.cc_sq_ft = r_04a9f9.ca_address_sk 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_3856c8.hd_dep_count as te_886bb9 
FROM
    db1.household_demographics AS r_3856c8 
INNER JOIN
    (
        SELECT
            r_0661b3.cc_rec_end_date as te_9f7fab,
            r_0661b3.cc_county as te_e9ec42 
        FROM
            db1.store_returns AS r_4ff118 
        INNER JOIN
            db1.store AS r_8fb20a 
                ON r_4ff118.sr_return_ship_cost = r_8fb20a.s_number_employees,
            db1.call_center AS r_0661b3 
        INNER JOIN
            db1.customer AS r_d05571 
                ON r_0661b3.cc_closed_date_sk = r_d05571.c_last_review_date_sk 
        WHERE
            r_8fb20a.s_store_id > r_0661b3.cc_call_center_id 
            AND r_0661b3.cc_company_name NOT ILIKE r_8fb20a.s_street_type 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC
    ) AS r_0c5437 
        ON r_3856c8.hd_buy_potential NOT LIKE r_0c5437.te_e9ec42 
WHERE
    r_3856c8.hd_buy_potential NOT LIKE 'U' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_401498.web_state as te_d9d7e3 
FROM
    db1.web_site AS r_401498 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.web_site PIVOT(count(web_mkt_class) AS pa_1cd9e8 FOR web_mkt_desc IN (('v95G6s') AS pf_df4f57,
            ('px6kg7x2SW') AS pf_c6531c,
            ('v') AS pf_88e6e5,
            ('D') AS pf_fece82))
    ) AS r_466d31 
        ON r_401498.web_gmt_offset < r_466d31.web_tax_percentage 
WHERE
    r_466d31.pf_fece82 < r_466d31.pf_88e6e5 
ORDER BY
    1 NULLS LAST 
LIMIT 51;
----------->
SELECT
    r_a483ef.d_date as te_cdd528,
    r_051dbc.cd_credit_rating as te_29ab5d,
    r_cd5deb.ca_gmt_offset as te_061967 
FROM
    db1.customer_demographics AS r_4b31e6 
INNER JOIN
    db1.customer_address AS r_cd5deb 
        ON r_4b31e6.cd_gender ILIKE r_cd5deb.ca_location_type,
    db1.date_dim AS r_a483ef 
LEFT JOIN
    db1.customer_demographics AS r_051dbc 
        ON r_a483ef.d_dow = r_051dbc.cd_dep_employed_count 
WHERE
    (
        NOT r_cd5deb.ca_address_sk <= r_a483ef.d_first_dom 
        AND r_a483ef.d_month_seq = 8 
        AND r_051dbc.cd_dep_count > r_a483ef.d_quarter_seq
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST 
OFFSET 59;
----------->
SELECT
    68.16113609 + r_f4f54d.wp_image_count as te_e53d0b,
    r_986c4a.upn_ac7772 as te_9e4fd0,
    r_39589f.r_reason_id as te_26cae5 
FROM
    db1.reason AS r_39589f 
INNER JOIN
    db1.income_band AS r_363191 
        ON r_39589f.r_reason_sk = r_363191.ib_upper_bound,
    (SELECT
        * 
    FROM
        db1.date_dim UNPIVOT EXCLUDE NULLS ((up_2b28f8,
        up_51a569,
        up_a1bc43) FOR upn_ac7772 IN ((d_same_day_lq,
        d_quarter_name,
        d_date) AS UPF_b6758b))) AS r_986c4a 
RIGHT JOIN
    db1.web_page AS r_f4f54d 
        ON r_986c4a.up_a1bc43 >= r_f4f54d.wp_rec_end_date 
WHERE
    r_39589f.r_reason_id ILIKE r_986c4a.d_following_holiday 
    AND r_986c4a.d_fy_quarter_seq <= 15 
    AND r_986c4a.d_current_quarter NOT LIKE 'zmS' 
    OR r_986c4a.d_date_sk < 20 
    OR r_39589f.r_reason_sk > 30 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_22839d.web_state as te_2c718b,
    r_22839d.web_street_number as te_72cdcf,
    r_22839d.web_rec_end_date as te_64ebdc 
FROM
    db1.customer AS r_4059df 
RIGHT JOIN
    db1.customer AS r_f62646 
        ON r_4059df.c_first_sales_date_sk != r_f62646.c_current_addr_sk,
    (SELECT
        r_6af189.wp_char_count as te_b0f2e4,
        r_1c9596.web_name as te_e386d4 
    FROM
        db1.web_page AS r_6af189 
    RIGHT JOIN
        db1.web_site AS r_1c9596 
            ON r_6af189.wp_link_count != r_1c9596.web_company_id,
        db1.date_dim AS r_4a0113 
    RIGHT JOIN
        (
            SELECT
                r_333ee4.d_date_sk as te_5273b5,
                r_333ee4.d_dow as te_cd30e8 
            FROM
                db1.store AS r_1b205f 
            INNER JOIN
                db1.ship_mode AS r_d6079e 
                    ON r_1b205f.s_closed_date_sk >= r_d6079e.sm_ship_mode_sk,
                db1.date_dim AS r_333ee4 
            RIGHT JOIN
                db1.call_center AS r_e443b0 
                    ON r_333ee4.d_year != r_e443b0.cc_division 
            WHERE
                r_1b205f.s_store_id NOT ILIKE r_333ee4.d_holiday 
            ORDER BY
                1 ASC NULLS LAST, 2 NULLS LAST
        ) AS r_961404 
            ON r_4a0113.d_month_seq >= r_961404.te_cd30e8 
    WHERE
        r_6af189.wp_rec_end_date > r_4a0113.d_date 
    ORDER BY
        1 DESC, 2 ASC NULLS LAST) AS r_249824 
    LEFT JOIN
        db1.web_site AS r_22839d 
            ON r_249824.te_e386d4 ILIKE r_22839d.web_street_number 
    WHERE
        r_f62646.c_salutation NOT ILIKE r_249824.te_e386d4 
        OR r_22839d.web_rec_start_date < r_22839d.web_rec_end_date 
        OR r_f62646.c_customer_id NOT LIKE 'ir9' 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    string(avg(r_56e090.sr_return_quantity)) as te_68f08c 
FROM
    db1.web_site AS r_d2537f 
LEFT JOIN
    db1.store_returns AS r_56e090 
        ON r_d2537f.web_site_sk != r_56e090.sr_return_ship_cost 
WHERE
    r_56e090.sr_addr_sk IN (
        SELECT
            r_f757e2.cc_company as te_eac2c3 
        FROM
            db1.call_center AS r_f757e2 
        RIGHT JOIN
            db1.income_band AS r_a2916a 
                ON r_f757e2.cc_division != r_a2916a.ib_income_band_sk 
        WHERE
            r_f757e2.cc_tax_percentage < r_f757e2.cc_gmt_offset 
            OR r_f757e2.cc_call_center_sk = 37 
            AND r_f757e2.cc_manager LIKE 'OVbBRG' 
        ORDER BY
            1 DESC NULLS LAST
    ) 
    OR r_d2537f.web_mkt_id > r_56e090.sr_addr_sk 
ORDER BY
    1 ASC;
----------->
SELECT
    r_72fc2b.s_rec_end_date as te_61d0d2,
    r_72fc2b.s_country || r_72fc2b.s_store_id as te_0620f6,
    r_72fc2b.s_rec_end_date as te_e97980 
FROM
    db1.store AS r_72fc2b,
    db1.household_demographics AS r_288ada 
WHERE
    r_72fc2b.s_street_number ILIKE r_288ada.hd_buy_potential 
ORDER BY
    1 DESC NULLS FIRST, 2, 3 DESC NULLS LAST;
----------->
SELECT
    char_length(r_aa7ae8.s_county) as te_adcce0,
    date_from_unix_date(84 * r_aa7ae8.s_store_sk) as te_e3b51a,
    pi() as te_0c0778 
FROM
    db1.store AS r_aa7ae8 
RIGHT JOIN
    db1.warehouse AS r_83d79e 
        ON r_aa7ae8.s_company_id < r_83d79e.w_gmt_offset,
    db1.customer AS r_8326b0 
LEFT JOIN
    db1.customer AS r_128521 
        ON r_8326b0.c_first_name NOT LIKE r_128521.c_first_name 
WHERE
    r_83d79e.w_street_type LIKE r_128521.c_first_name 
    OR r_aa7ae8.s_tax_percentage != r_83d79e.w_gmt_offset 
    AND r_128521.c_last_name LIKE 'tb2Gv7RXpR' 
GROUP BY
    3, 2, 1 
ORDER BY
    1 DESC, 2 DESC, 3 DESC NULLS LAST;
----------->
(
    SELECT
        add_months(r_85126c.wp_rec_end_date,
        r_3bb02c.wp_access_date_sk) as te_63e54d,
        make_timestamp(r_041d0e.c_first_shipto_date_sk,
        r_85126c.wp_link_count,
        r_041d0e.c_current_cdemo_sk,
        r_85126c.wp_char_count,
        r_041d0e.c_first_sales_date_sk,
        r_3bb02c.wp_image_count) as te_2b6386,
        r_92a2f8.ca_gmt_offset as te_8c2ad5 
    FROM
        db1.customer_address AS r_92a2f8 
    INNER JOIN
        db1.web_page AS r_3bb02c 
            ON r_92a2f8.ca_suite_number NOT LIKE r_3bb02c.wp_autogen_flag,
        db1.customer AS r_041d0e 
    INNER JOIN
        db1.web_page AS r_85126c 
            ON r_85126c.wp_rec_start_date BETWEEN r_85126c.wp_rec_end_date AND DATE'2024-10-11' 
    WHERE
        r_92a2f8.ca_location_type NOT ILIKE r_041d0e.c_preferred_cust_flag 
        OR r_3bb02c.wp_rec_start_date != DATE'2024-10-11' 
    ORDER BY
        1 DESC, 2 ASC NULLS LAST, 3 NULLS FIRST
) MINUS  (SELECT
    current_date() as te_2eb5a6, make_timestamp(r_1d62e9.sr_hdemo_sk, r_1d62e9.sr_customer_sk, r_1d62e9.sr_hdemo_sk, r_9cb864.wp_creation_date_sk, r_1d62e9.sr_hdemo_sk, r_1d62e9.web_county) as te_50e6ef, r_1d62e9.sr_reversed_charge as te_2922f8 
FROM
    db1.web_page AS r_9cb864 
INNER JOIN
    db1.store_returns AS r_1d62e9 
        ON r_9cb864.wp_creation_date_sk <= r_1d62e9.sr_cdemo_sk 
WHERE
    r_1d62e9.web_county >= r_1d62e9.sr_return_ship_cost 
    AND (NOT r_9cb864.wp_web_page_sk != 88 
    AND r_9cb864.wp_rec_end_date = DATE'2024-03-25') 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC, 3 NULLS FIRST) 
ORDER BY
1 ASC NULLS LAST, 2, 3 DESC 
OFFSET 19;
----------->
SELECT
    r_ce2823.web_site_id as te_f6f45b,
    r_a1cab8.c_email_address as te_88c951 
FROM
    (SELECT
        * 
    FROM
        db1.store_returns PIVOT(stddev(sr_addr_sk) AS pa_4d72d7 FOR sr_ticket_number IN ((67) AS pf_a8ceef,
        (77) AS pf_01773b,
        (96) AS pf_565df2,
        (61) AS pf_b41be6))) AS r_b21fce 
RIGHT JOIN
    db1.customer AS r_a1960a 
        ON r_b21fce.sr_reason_sk != r_a1960a.c_current_cdemo_sk,
    db1.customer AS r_a1cab8 
INNER JOIN
    db1.web_site AS r_ce2823 
        ON r_a1cab8.c_login LIKE r_ce2823.web_street_number 
WHERE
    r_a1960a.c_salutation NOT LIKE r_ce2823.web_mkt_class 
    AND r_a1cab8.c_birth_month > 97 
    AND r_ce2823.web_city NOT ILIKE 'CMaL' 
    AND r_a1960a.c_current_addr_sk >= 8 
    OR r_b21fce.pf_a8ceef < 33.64795441D 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST 
LIMIT 16;
----------->
SELECT
    hash(r_766e03.ca_zip,
    false) as te_33f5a9,
    79 - r_5215f7.cd_dep_college_count as te_5a6104 
FROM
    db1.reason AS r_f624d1 
RIGHT JOIN
    db1.customer_address AS r_766e03 
        ON r_f624d1.r_reason_desc ILIKE r_766e03.ca_street_name,
    db1.customer_demographics AS r_5215f7 
WHERE
    r_766e03.ca_country > r_5215f7.cd_marital_status 
    AND r_766e03.ca_gmt_offset > 43.44065036 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_f07ab4.sr_store_credit as te_7faa73 
FROM
    db1.store_returns AS r_f07ab4 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_efc4d3.wp_link_count as te_abf8d2 
FROM
    db1.web_page AS r_efc4d3 
LEFT JOIN
    (
        SELECT
            date_from_unix_date(r_00c459.c_birth_year) as te_7182bd 
        FROM
            db1.customer AS r_00c459 
        WHERE
            r_00c459.c_birth_day != 41 
            AND r_00c459.c_login NOT LIKE r_00c459.c_first_name 
            OR r_00c459.c_login NOT LIKE 'S' 
        ORDER BY
            1 ASC NULLS LAST
    ) AS r_ddb717 
        ON r_efc4d3.wp_rec_end_date < r_ddb717.te_7182bd 
ORDER BY
    1;
----------->
SELECT
    r_7ca7a8.wp_creation_date_sk as te_91e538 
FROM
    db1.web_page AS r_7ca7a8 
WHERE
    r_7ca7a8.wp_link_count = 56 
    AND r_7ca7a8.wp_access_date_sk >= 36 
    AND r_7ca7a8.wp_rec_start_date <= DATE'2024-10-11' 
    AND r_7ca7a8.wp_rec_start_date >= DATE'2024-03-26' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_266df4.up_5b396e as te_fd827d,
    r_2542cc.cd_dep_employed_count as te_5f248c,
    r_266df4.i_manager_id as te_706433 
FROM
    db1.store_returns AS r_5d2f7c 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.item UNPIVOT INCLUDE NULLS ((up_f5b0d7,
            up_c6485c,
            up_6ac21e,
            up_4f4fb5,
            up_5b396e) FOR upn_607e61 IN ((i_current_price,
            i_color,
            i_item_id,
            i_category_id,
            i_rec_end_date) AS UPF_b29cd0))
    ) AS r_266df4 
        ON r_5d2f7c.sr_reason_sk > r_266df4.i_manager_id,
    (SELECT
        r_bba71e.wp_type as te_44d0c1,
        r_4a0171.wp_web_page_id as te_8f6616 
    FROM
        db1.web_page AS r_bba71e,
        db1.web_page AS r_4a0171 
    WHERE
        r_bba71e.wp_image_count = r_4a0171.wp_max_ad_count 
        AND r_4a0171.wp_image_count != r_4a0171.wp_char_count 
        AND r_bba71e.wp_autogen_flag NOT LIKE '4QH' 
        OR r_bba71e.wp_rec_start_date BETWEEN DATE'2024-03-26' AND r_4a0171.wp_rec_end_date 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST 
    LIMIT 51) AS r_3efc04 LEFT JOIN
    db1.customer_demographics AS r_2542cc 
        ON r_3efc04.te_44d0c1 NOT ILIKE r_2542cc.cd_education_status 
WHERE
    r_266df4.up_4f4fb5 != r_2542cc.cd_dep_employed_count 
    AND r_3efc04.te_44d0c1 NOT LIKE r_266df4.i_product_name 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST, 3 DESC 
LIMIT 12;
----------->
SELECT
    r_70a54c.ca_location_type as te_af3325,
    r_b7f13a.c_first_shipto_date_sk as te_090cec 
FROM
    db1.date_dim AS r_8fe8fd 
LEFT JOIN
    db1.customer AS r_b7f13a 
        ON r_8fe8fd.d_current_week LIKE r_b7f13a.c_login,
    db1.customer_address AS r_70a54c 
WHERE
    r_b7f13a.c_preferred_cust_flag NOT LIKE r_70a54c.ca_street_number 
ORDER BY
    1 DESC, 2 DESC NULLS FIRST 
LIMIT 22;
----------->
SELECT
    r_5d6171.cc_call_center_id as te_b143b6 
FROM
    db1.call_center AS r_5d6171 
ORDER BY
    1 ASC NULLS FIRST 
OFFSET 27;
----------->
SELECT
    date_from_unix_date(r_c38868.wp_char_count) as te_9d9fca,
    r_c38868.wp_web_page_id as te_7f16c4,
    current_timestamp() as te_1db8c2 
FROM
    db1.customer AS r_73a2cf 
RIGHT JOIN
    db1.item AS r_c9d34c 
        ON r_73a2cf.c_first_sales_date_sk = r_c9d34c.i_manager_id,
    db1.store AS r_5b589c 
RIGHT JOIN
    db1.web_page AS r_c38868 
        ON r_5b589c.s_floor_space = r_c38868.wp_link_count 
WHERE
    r_c9d34c.i_category LIKE r_5b589c.s_geography_class 
    OR r_73a2cf.c_customer_sk < 17 
    AND r_5b589c.s_division_id > r_c38868.wp_link_count 
    AND r_73a2cf.c_last_name LIKE r_c9d34c.i_category 
    AND r_c38868.wp_autogen_flag > r_c38868.wp_web_page_id 
ORDER BY
    1 ASC, 2 DESC NULLS LAST, 3 ASC;
----------->
SELECT
    r_141524.i_wholesale_cost as te_14cc9a,
    hash(r_141524.i_current_price,
    now()) as te_79544f,
    mod(r_1e9fe7.cd_purchase_estimate,
    44) as te_0a77f7 
FROM
    db1.item AS r_141524,
    db1.customer_demographics AS r_1e9fe7 
WHERE
    r_141524.i_item_id NOT LIKE r_1e9fe7.cd_marital_status 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    hash(r_f6c1c8.te_261c14,
    r_f6c1c8.te_683aa7) as te_bb2d6a 
FROM
    (SELECT
        r_f593a5.web_gmt_offset as te_bbf105,
        current_timestamp() as te_683aa7,
        r_f593a5.web_mkt_id as te_261c14 
    FROM
        db1.web_site AS r_f593a5,
        db1.customer_address AS r_6ce62c 
    WHERE
        r_f593a5.web_tax_percentage >= r_6ce62c.ca_gmt_offset 
        OR r_f593a5.web_rec_start_date = r_f593a5.web_rec_end_date 
        AND r_6ce62c.ca_street_number NOT ILIKE 'kg7' 
        AND r_6ce62c.ca_state NOT LIKE r_6ce62c.ca_zip 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC, 3 NULLS FIRST) AS r_f6c1c8 
WHERE
    r_f6c1c8.te_bbf105 != 56.00660591 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    mod(r_880966.hd_vehicle_count,
    68) as te_f19c76 
FROM
    db1.household_demographics AS r_880966 
WHERE
    r_880966.hd_demo_sk > 91 
    AND r_880966.hd_buy_potential ILIKE 'R' 
    OR r_880966.hd_income_band_sk = 83 
    OR r_880966.hd_income_band_sk != r_880966.hd_vehicle_count 
ORDER BY
    1 DESC;
----------->
WITH CTE_15dcff(te_9acdf3, te_f63f64) AS (SELECT
    e() as te_9acdf3,
    r_d33ac2.ib_upper_bound as te_f63f64 
FROM
    db1.income_band AS r_f85c38,
    db1.income_band AS r_d33ac2 
WHERE
    r_f85c38.ib_upper_bound = r_d33ac2.ib_lower_bound 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS FIRST), CTE_4444ab(te_327785, te_a29f2a, te_e9a381) AS (SELECT
    r_0610c5.c_customer_id as te_327785, r_518e0c.ca_gmt_offset as te_a29f2a, r_518e0c.ca_gmt_offset as te_e9a381 
FROM
    db1.customer_address AS r_518e0c 
LEFT JOIN
    (SELECT
        chr(r_5d3c83.w_gmt_offset) as te_a41495 
    FROM
        db1.warehouse AS r_5d3c83 
    LEFT JOIN
        db1.household_demographics AS r_d887fd 
            ON r_5d3c83.w_city < r_d887fd.hd_buy_potential) AS r_b0adc4 
            ON r_518e0c.ca_location_type NOT ILIKE r_b0adc4.te_a41495,
        db1.customer AS r_0610c5 
    WHERE
        r_518e0c.ca_zip ILIKE r_0610c5.c_customer_id 
    ORDER BY
        1 DESC NULLS FIRST, 2 ASC NULLS LAST, 3 NULLS LAST) SELECT
            r_9d24ef.cd_dep_count as te_7d3dc5, (r_ac46c9.te_f63f64 - 56.29967013) / bigint(r_ac46c9.te_9acdf3) - r_9d24ef.cd_dep_count * 91 as te_5df690, r_9d24ef.cd_purchase_estimate as te_085c30 
        FROM
            CTE_15dcff AS r_ac46c9 
        RIGHT JOIN
            (
                SELECT
                    r_2e9a94.sr_addr_sk as te_3b3276 
                FROM
                    db1.store_returns AS r_2e9a94 
                INNER JOIN
                    db1.customer_address AS r_567086 
                        ON r_2e9a94.sr_returned_date_sk < r_567086.ca_address_sk 
                WHERE
                    r_567086.ca_country NOT LIKE r_567086.ca_county 
                    OR r_2e9a94.sr_reversed_charge > r_2e9a94.sr_fee 
                    OR r_567086.ca_suite_number ILIKE r_567086.ca_street_number 
                    AND r_2e9a94.sr_return_quantity > 95 
                ORDER BY
                    1
            ) AS r_9c9922 
                ON r_ac46c9.te_f63f64 <= r_9c9922.te_3b3276,
            db1.customer_demographics AS r_9d24ef 
        WHERE
            r_9c9922.te_3b3276 <= r_9d24ef.cd_dep_count 
            OR r_ac46c9.te_9acdf3 != 93.58657262D 
            AND r_9d24ef.cd_purchase_estimate >= r_ac46c9.te_f63f64 
            OR r_9d24ef.cd_dep_employed_count = 39 
            OR r_9d24ef.cd_gender NOT ILIKE 'BR' 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 NULLS FIRST;
----------->
SELECT
    r_e6a5fd.te_6d64e8 as te_d8f44f,
    r_e6a5fd.te_6d64e8 as te_32ffdb 
FROM
    db1.reason AS r_239956 
RIGHT JOIN
    db1.time_dim AS r_0e30c0 
        ON r_239956.r_reason_sk > r_0e30c0.t_minute,
    (SELECT
        16.4772951 * r_ea0f2a.cd_dep_college_count as te_ee2517,
        r_ea0f2a.cd_credit_rating as te_9559dd,
        r_1b5ad5.pf_fa512c - 77.59898014D as te_6d64e8 
    FROM
        (SELECT
            * 
        FROM
            (SELECT
                r_cd9975.w_street_name as te_a7ba38,
                r_cd9975.w_gmt_offset as te_1b7553 
            FROM
                db1.reason AS r_8c4072,
                db1.warehouse AS r_cd9975 
            WHERE
                r_8c4072.r_reason_sk = r_cd9975.w_gmt_offset 
                OR r_cd9975.w_warehouse_sq_ft = 79 
                OR r_8c4072.r_reason_sk < 16 
                AND r_cd9975.w_state ILIKE r_cd9975.w_country 
            ORDER BY
                1 NULLS LAST, 2 ASC NULLS LAST) PIVOT(count(te_a7ba38) AS pa_e47b16 FOR te_1b7553 IN ((90.53461017) AS pf_aaa706, (51.59437376) AS pf_e28e68, (63.71334422) AS pf_fa512c, (33.76880163) AS pf_747d58))) AS r_1b5ad5, db1.reason AS r_d99315 
        INNER JOIN
            db1.customer_demographics AS r_ea0f2a 
                ON r_d99315.r_reason_sk != r_ea0f2a.cd_dep_employed_count 
        WHERE
            chr(r_1b5ad5.pf_fa512c) LIKE r_ea0f2a.cd_gender 
            AND r_ea0f2a.cd_dep_count != 65 
            AND r_1b5ad5.pf_aaa706 < 21 
        ORDER BY
            1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST) AS r_e6a5fd 
    WHERE
        r_0e30c0.t_second != r_e6a5fd.te_ee2517 
        AND r_0e30c0.t_am_pm ILIKE '95G6sUgDDX' 
        AND r_0e30c0.t_shift ILIKE '95G6sUgDD' 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    overlay(r_6b70cf.wp_url PLACING r_6b70cf.wp_access_date_sk 
FROM
    r_6b70cf.wp_image_count) as te_43b36b 
FROM
    db1.reason AS r_b05bb8 
LEFT JOIN
    db1.web_page AS r_6b70cf 
        ON r_b05bb8.r_reason_sk <= r_6b70cf.wp_image_count 
WHERE
    r_6b70cf.wp_customer_sk > 46 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_b428d3.web_open_date_sk as te_24f526 
FROM
    db1.web_site AS r_b428d3 
INNER JOIN
    db1.customer_demographics AS r_a26fad 
        ON r_b428d3.web_open_date_sk > r_a26fad.cd_dep_count 
WHERE
    r_a26fad.cd_demo_sk >= r_a26fad.cd_dep_college_count 
    AND r_b428d3.web_manager NOT LIKE 'gDDXX4' 
    AND r_a26fad.cd_dep_college_count != r_a26fad.cd_purchase_estimate 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_6d342c.c_last_review_date_sk as te_532ce7,
    r_6d342c.c_last_name as te_186970 
FROM
    db1.customer AS r_6d342c,
    db1.store_returns AS r_25e0b0 
WHERE
    r_6d342c.c_birth_month < r_25e0b0.sr_ticket_number 
    AND NOT r_25e0b0.sr_return_amt >= r_25e0b0.web_county 
    OR r_25e0b0.sr_return_amt_inc_tax = r_25e0b0.sr_store_credit 
    OR NOT r_25e0b0.sr_return_quantity = 25 
    AND r_6d342c.c_last_review_date_sk IS NULL 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_74bd5c.c_preferred_cust_flag as te_6dd7e4,
    r_047029.hd_income_band_sk as te_dc691e,
    r_74bd5c.c_customer_sk as te_8d3c7b 
FROM
    db1.household_demographics AS r_047029,
    db1.customer AS r_74bd5c 
WHERE
    r_047029.hd_buy_potential < r_74bd5c.c_login 
    OR r_047029.hd_buy_potential LIKE '4elBy7I' 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS FIRST, 3 DESC;
----------->
SELECT
    r_d16c7c.t_am_pm as te_ebca18,
    r_3929f1.wp_autogen_flag as te_df77f6,
    r_4b4049.t_minute as te_4fbe19 
FROM
    db1.time_dim AS r_4b4049,
    db1.time_dim AS r_d16c7c 
INNER JOIN
    db1.web_page AS r_3929f1 
        ON r_d16c7c.t_am_pm NOT ILIKE r_3929f1.wp_url 
WHERE
    r_4b4049.t_time_sk > r_3929f1.wp_web_page_sk 
    OR r_3929f1.wp_customer_sk <= 75 
    AND r_4b4049.t_shift NOT ILIKE r_d16c7c.t_shift 
    AND r_3929f1.wp_link_count != 20 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_065c07.sr_customer_sk as te_686e25,
    chr(r_065c07.sr_hdemo_sk) as te_24cfd9 
FROM
    db1.ship_mode AS r_640ea6 
INNER JOIN
    db1.item AS r_2b9942 
        ON r_640ea6.sm_ship_mode_sk > r_2b9942.i_category_id,
    db1.store_returns AS r_065c07 
LEFT JOIN
    db1.call_center AS r_0cbdeb 
        ON r_065c07.sr_refunded_cash != r_0cbdeb.cc_tax_percentage 
WHERE
    r_2b9942.i_units NOT LIKE r_0cbdeb.cc_country 
    OR r_2b9942.i_category > r_0cbdeb.cc_street_type 
    AND r_0cbdeb.cc_city LIKE '2SW4P' 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
WITH CTE_9fc74f(te_f21bb1, te_ce53e0) AS (SELECT
    r_4bc349.ca_gmt_offset as te_f21bb1,
    r_54044a.ib_income_band_sk as te_ce53e0 
FROM
    db1.income_band AS r_54044a 
LEFT JOIN
    db1.customer_address AS r_4bc349 
        ON r_54044a.ib_income_band_sk < r_4bc349.ca_address_sk,
    db1.reason AS r_6e83f2 
WHERE
    r_4bc349.ca_street_name LIKE r_6e83f2.r_reason_desc 
    AND r_4bc349.ca_county ILIKE r_6e83f2.r_reason_desc 
    OR r_54044a.ib_income_band_sk != 4 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS FIRST) SELECT
    r_e3a55a.te_f21bb1 as te_01d88c 
FROM
    (SELECT
        try_add(r_f367bf.ib_upper_bound,
        DATE'2024-10-11') as te_258936 
    FROM
        db1.reason AS r_ec5335 
    LEFT JOIN
        db1.income_band AS r_f367bf 
            ON r_ec5335.r_reason_sk != r_f367bf.ib_income_band_sk 
    WHERE
        (
            NOT r_ec5335.r_reason_id NOT LIKE r_ec5335.r_reason_desc 
            OR r_ec5335.r_reason_desc ILIKE 'v7R' 
            OR r_f367bf.ib_income_band_sk != r_f367bf.ib_upper_bound
        ) 
    ORDER BY
        1 ASC NULLS FIRST) AS r_18f4c8 
INNER JOIN
    CTE_9fc74f AS r_e3a55a 
        ON date(timestamp(r_18f4c8.te_258936)) != date_from_unix_date(length(hex(r_e3a55a.te_f21bb1))) 
WHERE
    date_from_unix_date(hash(chr(cos(r_e3a55a.te_f21bb1)))) <= date_from_unix_date(year(r_18f4c8.te_258936)) 
    AND date_from_unix_date(bigint(double(r_e3a55a.te_f21bb1))) = date(timestamp(r_18f4c8.te_258936)) 
    OR r_18f4c8.te_258936 != DATE'2024-10-11' 
    AND date_from_unix_date(ascii(string(chr(r_e3a55a.te_f21bb1)))) >= date(timestamp(r_18f4c8.te_258936)) 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_19789e(te_d8fa8c, te_8a194d, te_99048b) AS (SELECT
    r_7c86f8.web_street_number as te_d8fa8c,
    r_631663.cc_call_center_id as te_8a194d,
    r_631663.cc_zip as te_99048b 
FROM
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(min(sm_contract) AS pa_6f9ccb FOR sm_carrier IN (('Gk4elBy') AS pf_4ae30a,
        ('m') AS pf_a7c16b,
        ('U') AS pf_d401ba,
        ('6') AS pf_d9fd1d,
        ('stntb2') AS pf_b13e03,
        ('sU') AS pf_1972fe))) AS r_0d64bb,
    db1.web_site AS r_7c86f8 
RIGHT JOIN
    db1.call_center AS r_631663 
        ON r_7c86f8.web_manager < r_631663.cc_name 
WHERE
    r_0d64bb.pf_1972fe LIKE r_7c86f8.web_mkt_class 
    OR r_631663.cc_mkt_id < 67 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC, 3 DESC NULLS LAST 
OFFSET 7) SELECT
    r_cf5884.w_warehouse_sk as te_360a0d FROM
        CTE_19789e AS r_261390 
    RIGHT JOIN
        db1.warehouse AS r_cf5884 
            ON r_261390.te_99048b NOT ILIKE r_cf5884.w_zip 
    WHERE
        r_261390.te_99048b ILIKE 'D' 
        AND r_261390.te_8a194d IN (
            WITH CTE_317162(te_81f33a) AS (SELECT
                r_10b496.t_hour as te_81f33a 
            FROM
                db1.household_demographics AS r_30917f 
            RIGHT JOIN
                db1.time_dim AS r_10b496 
                    ON r_30917f.hd_buy_potential NOT LIKE r_10b496.t_meal_time 
            WHERE
                r_10b496.t_time_id LIKE 'IvXUuls' 
                OR r_30917f.hd_income_band_sk >= r_10b496.t_time 
            GROUP BY
                1 
            ORDER BY
                1 ASC NULLS LAST), CTE_29ebe6(te_1ce87f) AS (SELECT
                current_timestamp() as te_1ce87f 
            FROM
                (SELECT
                    * 
                FROM
                    db1.customer_address PIVOT(min(ca_street_name) AS pa_b78117 FOR (ca_gmt_offset,
                    ca_state) IN (((56.4041947,
                    'XUuls')) AS pf_3d32ad,
                    ((78.67533691,
                    '0le')) AS pf_0134fa,
                    ((77.92245071,
                    'u')) AS pf_c59fee,
                    ((92.55671605,
                    'bB')) AS pf_2f0244,
                    ((18.82421533,
                    'G7ir9')) AS pf_d6afbb))) AS r_996666 
            WHERE
                r_996666.ca_street_number NOT LIKE 'e' 
                AND r_996666.ca_suite_number NOT ILIKE r_996666.ca_address_id 
                OR r_996666.ca_address_sk > 13 
                AND r_996666.ca_country < '8hv9' 
            ORDER BY
                1 DESC NULLS LAST 
            OFFSET 49) SELECT
                'UgDD' as te_056a73 FROM
                    CTE_317162 AS r_8af62c 
                WHERE
                    r_8af62c.te_81f33a BETWEEN 59 AND 59 
                    AND r_8af62c.te_81f33a > 77 
                ORDER BY
                    1 NULLS LAST 
                OFFSET 83) ORDER BY
                1 ASC;
----------->
SELECT
    date_from_unix_date(count(r_ecaead.w_county)) as te_49d04d,
    r_4baa40.ca_location_type as te_6c97a5,
    54 * r_ecaead.w_warehouse_sk as te_81cd8c 
FROM
    db1.customer_address AS r_4baa40 
INNER JOIN
    db1.warehouse AS r_ecaead 
        ON r_4baa40.ca_state NOT LIKE r_ecaead.w_warehouse_name,
    db1.customer_address AS r_0afd32 
RIGHT JOIN
    (
        SELECT
            r_ef0540.d_current_year as te_c2113c,
            r_bfa757.sr_reversed_charge as te_eb4ab2 
        FROM
            db1.date_dim AS r_ef0540,
            db1.ship_mode AS r_321f0b 
        RIGHT JOIN
            db1.store_returns AS r_bfa757 
                ON r_321f0b.sm_ship_mode_sk = r_bfa757.sr_cdemo_sk 
        WHERE
            r_ef0540.d_fy_quarter_seq <= r_bfa757.sr_reason_sk 
            OR r_bfa757.sr_refunded_cash > r_bfa757.sr_return_amt_inc_tax 
            OR r_bfa757.sr_return_quantity = 12 
        ORDER BY
            1 ASC NULLS FIRST, 2 NULLS LAST 
        OFFSET 98
) AS r_ec4b20 
    ON r_0afd32.ca_city NOT ILIKE r_ec4b20.te_c2113c WHERE
        r_ecaead.w_warehouse_sk <= r_0afd32.ca_address_sk 
        OR r_ec4b20.te_eb4ab2 <= r_4baa40.ca_gmt_offset 
        AND r_4baa40.ca_address_sk < r_ecaead.w_warehouse_sq_ft 
GROUP BY
    r_4baa40.ca_location_type, r_ecaead.w_warehouse_sk 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    timestamp(r_30037e.web_rec_start_date) as te_e4059d,
    r_30037e.web_street_type as te_2d9421 
FROM
    db1.customer AS r_c87d1d,
    db1.web_site AS r_30037e 
WHERE
    r_c87d1d.c_preferred_cust_flag NOT ILIKE r_30037e.web_site_id 
ORDER BY
    1, 2 DESC NULLS FIRST;
----------->
SELECT
    r_5627da.wp_url as te_616aee,
    r_0fa7f8.sr_net_loss + r_fd6c62.ib_lower_bound as te_8e02e4 
FROM
    db1.store_returns AS r_0fa7f8,
    db1.income_band AS r_fd6c62 
LEFT JOIN
    db1.web_page AS r_5627da 
        ON r_fd6c62.ib_income_band_sk != r_5627da.wp_creation_date_sk 
WHERE
    r_0fa7f8.sr_return_quantity != r_fd6c62.ib_upper_bound 
    OR r_5627da.wp_creation_date_sk >= 35 
    OR r_5627da.wp_type ILIKE r_5627da.wp_url 
    OR r_5627da.wp_rec_end_date > r_5627da.wp_rec_start_date 
    AND r_0fa7f8.sr_reversed_charge > r_0fa7f8.sr_store_credit 
ORDER BY
    1 DESC, 2 ASC;
----------->
SELECT
    r_952b61.web_street_name as te_16cc12,
    r_952b61.web_mkt_class as te_e2abb8,
    r_952b61.web_gmt_offset as te_794c34 
FROM
    db1.customer_demographics AS r_6fefe9 
INNER JOIN
    db1.web_site AS r_952b61 
        ON r_6fefe9.cd_dep_employed_count > r_952b61.web_company_id,
    db1.store AS r_f28460 
WHERE
    r_952b61.web_state ILIKE r_f28460.s_street_name 
    AND r_6fefe9.cd_marital_status ILIKE r_6fefe9.cd_credit_rating 
    AND r_f28460.s_store_id NOT LIKE r_6fefe9.cd_marital_status 
    OR r_952b61.web_site_sk = 71 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_7071b7.hd_vehicle_count as te_82adb3 
FROM
    db1.household_demographics AS r_7071b7 
ORDER BY
    1 NULLS LAST 
LIMIT 19;
----------->
SELECT
    r_25c28b.sr_customer_sk as te_cd5d15 
FROM
    db1.store_returns AS r_25c28b 
WHERE
    r_25c28b.sr_return_time_sk > 68 
    AND r_25c28b.sr_return_tax = r_25c28b.sr_reversed_charge 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 26;
----------->
SELECT
    r_9f346e.c_current_addr_sk as te_c3ca90,
    chr(r_5ec430.d_fy_quarter_seq) as te_211478,
    r_9f346e.c_last_review_date_sk as te_7c252b 
FROM
    (SELECT
        * 
    FROM
        db1.date_dim PIVOT(min(d_quarter_name) AS pa_ddcbb6,
        min(d_fy_week_seq) AS pa_9454c8 FOR d_current_day IN (('x') AS pf_29d66d,
        ('sUgD') AS pf_9578c3,
        ('G') AS pf_69fedf,
        ('Fr') AS pf_e8402a))) AS r_5ec430,
    (SELECT
        r_3d2ac7.ca_street_number as te_32fb0d,
        r_3d2ac7.ca_county as te_441345,
        r_cfdd4e.d_current_week as te_624ce0 
    FROM
        db1.date_dim AS r_cfdd4e 
    INNER JOIN
        db1.date_dim AS r_061185 
            ON r_cfdd4e.d_date <= r_061185.d_date,
        (SELECT
            * 
        FROM
            db1.customer_address UNPIVOT INCLUDE NULLS ((up_85501a,
            up_6282ef,
            up_ef8732,
            up_9521a2) FOR upn_36fc98 IN ((ca_gmt_offset,
            ca_street_name,
            ca_zip,
            ca_address_sk) AS UPF_08ca17))) AS r_3d2ac7 
    WHERE
        r_cfdd4e.d_date_sk = r_3d2ac7.up_9521a2 
        OR r_cfdd4e.d_date >= r_061185.d_date 
        OR r_061185.d_qoy != 61 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS LAST, 3 NULLS FIRST) AS r_1331b3 
    RIGHT JOIN
        db1.customer AS r_9f346e 
            ON r_1331b3.te_624ce0 NOT ILIKE r_9f346e.c_preferred_cust_flag 
    WHERE
        r_5ec430.d_date_id ILIKE r_1331b3.te_624ce0 
    ORDER BY
        1 ASC, 2 NULLS LAST, 3 DESC 
    LIMIT 94;
----------->
SELECT
    r_cc31b4.ca_address_sk as te_5e8069,
    r_2da211.s_gmt_offset as te_667816 
FROM
    db1.store AS r_2da211 
INNER JOIN
    db1.warehouse AS r_2e71e2 
        ON r_2da211.s_closed_date_sk >= r_2e71e2.w_warehouse_sq_ft,
    db1.customer_address AS r_cc31b4 
WHERE
    r_2da211.s_division_id = r_cc31b4.ca_address_sk 
    OR r_2e71e2.w_warehouse_name NOT ILIKE 'v7RXp' 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_ff80f6.t_hour as te_8397c3 
FROM
    db1.time_dim AS r_ff80f6 
WHERE
    r_ff80f6.t_meal_time NOT LIKE r_ff80f6.t_time_id 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_42c089.cd_demo_sk as te_9a6bde 
FROM
    db1.customer_demographics AS r_42c089 
RIGHT JOIN
    (
        SELECT
            r_cc1053.sr_return_amt as te_8bff62 
        FROM
            db1.store_returns AS r_cc1053 
        RIGHT JOIN
            db1.customer_demographics AS r_dfadd0 
                ON r_cc1053.sr_item_sk >= r_dfadd0.cd_dep_college_count 
        WHERE
            NOT r_dfadd0.cd_marital_status LIKE r_dfadd0.cd_credit_rating 
            OR r_cc1053.sr_return_time_sk = r_dfadd0.cd_dep_employed_count 
            AND r_cc1053.sr_item_sk != 38 
            OR r_cc1053.sr_net_loss = 14.65407468 
        ORDER BY
            1 ASC NULLS LAST
    ) AS r_1a8c5f 
        ON r_42c089.cd_education_status = chr(r_1a8c5f.te_8bff62) 
WHERE
    (
        NOT r_42c089.cd_credit_rating NOT ILIKE r_42c089.cd_marital_status 
        OR r_42c089.cd_dep_count <= 77
    ) 
ORDER BY
    1 NULLS FIRST;
----------->
WITH CTE_06ef24(te_09924c, te_efbdc9, te_56e5c5) AS (SELECT
    r_7b084c.t_hour as te_09924c,
    r_c3ff35.w_county as te_efbdc9,
    try_add(r_7a7c1f.cc_rec_end_date,
    r_7a7c1f.cc_sq_ft) as te_56e5c5 
FROM
    db1.warehouse AS r_c3ff35 
INNER JOIN
    db1.call_center AS r_7a7c1f 
        ON r_c3ff35.w_suite_number LIKE r_7a7c1f.cc_class,
    db1.ship_mode AS r_1b469f 
RIGHT JOIN
    db1.time_dim AS r_7b084c 
        ON r_1b469f.sm_ship_mode_sk > r_7b084c.t_minute 
WHERE
    r_7a7c1f.cc_open_date_sk = r_7b084c.t_hour 
    OR r_1b469f.sm_contract NOT LIKE r_7b084c.t_sub_shift), CTE_2bd980(te_b527a0) AS (SELECT
    r_a2762a.ca_address_sk as te_b527a0 
FROM
    db1.web_site AS r_f1bd15 
INNER JOIN
    db1.customer_address AS r_a2762a 
        ON r_f1bd15.web_gmt_offset >= r_a2762a.ca_gmt_offset 
ORDER BY
    1 ASC NULLS LAST) SELECT
    r_ef2f5b.te_56e5c5 as te_a7b523 
FROM
    CTE_06ef24 AS r_ef2f5b 
WHERE
    r_ef2f5b.te_efbdc9 ILIKE 'ir98' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_97a443.sm_ship_mode_sk as te_8d393b 
FROM
    db1.ship_mode AS r_97a443 
LEFT JOIN
    (
        WITH CTE_7d8e7f(te_9bbdbc, te_3d554f, te_337d2a) AS (SELECT
            chr(r_15be22.sr_store_sk) as te_9bbdbc,
            r_fb82c5.wp_rec_start_date as te_3d554f,
            r_fb82c5.wp_rec_end_date as te_337d2a 
        FROM
            db1.customer AS r_9c71a4 
        INNER JOIN
            db1.store_returns AS r_15be22 
                ON r_9c71a4.c_first_shipto_date_sk != r_15be22.sr_net_loss,
            db1.web_page AS r_fb82c5 
        RIGHT JOIN
            db1.store_returns AS r_eb228f 
                ON r_fb82c5.wp_image_count > r_eb228f.sr_return_quantity 
        WHERE
            r_15be22.sr_refunded_cash < r_eb228f.sr_return_tax 
            AND r_9c71a4.c_current_hdemo_sk = 75 
            OR r_eb228f.sr_store_sk = 86 
            AND r_eb228f.sr_item_sk = r_15be22.sr_return_time_sk 
            OR r_eb228f.sr_return_tax != r_eb228f.sr_fee 
        ORDER BY
            1 DESC, 2, 3 ASC NULLS LAST), CTE_f561c1(te_e60ca2, te_c17959) AS (SELECT
            hash(false) as te_e60ca2, stddev(r_3b4a3e.hd_demo_sk) as te_c17959 
        FROM
            db1.income_band AS r_5fa8cf,
            db1.household_demographics AS r_3b4a3e 
        WHERE
            r_5fa8cf.ib_upper_bound <= r_3b4a3e.hd_vehicle_count 
            AND r_5fa8cf.ib_income_band_sk != 0 
        GROUP BY
            r_3b4a3e.hd_demo_sk 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC) SELECT
            r_8afbc8.te_c17959 as te_d86553, current_timestamp() as te_e874e6, r_ac8a96.s_rec_start_date as te_81b968 
        FROM
            CTE_7d8e7f AS r_d7eebc 
        RIGHT JOIN
            CTE_f561c1 AS r_8afbc8 
                ON r_d7eebc.te_3d554f <= date_from_unix_date(r_8afbc8.te_e60ca2),
            db1.store AS r_ac8a96 
        RIGHT JOIN
            db1.customer AS r_3951b8 
                ON r_ac8a96.s_number_employees <= r_3951b8.c_first_shipto_date_sk 
        WHERE
            r_d7eebc.te_9bbdbc NOT ILIKE r_ac8a96.s_hours 
        ORDER BY
            1 DESC, 2 NULLS FIRST, 3
    ) AS r_13f3f0 
        ON r_97a443.sm_ship_mode_sk > r_13f3f0.te_d86553 
WHERE
    r_97a443.sm_carrier LIKE 'GC0lezm' 
    AND r_97a443.sm_carrier NOT LIKE r_97a443.sm_code 
    OR r_97a443.sm_type NOT ILIKE r_97a443.sm_carrier 
ORDER BY
    1 DESC;
----------->
SELECT
    r_8cc42c.c_customer_id as te_c305d6 
FROM
    db1.customer AS r_8cc42c 
WHERE
    r_8cc42c.c_first_name LIKE r_8cc42c.c_birth_country 
    AND r_8cc42c.c_birth_year != r_8cc42c.c_last_review_date_sk 
    OR r_8cc42c.c_customer_sk > 7 
    OR r_8cc42c.c_login NOT ILIKE r_8cc42c.c_last_name 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_1ad44a.w_gmt_offset as te_af4c34,
    r_7f81ac.ca_gmt_offset as te_b74fa8,
    r_1ad44a.w_warehouse_name as te_4cd3be 
FROM
    db1.income_band AS r_7b5c4b,
    db1.customer_address AS r_7f81ac 
INNER JOIN
    db1.warehouse AS r_1ad44a 
        ON r_7f81ac.ca_address_sk = r_1ad44a.w_gmt_offset 
WHERE
    r_7b5c4b.ib_income_band_sk < r_1ad44a.w_warehouse_sk 
    AND r_7f81ac.ca_zip ILIKE r_7f81ac.ca_suite_number 
    AND r_7f81ac.ca_county LIKE 'G7ir98hv95' 
    OR r_7f81ac.ca_street_number ILIKE r_7f81ac.ca_zip 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_74359b.cc_suite_number as te_892aeb,
    r_74359b.cc_gmt_offset as te_8c1f32 
FROM
    db1.income_band AS r_84491a,
    db1.call_center AS r_74359b 
INNER JOIN
    (
        WITH CTE_ad3f9e(te_828181) AS (SELECT
            r_23c618.cc_company_name as te_828181 
        FROM
            db1.time_dim AS r_2e2c0c 
        INNER JOIN
            db1.call_center AS r_23c618 
                ON r_2e2c0c.t_shift LIKE r_23c618.cc_county 
        WHERE
            r_23c618.cc_mkt_class LIKE r_23c618.cc_company_name 
        ORDER BY
            1 ASC NULLS LAST), CTE_9f1863(te_a748e5, te_681222) AS (SELECT
            r_5b2209.s_market_id as te_a748e5, r_5b2209.s_rec_end_date as te_681222 
        FROM
            db1.store AS r_5b2209 
        LEFT JOIN
            db1.customer_address AS r_615057 
                ON r_5b2209.s_store_sk < r_615057.ca_gmt_offset,
            CTE_ad3f9e AS r_9d6374 
        RIGHT JOIN
            CTE_ad3f9e AS r_dc9c36 
                ON r_9d6374.te_828181 NOT ILIKE r_dc9c36.te_828181 
        WHERE
            r_5b2209.s_manager NOT ILIKE r_9d6374.te_828181 
            AND r_5b2209.s_street_type NOT LIKE 'zz' 
        ORDER BY
            1 DESC, 2 DESC NULLS FIRST) SELECT
            r_14925a.te_a748e5 as te_138ba0, r_14925a.te_681222 as te_b5eb73 
        FROM
            CTE_ad3f9e AS r_50232b,
            CTE_9f1863 AS r_14925a 
        WHERE
            r_50232b.te_828181 ILIKE chr(r_14925a.te_a748e5) 
            AND date_from_unix_date(r_14925a.te_a748e5) >= r_14925a.te_681222 
        ORDER BY
            1 NULLS FIRST, 2 ASC NULLS LAST
    ) AS r_6c8994 
        ON r_74359b.cc_rec_end_date < r_6c8994.te_b5eb73 
WHERE
    (
        NOT r_84491a.ib_income_band_sk = r_74359b.cc_closed_date_sk 
        AND EXISTS (
            SELECT
                current_date() as te_e913fc,
                r_94e919.s_tax_percentage as te_036db9,
                r_94e919.s_gmt_offset as te_de4805 
            FROM
                db1.date_dim AS r_bda916 
            LEFT JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.customer_demographics PIVOT(count(cd_purchase_estimate) AS pa_c4c0e3 FOR cd_dep_employed_count IN ((66) AS pf_0be834,
                        (25) AS pf_1dfe66,
                        (53) AS pf_226359,
                        (31) AS pf_a652a3,
                        (41) AS pf_c57ee0,
                        (91) AS pf_07cf5a))
                ) AS r_f30db8 
                    ON r_bda916.d_current_month LIKE r_f30db8.cd_marital_status,
                db1.customer AS r_65da10 
            INNER JOIN
                db1.store AS r_94e919 
                    ON r_65da10.c_last_name NOT LIKE r_94e919.s_suite_number 
            WHERE
                r_bda916.d_qoy >= r_94e919.s_store_sk 
                AND r_94e919.s_company_name IS NOT NULL 
                AND r_bda916.d_quarter_seq <= 53 
            ORDER BY
                1 ASC NULLS FIRST, 2 DESC NULLS FIRST, 3 ASC NULLS LAST) 
                AND r_74359b.cc_name ILIKE 'CMaLQF' 
                OR r_74359b.cc_state LIKE r_74359b.cc_mkt_class 
                OR r_74359b.cc_division <= 52
        ) 
    ORDER BY
        1 NULLS LAST, 2 ASC;
----------->
SELECT
    r_3d5f3d.ca_zip as te_2cc5c7 
FROM
    db1.customer_address AS r_3d5f3d 
INNER JOIN
    db1.reason AS r_1b62d7 
        ON r_3d5f3d.ca_address_sk < r_1b62d7.r_reason_sk 
WHERE
    r_3d5f3d.ca_street_number LIKE '2k' 
    OR r_3d5f3d.ca_country NOT ILIKE 'By7IG7i' 
    OR r_3d5f3d.ca_street_name LIKE 'gDDXX' 
    AND r_3d5f3d.ca_state LIKE 'LQF' 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 27;
----------->
WITH CTE_b8bec7(te_1bb3fb, te_71883e) AS (SELECT
    r_e76d76.cd_gender as te_1bb3fb,
    r_73bbc5.sr_return_time_sk as te_71883e 
FROM
    db1.time_dim AS r_a5baaa,
    db1.customer_demographics AS r_e76d76 
RIGHT JOIN
    db1.store_returns AS r_73bbc5 
        ON r_e76d76.cd_dep_employed_count = r_73bbc5.sr_ticket_number 
WHERE
    r_a5baaa.t_shift NOT ILIKE r_e76d76.cd_gender 
    OR r_73bbc5.sr_fee = 91.50561229 
    AND r_73bbc5.sr_return_tax = r_73bbc5.sr_return_ship_cost 
ORDER BY
    1, 2 DESC) SELECT
    chr(unix_timestamp()) as te_34334d 
FROM
    CTE_b8bec7 AS r_a7b9ef 
RIGHT JOIN
    (
        SELECT
            r_7d9da3.web_gmt_offset as te_1815c4,
            r_7d9da3.web_market_manager as te_74c356 
        FROM
            (SELECT
                r_e06767.t_time_sk as te_bb6a62 
            FROM
                db1.income_band AS r_4175c9 
            INNER JOIN
                db1.time_dim AS r_e06767 
                    ON r_4175c9.ib_upper_bound = r_e06767.t_time_sk 
            WHERE
                r_e06767.t_time_id LIKE 'el' 
            ORDER BY
                1 DESC NULLS FIRST) AS r_630a76, db1.web_site AS r_7d9da3 
        WHERE
            r_630a76.te_bb6a62 = r_7d9da3.web_company_id 
            OR r_7d9da3.web_site_sk = 19 
            AND NOT r_7d9da3.web_rec_end_date >= r_7d9da3.web_rec_start_date 
            AND r_7d9da3.web_rec_end_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
        ORDER BY
            1 NULLS LAST, 2 NULLS FIRST) AS r_a1e0e9 
                ON r_a7b9ef.te_71883e >= r_a1e0e9.te_1815c4 
        ORDER BY
            1 DESC NULLS LAST;
----------->
SELECT
    r_f9265a.cc_call_center_id as te_2ee3b5,
    r_883e37.ca_zip as te_8bee54,
    r_f9265a.cc_gmt_offset as te_37a640 
FROM
    db1.call_center AS r_f9265a 
RIGHT JOIN
    db1.call_center AS r_555b9e 
        ON r_f9265a.cc_name NOT LIKE r_555b9e.cc_hours,
    db1.customer_address AS r_883e37 
LEFT JOIN
    db1.income_band AS r_ea97e4 
        ON r_883e37.ca_address_sk < r_ea97e4.ib_income_band_sk 
WHERE
    r_f9265a.cc_sq_ft != r_ea97e4.ib_lower_bound 
    AND r_555b9e.cc_class >= 'U' 
    OR r_555b9e.cc_rec_start_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
GROUP BY
    1, 2, 3 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_addee1.wp_url as te_25ad37,
    r_addee1.wp_url as te_ea0a42 
FROM
    db1.date_dim AS r_9faa46,
    db1.web_page AS r_addee1 
LEFT JOIN
    db1.income_band AS r_e507e2 
        ON r_addee1.wp_customer_sk <= r_e507e2.ib_income_band_sk 
WHERE
    r_9faa46.d_dow < r_addee1.wp_max_ad_count 
ORDER BY
    1 NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    try_add(55,
    r_96d0fc.te_4a97e6) as te_a86393,
    r_b98c3f.hd_buy_potential as te_295378 
FROM
    db1.household_demographics AS r_b98c3f,
    (SELECT
        length(r_553e7f.ca_street_type) as te_4a97e6 
    FROM
        (SELECT
            * 
        FROM
            db1.customer_address PIVOT(count(ca_location_type) AS pa_ef4506,
            max(ca_street_name) AS pa_a2c7e4 FOR ca_state IN (('r9') AS pf_299ae0,
            ('X4Q') AS pf_b751a9,
            ('G6sU') AS pf_9f11df,
            ('i') AS pf_c25b20,
            ('7RX') AS pf_64ec23,
            ('DDX') AS pf_ee77d1))) AS r_553e7f 
    RIGHT JOIN
        db1.call_center AS r_f488cf 
            ON r_553e7f.ca_gmt_offset > r_f488cf.cc_gmt_offset 
    WHERE
        r_553e7f.ca_street_number LIKE 'px6' 
        OR r_553e7f.ca_address_id ILIKE r_553e7f.ca_suite_number 
        AND r_553e7f.ca_street_number = r_f488cf.cc_call_center_id 
        AND r_f488cf.cc_tax_percentage != 86.64619888 
    ORDER BY
        1 NULLS LAST) AS r_96d0fc 
    INNER JOIN
        db1.customer_demographics AS r_05a30b 
            ON r_96d0fc.te_4a97e6 != r_05a30b.cd_dep_college_count 
    WHERE
        r_b98c3f.hd_buy_potential NOT ILIKE r_05a30b.cd_marital_status 
        AND r_b98c3f.hd_dep_count >= r_05a30b.cd_dep_employed_count 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    r_a52fb1.c_birth_country as te_957fb4 
FROM
    db1.time_dim AS r_38819d 
LEFT JOIN
    db1.customer AS r_a52fb1 
        ON r_38819d.t_time > r_a52fb1.c_current_cdemo_sk 
WHERE
    r_a52fb1.c_customer_id LIKE r_a52fb1.c_preferred_cust_flag 
    AND r_a52fb1.c_birth_month = 29 
    AND r_a52fb1.c_birth_day >= 83 
    OR r_a52fb1.c_login NOT ILIKE 'TUOV' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_7d2352.sr_return_time_sk as te_40444c,
    r_7d2352.sr_return_tax as te_230835 
FROM
    db1.store_returns AS r_7d2352,
    (SELECT
        r_0f5d0b.s_gmt_offset as te_a5aecb 
    FROM
        (SELECT
            * 
        FROM
            db1.store PIVOT(sum(s_market_id) AS pa_757def FOR s_company_name IN (('IHlp') AS pf_cec119,
            ('lstnt') AS pf_eeed05,
            ('DDXX') AS pf_2ae3d6))) AS r_0f5d0b 
    ORDER BY
        1 ASC NULLS FIRST) AS r_accb91 
    WHERE
        r_7d2352.sr_store_sk > r_accb91.te_a5aecb 
        OR r_7d2352.sr_hdemo_sk > r_7d2352.sr_addr_sk 
        AND r_7d2352.sr_ticket_number BETWEEN r_7d2352.sr_return_time_sk / 94 AND 32 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_7a951f.c_last_name as te_06c6e6,
    btrim(r_935e56.t_sub_shift) as te_1b457c 
FROM
    db1.customer AS r_7a951f 
LEFT JOIN
    db1.time_dim AS r_935e56 
        ON r_7a951f.c_salutation NOT ILIKE r_935e56.t_shift,
    db1.household_demographics AS r_07666d 
WHERE
    r_7a951f.c_birth_country < r_07666d.hd_buy_potential 
    OR r_935e56.t_time_sk < r_7a951f.c_customer_sk 
    AND r_7a951f.c_first_shipto_date_sk <= 17 
    AND r_7a951f.c_birth_year = 94 
    OR r_7a951f.c_preferred_cust_flag ILIKE r_7a951f.c_customer_id 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_7a207d.sr_return_time_sk as te_2880b6,
    r_be0cf6.wp_url as te_16e721 
FROM
    db1.store_returns AS r_7a207d 
INNER JOIN
    db1.income_band AS r_54b9cc 
        ON r_7a207d.sr_return_time_sk = r_54b9cc.ib_upper_bound,
    db1.web_page AS r_be0cf6 
WHERE
    r_54b9cc.ib_lower_bound = r_be0cf6.wp_char_count 
    AND r_7a207d.web_county != r_7a207d.sr_refunded_cash 
    OR r_be0cf6.wp_url > r_be0cf6.wp_type 
    OR r_be0cf6.wp_autogen_flag NOT LIKE 'ntb2G' 
    OR r_7a207d.sr_return_time_sk != 32 
ORDER BY
    1 DESC, 2 DESC;
----------->
SELECT
    chr(r_803c03.d_same_day_ly) as te_ab4ebf,
    r_2f0e1e.cd_education_status as te_88dc0e,
    r_803c03.d_current_month as te_1bc734 
FROM
    db1.income_band AS r_0131d5 
RIGHT JOIN
    db1.date_dim AS r_803c03 
        ON r_0131d5.ib_upper_bound < r_803c03.d_dom,
    db1.customer_demographics AS r_2f0e1e 
WHERE
    r_803c03.d_fy_year = r_2f0e1e.cd_demo_sk 
    OR r_803c03.d_quarter_seq > 16 
    AND r_803c03.d_date_id >= 'Xp' 
    OR r_803c03.d_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 DESC;
----------->
SELECT
    r_bb8581.cc_call_center_id as te_7db191,
    now() as te_89a618,
    r_fdbec5.web_rec_end_date as te_1df2c7 
FROM
    db1.web_site AS r_658622,
    db1.web_site AS r_fdbec5 
RIGHT JOIN
    db1.call_center AS r_bb8581 
        ON r_fdbec5.web_rec_start_date != r_bb8581.cc_rec_end_date 
WHERE
    r_658622.web_close_date_sk > r_fdbec5.web_site_sk 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 
LIMIT 90;
----------->
SELECT
    r_fc8299.wp_autogen_flag as te_d0455d 
FROM
    db1.warehouse AS r_afff28 
RIGHT JOIN
    db1.web_page AS r_fc8299 
        ON r_afff28.w_gmt_offset > r_fc8299.wp_max_ad_count 
WHERE
    r_afff28.w_warehouse_id NOT LIKE 'GC0lezm' 
    OR r_afff28.w_warehouse_sq_ft >= 4 
    OR r_afff28.w_warehouse_sq_ft > r_fc8299.wp_link_count 
    AND r_afff28.w_county LIKE r_fc8299.wp_autogen_flag 
ORDER BY
    1 DESC;
----------->
SELECT
    r_9777f4.i_brand_id as te_9a0b6d 
FROM
    db1.item AS r_9777f4 
WHERE
    r_9777f4.i_class_id != r_9777f4.i_item_sk 
    AND r_9777f4.i_units ILIKE 'y7IG7i' 
    AND r_9777f4.i_manufact NOT ILIKE 'aLQFr' 
    OR r_9777f4.i_wholesale_cost <= r_9777f4.i_current_price 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 12;
----------->
SELECT
    decimal(bigint(r_e4bb08.ib_income_band_sk)) as te_f331fd,
    r_777c3a.d_date as te_b100dd,
    r_777c3a.d_current_quarter as te_f9dd4f 
FROM
    db1.income_band AS r_e4bb08 
RIGHT JOIN
    db1.date_dim AS r_777c3a 
        ON r_e4bb08.ib_lower_bound >= r_777c3a.d_fy_year,
    db1.customer_demographics AS r_dab5c2 
INNER JOIN
    db1.ship_mode AS r_bfa253 
        ON r_dab5c2.cd_gender LIKE r_bfa253.sm_contract 
WHERE
    r_777c3a.d_dom IS NOT NULL 
    OR r_bfa253.sm_type ILIKE r_bfa253.sm_ship_mode_id 
    AND r_bfa253.sm_carrier ILIKE r_bfa253.sm_code 
    AND r_bfa253.sm_type NOT ILIKE r_bfa253.sm_contract 
    OR r_bfa253.sm_ship_mode_sk > 72 
ORDER BY
    1, 2 NULLS FIRST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_50a6b6.c_salutation as te_297a5a,
    r_50a6b6.c_salutation as te_6c4b4e,
    r_4c9b08.ca_city as te_1a2297 
FROM
    db1.customer AS r_50a6b6 
INNER JOIN
    db1.date_dim AS r_723d78 
        ON r_50a6b6.c_first_shipto_date_sk <= r_723d78.d_fy_year,
    (SELECT
        * 
    FROM
        db1.customer_address PIVOT(max(ca_address_id) AS pa_47c38d FOR ca_zip IN (('UOVbB') AS pf_55c0af,
        ('hv95G') AS pf_4a6f19,
        ('C') AS pf_bac96f,
        ('gDD') AS pf_394606))) AS r_4c9b08 
WHERE
    r_723d78.d_same_day_ly < r_4c9b08.ca_address_sk 
    OR r_4c9b08.ca_county NOT LIKE 'C0lezmSRI' 
    OR r_4c9b08.pf_394606 LIKE 'U' 
    OR r_723d78.d_quarter_name LIKE r_723d78.d_current_year 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS LAST 
LIMIT 17;
----------->
SELECT
    r_009951.w_county as te_c4f881 
FROM
    db1.warehouse AS r_009951 
WHERE
    r_009951.w_warehouse_sq_ft IN (
        SELECT
            9 as te_78bcee 
        FROM
            (SELECT
                try_add(r_e5d840.cc_division,
                r_e5d840.cc_rec_end_date) as te_c9d7e9,
                r_e5d840.cc_suite_number as te_697bd6 
            FROM
                db1.call_center AS r_e5d840,
                db1.household_demographics AS r_9c65db 
            WHERE
                r_e5d840.cc_sq_ft = r_9c65db.hd_dep_count 
                OR r_9c65db.hd_vehicle_count = 71 
                OR r_e5d840.cc_mkt_desc NOT LIKE 'x2S' 
                AND r_e5d840.cc_mkt_id != 45 
            ORDER BY
                1 DESC NULLS LAST, 2 NULLS FIRST) AS r_6e7df1 
        ORDER BY
            1 ASC NULLS FIRST) 
            AND r_009951.w_state LIKE '7IG7ir98hv' 
        ORDER BY
            1 ASC;
----------->
SELECT
    r_4916f2.c_last_review_date_sk as te_fe5788,
    current_date() as te_640a5d,
    r_4916f2.c_birth_day * 24 / r_499d76.te_9baa52 as te_30ae2c 
FROM
    db1.income_band AS r_119912 
INNER JOIN
    db1.customer AS r_4916f2 
        ON r_119912.ib_upper_bound > r_4916f2.c_birth_year,
    (SELECT
        r_07bda2.web_state as te_c92629,
        r_07bda2.web_zip as te_b8d84f 
    FROM
        db1.ship_mode AS r_ac2adb,
        (SELECT
            r_c2931a.cc_closed_date_sk as te_b560c4 
        FROM
            db1.call_center AS r_c2931a 
        INNER JOIN
            (
                SELECT
                    to_char(r_cdfb57.cc_tax_percentage,
                    '000D00') as te_63d322,
                    chr(r_cdfb57.cc_closed_date_sk) as te_16c8c5 
                FROM
                    db1.reason AS r_edf765,
                    db1.call_center AS r_cdfb57 
                LEFT JOIN
                    db1.income_band AS r_39172c 
                        ON r_cdfb57.cc_mkt_id = r_39172c.ib_lower_bound 
                WHERE
                    r_edf765.r_reason_id NOT ILIKE r_cdfb57.cc_name 
                    AND r_cdfb57.cc_closed_date_sk != r_cdfb57.cc_call_center_sk 
                ORDER BY
                    1 NULLS FIRST, 2 DESC NULLS LAST
            ) AS r_84c172 
                ON r_c2931a.cc_mkt_desc ILIKE r_84c172.te_16c8c5 
        WHERE
            (
                NOT r_c2931a.cc_company_name NOT LIKE 'XUulstn' 
                AND r_c2931a.cc_closed_date_sk < 11
            ) 
        ORDER BY
            1 DESC NULLS FIRST) AS r_485b42 
        LEFT JOIN
            db1.web_site AS r_07bda2 
                ON r_485b42.te_b560c4 < r_07bda2.web_open_date_sk 
        WHERE
            r_ac2adb.sm_ship_mode_sk <= r_07bda2.web_close_date_sk 
            OR (
                NOT r_07bda2.web_site_id NOT ILIKE r_07bda2.web_state 
                OR r_07bda2.web_site_id LIKE r_07bda2.web_state 
                OR r_07bda2.web_site_sk <= 93 
                AND r_485b42.te_b560c4 = 90
            ) 
        ORDER BY
            1 DESC, 2 DESC) AS r_37f958 
    LEFT JOIN
        (
            SELECT
                chr(bigint(r_c123ee.cc_sq_ft)) as te_aca5e9,
                r_c123ee.cc_tax_percentage as te_9baa52,
                r_c123ee.cc_call_center_sk as te_780752 
            FROM
                db1.ship_mode AS r_bf4c77,
                db1.call_center AS r_c123ee 
            WHERE
                r_bf4c77.sm_ship_mode_sk >= r_c123ee.cc_company 
            ORDER BY
                1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC
        ) AS r_499d76 
            ON r_37f958.te_b8d84f NOT ILIKE r_499d76.te_aca5e9 
    WHERE
        r_4916f2.c_birth_country = r_499d76.te_aca5e9 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS FIRST, 3 NULLS LAST;
----------->
SELECT
    r_d210df.cc_call_center_id as te_c6c5b2,
    r_d210df.cc_call_center_id as te_ae266b 
FROM
    db1.call_center AS r_d210df,
    db1.time_dim AS r_88ec6c 
WHERE
    r_d210df.cc_employees = r_88ec6c.t_time_sk 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    make_date(r_e744f8.t_time_sk,
    r_e744f8.t_minute,
    r_e744f8.t_hour) as te_599c70,
    unix_timestamp() as te_265af0 
FROM
    (SELECT
        r_c5f6ef.ca_county as te_8dd708 
    FROM
        db1.customer_address AS r_c5f6ef 
    WHERE
        r_c5f6ef.ca_address_sk <= 70 
        AND r_c5f6ef.ca_street_name NOT ILIKE 'ir98hv95G' 
        OR r_c5f6ef.ca_country NOT LIKE r_c5f6ef.ca_county 
    ORDER BY
        1 DESC 
    OFFSET 97) AS r_335a53, db1.time_dim AS r_e744f8 WHERE
    r_335a53.te_8dd708 ILIKE r_e744f8.t_time_id 
    AND r_e744f8.t_hour <= r_e744f8.t_time 
    OR r_e744f8.t_second != 82 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST;
----------->
WITH CTE_8f34b5(te_bdbfae, te_d6b05f) AS (SELECT
    r_b87d47.cc_rec_start_date as te_bdbfae,
    btrim(r_1cfd03.ca_state) as te_d6b05f 
FROM
    db1.ship_mode AS r_73c255 
RIGHT JOIN
    (SELECT
        r_33218f.cd_purchase_estimate * 39.3754966D as te_b1215f,
        r_0cbb69.t_am_pm as te_52adcf,
        (75.94158445D + r_33218f.cd_dep_college_count) * (4.76497291D - r_33218f.cd_purchase_estimate) as te_643190 
    FROM
        db1.time_dim AS r_0cbb69 
    INNER JOIN
        db1.customer_demographics AS r_33218f 
            ON r_0cbb69.t_hour >= r_33218f.cd_dep_employed_count,
        db1.call_center AS r_93b7da 
    WHERE
        r_0cbb69.t_am_pm NOT LIKE r_93b7da.cc_company_name 
        AND r_93b7da.cc_suite_number NOT LIKE 'RQii' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC, 3 NULLS LAST 
    OFFSET 65) AS r_f922a4 
    ON r_73c255.sm_ship_mode_id != r_f922a4.te_52adcf,
db1.call_center AS r_b87d47 LEFT JOIN
    db1.customer_address AS r_1cfd03 
        ON r_b87d47.cc_mkt_id = r_1cfd03.ca_gmt_offset 
WHERE
    r_73c255.sm_code < r_b87d47.cc_street_name 
    AND r_b87d47.cc_name NOT ILIKE 'pRQiizz2k' 
    OR r_b87d47.cc_market_manager NOT ILIKE 'LQFrTUOVb' 
    OR r_b87d47.cc_street_type ILIKE '7ir98hv' 
    OR r_1cfd03.ca_street_name ILIKE 'C' 
ORDER BY
    1, 2 DESC NULLS LAST) SELECT
        r_832930.te_016273 as te_1a1efe 
    FROM
        CTE_8f34b5 AS r_d9a69a 
    LEFT JOIN
        (
            SELECT
                r_fcb4ef.te_bbbde6 as te_016273,
                make_timestamp(r_fcb4ef.te_555207,
                r_0e46a9.s_company_id,
                r_0e46a9.s_closed_date_sk,
                r_0e46a9.s_company_id,
                r_0e46a9.s_store_sk,
                r_fcb4ef.te_bbbde6) as te_6ebeca,
                r_0e46a9.s_tax_percentage as te_45e0b4 
            FROM
                db1.store AS r_0e46a9 
            RIGHT JOIN
                (
                    SELECT
                        r_b1cb4a.s_store_sk as te_555207,
                        r_b1cb4a.s_tax_percentage as te_2fcac6,
                        e() as te_bbbde6 
                    FROM
                        CTE_8f34b5 AS r_6226f7 
                    INNER JOIN
                        CTE_8f34b5 AS r_5bf38b 
                            ON r_6226f7.te_bdbfae > r_5bf38b.te_bdbfae,
                        db1.store AS r_b1cb4a 
                    WHERE
                        r_6226f7.te_d6b05f NOT ILIKE r_b1cb4a.s_country 
                        OR r_b1cb4a.s_county NOT LIKE r_b1cb4a.s_division_name 
                        OR r_b1cb4a.s_rec_end_date = DATE'2024-10-11' 
                    GROUP BY
                        1, 2, 3
                ) AS r_fcb4ef 
                    ON r_0e46a9.s_company_id != r_fcb4ef.te_555207,
                db1.customer_address AS r_ab81a6 
            WHERE
                r_fcb4ef.te_2fcac6 > r_ab81a6.ca_gmt_offset 
                AND r_0e46a9.s_street_number LIKE 'sUgD' 
                OR r_0e46a9.s_closed_date_sk = 56 
                OR r_0e46a9.s_store_name ILIKE 'hv95G' 
            ORDER BY
                1 DESC, 2 ASC NULLS LAST, 3 DESC) AS r_832930 
                    ON r_d9a69a.te_d6b05f NOT LIKE chr(r_832930.te_45e0b4) 
            WHERE
                r_832930.te_6ebeca > TIMESTAMP'2024-03-26 00:50:41.733' 
                AND r_d9a69a.te_bdbfae > DATE'2024-03-25' 
            ORDER BY
                1 ASC NULLS FIRST;
----------->
SELECT
    r_d66f96.r_reason_desc as te_842753 
FROM
    db1.reason AS r_d66f96 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_501f97.d_date as te_e5e4fa,
    r_501f97.pf_adc47f_pa_f41852 as te_ff7ac4,
    r_3b95f1.ca_city as te_ab926c 
FROM
    db1.customer_address AS r_3b95f1,
    db1.reason AS r_4d28fc 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.date_dim PIVOT(stddev(d_fy_quarter_seq) AS pa_f41852,
            max(d_holiday) AS pa_0f7e51,
            stddev(d_fy_year) AS pa_711975 FOR d_quarter_seq IN ((35) AS pf_adc47f,
            (18) AS pf_0554b0))
    ) AS r_501f97 
        ON r_4d28fc.r_reason_sk <= r_501f97.d_qoy 
WHERE
    r_3b95f1.ca_address_sk > r_501f97.d_moy 
    AND r_501f97.d_same_day_lq != r_3b95f1.ca_address_sk 
    AND r_501f97.d_following_holiday NOT ILIKE r_501f97.d_current_day 
    AND r_501f97.d_first_dom != r_501f97.d_dom 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_e0e36b.t_meal_time as te_ad125a 
FROM
    (SELECT
        r_f910ed.d_current_week as te_d802c9 
    FROM
        db1.date_dim AS r_f910ed 
    WHERE
        r_f910ed.d_dow >= 82 
        OR r_f910ed.d_current_day ILIKE r_f910ed.d_following_holiday 
    ORDER BY
        1 DESC) AS r_e1615d 
INNER JOIN
    db1.time_dim AS r_e0e36b 
        ON r_e1615d.te_d802c9 ILIKE r_e0e36b.t_shift 
WHERE
    r_e0e36b.t_time != 29 
    OR r_e0e36b.t_minute > 80 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_b03c85(te_d90dd6, te_7c9992) AS (SELECT
    unix_timestamp() as te_d90dd6,
    r_9eded9.te_14a5e2 as te_7c9992 
FROM
    (SELECT
        * 
    FROM
        db1.household_demographics UNPIVOT INCLUDE NULLS ((up_80c87d,
        up_33f10a) FOR upn_7d6bf7 IN ((hd_income_band_sk,
        hd_buy_potential) AS UPF_7ef4d9))) AS r_8107cf,
    (SELECT
        reverse(r_449f84.d_following_holiday) as te_14a5e2,
        r_449f84.d_holiday as te_17e4a0 
    FROM
        db1.time_dim AS r_827456,
        db1.date_dim AS r_449f84 
    WHERE
        r_827456.t_minute != r_449f84.d_dom 
        OR r_827456.t_shift NOT LIKE r_827456.t_time_id 
    ORDER BY
        1 NULLS LAST, 2 ASC NULLS LAST) AS r_9eded9 
WHERE
    chr(r_8107cf.hd_demo_sk) NOT ILIKE r_9eded9.te_17e4a0 
    AND r_8107cf.hd_dep_count != 65 
    OR r_9eded9.te_17e4a0 LIKE r_9eded9.te_14a5e2 
    OR r_8107cf.upn_7d6bf7 LIKE 'mSRIvXUul' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST) SELECT
        pi() as te_01f2e4, r_7affa9.w_gmt_offset as te_b4a101 
    FROM
        CTE_b03c85 AS r_b52c89 
    INNER JOIN
        db1.time_dim AS r_02756c 
            ON chr(r_b52c89.te_d90dd6) NOT ILIKE r_02756c.t_shift,
        (SELECT
            * 
        FROM
            db1.warehouse PIVOT(count(w_state) AS pa_c4931e FOR w_country IN (('lBy7IG7ir') AS pf_ef491e,
            ('v7RXp') AS pf_fb89d8))) AS r_7affa9 
    WHERE
        r_02756c.t_time >= r_7affa9.w_warehouse_sq_ft 
        OR (
            NOT r_7affa9.w_suite_number LIKE '6sUgD' 
            OR r_02756c.t_minute != 19 
            OR r_b52c89.te_7c9992 NOT ILIKE r_7affa9.w_county 
            AND r_7affa9.w_street_number ILIKE r_7affa9.w_street_type
        ) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_9879cb.ca_gmt_offset as te_e2bd23,
    r_9879cb.ca_gmt_offset as te_1d74ff,
    btrim(r_6c6d57.d_following_holiday) as te_0f27d5 
FROM
    db1.customer_demographics AS r_e37618 
RIGHT JOIN
    db1.customer_address AS r_9879cb 
        ON r_e37618.cd_dep_employed_count >= r_9879cb.ca_address_sk,
    db1.date_dim AS r_6c6d57 
WHERE
    r_e37618.cd_dep_employed_count = r_6c6d57.d_week_seq 
ORDER BY
    1, 2, 3 DESC NULLS FIRST 
OFFSET 42;
----------->
SELECT
    chr(r_675c8f.sr_fee) as te_4fd4c2,
    r_675c8f.sr_refunded_cash as te_df0b77,
    r_675c8f.sr_cdemo_sk * 54.82386087D as te_1545b4 
FROM
    db1.income_band AS r_9dacf5,
    db1.store_returns AS r_675c8f 
WHERE
    r_9dacf5.ib_income_band_sk <= r_675c8f.sr_return_amt_inc_tax 
    AND r_675c8f.sr_reason_sk < 1 
ORDER BY
    1, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_a63fa2.cc_zip as te_a71887 
FROM
    db1.call_center AS r_a63fa2 
RIGHT JOIN
    (
        SELECT
            47 * r_908815.w_warehouse_sq_ft as te_8f3328,
            r_3e3060.ib_lower_bound as te_d83025,
            r_908815.w_country as te_5c1a6f 
        FROM
            db1.ship_mode AS r_df8711,
            db1.income_band AS r_3e3060 
        LEFT JOIN
            db1.warehouse AS r_908815 
                ON r_908815.w_county IS NULL 
        WHERE
            r_df8711.sm_ship_mode_sk > r_3e3060.ib_upper_bound 
        GROUP BY
            2, 3, 1 
        ORDER BY
            1 DESC, 2 DESC, 3 DESC NULLS LAST 
        LIMIT 10 OFFSET 74
) AS r_275d03 
    ON r_a63fa2.cc_closed_date_sk >= r_275d03.te_8f3328 WHERE
        r_275d03.te_d83025 != 99 
        OR r_275d03.te_8f3328 BETWEEN r_a63fa2.cc_closed_date_sk + 88 AND 2 
        AND r_a63fa2.cc_manager LIKE r_a63fa2.cc_mkt_desc 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_1b2c59.ib_upper_bound as te_9c6ff3,
    date_sub(add_months(make_date(r_1b2c59.ib_upper_bound,
    r_1b2c59.ib_lower_bound,
    r_2d7638.cd_demo_sk),
    94 + r_2d7638.cd_demo_sk),
    r_2d7638.cd_dep_count) as te_7c88d7 
FROM
    db1.income_band AS r_1b2c59,
    db1.customer_demographics AS r_2d7638 
WHERE
    r_1b2c59.ib_lower_bound >= r_2d7638.cd_demo_sk 
    AND r_2d7638.cd_marital_status IS NULL 
    OR r_1b2c59.ib_upper_bound != 85 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    date_add(r_8e95e5.i_rec_start_date,
    r_8e95e5.i_manager_id) as te_b3258a 
FROM
    db1.item AS r_8e95e5 
WHERE
    r_8e95e5.i_current_price != r_8e95e5.i_wholesale_cost 
    OR r_8e95e5.i_class > 'mSRIvXU' 
    AND r_8e95e5.i_manufact_id >= 96 
    AND r_8e95e5.i_class_id BETWEEN 4 AND 4 
ORDER BY
    1 ASC;
----------->
SELECT
    r_1f5467.d_year as te_2a17f8,
    r_1f5467.d_same_day_lq + bigint(r_1f5467.d_dow) as te_e98a9f 
FROM
    db1.date_dim AS r_1f5467,
    db1.customer_address AS r_39f473 
WHERE
    r_1f5467.d_first_dom < r_39f473.ca_address_sk 
    AND r_1f5467.d_dow NOT IN (
        SELECT
            try_add(90,
            r_63907b.ib_lower_bound) as te_3ac763 
        FROM
            db1.income_band AS r_63907b 
        INNER JOIN
            (
                SELECT
                    r_e91143.c_customer_id as te_234506,
                    r_cb7e71.pf_cd8b7e as te_1664d0,
                    r_e91143.c_last_name as te_3c924c 
                FROM
                    db1.income_band AS r_48533e 
                LEFT JOIN
                    db1.customer AS r_e91143 
                        ON r_48533e.ib_lower_bound < r_e91143.c_birth_day,
                    (SELECT
                        * 
                    FROM
                        db1.income_band PIVOT(variance(ib_upper_bound) AS pa_96ea68 FOR ib_lower_bound IN ((23) AS pf_d7c205,
                        (62) AS pf_cd8b7e,
                        (8) AS pf_c0b3ca,
                        (3) AS pf_25a92f,
                        (75) AS pf_418401,
                        (37) AS pf_8bf78c))) AS r_cb7e71 
                INNER JOIN
                    db1.store_returns AS r_6e1307 
                        ON r_cb7e71.ib_income_band_sk != r_6e1307.sr_item_sk 
                WHERE
                    NOT r_e91143.c_birth_day <= r_6e1307.sr_return_quantity 
                    OR r_e91143.c_customer_id IN (
                        SELECT
                            '7' as te_5319f4 
                        FROM
                            db1.reason AS r_cb3ee2,
                            db1.store_returns AS r_9ca1b1 
                        WHERE
                            r_cb3ee2.r_reason_sk = r_9ca1b1.sr_hdemo_sk 
                            OR r_9ca1b1.sr_ticket_number != 47 
                            OR r_9ca1b1.sr_reversed_charge BETWEEN r_9ca1b1.sr_ticket_number - 58.91190292 AND 31.32199594 
                            AND r_cb3ee2.r_reason_desc ILIKE r_cb3ee2.r_reason_id 
                            OR r_9ca1b1.sr_fee = r_9ca1b1.sr_return_amt_inc_tax 
                        ORDER BY
                            1 DESC NULLS LAST
                    ) 
                    OR r_6e1307.web_county < 85.60089186 
                    OR r_cb7e71.pf_d7c205 <= r_cb7e71.pf_cd8b7e 
                    OR r_e91143.c_birth_country NOT ILIKE r_e91143.c_first_name 
                ORDER BY
                    1, 2 DESC NULLS LAST, 3 NULLS LAST) AS r_099c8f 
                        ON chr(r_63907b.ib_income_band_sk) NOT ILIKE r_099c8f.te_3c924c 
                WHERE
                    r_63907b.ib_lower_bound != 18 
                    AND r_63907b.ib_income_band_sk = r_63907b.ib_lower_bound 
                    AND r_099c8f.te_234506 NOT LIKE 'RXp' 
                ORDER BY
                    1 
                OFFSET 74) ORDER BY
                    1 DESC, 2 DESC;
----------->
SELECT
    r_2e24fd.s_tax_percentage as te_61e49d 
FROM
    db1.customer_demographics AS r_925190 
INNER JOIN
    db1.store AS r_2e24fd 
        ON r_925190.cd_purchase_estimate >= r_2e24fd.s_market_id 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 29;
----------->
SELECT
    r_57c568.w_warehouse_sk as te_b8aa03 
FROM
    db1.warehouse AS r_57c568 
WHERE
    r_57c568.w_gmt_offset < 9.2115898 
    OR r_57c568.w_city ILIKE r_57c568.w_street_name 
ORDER BY
    1 DESC 
LIMIT 30;
----------->
SELECT
    r_3ef737.t_time as te_15d746 
FROM
    db1.time_dim AS r_c7f8a6 
RIGHT JOIN
    db1.time_dim AS r_3ef737 
        ON r_c7f8a6.t_sub_shift ILIKE r_3ef737.t_am_pm 
WHERE
    r_3ef737.t_time > 93 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 29;
----------->
SELECT
    date_add(r_1fe502.i_rec_start_date,
    r_1fe502.i_class_id) as te_e52428,
    r_595def.ca_zip as te_0a481c 
FROM
    db1.item AS r_1fe502,
    db1.customer_address AS r_595def 
LEFT JOIN
    db1.ship_mode AS r_aa9efe 
        ON r_595def.ca_country LIKE r_aa9efe.sm_carrier 
WHERE
    r_1fe502.i_item_sk < r_595def.ca_address_sk 
    OR r_1fe502.i_size NOT ILIKE 'mSRIvXUuls' 
    AND r_1fe502.i_rec_start_date = r_1fe502.i_rec_end_date 
    AND r_aa9efe.sm_contract ILIKE 'stn' 
    AND r_1fe502.i_item_sk >= r_aa9efe.sm_ship_mode_sk 
ORDER BY
    1, 2 DESC NULLS LAST;
----------->
SELECT
    make_timestamp(r_05f5bb.sr_cdemo_sk,
    r_5eff90.cc_open_date_sk,
    r_05f5bb.sr_returned_date_sk,
    r_05f5bb.sr_store_sk,
    r_5eff90.cc_sq_ft,
    r_05f5bb.sr_net_loss) as te_723958 
FROM
    db1.call_center AS r_5eff90 
RIGHT JOIN
    db1.store_returns AS r_05f5bb 
        ON r_5eff90.cc_company <= r_05f5bb.sr_return_ship_cost 
WHERE
    r_05f5bb.sr_fee >= 55.06256161 
    AND r_05f5bb.sr_return_quantity = 31 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_00c1f0.r_reason_id as te_8a8c2b 
FROM
    db1.reason AS r_00c1f0 
WHERE
    r_00c1f0.r_reason_sk != 91 
    OR r_00c1f0.r_reason_desc LIKE r_00c1f0.r_reason_id 
    AND r_00c1f0.r_reason_desc LIKE r_00c1f0.r_reason_id 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        r_5b8817.web_rec_start_date as te_7a6982,
        r_5b8817.web_gmt_offset as te_b8ea6e,
        r_e39dda.i_manufact_id as te_6c20d0 
    FROM
        db1.customer AS r_46b76f 
    LEFT JOIN
        db1.item AS r_e39dda 
            ON r_46b76f.c_birth_year <= r_e39dda.i_manager_id,
        db1.web_site AS r_5b8817 
    WHERE
        r_46b76f.c_customer_id ILIKE r_5b8817.web_state 
    ORDER BY
        1 DESC, 2 NULLS FIRST, 3 NULLS LAST
) 
UNION
SELECT
r_ae041e.i_rec_end_date as te_884175,
r_ae041e.i_wholesale_cost as te_e142c9,
r_ae041e.i_item_sk as te_7cb060 
FROM
db1.item AS r_ae041e 
INNER JOIN
db1.customer AS r_c5919f 
    ON r_ae041e.i_rec_start_date > DATE'2024-03-25' 
ORDER BY
1 ASC NULLS LAST, 2 ASC, 3;
----------->
SELECT
    r_0193b9.sm_ship_mode_sk as te_d3ce22 
FROM
    db1.ship_mode AS r_0193b9 
WHERE
    r_0193b9.sm_carrier LIKE r_0193b9.sm_contract 
    OR r_0193b9.sm_contract NOT LIKE r_0193b9.sm_carrier 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    add_months(try_add(r_aae3bf.ca_address_sk,
    date_sub(DATE'2024-10-11',
    r_aae3bf.ca_address_sk)),
    r_eb4502.r_reason_sk + r_aae3bf.ca_address_sk * 5) as te_c5ab7e,
    r_aae3bf.ca_gmt_offset as te_4a9ca7 
FROM
    db1.customer_address AS r_aae3bf 
RIGHT JOIN
    db1.income_band AS r_5b5a04 
        ON r_aae3bf.ca_address_sk < r_5b5a04.ib_income_band_sk,
    db1.reason AS r_eb4502 
WHERE
    r_aae3bf.ca_county NOT ILIKE r_eb4502.r_reason_id 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_00d8bb.wp_rec_start_date as te_b23f2a 
FROM
    db1.web_page AS r_00d8bb 
RIGHT JOIN
    db1.customer AS r_a0b4f9 
        ON r_00d8bb.wp_url NOT LIKE r_a0b4f9.c_email_address 
WHERE
    r_00d8bb.wp_rec_end_date >= r_00d8bb.wp_rec_start_date 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_e35436.ib_income_band_sk as te_e8ac5f 
FROM
    db1.income_band AS r_e35436 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_488634.cd_gender as te_8908f1,
    r_488634.cd_education_status as te_b43438 
FROM
    db1.customer_address AS r_d4b56f,
    db1.customer_demographics AS r_488634 
INNER JOIN
    db1.income_band AS r_786833 
        ON r_488634.cd_dep_count != r_786833.ib_lower_bound 
WHERE
    r_d4b56f.ca_address_sk = r_786833.ib_upper_bound 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC;
----------->
SELECT
    try_add(r_66c31f.s_closed_date_sk,
    r_66c31f.s_rec_start_date) as te_9462a4,
    r_d046c0.pf_e356e2 as te_e337be 
FROM
    (SELECT
        * 
    FROM
        db1.reason PIVOT(count(r_reason_sk) AS pa_b5f0ef FOR (r_reason_id,
        r_reason_desc) IN ((('ir98hv95G',
        'DCMaLQFrT')) AS pf_b2074c,
        (('TU',
        'x2SW4P')) AS pf_e356e2))) AS r_d046c0,
    db1.store AS r_66c31f 
WHERE
    r_d046c0.pf_b2074c != r_66c31f.s_division_id 
ORDER BY
    1 DESC NULLS LAST, 2 DESC 
LIMIT 55 OFFSET 98;
----------->
SELECT
    r_564d4f.web_mkt_class as te_a5b6a7 
FROM
    db1.time_dim AS r_f1105f 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.web_site PIVOT(count(web_zip) AS pa_a9367c FOR web_street_name IN (('BRGC0lez') AS pf_0fc65b,
            ('IG7ir9') AS pf_3235e8,
            ('tb2Gv7RX') AS pf_e5ca0f,
            ('Q') AS pf_b22a10,
            ('DXX4Q') AS pf_9d3ea1))
    ) AS r_564d4f 
        ON r_f1105f.t_time_sk = r_564d4f.web_gmt_offset 
WHERE
    r_564d4f.web_close_date_sk > r_f1105f.t_second 
    OR r_f1105f.t_second < 4 
    AND r_564d4f.pf_3235e8 != r_564d4f.pf_9d3ea1 
    OR r_564d4f.web_market_manager LIKE 'z2kG' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_e2a600.sr_return_time_sk as te_9a56c2,
    r_e2a600.sr_reason_sk as te_acfead,
    r_dd1a14.cc_company as te_3fb146 
FROM
    db1.store_returns AS r_e2a600 
RIGHT JOIN
    db1.call_center AS r_dd1a14 
        ON r_e2a600.sr_refunded_cash != r_dd1a14.cc_employees,
    db1.customer_demographics AS r_1f8ed3 
WHERE
    r_dd1a14.cc_class NOT LIKE r_1f8ed3.cd_marital_status 
    OR r_dd1a14.cc_company >= 15 
    AND r_1f8ed3.cd_marital_status ILIKE r_1f8ed3.cd_credit_rating 
    AND r_e2a600.sr_refunded_cash != r_dd1a14.cc_gmt_offset 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_b90546.ib_lower_bound as te_506b68,
    r_6b1041.cc_employees as te_92dd95,
    r_fcc2e0.web_mkt_id as te_dbfe90 
FROM
    db1.income_band AS r_b90546 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.household_demographics UNPIVOT EXCLUDE NULLS ((up_69d1eb,
            up_9a7bed) FOR upn_9da82c IN ((hd_buy_potential,
            hd_demo_sk) AS UPF_af505c))
    ) AS r_b5f4dc 
        ON r_b90546.ib_lower_bound > r_b5f4dc.up_9a7bed,
    db1.web_site AS r_fcc2e0 
RIGHT JOIN
    db1.call_center AS r_6b1041 
        ON r_6b1041.cc_gmt_offset IS NOT NULL 
WHERE
    r_b5f4dc.upn_9da82c LIKE r_6b1041.cc_zip 
    OR r_fcc2e0.web_company_name ILIKE 'OVbBRG' 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    unix_timestamp() as te_2c0b8f,
    r_dfcc3d.sm_contract as te_5e59b6 
FROM
    db1.customer_demographics AS r_3e90c0,
    db1.ship_mode AS r_dfcc3d 
WHERE
    r_3e90c0.cd_dep_college_count = r_dfcc3d.sm_ship_mode_sk 
    OR r_3e90c0.cd_education_status LIKE 'X' 
    OR r_3e90c0.cd_demo_sk > 33 
    AND r_dfcc3d.sm_type LIKE r_dfcc3d.sm_code 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_1496b7.sr_cdemo_sk as te_17cc03 
FROM
    db1.store_returns AS r_1496b7 
WHERE
    r_1496b7.sr_store_sk <= r_1496b7.sr_hdemo_sk 
    AND r_1496b7.sr_refunded_cash = 38.43927671 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_ec72a7(te_fa5a3e) AS (SELECT
    r_5a7c81.r_reason_desc as te_fa5a3e 
FROM
    db1.reason AS r_5a7c81 
ORDER BY
    1 NULLS LAST) SELECT
    r_7846f8.i_wholesale_cost as te_5d9738, r_f5ae9d.c_last_name as te_42bb09, r_7846f8.i_rec_end_date as te_16d57f 
FROM
    CTE_ec72a7 AS r_2a887d,
    db1.item AS r_7846f8 
LEFT JOIN
    db1.customer AS r_f5ae9d 
        ON r_7846f8.i_wholesale_cost != r_f5ae9d.c_birth_year 
WHERE
    NOT r_2a887d.te_fa5a3e NOT LIKE r_f5ae9d.c_first_name 
    AND r_7846f8.i_manager_id < r_7846f8.i_brand_id 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 ASC NULLS FIRST 
OFFSET 37;
----------->
SELECT
    hash(36.13156921D / r_39bfe9.sr_returned_date_sk * double(r_39bfe9.sr_hdemo_sk) / unix_timestamp() / r_39bfe9.sr_return_time_sk * 4,
    now()) as te_c71bd2 
FROM
    db1.store_returns AS r_39bfe9 
RIGHT JOIN
    (
        SELECT
            decimal(r_066db9.c_current_addr_sk) - unix_timestamp() as te_4da919 
        FROM
            db1.customer AS r_066db9 
        WHERE
            r_066db9.c_birth_day >= r_066db9.c_birth_month 
            AND r_066db9.c_birth_day = 61 
        ORDER BY
            1 NULLS LAST
    ) AS r_f006d1 
        ON r_39bfe9.sr_ticket_number <= r_f006d1.te_4da919 
WHERE
    r_39bfe9.sr_reason_sk <= 38 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_4313fb.t_am_pm as te_68929b,
    now() as te_1f78b7 
FROM
    (SELECT
        make_date(r_b4f39d.t_hour,
        r_b4f39d.t_second,
        r_b4f39d.t_time) as te_e46008 
    FROM
        db1.time_dim AS r_b4f39d 
    LEFT JOIN
        db1.customer_demographics AS r_779f6b 
            ON r_b4f39d.t_hour <= r_779f6b.cd_dep_count 
    WHERE
        r_b4f39d.t_time_sk > r_b4f39d.t_second 
        OR r_b4f39d.t_meal_time NOT ILIKE 'XX4QHZgG' 
        AND r_b4f39d.t_minute != r_779f6b.cd_purchase_estimate 
    ORDER BY
        1 DESC NULLS LAST) AS r_8c8b09 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.time_dim PIVOT(max(t_time_id) AS pa_749cab FOR t_time_sk IN ((73) AS pf_b874cf,
            (12) AS pf_e281a1))
    ) AS r_4313fb 
        ON r_8c8b09.te_e46008 <= date_from_unix_date(r_4313fb.t_minute),
    db1.web_page AS r_bf44a8 
WHERE
    r_4313fb.t_hour != r_bf44a8.wp_image_count 
    OR r_4313fb.t_second >= 14 
    OR r_4313fb.t_hour >= 66 
    AND r_4313fb.pf_e281a1 LIKE 'ZgG' 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
WITH CTE_b4d5d5(te_c8c0f5, te_ebcae3, te_901843) AS (SELECT
    hex(r_d9d01c.sr_addr_sk) as te_c8c0f5,
    r_00db00.i_category as te_ebcae3,
    r_00db00.i_item_desc as te_901843 
FROM
    db1.date_dim AS r_e4daba 
RIGHT JOIN
    db1.store_returns AS r_d9d01c 
        ON r_e4daba.d_fy_year != r_d9d01c.sr_reason_sk,
    db1.item AS r_00db00 
RIGHT JOIN
    (SELECT
        r_99f323.web_gmt_offset as te_e58543,
        r_99f323.web_rec_end_date as te_d647d9,
        min(r_058231.i_class) as te_abff76 
    FROM
        db1.item AS r_058231 
    LEFT JOIN
        db1.web_site AS r_99f323 
            ON r_058231.i_wholesale_cost <= r_99f323.web_gmt_offset,
        db1.reason AS r_3355ba 
    INNER JOIN
        db1.ship_mode AS r_c4c222 
            ON r_3355ba.r_reason_sk != r_c4c222.sm_ship_mode_sk 
    WHERE
        r_99f323.web_site_sk < r_c4c222.sm_ship_mode_sk 
        AND r_99f323.web_street_number NOT LIKE r_99f323.web_market_manager 
        OR r_058231.i_container ILIKE r_99f323.web_name 
        AND r_058231.i_class_id <= 54 
        AND r_058231.i_brand NOT ILIKE r_99f323.web_city 
    GROUP BY
        r_99f323.web_rec_end_date, r_99f323.web_gmt_offset 
    ORDER BY
        1 ASC, 2 NULLS LAST, 3 NULLS LAST 
    OFFSET 39) AS r_a2f14a 
    ON r_00db00.i_rec_start_date != r_a2f14a.te_d647d9 WHERE
        r_e4daba.d_last_dom != r_00db00.i_category_id 
        AND r_e4daba.d_quarter_name NOT LIKE r_e4daba.d_current_month),
    CTE_96e306(te_46ac50) AS (SELECT
        r_33ce40.t_hour as te_46ac50 
    FROM
        db1.time_dim AS r_33ce40 
    LEFT JOIN
        db1.ship_mode AS r_f857ae 
            ON r_33ce40.t_meal_time ILIKE r_f857ae.sm_type 
    ORDER BY
        1 ASC 
    LIMIT 27) SELECT
    r_1f78dc.te_c8c0f5 as te_29e51c, 'I' as te_be48c4 FROM
        CTE_b4d5d5 AS r_1f78dc,
        CTE_96e306 AS r_810b5f 
    RIGHT JOIN
        CTE_96e306 AS r_f84cff 
            ON r_810b5f.te_46ac50 > r_f84cff.te_46ac50 
    WHERE
        r_1f78dc.te_ebcae3 NOT LIKE chr(r_f84cff.te_46ac50) 
        AND r_1f78dc.te_901843 LIKE 'stntb2G' 
        OR NOT r_f84cff.te_46ac50 != 78 
        AND r_810b5f.te_46ac50 != r_f84cff.te_46ac50 
        OR r_1f78dc.te_c8c0f5 LIKE '5G6sUgDDXX' 
    ORDER BY
        1 NULLS LAST, 2 DESC;
----------->
WITH CTE_9bd670(te_08c353, te_97f20e, te_e3dd8b) AS (SELECT
    r_d5f75f.w_county as te_08c353,
    r_d5f75f.w_city as te_97f20e,
    r_33cbfa.cc_gmt_offset as te_e3dd8b 
FROM
    db1.call_center AS r_33cbfa,
    db1.warehouse AS r_d5f75f 
INNER JOIN
    db1.income_band AS r_6d1ab2 
        ON r_d5f75f.w_warehouse_sk <= r_6d1ab2.ib_lower_bound 
WHERE
    r_33cbfa.cc_mkt_id >= r_d5f75f.w_gmt_offset 
    AND r_33cbfa.cc_call_center_id LIKE r_d5f75f.w_county), CTE_465519(te_a88cba) AS (SELECT
    r_fe0d18.c_current_hdemo_sk as te_a88cba 
FROM
    db1.customer AS r_fe0d18 
WHERE
    r_fe0d18.c_customer_sk = 16 
ORDER BY
    1 DESC NULLS LAST) SELECT
    string(r_606742.te_a88cba) as te_144351 
FROM
    CTE_9bd670 AS r_9b63bd 
INNER JOIN
    CTE_465519 AS r_606742 
        ON r_9b63bd.te_e3dd8b = r_606742.te_a88cba 
WHERE
    r_9b63bd.te_97f20e ILIKE 'hv95G' 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_a392e9.w_gmt_offset as te_c0e903 
FROM
    db1.warehouse AS r_a392e9 
WHERE
    r_a392e9.w_street_number ILIKE r_a392e9.w_warehouse_name 
    OR r_a392e9.w_street_number NOT LIKE r_a392e9.w_suite_number 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_aaf881.web_tax_percentage as te_63bb7f,
    r_aaf881.web_market_manager as te_264249 
FROM
    (SELECT
        r_84844e.cd_dep_employed_count as te_f273f0,
        r_84844e.cd_dep_count as te_6a0cea 
    FROM
        db1.customer_demographics AS r_84844e,
        (SELECT
            r_86d4ad.i_item_id as te_b5866c 
        FROM
            db1.item AS r_86d4ad 
        WHERE
            r_86d4ad.i_formulation NOT LIKE '7x2' 
            AND r_86d4ad.i_wholesale_cost <= 2.30762435 
            AND r_86d4ad.i_wholesale_cost != 30.04433317 
        ORDER BY
            1 DESC NULLS LAST 
        LIMIT 56) AS r_92db34 WHERE
        r_84844e.cd_education_status NOT LIKE r_92db34.te_b5866c 
        AND r_84844e.cd_marital_status LIKE r_84844e.cd_gender 
        AND r_92db34.te_b5866c NOT LIKE 'RXpR' 
        OR r_84844e.cd_demo_sk BETWEEN r_84844e.cd_purchase_estimate * 62 AND 24 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST) AS r_13f7e6, (SELECT
            min(r_3beeb2.ca_street_number) as te_6ceb68, r_24ed1e.i_manufact_id + r_24ed1e.i_item_sk as te_d64062 
        FROM
            db1.item AS r_d930f4 
        INNER JOIN
            db1.item AS r_24ed1e 
                ON r_d930f4.i_rec_start_date > r_24ed1e.i_rec_end_date,
            db1.customer_address AS r_3beeb2 
        WHERE
            r_24ed1e.i_size ILIKE r_3beeb2.ca_city 
            OR (
                NOT r_24ed1e.i_item_id NOT ILIKE 'Gk4e' 
                AND r_24ed1e.i_manufact_id >= r_d930f4.i_category_id 
                OR r_d930f4.i_manager_id = r_24ed1e.i_class_id
            ) 
        GROUP BY
            r_3beeb2.ca_street_number, r_24ed1e.i_item_sk, r_24ed1e.i_manufact_id 
        ORDER BY
            1 NULLS LAST, 2 NULLS LAST) AS r_16eeb8 
    INNER JOIN
        db1.web_site AS r_aaf881 
            ON r_16eeb8.te_6ceb68 ILIKE r_aaf881.web_site_id 
    WHERE
        r_13f7e6.te_f273f0 != r_aaf881.web_company_id 
    ORDER BY
        1 DESC NULLS FIRST, 2 NULLS FIRST;
----------->
SELECT
    r_95a788.sr_store_sk as te_5bc95e,
    try_add(r_e052c3.t_second,
    DATE'2024-03-25') as te_1b11f3,
    r_e052c3.t_meal_time as te_1c88a8 
FROM
    db1.time_dim AS r_e052c3,
    (WITH CTE_df457b(te_c95650) AS (SELECT
        abs(r_911e2f.i_manufact_id - 8) as te_c95650 
    FROM
        db1.item AS r_911e2f 
    RIGHT JOIN
        db1.store_returns AS r_60bd97 
            ON r_911e2f.i_current_price = r_60bd97.sr_net_loss 
    WHERE
        r_60bd97.sr_net_loss < 48.01220922 
        AND r_911e2f.i_category NOT LIKE 'pRQiizz2k' 
        OR r_60bd97.sr_item_sk = 80 
    ORDER BY
        1 ASC NULLS LAST), CTE_bbf679(te_a12baf, te_e6609a, te_1b4f86) AS (SELECT
        chr(r_1f4cf1.sm_ship_mode_sk / r_f344c8.w_gmt_offset) as te_a12baf, r_f344c8.w_county as te_e6609a, current_date() as te_1b4f86 
    FROM
        db1.warehouse AS r_f344c8 
    LEFT JOIN
        db1.ship_mode AS r_dc93b8 
            ON r_f344c8.w_warehouse_sk != r_dc93b8.sm_ship_mode_sk,
        db1.call_center AS r_56432d 
    RIGHT JOIN
        db1.ship_mode AS r_1f4cf1 
            ON r_56432d.cc_open_date_sk > r_1f4cf1.sm_ship_mode_sk 
    WHERE
        r_f344c8.w_warehouse_sq_ft > r_56432d.cc_company 
        AND r_1f4cf1.sm_code ILIKE 'rTUOVbBRG' 
        OR r_f344c8.w_state LIKE r_1f4cf1.sm_carrier 
        OR r_f344c8.w_gmt_offset != r_56432d.cc_tax_percentage 
        OR r_dc93b8.sm_ship_mode_sk = r_1f4cf1.sm_ship_mode_sk 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST 
    LIMIT 47) SELECT
    r_f5b344.upn_ac1c99 as te_3f3814, overlay(r_f5b344.up_67e417 PLACING 88 FROM
        64) as te_730272,
        r_f5b344.upn_ac1c99 as te_7c1d7f 
    FROM
        CTE_df457b AS r_ba3354 
    INNER JOIN
        (
            SELECT
                * 
            FROM
                (SELECT
                    r_96e5d4.s_rec_start_date as te_d787e0,
                    r_96e5d4.s_suite_number as te_2f055f 
                FROM
                    db1.customer AS r_30ce22,
                    db1.store AS r_96e5d4 
                RIGHT JOIN
                    (
                        SELECT
                            r_6b1024.s_number_employees as te_f5ac76,
                            r_6b1024.s_zip as te_b77a26 
                        FROM
                            db1.time_dim AS r_ea067a 
                        INNER JOIN
                            db1.store AS r_6b1024 
                                ON r_ea067a.t_meal_time LIKE r_6b1024.s_geography_class,
                            db1.customer_address AS r_6eea49 
                        INNER JOIN
                            (
                                SELECT
                                    r_45ad6e.d_date as te_d06131 
                                FROM
                                    db1.date_dim AS r_45ad6e 
                                LEFT JOIN
                                    db1.time_dim AS r_158c42 
                                        ON r_45ad6e.d_same_day_ly >= r_158c42.t_minute 
                                WHERE
                                    r_158c42.t_shift <= 'RXpRQiizz2' 
                                    OR r_45ad6e.d_current_week NOT LIKE 'GC0le' 
                                ORDER BY
                                    1 NULLS FIRST
                            ) AS r_92e273 
                                ON chr(character_length(r_6eea49.ca_street_name)) NOT LIKE chr(unix_seconds(timestamp(r_92e273.te_d06131))) 
                        WHERE
                            r_6b1024.s_gmt_offset <= r_6eea49.ca_gmt_offset 
                            OR r_6b1024.s_market_desc LIKE 's' 
                            OR r_ea067a.t_sub_shift NOT LIKE r_ea067a.t_meal_time 
                        ORDER BY
                            1 DESC, 2 DESC NULLS LAST) AS r_8879b2 
                                ON r_96e5d4.s_manager ILIKE r_8879b2.te_b77a26 
                        WHERE
                            NOT r_30ce22.c_customer_id NOT LIKE r_96e5d4.s_store_id 
                            OR NOT r_30ce22.c_preferred_cust_flag NOT LIKE r_96e5d4.s_store_id 
                            AND r_96e5d4.s_tax_percentage != r_96e5d4.s_gmt_offset 
                        ORDER BY
                            1 DESC, 2 ASC NULLS FIRST
                    ) UNPIVOT INCLUDE NULLS ((up_74b18d, up_67e417) FOR upn_ac1c99 IN ((te_d787e0, te_2f055f) AS UPF_45c3b0))
                ) AS r_f5b344 
                    ON chr(r_ba3354.te_c95650) LIKE r_f5b344.upn_ac1c99,
                CTE_bbf679 AS r_19fcb1 
            WHERE
                r_f5b344.upn_ac1c99 LIKE r_19fcb1.te_a12baf 
            ORDER BY
                1 ASC NULLS LAST, 2 ASC, 3 DESC NULLS FIRST) AS r_eec7c4 
        RIGHT JOIN
            db1.store_returns AS r_95a788 
                ON r_eec7c4.te_7c1d7f ILIKE chr(r_95a788.sr_ticket_number) 
        WHERE
            r_e052c3.t_time_id LIKE r_eec7c4.te_7c1d7f 
            AND r_95a788.sr_return_quantity >= r_95a788.sr_returned_date_sk 
            OR (
                NOT r_95a788.sr_store_sk <= 2 
                OR r_95a788.sr_reversed_charge >= 17.14679773
            ) 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    abs(avg(mod(r_07ce59.d_last_dom,
    93)) + 40 + r_07ce59.d_fy_quarter_seq) as te_0f388e 
FROM
    db1.customer AS r_c078e2 
INNER JOIN
    db1.date_dim AS r_07ce59 
        ON r_c078e2.c_salutation <= r_07ce59.d_current_quarter 
WHERE
    r_07ce59.d_moy = r_07ce59.d_dow 
    OR r_07ce59.d_first_dom != r_07ce59.d_year 
GROUP BY
    r_07ce59.d_last_dom, r_07ce59.d_fy_quarter_seq 
ORDER BY
    1 NULLS LAST 
OFFSET 14;
----------->
SELECT
    hash(true,
    false) as te_c6f649,
    make_date(r_b14889.cc_division,
    r_b14889.cc_employees,
    r_bda522.ib_upper_bound) as te_da2d98,
    count(r_b14889.cc_employees) as te_527c0d 
FROM
    db1.income_band AS r_bda522,
    db1.call_center AS r_b14889 
LEFT JOIN
    db1.reason AS r_8fb319 
        ON r_b14889.cc_name ILIKE r_8fb319.r_reason_id 
WHERE
    r_bda522.ib_income_band_sk <= r_b14889.cc_closed_date_sk 
    OR r_b14889.cc_gmt_offset <= 99.34022881 
GROUP BY
    r_bda522.ib_upper_bound, r_b14889.cc_employees, r_b14889.cc_division 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_0919f4.ca_street_number as te_77b9c5,
    r_0919f4.ca_suite_number as te_706c38 
FROM
    db1.customer AS r_aecb61 
RIGHT JOIN
    (
        SELECT
            current_timestamp() as te_fcf183,
            r_1de75f.d_fy_week_seq + 88 as te_b83962,
            r_0480eb.web_manager as te_c0f5ad 
        FROM
            db1.date_dim AS r_1de75f 
        RIGHT JOIN
            db1.web_site AS r_0480eb 
                ON r_1de75f.d_date < r_0480eb.web_rec_end_date,
            db1.income_band AS r_cdd4f9 
        WHERE
            r_1de75f.d_dow = r_cdd4f9.ib_upper_bound 
            AND r_0480eb.web_tax_percentage = 83.02334329 
            AND r_1de75f.d_dom <= 61 
            AND r_0480eb.web_market_manager NOT ILIKE '8hv95G6sUg'
    ) AS r_d56ed5 
        ON r_aecb61.c_first_name NOT LIKE r_d56ed5.te_c0f5ad,
    db1.customer_address AS r_0919f4 
INNER JOIN
    db1.customer AS r_4a8003 
        ON r_0919f4.ca_country = r_4a8003.c_preferred_cust_flag 
WHERE
    r_aecb61.c_birth_country LIKE r_0919f4.ca_state 
    AND r_4a8003.c_last_review_date_sk <= r_4a8003.c_birth_month 
    AND r_0919f4.ca_street_number ILIKE 'pRQi' 
    AND r_aecb61.c_email_address NOT LIKE 'kg7x2SW4P' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST 
LIMIT 24;
----------->
SELECT
    r_585656.sm_code as te_073b47 
FROM
    db1.ship_mode AS r_585656 
WHERE
    r_585656.sm_contract <= 'bBRGC' 
    AND r_585656.sm_code NOT LIKE r_585656.sm_carrier 
    OR r_585656.sm_ship_mode_sk BETWEEN 5 AND 5 
    OR r_585656.sm_code = 'By7IG' 
ORDER BY
    1 NULLS FIRST 
LIMIT 42;
----------->
SELECT
    r_b38547.s_street_number as te_fce413 
FROM
    (SELECT
        r_8b0ca0.d_date as te_e9f688,
        r_8b0ca0.d_date as te_39cff6,
        r_8b0ca0.d_day_name as te_491edd 
    FROM
        db1.date_dim AS r_8b0ca0 
    RIGHT JOIN
        db1.customer AS r_2e81a8 
            ON r_8b0ca0.d_fy_week_seq != r_2e81a8.c_current_addr_sk,
        (SELECT
            r_085407.d_holiday as te_df41b9,
            r_b2f8d3.c_current_addr_sk as te_6891fa,
            r_b2f8d3.c_birth_year as te_e00132 
        FROM
            db1.date_dim AS r_085407,
            db1.web_site AS r_46c244 
        INNER JOIN
            db1.customer AS r_b2f8d3 
                ON r_46c244.web_class NOT LIKE r_b2f8d3.c_login 
        WHERE
            r_085407.d_weekend NOT LIKE r_46c244.web_state 
            AND r_46c244.web_gmt_offset IN (
                WITH CTE_bc9230(te_1eb3d6) AS (SELECT
                    unix_timestamp() as te_1eb3d6 
                FROM
                    db1.ship_mode AS r_1137c3 
                RIGHT JOIN
                    db1.time_dim AS r_44752d 
                        ON r_1137c3.sm_ship_mode_sk != r_44752d.t_time_sk 
                WHERE
                    r_44752d.t_shift NOT ILIKE 'zz2' 
                    OR r_1137c3.sm_carrier NOT IN (SELECT
                        'S' as te_3f9b9c 
                    FROM
                        db1.date_dim AS r_da04a3 
                    WHERE
                        r_da04a3.d_fy_quarter_seq = 58 
                    ORDER BY
                        1 DESC) 
                    OR r_44752d.t_minute != r_44752d.t_second), CTE_3aea52(te_ce8095, te_ddb7e0, te_d6ca42) AS (SELECT
                        r_ea5e38.c_preferred_cust_flag as te_ce8095, r_d00129.sr_return_quantity as te_ddb7e0, r_d00129.sr_reason_sk as te_d6ca42 
                    FROM
                        db1.warehouse AS r_3f379a 
                    LEFT JOIN
                        db1.customer AS r_ea5e38 
                            ON r_3f379a.w_zip LIKE r_ea5e38.c_salutation,
                        db1.store_returns AS r_d00129 
                    WHERE
                        r_3f379a.w_warehouse_sq_ft < r_d00129.sr_item_sk 
                        OR r_d00129.sr_return_amt = 0.54825209 
                        AND r_ea5e38.c_current_addr_sk != 83 
                    ORDER BY
                        1 NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST) SELECT
                        r_dca959.ca_gmt_offset as te_dbe6fc 
                    FROM
                        CTE_bc9230 AS r_6fb1c0 
                    RIGHT JOIN
                        db1.customer_address AS r_dca959 
                            ON r_6fb1c0.te_1eb3d6 >= r_dca959.ca_address_sk 
                    WHERE
                        r_dca959.ca_street_number LIKE r_dca959.ca_address_id 
                        OR r_dca959.ca_street_number NOT LIKE r_dca959.ca_street_type 
                        AND r_dca959.ca_state NOT ILIKE r_dca959.ca_zip 
                    ORDER BY
                        1 DESC NULLS FIRST
                ) 
                AND r_b2f8d3.c_customer_id ILIKE 't' 
            ORDER BY
                1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC) AS r_ad56d4 
            WHERE
                r_8b0ca0.d_weekend ILIKE r_ad56d4.te_df41b9 
                AND r_2e81a8.c_last_review_date_sk = 2 
                AND r_2e81a8.c_first_shipto_date_sk <= r_ad56d4.te_e00132 
            ORDER BY
                1 DESC, 2 DESC NULLS FIRST, 3 NULLS FIRST
        ) AS r_90dfca 
    LEFT JOIN
        db1.store AS r_b38547 
            ON r_90dfca.te_39cff6 != r_b38547.s_rec_end_date 
    WHERE
        (
            NOT r_b38547.s_gmt_offset > r_b38547.s_tax_percentage 
            OR r_b38547.s_rec_end_date > DATE'2024-03-25'
        ) 
    ORDER BY
        1 DESC NULLS LAST;
----------->
SELECT
    hex(r_744108.ib_lower_bound) as te_6003f8 
FROM
    db1.income_band AS r_744108 
WHERE
    r_744108.ib_lower_bound < 52 
    AND r_744108.ib_lower_bound != 87 
    AND r_744108.ib_lower_bound BETWEEN 39 AND 39 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_f51f6a.w_county as te_021c16 
FROM
    db1.warehouse AS r_f51f6a 
WHERE
    r_f51f6a.w_street_name ILIKE 'RGC0le' 
ORDER BY
    1 ASC NULLS LAST;
----------->
WITH CTE_68d6d5(te_06e365, te_630fa9, te_0443ed) AS (SELECT
    r_17ca23.hd_income_band_sk as te_06e365,
    r_4ace6c.s_rec_start_date as te_630fa9,
    r_bcb006.sr_reversed_charge as te_0443ed 
FROM
    db1.store AS r_4ace6c 
LEFT JOIN
    db1.household_demographics AS r_17ca23 
        ON r_4ace6c.s_street_name ILIKE r_17ca23.hd_buy_potential,
    db1.store_returns AS r_bcb006 
LEFT JOIN
    db1.customer_address AS r_5f4213 
        ON r_bcb006.sr_return_amt_inc_tax > r_5f4213.ca_gmt_offset 
WHERE
    r_4ace6c.s_manager ILIKE r_5f4213.ca_zip 
    OR r_bcb006.sr_cdemo_sk != r_bcb006.sr_ticket_number 
    AND r_bcb006.sr_return_amt <= 95.44281845 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC NULLS FIRST, 3 NULLS LAST 
OFFSET 19), CTE_23e6c1(te_f7e414, te_e1d295) AS (SELECT
r_de64bb.s_store_id as te_f7e414, r_de64bb.s_hours as te_e1d295 FROM
    db1.store_returns AS r_d120c8,
    db1.store AS r_de64bb 
WHERE
    r_d120c8.sr_cdemo_sk <= r_de64bb.s_gmt_offset 
    OR r_d120c8.sr_returned_date_sk <= 75 
ORDER BY
    1 NULLS FIRST, 2 NULLS LAST) SELECT
    to_char(r_9618b0.te_0443ed, '999') as te_db8624, r_b4f0e3.c_birth_country || r_254e22.te_f7e414 as te_f86079 
FROM
    CTE_68d6d5 AS r_9618b0 
LEFT JOIN
    CTE_23e6c1 AS r_254e22 
        ON chr(r_9618b0.te_06e365) LIKE r_254e22.te_e1d295,
    db1.reason AS r_23d988 
RIGHT JOIN
    db1.customer AS r_b4f0e3 
        ON r_23d988.r_reason_id LIKE r_b4f0e3.c_first_name 
WHERE
    r_9618b0.te_06e365 > r_b4f0e3.c_current_addr_sk 
    OR r_b4f0e3.c_salutation ILIKE 'aLQFr' 
    OR r_b4f0e3.c_first_sales_date_sk < r_b4f0e3.c_current_hdemo_sk 
    AND r_b4f0e3.c_birth_day = 94 
ORDER BY
    1 ASC NULLS LAST, 2 DESC 
LIMIT 28;
----------->
SELECT
    chr(r_89c131.wp_max_ad_count) as te_54f50a 
FROM
    db1.web_page AS r_89c131 
WHERE
    r_89c131.wp_web_page_id > r_89c131.wp_autogen_flag 
    OR r_89c131.wp_web_page_id NOT LIKE 'ZgGI' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    hex(abs(positive(e()))) as te_41397c 
FROM
    (SELECT
        r_7ab036.te_b72b96 as te_231264 
    FROM
        db1.household_demographics AS r_3bae82 
    RIGHT JOIN
        (
            SELECT
                r_610b51.d_fy_week_seq / r_610b51.d_same_day_lq as te_89e748,
                r_610b51.d_date as te_b72b96 
            FROM
                db1.warehouse AS r_8b5bba 
            RIGHT JOIN
                db1.date_dim AS r_610b51 
                    ON r_8b5bba.w_warehouse_sk != r_610b51.d_year,
                db1.ship_mode AS r_47ff98 
            WHERE
                r_8b5bba.w_street_type LIKE r_47ff98.sm_type 
                AND r_610b51.d_quarter_name NOT LIKE '8' 
                OR r_8b5bba.w_county NOT ILIKE r_610b51.d_weekend 
                AND r_8b5bba.w_country ILIKE r_8b5bba.w_zip 
                AND NOT r_610b51.d_dow <= 1 
            ORDER BY
                1, 2 DESC NULLS LAST
        ) AS r_7ab036 
            ON r_3bae82.hd_income_band_sk < r_7ab036.te_89e748 
    ORDER BY
        1 DESC) AS r_f4ade4 
    WHERE
        r_f4ade4.te_231264 = DATE'2024-03-26' 
        AND r_f4ade4.te_231264 >= DATE'2024-03-26' 
        AND r_f4ade4.te_231264 = r_f4ade4.te_231264 
    ORDER BY
        1 ASC;
----------->
SELECT
    r_9a506a.sr_return_amt as te_5685b1,
    r_9a506a.sr_addr_sk as te_126e22 
FROM
    db1.store_returns AS r_be1058 
LEFT JOIN
    db1.store AS r_5a8c6d 
        ON r_be1058.sr_store_sk = r_5a8c6d.s_market_id,
    db1.store_returns AS r_9a506a 
INNER JOIN
    db1.customer_demographics AS r_002d0e 
        ON r_9a506a.sr_store_credit < r_002d0e.cd_dep_college_count 
WHERE
    r_be1058.sr_return_quantity >= r_9a506a.sr_return_amt 
    AND r_5a8c6d.s_tax_percentage < 86.09615886 
    AND r_be1058.sr_hdemo_sk < 50 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_02a01f.d_fy_quarter_seq as te_2543d4 
FROM
    db1.date_dim AS r_02a01f 
LEFT JOIN
    db1.income_band AS r_80a128 
        ON r_02a01f.d_last_dom != r_80a128.ib_lower_bound 
WHERE
    r_02a01f.d_weekend >= 'LQFr' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_3bf1a9.hd_dep_count as te_ed8cd8 
FROM
    db1.household_demographics AS r_3bf1a9 
WHERE
    r_3bf1a9.hd_demo_sk > 2 
ORDER BY
    1 NULLS LAST 
LIMIT 23;
----------->
SELECT
    r_51a722.i_item_id as te_498b30 
FROM
    db1.item AS r_51a722 
WHERE
    r_51a722.i_rec_start_date BETWEEN DATE'2024-03-25' AND r_51a722.i_rec_start_date 
    AND r_51a722.i_formulation NOT ILIKE '6kg7x2SW4P' 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_d5e37d.c_customer_id as te_9c8b59,
    r_d5e37d.c_customer_id as te_1fb73d 
FROM
    db1.income_band AS r_d0bc69 
INNER JOIN
    db1.store_returns AS r_322e95 
        ON r_d0bc69.ib_income_band_sk != r_322e95.sr_return_time_sk,
    db1.customer AS r_d5e37d 
WHERE
    NOT r_d0bc69.ib_income_band_sk > r_d5e37d.c_birth_year 
    AND r_d5e37d.c_login LIKE r_d5e37d.c_first_name 
    OR r_d5e37d.c_salutation ILIKE r_d5e37d.c_last_name 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_18f28d.s_hours as te_219004,
    r_18f28d.s_market_desc as te_9369d9 
FROM
    db1.reason AS r_e7cb1a 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.income_band PIVOT(avg(ib_upper_bound) AS pa_363297 FOR ib_income_band_sk IN ((38) AS pf_b55df7,
            (73) AS pf_ac9826,
            (85) AS pf_f1c52d,
            (9) AS pf_db00a3,
            (9) AS pf_d9c23e,
            (10) AS pf_3dbc7f))
    ) AS r_f38802 
        ON r_e7cb1a.r_reason_sk != r_f38802.ib_lower_bound,
    (SELECT
        r_97f6a9.i_rec_start_date as te_7ee676,
        r_97f6a9.i_class as te_f8e32e,
        r_97f6a9.i_category_id as te_804975 
    FROM
        db1.household_demographics AS r_ccae86,
        db1.warehouse AS r_b07b38 
    LEFT JOIN
        db1.item AS r_97f6a9 
            ON r_97f6a9.i_wholesale_cost BETWEEN 27.17528469 AND 27.17528469 
    WHERE
        r_ccae86.hd_demo_sk != r_b07b38.w_warehouse_sq_ft 
        AND r_b07b38.w_suite_number NOT LIKE 'Uulst' 
        AND r_97f6a9.i_rec_end_date != r_97f6a9.i_rec_start_date 
        AND r_97f6a9.i_rec_end_date IS NULL 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC, 3 NULLS FIRST) AS r_42c1cd 
INNER JOIN
    db1.store AS r_18f28d 
        ON r_42c1cd.te_7ee676 = r_18f28d.s_rec_end_date 
WHERE
    r_e7cb1a.r_reason_sk = r_18f28d.s_market_id 
    OR r_18f28d.s_rec_start_date < DATE'2024-10-11' 
    AND r_42c1cd.te_7ee676 > r_18f28d.s_rec_end_date 
    OR r_18f28d.s_division_id <= 96 
    OR r_18f28d.s_tax_percentage < r_18f28d.s_gmt_offset 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_e30150.wp_type as te_b0dc18,
    r_e30150.wp_rec_end_date as te_ca3c57,
    max(r_e30150.wp_char_count) as te_2860ad 
FROM
    db1.web_page AS r_e30150 
RIGHT JOIN
    db1.ship_mode AS r_ce4e35 
        ON r_e30150.wp_url NOT LIKE r_ce4e35.sm_ship_mode_id,
    db1.income_band AS r_218297 
WHERE
    r_ce4e35.sm_ship_mode_sk < r_218297.ib_lower_bound 
    AND r_218297.ib_income_band_sk != 66 
GROUP BY
    r_e30150.wp_rec_end_date, r_e30150.wp_type 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    chr(r_7d0ceb.sr_ticket_number) as te_e24270,
    current_timestamp() as te_967704,
    mod(r_7d0ceb.sr_customer_sk,
    82) as te_ba30b7 
FROM
    db1.store_returns AS r_d70ef5 
RIGHT JOIN
    db1.store_returns AS r_7d0ceb 
        ON r_d70ef5.sr_returned_date_sk != r_7d0ceb.sr_reason_sk,
    db1.customer AS r_ee1385 
LEFT JOIN
    (
        SELECT
            r_75ae84.w_warehouse_sk as te_56060e,
            (76.58956472D + r_75ae84.w_warehouse_sk) * unix_timestamp() as te_595675 
        FROM
            (SELECT
                r_e6d554.w_state as te_193e6d,
                r_59da53.d_quarter_name as te_33e289,
                r_59da53.d_date as te_f80762 
            FROM
                db1.date_dim AS r_59da53,
                db1.warehouse AS r_e6d554 
            LEFT JOIN
                (
                    SELECT
                        r_a8e87a.r_reason_desc as te_5694d7 
                    FROM
                        db1.customer AS r_939fef 
                    LEFT JOIN
                        db1.reason AS r_a8e87a 
                            ON r_939fef.c_email_address >= r_a8e87a.r_reason_desc 
                    ORDER BY
                        1 DESC NULLS LAST
                ) AS r_e275ff 
                    ON r_e6d554.w_street_type LIKE r_e275ff.te_5694d7 
            WHERE
                r_59da53.d_day_name ILIKE r_e6d554.w_zip 
            ORDER BY
                1 ASC NULLS LAST, 2 DESC, 3 DESC) AS r_0f5863, db1.warehouse AS r_75ae84 
            WHERE
                r_0f5863.te_193e6d ILIKE r_75ae84.w_state 
                AND r_0f5863.te_33e289 > r_75ae84.w_county 
            ORDER BY
                1 ASC NULLS LAST, 2 ASC) AS r_11b9d3 
                ON r_ee1385.c_current_hdemo_sk >= r_11b9d3.te_595675 
        WHERE
            NOT r_7d0ceb.sr_return_quantity < r_ee1385.c_last_review_date_sk 
            AND (
                NOT r_ee1385.c_preferred_cust_flag ILIKE 'V' 
                OR r_d70ef5.sr_return_amt > 41.55042836
            ) 
        GROUP BY
            1, 3, 2 
        ORDER BY
            1 DESC NULLS FIRST, 2 NULLS LAST, 3 DESC;
----------->
SELECT
    r_7bcacf.ca_suite_number as te_ec2ad2 
FROM
    db1.household_demographics AS r_505a46 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.customer_address PIVOT(min(ca_street_type) AS pa_f606eb FOR ca_address_id IN (('uls') AS pf_48df67,
            ('7') AS pf_a7b01f,
            ('gDDXX') AS pf_822f30))
    ) AS r_7bcacf 
        ON r_505a46.hd_vehicle_count <= r_7bcacf.ca_address_sk 
WHERE
    r_7bcacf.pf_822f30 NOT LIKE 'RI' 
    AND r_505a46.hd_income_band_sk BETWEEN r_505a46.hd_dep_count AND 3 
    OR r_505a46.hd_vehicle_count >= 13 
    AND r_505a46.hd_vehicle_count <= r_7bcacf.ca_address_sk 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_38b0e0.d_quarter_name as te_eb0c92,
    r_38b0e0.d_current_year as te_2f1cde 
FROM
    db1.warehouse AS r_ae0953 
INNER JOIN
    db1.income_band AS r_f5fa67 
        ON r_ae0953.w_gmt_offset >= r_f5fa67.ib_income_band_sk,
    db1.date_dim AS r_38b0e0 
WHERE
    r_ae0953.w_county >= r_38b0e0.d_weekend 
    AND (
        NOT r_f5fa67.ib_lower_bound = (
            (
                SELECT
                    r_60e378.hd_demo_sk as te_27e60f 
                FROM
                    db1.household_demographics AS r_60e378 
                WHERE
                    r_60e378.hd_buy_potential >= 'IG7ir' 
                    AND r_60e378.hd_income_band_sk = r_60e378.hd_dep_count 
                    OR r_60e378.hd_income_band_sk != 78 
                ORDER BY
                    1 ASC NULLS LAST 
                LIMIT 1 OFFSET 69
        )
    ) 
    OR r_ae0953.w_warehouse_sq_ft > r_38b0e0.d_month_seq 
    AND r_38b0e0.d_moy <= 89
) ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_8c1de8.ib_lower_bound as te_acb2d5 
FROM
    db1.income_band AS r_8c1de8 
WHERE
    r_8c1de8.ib_income_band_sk != 26 
    OR r_8c1de8.ib_upper_bound <= r_8c1de8.ib_income_band_sk 
    AND r_8c1de8.ib_upper_bound <= r_8c1de8.ib_lower_bound 
    OR r_8c1de8.ib_upper_bound != r_8c1de8.ib_income_band_sk 
ORDER BY
    1 DESC;
----------->
WITH CTE_7c7ce5(te_118561, te_01f7e9) AS (SELECT
    r_ec54c2.ca_street_name as te_118561,
    r_ec54c2.ca_suite_number as te_01f7e9 
FROM
    db1.customer_address AS r_ec54c2,
    (SELECT
        r_feb412.ca_suite_number as te_547afc 
    FROM
        db1.customer_address AS r_feb412 
    INNER JOIN
        (SELECT
            r_361540.w_gmt_offset as te_b81809,
            r_361540.w_street_type as te_0ba659,
            make_timestamp(r_de9e88.d_dow,
            r_de9e88.d_fy_week_seq,
            r_de9e88.d_first_dom,
            r_de9e88.d_date_sk,
            r_de9e88.d_same_day_lq,
            r_361540.w_gmt_offset) as te_e298f6 
        FROM
            db1.reason AS r_be16f7 
        RIGHT JOIN
            db1.warehouse AS r_361540 
                ON r_be16f7.r_reason_sk != r_361540.w_warehouse_sq_ft,
            db1.date_dim AS r_de9e88 
        WHERE
            r_361540.w_gmt_offset < r_de9e88.d_dow 
            AND r_de9e88.d_fy_quarter_seq != r_de9e88.d_month_seq 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 ASC NULLS FIRST) AS r_39ee4f 
            ON r_feb412.ca_suite_number ILIKE r_39ee4f.te_0ba659 
    WHERE
        r_feb412.ca_address_id NOT ILIKE '4QHZ' 
    ORDER BY
        1 DESC NULLS FIRST 
    LIMIT 37) AS r_406178 WHERE
        r_ec54c2.ca_location_type ILIKE r_406178.te_547afc 
        AND r_ec54c2.ca_street_number ILIKE 'gDDXX' 
        AND r_ec54c2.ca_street_type NOT LIKE r_ec54c2.ca_zip 
        OR r_ec54c2.ca_address_id LIKE 'OVb' 
        OR r_ec54c2.ca_street_type NOT LIKE 'aLQFr' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST), CTE_c231eb(te_e7d87f) AS (SELECT
        r_3dfd4e.sr_refunded_cash as te_e7d87f 
    FROM
        db1.store_returns AS r_3dfd4e 
    WHERE
        r_3dfd4e.sr_addr_sk != 46 
        OR r_3dfd4e.sr_cdemo_sk > r_3dfd4e.sr_return_quantity 
    ORDER BY
        1 ASC NULLS LAST) SELECT
        current_date() as te_dc9733, r_ec489b.te_01f7e9 as te_aad288, r_c65360.pf_856ad8 as te_4482e3 
    FROM
        (SELECT
            * 
        FROM
            (SELECT
                mod(r_363259.i_brand_id + 96,
                unix_timestamp()) * e() as te_2c8b56,
                r_e91606.web_street_number as te_086b3d,
                r_363259.i_item_id as te_77d887 
            FROM
                db1.web_site AS r_e91606,
                db1.warehouse AS r_972737 
            INNER JOIN
                db1.item AS r_363259 
                    ON r_972737.w_gmt_offset != r_363259.i_current_price 
            WHERE
                r_e91606.web_mkt_id >= r_363259.i_item_sk 
                AND r_363259.i_category_id = 45 
                AND r_e91606.web_gmt_offset > r_972737.w_gmt_offset 
                OR r_363259.i_size >= 'XpRQiizz2k' 
            ORDER BY
                1 ASC NULLS LAST, 2 DESC, 3 NULLS LAST) PIVOT(avg(te_2c8b56) AS pa_1737a8 FOR te_77d887 IN (('7') AS pf_d1c2a0, ('5G') AS pf_856ad8, ('v95G') AS pf_941091, ('Qii') AS pf_53a2b5))) AS r_c65360, CTE_7c7ce5 AS r_ec489b 
        RIGHT JOIN
            CTE_c231eb AS r_9763e0 
                ON r_ec489b.te_118561 <= chr(r_9763e0.te_e7d87f) 
        WHERE
            r_c65360.te_086b3d NOT LIKE r_ec489b.te_01f7e9 
            AND r_9763e0.te_e7d87f IN (
                SELECT
                    56.41565832 as te_df5382 
                FROM
                    db1.income_band AS r_821313,
                    db1.web_page AS r_32c2ca 
                RIGHT JOIN
                    db1.customer_demographics AS r_ca5f69 
                        ON r_32c2ca.wp_creation_date_sk BETWEEN 10 AND 10 
                WHERE
                    r_821313.ib_income_band_sk <= r_32c2ca.wp_access_date_sk 
                    AND r_ca5f69.cd_gender NOT LIKE r_32c2ca.wp_autogen_flag 
                    OR NOT r_32c2ca.wp_char_count < 84 
                    AND r_821313.ib_upper_bound < 6
            ) 
            OR r_c65360.pf_d1c2a0 = r_c65360.pf_941091 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC NULLS FIRST, 3 ASC;
----------->
SELECT
    r_1220be.wp_rec_start_date as te_3b209f,
    r_f026c7.ca_city as te_b78244 
FROM
    db1.customer_address AS r_f026c7,
    db1.web_page AS r_1220be 
RIGHT JOIN
    (
        SELECT
            positive(r_727e04.ca_gmt_offset) as te_4e38f1 
        FROM
            db1.customer_address AS r_727e04 
        ORDER BY
            1 ASC 
        LIMIT 17
) AS r_e41bd4 
    ON r_1220be.wp_access_date_sk > r_e41bd4.te_4e38f1 WHERE
        r_f026c7.ca_gmt_offset = r_1220be.wp_char_count 
        AND r_1220be.wp_rec_start_date >= r_1220be.wp_rec_end_date 
        AND r_1220be.wp_link_count >= 67 
        OR r_1220be.wp_access_date_sk > r_1220be.wp_link_count 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
SELECT
    r_0ba23e.sm_carrier as te_2a4d36,
    r_7f9b07.i_item_id as te_7095e6 
FROM
    db1.call_center AS r_c42a7c 
RIGHT JOIN
    db1.ship_mode AS r_0ba23e 
        ON r_c42a7c.cc_hours NOT ILIKE r_0ba23e.sm_code,
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(count(sm_contract) AS pa_aa7d6f FOR sm_type IN (('7ir') AS pf_c7b73c,
        ('z') AS pf_eb3afc,
        ('aLQ') AS pf_39f910))) AS r_b24f0b 
LEFT JOIN
    db1.item AS r_7f9b07 
        ON r_b24f0b.sm_carrier NOT ILIKE r_7f9b07.i_container 
WHERE
    r_c42a7c.cc_tax_percentage > r_7f9b07.i_wholesale_cost 
    AND r_c42a7c.cc_sq_ft >= r_7f9b07.i_manufact_id 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_7f9516.s_gmt_offset as te_fe85df 
FROM
    db1.store AS r_7f9516 
WHERE
    r_7f9516.s_market_id >= r_7f9516.s_company_id 
ORDER BY
    1 ASC NULLS FIRST 
LIMIT 85;
----------->
SELECT
    try_add(r_39c041.ca_city,
    r_0b5d22.ib_lower_bound) as te_d6ef77 
FROM
    db1.income_band AS r_0b5d22 
INNER JOIN
    db1.customer_address AS r_39c041 
        ON r_0b5d22.ib_income_band_sk >= r_39c041.ca_gmt_offset 
WHERE
    r_39c041.ca_gmt_offset < 19.08949347 
    OR r_39c041.ca_street_name LIKE '7IG7ir98h' 
    AND r_39c041.ca_city NOT LIKE 'stnt' 
    AND r_39c041.ca_city ILIKE '6kg7x2S' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_87afc0.cd_dep_count * r_87afc0.cd_demo_sk as te_95b413 
FROM
    db1.customer_demographics AS r_87afc0 
WHERE
    r_87afc0.cd_dep_employed_count <= 34 
    OR r_87afc0.cd_dep_college_count != r_87afc0.cd_dep_employed_count 
    AND r_87afc0.cd_purchase_estimate != 89 
    OR r_87afc0.cd_demo_sk >= 3 
ORDER BY
    1 ASC NULLS LAST;
----------->
WITH CTE_6e65c5(te_1eba2a) AS (SELECT
    r_de4bed.c_customer_id as te_1eba2a 
FROM
    db1.customer AS r_de4bed 
LEFT JOIN
    (SELECT
        r_cd2508.t_am_pm as te_a42bda 
    FROM
        db1.reason AS r_2aff27 
    INNER JOIN
        db1.time_dim AS r_cd2508 
            ON r_2aff27.r_reason_sk >= r_cd2508.t_time 
    WHERE
        r_cd2508.t_shift NOT ILIKE '7x2' 
        OR r_cd2508.t_time >= 76 
        OR r_cd2508.t_time < 48 
    ORDER BY
        1 DESC NULLS LAST 
    OFFSET 71) AS r_b5310e 
    ON r_de4bed.c_login NOT LIKE 'X4QHZgGIHl' WHERE
        r_de4bed.c_customer_id LIKE r_de4bed.c_preferred_cust_flag 
        AND r_de4bed.c_first_shipto_date_sk != 95 
        AND r_de4bed.c_last_name LIKE r_b5310e.te_a42bda 
ORDER BY
    1 ASC NULLS LAST) SELECT
        r_2917da.i_rec_end_date as te_f7cd93, r_2917da.i_wholesale_cost as te_278657, r_2917da.i_current_price as te_cc5f64 
    FROM
        CTE_6e65c5 AS r_f8cd44,
        db1.household_demographics AS r_b66d53 
    LEFT JOIN
        db1.item AS r_2917da 
            ON r_b66d53.hd_buy_potential NOT LIKE r_2917da.i_color 
    WHERE
        r_f8cd44.te_1eba2a >= r_2917da.i_item_id 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC, 3 DESC;
----------->
SELECT
    r_e6adda.cc_open_date_sk as te_c566d4,
    r_ac3262.sm_ship_mode_sk as te_9f1d8a 
FROM
    db1.call_center AS r_e6adda,
    db1.customer AS r_3f29a0 
LEFT JOIN
    db1.ship_mode AS r_ac3262 
        ON r_3f29a0.c_customer_sk IS NULL 
WHERE
    r_e6adda.cc_closed_date_sk = r_3f29a0.c_birth_year 
    AND r_e6adda.cc_open_date_sk >= 59 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST;
----------->
SELECT
    string(r_412f12.ca_address_sk) as te_872c7f 
FROM
    (SELECT
        r_aa5689.c_birth_country as te_043c9a,
        reflect('java.util.UUID',
        'randomUUID') as te_79dc96 
    FROM
        db1.customer AS r_aa5689,
        db1.web_page AS r_fcec9c 
    WHERE
        r_aa5689.c_current_hdemo_sk = r_fcec9c.wp_access_date_sk 
        OR r_aa5689.c_birth_year > 50 
        AND r_aa5689.c_current_hdemo_sk > 56 
        AND r_fcec9c.wp_type NOT ILIKE 'VbBRGC0le' 
        OR r_aa5689.c_birth_day = 93 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST) AS r_f60937 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.customer_address PIVOT(count(ca_suite_number) AS pa_4bce78 FOR ca_address_id IN (('95G6s') AS pf_ae2056,
            ('v7R') AS pf_3ba00c,
            ('RQ') AS pf_5bfaf8,
            ('LQFr') AS pf_092391))
    ) AS r_412f12 
        ON r_f60937.te_79dc96 LIKE r_412f12.ca_country 
WHERE
    r_412f12.ca_gmt_offset >= 72.94631979 
    OR r_412f12.pf_092391 >= 28 
    OR r_412f12.pf_3ba00c >= 90 
    OR r_412f12.ca_address_sk = 30 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_3bd0ec.c_first_sales_date_sk / 47 as te_c5a98d 
FROM
    (SELECT
        * 
    FROM
        db1.customer UNPIVOT INCLUDE NULLS ((up_daa5cd,
        up_f3eeee,
        up_9fb73a) FOR upn_7085e1 IN ((c_customer_id,
        c_birth_country,
        c_current_addr_sk) AS UPF_cdaf13,
        (c_preferred_cust_flag,
        c_email_address,
        c_birth_year) AS UPF_baf1e3))) AS r_18a754 
RIGHT JOIN
    db1.customer AS r_3bd0ec 
        ON r_18a754.upn_7085e1 NOT ILIKE r_3bd0ec.c_birth_country 
WHERE
    r_3bd0ec.c_first_name LIKE 'G' 
    OR r_3bd0ec.c_last_review_date_sk BETWEEN 56 AND 56 
ORDER BY
    1 ASC;
----------->
SELECT
    r_6400e3.cc_employees as te_bdf0a1 
FROM
    db1.call_center AS r_6400e3 
INNER JOIN
    (
        SELECT
            to_char(r_fcef99.wp_web_page_sk,
            '999') as te_a52369,
            r_be4c87.web_name as te_450d44,
            mod(r_790b4d.web_mkt_id,
            unix_timestamp()) / decimal(r_be4c87.web_mkt_id) as te_34ba74 
        FROM
            db1.web_site AS r_be4c87,
            db1.web_page AS r_fcef99 
        RIGHT JOIN
            db1.web_site AS r_790b4d 
                ON r_fcef99.wp_char_count <= r_790b4d.web_company_id 
        WHERE
            r_be4c87.web_close_date_sk = r_fcef99.wp_customer_sk 
            AND r_fcef99.wp_rec_end_date != r_fcef99.wp_rec_start_date 
            OR r_790b4d.web_name ILIKE 'Gv7R' 
            OR r_790b4d.web_gmt_offset = 46.21336266
    ) AS r_208882 
        ON r_6400e3.cc_hours ILIKE r_208882.te_450d44 
WHERE
    r_6400e3.cc_division != 42 
    AND r_6400e3.cc_call_center_id LIKE 'r98' 
    OR r_6400e3.cc_street_name NOT ILIKE 'X' 
ORDER BY
    1 ASC NULLS FIRST 
LIMIT 23;
----------->
SELECT
    38.48817607D * r_7a12fa.sr_hdemo_sk as te_22787b,
    r_c1f63e.sm_carrier as te_50fec5,
    r_7a12fa.sr_return_time_sk as te_9b1d69 
FROM
    (SELECT
        * 
    FROM
        db1.store_returns PIVOT(count(sr_item_sk) AS pa_fef41e FOR sr_store_credit IN ((56.08379589) AS pf_b91429,
        (59.43256992) AS pf_e28a5a,
        (52.94977485) AS pf_a7c9a8,
        (85.61303244) AS pf_d4c136))) AS r_7a12fa,
    db1.ship_mode AS r_c1f63e 
WHERE
    r_7a12fa.sr_store_sk != r_c1f63e.sm_ship_mode_sk 
    OR r_c1f63e.sm_type LIKE r_c1f63e.sm_ship_mode_id 
    AND r_7a12fa.sr_cdemo_sk > 25 
GROUP BY
    1, 3, 2 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST, 3 NULLS LAST 
LIMIT 24;
----------->
SELECT
    r_dee64a.t_meal_time as te_5000f9,
    r_dee64a.t_second as te_229ba4 
FROM
    (SELECT
        r_e7c3be.ca_city as te_5a6cf3 
    FROM
        db1.customer_address AS r_e7c3be 
    WHERE
        r_e7c3be.ca_county LIKE 'v7RXp' 
        AND r_e7c3be.ca_street_name NOT ILIKE 'gGIH' 
        OR r_e7c3be.ca_address_id IS NOT NULL 
    ORDER BY
        1 NULLS FIRST) AS r_2a2005, db1.ship_mode AS r_681eb8 
RIGHT JOIN
    db1.time_dim AS r_dee64a 
        ON r_681eb8.sm_contract < r_dee64a.t_meal_time 
WHERE
    r_2a2005.te_5a6cf3 NOT ILIKE r_dee64a.t_am_pm 
    OR r_681eb8.sm_type NOT LIKE 'P' 
    OR r_dee64a.t_sub_shift NOT LIKE 'VbBRGC' 
ORDER BY
    1 NULLS FIRST, 2 ASC NULLS FIRST 
OFFSET 53;
----------->
SELECT
    r_a5ba0c.s_county as te_86b370,
    r_a5ba0c.s_gmt_offset as te_f01584 
FROM
    db1.store AS r_a5ba0c,
    db1.date_dim AS r_1071fc 
WHERE
    r_a5ba0c.s_street_number NOT ILIKE r_1071fc.d_current_day 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
WITH CTE_754558(te_6394de, te_4f7140, te_246215) AS (SELECT
    r_154727.cc_mkt_id as te_6394de,
    r_154727.cc_call_center_id as te_4f7140,
    r_982a38.w_warehouse_id as te_246215 
FROM
    (SELECT
        try_add(r_f10bda.wp_rec_end_date,
        r_263b31.sm_ship_mode_sk) as te_ade018,
        btrim(r_f10bda.wp_autogen_flag) as te_9006de 
    FROM
        db1.ship_mode AS r_263b31 
    INNER JOIN
        db1.web_page AS r_f10bda 
            ON r_263b31.sm_ship_mode_sk = r_f10bda.wp_link_count,
        db1.time_dim AS r_e16b69 
    WHERE
        r_263b31.sm_carrier ILIKE r_e16b69.t_time_id 
    ORDER BY
        1, 2 DESC NULLS FIRST) AS r_8a4983 
INNER JOIN
    db1.warehouse AS r_982a38 
        ON r_8a4983.te_9006de LIKE r_982a38.w_county,
    db1.call_center AS r_154727 
WHERE
    r_982a38.w_gmt_offset < r_154727.cc_open_date_sk 
    OR r_154727.cc_mkt_desc NOT LIKE 'DX' 
    OR r_154727.cc_division_name ILIKE 'vXUulstn' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 NULLS LAST 
OFFSET 49), CTE_b94b61(te_80bdff, te_2a649f) AS (SELECT
    r_18ce69.i_rec_start_date as te_80bdff, r_18ce69.i_rec_start_date as te_2a649f FROM
        db1.customer AS r_810054 
    INNER JOIN
        db1.item AS r_18ce69 
            ON r_810054.c_customer_id ILIKE r_18ce69.i_class,
        db1.reason AS r_6e556b 
    INNER JOIN
        db1.income_band AS r_099205 
            ON r_6e556b.r_reason_sk <= r_099205.ib_income_band_sk 
    WHERE
        r_810054.c_first_shipto_date_sk != r_099205.ib_lower_bound 
        OR r_18ce69.i_rec_start_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26') SELECT
        pi() as te_1ad49b 
    FROM
        (SELECT
            r_80ec42.cc_rec_end_date as te_eed540 
        FROM
            db1.call_center AS r_80ec42 
        RIGHT JOIN
            db1.time_dim AS r_ddc69e 
                ON r_80ec42.cc_call_center_id ILIKE r_ddc69e.t_meal_time 
        WHERE
            r_ddc69e.t_minute > 43 
        ORDER BY
            1 ASC 
        OFFSET 33) AS r_d56a1b LEFT JOIN
        CTE_754558 AS r_9cafcd 
            ON chr(hash(r_d56a1b.te_eed540)) NOT LIKE chr(character_length(r_9cafcd.te_246215)) 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    56 / r_017468.hd_demo_sk as te_54edb9 
FROM
    db1.household_demographics AS r_017468 
LEFT JOIN
    db1.income_band AS r_40a0bf 
        ON r_017468.hd_vehicle_count <= r_40a0bf.ib_income_band_sk 
ORDER BY
    1 DESC;
----------->
SELECT
    r_73ff19.sr_return_time_sk as te_26c9e8 
FROM
    db1.store_returns AS r_73ff19 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    chr(r_6c3b18.cc_gmt_offset) as te_a29ba4,
    r_638a03.ca_address_id as te_62c3a2 
FROM
    db1.customer_address AS r_638a03,
    db1.call_center AS r_6c3b18 
LEFT JOIN
    db1.call_center AS r_15b71e 
        ON r_6c3b18.cc_call_center_id NOT ILIKE r_15b71e.cc_call_center_id 
WHERE
    r_638a03.ca_gmt_offset > r_6c3b18.cc_company 
    AND r_6c3b18.cc_rec_end_date BETWEEN r_6c3b18.cc_rec_start_date AND DATE'2024-10-11' 
    AND NOT r_15b71e.cc_city NOT ILIKE '8hv95G6s' 
    AND r_638a03.ca_county <= 'lezmSR' 
ORDER BY
    1 DESC, 2 DESC;
----------->
SELECT
    r_ddf8fc.cc_call_center_id as te_a7917c,
    r_ddf8fc.cc_company as te_376ea0,
    r_ddf8fc.cc_mkt_id as te_0e14a6 
FROM
    db1.income_band AS r_73b47c,
    db1.call_center AS r_ddf8fc 
WHERE
    r_73b47c.ib_upper_bound != r_ddf8fc.cc_sq_ft 
    AND r_ddf8fc.cc_manager LIKE 'QH' 
    AND r_ddf8fc.cc_suite_number LIKE 'F' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC, 3 NULLS LAST;
----------->
SELECT
    r_550025.wp_url as te_2e00fd 
FROM
    db1.web_page AS r_550025 
WHERE
    r_550025.wp_rec_end_date > r_550025.wp_rec_start_date 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_ffb821.cc_rec_start_date as te_300aac 
FROM
    db1.customer AS r_3e8628 
LEFT JOIN
    db1.call_center AS r_ffb821 
        ON r_3e8628.c_birth_day > r_ffb821.cc_employees 
WHERE
    r_3e8628.c_email_address LIKE 'ir98hv95' 
    AND r_ffb821.cc_tax_percentage != r_ffb821.cc_gmt_offset 
    AND r_ffb821.cc_gmt_offset = r_ffb821.cc_tax_percentage 
ORDER BY
    1 DESC;
----------->
SELECT
    r_704689.r_reason_sk as te_0b2805 
FROM
    db1.reason AS r_704689 
WHERE
    r_704689.r_reason_desc NOT LIKE r_704689.r_reason_id 
    AND r_704689.r_reason_id ILIKE r_704689.r_reason_desc 
    OR r_704689.r_reason_desc NOT ILIKE 'y7IG7i' 
    OR r_704689.r_reason_sk != 23 
ORDER BY
    1;
----------->
SELECT
    r_b22524.c_first_name as te_2d40ae 
FROM
    db1.household_demographics AS r_7c27b2 
RIGHT JOIN
    db1.customer AS r_b22524 
        ON r_7c27b2.hd_buy_potential NOT LIKE r_b22524.c_first_name 
WHERE
    r_b22524.c_first_shipto_date_sk < r_b22524.c_last_review_date_sk 
    OR r_b22524.c_email_address NOT ILIKE '2SW' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    date_sub(r_3c6ea7.te_f32467,
    r_0694ba.cd_purchase_estimate) as te_3938a4,
    r_2956e0.cd_gender as te_429f2f 
FROM
    db1.customer_demographics AS r_0694ba 
LEFT JOIN
    db1.customer_demographics AS r_2956e0 
        ON r_0694ba.cd_dep_count < r_2956e0.cd_demo_sk,
    (WITH CTE_3de0a4(te_7e338c) AS (SELECT
        to_char(r_c48fea.up_d02062,
        '000D00') as te_7e338c 
    FROM
        (SELECT
            * 
        FROM
            db1.web_site UNPIVOT INCLUDE NULLS ((up_8b0c79,
            up_d02062,
            up_c2d802,
            up_46978f,
            up_921813) FOR upn_16a481 IN ((web_rec_start_date,
            web_tax_percentage,
            web_class,
            web_close_date_sk,
            web_state) AS UPF_515fbb,
            (web_rec_end_date,
            web_gmt_offset,
            web_company_name,
            web_open_date_sk,
            web_site_id) AS UPF_7b1def))) AS r_c48fea 
    WHERE
        r_c48fea.upn_16a481 LIKE 'MaLQFrTUOV' 
        AND r_c48fea.up_d02062 IS NULL 
        AND r_c48fea.web_site_sk = 67 
        OR r_c48fea.web_street_type ILIKE 'v7RXp' 
    ORDER BY
        1 DESC NULLS LAST 
    LIMIT 27), CTE_864916(te_1b43ea) AS (SELECT
        r_2878f5.d_qoy as te_1b43ea FROM
            db1.date_dim AS r_2878f5 
        ORDER BY
            1 ASC NULLS FIRST) SELECT
            make_date(r_ddbedd.t_hour, r_63c87f.cd_demo_sk, r_63c87f.cd_demo_sk) as te_f32467, r_63c87f.cd_marital_status as te_5732ff 
        FROM
            CTE_3de0a4 AS r_d6b8ce 
        LEFT JOIN
            db1.time_dim AS r_ddbedd 
                ON r_d6b8ce.te_7e338c < r_ddbedd.t_minute,
            CTE_864916 AS r_a48ce6 
        LEFT JOIN
            db1.customer_demographics AS r_63c87f 
                ON r_a48ce6.te_1b43ea = r_63c87f.cd_purchase_estimate 
        WHERE
            r_ddbedd.t_time_id IS NOT NULL 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST 
        OFFSET 96) AS r_3c6ea7 WHERE
        r_0694ba.cd_marital_status LIKE r_3c6ea7.te_5732ff 
    ORDER BY
        1 ASC, 2 DESC;
----------->
SELECT
    abs(r_f448cf.hd_income_band_sk) as te_d229cd,
    r_870e95.ca_zip as te_153816 
FROM
    db1.household_demographics AS r_f448cf 
RIGHT JOIN
    db1.customer_address AS r_870e95 
        ON r_f448cf.hd_buy_potential LIKE r_870e95.ca_address_id,
    db1.ship_mode AS r_03b210 
WHERE
    r_870e95.ca_street_name NOT LIKE r_03b210.sm_type 
    OR r_870e95.ca_address_id = 'G6sU' 
    AND r_f448cf.hd_income_band_sk != 64 
    OR r_870e95.ca_city NOT ILIKE 'G6' 
    OR r_870e95.ca_location_type NOT LIKE 'UOV' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST 
OFFSET 67;
----------->
SELECT
    r_d2dccf.d_dow * 57 as te_1f10b9,
    r_d2dccf.d_date as te_5a867f,
    r_302a87.sr_net_loss as te_769284 
FROM
    db1.reason AS r_9998e1 
INNER JOIN
    db1.store_returns AS r_302a87 
        ON r_9998e1.r_reason_sk != r_302a87.sr_addr_sk,
    db1.customer_demographics AS r_0fcd27 
LEFT JOIN
    db1.date_dim AS r_d2dccf 
        ON r_0fcd27.cd_demo_sk >= r_d2dccf.d_dom 
WHERE
    r_9998e1.r_reason_id ILIKE r_d2dccf.d_weekend 
    AND r_9998e1.r_reason_id NOT ILIKE r_9998e1.r_reason_desc 
    OR r_d2dccf.d_last_dom != 70 
    AND r_d2dccf.d_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
ORDER BY
    1 ASC NULLS FIRST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_630c77.r_reason_desc as te_12f33f,
    r_630c77.r_reason_id as te_26a001,
    mod(r_e546b8.sr_hdemo_sk,
    28) as te_533157 
FROM
    db1.store_returns AS r_e546b8,
    db1.reason AS r_630c77 
RIGHT JOIN
    db1.reason AS r_ac5951 
        ON r_630c77.r_reason_desc ILIKE r_ac5951.r_reason_id 
WHERE
    r_e546b8.sr_returned_date_sk < r_ac5951.r_reason_sk 
    AND r_ac5951.r_reason_desc LIKE 'gDDX' 
    AND r_e546b8.sr_item_sk BETWEEN 30 AND 30 
    AND r_e546b8.sr_reason_sk <= 81 
    OR r_e546b8.sr_return_amt <= r_e546b8.sr_refunded_cash 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 DESC NULLS LAST 
OFFSET 63;
----------->
SELECT
    r_f23721.i_class as te_836d66,
    unix_timestamp() as te_840eac,
    r_f23721.i_brand as te_f6fce7 
FROM
    (SELECT
        r_68f594.r_reason_sk as te_e7a5f9 
    FROM
        db1.reason AS r_68f594 
    ORDER BY
        1 DESC NULLS FIRST) AS r_be4541, db1.item AS r_f23721 
RIGHT JOIN
    (
        SELECT
            r_9c92cc.i_current_price as te_f4d862 
        FROM
            db1.item AS r_9c92cc 
        LEFT JOIN
            db1.customer_demographics AS r_3f9c38 
                ON r_9c92cc.i_category NOT ILIKE r_3f9c38.cd_credit_rating 
        WHERE
            (
                NOT r_9c92cc.i_class_id >= 6 
                AND r_9c92cc.i_wholesale_cost < 4.11669942
            ) 
        ORDER BY
            1 NULLS LAST
    ) AS r_f8a1f6 
        ON r_f23721.i_current_price >= r_f8a1f6.te_f4d862 
WHERE
    r_be4541.te_e7a5f9 != r_f23721.i_brand_id 
    OR r_f23721.i_manufact_id <= 25 
    OR r_f23721.i_rec_start_date > DATE'2024-03-26' 
    OR r_f23721.i_class != r_f23721.i_product_name 
    AND r_f23721.i_class ILIKE r_f23721.i_container 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    hash(r_84d31c.wp_rec_start_date,
    date_sub(r_84d31c.wp_rec_end_date,
    r_84d31c.wp_image_count)) as te_93a146,
    r_84d31c.wp_web_page_id as te_c12218 
FROM
    db1.household_demographics AS r_addbf2,
    db1.web_page AS r_84d31c 
INNER JOIN
    db1.reason AS r_2371ee 
        ON r_84d31c.wp_max_ad_count = r_2371ee.r_reason_sk 
WHERE
    r_addbf2.hd_income_band_sk >= r_84d31c.wp_image_count 
    OR r_addbf2.hd_dep_count < 10 
    AND NOT r_84d31c.wp_link_count > 63 
    AND r_84d31c.wp_link_count < 51 
ORDER BY
    1 NULLS FIRST, 2 DESC;
----------->
SELECT
    r_ef9925.c_current_cdemo_sk as te_f2c668 
FROM
    db1.household_demographics AS r_101c0f 
RIGHT JOIN
    db1.customer AS r_ef9925 
        ON r_101c0f.hd_dep_count <= r_ef9925.c_customer_sk 
WHERE
    r_101c0f.hd_dep_count != 33 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_4ec5df.wp_autogen_flag as te_856546 
FROM
    db1.web_page AS r_4ec5df 
WHERE
    r_4ec5df.wp_web_page_id LIKE 'm' 
    AND r_4ec5df.wp_image_count = 12 
    AND r_4ec5df.wp_char_count >= 42 
ORDER BY
    1 NULLS LAST 
OFFSET 95;
----------->
SELECT
    current_timestamp() as te_eae33a,
    r_55efd9.d_date as te_1d1484 
FROM
    db1.date_dim AS r_55efd9 
RIGHT JOIN
    db1.date_dim AS r_c87949 
        ON r_55efd9.d_weekend NOT ILIKE r_c87949.d_holiday,
    db1.date_dim AS r_fa8d0c 
WHERE
    r_55efd9.d_date != r_fa8d0c.d_date 
    AND r_55efd9.d_date_sk >= 75 
    AND r_55efd9.d_year >= 44 
    OR r_c87949.d_following_holiday NOT LIKE r_fa8d0c.d_current_quarter 
    AND r_fa8d0c.d_current_week ILIKE r_fa8d0c.d_quarter_name 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_e8104b.sr_return_quantity as te_2f0999,
    chr(r_d2d641.wp_link_count) as te_841fb8 
FROM
    db1.store_returns AS r_e8104b 
LEFT JOIN
    db1.web_page AS r_d2d641 
        ON r_e8104b.sr_reason_sk >= r_d2d641.wp_image_count,
    db1.customer_demographics AS r_cef6e5 
LEFT JOIN
    db1.household_demographics AS r_79f26e 
        ON r_cef6e5.cd_marital_status NOT ILIKE r_79f26e.hd_buy_potential 
WHERE
    r_e8104b.sr_store_sk > r_79f26e.hd_demo_sk 
    AND r_d2d641.wp_rec_end_date < r_d2d641.wp_rec_start_date 
    AND r_d2d641.wp_autogen_flag ILIKE r_cef6e5.cd_marital_status 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_2c27d3.s_market_manager as te_5f7058,
    r_3749f8.pf_6e85f6 as te_befd14 
FROM
    (SELECT
        * 
    FROM
        db1.store_returns PIVOT(min(sr_return_tax) AS pa_b5b455 FOR sr_return_ship_cost IN ((73.470019) AS pf_8f698f,
        (12.28415145) AS pf_ed1144,
        (57.73804299) AS pf_ccab51,
        (3.2362428) AS pf_98ec75,
        (73.00619212) AS pf_7f8770))) AS r_b40684 
RIGHT JOIN
    db1.household_demographics AS r_d916fc 
        ON r_b40684.sr_cdemo_sk >= r_d916fc.hd_dep_count,
    db1.store AS r_2c27d3 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.store_returns PIVOT(sum(sr_return_time_sk) AS pa_54039d FOR sr_ticket_number IN ((23) AS pf_433872,
            (96) AS pf_804d02,
            (25) AS pf_582854,
            (47) AS pf_6e85f6,
            (73) AS pf_a55d07))
    ) AS r_3749f8 
        ON r_2c27d3.s_gmt_offset != r_3749f8.sr_reversed_charge 
WHERE
    r_b40684.sr_returned_date_sk >= r_3749f8.pf_6e85f6 
    AND r_3749f8.pf_433872 >= r_3749f8.pf_582854 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    current_timestamp() as te_4fcaff,
    r_328866.w_street_name as te_7bdc63,
    r_328866.w_warehouse_sq_ft as te_433ca0 
FROM
    db1.household_demographics AS r_b8e3d0,
    db1.store_returns AS r_d8b189 
RIGHT JOIN
    db1.warehouse AS r_328866 
        ON r_d8b189.sr_return_amt != r_328866.w_gmt_offset 
WHERE
    (
        NOT r_b8e3d0.hd_buy_potential NOT ILIKE r_328866.w_zip 
        OR r_328866.w_state LIKE r_328866.w_country 
        OR r_328866.w_warehouse_sq_ft != 33 
        OR r_328866.w_state ILIKE r_328866.w_street_number
    ) 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS FIRST, 3 ASC NULLS LAST;
----------->
WITH CTE_71951e(te_d2644d, te_96800d, te_e03cec) AS (SELECT
    r_922d48.pf_012a1f - r_922d48.i_manufact_id as te_d2644d,
    r_922d48.i_rec_end_date as te_96800d,
    r_922d48.i_wholesale_cost as te_e03cec 
FROM
    (SELECT
        * 
    FROM
        db1.item PIVOT(variance(i_item_sk) AS pa_d6f42d FOR i_brand_id IN ((19) AS pf_c713e5,
        (88) AS pf_012a1f))) AS r_922d48,
    db1.date_dim AS r_fe3d7a 
WHERE
    r_922d48.i_rec_start_date >= r_fe3d7a.d_date 
    AND r_fe3d7a.d_current_week > 'hv9' 
    AND r_922d48.pf_c713e5 > r_922d48.pf_012a1f 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS FIRST) SELECT
        r_0d039f.te_96800d as te_18b972 
    FROM
        db1.reason AS r_ea9ee1 
    LEFT JOIN
        CTE_71951e AS r_0d039f 
            ON cos(r_ea9ee1.r_reason_sk) >= r_0d039f.te_d2644d 
    WHERE
        r_ea9ee1.r_reason_id NOT ILIKE r_ea9ee1.r_reason_desc 
        OR r_ea9ee1.r_reason_sk < 60 
    ORDER BY
        1 ASC NULLS LAST 
    OFFSET 59;
----------->
(
    SELECT
        r_52cc2d.hd_buy_potential as te_c2a79e,
        r_52cc2d.hd_buy_potential as te_8ff323 
    FROM
        db1.household_demographics AS r_52cc2d,
        db1.customer_demographics AS r_5b2027 
    WHERE
        (
            NOT r_52cc2d.hd_dep_count >= r_5b2027.cd_dep_employed_count 
            OR r_5b2027.cd_purchase_estimate >= 81 
            OR r_52cc2d.hd_vehicle_count BETWEEN 3 AND 3 
            AND r_5b2027.cd_marital_status ILIKE '98hv' 
            AND r_5b2027.cd_marital_status ILIKE 'kg'
        ) 
    ORDER BY
        1 NULLS LAST, 2 ASC
) EXCEPT ALL (
    SELECT
        r_b5afb3.te_eba083 as te_151385, r_a2e26d.t_meal_time as te_738895 
    FROM
        (SELECT
            reflect('java.util.UUID',
            'randomUUID') as te_eba083 
        FROM
            db1.customer_demographics AS r_435639 
        WHERE
            r_435639.cd_purchase_estimate = 12 
            AND r_435639.cd_purchase_estimate = 73 
            AND r_435639.cd_dep_count < 58 
        ORDER BY
            1 DESC NULLS LAST) AS r_b5afb3 
    LEFT JOIN
        db1.time_dim AS r_a2e26d 
            ON r_b5afb3.te_eba083 NOT ILIKE r_a2e26d.t_shift,
        db1.web_page AS r_fb5167 
    WHERE
        r_a2e26d.t_am_pm ILIKE r_fb5167.wp_url 
        OR r_fb5167.wp_rec_end_date = r_fb5167.wp_rec_start_date 
        AND r_fb5167.wp_rec_end_date >= r_fb5167.wp_rec_start_date 
        AND r_fb5167.wp_rec_start_date = r_fb5167.wp_rec_end_date 
    ORDER BY
        1 ASC, 2 DESC NULLS LAST) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_417a70.d_date as te_bc10e8,
    r_417a70.d_date as te_764e46 
FROM
    db1.date_dim AS r_417a70,
    db1.warehouse AS r_a09947 
WHERE
    r_417a70.d_month_seq > r_a09947.w_warehouse_sq_ft 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST 
OFFSET 90;
----------->
SELECT
    hash(date_sub(DATE'2024-03-26',
    r_648689.cd_purchase_estimate),
    make_timestamp(r_648689.cd_purchase_estimate,
    r_648689.cd_dep_college_count,
    r_648689.cd_dep_college_count,
    r_b7fd6e.t_hour,
    r_648689.cd_purchase_estimate,
    69.4977422D)) as te_dc54c7,
    substring(r_76fdf1.w_country,
    r_648689.cd_demo_sk) as te_d5a784,
    r_b7fd6e.t_time_id as te_bfe261 
FROM
    db1.time_dim AS r_b7fd6e,
    db1.warehouse AS r_76fdf1 
LEFT JOIN
    db1.customer_demographics AS r_648689 
        ON r_76fdf1.w_gmt_offset <= r_648689.cd_dep_count 
WHERE
    r_b7fd6e.t_time_sk > r_648689.cd_dep_employed_count 
    AND r_76fdf1.w_warehouse_id NOT ILIKE 'ir' 
    AND r_b7fd6e.t_meal_time NOT ILIKE '6' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_60c078.web_street_number as te_3866f4,
    r_85b424.d_current_month as te_f4e94f 
FROM
    db1.customer_address AS r_c8297a 
INNER JOIN
    db1.web_site AS r_60c078 
        ON r_c8297a.ca_address_sk != r_60c078.web_site_sk,
    db1.date_dim AS r_85b424 
LEFT JOIN
    db1.time_dim AS r_1b2da6 
        ON r_85b424.d_current_month IS NOT NULL 
WHERE
    r_60c078.web_site_id ILIKE r_85b424.d_weekend 
    AND (
        NOT r_60c078.web_gmt_offset <= r_60c078.web_tax_percentage 
        OR r_c8297a.ca_state LIKE 'g' 
        OR r_85b424.d_holiday ILIKE r_85b424.d_quarter_name
    ) 
ORDER BY
    1 ASC, 2 NULLS LAST;
----------->
SELECT
    r_4ecfa0.c_birth_country as te_ef8319,
    positive(pi()) as te_8863f6,
    r_4ecfa0.c_first_name as te_aa240d 
FROM
    db1.income_band AS r_981ce7,
    db1.customer AS r_4ecfa0 
RIGHT JOIN
    db1.date_dim AS r_e35c5c 
        ON r_4ecfa0.c_current_addr_sk <= r_e35c5c.d_same_day_ly 
WHERE
    r_981ce7.ib_upper_bound < r_e35c5c.d_fy_year 
    OR r_4ecfa0.c_email_address NOT LIKE r_4ecfa0.c_birth_country 
    OR r_4ecfa0.c_birth_country LIKE r_4ecfa0.c_login 
    OR r_e35c5c.d_current_year ILIKE 'vXU' 
    OR NOT r_e35c5c.d_current_quarter NOT LIKE 'x2S' 
ORDER BY
    1 DESC, 2, 3 NULLS FIRST;
----------->
SELECT
    r_092bf8.upn_cbdc10 as te_403beb,
    r_90af0b.d_quarter_name as te_70a027,
    ascii(r_092bf8.upn_cbdc10) as te_dbf931 
FROM
    db1.date_dim AS r_90af0b 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.customer_address UNPIVOT EXCLUDE NULLS ((up_c01a31,
            up_3da17f,
            up_bfefb2,
            up_90b04e) FOR upn_cbdc10 IN ((ca_address_sk,
            ca_gmt_offset,
            ca_country,
            ca_street_type) AS UPF_83f3b6))
    ) AS r_092bf8 
        ON r_90af0b.d_current_week NOT ILIKE r_092bf8.up_90b04e,
    db1.reason AS r_146ac3 
LEFT JOIN
    db1.store_returns AS r_e66a88 
        ON r_146ac3.r_reason_sk <= r_e66a88.sr_net_loss 
WHERE
    r_90af0b.d_dom < r_146ac3.r_reason_sk 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_e5412f.sr_cdemo_sk as te_f44dfb 
FROM
    db1.store_returns AS r_e5412f 
WHERE
    r_e5412f.sr_reason_sk != r_e5412f.sr_return_time_sk 
    OR NOT r_e5412f.sr_store_sk = 78 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 51 OFFSET 29;
----------->
SELECT
    r_2f3389.ca_street_name as te_145326,
    double(r_a6a5ad.ib_lower_bound) as te_3cf304,
    r_2f3389.ca_state as te_36230d 
FROM
    db1.customer_address AS r_2f3389 
RIGHT JOIN
    db1.household_demographics AS r_31a97a 
        ON r_2f3389.ca_gmt_offset = r_31a97a.hd_vehicle_count,
    db1.reason AS r_7259f2 
RIGHT JOIN
    db1.income_band AS r_a6a5ad 
        ON r_7259f2.r_reason_sk >= r_a6a5ad.ib_lower_bound 
WHERE
    r_2f3389.ca_address_sk < r_a6a5ad.ib_income_band_sk 
    AND (
        NOT r_2f3389.ca_country LIKE r_7259f2.r_reason_id 
        OR r_31a97a.hd_dep_count != 3
    ) 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_4771ba.i_class as te_4e8ba6,
    r_4771ba.i_class_id as te_29a05c,
    btrim(r_4771ba.i_item_id) as te_ea17b4 
FROM
    db1.store_returns AS r_e6161d 
LEFT JOIN
    db1.store_returns AS r_8542b8 
        ON r_e6161d.web_county = r_8542b8.sr_return_amt_inc_tax,
    db1.income_band AS r_555ac0 
INNER JOIN
    db1.item AS r_4771ba 
        ON r_555ac0.ib_income_band_sk <= r_4771ba.i_manufact_id 
WHERE
    r_e6161d.sr_reason_sk >= r_4771ba.i_category_id 
    AND r_4771ba.i_class ILIKE r_4771ba.i_size 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_2a411f.te_6b8ca9 as te_f7a621 
FROM
    (SELECT
        r_823288.r_reason_desc as te_6b8ca9,
        r_c44cfb.t_second as te_4c79e3,
        current_timestamp() as te_48a480 
    FROM
        db1.time_dim AS r_c44cfb 
    INNER JOIN
        db1.reason AS r_823288 
            ON r_c44cfb.t_minute >= r_823288.r_reason_sk,
        db1.customer AS r_3c7cd7 
    INNER JOIN
        db1.time_dim AS r_84829e 
            ON r_3c7cd7.c_salutation ILIKE r_84829e.t_sub_shift 
    WHERE
        r_c44cfb.t_meal_time ILIKE r_3c7cd7.c_salutation 
        OR r_3c7cd7.c_customer_id NOT ILIKE r_3c7cd7.c_preferred_cust_flag 
        OR r_3c7cd7.c_preferred_cust_flag ILIKE r_3c7cd7.c_customer_id 
        AND r_c44cfb.t_shift NOT LIKE r_84829e.t_sub_shift 
        AND r_84829e.t_minute != 47 
    ORDER BY
        1 NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST) AS r_2a411f 
WHERE
    r_2a411f.te_6b8ca9 NOT LIKE 'G' 
    AND r_2a411f.te_4c79e3 <= 73 
ORDER BY
    1;
----------->
SELECT
    current_timestamp() as te_6ba434,
    r_c4d472.cc_tax_percentage as te_156c9f 
FROM
    db1.call_center AS r_c4d472,
    db1.store AS r_cb19b5 
WHERE
    (
        NOT r_c4d472.cc_company = r_cb19b5.s_store_sk 
        AND r_cb19b5.s_tax_percentage != r_c4d472.cc_gmt_offset
    ) 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_eda3f5.te_addc9b as te_0a7627 
FROM
    (SELECT
        r_a30047.cc_rec_end_date as te_d1c7f7,
        r_a30047.cc_tax_percentage as te_56cae2,
        year(r_a30047.cc_rec_start_date) as te_addc9b 
    FROM
        db1.customer AS r_0d6cb0,
        db1.call_center AS r_a30047 
    WHERE
        r_0d6cb0.c_customer_id NOT ILIKE r_a30047.cc_call_center_id 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC) AS r_eda3f5 
WHERE
    r_eda3f5.te_56cae2 BETWEEN 1.57686681 AND 1.57686681 
    AND r_eda3f5.te_56cae2 BETWEEN 19.64790371 AND 19.64790371 
ORDER BY
    1 DESC 
LIMIT 64;
----------->
SELECT
    r_d32e08.sr_cdemo_sk as te_8e7f59,
    r_2a4cfb.hd_dep_count as te_485f15 
FROM
    db1.store_returns AS r_d32e08,
    db1.household_demographics AS r_2a4cfb 
RIGHT JOIN
    db1.income_band AS r_24f969 
        ON r_2a4cfb.hd_income_band_sk < r_24f969.ib_upper_bound 
WHERE
    r_d32e08.sr_store_sk = r_2a4cfb.hd_demo_sk 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
WITH CTE_860e0f(te_b8de29) AS (SELECT
    r_76a8b1.wp_rec_end_date as te_b8de29 
FROM
    db1.web_page AS r_76a8b1 
ORDER BY
    1 DESC NULLS LAST), CTE_c9cb69(te_49305c, te_c36d47) AS (SELECT
    r_ad4b64.cd_dep_count as te_49305c, r_ae03c1.cc_gmt_offset as te_c36d47 
FROM
    db1.call_center AS r_ae03c1 
RIGHT JOIN
    db1.customer_demographics AS r_ad4b64 
        ON r_ae03c1.cc_class NOT ILIKE r_ad4b64.cd_credit_rating,
    (SELECT
        r_658c04.te_71ee60 as te_16c922 
    FROM
        (SELECT
            r_9c7c6a.cd_dep_employed_count as te_71ee60 
        FROM
            db1.customer_address AS r_3e73c1 
        INNER JOIN
            db1.customer_demographics AS r_9c7c6a 
                ON r_3e73c1.ca_street_type NOT ILIKE r_9c7c6a.cd_credit_rating 
        ORDER BY
            1 DESC NULLS FIRST) AS r_658c04 
    WHERE
        (NOT r_658c04.te_71ee60 >= 54 
        AND r_658c04.te_71ee60 >= r_658c04.te_71ee60 
        OR NOT r_658c04.te_71ee60 = 26) 
    ORDER BY
        1 DESC NULLS FIRST) AS r_d6ccf9 
    WHERE
        r_ad4b64.cd_dep_count != r_d6ccf9.te_16c922 
        AND r_ad4b64.cd_credit_rating LIKE r_ad4b64.cd_gender) SELECT
        r_46b47f.wp_type as te_5b98e3 
    FROM
        CTE_c9cb69 AS r_65c551 
    LEFT JOIN
        db1.web_page AS r_46b47f 
            ON r_65c551.te_49305c <= r_46b47f.wp_web_page_sk 
    WHERE
        r_46b47f.wp_image_count <= 17 
    ORDER BY
        1 DESC NULLS LAST 
    OFFSET 79;
----------->
SELECT
    r_aa0b38.c_last_name as te_dbf3dd,
    r_f4a5dc.c_customer_id as te_b135f0,
    r_aa0b38.c_customer_id as te_90aecc 
FROM
    db1.ship_mode AS r_f7f227,
    db1.customer AS r_f4a5dc 
LEFT JOIN
    db1.customer AS r_aa0b38 
        ON r_f4a5dc.c_first_shipto_date_sk BETWEEN 14 AND 14 
WHERE
    r_f7f227.sm_ship_mode_sk != r_aa0b38.c_birth_day 
    AND r_f7f227.sm_carrier ILIKE 'z2kGk' 
ORDER BY
    1 ASC NULLS LAST, 2 ASC, 3 NULLS LAST;
----------->
SELECT
    r_e7fef7.sm_carrier as te_e30577 
FROM
    db1.ship_mode AS r_e7fef7 
INNER JOIN
    (
        SELECT
            r_6d618c.c_birth_year as te_a3d005 
        FROM
            db1.item AS r_0adcbd 
        RIGHT JOIN
            db1.customer AS r_6d618c 
                ON r_0adcbd.i_product_name NOT ILIKE r_6d618c.c_email_address 
        WHERE
            r_0adcbd.i_category NOT LIKE r_6d618c.c_email_address 
            OR r_6d618c.c_salutation ILIKE 'g' 
        ORDER BY
            1 ASC 
        LIMIT 22
) AS r_f22640 
    ON r_e7fef7.sm_ship_mode_sk != r_f22640.te_a3d005 WHERE
        r_f22640.te_a3d005 IS NOT NULL 
        OR r_f22640.te_a3d005 > r_e7fef7.sm_ship_mode_sk 
        AND r_e7fef7.sm_carrier ILIKE 'C0le' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_9eab1c.te_39af6d as te_2cb4f3 
FROM
    (SELECT
        sin(r_d103d2.i_manager_id) as te_39af6d 
    FROM
        db1.web_site AS r_04649a 
    LEFT JOIN
        db1.item AS r_d103d2 
            ON r_04649a.web_gmt_offset = r_d103d2.i_current_price 
    WHERE
        r_d103d2.i_current_price >= r_04649a.web_tax_percentage 
    ORDER BY
        1 DESC) AS r_9eab1c 
WHERE
    r_9eab1c.te_39af6d = r_9eab1c.te_39af6d 
    AND r_9eab1c.te_39af6d != 32.1154926D 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_f8e7ec.r_reason_id as te_8ab3ab 
FROM
    db1.reason AS r_f8e7ec 
RIGHT JOIN
    db1.customer_demographics AS r_e2aa87 
        ON r_f8e7ec.r_reason_sk > r_e2aa87.cd_dep_employed_count 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    make_date(14,
    r_23fdfb.r_reason_sk,
    r_23fdfb.r_reason_sk) as te_ce3056,
    r_23fdfb.r_reason_sk as te_fe36ed 
FROM
    (WITH CTE_9d347c(te_2a0c0f,
    te_e412e4,
    te_8fd1ad) AS (SELECT
        r_cd7d79.i_item_id as te_2a0c0f,
        current_date() as te_e412e4,
        r_cd7d79.i_item_desc || r_6892fd.w_county as te_8fd1ad 
    FROM
        db1.warehouse AS r_6892fd 
    RIGHT JOIN
        db1.item AS r_cd7d79 
            ON r_6892fd.w_country NOT LIKE r_cd7d79.i_brand,
        db1.household_demographics AS r_be7e0a 
    WHERE
        r_cd7d79.i_class_id >= r_be7e0a.hd_demo_sk 
        OR r_cd7d79.i_rec_start_date < r_cd7d79.i_rec_end_date 
        OR r_6892fd.w_gmt_offset <= r_cd7d79.i_current_price 
        AND r_6892fd.w_warehouse_sk < 10 
        OR r_cd7d79.i_class_id <= 8 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 NULLS LAST), CTE_38ee58(te_f2c3c6) AS (SELECT
        r_b5ee21.sm_carrier as te_f2c3c6 
    FROM
        db1.ship_mode AS r_b5ee21 
    WHERE
        r_b5ee21.sm_ship_mode_id NOT LIKE 'G7ir98hv' 
        AND r_b5ee21.sm_code ILIKE 'x6kg7x2S' 
    ORDER BY
        1 DESC) SELECT
        r_fae7a3.up_41812f as te_6093dc, r_6b1eb9.sr_return_ship_cost as te_c0cc65, 15.97695542D - r_6b1eb9.sr_reason_sk as te_208823 
    FROM
        db1.store_returns AS r_6b1eb9,
        (SELECT
            * 
        FROM
            CTE_9d347c UNPIVOT INCLUDE NULLS ((up_3a8ab7,
            up_41812f,
            up_1b6f73) FOR upn_ed05d4 IN ((te_8fd1ad,
            te_2a0c0f,
            te_e412e4) AS UPF_bb1acb))) AS r_fae7a3 
    WHERE
        date_from_unix_date(hash(r_6b1eb9.sr_net_loss)) >= date_from_unix_date(hash(string(r_fae7a3.up_1b6f73))) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC) AS r_ebb46a, db1.reason AS r_23fdfb 
    WHERE
        r_ebb46a.te_c0cc65 = decimal(r_23fdfb.r_reason_sk) 
        OR r_23fdfb.r_reason_desc NOT LIKE r_23fdfb.r_reason_id 
    ORDER BY
        1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_f5b708.ib_lower_bound as te_093391 
FROM
    db1.income_band AS r_f5b708 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_6118aa.d_moy * 50.47280071D + 11.71659307D + r_6118aa.d_fy_year as te_0cbe9d 
FROM
    db1.time_dim AS r_947c57 
LEFT JOIN
    db1.date_dim AS r_6118aa 
        ON r_947c57.t_meal_time LIKE r_6118aa.d_holiday 
WHERE
    r_947c57.t_time_id != 'Qiizz2' 
    OR r_6118aa.d_fy_quarter_seq BETWEEN 3 AND 3 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 61;
----------->
SELECT
    r_287da0.s_market_manager as te_7e3c7e 
FROM
    db1.store AS r_287da0 
WHERE
    r_287da0.s_geography_class NOT ILIKE 'QFrTU' 
    OR r_287da0.s_rec_start_date BETWEEN r_287da0.s_rec_end_date AND DATE'2024-03-25' 
    OR r_287da0.s_state NOT ILIKE r_287da0.s_street_type 
ORDER BY
    1 NULLS FIRST 
OFFSET 99;
----------->
SELECT
    r_1d3e61.cd_marital_status as te_30fe2c 
FROM
    db1.customer_demographics AS r_1d3e61 
WHERE
    r_1d3e61.cd_dep_college_count = r_1d3e61.cd_dep_employed_count 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_894f29.ca_gmt_offset as te_529aa1,
    now() as te_5d84c6 
FROM
    db1.web_site AS r_d44313 
RIGHT JOIN
    db1.customer_address AS r_894f29 
        ON r_d44313.web_street_name NOT LIKE r_894f29.ca_country,
    db1.reason AS r_7ba8ea 
WHERE
    r_d44313.web_site_sk >= r_7ba8ea.r_reason_sk 
    AND r_d44313.web_rec_end_date != r_d44313.web_rec_start_date 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC 
LIMIT 34 OFFSET 8;
----------->
SELECT
    r_60e062.i_rec_start_date as te_cc74bf 
FROM
    db1.item AS r_60e062 
INNER JOIN
    db1.time_dim AS r_e92c4e 
        ON r_60e062.i_manufact_id >= r_e92c4e.t_time 
ORDER BY
    1 DESC;
----------->
SELECT
    r_389102.d_day_name as te_aaa0fe,
    r_5c0ce5.i_size as te_34f5e3,
    hash(r_389102.d_first_dom + 46,
    timestamp_millis(r_5c0ce5.i_item_sk)) as te_231f7f 
FROM
    db1.item AS r_5c0ce5,
    db1.date_dim AS r_389102 
WHERE
    r_5c0ce5.i_rec_end_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
    AND r_5c0ce5.i_current_price >= r_5c0ce5.i_wholesale_cost 
    OR r_5c0ce5.i_rec_end_date >= r_5c0ce5.i_rec_start_date 
    OR r_5c0ce5.i_units NOT LIKE 'bBRGC' 
    AND r_389102.d_quarter_name NOT ILIKE '7RX' 
ORDER BY
    1 NULLS LAST, 2, 3 DESC NULLS LAST;
----------->
SELECT
    r_3926a5.te_9efa98 as te_06d9cb 
FROM
    (SELECT
        r_cd66f2.ca_address_sk + r_cd66f2.ca_gmt_offset as te_9efa98 
    FROM
        db1.customer_address AS r_cd66f2 
    WHERE
        r_cd66f2.ca_city ILIKE 'RGC' 
        OR r_cd66f2.ca_gmt_offset BETWEEN r_cd66f2.ca_gmt_offset AND 32.22548125 
    ORDER BY
        1 DESC NULLS FIRST) AS r_3926a5 
WHERE
    r_3926a5.te_9efa98 != r_3926a5.te_9efa98 
ORDER BY
    1 DESC;
----------->
SELECT
    sin(r_17e933.s_gmt_offset) as te_b5b086 
FROM
    db1.customer_demographics AS r_5068c3 
INNER JOIN
    db1.store AS r_17e933 
        ON r_5068c3.cd_gender LIKE r_17e933.s_store_id 
WHERE
    r_17e933.s_rec_start_date != r_17e933.s_rec_end_date 
ORDER BY
    1 DESC;
----------->
SELECT
    hash(r_71da80.sr_hdemo_sk) as te_b3d26c,
    r_f519b9.d_date as te_6d9537,
    decimal(r_982c26.cd_purchase_estimate) as te_8638e2 
FROM
    db1.customer_demographics AS r_982c26,
    db1.date_dim AS r_f519b9 
RIGHT JOIN
    db1.store_returns AS r_71da80 
        ON r_f519b9.d_fy_week_seq < r_71da80.sr_store_sk 
WHERE
    r_982c26.cd_dep_employed_count > r_71da80.sr_store_sk 
    AND (
        NOT r_982c26.cd_dep_count > 35 
        OR r_f519b9.d_current_month NOT ILIKE r_f519b9.d_date_id 
        AND r_71da80.sr_return_amt != r_71da80.sr_return_amt_inc_tax 
        AND r_71da80.sr_fee > 75.05391915
    ) 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 DESC NULLS LAST 
LIMIT 46;
----------->
SELECT
    r_ab8fb6.cc_rec_end_date as te_b6b8e1,
    r_ab8fb6.cc_manager as te_f907ea,
    r_ab8fb6.cc_sq_ft as te_dd0de8 
FROM
    (SELECT
        r_97565b.sr_addr_sk as te_b5d265 
    FROM
        db1.store_returns AS r_97565b 
    WHERE
        r_97565b.sr_hdemo_sk < 17 
        OR r_97565b.sr_store_sk > 19 
    ORDER BY
        1 NULLS FIRST 
    OFFSET 93) AS r_0d5d30 LEFT JOIN
    db1.call_center AS r_ab8fb6 
        ON r_0d5d30.te_b5d265 <= r_ab8fb6.cc_division,
    db1.income_band AS r_b615d4 
RIGHT JOIN
    db1.household_demographics AS r_9e9701 
        ON r_b615d4.ib_upper_bound >= r_9e9701.hd_vehicle_count 
WHERE
    r_ab8fb6.cc_company = r_b615d4.ib_lower_bound 
    AND r_ab8fb6.cc_hours ILIKE '98hv95G' 
ORDER BY
    1 NULLS LAST, 2, 3 DESC NULLS FIRST;
----------->
SELECT
    r_53f60d.sm_type as te_135a89 
FROM
    db1.ship_mode AS r_53f60d 
WHERE
    r_53f60d.sm_type ILIKE r_53f60d.sm_contract 
    AND r_53f60d.sm_ship_mode_id LIKE '2Gv7RXpRQi' 
ORDER BY
    1 DESC;
----------->
WITH CTE_523e18(te_c46fa6) AS (SELECT
    r_b9ec40.sr_return_quantity as te_c46fa6 
FROM
    db1.ship_mode AS r_a8edcc 
RIGHT JOIN
    db1.store_returns AS r_b9ec40 
        ON r_a8edcc.sm_ship_mode_sk >= r_b9ec40.sr_return_time_sk 
WHERE
    r_a8edcc.sm_code NOT LIKE r_a8edcc.sm_ship_mode_id 
ORDER BY
    1 NULLS LAST) (
    SELECT
        r_73bc2f.web_gmt_offset as te_17c1a6, r_3b50b6.te_48db86 as te_a95384, hash(r_73bc2f.web_tax_percentage, current_timestamp()) as te_f6aba8 
    FROM
        db1.income_band AS r_c60a0e,
        (SELECT
            r_3a1d2c.wp_web_page_id as te_48db86,
            r_3a1d2c.wp_char_count as te_6750ec,
            r_3a1d2c.wp_web_page_id as te_472546 
        FROM
            db1.ship_mode AS r_4961b4,
            db1.web_page AS r_3a1d2c 
        INNER JOIN
            db1.warehouse AS r_b83ccd 
                ON r_3a1d2c.wp_autogen_flag LIKE r_b83ccd.w_county 
        WHERE
            r_4961b4.sm_ship_mode_sk > r_3a1d2c.wp_link_count 
        GROUP BY
            3, 1, 2 
        ORDER BY
            1 NULLS LAST, 2 ASC NULLS LAST, 3 DESC) AS r_3b50b6 
    RIGHT JOIN
        db1.web_site AS r_73bc2f 
            ON r_3b50b6.te_6750ec != r_73bc2f.web_open_date_sk 
    WHERE
        r_c60a0e.ib_upper_bound >= r_73bc2f.web_gmt_offset 
        AND r_73bc2f.web_open_date_sk != 1 
        AND r_73bc2f.web_country NOT LIKE r_73bc2f.web_county 
        OR r_73bc2f.web_name NOT ILIKE 'lB' 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC, 3 NULLS FIRST) 
    UNION
    (
        SELECT
            decimal(r_008fde.d_year) as te_68d561,
            r_008fde.d_following_holiday as te_d294b3,
            r_b10e68.w_warehouse_sk * r_008fde.d_dom as te_2a75de 
        FROM
            db1.warehouse AS r_b10e68,
            db1.date_dim AS r_008fde 
        INNER JOIN
            db1.item AS r_179eb9 
                ON r_008fde.d_date > r_179eb9.i_rec_start_date 
        WHERE
            r_b10e68.w_street_number NOT ILIKE r_179eb9.i_item_desc 
        ORDER BY
            1 NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS FIRST
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC NULLS LAST 
OFFSET 6;
----------->
SELECT
    r_f0207c.cc_rec_start_date as te_f6f18a,
    hex(double(r_9dc902.te_fdcea0)) as te_95e80f 
FROM
    (SELECT
        chr(r_06a5e7.s_tax_percentage) as te_f147f4,
        r_8e690a.sr_fee as te_fdcea0 
    FROM
        db1.store_returns AS r_8e690a,
        db1.web_page AS r_9bdef7 
    INNER JOIN
        db1.store AS r_06a5e7 
            ON r_9bdef7.wp_autogen_flag LIKE r_06a5e7.s_store_id 
    WHERE
        r_8e690a.sr_reason_sk > r_06a5e7.s_number_employees) AS r_9dc902,
    db1.call_center AS r_f0207c 
WHERE
    r_9dc902.te_fdcea0 < r_f0207c.cc_tax_percentage 
    AND r_f0207c.cc_tax_percentage = 82.10465764 
    AND r_f0207c.cc_country ILIKE 'z2kG' 
GROUP BY
    2, 1 
ORDER BY
    1 ASC NULLS LAST, 2;
----------->
SELECT
    r_dc10ae.c_preferred_cust_flag as te_766aae,
    r_2f3134.wp_image_count as te_f29f20 
FROM
    db1.ship_mode AS r_ba8e6b 
LEFT JOIN
    db1.reason AS r_9bd839 
        ON r_ba8e6b.sm_ship_mode_sk = r_9bd839.r_reason_sk,
    db1.web_page AS r_2f3134 
INNER JOIN
    db1.customer AS r_dc10ae 
        ON r_2f3134.wp_access_date_sk < r_dc10ae.c_last_review_date_sk 
WHERE
    r_ba8e6b.sm_ship_mode_id NOT ILIKE r_2f3134.wp_type 
    OR r_dc10ae.c_customer_sk <= r_2f3134.wp_customer_sk 
    OR r_dc10ae.c_last_review_date_sk <= 20 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_4ca30f.wp_rec_start_date as te_21a7d1 
FROM
    db1.web_page AS r_4ca30f 
WHERE
    r_4ca30f.wp_rec_end_date BETWEEN DATE'2024-10-11' AND r_4ca30f.wp_rec_end_date 
    AND r_4ca30f.wp_type ILIKE r_4ca30f.wp_url 
    OR r_4ca30f.wp_image_count BETWEEN 67 AND r_4ca30f.wp_access_date_sk + 37 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    unix_timestamp() as te_dbc07b 
FROM
    db1.household_demographics AS r_f32e53 
LEFT JOIN
    db1.customer_address AS r_30685f 
        ON r_f32e53.hd_buy_potential NOT LIKE r_30685f.ca_street_name 
WHERE
    r_30685f.ca_zip NOT ILIKE 'G7i' 
    AND r_30685f.ca_gmt_offset >= 46.62766672 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_5411a8.s_division_name as te_06445f,
    r_5411a8.s_store_id as te_29061b 
FROM
    db1.store AS r_5411a8,
    (SELECT
        date_sub(current_date(),
        r_2dfc1b.w_warehouse_sk) as te_41d521,
        date_add(r_bfb7cd.s_rec_start_date,
        r_bfb7cd.s_number_employees) as te_334ec4 
    FROM
        db1.store AS r_bfb7cd 
    LEFT JOIN
        (
            SELECT
                current_timestamp() as te_9303cf,
                chr(r_4abf4e.s_store_sk) as te_dea792 
            FROM
                db1.web_site AS r_7310a7 
            RIGHT JOIN
                db1.customer AS r_7c66cf 
                    ON r_7310a7.web_company_id = r_7c66cf.c_birth_month,
                db1.store AS r_4abf4e 
            LEFT JOIN
                db1.customer_address AS r_65e490 
                    ON r_4abf4e.s_country NOT ILIKE r_65e490.ca_suite_number 
            WHERE
                r_7310a7.web_site_id NOT ILIKE r_65e490.ca_location_type 
                OR r_7310a7.web_rec_end_date >= r_4abf4e.s_rec_start_date 
            ORDER BY
                1 ASC NULLS LAST, 2 ASC NULLS FIRST
        ) AS r_b10e47 
            ON r_bfb7cd.s_store_id NOT LIKE r_b10e47.te_dea792,
        (SELECT
            r_e2d1c4.te_43f521 as te_8e3026, r_758f31.te_2df4d3 as te_625311, r_e2d1c4.te_43f521 as te_550eb6 
        FROM
            (SELECT
                r_e28ec8.hd_demo_sk * 95 + cos(r_76a3e9.ib_upper_bound) + r_5602cc.t_time + 85.37417007 + mod(r_e28ec8.hd_dep_count,
                81) as te_47c6e8,
                r_76a3e9.ib_upper_bound as te_6d90b2,
                unix_timestamp() as te_2df4d3 
            FROM
                db1.household_demographics AS r_e28ec8 
            RIGHT JOIN
                (
                    SELECT
                        timestamp_seconds(78 - r_597f80.web_open_date_sk) as te_d678f2,
                        r_19c2c7.cd_gender as te_640b63 
                    FROM
                        db1.web_site AS r_597f80,
                        db1.customer_demographics AS r_19c2c7 
                    WHERE
                        r_597f80.web_state ILIKE r_19c2c7.cd_marital_status 
                    GROUP BY
                        1, 2 
                    ORDER BY
                        1 NULLS LAST, 2
                ) AS r_71fe92 
                    ON chr(r_e28ec8.hd_income_band_sk) NOT LIKE r_71fe92.te_640b63,
                db1.time_dim AS r_5602cc 
            INNER JOIN
                db1.income_band AS r_76a3e9 
                    ON r_5602cc.t_hour BETWEEN 55 AND 55 
            WHERE
                r_e28ec8.hd_buy_potential LIKE r_5602cc.t_meal_time 
                OR r_5602cc.t_meal_time LIKE r_5602cc.t_time_id 
                AND r_5602cc.t_second >= 27 
            ORDER BY
                1 ASC, 2 NULLS LAST, 3 DESC NULLS LAST) AS r_758f31, (SELECT
                    r_b7809c.d_day_name as te_43f521 
                FROM
                    db1.income_band AS r_3c5911 
                RIGHT JOIN
                    db1.date_dim AS r_b7809c 
                        ON r_3c5911.ib_upper_bound > r_b7809c.d_week_seq 
                WHERE
                    r_b7809c.d_following_holiday NOT ILIKE '2G' 
                    OR r_b7809c.d_weekend LIKE '7RX' 
                    AND r_b7809c.d_same_day_lq = 48 
                ORDER BY
                    1 DESC NULLS FIRST 
                OFFSET 9) AS r_e2d1c4 WHERE
                chr(r_758f31.te_6d90b2) ILIKE r_e2d1c4.te_43f521 
                AND r_758f31.te_6d90b2 = 50 
                AND r_e2d1c4.te_43f521 NOT LIKE 'bB' 
            ORDER BY
                1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS LAST) AS r_8d6311 
            RIGHT JOIN
                db1.warehouse AS r_2dfc1b 
                    ON r_8d6311.te_625311 = r_2dfc1b.w_warehouse_sk 
            WHERE
                r_bfb7cd.s_market_id != r_2dfc1b.w_warehouse_sk) AS r_704ee8 
        RIGHT JOIN
            db1.warehouse AS r_672c88 
                ON chr(ascii(string(timestamp(r_704ee8.te_41d521)))) NOT LIKE btrim(r_672c88.w_county) 
        WHERE
            r_5411a8.s_rec_end_date < r_704ee8.te_41d521 
            OR r_672c88.w_street_type LIKE 'RGC0lezm' 
        ORDER BY
            1, 2 DESC NULLS FIRST 
        OFFSET 40;
----------->
WITH CTE_ee1899(te_69d239, te_97a260) AS (SELECT
    r_220b36.d_date as te_69d239,
    r_220b36.d_date as te_97a260 
FROM
    db1.store_returns AS r_39e654,
    db1.date_dim AS r_220b36 
WHERE
    r_39e654.sr_customer_sk != r_220b36.d_fy_week_seq 
ORDER BY
    1 ASC NULLS LAST, 2 DESC), CTE_dac95f(te_c45beb, te_309ccb, te_e9687c) AS (SELECT
    r_c4ead9.te_4954da as te_c45beb, r_c4ead9.te_4954da as te_309ccb, r_c4ead9.te_4954da as te_e9687c 
FROM
    (SELECT
        r_3b495d.s_division_id as te_e36258,
        make_timestamp(r_25e83f.cd_purchase_estimate,
        r_3b495d.s_number_employees,
        r_3b495d.s_company_id,
        r_25e83f.cd_demo_sk,
        r_25e83f.cd_demo_sk,
        r_25e83f.cd_dep_employed_count - 46.1399789D) as te_4954da,
        r_25e83f.cd_dep_employed_count as te_5686bb 
    FROM
        db1.store AS r_3b495d,
        db1.customer_demographics AS r_25e83f 
    WHERE
        r_3b495d.s_division_name ILIKE r_25e83f.cd_credit_rating 
        OR r_25e83f.cd_dep_count != 48 
        OR r_3b495d.s_market_desc ILIKE r_3b495d.s_store_name 
        OR r_25e83f.cd_gender <= 'tb' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS FIRST) AS r_c4ead9, db1.store_returns AS r_8a7b6b 
WHERE
    NOT r_c4ead9.te_e36258 <= r_8a7b6b.sr_reason_sk 
    AND r_8a7b6b.sr_hdemo_sk = r_8a7b6b.sr_reason_sk 
    AND r_8a7b6b.sr_customer_sk != 64 
    AND r_8a7b6b.web_county != 52.011452 
    AND r_8a7b6b.sr_hdemo_sk >= 89 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS FIRST, 3 DESC) SELECT
        timestamp(r_e7f992.te_69d239) as te_a207a6 
    FROM
        CTE_ee1899 AS r_e7f992 
    INNER JOIN
        (
            SELECT
                r_19e27c.cd_demo_sk as te_b8f0c9 
            FROM
                db1.customer_demographics AS r_19e27c 
            INNER JOIN
                (
                    SELECT
                        r_aa265d.d_current_month as te_2badc4,
                        r_2900e6.i_wholesale_cost as te_930e1e,
                        r_2900e6.i_current_price as te_421977 
                    FROM
                        db1.item AS r_2900e6 
                    LEFT JOIN
                        db1.date_dim AS r_aa265d 
                            ON r_2900e6.i_rec_end_date >= r_aa265d.d_date,
                        db1.warehouse AS r_fc949b 
                    LEFT JOIN
                        (
                            SELECT
                                * 
                            FROM
                                db1.household_demographics PIVOT(min(hd_buy_potential) AS pa_e3f920 FOR hd_demo_sk IN ((76) AS pf_7eaa6f,
                                (16) AS pf_851cee,
                                (32) AS pf_aafce8))
                        ) AS r_c37ffc 
                            ON r_fc949b.w_warehouse_sk != r_c37ffc.hd_dep_count 
                    WHERE
                        r_2900e6.i_current_price < r_fc949b.w_gmt_offset 
                        OR r_2900e6.i_class_id != 35 
                        AND r_2900e6.i_manager_id <= r_aa265d.d_qoy 
                        AND r_fc949b.w_warehouse_name NOT LIKE 'kGk4elBy7' 
                        OR r_fc949b.w_street_type NOT LIKE r_2900e6.i_units 
                    ORDER BY
                        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST) AS r_23e852 
                            ON r_19e27c.cd_credit_rating = r_23e852.te_2badc4 
                    WHERE
                        NOT EXISTS (
                            SELECT
                                r_4e20a4.w_gmt_offset + r_4e20a4.w_warehouse_sk as te_b16930 
                            FROM
                                db1.warehouse AS r_4e20a4 
                            RIGHT JOIN
                                db1.reason AS r_b3bd28 
                                    ON r_4e20a4.w_warehouse_sk = r_b3bd28.r_reason_sk 
                            WHERE
                                (
                                    NOT r_4e20a4.w_street_number <= 'MaLQF' 
                                    AND NOT r_4e20a4.w_city NOT LIKE '7i'
                                ) 
                                AND r_4e20a4.w_street_name NOT IN (
                                    SELECT
                                        r_00f095.w_country as te_f1b79a 
                                    FROM
                                        db1.warehouse AS r_27a72c,
                                        (SELECT
                                            * 
                                        FROM
                                            db1.warehouse PIVOT(stddev(w_warehouse_sk) AS pa_cb6a2f FOR w_suite_number IN (('6sU') AS pf_dad11d,
                                            ('G6s') AS pf_8d8d05,
                                            ('pRQiizz2kG') AS pf_34cb39,
                                            ('IH') AS pf_e37c79,
                                            ('7RX') AS pf_080c08))) AS r_00f095 
                                    INNER JOIN
                                        db1.reason AS r_8181a7 
                                            ON r_00f095.w_city NOT ILIKE r_8181a7.r_reason_id 
                                    WHERE
                                        r_27a72c.w_warehouse_name NOT LIKE r_00f095.w_street_type 
                                        AND r_00f095.w_warehouse_sq_ft < r_8181a7.r_reason_sk 
                                        OR r_27a72c.w_warehouse_id LIKE r_27a72c.w_city 
                                    ORDER BY
                                        1 DESC NULLS LAST) 
                                        AND r_4e20a4.w_street_type ILIKE r_b3bd28.r_reason_id
                                ) 
                                OR r_19e27c.cd_marital_status ILIKE '7' 
                            ORDER BY
                                1 NULLS FIRST) AS r_967554 
                                    ON r_e7f992.te_97a260 < date_from_unix_date(r_967554.te_b8f0c9) 
                            WHERE
                                r_967554.te_b8f0c9 = 86 
                            ORDER BY
                                1 ASC NULLS LAST;
----------->
SELECT
    r_91f89c.i_wholesale_cost as te_ee4d3f 
FROM
    db1.item AS r_91f89c 
LEFT JOIN
    db1.web_site AS r_5eef54 
        ON r_91f89c.i_rec_start_date < r_5eef54.web_rec_start_date 
WHERE
    r_5eef54.web_site_id NOT ILIKE r_5eef54.web_state 
    OR (
        NOT r_91f89c.i_manufact NOT LIKE '8hv95' 
        OR r_91f89c.i_category_id = r_5eef54.web_company_id 
        OR r_5eef54.web_zip NOT LIKE 'u'
    ) 
ORDER BY
    1 DESC NULLS FIRST 
LIMIT 1;
----------->
SELECT
    r_4df93f.t_hour as te_94a55d 
FROM
    db1.household_demographics AS r_4f12b1 
LEFT JOIN
    db1.time_dim AS r_4df93f 
        ON r_4f12b1.hd_demo_sk != r_4df93f.t_minute 
ORDER BY
    1 NULLS FIRST;
----------->
WITH CTE_f6ddad(te_5b6f06) AS (SELECT
    r_301f4c.s_tax_percentage as te_5b6f06 
FROM
    db1.store AS r_301f4c 
WHERE
    r_301f4c.s_store_sk != r_301f4c.s_market_id 
ORDER BY
    1 DESC NULLS FIRST) SELECT
    r_6fff60.r_reason_desc as te_5159f9, r_6fff60.r_reason_sk as te_924949, r_6fff60.r_reason_sk as te_b7390f 
FROM
    CTE_f6ddad AS r_0cc707,
    db1.reason AS r_6fff60 
WHERE
    chr(r_0cc707.te_5b6f06) LIKE r_6fff60.r_reason_id 
    AND r_6fff60.r_reason_desc NOT ILIKE 'zz2kGk4el' 
    OR r_6fff60.r_reason_desc NOT ILIKE r_6fff60.r_reason_id 
ORDER BY
    1, 2 DESC, 3 DESC NULLS FIRST;
----------->
SELECT
    try_add(r_0dc0c1.d_date,
    r_0dc0c1.d_qoy) as te_2b3da9 
FROM
    db1.date_dim AS r_0dc0c1 
WHERE
    r_0dc0c1.d_dom != 8 
    AND r_0dc0c1.d_date < DATE'2024-10-11' 
    AND r_0dc0c1.d_quarter_seq <= 7 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_5e6bb5.sr_return_tax + r_f1e8d6.sr_return_amt as te_c0cfb8,
    r_6274a1.t_shift as te_4b7202,
    r_e46a80.web_site_id as te_46c69b 
FROM
    db1.time_dim AS r_6274a1 
RIGHT JOIN
    db1.web_site AS r_e46a80 
        ON r_6274a1.t_second != r_e46a80.web_open_date_sk,
    db1.store_returns AS r_5e6bb5 
RIGHT JOIN
    db1.store_returns AS r_f1e8d6 
        ON r_5e6bb5.sr_fee >= r_f1e8d6.sr_return_tax 
WHERE
    r_e46a80.web_open_date_sk = r_f1e8d6.sr_return_amt 
    AND r_e46a80.web_site_id NOT ILIKE r_e46a80.web_state 
    AND r_e46a80.web_company_name >= '7ir9' 
    AND r_e46a80.web_site_id NOT LIKE 'X4QH' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_542bbb.cc_city || r_d6f9b1.cc_call_center_id as te_ff2035,
    r_542bbb.cc_tax_percentage as te_40651f,
    r_542bbb.cc_gmt_offset as te_1da46a 
FROM
    db1.call_center AS r_542bbb,
    db1.call_center AS r_d6f9b1 
LEFT JOIN
    (
        SELECT
            r_35c8c0.s_gmt_offset as te_786443,
            r_35c8c0.s_street_type as te_715a90 
        FROM
            db1.customer_address AS r_2b40bf 
        LEFT JOIN
            db1.customer_address AS r_0e8cce 
                ON r_2b40bf.ca_country NOT ILIKE r_0e8cce.ca_street_type,
            db1.store AS r_35c8c0 
        WHERE
            r_0e8cce.ca_address_id NOT ILIKE r_35c8c0.s_store_id 
            OR r_0e8cce.ca_city NOT ILIKE 'X4' 
            AND r_2b40bf.ca_suite_number != 'C0l' 
            AND r_35c8c0.s_gmt_offset = r_35c8c0.s_tax_percentage 
            OR r_35c8c0.s_geography_class NOT ILIKE 'ulstntb2' 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS FIRST
    ) AS r_20f96f 
        ON r_d6f9b1.cc_manager NOT ILIKE r_20f96f.te_715a90 
WHERE
    r_542bbb.cc_street_name NOT LIKE r_d6f9b1.cc_name 
    AND r_d6f9b1.cc_city < 'C0lezmSRI' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_63f637.d_qoy as te_370843 
FROM
    db1.date_dim AS r_63f637 
ORDER BY
    1 ASC;
----------->
SELECT
    r_548486.i_units as te_e2fc2d,
    r_a25517.s_hours as te_abf501,
    hash(r_548486.i_rec_end_date,
    true) as te_834a45 
FROM
    db1.store AS r_a25517,
    db1.item AS r_548486 
WHERE
    r_a25517.s_rec_end_date < r_548486.i_rec_end_date 
    OR NOT r_548486.i_product_name NOT LIKE '2Gv7R' 
    AND r_548486.i_item_id LIKE r_a25517.s_store_id 
    OR (
        NOT r_a25517.s_street_name LIKE r_a25517.s_market_manager 
        AND r_a25517.s_rec_end_date <= DATE'2024-10-11'
    ) 
ORDER BY
    1 DESC, 2 ASC NULLS FIRST, 3 ASC NULLS LAST;
----------->
SELECT
    r_f57736.w_county as te_a0734d,
    r_42993b.w_county as te_ca47a6 
FROM
    db1.store_returns AS r_dabda6 
LEFT JOIN
    db1.ship_mode AS r_cf17c1 
        ON r_dabda6.sr_addr_sk > r_cf17c1.sm_ship_mode_sk,
    db1.warehouse AS r_f57736 
INNER JOIN
    db1.warehouse AS r_42993b 
        ON r_f57736.w_country NOT LIKE r_42993b.w_warehouse_name 
WHERE
    r_cf17c1.sm_code NOT ILIKE r_f57736.w_city 
    AND r_42993b.w_county NOT LIKE r_f57736.w_county 
    AND r_dabda6.sr_fee != r_f57736.w_gmt_offset 
    OR r_dabda6.sr_return_quantity <= r_dabda6.sr_return_time_sk 
    OR r_f57736.w_warehouse_id NOT ILIKE r_42993b.w_warehouse_id 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_bdbc41.wp_type || r_22909a.d_day_name as te_7a25dd,
    r_bdbc41.wp_char_count as te_148d01,
    r_22909a.d_quarter_name as te_e37eef 
FROM
    db1.date_dim AS r_22909a 
INNER JOIN
    db1.web_page AS r_bdbc41 
        ON r_22909a.d_fy_quarter_seq != r_bdbc41.wp_link_count,
    db1.store_returns AS r_7e037f 
LEFT JOIN
    (
        SELECT
            r_06c98a.w_city as te_0c2eb0,
            r_c5facd.d_date as te_d275f6 
        FROM
            db1.warehouse AS r_06c98a 
        INNER JOIN
            db1.customer_demographics AS r_a99b3e 
                ON r_06c98a.w_gmt_offset < r_a99b3e.cd_purchase_estimate,
            db1.date_dim AS r_c5facd 
        WHERE
            r_a99b3e.cd_dep_count <= r_c5facd.d_year 
        ORDER BY
            1 NULLS LAST, 2 ASC NULLS LAST
    ) AS r_746e59 
        ON chr(r_7e037f.web_county) >= r_746e59.te_0c2eb0 
WHERE
    r_bdbc41.wp_rec_start_date != r_746e59.te_d275f6 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_3ee9f6.s_rec_start_date as te_a14aed 
FROM
    db1.store AS r_3ee9f6 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_2d8683.d_date as te_27282c,
    r_2d8683.d_day_name as te_4dfe68,
    r_2d8683.d_day_name as te_6140f9 
FROM
    db1.ship_mode AS r_280f72,
    db1.date_dim AS r_2d8683 
WHERE
    r_280f72.sm_ship_mode_sk != r_2d8683.d_week_seq 
    OR r_280f72.sm_carrier NOT ILIKE r_280f72.sm_ship_mode_id 
    OR r_2d8683.d_holiday ILIKE 'QHZgG' 
    OR r_2d8683.d_holiday ILIKE r_2d8683.d_date_id 
    OR r_2d8683.d_weekend ILIKE r_2d8683.d_date_id 
ORDER BY
    1 ASC, 2 NULLS FIRST, 3 
OFFSET 95;
----------->
SELECT
    r_70fd90.ca_location_type as te_260420,
    reflect('java.util.UUID',
    'randomUUID') as te_c419a4,
    r_70fd90.ca_county as te_15b26d 
FROM
    db1.call_center AS r_2534b3 
INNER JOIN
    db1.warehouse AS r_7c9cce 
        ON r_2534b3.cc_mkt_id < r_7c9cce.w_warehouse_sq_ft,
    db1.customer_address AS r_70fd90 
WHERE
    r_2534b3.cc_company != r_70fd90.ca_address_sk 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_22e7db.w_street_name as te_237aa4,
    r_0f14d9.sr_cdemo_sk as te_255f7f 
FROM
    db1.warehouse AS r_22e7db 
LEFT JOIN
    (
        SELECT
            r_d49f52.cc_call_center_sk as te_17ebca 
        FROM
            db1.call_center AS r_d49f52 
        WHERE
            r_d49f52.cc_city NOT ILIKE 'GIHlpx6' 
            AND r_d49f52.cc_rec_end_date < r_d49f52.cc_rec_start_date 
        ORDER BY
            1 DESC NULLS LAST 
        OFFSET 59
) AS r_307883 
    ON r_22e7db.w_gmt_offset <= r_307883.te_17ebca,
db1.store_returns AS r_0f14d9 LEFT JOIN
    db1.customer AS r_11d3a4 
        ON r_0f14d9.sr_reason_sk > r_11d3a4.c_current_addr_sk 
WHERE
    r_22e7db.w_warehouse_sk >= r_11d3a4.c_current_hdemo_sk 
ORDER BY
    1 NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    unix_timestamp() as te_fe7655 
FROM
    db1.web_page AS r_2606be 
INNER JOIN
    db1.time_dim AS r_8b4d18 
        ON r_2606be.wp_url ILIKE r_8b4d18.t_sub_shift 
WHERE
    r_2606be.wp_creation_date_sk >= 58 
    AND r_8b4d18.t_sub_shift NOT ILIKE 'rT' 
    AND r_2606be.wp_rec_end_date = r_2606be.wp_rec_start_date 
    OR r_8b4d18.t_meal_time LIKE 'k' 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_042cd3.c_preferred_cust_flag as te_94e6a1,
    r_12f66d.s_geography_class as te_3ba2e6 
FROM
    db1.store_returns AS r_cf8c2a,
    db1.store AS r_12f66d 
LEFT JOIN
    db1.customer AS r_042cd3 
        ON r_12f66d.s_store_sk <= r_042cd3.c_customer_sk 
WHERE
    r_cf8c2a.sr_addr_sk >= r_12f66d.s_division_id 
    OR r_cf8c2a.sr_net_loss < 99.66934271 
    OR r_042cd3.c_current_hdemo_sk <= 18 
    OR r_042cd3.c_first_name NOT LIKE 'v7RXp' 
ORDER BY
    1 ASC, 2 DESC;
----------->
SELECT
    r_e15607.s_rec_end_date as te_537834,
    r_985efd.ca_street_type as te_dd8f34 
FROM
    db1.time_dim AS r_73669b 
LEFT JOIN
    db1.store AS r_e15607 
        ON r_73669b.t_meal_time NOT LIKE r_e15607.s_manager,
    db1.customer_address AS r_985efd 
WHERE
    r_e15607.s_tax_percentage > r_985efd.ca_gmt_offset 
    AND r_e15607.s_rec_start_date < r_e15607.s_rec_end_date 
    AND r_e15607.s_hours ILIKE r_e15607.s_division_name 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST;
----------->
WITH CTE_becf03(te_1eb3f7) AS (WITH CTE_01a84a(te_91bc56) AS (WITH CTE_9a8ab6(te_7566e0) AS (SELECT
    reverse(r_b4057e.w_county) as te_7566e0 
FROM
    db1.warehouse AS r_b4057e 
WHERE
    NOT r_b4057e.w_warehouse_sk >= r_b4057e.w_warehouse_sq_ft 
    OR r_b4057e.w_warehouse_sq_ft > r_b4057e.w_warehouse_sk 
    OR r_b4057e.w_warehouse_sk != 61 
ORDER BY
    1 DESC NULLS LAST) SELECT
    r_fbb512.te_7566e0 as te_91bc56 
FROM
    CTE_9a8ab6 AS r_fbb512 
WHERE
    r_fbb512.te_7566e0 NOT LIKE 's' 
ORDER BY
    1 ASC NULLS LAST), CTE_be00cc(te_24ac18) AS (SELECT
    r_be8fb2.wp_web_page_id as te_24ac18 
FROM
    db1.web_page AS r_be8fb2 
WHERE
    r_be8fb2.wp_char_count > r_be8fb2.wp_access_date_sk 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 48) SELECT
length('VbBR' || 'b2G') as te_1eb3f7 FROM
    CTE_01a84a AS r_f0c6a4 
WHERE
    r_f0c6a4.te_91bc56 >= 'b2' 
    OR NOT r_f0c6a4.te_91bc56 LIKE 'UO' 
ORDER BY
    1 DESC NULLS LAST), CTE_78edfa(te_9bdd68) AS (SELECT
    r_ea1808.hd_buy_potential as te_9bdd68 
FROM
    db1.household_demographics AS r_ea1808 
WHERE
    r_ea1808.hd_vehicle_count >= 79 
    OR r_ea1808.hd_dep_count != 1 
    OR r_ea1808.hd_income_band_sk = 75 
ORDER BY
    1 DESC) SELECT
    r_582870.te_cb0b61 as te_b25ac5, r_4e3b55.te_9bdd68 as te_b44751 
FROM
    CTE_becf03 AS r_5848d9,
    (SELECT
        r_ab4d25.hd_income_band_sk as te_cb0b61 
    FROM
        db1.household_demographics AS r_ab4d25 
    WHERE
        r_ab4d25.hd_demo_sk > r_ab4d25.hd_income_band_sk 
        AND r_ab4d25.hd_buy_potential NOT ILIKE '0' 
        AND r_ab4d25.hd_vehicle_count > r_ab4d25.hd_income_band_sk 
    ORDER BY
        1 DESC NULLS LAST) AS r_582870 
RIGHT JOIN
    CTE_78edfa AS r_4e3b55 
        ON chr(r_582870.te_cb0b61) != r_4e3b55.te_9bdd68 
WHERE
    r_5848d9.te_1eb3f7 != r_582870.te_cb0b61 
    AND r_582870.te_cb0b61 < r_5848d9.te_1eb3f7 
    OR r_582870.te_cb0b61 > 30 
    AND r_4e3b55.te_9bdd68 NOT ILIKE 'IG7ir98h' 
    OR r_582870.te_cb0b61 < r_5848d9.te_1eb3f7 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    btrim(chr(r_393e97.sr_item_sk * 10)) as te_62b3c4,
    make_date(r_393e97.sr_customer_sk,
    r_393e97.sr_customer_sk,
    r_7000d6.s_division_id) as te_380b11 
FROM
    db1.store_returns AS r_393e97 
INNER JOIN
    (
        WITH CTE_ab9afb(te_ed0532) AS (SELECT
            r_58a7dd.cd_dep_employed_count as te_ed0532 
        FROM
            db1.customer_demographics AS r_58a7dd 
        WHERE
            r_58a7dd.cd_demo_sk >= 11 
        ORDER BY
            1 ASC NULLS LAST), CTE_eaf6ff(te_b7025f, te_e1b86e, te_404538) AS (SELECT
            r_180d21.cc_tax_percentage as te_b7025f, chr(r_180d21.cc_sq_ft - unix_timestamp()) as te_e1b86e, hex(r_180d21.cc_gmt_offset) as te_404538 
        FROM
            db1.call_center AS r_180d21,
            (SELECT
                r_2efdb0.ca_street_type as te_e987ab 
            FROM
                db1.customer_address AS r_2efdb0 
            WHERE
                r_2efdb0.ca_county NOT LIKE r_2efdb0.ca_city 
                AND r_2efdb0.ca_city LIKE '4QHZgGIHl' 
                OR r_2efdb0.ca_street_name ILIKE 'G' 
            ORDER BY
                1 DESC NULLS LAST) AS r_b43194 
        WHERE
            r_180d21.cc_mkt_class ILIKE r_b43194.te_e987ab 
            OR r_180d21.cc_call_center_sk > 55 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC) SELECT
                r_fe7e0d.te_ad98f1 as te_7a0dd0, month(now()) as te_d3efc4 
            FROM
                CTE_ab9afb AS r_a2b8cb 
            INNER JOIN
                (
                    SELECT
                        r_e29380.i_current_price as te_39062e,
                        r_f8ae9a.s_store_id as te_20b8de 
                    FROM
                        (SELECT
                            r_8b765f.ca_address_id as te_de0fbd 
                        FROM
                            db1.time_dim AS r_40b08d 
                        INNER JOIN
                            db1.customer_address AS r_8b765f 
                                ON r_40b08d.t_time <= r_8b765f.ca_address_sk 
                        WHERE
                            r_40b08d.t_minute < 50 
                            OR r_40b08d.t_minute != r_40b08d.t_time_sk 
                            AND r_8b765f.ca_address_id LIKE 'IHl' 
                        ORDER BY
                            1 DESC NULLS FIRST 
                        LIMIT 91) AS r_a84285, db1.item AS r_e29380 LEFT JOIN
                        db1.store AS r_f8ae9a 
                            ON r_e29380.i_item_id NOT ILIKE r_f8ae9a.s_store_id 
                    WHERE
                        r_a84285.te_de0fbd NOT LIKE r_f8ae9a.s_store_id 
                        OR r_e29380.i_rec_start_date >= r_e29380.i_rec_end_date 
                        AND r_f8ae9a.s_closed_date_sk != r_f8ae9a.s_company_id 
                        AND r_f8ae9a.s_store_id ILIKE 'bBRG' 
                        AND r_e29380.i_manufact_id = 22 
                    ORDER BY
                        1 ASC NULLS FIRST, 2 NULLS FIRST) AS r_875248 
                            ON decimal(r_a2b8cb.te_ed0532) != r_875248.te_39062e, (SELECT
                                r_dc8b5a.i_rec_end_date as te_ad98f1, r_3607af.w_suite_number as te_43c862, r_dc8b5a.i_item_id as te_da9893 
                        FROM
                            db1.warehouse AS r_3607af,
                            db1.item AS r_dc8b5a 
                        RIGHT JOIN
                            db1.household_demographics AS r_0fa4b0 
                                ON r_dc8b5a.i_class NOT LIKE r_0fa4b0.hd_buy_potential 
                        WHERE
                            r_3607af.w_street_number LIKE r_dc8b5a.i_manufact 
                            AND r_3607af.w_warehouse_id ILIKE r_3607af.w_warehouse_name 
                            AND r_3607af.w_state NOT ILIKE 'sUgD' 
                        ORDER BY
                            1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS FIRST 
                        OFFSET 46) AS r_fe7e0d WHERE
                        r_875248.te_20b8de LIKE r_fe7e0d.te_da9893 
                        AND r_fe7e0d.te_43c862 NOT LIKE 'v95G6sUg' 
                    ORDER BY
                        1 NULLS FIRST, 2 ASC 
                    LIMIT 59) AS r_2b826e 
                        ON r_393e97.sr_fee != r_2b826e.te_d3efc4, (SELECT
                            r_d3c057.te_3509a5 as te_472e09 FROM
                                (SELECT
                                    try_add(14,
                                    87) as te_3509a5 
                            FROM
                                (SELECT
                                    r_f40bde.cc_call_center_sk as te_5d9dcd 
                                FROM
                                    db1.call_center AS r_f40bde 
                                WHERE
                                    r_f40bde.cc_rec_end_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
                                    OR r_f40bde.cc_sq_ft > 78 
                                    OR r_f40bde.cc_rec_end_date <= DATE'2024-03-25' 
                                ORDER BY
                                    1 DESC NULLS LAST) AS r_766ca1 
                            WHERE
                                r_766ca1.te_5d9dcd <= r_766ca1.te_5d9dcd 
                                AND r_766ca1.te_5d9dcd = 59 
                                AND r_766ca1.te_5d9dcd != 16 
                                OR r_766ca1.te_5d9dcd = r_766ca1.te_5d9dcd 
                            GROUP BY
                                1 
                            ORDER BY
                                1 DESC NULLS LAST) AS r_d3c057 
                            WHERE
                                r_d3c057.te_3509a5 > 80 
                                AND r_d3c057.te_3509a5 < r_d3c057.te_3509a5 
                            ORDER BY
                                1 NULLS LAST) AS r_91bc84 
                        LEFT JOIN
                            db1.store AS r_7000d6 
                                ON r_91bc84.te_472e09 >= r_7000d6.s_division_id 
                        WHERE
                            r_393e97.sr_return_tax < r_7000d6.s_tax_percentage 
                        ORDER BY
                            1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_5a06d4.s_gmt_offset as te_d5b6ed,
    r_bf7f81.up_de24d0 as te_a1857e 
FROM
    (SELECT
        * 
    FROM
        db1.customer_demographics UNPIVOT EXCLUDE NULLS ((up_0599e4,
        up_de24d0) FOR upn_3451df IN ((cd_dep_employed_count,
        cd_education_status) AS UPF_b0b90f,
        (cd_dep_college_count,
        cd_credit_rating) AS UPF_c34f16,
        (cd_purchase_estimate,
        cd_gender) AS UPF_9377d0,
        (cd_dep_count,
        cd_marital_status) AS UPF_51f344))) AS r_bf7f81,
    (SELECT
        * 
    FROM
        (SELECT
            r_5af4d2.i_rec_end_date as te_b616c3,
            r_5af4d2.i_wholesale_cost + 65.74342292D as te_5a083d 
        FROM
            db1.item AS r_5af4d2,
            db1.store_returns AS r_7b5eca 
        WHERE
            r_5af4d2.i_current_price > r_7b5eca.sr_net_loss 
            AND r_5af4d2.i_brand NOT LIKE 'Gv7RXpR' 
            AND r_5af4d2.i_brand LIKE ((SELECT
                r_f5d8a3.web_state || r_f5d8a3.web_mkt_desc as te_6285cb 
            FROM
                db1.household_demographics AS r_9363dc 
            INNER JOIN
                db1.customer_address AS r_d1f479 
                    ON r_9363dc.hd_buy_potential NOT LIKE r_d1f479.ca_city,
                db1.web_site AS r_f5d8a3 
            INNER JOIN
                db1.customer AS r_60eb4e 
                    ON r_f5d8a3.web_street_name NOT ILIKE r_60eb4e.c_last_name 
            WHERE
                r_9363dc.hd_dep_count = r_60eb4e.c_current_addr_sk 
            ORDER BY
                1 DESC NULLS LAST 
            LIMIT 1)) ORDER BY
            1 ASC, 2 DESC NULLS LAST 
        OFFSET 35) PIVOT(max(te_b616c3) AS pa_225fb2 FOR te_5a083d IN ((12.10004281) AS pf_644ddd, (98.21809273) AS pf_9b1b05, (17.8630673) AS pf_74f286, (60.9076538) AS pf_873fb1, (54.24728623) AS pf_1c96b9))) AS r_f7d139 RIGHT JOIN
        db1.store AS r_5a06d4 
            ON r_f7d139.pf_873fb1 != r_5a06d4.s_rec_start_date 
    WHERE
        r_bf7f81.up_0599e4 != r_5a06d4.s_closed_date_sk 
    ORDER BY
        1 NULLS FIRST, 2 DESC NULLS LAST;
----------->
WITH CTE_e0bdba(te_e5be7b) AS (SELECT
    r_eb6f6a.d_quarter_seq / 95 as te_e5be7b 
FROM
    db1.date_dim AS r_eb6f6a 
WHERE
    r_eb6f6a.d_date_sk != 97 
    OR r_eb6f6a.d_year < r_eb6f6a.d_fy_week_seq 
    OR r_eb6f6a.d_first_dom != 42 
ORDER BY
    1 NULLS FIRST) SELECT
    min(r_696968.te_e5be7b) as te_0919fe, timestamp_seconds(unix_timestamp()) as te_4e6bd5 
FROM
    CTE_e0bdba AS r_696968 
LEFT JOIN
    db1.customer_demographics AS r_0ea5c8 
        ON r_696968.te_e5be7b > double(r_0ea5c8.cd_dep_employed_count),
    db1.item AS r_588f01 
LEFT JOIN
    db1.customer_address AS r_3158d3 
        ON r_588f01.i_brand_id = r_3158d3.ca_address_sk 
WHERE
    r_0ea5c8.cd_gender LIKE r_3158d3.ca_suite_number 
ORDER BY
    1 DESC NULLS LAST, 2;
----------->
WITH CTE_f94c40(te_f90193) AS (SELECT
    r_7abee9.d_date as te_f90193 
FROM
    db1.date_dim AS r_7abee9 
ORDER BY
    1 NULLS FIRST), CTE_c768b5(te_7f0f3d, te_8141e1, te_3894a9) AS (WITH CTE_c98175(te_531559, te_09d9f4) AS (SELECT
    try_add(r_552d5a.sr_addr_sk, r_e1e2cc.s_rec_end_date) as te_531559, overlay(r_e1e2cc.s_market_manager PLACING r_552d5a.sr_addr_sk 
FROM
    r_e1e2cc.s_closed_date_sk) as te_09d9f4 
FROM
    db1.store AS r_e1e2cc,
    db1.store_returns AS r_552d5a 
INNER JOIN
    db1.income_band AS r_fa8139 
        ON r_552d5a.sr_ticket_number > r_fa8139.ib_upper_bound 
WHERE
    r_e1e2cc.s_market_id >= r_552d5a.sr_item_sk 
    AND r_e1e2cc.s_rec_start_date >= r_e1e2cc.s_rec_end_date 
ORDER BY
    1 DESC, 2 DESC NULLS LAST) SELECT
    ascii(r_1cde54.cc_manager) as te_7f0f3d, r_1cde54.cc_closed_date_sk + 82.89854585D as te_8141e1, r_1cde54.cc_call_center_id as te_3894a9 
FROM
    CTE_c98175 AS r_0c92ae,
    db1.customer AS r_32c1dc 
LEFT JOIN
    db1.call_center AS r_1cde54 
        ON r_32c1dc.c_first_shipto_date_sk >= r_1cde54.cc_tax_percentage 
WHERE
    r_0c92ae.te_09d9f4 NOT LIKE r_32c1dc.c_birth_country 
    OR r_1cde54.cc_market_manager <= 'lpx6kg7x' 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST) SELECT
    r_cdd938.te_f90193 as te_c40031 
FROM
    CTE_f94c40 AS r_cdd938 
INNER JOIN
    CTE_c768b5 AS r_efc3ff 
        ON date_from_unix_date(month(r_cdd938.te_f90193)) >= date_from_unix_date(character_length(chr(r_efc3ff.te_8141e1))) 
WHERE
    r_efc3ff.te_7f0f3d != 50 
    AND NOT r_efc3ff.te_3894a9 LIKE r_efc3ff.te_3894a9 
    OR r_efc3ff.te_8141e1 != 62.96253378D 
    OR r_efc3ff.te_8141e1 >= 56.31195548D 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 38 OFFSET 62;
----------->
SELECT
    r_65e824.web_street_name as te_3d574b,
    r_65e824.web_state as te_9231aa,
    r_65e824.web_site_id as te_248579 
FROM
    (SELECT
        r_6a0db6.wp_creation_date_sk as te_067aa2 
    FROM
        db1.web_page AS r_6a0db6 
    WHERE
        r_6a0db6.wp_access_date_sk != 41 
    ORDER BY
        1 DESC NULLS LAST) AS r_c8cd75 
RIGHT JOIN
    db1.web_site AS r_65e824 
        ON r_c8cd75.te_067aa2 = r_65e824.web_site_sk,
    db1.household_demographics AS r_851b9f 
RIGHT JOIN
    (
        SELECT
            r_054183.cc_closed_date_sk as te_526ffa 
        FROM
            db1.call_center AS r_054183 
        RIGHT JOIN
            db1.date_dim AS r_6a69f1 
                ON r_054183.cc_rec_end_date > r_6a69f1.d_date 
        WHERE
            (
                NOT r_054183.cc_class LIKE 'tnt' 
                OR r_054183.cc_rec_start_date <= r_6a69f1.d_date
            ) 
        ORDER BY
            1 NULLS FIRST
    ) AS r_a52c01 
        ON r_851b9f.hd_demo_sk < r_a52c01.te_526ffa 
WHERE
    (
        NOT r_65e824.web_company_id >= r_851b9f.hd_income_band_sk 
        AND r_65e824.web_rec_start_date < r_65e824.web_rec_end_date
    ) 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_834e46.w_city as te_a6120a,
    bigint(r_7fb891.cc_division + 36.12573095D) as te_1bdc1e 
FROM
    db1.time_dim AS r_69acc0,
    db1.call_center AS r_7fb891 
LEFT JOIN
    db1.warehouse AS r_834e46 
        ON r_7fb891.cc_division > r_834e46.w_warehouse_sq_ft 
WHERE
    r_69acc0.t_am_pm NOT ILIKE r_7fb891.cc_mkt_desc 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_72c550.cd_marital_status as te_53f6c5 
FROM
    db1.customer AS r_d3ba17 
RIGHT JOIN
    db1.customer_demographics AS r_72c550 
        ON r_d3ba17.c_login ILIKE r_72c550.cd_gender 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_cc3700.w_county as te_b088a7,
    r_bb1a4c.hd_demo_sk * 63 as te_007479 
FROM
    db1.household_demographics AS r_bb1a4c 
INNER JOIN
    db1.warehouse AS r_cc3700 
        ON r_bb1a4c.hd_dep_count > r_cc3700.w_warehouse_sk,
    db1.reason AS r_9f4d33 
WHERE
    r_cc3700.w_street_type NOT ILIKE r_9f4d33.r_reason_id 
    AND r_bb1a4c.hd_income_band_sk >= 71 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST 
OFFSET 50;
----------->
SELECT
    r_c4d3b7.sr_addr_sk as te_159b77 
FROM
    db1.store_returns AS r_c4d3b7 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_65736c.web_county + r_4370d5.d_month_seq as te_891ad8,
    r_4370d5.d_day_name as te_9a1941 
FROM
    db1.store AS r_7f85a3,
    db1.date_dim AS r_4370d5 
INNER JOIN
    db1.store_returns AS r_65736c 
        ON r_4370d5.d_dom < r_65736c.sr_fee 
WHERE
    r_7f85a3.s_tax_percentage >= r_65736c.sr_cdemo_sk 
    OR (
        NOT r_4370d5.d_date <= r_7f85a3.s_rec_start_date 
        OR r_4370d5.d_date NOT IN (
            SELECT
                DATE'2024-10-11' as te_57618d 
            FROM
                db1.customer_address AS r_eece9b 
            WHERE
                r_eece9b.ca_city LIKE r_eece9b.ca_country 
                OR r_eece9b.ca_street_name ILIKE 'LQ' 
            ORDER BY
                1 ASC NULLS LAST
        )
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_32723c.d_dow as te_427bb0,
    r_f95f19.s_store_sk as te_9560ae,
    r_f95f19.s_country as te_e7f6d4 
FROM
    db1.date_dim AS r_32723c 
RIGHT JOIN
    db1.web_site AS r_983ec6 
        ON r_32723c.d_current_day NOT ILIKE r_983ec6.web_site_id,
    db1.web_page AS r_73b86a 
LEFT JOIN
    db1.store AS r_f95f19 
        ON r_73b86a.wp_type NOT LIKE r_f95f19.s_zip 
WHERE
    r_32723c.d_last_dom = r_73b86a.wp_creation_date_sk 
    OR r_32723c.d_year > 64 
ORDER BY
    1 DESC NULLS LAST, 2, 3 ASC;
----------->
(
    SELECT
        r_50b9f3.i_rec_end_date as te_c96c7e,
        r_915bae.ca_county as te_f750a8,
        r_915bae.ca_zip as te_5469dc 
    FROM
        db1.item AS r_50b9f3 
    INNER JOIN
        (
            SELECT
                * 
            FROM
                db1.web_page UNPIVOT INCLUDE NULLS ((up_73fc1a,
                up_c7fd32,
                up_e00892,
                up_3c8666) FOR upn_324007 IN ((wp_type,
                wp_access_date_sk,
                wp_autogen_flag,
                wp_rec_end_date) AS UPF_e0e37a,
                (wp_url,
                wp_image_count,
                wp_web_page_id,
                wp_rec_start_date) AS UPF_98c7d2))
        ) AS r_ac01c3 
            ON r_50b9f3.i_item_sk <= r_ac01c3.wp_web_page_sk,
        db1.customer_address AS r_915bae 
    WHERE
        r_50b9f3.i_current_price = r_915bae.ca_gmt_offset 
        AND r_50b9f3.i_rec_start_date BETWEEN DATE'2024-10-11' AND r_50b9f3.i_rec_end_date 
    ORDER BY
        1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST) MINUS ALL (SELECT
            r_54d88c.web_rec_end_date as te_5bd481, overlay(r_c304d6.ca_street_name PLACING r_54d88c.web_close_date_sk 
        FROM
            r_54d88c.web_mkt_id) as te_8fd420,
            r_c304d6.ca_address_id as te_81ef5f 
        FROM
            db1.customer_address AS r_c304d6 
        RIGHT JOIN
            db1.web_site AS r_54d88c 
                ON r_c304d6.ca_country NOT ILIKE r_54d88c.web_city,
            db1.call_center AS r_8c53c4 
        LEFT JOIN
            db1.customer_demographics AS r_556167 
                ON r_8c53c4.cc_city ILIKE r_556167.cd_gender 
        WHERE
            r_54d88c.web_gmt_offset > r_8c53c4.cc_tax_percentage 
        ORDER BY
            1 DESC, 2 NULLS LAST, 3 ASC NULLS FIRST) 
    ORDER BY
        1 ASC, 2 DESC NULLS FIRST, 3 NULLS LAST 
    LIMIT 31;
----------->
SELECT
    r_19e1ef.c_last_name as te_044174,
    r_479cb6.cd_credit_rating as te_0cd68f,
    r_19e1ef.c_email_address as te_d7e361 
FROM
    db1.customer_demographics AS r_479cb6,
    db1.customer AS r_19e1ef 
WHERE
    r_479cb6.cd_dep_employed_count = r_19e1ef.c_current_addr_sk 
GROUP BY
    1, 3, 2 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_1e351c.cc_open_date_sk as te_a3c821,
    r_1e351c.cc_company_name as te_590cac,
    r_57fecf.s_rec_start_date as te_9ddd88 
FROM
    db1.reason AS r_c3ac38,
    db1.store AS r_57fecf 
LEFT JOIN
    db1.call_center AS r_1e351c 
        ON r_57fecf.s_street_name NOT ILIKE r_1e351c.cc_city 
WHERE
    r_c3ac38.r_reason_desc NOT LIKE r_57fecf.s_market_manager 
    OR r_1e351c.cc_country NOT LIKE 'b2Gv7RX' 
    OR r_57fecf.s_rec_end_date = r_1e351c.cc_rec_end_date 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 ASC NULLS LAST 
OFFSET 82;
----------->
SELECT
    to_char(r_c81b5f.hd_vehicle_count - pi() + decimal(r_527453.wp_image_count),
    '999') as te_0728d6,
    r_527453.wp_rec_start_date as te_82f2b0 
FROM
    db1.web_page AS r_527453 
RIGHT JOIN
    db1.household_demographics AS r_c81b5f 
        ON r_527453.wp_char_count >= r_c81b5f.hd_income_band_sk,
    db1.income_band AS r_9dd986 
WHERE
    r_c81b5f.hd_demo_sk < r_9dd986.ib_income_band_sk 
ORDER BY
    1 DESC, 2 ASC NULLS LAST;
----------->
SELECT
    date_sub(r_d6418a.web_rec_end_date,
    r_068487.c_current_cdemo_sk) as te_39b04b 
FROM
    db1.customer AS r_068487 
LEFT JOIN
    db1.web_site AS r_d6418a 
        ON r_068487.c_current_cdemo_sk = r_d6418a.web_tax_percentage 
WHERE
    r_068487.c_preferred_cust_flag >= r_d6418a.web_state 
    OR r_068487.c_birth_month >= 38 
    OR r_d6418a.web_gmt_offset <= 95.99494948 
    AND r_d6418a.web_class NOT LIKE 'DCM' 
ORDER BY
    1 ASC;
----------->
SELECT
    r_fb8f35.i_manager_id as te_bd1178,
    r_fb8f35.i_rec_end_date as te_1fded2,
    33 - r_fb828c.cc_division as te_1b16e1 
FROM
    db1.ship_mode AS r_4687a9 
INNER JOIN
    db1.customer_demographics AS r_df0e09 
        ON r_4687a9.sm_ship_mode_sk < r_df0e09.cd_purchase_estimate,
    db1.call_center AS r_fb828c 
RIGHT JOIN
    db1.item AS r_fb8f35 
        ON r_fb828c.cc_street_number LIKE r_fb8f35.i_formulation 
WHERE
    (
        NOT r_df0e09.cd_purchase_estimate >= r_fb828c.cc_closed_date_sk 
        AND (
            NOT r_4687a9.sm_carrier NOT LIKE 'W4Pg' 
            OR r_df0e09.cd_purchase_estimate = r_fb8f35.i_manager_id 
            AND r_fb828c.cc_call_center_id ILIKE '7ir98' 
            AND r_df0e09.cd_purchase_estimate > 81
        )
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3;
----------->
SELECT
    r_918d7e.t_time_sk - 14 as te_513616 
FROM
    db1.time_dim AS r_918d7e 
WHERE
    r_918d7e.t_time >= r_918d7e.t_time_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_c467d9.ca_address_sk as te_83ad9d,
    r_537315.ca_gmt_offset as te_17884a 
FROM
    db1.customer_address AS r_537315,
    db1.ship_mode AS r_b52624 
RIGHT JOIN
    db1.customer_address AS r_c467d9 
        ON r_b52624.sm_contract ILIKE r_c467d9.ca_country 
WHERE
    r_537315.ca_gmt_offset = r_c467d9.ca_gmt_offset 
    AND r_537315.ca_suite_number NOT ILIKE 'tb2Gv' 
    AND r_537315.ca_suite_number NOT ILIKE 'v7R' 
    AND r_537315.ca_address_sk <= 52 
    OR r_537315.ca_state NOT LIKE 'HZ' 
ORDER BY
    1 ASC NULLS LAST, 2 DESC;
----------->
SELECT
    r_f20ed7.s_store_id as te_41bafe 
FROM
    (SELECT
        date_from_unix_date(r_e4f183.t_minute) as te_71e2cb,
        r_e4f183.t_time_id as te_acfb80,
        r_49ca52.ca_gmt_offset as te_1cbab1 
    FROM
        db1.warehouse AS r_81dc27 
    RIGHT JOIN
        db1.time_dim AS r_e4f183 
            ON r_81dc27.w_city NOT LIKE r_e4f183.t_shift,
        db1.customer_address AS r_49ca52 
    WHERE
        r_81dc27.w_warehouse_sk < r_49ca52.ca_gmt_offset 
    ORDER BY
        1 NULLS FIRST, 2 DESC NULLS LAST, 3 NULLS LAST) AS r_d0be01 
INNER JOIN
    db1.store AS r_f20ed7 
        ON r_d0be01.te_acfb80 LIKE r_f20ed7.s_country 
WHERE
    r_d0be01.te_1cbab1 = r_f20ed7.s_tax_percentage 
    OR r_f20ed7.s_store_sk = r_f20ed7.s_closed_date_sk 
    OR r_f20ed7.s_hours ILIKE 'z2k' 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 35;
----------->
SELECT
    r_54f867.d_date_sk as te_0790fc,
    r_632202.s_rec_end_date as te_33511a 
FROM
    db1.store AS r_632202 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.date_dim PIVOT(count(d_current_year) AS pa_cc16e5,
            avg(d_first_dom) AS pa_d2f8c1 FOR d_fy_quarter_seq IN ((60) AS pf_33e666,
            (44) AS pf_66833b,
            (36) AS pf_9b5530,
            (53) AS pf_aa0f8d,
            (29) AS pf_d83d3c))
    ) AS r_54f867 
        ON r_632202.s_store_id NOT ILIKE r_54f867.d_current_quarter,
    db1.customer_demographics AS r_15f7e1 
LEFT JOIN
    db1.item AS r_245261 
        ON r_15f7e1.cd_dep_college_count != r_245261.i_class_id 
WHERE
    r_54f867.d_moy = r_15f7e1.cd_dep_college_count 
    AND r_632202.s_rec_start_date <= r_245261.i_rec_end_date 
    OR (
        NOT r_632202.s_rec_start_date BETWEEN r_54f867.d_date AND DATE'2024-03-26' 
        OR r_632202.s_store_id <= 'Gv7R'
    ) 
ORDER BY
    1 DESC, 2 ASC NULLS LAST 
LIMIT 20;
----------->
SELECT
    r_7f195d.up_3c6b86 as te_f0a717,
    r_7f195d.i_current_price as te_0971b8 
FROM
    db1.warehouse AS r_45b1da 
RIGHT JOIN
    db1.warehouse AS r_4449b8 
        ON r_45b1da.w_county NOT ILIKE r_4449b8.w_county,
    (SELECT
        * 
    FROM
        db1.item UNPIVOT INCLUDE NULLS ((up_bc3ca6,
        up_ec6b5b,
        up_3c6b86,
        up_dd4428,
        up_e8d432) FOR upn_4f919f IN ((i_rec_end_date,
        i_units,
        i_item_id,
        i_wholesale_cost,
        i_item_sk) AS UPF_743214))) AS r_7f195d 
WHERE
    r_45b1da.w_zip NOT ILIKE r_7f195d.i_manufact 
    AND r_7f195d.i_brand_id >= 18 
    AND r_45b1da.w_street_number NOT LIKE r_7f195d.i_category 
    AND r_7f195d.i_class_id = r_7f195d.i_manufact_id 
GROUP BY
    1, 2 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    r_7acae4.sr_return_tax as te_0a4558,
    r_6cebc3.web_class as te_4f4c30 
FROM
    db1.store_returns AS r_7acae4,
    db1.web_site AS r_6cebc3 
INNER JOIN
    db1.reason AS r_bd5d77 
        ON r_6cebc3.web_company_name LIKE r_bd5d77.r_reason_id 
WHERE
    r_7acae4.sr_refunded_cash <= r_6cebc3.web_gmt_offset 
ORDER BY
    1 ASC, 2 ASC 
OFFSET 15;
----------->
SELECT
    r_547769.ca_address_id as te_e21b63 
FROM
    db1.web_site AS r_b8f903 
LEFT JOIN
    db1.customer_address AS r_547769 
        ON r_b8f903.web_class NOT ILIKE r_547769.ca_location_type 
WHERE
    r_547769.ca_state ILIKE '6kg' 
    OR r_b8f903.web_rec_start_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    AND r_b8f903.web_site_sk != r_b8f903.web_open_date_sk 
    OR r_b8f903.web_gmt_offset <= r_547769.ca_gmt_offset 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_fb6ab3(te_978ae7) AS (SELECT
    r_d00a79.i_category_id as te_978ae7 
FROM
    (SELECT
        r_f5a299.c_current_cdemo_sk as te_e90e73,
        r_f5a299.c_current_cdemo_sk * 33.29085662 / e() as te_c8c7e6,
        r_f5a299.c_preferred_cust_flag as te_062452 
    FROM
        db1.customer AS r_f5a299,
        db1.income_band AS r_6deb96 
    INNER JOIN
        db1.reason AS r_ea6fe4 
            ON r_6deb96.ib_lower_bound = r_ea6fe4.r_reason_sk 
    WHERE
        r_f5a299.c_email_address NOT ILIKE r_ea6fe4.r_reason_desc 
        OR r_f5a299.c_birth_month >= r_f5a299.c_birth_year 
    ORDER BY
        1 ASC, 2 ASC NULLS LAST, 3 NULLS LAST 
    OFFSET 60) AS r_a5e4cf LEFT JOIN
    db1.item AS r_d00a79 
        ON r_a5e4cf.te_062452 ILIKE r_d00a79.i_category 
WHERE
    r_d00a79.i_rec_end_date = r_d00a79.i_rec_start_date 
ORDER BY
    1) SELECT
        r_4efe83.s_county as te_f929e3, r_4efe83.s_gmt_offset as te_1f4a59 
    FROM
        CTE_fb6ab3 AS r_25606c 
    INNER JOIN
        db1.store AS r_4efe83 
            ON r_25606c.te_978ae7 < r_4efe83.s_floor_space,
        db1.date_dim AS r_596f42 
    RIGHT JOIN
        db1.web_site AS r_d80a6f 
            ON r_596f42.d_date < r_d80a6f.web_rec_start_date 
    WHERE
        r_4efe83.s_gmt_offset > r_d80a6f.web_tax_percentage 
        AND r_4efe83.s_store_id NOT ILIKE 'g' 
        AND NOT r_4efe83.s_store_id LIKE r_d80a6f.web_site_id 
    ORDER BY
        1 ASC, 2 DESC;
----------->
WITH CTE_10bfa6(te_1a4e30) AS (SELECT
    sin(r_f1ba0f.t_second) * 55.01922003D / r_f1ba0f.t_minute / (r_f1ba0f.t_time + 7.42139814D) as te_1a4e30 
FROM
    db1.time_dim AS r_f1ba0f 
WHERE
    r_f1ba0f.t_time_sk IS NULL 
    AND r_f1ba0f.t_second >= 95 
    OR r_f1ba0f.t_time BETWEEN r_f1ba0f.t_time_sk AND 0 
ORDER BY
    1 ASC NULLS LAST), CTE_116a6e(te_3f603b) AS (SELECT
    timestamp_millis(r_79120e.i_brand_id) as te_3f603b 
FROM
    db1.item AS r_79120e 
WHERE
    r_79120e.i_rec_end_date >= DATE'2024-10-11' 
    OR r_79120e.i_item_id IS NULL 
    OR r_79120e.i_brand_id < 20 
ORDER BY
    1 DESC NULLS FIRST 
OFFSET 69) SELECT
r_7ad858.te_a02467 as te_bd4af0 FROM
    CTE_10bfa6 AS r_3b35d2 
LEFT JOIN
    (
        SELECT
            r_cdec9b.i_item_id as te_a02467 
        FROM
            db1.item AS r_cdec9b 
        RIGHT JOIN
            db1.reason AS r_8fe4d7 
                ON r_cdec9b.i_units > r_8fe4d7.r_reason_id 
        WHERE
            r_8fe4d7.r_reason_desc NOT ILIKE 'b2Gv7RXpRQ' 
        ORDER BY
            1 DESC NULLS FIRST
    ) AS r_7ad858 
        ON chr(r_3b35d2.te_1a4e30) LIKE r_7ad858.te_a02467 
ORDER BY
    1 ASC;
----------->
SELECT
    sin(e() / cos(r_4cef54.t_hour)) as te_2a3991,
    r_4cef54.t_meal_time as te_87a040,
    r_fce3a9.d_date as te_643220 
FROM
    db1.time_dim AS r_4cef54,
    db1.date_dim AS r_fce3a9 
WHERE
    r_4cef54.t_time_id >= r_fce3a9.d_current_week 
    AND r_fce3a9.d_quarter_name LIKE '6kg7' 
    OR r_4cef54.t_minute = 17 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_a829d5.c_preferred_cust_flag as te_fd57b7 
FROM
    db1.store_returns AS r_146e07 
LEFT JOIN
    db1.customer AS r_a829d5 
        ON r_146e07.sr_cdemo_sk < r_a829d5.c_current_hdemo_sk 
WHERE
    r_a829d5.c_preferred_cust_flag NOT ILIKE r_a829d5.c_customer_id 
    OR r_a829d5.c_customer_id LIKE r_a829d5.c_preferred_cust_flag 
    OR r_146e07.sr_addr_sk > r_146e07.sr_hdemo_sk 
    OR r_a829d5.c_customer_id ILIKE r_a829d5.c_preferred_cust_flag 
ORDER BY
    1 ASC NULLS LAST 
OFFSET 55;
----------->
SELECT
    character_length(r_e452f7.r_reason_id) as te_228cb3 
FROM
    db1.reason AS r_e452f7 
LEFT JOIN
    (
        SELECT
            r_36a4b1.web_tax_percentage as te_32e76f,
            r_cee8e2.i_category_id as te_e89689,
            r_34d5c2.ca_address_id as te_3cad3a 
        FROM
            db1.web_site AS r_36a4b1 
        INNER JOIN
            db1.item AS r_cee8e2 
                ON r_36a4b1.web_gmt_offset != r_cee8e2.i_item_sk,
            db1.customer_address AS r_34d5c2 
        LEFT JOIN
            (
                SELECT
                    r_5afca3.r_reason_desc as te_fc3a55 
                FROM
                    db1.reason AS r_5afca3 
                WHERE
                    r_5afca3.r_reason_sk BETWEEN 45 AND 45 
                    AND (
                        NOT r_5afca3.r_reason_desc LIKE r_5afca3.r_reason_id 
                        AND r_5afca3.r_reason_sk = 69
                    ) 
                ORDER BY
                    1 NULLS FIRST
            ) AS r_795f6a 
                ON r_34d5c2.ca_city NOT ILIKE r_795f6a.te_fc3a55 
        WHERE
            r_cee8e2.i_current_price != r_34d5c2.ca_gmt_offset 
            AND r_36a4b1.web_rec_start_date BETWEEN r_36a4b1.web_rec_end_date AND DATE'2024-10-11' 
            AND r_cee8e2.i_manufact NOT ILIKE 'a' 
            AND r_36a4b1.web_county NOT ILIKE 'DCMaLQFr' 
            OR r_36a4b1.web_rec_start_date < DATE'2024-10-11' 
        ORDER BY
            1 ASC, 2 NULLS FIRST, 3 DESC) AS r_cc9225 
                ON r_e452f7.r_reason_id ILIKE r_cc9225.te_3cad3a 
        WHERE
            r_e452f7.r_reason_desc NOT LIKE 'RGC0lezmS' 
        ORDER BY
            1 NULLS LAST;
----------->
SELECT
    r_0c01ad.sr_item_sk as te_453a9a,
    reverse(r_8b46fa.web_site_id) as te_2eeae5,
    r_8b46fa.web_open_date_sk as te_8ba756 
FROM
    db1.store_returns AS r_0c01ad 
LEFT JOIN
    db1.web_site AS r_8b46fa 
        ON r_0c01ad.web_county < r_8b46fa.web_gmt_offset,
    (SELECT
        * 
    FROM
        db1.web_site PIVOT(max(web_class) AS pa_5445a2 FOR web_company_name IN (('SRI') AS pf_a4afd4,
        ('kg7x2S') AS pf_f82163,
        ('By7IG7ir') AS pf_80ed89))) AS r_34ddfc 
LEFT JOIN
    db1.warehouse AS r_04d9e1 
        ON r_34ddfc.web_gmt_offset <= r_04d9e1.w_gmt_offset 
WHERE
    r_0c01ad.sr_return_amt <= r_34ddfc.web_gmt_offset 
    AND r_8b46fa.web_state NOT LIKE 'nt' 
    OR r_04d9e1.w_suite_number ILIKE 'X4Q' 
    OR r_34ddfc.web_manager LIKE '6' 
    AND r_34ddfc.web_mkt_class <= 'px' 
ORDER BY
    1 DESC, 2 ASC NULLS FIRST, 3 NULLS LAST;
----------->
(
    SELECT
        r_96e47f.w_city as te_7def9c,
        r_b2e057.cc_rec_start_date as te_d936c4,
        r_b2e057.cc_rec_start_date as te_ccc422 
    FROM
        db1.income_band AS r_b8e37b,
        db1.warehouse AS r_96e47f 
    LEFT JOIN
        db1.call_center AS r_b2e057 
            ON r_96e47f.w_gmt_offset > r_b2e057.cc_tax_percentage 
    WHERE
        r_b8e37b.ib_upper_bound = r_b2e057.cc_company 
        AND r_b2e057.cc_company < 45 
        OR r_b2e057.cc_state NOT LIKE '2SW4P' 
        AND r_96e47f.w_street_type NOT ILIKE 'pR' 
        OR r_96e47f.w_city != r_b2e057.cc_manager 
    ORDER BY
        1 NULLS LAST, 2 ASC, 3 DESC
) 
UNION
ALL (
SELECT
    string(r_86861d.web_county) as te_3dfa0d,
    DATE'2024-03-25' as te_fc2ad1,
    try_add(r_86861d.sr_returned_date_sk,
    DATE'2024-10-11') as te_ed5545 
FROM
    db1.store_returns AS r_86861d,
    db1.household_demographics AS r_a80730 
WHERE
    r_86861d.sr_hdemo_sk = r_a80730.hd_income_band_sk 
    AND r_86861d.sr_return_tax != 84.52972941 
    AND (
        NOT r_86861d.sr_addr_sk < r_86861d.sr_item_sk 
        OR r_86861d.sr_store_sk > r_a80730.hd_income_band_sk
    ) 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 ASC
) 
ORDER BY
1 DESC, 2 NULLS FIRST, 3 DESC;
----------->
SELECT
    r_facffb.s_rec_end_date as te_1745a0 
FROM
    db1.store AS r_facffb 
WHERE
    NOT r_facffb.s_gmt_offset != 59.58505823 
    AND r_facffb.s_store_name NOT LIKE 'g' 
    OR r_facffb.s_division_name ILIKE 'y7IG7ir98' 
    OR r_facffb.s_rec_end_date <= r_facffb.s_rec_start_date 
ORDER BY
    1 DESC NULLS FIRST 
OFFSET 13;
----------->
SELECT
    r_478f69.d_holiday as te_130dcc,
    try_add(r_f8b828.sm_code,
    r_478f69.d_qoy) as te_999247,
    r_478f69.up_59bd8d as te_702c24 
FROM
    (SELECT
        * 
    FROM
        db1.date_dim UNPIVOT ((up_4c6228,
        up_59bd8d,
        up_265232) FOR upn_a4bdfe IN ((d_day_name,
        d_date,
        d_first_dom) AS UPF_41d82b))) AS r_478f69,
    db1.store AS r_52e5a6 
RIGHT JOIN
    db1.ship_mode AS r_f8b828 
        ON r_52e5a6.s_manager NOT ILIKE r_f8b828.sm_contract 
WHERE
    r_478f69.d_week_seq > r_52e5a6.s_store_sk 
    OR r_478f69.d_holiday NOT ILIKE '2Gv7' 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC, 3 ASC NULLS LAST;
----------->
SELECT
    r_e8a60a.te_4bb450 as te_778402 
FROM
    (SELECT
        abs(r_7d789b.c_first_sales_date_sk) as te_4bb450,
        r_76ba8c.wp_max_ad_count as te_2a7ba0 
    FROM
        db1.item AS r_1e35af 
    INNER JOIN
        db1.web_page AS r_76ba8c 
            ON r_1e35af.i_manufact_id > r_76ba8c.wp_access_date_sk,
        db1.customer AS r_7d789b 
    RIGHT JOIN
        db1.item AS r_0767c4 
            ON r_7d789b.c_customer_id LIKE r_0767c4.i_item_id 
    WHERE
        r_1e35af.i_manufact NOT ILIKE r_0767c4.i_item_desc 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS LAST) AS r_e8a60a 
WHERE
    r_e8a60a.te_2a7ba0 >= r_e8a60a.te_4bb450 
    AND r_e8a60a.te_4bb450 <= 32 
ORDER BY
    1 DESC;
----------->
SELECT
    r_2937f3.web_rec_end_date as te_3f18ad,
    hash(r_2937f3.web_mkt_class) as te_f5fc12,
    r_cd81a6.s_rec_end_date as te_a2705b 
FROM
    db1.store AS r_cd81a6 
LEFT JOIN
    db1.income_band AS r_da9822 
        ON r_cd81a6.s_number_employees > r_da9822.ib_lower_bound,
    db1.web_site AS r_2937f3 
WHERE
    (
        NOT r_cd81a6.s_tax_percentage > r_2937f3.web_gmt_offset 
        OR r_2937f3.web_suite_number ILIKE 'z2kG' 
        OR r_cd81a6.s_street_type NOT LIKE 'IG7ir98hv'
    ) 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS FIRST, 3 NULLS FIRST;
----------->
SELECT
    r_c5b675.i_rec_start_date as te_a0a179,
    r_8817fd.cc_company_name as te_d3ce30 
FROM
    db1.item AS r_c5b675,
    db1.call_center AS r_8817fd 
WHERE
    r_c5b675.i_class_id = r_8817fd.cc_open_date_sk 
    OR r_c5b675.i_category NOT LIKE 'O' 
    AND r_c5b675.i_item_sk = 92 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_b571a4.sr_store_credit as te_b93b27,
    31.74509296D + r_b571a4.sr_fee as te_5d0840 
FROM
    db1.customer_demographics AS r_8f2135,
    db1.store_returns AS r_b571a4 
RIGHT JOIN
    (
        SELECT
            r_7a56cd.web_open_date_sk as te_09da9e 
        FROM
            db1.web_site AS r_7a56cd 
        WHERE
            r_7a56cd.web_street_type NOT ILIKE 'zmSRIvXUu' 
            OR r_7a56cd.web_street_number ILIKE r_7a56cd.web_zip 
            OR r_7a56cd.web_rec_start_date != DATE'2024-10-11' 
        ORDER BY
            1 DESC 
        LIMIT 45
) AS r_da97ef 
    ON r_b571a4.sr_returned_date_sk > r_da97ef.te_09da9e WHERE
        r_8f2135.cd_dep_employed_count <= r_b571a4.sr_net_loss 
        AND r_b571a4.sr_return_ship_cost < r_b571a4.sr_store_credit 
        AND r_b571a4.sr_return_time_sk < 31 
GROUP BY
    1, 2 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST;
----------->
WITH CTE_1e411b(te_d1c1bf, te_dcfeaf) AS (SELECT
    to_char(r_9d66ba.i_current_price,
    '999') as te_d1c1bf,
    r_9d66ba.i_current_price as te_dcfeaf 
FROM
    (SELECT
        * 
    FROM
        db1.reason UNPIVOT EXCLUDE NULLS ((up_0eae2e,
        up_864919) FOR upn_5fdc86 IN ((r_reason_id,
        r_reason_sk) AS UPF_af4652))) AS r_aac8b7 
INNER JOIN
    (SELECT
        r_0f5337.s_market_id * r_c12c4a.pf_4ca535 as te_3bf42e 
    FROM
        (SELECT
            * 
        FROM
            db1.store UNPIVOT EXCLUDE NULLS ((up_ce3ddf,
            up_068aab,
            up_e83092,
            up_b789e5,
            up_3fedb8) FOR upn_97c319 IN ((s_closed_date_sk,
            s_gmt_offset,
            s_city,
            s_store_id,
            s_rec_end_date) AS UPF_5c325e))) AS r_0f5337 
    LEFT JOIN
        (SELECT
            * 
        FROM
            db1.web_page PIVOT(skewness(wp_customer_sk) AS pa_3575cb FOR wp_autogen_flag IN (('elBy7') AS pf_9d6b08,
            ('Hlpx') AS pf_4ca535,
            ('Uu') AS pf_4d2c0c))) AS r_c12c4a 
            ON r_0f5337.s_manager NOT LIKE r_c12c4a.wp_web_page_id 
    WHERE
        r_c12c4a.wp_rec_end_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
        AND r_c12c4a.wp_type NOT LIKE 'G7ir98hv95' 
        AND r_0f5337.s_store_name LIKE '5G6s' 
    GROUP BY
        1 
    ORDER BY
        1 DESC) AS r_6e58ca 
            ON double(r_aac8b7.up_864919) <= r_6e58ca.te_3bf42e, db1.item AS r_9d66ba 
    RIGHT JOIN
        db1.customer_address AS r_f032f7 
            ON r_9d66ba.i_item_id LIKE r_f032f7.ca_street_number 
    WHERE
        r_aac8b7.up_864919 != r_9d66ba.i_class_id 
        OR r_6e58ca.te_3bf42e > 77.93998254D 
        AND r_9d66ba.i_product_name ILIKE 'C0l' 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST), CTE_7a3468(te_52ea41, te_e57afd) AS (SELECT
            r_188212.i_color as te_52ea41, r_ae5230.te_8027cf as te_e57afd 
        FROM
            db1.web_page AS r_07817e,
            db1.item AS r_188212 
        RIGHT JOIN
            (SELECT
                r_70d8e9.w_gmt_offset * sum(0.31438937D / r_291e16.hd_dep_count) as te_21e56e,
                r_70d8e9.w_county as te_8027cf 
            FROM
                db1.household_demographics AS r_291e16,
                db1.warehouse AS r_70d8e9 
            WHERE
                NOT r_291e16.hd_income_band_sk = r_70d8e9.w_warehouse_sk 
                OR r_70d8e9.w_warehouse_sk > r_291e16.hd_income_band_sk 
                AND r_70d8e9.w_warehouse_sq_ft != r_291e16.hd_dep_count 
            GROUP BY
                r_291e16.hd_dep_count, r_70d8e9.w_gmt_offset, r_70d8e9.w_county 
            ORDER BY
                1 DESC NULLS LAST, 2 ASC NULLS LAST 
            LIMIT 77) AS r_ae5230 
            ON r_188212.i_item_id LIKE r_ae5230.te_8027cf WHERE
                (NOT r_07817e.wp_access_date_sk < r_188212.i_category_id 
                AND r_188212.i_current_price < 71.33862742 
                AND r_07817e.wp_rec_start_date BETWEEN DATE'2024-03-26' AND r_188212.i_rec_end_date 
                AND r_188212.i_size ILIKE 'kG' 
                OR r_188212.i_wholesale_cost != 47.27097398)) SELECT
                    r_21df8c.te_d1c1bf * 17.8550267D as te_7f6590 
            FROM
                (SELECT
                    r_74bf64.d_quarter_name as te_b16f09 
                FROM
                    db1.date_dim AS r_74bf64 
                LEFT JOIN
                    (
                        WITH CTE_171ee5(te_491894, te_9f0344) AS (SELECT
                            r_5e7722.web_state as te_491894,
                            r_5e7722.web_rec_end_date as te_9f0344 
                        FROM
                            db1.household_demographics AS r_aa076b 
                        RIGHT JOIN
                            db1.reason AS r_c93706 
                                ON r_aa076b.hd_income_band_sk <= r_c93706.r_reason_sk,
                            db1.web_site AS r_5e7722 
                        RIGHT JOIN
                            db1.income_band AS r_29881e 
                                ON r_5e7722.web_tax_percentage < r_29881e.ib_lower_bound 
                        WHERE
                            r_aa076b.hd_income_band_sk < r_29881e.ib_lower_bound 
                            OR r_5e7722.web_mkt_id != 63 
                        ORDER BY
                            1 DESC NULLS LAST, 2 DESC) SELECT
                            r_74e1fa.c_customer_id as te_718acc, r_062802.te_726209 as te_0f6554 
                        FROM
                            (SELECT
                                r_f86a86.hd_dep_count as te_726209 
                            FROM
                                db1.household_demographics AS r_f86a86 
                            WHERE
                                r_f86a86.hd_demo_sk = r_f86a86.hd_dep_count 
                                OR r_f86a86.hd_demo_sk <= r_f86a86.hd_vehicle_count 
                            ORDER BY
                                1 DESC NULLS FIRST) AS r_062802 
                        RIGHT JOIN
                            CTE_171ee5 AS r_8fc8af 
                                ON date_from_unix_date(r_062802.te_726209) >= r_8fc8af.te_9f0344,
                            db1.store_returns AS r_e6be07 
                        RIGHT JOIN
                            db1.customer AS r_74e1fa 
                                ON r_e6be07.sr_fee >= r_74e1fa.c_current_addr_sk 
                        WHERE
                            r_062802.te_726209 < r_74e1fa.c_birth_year 
                            AND r_74e1fa.c_first_sales_date_sk <= r_e6be07.sr_reason_sk 
                            OR r_e6be07.sr_ticket_number <= 58 
                            OR r_e6be07.sr_reversed_charge IS NULL 
                            AND r_e6be07.sr_customer_sk = r_e6be07.sr_ticket_number 
                        ORDER BY
                            1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_8436d1 
                                ON r_74bf64.d_current_month NOT LIKE r_8436d1.te_718acc 
                        WHERE
                            r_74bf64.d_moy < 10 
                        ORDER BY
                            1 DESC
                    ) AS r_1ad3a4 
                INNER JOIN
                    CTE_1e411b AS r_21df8c 
                        ON r_1ad3a4.te_b16f09 = chr(r_21df8c.te_d1c1bf) 
                WHERE
                    r_21df8c.te_d1c1bf != 80.38466246 
                    OR r_21df8c.te_dcfeaf = 94.68554943 
                    AND r_21df8c.te_d1c1bf >= r_21df8c.te_dcfeaf 
                ORDER BY
                    1 NULLS FIRST;
----------->
SELECT
    r_6de6d1.s_store_id as te_8f577b 
FROM
    db1.store AS r_6de6d1 
LEFT JOIN
    db1.income_band AS r_970ae5 
        ON r_6de6d1.s_division_id >= r_970ae5.ib_lower_bound 
WHERE
    (
        NOT r_6de6d1.s_street_type ILIKE 'TUOVbBR' 
        AND r_970ae5.ib_upper_bound > 93 
        AND r_6de6d1.s_street_type NOT ILIKE '7x2SW4' 
        AND r_6de6d1.s_gmt_offset != r_6de6d1.s_tax_percentage
    ) 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    date_add(r_ebda23.web_rec_start_date,
    r_055e43.c_current_cdemo_sk) as te_a5c05c 
FROM
    db1.web_site AS r_ebda23 
INNER JOIN
    db1.customer AS r_055e43 
        ON r_ebda23.web_suite_number ILIKE r_055e43.c_email_address 
WHERE
    r_ebda23.web_mkt_class ILIKE 'U' 
    AND r_055e43.c_birth_day <= 42 
    AND r_ebda23.web_mkt_class NOT ILIKE 'ezm' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_d64431.cd_credit_rating as te_c1b502 
FROM
    db1.customer_demographics AS r_d64431 
WHERE
    r_d64431.cd_education_status NOT ILIKE r_d64431.cd_marital_status 
ORDER BY
    1 DESC NULLS FIRST 
LIMIT 29;
----------->
SELECT
    r_0003eb.t_time_sk as te_8e644c,
    decimal(r_7d4c75.cc_employees) as te_2ac1b9 
FROM
    db1.time_dim AS r_0003eb 
LEFT JOIN
    (
        SELECT
            r_51c03d.c_last_name as te_c92a24,
            r_51c03d.c_customer_sk as te_ccaa56 
        FROM
            db1.store_returns AS r_d47f45,
            db1.time_dim AS r_397ea7 
        LEFT JOIN
            db1.customer AS r_51c03d 
                ON r_397ea7.t_hour > r_51c03d.c_first_shipto_date_sk 
        WHERE
            r_d47f45.sr_customer_sk > r_51c03d.c_birth_day 
        ORDER BY
            1 ASC NULLS FIRST, 2 DESC NULLS FIRST 
        OFFSET 40
) AS r_d22110 
    ON r_0003eb.t_minute <= r_d22110.te_ccaa56,
db1.call_center AS r_7d4c75 WHERE
    r_0003eb.t_time = r_7d4c75.cc_sq_ft 
    OR EXISTS (
        SELECT
            r_3ce6d4.te_fe8860 as te_5baaa8,
            chr(r_6b20af.t_time) as te_5230b2 
        FROM
            db1.time_dim AS r_6b20af,
            (SELECT
                r_cbd57c.d_date as te_fe8860 
            FROM
                db1.date_dim AS r_cbd57c 
            WHERE
                r_cbd57c.d_current_year ILIKE 'UgDD') AS r_3ce6d4 
        WHERE
            chr(character_length(r_6b20af.t_sub_shift)) NOT LIKE chr(char_length(string(r_3ce6d4.te_fe8860))) 
            AND (
                NOT r_6b20af.t_meal_time NOT LIKE 'lpx6kg' 
                AND r_6b20af.t_hour < 62 
                AND r_6b20af.t_time > r_6b20af.t_second
            ) 
        ORDER BY
            1 NULLS LAST, 2) 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC 
        LIMIT 7;
----------->
SELECT
    r_30a97b.web_street_name as te_497d9a 
FROM
    db1.web_site AS r_30a97b 
INNER JOIN
    db1.web_page AS r_4f7b4b 
        ON r_30a97b.web_open_date_sk = r_4f7b4b.wp_access_date_sk 
WHERE
    r_30a97b.web_tax_percentage = 87.23816389 
    OR r_30a97b.web_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    AND r_30a97b.web_mkt_desc NOT LIKE 'LQFrTU' 
    OR r_30a97b.web_mkt_class > 'l' 
ORDER BY
    1;
----------->
SELECT
    r_efc12a.wp_web_page_id as te_13e6ea,
    make_timestamp(r_9820e5.sr_store_sk,
    r_9820e5.sr_cdemo_sk,
    r_9820e5.sr_returned_date_sk,
    r_9820e5.sr_ticket_number,
    r_efc12a.wp_web_page_sk,
    r_9820e5.sr_return_tax) as te_7e987f,
    r_efc12a.wp_rec_end_date as te_f58642 
FROM
    db1.web_page AS r_efc12a,
    db1.store_returns AS r_9820e5 
WHERE
    r_efc12a.wp_web_page_sk != r_9820e5.sr_return_ship_cost 
    AND r_efc12a.wp_type ILIKE 'kg7x2' 
    AND r_9820e5.web_county BETWEEN 26.03638728 AND 26.03638728 
    AND r_efc12a.wp_rec_start_date != r_efc12a.wp_rec_end_date 
GROUP BY
    2, 1, 3 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_e6db92.te_52ec4a as te_608204 
FROM
    (SELECT
        chr(r_f7fdca.i_manufact_id) as te_52ec4a 
    FROM
        db1.item AS r_f7fdca 
    INNER JOIN
        db1.warehouse AS r_f9d2b7 
            ON r_f7fdca.i_item_id NOT LIKE r_f9d2b7.w_county 
    WHERE
        (
            NOT r_f7fdca.i_color LIKE 'G' 
            AND r_f7fdca.i_class NOT ILIKE '8' 
            OR r_f9d2b7.w_warehouse_name LIKE 'RQiizz2kG'
        ) 
    ORDER BY
        1 ASC NULLS FIRST) AS r_e6db92 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_e15313.cc_tax_percentage as te_c7e329,
    r_e15313.cc_closed_date_sk as te_5476b1 
FROM
    db1.call_center AS r_e15313,
    db1.web_page AS r_761c2f 
INNER JOIN
    db1.time_dim AS r_8013c6 
        ON r_761c2f.wp_type LIKE r_8013c6.t_time_id 
WHERE
    r_e15313.cc_street_type ILIKE r_8013c6.t_sub_shift 
ORDER BY
    1 NULLS FIRST, 2 ASC NULLS LAST 
LIMIT 45;
----------->
(
    SELECT
        substring(r_1e3614.wp_type,
        r_1e3614.wp_max_ad_count) as te_1da932,
        r_1e3614.wp_type as te_67e596,
        r_2dcfd8.cd_marital_status as te_bb85a1 
    FROM
        (SELECT
            r_99bdbe.t_time_sk as te_25a348 
        FROM
            db1.household_demographics AS r_686cec 
        INNER JOIN
            db1.time_dim AS r_99bdbe 
                ON r_686cec.hd_vehicle_count != r_99bdbe.t_second 
        WHERE
            r_99bdbe.t_time_sk <= 67 
            AND r_99bdbe.t_hour >= 28 
            OR r_99bdbe.t_sub_shift NOT LIKE r_99bdbe.t_meal_time 
        ORDER BY
            1 ASC NULLS LAST) AS r_f05e1c, db1.customer_demographics AS r_2dcfd8 
    RIGHT JOIN
        db1.web_page AS r_1e3614 
            ON r_2dcfd8.cd_gender ILIKE r_1e3614.wp_type 
    WHERE
        r_f05e1c.te_25a348 < r_1e3614.wp_max_ad_count 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST 
    LIMIT 41) EXCEPT  SELECT
        r_c97f51.hd_buy_potential as te_7d4566, r_adf827.c_last_name as te_1d8c62, r_f153eb.ca_street_type as te_991542 FROM
            db1.customer_address AS r_f153eb 
        LEFT JOIN
            db1.customer AS r_adf827 
                ON r_f153eb.ca_county ILIKE r_adf827.c_last_name,
            db1.store_returns AS r_ae3e1c 
        RIGHT JOIN
            db1.household_demographics AS r_c97f51 
                ON r_ae3e1c.sr_hdemo_sk < r_c97f51.hd_dep_count 
        WHERE
            r_adf827.c_first_shipto_date_sk >= r_ae3e1c.sr_return_time_sk 
            AND r_ae3e1c.sr_return_tax BETWEEN r_adf827.c_current_hdemo_sk + 36.42055516 AND 74.92176258 
            OR r_f153eb.ca_address_id NOT LIKE 'Fr' 
        ORDER BY
            1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 DESC 
        LIMIT 26;
----------->
SELECT
    make_date(r_de34e1.t_time_sk,
    r_de34e1.pf_232a35,
    r_cdc361.d_date_sk) as te_47e449,
    r_b16339.s_street_type as te_ca0ede,
    r_fcd4e9.d_date as te_ccbbb0 
FROM
    db1.store AS r_b16339 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.time_dim PIVOT(min(t_second) AS pa_b8784c FOR t_time_id IN (('kg7x2SW') AS pf_769a9e,
            ('z2kGk4el') AS pf_584f2b,
            ('2') AS pf_232a35,
            ('X') AS pf_471390,
            ('G6sUgDDXX') AS pf_940446,
            ('XpRQii') AS pf_3dcd3c))
    ) AS r_de34e1 
        ON r_b16339.s_company_id != r_de34e1.t_hour,
    db1.date_dim AS r_fcd4e9 
RIGHT JOIN
    db1.date_dim AS r_cdc361 
        ON r_fcd4e9.d_date > r_cdc361.d_date 
WHERE
    r_b16339.s_suite_number ILIKE r_fcd4e9.d_day_name 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 
LIMIT 33;
----------->
SELECT
    r_3fee60.wp_autogen_flag as te_e19191 
FROM
    db1.web_page AS r_3fee60 
INNER JOIN
    db1.store AS r_85820c 
        ON r_3fee60.wp_rec_end_date != r_85820c.s_rec_start_date 
WHERE
    r_85820c.s_company_id <= 46 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_469bbb.cc_gmt_offset as te_031667,
    r_0ff6f1.te_5977ba + r_469bbb.cc_division as te_f3b032 
FROM
    db1.time_dim AS r_69988b,
    (SELECT
        r_f558c0.t_hour as te_af2a22,
        r_f558c0.t_hour - bigint(cos(r_f558c0.t_minute)) as te_5977ba,
        r_b15167.w_gmt_offset as te_b7e903 
    FROM
        (SELECT
            * 
        FROM
            db1.warehouse PIVOT(min(w_county) AS pa_f4427a FOR w_street_number IN (('4el') AS pf_fa039a,
            ('7x2') AS pf_2fd7ed,
            ('tb2Gv7RXp') AS pf_1274ae,
            ('XpRQ') AS pf_c18083,
            ('8hv95G6s') AS pf_a4f7ac))) AS r_b15167,
        db1.time_dim AS r_f558c0 
    WHERE
        r_b15167.w_gmt_offset < r_f558c0.t_minute 
        OR r_b15167.pf_fa039a NOT ILIKE r_b15167.pf_a4f7ac 
    ORDER BY
        1 ASC NULLS FIRST, 2 NULLS LAST, 3 NULLS LAST) AS r_0ff6f1 
    RIGHT JOIN
        db1.call_center AS r_469bbb 
            ON r_0ff6f1.te_b7e903 >= r_469bbb.cc_tax_percentage 
    WHERE
        r_69988b.t_shift LIKE r_469bbb.cc_zip 
        OR r_469bbb.cc_street_type LIKE 'v7' 
    GROUP BY
        2, 1 
    ORDER BY
        1 ASC NULLS FIRST, 2 DESC;
----------->
SELECT
    r_76639a.t_time_sk as te_475d06 
FROM
    db1.time_dim AS r_76639a 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 1;
----------->
SELECT
    reverse(r_757d5c.cc_call_center_id) as te_9c0acc 
FROM
    db1.call_center AS r_757d5c 
WHERE
    r_757d5c.cc_mkt_desc LIKE 'k4elBy7' 
    OR r_757d5c.cc_call_center_id ILIKE 'ntb' 
ORDER BY
    1 DESC 
OFFSET 2;
----------->
SELECT
    r_e6342c.t_shift as te_84ab1d 
FROM
    db1.time_dim AS r_e6342c 
WHERE
    r_e6342c.t_meal_time ILIKE r_e6342c.t_shift 
    OR r_e6342c.t_time_sk < 20 
    OR r_e6342c.t_time_id <= r_e6342c.t_meal_time 
    AND r_e6342c.t_sub_shift NOT LIKE r_e6342c.t_shift 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        r_3eb2cb.i_product_name as te_8d6e27,
        43 as te_dd44d6 
    FROM
        db1.customer_address AS r_b61615 
    INNER JOIN
        db1.item AS r_3eb2cb 
            ON r_b61615.ca_street_name LIKE r_3eb2cb.i_container,
        (SELECT
            r_cb9c98.web_company_id as te_e46259 
        FROM
            db1.web_site AS r_cb9c98 
        LEFT JOIN
            db1.item AS r_4e2b93 
                ON r_cb9c98.web_suite_number > r_4e2b93.i_size 
        WHERE
            r_cb9c98.web_mkt_class ILIKE 'DDXX' 
        ORDER BY
            1 DESC) AS r_6013cf 
    INNER JOIN
        db1.web_page AS r_22f677 
            ON r_6013cf.te_e46259 < r_22f677.wp_customer_sk 
    WHERE
        r_3eb2cb.i_item_sk < r_22f677.wp_link_count 
        AND r_22f677.wp_creation_date_sk > r_3eb2cb.i_class_id 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST) INTERSECT  (SELECT
            r_469d1b.s_market_manager as te_8fd982, bigint(e()) as te_a44491 
        FROM
            db1.time_dim AS r_da4d8c 
        LEFT JOIN
            db1.customer_address AS r_6a33c9 
                ON r_da4d8c.t_shift NOT ILIKE r_6a33c9.ca_street_number,
            db1.time_dim AS r_c640a6 
        INNER JOIN
            db1.store AS r_469d1b 
                ON r_c640a6.t_time_id NOT ILIKE r_469d1b.s_store_name 
        WHERE
            r_da4d8c.t_hour = r_469d1b.s_number_employees 
            AND r_6a33c9.ca_street_number NOT LIKE 'BRGC0' 
        ORDER BY
            1 NULLS FIRST, 2 DESC NULLS LAST) 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_8b9096.te_ef3be5 as te_f662c3 
FROM
    db1.time_dim AS r_d674ef 
INNER JOIN
    (
        WITH CTE_074fb6(te_293a17) AS (SELECT
            r_596420.sm_ship_mode_sk as te_293a17 
        FROM
            db1.ship_mode AS r_596420 
        WHERE
            r_596420.sm_contract = r_596420.sm_code 
            AND r_596420.sm_type ILIKE 'RIvX' 
        ORDER BY
            1 DESC NULLS FIRST), CTE_3abb98(te_8f236f) AS (SELECT
            make_date(hash(negative(e() - pi()), current_date()), r_f2249d.ca_address_sk, r_f2249d.ca_address_sk) as te_8f236f 
        FROM
            db1.customer_address AS r_f2249d 
        WHERE
            r_f2249d.ca_street_type ILIKE 'W4P' 
            OR r_f2249d.ca_county NOT ILIKE 'lBy7I' 
            OR r_f2249d.ca_state ILIKE 'l' 
            OR r_f2249d.ca_location_type NOT LIKE 'VbB' 
        ORDER BY
            1 DESC NULLS LAST) SELECT
            r_b78fbe.cc_rec_end_date as te_c2fd58, r_b78fbe.cc_call_center_id as te_ef3be5 
        FROM
            CTE_074fb6 AS r_1bd6a8,
            CTE_3abb98 AS r_d6a4f1 
        LEFT JOIN
            db1.call_center AS r_b78fbe 
                ON r_d6a4f1.te_8f236f = r_b78fbe.cc_rec_end_date 
        WHERE
            r_1bd6a8.te_293a17 >= r_b78fbe.cc_employees 
        ORDER BY
            1 ASC NULLS FIRST, 2 DESC NULLS LAST
    ) AS r_8b9096 
        ON r_d674ef.t_shift NOT LIKE r_8b9096.te_ef3be5 
WHERE
    r_d674ef.t_sub_shift ILIKE 'XX4Q' 
    AND r_d674ef.t_time_id ILIKE r_d674ef.t_am_pm 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_96d108.c_first_shipto_date_sk / 88.67399245D as te_807b13 
FROM
    db1.customer AS r_96d108 
WHERE
    r_96d108.c_birth_year < 71 
    OR r_96d108.c_birth_day >= 72 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_08de1b.r_reason_desc as te_0cf3c6 
FROM
    (WITH CTE_2fcc71(te_db06df) AS (SELECT
        r_9f4ccf.sr_return_ship_cost as te_db06df 
    FROM
        db1.time_dim AS r_07db8e 
    INNER JOIN
        db1.store_returns AS r_9f4ccf 
            ON r_07db8e.t_time = r_9f4ccf.sr_returned_date_sk 
    WHERE
        r_9f4ccf.sr_net_loss = 2.40880954 
        AND r_9f4ccf.sr_fee != 70.45040251 
    ORDER BY
        1 DESC), CTE_825f7e(te_6fdb37, te_b38ed5) AS (WITH CTE_f5237e(te_a9b99b) AS (SELECT
        r_4e86e8.hd_vehicle_count as te_a9b99b 
    FROM
        db1.item AS r_93553b 
    RIGHT JOIN
        db1.household_demographics AS r_4e86e8 
            ON r_93553b.i_size != r_4e86e8.hd_buy_potential 
    WHERE
        r_93553b.i_brand NOT LIKE ((SELECT
            r_4502a0.te_958f26 as te_7f25b7 
        FROM
            db1.customer_demographics AS r_f50707 
        RIGHT JOIN
            (SELECT
                r_63fbdd.d_following_holiday as te_f100be,
                r_4c9ba3.hd_buy_potential as te_958f26,
                0 / r_4c9ba3.hd_vehicle_count as te_ec0fd1 
            FROM
                db1.household_demographics AS r_4c9ba3 
            LEFT JOIN
                (SELECT
                    * 
                FROM
                    db1.web_page UNPIVOT INCLUDE NULLS ((up_634559,
                    up_d40f61,
                    up_f968a2,
                    up_32eb3a) FOR upn_262562 IN ((wp_creation_date_sk,
                    wp_type,
                    wp_rec_end_date,
                    wp_autogen_flag) AS UPF_0b8a20,
                    (wp_link_count,
                    wp_url,
                    wp_rec_start_date,
                    wp_web_page_id) AS UPF_b097e8))) AS r_335b2d 
                    ON r_4c9ba3.hd_buy_potential ILIKE r_335b2d.up_d40f61,
                db1.date_dim AS r_63fbdd 
            WHERE
                r_335b2d.up_f968a2 <= r_63fbdd.d_date 
            ORDER BY
                1, 2 DESC, 3 ASC) AS r_4502a0 
                    ON r_f50707.cd_credit_rating NOT ILIKE r_4502a0.te_958f26 
            WHERE
                r_f50707.cd_credit_rating <= 'RXpR' 
                OR r_f50707.cd_gender NOT LIKE 'ZgGI' 
            ORDER BY
                1 NULLS LAST 
            LIMIT 1 OFFSET 58)) 
        OR r_93553b.i_item_sk != 21 
        AND r_93553b.i_current_price IS NOT NULL ORDER BY
            1 DESC NULLS FIRST), CTE_e24e76(te_0663b2, te_6fefc9) AS (SELECT
            make_timestamp(r_0153d4.cc_sq_ft, r_0153d4.cc_open_date_sk, r_0153d4.cc_mkt_id, r_c774ba.web_site_sk, r_0153d4.cc_employees, r_c774ba.web_mkt_id) as te_0663b2, r_c774ba.web_close_date_sk / 13.60116895D as te_6fefc9 
        FROM
            db1.web_site AS r_c774ba 
        RIGHT JOIN
            (SELECT
                r_11bc59.i_current_price as te_e8eaaf,
                mod(10 - r_11bc59.i_brand_id - unix_timestamp(),
                unix_timestamp()) as te_53e8c5,
                r_11bc59.i_current_price as te_50e68a 
            FROM
                db1.item AS r_11bc59 
            INNER JOIN
                db1.reason AS r_de8244 
                    ON r_11bc59.i_class_id <= r_de8244.r_reason_sk,
                (WITH CTE_c935e8(te_b38afe,
                te_8984e9,
                te_2fce97) AS (SELECT
                    r_8f9e67.w_gmt_offset as te_b38afe,
                    decimal(r_9818a2.cd_purchase_estimate) as te_8984e9,
                    r_8f9e67.w_gmt_offset as te_2fce97 
                FROM
                    db1.warehouse AS r_8f9e67,
                    db1.customer_demographics AS r_9818a2 
                WHERE
                    r_8f9e67.w_county NOT LIKE r_9818a2.cd_gender 
                ORDER BY
                    1 DESC NULLS LAST, 2 NULLS LAST, 3 NULLS LAST) SELECT
                    r_477998.t_time_sk as te_7d7e80 
                FROM
                    db1.time_dim AS r_477998 
                RIGHT JOIN
                    CTE_c935e8 AS r_a2a70f 
                        ON r_477998.t_second > r_a2a70f.te_b38afe 
                WHERE
                    r_a2a70f.te_2fce97 >= r_a2a70f.te_8984e9 
                    OR NOT r_477998.t_hour <= 61 
                    AND r_477998.t_time_sk = r_477998.t_time 
                ORDER BY
                    1 ASC 
                OFFSET 64) AS r_7db377 WHERE
                r_11bc59.i_manufact_id != r_7db377.te_7d7e80 
                AND (NOT r_11bc59.i_category_id <= 98 
                AND r_11bc59.i_item_sk = r_7db377.te_7d7e80 
                OR (r_11bc59.i_wholesale_cost, r_11bc59.i_item_id) NOT IN (SELECT
                    44.57888307 as te_f2eef8,
                    r_4203d6.d_current_day as te_522842 
                FROM
                    db1.household_demographics AS r_acc014 
                RIGHT JOIN
                    db1.date_dim AS r_4203d6 
                        ON r_acc014.hd_vehicle_count <= r_4203d6.d_moy 
                WHERE
                    r_4203d6.d_current_year NOT LIKE 'z2kGk' 
                    AND r_4203d6.d_day_name = r_4203d6.d_current_quarter 
                    OR r_4203d6.d_month_seq > ((SELECT
                        hash(pi()) as te_48d13d 
                    FROM
                        (SELECT
                            r_f6b75c.wp_rec_start_date as te_bbc087,
                            r_f6b75c.wp_url as te_a65776 
                        FROM
                            db1.web_page AS r_f6b75c 
                        INNER JOIN
                            db1.customer_demographics AS r_0fc13c 
                                ON r_f6b75c.wp_link_count > r_0fc13c.cd_demo_sk,
                            db1.reason AS r_6be62a 
                        WHERE
                            r_f6b75c.wp_image_count >= r_6be62a.r_reason_sk 
                            AND r_6be62a.r_reason_desc NOT LIKE r_f6b75c.wp_type 
                            OR r_f6b75c.wp_access_date_sk < r_f6b75c.wp_max_ad_count 
                        ORDER BY
                            1 DESC NULLS LAST, 2 ASC NULLS LAST 
                        LIMIT 67) AS r_d0d188 LEFT JOIN
                        db1.reason AS r_ba263e 
                            ON r_d0d188.te_a65776 ILIKE r_ba263e.r_reason_id 
                    WHERE
                        r_d0d188.te_a65776 < 'bBRGC0' 
                        OR r_d0d188.te_bbc087 = DATE'2024-10-11' 
                    ORDER BY
                        1 ASC 
                    LIMIT 1)) 
                OR r_acc014.hd_dep_count BETWEEN 79 AND 79)) ORDER BY
                    1 DESC, 2 DESC, 3 DESC NULLS LAST) AS r_9682d7 
                    ON r_c774ba.web_gmt_offset != r_9682d7.te_50e68a,
                db1.call_center AS r_0153d4 
            WHERE
                r_c774ba.web_county NOT LIKE r_0153d4.cc_zip 
                OR r_0153d4.cc_rec_start_date != r_c774ba.web_rec_end_date 
            ORDER BY
                1 ASC NULLS FIRST, 2 DESC NULLS LAST) SELECT
                    r_2d6346.te_0663b2 as te_6fdb37, r_5204f8.te_a9b99b as te_b38ed5 
                FROM
                    CTE_f5237e AS r_5204f8 
                RIGHT JOIN
                    CTE_e24e76 AS r_2d6346 
                        ON r_5204f8.te_a9b99b <= r_2d6346.te_6fefc9,
                    (SELECT
                        * 
                    FROM
                        (SELECT
                            r_dd47c5.ca_country as te_dd6029,
                            r_ca5362.r_reason_sk as te_1baa37 
                        FROM
                            db1.reason AS r_ca5362,
                            db1.warehouse AS r_ef2b66 
                        RIGHT JOIN
                            db1.customer_address AS r_dd47c5 
                                ON r_ef2b66.w_street_number NOT ILIKE r_dd47c5.ca_street_number 
                        WHERE
                            r_ca5362.r_reason_id LIKE r_ef2b66.w_warehouse_name 
                        ORDER BY
                            1 DESC NULLS LAST, 2 DESC NULLS LAST) PIVOT(count(te_dd6029) AS pa_f84fa8 FOR te_1baa37 IN ((78) AS pf_d0bd5b, (12) AS pf_7833c1))) AS r_76ec9c 
                    WHERE
                        bigint(r_5204f8.te_a9b99b) <= r_76ec9c.pf_d0bd5b 
                    ORDER BY
                        1 ASC, 2 DESC NULLS LAST) SELECT
                        r_05d178.sr_return_tax as te_0bc198, r_05d178.sr_return_amt_inc_tax as te_c8ce05 
                    FROM
                        CTE_2fcc71 AS r_6273dd 
                    RIGHT JOIN
                        db1.store_returns AS r_05d178 
                            ON r_6273dd.te_db06df < r_05d178.sr_fee,
                        db1.income_band AS r_a256c4 
                    WHERE
                        r_05d178.sr_hdemo_sk <= 9 
                        OR r_a256c4.ib_lower_bound = 92 
                    ORDER BY
                        1 NULLS LAST, 2 DESC NULLS FIRST) AS r_b9af9d 
                LEFT JOIN
                    db1.reason AS r_08de1b 
                        ON r_b9af9d.te_0bc198 >= r_08de1b.r_reason_sk 
                ORDER BY
                    1 ASC NULLS LAST;
----------->
SELECT
    r_b16f3e.ca_address_id as te_48694a 
FROM
    db1.customer_address AS r_b16f3e 
WHERE
    r_b16f3e.ca_county ILIKE r_b16f3e.ca_street_name 
    OR r_b16f3e.ca_country > 'lezmS' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_08b2d6.w_gmt_offset as te_0ba99a,
    hash(r_08b2d6.w_suite_number,
    DATE'2024-03-25') as te_7193e9,
    r_08b2d6.w_warehouse_id as te_29d5b4 
FROM
    (SELECT
        chr(r_b538ff.w_warehouse_sq_ft) as te_1eb1aa 
    FROM
        db1.warehouse AS r_b538ff 
    WHERE
        r_b538ff.w_suite_number NOT LIKE r_b538ff.w_warehouse_id 
    GROUP BY
        1 
    ORDER BY
        1 DESC NULLS LAST) AS r_5138be, db1.warehouse AS r_08b2d6 
RIGHT JOIN
    db1.household_demographics AS r_c160c3 
        ON r_08b2d6.w_warehouse_sq_ft = r_c160c3.hd_dep_count 
WHERE
    r_5138be.te_1eb1aa LIKE r_08b2d6.w_county 
    AND r_c160c3.hd_buy_potential LIKE r_08b2d6.w_warehouse_name 
    OR r_c160c3.hd_income_band_sk IS NULL 
    OR r_5138be.te_1eb1aa NOT LIKE r_08b2d6.w_county 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_673282.cc_call_center_id as te_cf9cd0,
    e() as te_9e7045,
    r_673282.cc_gmt_offset as te_56fd06 
FROM
    (SELECT
        r_016305.cc_open_date_sk as te_9555d1,
        length(r_016305.cc_manager) as te_d1d505 
    FROM
        db1.warehouse AS r_d2f24e,
        db1.date_dim AS r_4929a4 
    INNER JOIN
        db1.call_center AS r_016305 
            ON r_4929a4.d_date <= r_016305.cc_rec_end_date 
    WHERE
        r_d2f24e.w_gmt_offset > r_016305.cc_sq_ft 
        AND (
            NOT r_d2f24e.w_gmt_offset IN (
                SELECT
                    r_537ff9.ca_gmt_offset as te_7ccfa8 
                FROM
                    db1.customer_address AS r_537ff9 
                RIGHT JOIN
                    db1.customer AS r_cee2ef 
                        ON r_537ff9.ca_gmt_offset = r_cee2ef.c_first_shipto_date_sk,
                    db1.warehouse AS r_356561 
                WHERE
                    r_cee2ef.c_first_shipto_date_sk < r_356561.w_gmt_offset 
                    OR r_537ff9.ca_address_id NOT ILIKE 'x2SW4' 
                    AND r_356561.w_warehouse_id ILIKE r_356561.w_street_name 
                    OR r_356561.w_street_name ILIKE 'sUgDDXX4Q' 
                    OR r_cee2ef.c_birth_day != 69 
                ORDER BY
                    1 DESC
            ) 
            AND r_016305.cc_open_date_sk BETWEEN 38 AND 38
        ) 
    ORDER BY
        1 ASC, 2 NULLS LAST 
    OFFSET 46) AS r_232602 LEFT JOIN
        db1.reason AS r_55ceac 
            ON r_232602.te_9555d1 > r_55ceac.r_reason_sk,
        db1.income_band AS r_2867d3 
    RIGHT JOIN
        db1.call_center AS r_673282 
            ON r_2867d3.ib_upper_bound != r_673282.cc_division 
    WHERE
        r_55ceac.r_reason_id NOT LIKE r_673282.cc_state 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    make_timestamp(r_9f02c7.c_birth_day,
    r_9f02c7.c_current_hdemo_sk,
    r_9f02c7.c_current_cdemo_sk,
    r_9f02c7.c_birth_month,
    r_9f02c7.c_current_addr_sk,
    42.30741889) as te_f807f3 
FROM
    db1.customer AS r_9f02c7 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_6535d3.i_rec_start_date as te_1c3645,
    r_f3a226.wp_customer_sk as te_662d54,
    r_6535d3.i_manager_id as te_c6b8c3 
FROM
    db1.item AS r_6535d3 
INNER JOIN
    db1.web_page AS r_f3a226 
        ON r_6535d3.i_item_id NOT ILIKE r_f3a226.wp_web_page_id,
    (SELECT
        e() as te_6e0033 
    FROM
        (SELECT
            * 
        FROM
            db1.time_dim PIVOT(min(t_time_id) AS pa_2acbe9 FOR t_minute IN ((42) AS pf_271548,
            (71) AS pf_0b5a38,
            (87) AS pf_dccd80))) AS r_838b88 
    RIGHT JOIN
        db1.warehouse AS r_8353b4 
            ON r_838b88.pf_271548 ILIKE r_8353b4.w_street_name 
    WHERE
        r_8353b4.w_warehouse_sq_ft >= r_838b88.t_time_sk 
        OR r_838b88.pf_271548 NOT ILIKE r_838b88.t_sub_shift 
        OR r_8353b4.w_warehouse_sq_ft > 28 
        OR r_8353b4.w_warehouse_sk <= r_838b88.t_time 
    ORDER BY
        1 NULLS FIRST) AS r_b2394f 
    LEFT JOIN
        db1.income_band AS r_c5eca0 
            ON r_b2394f.te_6e0033 != r_c5eca0.ib_lower_bound 
    WHERE
        r_f3a226.wp_web_page_sk > r_c5eca0.ib_lower_bound 
        OR r_c5eca0.ib_lower_bound < 52 
        AND r_6535d3.i_item_id NOT LIKE 'Uuls' 
        AND r_6535d3.i_formulation NOT LIKE 'mSRIvXUuls' 
    GROUP BY
        2, 1, 3 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_b9f3a1.web_gmt_offset as te_cfb51b,
    r_b9f3a1.web_rec_start_date as te_c27277 
FROM
    db1.web_site AS r_b9f3a1,
    (SELECT
        substring(r_65f6b6.w_street_type,
        r_6ba478.d_dom) as te_4b88a4,
        r_6ba478.d_date as te_e84150,
        r_6ba478.d_current_quarter as te_f206a5 
    FROM
        db1.date_dim AS r_6ba478,
        db1.warehouse AS r_65f6b6 
    WHERE
        r_6ba478.d_holiday NOT LIKE r_65f6b6.w_county 
        OR NOT r_65f6b6.w_state NOT ILIKE r_65f6b6.w_street_number 
        AND NOT r_6ba478.d_qoy <= r_6ba478.d_year 
        OR (
            NOT r_6ba478.d_dom = 28 
            OR r_6ba478.d_fy_year != 52
        ) 
    ORDER BY
        1 ASC, 2 DESC NULLS LAST, 3 ASC NULLS FIRST) AS r_b8c51f 
LEFT JOIN
    db1.household_demographics AS r_a76d12 
        ON r_b8c51f.te_f206a5 LIKE r_a76d12.hd_buy_potential 
WHERE
    r_b9f3a1.web_state NOT LIKE r_b8c51f.te_f206a5 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_2d3c28.cc_division as te_0b5dea,
    r_194d2d.sr_reversed_charge as te_20400a,
    r_2d3c28.cc_rec_start_date as te_a20e2b 
FROM
    db1.ship_mode AS r_e86851 
INNER JOIN
    db1.call_center AS r_2d3c28 
        ON r_e86851.sm_ship_mode_id = r_2d3c28.cc_street_type,
    db1.store_returns AS r_194d2d 
LEFT JOIN
    db1.warehouse AS r_a41247 
        ON r_194d2d.sr_refunded_cash > r_a41247.w_gmt_offset 
WHERE
    r_2d3c28.cc_open_date_sk != r_194d2d.sr_fee 
    AND r_194d2d.web_county > 85.72099789 
    OR r_2d3c28.cc_sq_ft < (
        (
            WITH CTE_a253d0(te_bea3c5) AS (SELECT
                chr(r_8f975e.hd_demo_sk) as te_bea3c5 
            FROM
                db1.household_demographics AS r_8f975e 
            WHERE
                r_8f975e.hd_demo_sk BETWEEN 0 AND 0 
            ORDER BY
                1 ASC NULLS LAST) SELECT
                r_11cba3.cd_demo_sk as te_cdc7d2 
            FROM
                CTE_a253d0 AS r_4a05a3,
                (SELECT
                    r_7f20d0.t_hour as te_e2ff74,
                    r_7f20d0.t_am_pm as te_5ab307,
                    r_ec073c.cd_marital_status as te_b5ee6e 
                FROM
                    db1.time_dim AS r_7f20d0 
                RIGHT JOIN
                    db1.customer_demographics AS r_ec073c 
                        ON r_7f20d0.t_second = r_ec073c.cd_dep_count,
                    db1.customer_demographics AS r_c7725e 
                WHERE
                    r_7f20d0.t_time != r_c7725e.cd_dep_count 
                    AND r_ec073c.cd_dep_college_count > 74 
                    OR r_c7725e.cd_credit_rating NOT ILIKE r_ec073c.cd_credit_rating 
                ORDER BY
                    1 ASC NULLS FIRST, 2 DESC NULLS LAST, 3 ASC NULLS LAST 
                OFFSET 39) AS r_53d71b RIGHT JOIN
                db1.customer_demographics AS r_11cba3 
                    ON r_53d71b.te_e2ff74 <= r_11cba3.cd_dep_count 
            WHERE
                r_4a05a3.te_bea3c5 NOT LIKE r_11cba3.cd_marital_status 
                OR r_11cba3.cd_credit_rating NOT LIKE r_11cba3.cd_education_status 
            ORDER BY
                1 DESC NULLS LAST 
            LIMIT 1)
    ) 
    AND r_a41247.w_warehouse_sk >= r_2d3c28.cc_division 
    OR r_a41247.w_zip ILIKE r_2d3c28.cc_street_name ORDER BY
        1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 DESC;
----------->
SELECT
    r_189d71.s_floor_space as te_28ae9a,
    hash(r_9059c8.cd_education_status,
    true) as te_851283 
FROM
    db1.store AS r_189d71 
RIGHT JOIN
    db1.customer_demographics AS r_9059c8 
        ON r_189d71.s_market_id <= r_9059c8.cd_dep_college_count,
    db1.call_center AS r_4ece80 
WHERE
    r_189d71.s_rec_start_date > r_4ece80.cc_rec_start_date 
ORDER BY
    1 DESC NULLS LAST, 2;
----------->
SELECT
    chr(r_dbae84.sr_store_sk) as te_1e2fde,
    r_dbae84.sr_return_amt as te_17c8c0 
FROM
    db1.store AS r_988474 
INNER JOIN
    db1.customer_demographics AS r_511b8e 
        ON r_988474.s_market_id != r_511b8e.cd_demo_sk,
    db1.store_returns AS r_dbae84 
WHERE
    r_511b8e.cd_dep_employed_count = r_dbae84.sr_return_amt_inc_tax 
    OR r_dbae84.web_county < r_dbae84.sr_refunded_cash 
    AND r_dbae84.sr_reversed_charge > r_dbae84.sr_return_amt_inc_tax 
    OR NOT r_988474.s_rec_start_date <= r_988474.s_rec_end_date 
    OR r_511b8e.cd_dep_employed_count > 83 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_ef521b.wp_rec_start_date as te_77a378 
FROM
    (SELECT
        r_cb8bd3.w_warehouse_name as te_2bc7da,
        r_cb8bd3.w_gmt_offset as te_f57070,
        r_cb8bd3.w_gmt_offset + unix_timestamp() + unix_timestamp() as te_0c69e4 
    FROM
        (SELECT
            r_20c415.c_first_shipto_date_sk as te_bf2053 
        FROM
            db1.customer AS r_20c415 
        LEFT JOIN
            db1.household_demographics AS r_1d8786 
                ON r_20c415.c_email_address ILIKE r_1d8786.hd_buy_potential 
        WHERE
            NOT r_20c415.c_current_hdemo_sk > 67 
            OR r_20c415.c_first_shipto_date_sk > 52 
            OR r_1d8786.hd_buy_potential LIKE r_20c415.c_email_address 
        GROUP BY
            1 
        ORDER BY
            1 NULLS FIRST) AS r_80864e, db1.warehouse AS r_cb8bd3 
    RIGHT JOIN
        db1.customer_demographics AS r_4fb5db 
            ON r_cb8bd3.w_warehouse_sq_ft != r_4fb5db.cd_demo_sk 
    WHERE
        r_80864e.te_bf2053 != r_4fb5db.cd_demo_sk 
    ORDER BY
        1 ASC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST) AS r_690cf5 
    INNER JOIN
        db1.web_page AS r_ef521b 
            ON r_690cf5.te_2bc7da != r_ef521b.wp_web_page_id 
    WHERE
        r_ef521b.wp_max_ad_count > 98 
        OR r_ef521b.wp_url NOT ILIKE 'y7IG7i' 
        AND r_ef521b.wp_rec_start_date <= r_ef521b.wp_rec_end_date 
    ORDER BY
        1 NULLS LAST;
----------->
SELECT
    r_d0cdcd.d_date as te_26fbf3,
    r_d0cdcd.d_quarter_seq as te_7f451e 
FROM
    db1.date_dim AS r_d0cdcd 
LEFT JOIN
    db1.reason AS r_cfb708 
        ON r_d0cdcd.d_quarter_name >= r_cfb708.r_reason_desc,
    db1.customer_demographics AS r_f22c95 
WHERE
    r_d0cdcd.d_holiday ILIKE r_f22c95.cd_marital_status 
    OR r_d0cdcd.d_date_sk != 58 
    OR r_d0cdcd.d_holiday ILIKE r_d0cdcd.d_current_day 
    AND r_d0cdcd.d_year != 9 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_7f5fb2.hd_buy_potential as te_ea0897 
FROM
    db1.household_demographics AS r_7f5fb2 
WHERE
    r_7f5fb2.hd_income_band_sk >= r_7f5fb2.hd_dep_count 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_4d1a1f.w_gmt_offset as te_a295d1 
FROM
    db1.warehouse AS r_4d1a1f 
RIGHT JOIN
    db1.customer_demographics AS r_3e6e36 
        ON r_4d1a1f.w_state LIKE r_3e6e36.cd_marital_status 
WHERE
    (
        NOT r_3e6e36.cd_dep_count < r_3e6e36.cd_dep_college_count 
        AND (
            NOT r_4d1a1f.w_country LIKE r_4d1a1f.w_zip 
            OR r_4d1a1f.w_street_type > r_4d1a1f.w_state
        )
    ) 
ORDER BY
    1 DESC;
----------->
SELECT
    r_4b115c.ca_county as te_bb06ab,
    now() as te_c0fb18,
    reverse(r_cfae4e.t_sub_shift) as te_4cab19 
FROM
    db1.customer_address AS r_4b115c 
LEFT JOIN
    db1.customer AS r_4f3f01 
        ON r_4b115c.ca_suite_number NOT LIKE r_4f3f01.c_preferred_cust_flag,
    db1.time_dim AS r_cfae4e 
LEFT JOIN
    db1.call_center AS r_7986d7 
        ON r_cfae4e.t_time_sk > r_7986d7.cc_division 
WHERE
    r_4f3f01.c_last_review_date_sk > r_7986d7.cc_sq_ft 
    OR NOT r_4f3f01.c_login LIKE r_4f3f01.c_salutation 
    AND r_7986d7.cc_division = 2 
    OR r_4f3f01.c_birth_year = 5 
ORDER BY
    1 DESC NULLS FIRST, 2, 3 DESC;
----------->
SELECT
    r_9cdcf0.c_last_name as te_335331 
FROM
    db1.customer AS r_9cdcf0 
ORDER BY
    1;
----------->
SELECT
    r_e91877.cd_gender as te_a08f26,
    reflect('java.util.UUID',
    'randomUUID') as te_e01234,
    r_76952d.c_birth_country as te_b392e6 
FROM
    db1.customer AS r_76952d,
    db1.customer_demographics AS r_e91877 
WHERE
    r_76952d.c_salutation <= r_e91877.cd_gender 
    OR r_e91877.cd_dep_count > 31 
    AND r_76952d.c_birth_country LIKE r_76952d.c_last_name 
    AND r_76952d.c_current_addr_sk >= 76 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC;
----------->
SELECT
    to_char(39.38912189D / r_a94331.cd_dep_count,
    '000D00') + 87 - r_a94331.cd_purchase_estimate as te_5052c4 
FROM
    db1.customer_demographics AS r_a94331 
RIGHT JOIN
    db1.income_band AS r_37ac29 
        ON r_a94331.cd_demo_sk > r_37ac29.ib_upper_bound 
GROUP BY
    1 
ORDER BY
    1 NULLS LAST 
LIMIT 35;
----------->
SELECT
    r_15d15d.cd_credit_rating as te_f7ca88 
FROM
    db1.customer_demographics AS r_15d15d 
WHERE
    r_15d15d.cd_purchase_estimate > 45 
    OR r_15d15d.cd_marital_status NOT ILIKE 'y7IG7' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_f1a28a.c_customer_id as te_707437 
FROM
    db1.time_dim AS r_a66e50 
INNER JOIN
    db1.customer AS r_f1a28a 
        ON r_a66e50.t_shift NOT ILIKE r_f1a28a.c_preferred_cust_flag 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    to_char(r_24d9e4.d_month_seq / r_24d9e4.d_year,
    '000D00') as te_ad3bc8 
FROM
    db1.date_dim AS r_24d9e4 
WHERE
    NOT r_24d9e4.d_dom < 7 
    AND r_24d9e4.d_dom = 30 
ORDER BY
    1 DESC 
LIMIT 54;
----------->
SELECT
    r_896666.w_warehouse_name as te_c10693,
    r_896666.w_county as te_75937f 
FROM
    db1.warehouse AS r_896666 
INNER JOIN
    db1.income_band AS r_626eee 
        ON r_896666.w_gmt_offset < r_626eee.ib_upper_bound,
    db1.household_demographics AS r_ce1207 
WHERE
    r_896666.w_warehouse_sk != r_ce1207.hd_vehicle_count 
    AND r_ce1207.hd_income_band_sk > 44 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
(
    SELECT
        r_1affa8.t_shift as te_06e6dd 
    FROM
        db1.time_dim AS r_1affa8 
    WHERE
        r_1affa8.t_time <= 73 
        OR r_1affa8.t_time_sk BETWEEN r_1affa8.t_second + 55 AND 4 
    ORDER BY
        1 DESC NULLS LAST
) EXCEPT  SELECT
    substring(r_ed24d7.cc_zip, 61) as te_6eed8f 
FROM
    db1.call_center AS r_ed24d7 
WHERE
    r_ed24d7.cc_country = 'UOVbBR' 
    OR NOT r_ed24d7.cc_rec_start_date < r_ed24d7.cc_rec_end_date 
    OR r_ed24d7.cc_rec_start_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_6ae496.r_reason_sk as te_0453d7 
FROM
    db1.reason AS r_6ae496 
WHERE
    r_6ae496.r_reason_desc LIKE 'k4elBy7IG' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_35e695.sm_contract as te_a772e3,
    string(r_b9d326.d_date_id) as te_c41f83,
    r_b9d326.d_current_month as te_12a1f3 
FROM
    db1.ship_mode AS r_35e695,
    db1.date_dim AS r_b9d326 
WHERE
    r_35e695.sm_ship_mode_sk > r_b9d326.d_fy_year 
    AND r_b9d326.d_fy_week_seq > 37 
    AND r_35e695.sm_carrier LIKE r_35e695.sm_type 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC 
OFFSET 50;
----------->
SELECT
    r_444215.pf_d9ec40 as te_b7908b,
    r_444215.pf_b13828 as te_bd0d0c 
FROM
    (SELECT
        r_72e000.t_am_pm as te_99e17f,
        add_months(date_from_unix_date(r_dd7b0a.c_last_review_date_sk),
        r_dd7b0a.c_current_addr_sk) as te_60f033 
    FROM
        db1.customer AS r_dd7b0a 
    RIGHT JOIN
        db1.time_dim AS r_72e000 
            ON r_dd7b0a.c_first_shipto_date_sk < r_72e000.t_time,
        db1.ship_mode AS r_a78ed0 
    WHERE
        r_dd7b0a.c_login LIKE r_a78ed0.sm_ship_mode_id 
        AND r_72e000.t_hour >= 90 
    ORDER BY
        1 DESC NULLS FIRST, 2 NULLS LAST 
    LIMIT 41) AS r_2db0a9, (SELECT
    * FROM
        db1.web_page PIVOT(max(wp_type) AS pa_67f1a5 FOR wp_url IN (('RGC0lez') AS pf_b13828,
        ('ntb2Gv7RX') AS pf_d9ec40))) AS r_444215 
RIGHT JOIN
    db1.web_page AS r_a50fc4 
        ON r_444215.pf_b13828 IS NULL 
WHERE
    r_2db0a9.te_60f033 < r_444215.wp_rec_end_date 
    OR r_2db0a9.te_60f033 IN (
        SELECT
            current_date() as te_6f12e8 
        FROM
            db1.ship_mode AS r_d47b52 
        RIGHT JOIN
            (
                SELECT
                    to_char(mod(r_931425.sr_hdemo_sk,
                    54.02800105D),
                    '000D00') as te_f5115d,
                    r_931425.sr_cdemo_sk as te_2d0550,
                    r_931425.sr_item_sk as te_3e79cc 
                FROM
                    db1.income_band AS r_214bad,
                    db1.store_returns AS r_931425 
                WHERE
                    r_214bad.ib_upper_bound >= r_931425.sr_ticket_number 
                    OR r_214bad.ib_upper_bound > 67 
                    AND r_931425.sr_returned_date_sk <= r_931425.sr_addr_sk 
                    OR r_931425.sr_customer_sk != 18 
                ORDER BY
                    1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST
            ) AS r_bce7df 
                ON r_d47b52.sm_ship_mode_sk >= r_bce7df.te_3e79cc 
        WHERE
            r_d47b52.sm_code NOT ILIKE 'v' 
            OR r_bce7df.te_2d0550 >= 86 
            AND r_d47b52.sm_contract LIKE r_d47b52.sm_carrier 
            OR EXISTS (
                SELECT
                    btrim(r_f55518.wp_autogen_flag) as te_51df31 
                FROM
                    db1.web_page AS r_f55518 
                ORDER BY
                    1 ASC
            ) 
        ORDER BY
            1 ASC 
        OFFSET 61) 
        OR r_444215.wp_creation_date_sk != 73 
        OR r_a50fc4.wp_max_ad_count >= r_444215.wp_max_ad_count ORDER BY
            1 ASC, 2 ASC NULLS FIRST;
----------->
SELECT
    r_299f0a.r_reason_desc as te_820068,
    r_7afd03.cd_credit_rating as te_f91b2a,
    r_7afd03.cd_marital_status as te_4e9e35 
FROM
    db1.customer_demographics AS r_7afd03 
RIGHT JOIN
    db1.reason AS r_8566af 
        ON r_7afd03.cd_gender ILIKE r_8566af.r_reason_desc,
    db1.reason AS r_299f0a 
WHERE
    r_8566af.r_reason_desc LIKE r_299f0a.r_reason_desc 
    AND r_7afd03.cd_dep_count = 25 
    AND r_8566af.r_reason_desc ILIKE 'CMa' 
    OR r_299f0a.r_reason_sk >= 10 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    current_date() as te_94afdc 
FROM
    db1.call_center AS r_404949 
LEFT JOIN
    db1.warehouse AS r_227705 
        ON r_404949.cc_street_name ILIKE r_227705.w_warehouse_id 
WHERE
    r_404949.cc_suite_number NOT LIKE '7x2SW4Pg' 
    OR r_404949.cc_call_center_sk IS NOT NULL 
    OR r_404949.cc_tax_percentage >= r_227705.w_gmt_offset 
    AND r_404949.cc_hours NOT LIKE 'By7I' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
WITH CTE_c311e4(te_d011dd, te_2c0b9b, te_d05929) AS (SELECT
    date_sub(DATE'2024-03-25',
    r_ba0a6f.te_c56c50) as te_d011dd,
    r_cf253b.te_586acf as te_2c0b9b,
    r_cf253b.te_586acf as te_d05929 
FROM
    (SELECT
        r_c352b9.ca_address_sk as te_c56c50 
    FROM
        db1.customer_address AS r_c352b9 
    WHERE
        r_c352b9.ca_street_type > 'v95G6' 
        AND r_c352b9.ca_gmt_offset <= 53.87870295 
        AND r_c352b9.ca_state NOT ILIKE r_c352b9.ca_zip 
        AND NOT r_c352b9.ca_street_type LIKE r_c352b9.ca_state 
    ORDER BY
        1 DESC NULLS LAST 
    LIMIT 28) AS r_ba0a6f INNER JOIN
    db1.customer_demographics AS r_dc5807 
        ON r_ba0a6f.te_c56c50 < r_dc5807.cd_dep_count,
    (SELECT
        r_3ce138.w_warehouse_sk as te_ac6025,
        r_3ce138.w_warehouse_sk as te_7e47e2,
        r_dc58dc.te_12c69c as te_586acf 
    FROM
        db1.warehouse AS r_3ce138,
        (SELECT
            r_639491.cd_gender as te_9e2762,
            r_cbb54a.sm_type as te_12c69c,
            r_cbb54a.sm_type as te_de952a 
        FROM
            db1.customer_demographics AS r_e6f5ad 
        INNER JOIN
            db1.customer_demographics AS r_639491 
                ON r_e6f5ad.cd_education_status < r_639491.cd_credit_rating,
            db1.ship_mode AS r_cbb54a 
        LEFT JOIN
            db1.income_band AS r_40f1c4 
                ON r_cbb54a.sm_ship_mode_sk = r_40f1c4.ib_upper_bound 
        WHERE
            r_639491.cd_purchase_estimate <= r_40f1c4.ib_lower_bound 
            OR r_cbb54a.sm_contract NOT ILIKE r_cbb54a.sm_ship_mode_id 
            AND r_e6f5ad.cd_marital_status NOT LIKE '98hv9' 
            AND r_639491.cd_demo_sk = 34 
        ORDER BY
            1, 2 DESC NULLS LAST, 3 DESC) AS r_dc58dc 
    WHERE
        r_3ce138.w_street_number NOT ILIKE r_dc58dc.te_de952a 
        OR r_dc58dc.te_12c69c ILIKE 'DDXX4' 
        AND r_dc58dc.te_9e2762 LIKE r_3ce138.w_county 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_cf253b 
    WHERE
        r_ba0a6f.te_c56c50 <= r_cf253b.te_ac6025 
        AND r_cf253b.te_586acf LIKE 'zz2kGk4e' 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST), CTE_6d9786(te_1187ec) AS (SELECT
        r_79e511.hd_income_band_sk * r_79e511.hd_demo_sk as te_1187ec 
    FROM
        db1.household_demographics AS r_79e511 
    RIGHT JOIN
        db1.reason AS r_2ef036 
            ON r_79e511.hd_demo_sk = r_2ef036.r_reason_sk 
    WHERE
        r_79e511.hd_demo_sk > r_79e511.hd_income_band_sk 
    ORDER BY
        1 ASC NULLS LAST) (SELECT
        r_706cb2.web_name as te_0dc82c, r_3572dc.wp_access_date_sk as te_76cd7c 
    FROM
        (SELECT
            r_9bba59.c_birth_year as te_1755ea,
            r_9bba59.pf_884c6d as te_93896d 
        FROM
            db1.customer_demographics AS r_d7a28e,
            (SELECT
                * 
            FROM
                db1.customer PIVOT(count(c_current_cdemo_sk) AS pa_59518a FOR c_birth_country IN (('SW4P') AS pf_3f6dca,
                ('rTUOVbBRG') AS pf_c991e6,
                ('RXpRQiizz2') AS pf_42f3ce,
                ('hv95G6sU') AS pf_884c6d,
                ('OVbBRGC0l') AS pf_f303a4,
                ('DCMa') AS pf_6d9bd0))) AS r_9bba59 
        WHERE
            r_d7a28e.cd_purchase_estimate != r_9bba59.c_first_shipto_date_sk 
            AND r_d7a28e.cd_credit_rating NOT ILIKE '7IG7i' 
            AND r_9bba59.c_birth_year <= 54 
            AND r_9bba59.c_preferred_cust_flag NOT LIKE r_d7a28e.cd_education_status 
            OR r_9bba59.c_birth_year = r_9bba59.c_birth_month 
        ORDER BY
            1 DESC, 2 ASC NULLS LAST) AS r_90f2d0 
        LEFT JOIN
            db1.web_page AS r_3572dc 
                ON r_90f2d0.te_1755ea = r_3572dc.wp_char_count,
            db1.web_site AS r_706cb2 
        WHERE
            r_3572dc.wp_rec_end_date = r_706cb2.web_rec_start_date 
            AND r_706cb2.web_rec_end_date > r_706cb2.web_rec_start_date 
            OR r_706cb2.web_state NOT LIKE r_706cb2.web_site_id 
            AND r_3572dc.wp_max_ad_count != 42 
        ORDER BY
            1 ASC NULLS LAST, 2 NULLS LAST) EXCEPT ALL (SELECT
            string(r_fd013a.wp_char_count) as te_9b5431, hour(current_timestamp()) as te_30c588 
        FROM
            db1.web_page AS r_fd013a 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC) 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_fe893b.c_salutation as te_ad78b2,
    r_c6b266.hd_buy_potential as te_533d27,
    37 + r_fe893b.c_current_addr_sk - 74 - r_fe893b.c_birth_month - r_fe893b.c_current_hdemo_sk - 24 as te_d3b3b4 
FROM
    db1.household_demographics AS r_c6b266 
LEFT JOIN
    db1.customer_demographics AS r_9a2c03 
        ON r_c6b266.hd_demo_sk > r_9a2c03.cd_purchase_estimate,
    db1.customer AS r_fe893b 
LEFT JOIN
    db1.household_demographics AS r_94b94e 
        ON r_fe893b.c_last_name != r_94b94e.hd_buy_potential 
WHERE
    r_9a2c03.cd_dep_college_count < r_fe893b.c_birth_year 
    OR r_9a2c03.cd_education_status NOT LIKE r_9a2c03.cd_gender 
    OR r_c6b266.hd_dep_count > r_94b94e.hd_vehicle_count 
    OR r_94b94e.hd_dep_count > 63 
    AND r_94b94e.hd_dep_count != 68 
ORDER BY
    1 DESC, 2 ASC NULLS FIRST, 3 ASC;
----------->
SELECT
    reverse(r_8418af.c_preferred_cust_flag) as te_597b00,
    r_913131.te_676862 as te_b51d2c,
    r_913131.te_676862 as te_c1f04e 
FROM
    db1.call_center AS r_3af918 
RIGHT JOIN
    (
        SELECT
            make_timestamp(r_e3a016.ib_income_band_sk,
            r_e3a016.ib_lower_bound,
            r_033839.c_customer_sk,
            r_e3a016.ib_income_band_sk,
            r_033839.c_first_shipto_date_sk,
            r_200807.i_wholesale_cost) as te_676862,
            r_200807.i_wholesale_cost as te_467804 
        FROM
            db1.item AS r_200807,
            (SELECT
                * 
            FROM
                db1.customer PIVOT(count(c_last_name) AS pa_7fc9b7 FOR c_current_addr_sk IN ((4) AS pf_86a3ec,
                (33) AS pf_b2a370,
                (78) AS pf_7ccfec,
                (70) AS pf_3fee11,
                (51) AS pf_b3bd99,
                (92) AS pf_fafc50))) AS r_033839 
        RIGHT JOIN
            db1.income_band AS r_e3a016 
                ON r_033839.c_birth_month >= r_e3a016.ib_income_band_sk 
        WHERE
            r_200807.i_size NOT ILIKE r_033839.c_birth_country 
            OR r_033839.c_birth_country ILIKE 'gDDXX4QH' 
            AND r_033839.c_login NOT ILIKE 'Gk4' 
            OR r_033839.pf_fafc50 <= r_033839.pf_7ccfec 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_913131 
                ON r_3af918.cc_tax_percentage >= r_913131.te_467804, db1.customer AS r_8418af 
        INNER JOIN
            db1.income_band AS r_127f82 
                ON r_8418af.c_current_addr_sk <= r_127f82.ib_income_band_sk 
        WHERE
            r_3af918.cc_rec_start_date BETWEEN r_3af918.cc_rec_end_date AND DATE'2024-10-11' 
            OR r_3af918.cc_zip LIKE 'b2G' 
            OR r_3af918.cc_rec_start_date < r_3af918.cc_rec_end_date 
        ORDER BY
            1 DESC NULLS FIRST, 2 ASC, 3 DESC;
----------->
SELECT
    r_c259d4.t_second as te_a9cd19,
    r_bc86cb.te_196010 as te_4fbe50 
FROM
    (SELECT
        * 
    FROM
        db1.time_dim PIVOT(max(t_time_id) AS pa_85e5fa FOR t_hour IN ((23) AS pf_5db99d,
        (66) AS pf_3b3319,
        (93) AS pf_93f830,
        (43) AS pf_791481,
        (6) AS pf_38b627,
        (32) AS pf_e01cc3))) AS r_c259d4,
    (SELECT
        r_7c5de2.t_time as te_f2277b 
    FROM
        db1.time_dim AS r_7c5de2 
    LEFT JOIN
        (
            SELECT
                * 
            FROM
                db1.household_demographics PIVOT(count(hd_buy_potential) AS pa_9aa5ef FOR hd_income_band_sk IN ((39) AS pf_3c4638,
                (10) AS pf_06ed50,
                (88) AS pf_954bd6,
                (54) AS pf_7d1833,
                (21) AS pf_a4189a))
        ) AS r_2d9330 
            ON r_7c5de2.t_hour >= r_2d9330.hd_vehicle_count 
    WHERE
        r_2d9330.hd_dep_count < r_7c5de2.t_minute 
        OR r_2d9330.pf_3c4638 >= 28 
        AND r_7c5de2.t_sub_shift ILIKE 'IG7ir98hv' 
        AND r_2d9330.pf_3c4638 = 0 
    ORDER BY
        1 NULLS LAST) AS r_e88611 
    LEFT JOIN
        (
            SELECT
                current_date() as te_196010 
            FROM
                (SELECT
                    r_c889e3.c_customer_id as te_6dad82 
                FROM
                    db1.customer AS r_c889e3 
                WHERE
                    r_c889e3.c_birth_month = r_c889e3.c_last_review_date_sk 
                    AND r_c889e3.c_first_shipto_date_sk <= r_c889e3.c_birth_year 
                ORDER BY
                    1) AS r_581fdd 
            INNER JOIN
                db1.customer_address AS r_a163bb 
                    ON r_581fdd.te_6dad82 LIKE r_a163bb.ca_street_type 
            WHERE
                r_a163bb.ca_zip ILIKE 'SRI' 
                AND r_a163bb.ca_zip LIKE 'z2kG' 
                OR r_a163bb.ca_street_type LIKE '95G6'
            ) AS r_bc86cb 
                ON date_from_unix_date(r_e88611.te_f2277b) != r_bc86cb.te_196010 
        WHERE
            r_c259d4.t_minute != r_e88611.te_f2277b 
            AND r_c259d4.pf_5db99d ILIKE '4P' 
            AND r_c259d4.pf_93f830 ILIKE 'ir98hv9' 
        ORDER BY
            1 DESC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_e89cba.ca_gmt_offset as te_c74e22,
    r_ec9f42.w_county as te_6f42f9 
FROM
    db1.customer_address AS r_e89cba 
LEFT JOIN
    db1.warehouse AS r_ec9f42 
        ON r_e89cba.ca_gmt_offset < r_ec9f42.w_gmt_offset,
    db1.household_demographics AS r_3081dc 
LEFT JOIN
    db1.reason AS r_d6b73b 
        ON r_3081dc.hd_buy_potential NOT ILIKE r_d6b73b.r_reason_desc 
WHERE
    r_ec9f42.w_warehouse_sq_ft <= r_d6b73b.r_reason_sk 
    OR r_3081dc.hd_demo_sk != r_d6b73b.r_reason_sk 
ORDER BY
    1, 2 DESC NULLS FIRST;
----------->
SELECT
    r_14f897.te_ca11ad as te_5f85ba 
FROM
    (SELECT
        r_fcc92f.cd_gender as te_0c88cd,
        r_1ee7fe.ib_upper_bound as te_ca11ad,
        r_fcc92f.cd_education_status as te_ff5343 
    FROM
        db1.income_band AS r_1ee7fe,
        db1.household_demographics AS r_509ef7 
    LEFT JOIN
        db1.customer_demographics AS r_fcc92f 
            ON r_509ef7.hd_demo_sk < r_fcc92f.cd_demo_sk 
    WHERE
        r_1ee7fe.ib_income_band_sk = r_fcc92f.cd_purchase_estimate 
        OR r_509ef7.hd_dep_count BETWEEN 13 AND 13 
        AND r_fcc92f.cd_education_status NOT LIKE r_fcc92f.cd_marital_status 
        OR r_fcc92f.cd_credit_rating NOT LIKE 'vX' 
        OR r_fcc92f.cd_demo_sk < r_fcc92f.cd_dep_college_count 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 NULLS FIRST) AS r_14f897 
WHERE
    r_14f897.te_0c88cd NOT LIKE r_14f897.te_ff5343 
    OR r_14f897.te_0c88cd ILIKE 'k' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_adbc4e.r_reason_sk as te_14c24e 
FROM
    db1.reason AS r_adbc4e 
WHERE
    r_adbc4e.r_reason_sk <= 48 
    AND r_adbc4e.r_reason_desc NOT ILIKE r_adbc4e.r_reason_id 
ORDER BY
    1;
----------->
SELECT
    r_3a1e4a.d_fy_week_seq as te_46baa7 
FROM
    db1.date_dim AS r_3a1e4a 
WHERE
    r_3a1e4a.d_current_day NOT LIKE 'RXpR' 
    OR r_3a1e4a.d_current_week NOT LIKE 'pRQii' 
ORDER BY
    1 ASC NULLS FIRST 
LIMIT 6;
----------->
WITH CTE_b1fd75(te_b95997, te_5448f0) AS (SELECT
    r_961819.d_date as te_b95997,
    r_4674ef.w_gmt_offset as te_5448f0 
FROM
    (SELECT
        r_77bf39.sr_hdemo_sk as te_a6291f,
        r_77bf39.sr_fee as te_91f38b 
    FROM
        (SELECT
            * 
        FROM
            db1.ship_mode UNPIVOT INCLUDE NULLS ((up_a78496,
            up_a64a9b) FOR upn_595aa2 IN ((sm_ship_mode_sk,
            sm_carrier) AS UPF_aa9acc))) AS r_31aea7,
        db1.store_returns AS r_77bf39 
    RIGHT JOIN
        db1.income_band AS r_56ccc2 
            ON r_77bf39.sr_cdemo_sk < r_56ccc2.ib_income_band_sk 
    WHERE
        r_31aea7.up_a78496 < r_77bf39.web_county 
    GROUP BY
        1, 2 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC) AS r_95e175 
    RIGHT JOIN
        db1.warehouse AS r_4674ef 
            ON r_95e175.te_a6291f >= r_4674ef.w_warehouse_sq_ft,
        db1.date_dim AS r_961819 
    WHERE
        r_4674ef.w_warehouse_sk != r_961819.d_fy_year 
        AND r_961819.d_fy_quarter_seq = 62 
        AND r_4674ef.w_street_type LIKE 'vXUulstntb' 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) SELECT
        r_6cfdcc.ca_zip as te_81c43e, r_6cfdcc.ca_country as te_1e9f62 
    FROM
        CTE_b1fd75 AS r_80fbdf,
        db1.customer_address AS r_6cfdcc 
    LEFT JOIN
        db1.reason AS r_cabdac 
            ON r_6cfdcc.ca_gmt_offset = r_cabdac.r_reason_sk 
    WHERE
        r_80fbdf.te_5448f0 = r_6cfdcc.ca_gmt_offset 
        OR r_6cfdcc.ca_gmt_offset = r_80fbdf.te_5448f0 
        AND r_cabdac.r_reason_id NOT LIKE r_6cfdcc.ca_city 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_77ccb2.cd_gender as te_10916c,
    r_692c6d.w_street_number as te_37474b,
    hash(r_77ccb2.cd_demo_sk * e(),
    false) as te_51fa10 
FROM
    db1.customer_demographics AS r_77ccb2,
    db1.warehouse AS r_692c6d 
WHERE
    r_77ccb2.cd_credit_rating NOT ILIKE r_692c6d.w_county 
GROUP BY
    3, 1, 2 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 NULLS LAST;
----------->
(
    SELECT
        r_57eeec.ib_lower_bound as te_d0ca7b,
        chr(bigint(r_fb7ed7.w_warehouse_sq_ft)) as te_761c6d,
        r_9626d4.ca_gmt_offset as te_8fbf68 
    FROM
        db1.customer_address AS r_9626d4,
        db1.income_band AS r_57eeec 
    RIGHT JOIN
        db1.warehouse AS r_fb7ed7 
            ON r_57eeec.ib_upper_bound > r_fb7ed7.w_gmt_offset 
    WHERE
        r_9626d4.ca_gmt_offset >= r_57eeec.ib_income_band_sk 
        OR (
            NOT r_57eeec.ib_upper_bound = r_57eeec.ib_lower_bound 
            AND r_fb7ed7.w_warehouse_id NOT ILIKE 'hv95G6' 
            OR r_fb7ed7.w_street_type NOT ILIKE r_fb7ed7.w_zip 
            AND r_9626d4.ca_county NOT LIKE 'iizz2kG'
        ) 
    ORDER BY
        1 NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS FIRST 
    LIMIT 77
) UNION
(
SELECT
    hash(r_934458.web_site_sk,
    TIMESTAMP'2024-03-26 06:22:02.716') as te_ced04b,
    reverse(r_d790d1.cd_gender) as te_27d103,
    r_934458.web_gmt_offset as te_283ee8 
FROM
    db1.web_site AS r_934458 
LEFT JOIN
    db1.customer AS r_c05553 
        ON r_934458.web_open_date_sk < r_c05553.c_birth_year,
    db1.web_site AS r_09c7a3 
RIGHT JOIN
    db1.customer_demographics AS r_d790d1 
        ON r_09c7a3.web_mkt_class LIKE r_d790d1.cd_marital_status 
WHERE
    r_934458.web_rec_start_date >= r_09c7a3.web_rec_start_date 
    OR r_09c7a3.web_name ILIKE 'iz' 
    AND r_934458.web_city ILIKE 'gDDX' 
    AND r_c05553.c_last_review_date_sk >= 37 
ORDER BY
    1, 2 NULLS FIRST, 3 DESC NULLS LAST 
LIMIT 45
) ORDER BY
1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    reverse(r_d68c8b.w_county) as te_4b56a4,
    r_5327a9.cc_gmt_offset as te_768684,
    r_5327a9.cc_rec_start_date as te_63cafe 
FROM
    db1.warehouse AS r_d68c8b 
RIGHT JOIN
    db1.call_center AS r_5327a9 
        ON r_d68c8b.w_county != r_5327a9.cc_call_center_id,
    db1.income_band AS r_aa9049 
WHERE
    r_5327a9.cc_closed_date_sk <= r_aa9049.ib_income_band_sk 
    OR r_5327a9.cc_rec_start_date = DATE'2024-10-11' 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_7b0628.cc_mkt_id as te_7a4398,
    r_7b0628.cc_mkt_id as te_d6cea4,
    e() as te_8d09da 
FROM
    db1.customer AS r_24c880,
    db1.ship_mode AS r_844653 
INNER JOIN
    db1.call_center AS r_7b0628 
        ON r_844653.sm_ship_mode_sk <= r_7b0628.cc_division 
WHERE
    r_24c880.c_first_name <= r_7b0628.cc_city 
    AND r_844653.sm_type ILIKE 'G' 
    OR r_24c880.c_first_sales_date_sk = 59 
ORDER BY
    1, 2 NULLS LAST, 3 ASC;
----------->
SELECT
    r_ecb7be.d_date as te_c51c2b,
    try_add(r_f290c3.c_current_addr_sk,
    34) as te_27f466,
    r_ecb7be.d_date as te_97ca3f 
FROM
    (SELECT
        * 
    FROM
        db1.date_dim PIVOT(max(d_date_id) AS pa_7e0e48 FOR d_quarter_seq IN ((79) AS pf_e6dc10,
        (98) AS pf_7416a5,
        (59) AS pf_138494))) AS r_ecb7be 
RIGHT JOIN
    db1.customer AS r_f290c3 
        ON r_ecb7be.pf_138494 LIKE r_f290c3.c_customer_id,
    db1.customer AS r_ac1ec0 
WHERE
    r_f290c3.c_first_shipto_date_sk = r_ac1ec0.c_current_cdemo_sk 
    AND r_ecb7be.d_weekend LIKE '0lezm' 
    AND r_ac1ec0.c_first_sales_date_sk <= 75 
    AND r_ecb7be.d_last_dom = r_ecb7be.d_qoy 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 ASC NULLS LAST;
----------->
SELECT
    r_c9513b.s_tax_percentage as te_04956b,
    r_98bb5f.s_rec_start_date as te_dc0877 
FROM
    db1.store AS r_98bb5f,
    db1.store AS r_c9513b 
WHERE
    r_98bb5f.s_division_id <= r_c9513b.s_division_id 
    OR r_98bb5f.s_rec_end_date = r_c9513b.s_rec_start_date 
    AND r_c9513b.s_number_employees IN (
        SELECT
            hash(r_f93d09.wp_autogen_flag) as te_c77baa 
        FROM
            db1.web_page AS r_f93d09 
        INNER JOIN
            db1.customer_address AS r_871c22 
                ON r_f93d09.wp_web_page_id ILIKE r_871c22.ca_location_type,
            db1.store_returns AS r_d1b68a 
        WHERE
            r_f93d09.wp_link_count > r_d1b68a.sr_addr_sk 
            AND r_d1b68a.sr_ticket_number >= 55 
            AND r_d1b68a.sr_cdemo_sk <= r_d1b68a.sr_returned_date_sk 
        ORDER BY
            1 DESC NULLS LAST 
        LIMIT 23 OFFSET 38
) 
AND r_c9513b.s_company_name NOT LIKE '7IG7ir' ORDER BY
    1 DESC NULLS LAST, 2 ASC 
LIMIT 78;
----------->
SELECT
    r_5cef2a.wp_web_page_id as te_e7014c,
    r_5cef2a.wp_autogen_flag as te_2dc12b 
FROM
    db1.web_page AS r_5cef2a,
    (SELECT
        r_c07439.web_rec_start_date as te_9f0f15 
    FROM
        db1.web_site AS r_c07439 
    LEFT JOIN
        db1.customer_demographics AS r_f88556 
            ON r_c07439.web_site_sk >= r_f88556.cd_dep_count 
    ORDER BY
        1) AS r_87d0af 
LEFT JOIN
    db1.reason AS r_a75473 
        ON chr(month(r_87d0af.te_9f0f15)) NOT ILIKE chr(length(r_a75473.r_reason_desc)) 
WHERE
    r_5cef2a.wp_type ILIKE r_a75473.r_reason_desc 
    AND r_5cef2a.wp_web_page_sk <= 3 
    OR r_5cef2a.wp_access_date_sk > 6 
    AND r_5cef2a.wp_type NOT ILIKE 'G7' 
    AND r_5cef2a.wp_max_ad_count = 43 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST;
----------->
(
    SELECT
        r_e433a5.cc_sq_ft as te_f9eb86,
        r_e433a5.cc_gmt_offset as te_bba76d,
        r_e433a5.cc_gmt_offset as te_ecabcf 
    FROM
        (SELECT
            r_ee9358.ca_location_type as te_79c074,
            r_ee9358.ca_county as te_fd2f33 
        FROM
            db1.customer_address AS r_ee9358,
            (SELECT
                * 
            FROM
                db1.store_returns PIVOT(sum(sr_cdemo_sk) AS pa_1a8cd3 FOR sr_reversed_charge IN ((62.91494149) AS pf_e24250,
                (52.53113112) AS pf_c02d70,
                (94.84238434) AS pf_1db9be,
                (78.06626024) AS pf_f36ac6,
                (60.47339103) AS pf_e56580))) AS r_149e03 
        WHERE
            r_ee9358.ca_gmt_offset <= r_149e03.web_county 
            AND r_149e03.sr_return_quantity <= 46 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC NULLS LAST) AS r_d46500, db1.call_center AS r_e433a5 
        WHERE
            r_d46500.te_fd2f33 NOT ILIKE r_e433a5.cc_manager 
        ORDER BY
            1 DESC, 2 ASC NULLS LAST, 3 DESC) EXCEPT  (SELECT
            r_615ddd.s_store_sk as te_3dc86e, r_8670c0.web_gmt_offset as te_c87784, r_8670c0.web_tax_percentage as te_e4fa3e 
        FROM
            db1.income_band AS r_a943d2 
        LEFT JOIN
            db1.warehouse AS r_e912e1 
                ON r_a943d2.ib_income_band_sk != r_e912e1.w_warehouse_sk,
            db1.web_site AS r_8670c0 
        RIGHT JOIN
            db1.store AS r_615ddd 
                ON r_8670c0.web_state != r_615ddd.s_store_id 
        WHERE
            r_e912e1.w_county ILIKE r_615ddd.s_store_id 
            AND r_8670c0.web_site_id LIKE '4elBy' 
        ORDER BY
            1 ASC NULLS LAST, 2 NULLS FIRST, 3 ASC) 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_3db014.t_time_id as te_d2cbf2 
FROM
    db1.ship_mode AS r_cb891b 
LEFT JOIN
    db1.time_dim AS r_3db014 
        ON r_cb891b.sm_code LIKE r_3db014.t_sub_shift 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_d8592c.cd_education_status as te_447499 
FROM
    db1.customer_demographics AS r_e24a3e 
LEFT JOIN
    db1.customer_demographics AS r_d8592c 
        ON r_e24a3e.cd_education_status NOT LIKE r_d8592c.cd_marital_status 
WHERE
    r_e24a3e.cd_purchase_estimate = 15 
    OR r_d8592c.cd_purchase_estimate >= 57 
    AND r_e24a3e.cd_gender NOT LIKE r_e24a3e.cd_credit_rating 
    OR r_d8592c.cd_purchase_estimate > r_e24a3e.cd_demo_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    date_sub(r_18ebfc.wp_rec_start_date,
    r_18ebfc.wp_max_ad_count) as te_59e4f3,
    positive(54 / r_ab25ba.wp_customer_sk) as te_ac7ae3,
    r_ab25ba.wp_url as te_fb6616 
FROM
    db1.web_page AS r_ab25ba,
    db1.web_page AS r_18ebfc 
INNER JOIN
    db1.customer_address AS r_a4d398 
        ON r_18ebfc.wp_url LIKE r_a4d398.ca_country 
WHERE
    r_ab25ba.wp_rec_end_date <= r_18ebfc.wp_rec_start_date 
    OR (
        NOT r_18ebfc.wp_char_count <= 83 
        OR r_a4d398.ca_street_type NOT LIKE r_18ebfc.wp_web_page_id
    ) 
    AND r_18ebfc.wp_rec_start_date != r_ab25ba.wp_rec_start_date 
    OR r_18ebfc.wp_image_count = 12 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_b34fbf.w_country as te_7a28de,
    make_date(r_3a09f9.t_second,
    r_b34fbf.w_warehouse_sk,
    r_3a09f9.t_time_sk) as te_9c3a5e 
FROM
    db1.household_demographics AS r_b33974 
RIGHT JOIN
    db1.warehouse AS r_b34fbf 
        ON r_b33974.hd_vehicle_count > r_b34fbf.w_warehouse_sk,
    db1.time_dim AS r_3a09f9 
WHERE
    r_b34fbf.w_zip LIKE r_3a09f9.t_time_id 
    OR r_3a09f9.t_minute <= r_b33974.hd_vehicle_count 
    OR r_b34fbf.w_county LIKE 'mSR' 
    OR r_b33974.hd_demo_sk >= r_b33974.hd_dep_count 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
SELECT
    r_8e1243.ca_county as te_8d62cb,
    r_b0e74b.te_1fe0ba as te_9330ce 
FROM
    (SELECT
        cos(r_c03f8d.web_county) as te_29ffe3,
        r_c03f8d.sr_return_ship_cost as te_1fe0ba,
        hash(r_b941de.sr_store_sk,
        true) as te_2f7f2a 
    FROM
        (SELECT
            72.54459686 * r_3ca326.wp_web_page_sk as te_58b820,
            r_3ca326.wp_image_count as te_0e15d3 
        FROM
            db1.time_dim AS r_6fca95,
            db1.web_page AS r_3ca326 
        WHERE
            r_6fca95.t_meal_time NOT LIKE r_3ca326.wp_type 
        ORDER BY
            1 DESC NULLS LAST, 2 NULLS FIRST) AS r_190d68, (SELECT
            * 
        FROM
            db1.store_returns PIVOT(count(sr_customer_sk) AS pa_3f40c5 FOR sr_ticket_number IN ((77) AS pf_d0c271,
            (75) AS pf_19a5cb,
            (96) AS pf_7a85aa,
            (83) AS pf_a11ffd,
            (8) AS pf_7b66c7,
            (75) AS pf_c63461))) AS r_c03f8d 
    RIGHT JOIN
        db1.store_returns AS r_b941de 
            ON r_c03f8d.sr_returned_date_sk = r_b941de.sr_item_sk 
    WHERE
        r_190d68.te_0e15d3 != r_b941de.sr_store_sk 
        OR r_c03f8d.sr_cdemo_sk != 5 
        AND r_b941de.sr_return_tax >= 75.19345877 
        AND r_c03f8d.sr_store_sk > r_190d68.te_0e15d3 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3) AS r_b0e74b 
    LEFT JOIN
        db1.customer_address AS r_c474a5 
            ON r_b0e74b.te_29ffe3 < r_c474a5.ca_address_sk,
        db1.customer_address AS r_8e1243 
    WHERE
        r_b0e74b.te_2f7f2a <= r_8e1243.ca_address_sk 
        OR (
            NOT r_8e1243.ca_address_sk = r_c474a5.ca_address_sk 
            AND r_8e1243.ca_address_sk != 32
        ) 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC;
----------->
SELECT
    timestamp_millis(r_b907ed.sr_item_sk) as te_a9e1c0,
    r_b907ed.sr_reason_sk as te_2669e1,
    make_date(r_b907ed.sr_addr_sk,
    r_b907ed.sr_item_sk,
    r_b907ed.sr_return_time_sk) as te_a9ac43 
FROM
    db1.store_returns AS r_b907ed 
INNER JOIN
    db1.customer_demographics AS r_e30a15 
        ON r_b907ed.sr_customer_sk > r_e30a15.cd_demo_sk,
    (SELECT
        r_1c39c7.d_year as te_23faf8 
    FROM
        db1.date_dim AS r_1c39c7 
    WHERE
        r_1c39c7.d_following_holiday NOT LIKE r_1c39c7.d_current_day 
        OR r_1c39c7.d_month_seq < 46 
        OR r_1c39c7.d_date_sk != 21 
        AND r_1c39c7.d_quarter_name ILIKE 'BRGC' 
    ORDER BY
        1 DESC NULLS LAST 
    OFFSET 56) AS r_3a2eee WHERE
    r_b907ed.sr_reason_sk != r_3a2eee.te_23faf8 
    AND r_b907ed.sr_ticket_number IS NULL 
    OR (
        NOT r_b907ed.sr_store_credit = (
            (
                SELECT
                    r_139427.i_wholesale_cost as te_f923dd 
                FROM
                    db1.household_demographics AS r_5b9552 
                RIGHT JOIN
                    db1.store_returns AS r_c2fe2a 
                        ON r_5b9552.hd_income_band_sk > r_c2fe2a.sr_return_tax,
                    db1.customer AS r_ad0b2b 
                RIGHT JOIN
                    db1.item AS r_139427 
                        ON r_ad0b2b.c_birth_month >= r_139427.i_manufact_id 
                WHERE
                    r_5b9552.hd_vehicle_count != r_139427.i_manager_id 
                    AND r_c2fe2a.sr_return_ship_cost = 35.50377724 
                    AND NOT r_c2fe2a.sr_return_ship_cost <= r_c2fe2a.sr_store_credit 
                ORDER BY
                    1 DESC 
                LIMIT 1
        )
    ) 
    AND r_b907ed.sr_cdemo_sk > 46
) ORDER BY
    1 ASC, 2 ASC, 3 ASC;
----------->
SELECT
    r_052bb3.ca_street_name as te_906302 
FROM
    db1.customer_address AS r_052bb3 
WHERE
    r_052bb3.ca_country ILIKE '4QHZgGI' 
    OR r_052bb3.ca_street_type NOT ILIKE '8hv9' 
    AND r_052bb3.ca_address_sk < 33 
ORDER BY
    1 DESC NULLS FIRST;
----------->
WITH CTE_3d0126(te_cab6c8) AS (SELECT
    r_c2700e.ca_city as te_cab6c8 
FROM
    db1.store_returns AS r_3a658f 
RIGHT JOIN
    db1.customer_address AS r_c2700e 
        ON r_3a658f.sr_return_tax = r_c2700e.ca_gmt_offset 
WHERE
    r_c2700e.ca_county ILIKE 'RQi' 
    OR r_3a658f.sr_return_time_sk < r_3a658f.sr_item_sk 
    AND r_3a658f.sr_fee > 89.70077323 
    OR r_3a658f.sr_ticket_number >= 43 
ORDER BY
    1 DESC NULLS LAST) SELECT
    r_c350ab.ca_street_type as te_5ae24c 
FROM
    CTE_3d0126 AS r_54bae3 
LEFT JOIN
    db1.customer_address AS r_c350ab 
        ON r_54bae3.te_cab6c8 LIKE r_c350ab.ca_location_type 
WHERE
    r_c350ab.ca_city LIKE r_54bae3.te_cab6c8 
    AND r_c350ab.ca_city = 'rTUOV' 
    AND NOT r_c350ab.ca_address_sk BETWEEN 45 AND 45 
    AND r_c350ab.ca_city ILIKE r_c350ab.ca_country 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_ad0ec8.pf_06041d as te_419b1d,
    r_ad0ec8.ca_county as te_c8a15c 
FROM
    db1.customer_demographics AS r_bd1aaf 
RIGHT JOIN
    db1.time_dim AS r_f33111 
        ON r_bd1aaf.cd_credit_rating NOT ILIKE r_f33111.t_meal_time,
    db1.store AS r_7f8d7e 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.customer_address PIVOT(count(ca_street_number) AS pa_aa4014 FOR ca_address_sk IN ((71) AS pf_153733,
            (37) AS pf_06041d,
            (16) AS pf_8f31b9,
            (35) AS pf_ee5997))
    ) AS r_ad0ec8 
        ON r_7f8d7e.s_store_id = r_ad0ec8.ca_state 
WHERE
    r_bd1aaf.cd_dep_count = r_7f8d7e.s_floor_space 
    AND r_ad0ec8.ca_city NOT ILIKE 'Gk4elBy7I' 
    OR r_bd1aaf.cd_education_status ILIKE 'G6' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_f6dc92.te_13fa40 as te_f7d3bf,
    r_16088a.c_customer_id as te_8647d2 
FROM
    db1.household_demographics AS r_1dd052 
INNER JOIN
    (
        SELECT
            r_1bb587.web_close_date_sk as te_88b4cc,
            r_1bb587.web_country as te_5c3ba4,
            r_1bb587.web_rec_start_date as te_13fa40 
        FROM
            db1.customer_address AS r_f32bbc 
        LEFT JOIN
            db1.web_site AS r_1bb587 
                ON r_f32bbc.ca_gmt_offset >= r_1bb587.web_site_sk,
            db1.household_demographics AS r_a4b4aa 
        WHERE
            r_f32bbc.ca_gmt_offset < r_a4b4aa.hd_vehicle_count 
            AND (
                NOT r_f32bbc.ca_country LIKE 'G' 
                OR r_1bb587.web_rec_start_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
                OR r_f32bbc.ca_street_number >= '7R'
            ) 
        ORDER BY
            1 DESC NULLS FIRST, 2, 3 DESC NULLS LAST
    ) AS r_f6dc92 
        ON r_1dd052.hd_buy_potential ILIKE r_f6dc92.te_5c3ba4,
    db1.customer AS r_16088a 
WHERE
    r_1dd052.hd_income_band_sk != r_16088a.c_birth_year 
    OR r_16088a.c_login NOT ILIKE r_16088a.c_birth_country 
    AND r_16088a.c_email_address NOT LIKE r_f6dc92.te_5c3ba4 
    AND r_16088a.c_preferred_cust_flag LIKE '2' 
    AND r_f6dc92.te_5c3ba4 LIKE 'G6sU' 
ORDER BY
    1 ASC, 2 DESC NULLS LAST 
LIMIT 25;
----------->
(
    SELECT
        r_ec0427.web_street_type as te_e6ba64,
        hash(true,
        false) as te_d3c4b1,
        negative(count(timestamp_millis(r_ec0427.web_close_date_sk))) as te_35b23b 
    FROM
        db1.store_returns AS r_754de8 
    LEFT JOIN
        db1.customer_demographics AS r_bbed5e 
            ON r_754de8.sr_return_quantity = r_bbed5e.cd_dep_employed_count,
        db1.web_site AS r_ec0427 
    LEFT JOIN
        db1.item AS r_13656a 
            ON r_ec0427.web_mkt_class NOT ILIKE r_13656a.i_units 
    WHERE
        r_bbed5e.cd_education_status LIKE r_ec0427.web_state 
        OR r_13656a.i_wholesale_cost != 44.42465848 
        AND NOT r_ec0427.web_country NOT ILIKE 'QFrTUOV' 
        OR r_13656a.i_item_desc ILIKE 'QFrTUOV' 
    GROUP BY
        r_ec0427.web_street_type 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST
) EXCEPT  (SELECT
    r_66b959.i_product_name as te_7e2247, r_66b959.i_category_id as te_9262aa, 37 as te_8c1147 
FROM
    db1.ship_mode AS r_d3a0a4 
RIGHT JOIN
    db1.ship_mode AS r_8e299c 
        ON r_d3a0a4.sm_ship_mode_id NOT LIKE r_8e299c.sm_ship_mode_id,
    db1.item AS r_66b959 
WHERE
    r_d3a0a4.sm_ship_mode_sk > r_66b959.i_category_id 
    AND r_66b959.i_rec_start_date < r_66b959.i_rec_end_date 
ORDER BY
    1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS FIRST) 
ORDER BY
1 DESC NULLS LAST, 2, 3 DESC NULLS FIRST;
----------->
SELECT
    hash(r_98eec5.wp_rec_end_date,
    r_98eec5.wp_rec_end_date) as te_8475a2 
FROM
    db1.web_page AS r_98eec5 
WHERE
    r_98eec5.wp_rec_start_date = r_98eec5.wp_rec_end_date 
ORDER BY
    1 ASC 
LIMIT 69;
----------->
SELECT
    try_add(r_cfb375.s_country,
    r_68f9dc.sr_returned_date_sk) as te_38c8ae,
    r_68f9dc.web_county as te_52c92b 
FROM
    db1.store AS r_cfb375,
    db1.store_returns AS r_68f9dc 
WHERE
    r_cfb375.s_number_employees = r_68f9dc.sr_reversed_charge 
    OR r_cfb375.s_rec_start_date >= r_cfb375.s_rec_end_date 
ORDER BY
    1, 2 ASC NULLS FIRST 
OFFSET 17;
----------->
SELECT
    btrim(r_f82d41.ca_suite_number) as te_69c00a,
    r_cc046b.c_birth_day as te_9a05dc 
FROM
    db1.customer_address AS r_f82d41,
    db1.customer AS r_cc046b 
WHERE
    r_f82d41.ca_street_type >= r_cc046b.c_preferred_cust_flag 
    AND (
        NOT r_f82d41.ca_street_number NOT ILIKE r_cc046b.c_customer_id 
        AND r_cc046b.c_current_addr_sk <= 34 
        OR r_f82d41.ca_gmt_offset < 23.66928564
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_a21935.ca_gmt_offset as te_8d9b45 
FROM
    db1.customer_address AS r_a21935 
WHERE
    r_a21935.ca_street_name NOT ILIKE r_a21935.ca_county 
    AND r_a21935.ca_street_number ILIKE r_a21935.ca_address_id 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    hash(bigint(r_073adc.cd_dep_college_count) - r_051c22.t_hour - 63.3922312D,
    now()) as te_4f76ce 
FROM
    db1.customer_demographics AS r_073adc 
INNER JOIN
    db1.time_dim AS r_051c22 
        ON r_073adc.cd_purchase_estimate = r_051c22.t_minute 
WHERE
    r_073adc.cd_marital_status ILIKE 'rTUO' 
    AND r_051c22.t_minute < 63 
    OR r_073adc.cd_education_status LIKE 'Hlpx6' 
    AND r_051c22.t_time != 9 
ORDER BY
    1 ASC;
----------->
SELECT
    r_9c3f0d.i_item_id as te_696811 
FROM
    db1.item AS r_9c3f0d 
RIGHT JOIN
    db1.household_demographics AS r_d20891 
        ON r_9c3f0d.i_item_desc ILIKE r_d20891.hd_buy_potential 
ORDER BY
    1 ASC;
----------->
SELECT
    r_737470.s_rec_end_date as te_536ba4 
FROM
    db1.store_returns AS r_e5616c 
RIGHT JOIN
    db1.store AS r_737470 
        ON r_e5616c.sr_addr_sk >= r_737470.s_store_sk 
WHERE
    r_e5616c.sr_store_sk < 52 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_4b94fa.cc_tax_percentage as te_9e03b3,
    double(r_4b94fa.cc_company) as te_7f02f4 
FROM
    db1.call_center AS r_4b94fa 
RIGHT JOIN
    db1.ship_mode AS r_23693c 
        ON r_4b94fa.cc_tax_percentage >= r_23693c.sm_ship_mode_sk,
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(max(sm_contract) AS pa_353cd7 FOR sm_code IN (('2kGk4elB') AS pf_e8690e,
        ('mSRIvXUul') AS pf_5e6ca9,
        ('C0lezmSRI') AS pf_54f728,
        ('2Gv') AS pf_7761f4))) AS r_eabd22 
WHERE
    r_4b94fa.cc_open_date_sk >= r_eabd22.sm_ship_mode_sk 
    OR r_eabd22.pf_5e6ca9 NOT LIKE 'DDXX4QH' 
    AND r_4b94fa.cc_suite_number NOT ILIKE '2kGk' 
ORDER BY
    1 DESC NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_bc1a5d.cc_rec_start_date as te_e2d6cb 
FROM
    db1.warehouse AS r_3ac6ad 
INNER JOIN
    db1.call_center AS r_bc1a5d 
        ON r_3ac6ad.w_warehouse_sq_ft != r_bc1a5d.cc_employees 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_3f25ed.sm_code as te_c2ec2a 
FROM
    db1.ship_mode AS r_3f25ed 
WHERE
    r_3f25ed.sm_code = 'x2SW4P' 
    AND r_3f25ed.sm_contract ILIKE r_3f25ed.sm_ship_mode_id 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    make_date(r_ab1c4b.i_manufact_id,
    r_37f998.web_open_date_sk,
    r_ab1c4b.i_class_id) as te_b16f44 
FROM
    db1.item AS r_ab1c4b 
RIGHT JOIN
    db1.web_site AS r_37f998 
        ON r_ab1c4b.i_category_id < r_37f998.web_site_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    unix_timestamp() as te_c43166 
FROM
    db1.date_dim AS r_b34918 
INNER JOIN
    db1.time_dim AS r_88c690 
        ON r_b34918.d_moy <= r_88c690.t_second 
ORDER BY
    1 DESC;
----------->
SELECT
    r_92d13a.ca_street_type as te_71ecca,
    r_0eab23.sr_return_amt * r_0eab23.sr_store_credit as te_674024 
FROM
    db1.store_returns AS r_0eab23 
INNER JOIN
    db1.warehouse AS r_f503b3 
        ON r_0eab23.sr_customer_sk <= r_f503b3.w_warehouse_sq_ft,
    db1.customer_address AS r_92d13a 
WHERE
    r_0eab23.sr_ticket_number > r_92d13a.ca_address_sk 
ORDER BY
    1 NULLS FIRST, 2 NULLS LAST;
----------->
WITH CTE_0735d0(te_99c7fb) AS (SELECT
    r_6e8127.web_market_manager as te_99c7fb 
FROM
    db1.web_site AS r_6e8127 
WHERE
    r_6e8127.web_company_id < r_6e8127.web_mkt_id 
    AND r_6e8127.web_mkt_desc LIKE 'OVb' 
    AND r_6e8127.web_open_date_sk != r_6e8127.web_company_id 
ORDER BY
    1 ASC NULLS LAST) SELECT
    r_b27672.cd_credit_rating as te_f93f74 
FROM
    CTE_0735d0 AS r_4d51d2 
INNER JOIN
    db1.customer_demographics AS r_b27672 
        ON r_4d51d2.te_99c7fb NOT ILIKE r_b27672.cd_marital_status 
WHERE
    r_b27672.cd_marital_status LIKE r_b27672.cd_credit_rating 
    OR r_b27672.cd_marital_status != r_b27672.cd_gender 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_e296f4.web_company_id - 49.33406884D as te_61d4ff,
    date_sub(r_e296f4.web_rec_end_date,
    r_c9947e.c_customer_sk) as te_e0cca6 
FROM
    db1.web_site AS r_f98e58 
LEFT JOIN
    db1.web_site AS r_e296f4 
        ON r_f98e58.web_rec_start_date < r_e296f4.web_rec_end_date,
    db1.customer AS r_c9947e 
WHERE
    r_f98e58.web_site_id NOT LIKE r_c9947e.c_customer_id 
    AND r_e296f4.web_site_sk <= 87 
    OR r_f98e58.web_rec_start_date = DATE'2024-10-11' 
    AND r_c9947e.c_customer_sk = 23 
    OR NOT r_e296f4.web_site_id NOT ILIKE r_f98e58.web_site_id 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS FIRST;
----------->
SELECT
    r_0f6fb4.r_reason_desc as te_214ccb,
    r_d5de59.sr_return_tax as te_10fbc7,
    r_650817.hd_buy_potential as te_39eee7 
FROM
    db1.reason AS r_0f6fb4,
    db1.household_demographics AS r_650817 
INNER JOIN
    db1.store_returns AS r_d5de59 
        ON r_650817.hd_vehicle_count != r_d5de59.sr_return_ship_cost 
WHERE
    r_0f6fb4.r_reason_sk = r_650817.hd_dep_count 
    OR r_d5de59.sr_customer_sk != 70 
    AND r_d5de59.web_county <= r_d5de59.sr_return_tax 
    OR r_d5de59.sr_store_sk < 41 
    AND r_d5de59.sr_addr_sk >= 88 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
(
    SELECT
        r_d80473.s_closed_date_sk as te_51be27,
        7 as te_58ee6d 
    FROM
        db1.store AS r_d80473 
    WHERE
        r_d80473.s_company_name ILIKE 'FrTUOVbBRG' 
    ORDER BY
        1 DESC NULLS FIRST, 2 
    LIMIT 86
) UNION
SELECT
r_695d2f.i_manufact_id as te_770398,
r_8c53d3.te_00263b - 34 as te_4e43f3 
FROM
(SELECT
    r_99f835.ib_lower_bound as te_00263b 
FROM
    db1.income_band AS r_99f835 
WHERE
    r_99f835.ib_income_band_sk != 41 
    OR r_99f835.ib_lower_bound = 52 
    OR r_99f835.ib_income_band_sk = r_99f835.ib_lower_bound 
ORDER BY
    1 DESC NULLS LAST) AS r_8c53d3, db1.item AS r_695d2f 
WHERE
r_8c53d3.te_00263b <= r_695d2f.i_item_sk 
OR NOT r_695d2f.i_category NOT ILIKE r_695d2f.i_brand 
AND r_695d2f.i_class_id < r_695d2f.i_item_sk 
ORDER BY
1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    make_timestamp(r_321f86.sr_item_sk,
    r_ed8e33.i_item_sk,
    r_ed8e33.i_manufact_id,
    r_ed8e33.i_manager_id,
    r_ed8e33.i_item_sk,
    r_321f86.sr_hdemo_sk) as te_108c6f 
FROM
    db1.store_returns AS r_321f86 
LEFT JOIN
    db1.item AS r_ed8e33 
        ON r_321f86.sr_item_sk >= r_ed8e33.i_manufact_id 
WHERE
    r_ed8e33.i_wholesale_cost >= 62.02462333 
    OR (
        NOT r_321f86.web_county != r_321f86.sr_fee 
        AND r_ed8e33.i_manufact NOT LIKE r_ed8e33.i_product_name
    ) 
ORDER BY
    1 ASC;
----------->
SELECT
    r_2349b0.up_f32ae4 as te_13ddea,
    r_45c45c.wp_autogen_flag as te_696384,
    r_45c45c.wp_web_page_sk / r_32c24a.cc_division as te_5abead 
FROM
    db1.web_page AS r_0d1864 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.customer_address UNPIVOT INCLUDE NULLS ((up_476c6d,
            up_f32ae4,
            up_9ea205,
            up_9ea9e2) FOR upn_267f01 IN ((ca_address_sk,
            ca_gmt_offset,
            ca_county,
            ca_street_type) AS UPF_050b53))
    ) AS r_2349b0 
        ON r_0d1864.wp_image_count >= r_2349b0.up_476c6d,
    db1.web_page AS r_45c45c 
LEFT JOIN
    db1.call_center AS r_32c24a 
        ON r_45c45c.wp_rec_end_date != r_32c24a.cc_rec_start_date 
WHERE
    r_2349b0.ca_street_name LIKE r_32c24a.cc_state 
ORDER BY
    1 NULLS LAST, 2 ASC, 3 ASC 
LIMIT 27;
----------->
WITH CTE_a078c2(te_fa29d8, te_23b0a0, te_c2a91b) AS (WITH CTE_3dd994(te_181e2f) AS (SELECT
    r_6b08d6.sm_carrier as te_181e2f 
FROM
    db1.ship_mode AS r_6b08d6 
WHERE
    r_6b08d6.sm_contract NOT LIKE r_6b08d6.sm_type 
    AND r_6b08d6.sm_type >= 'R' 
GROUP BY
    1 
ORDER BY
    1 ASC NULLS FIRST 
OFFSET 38) SELECT
r_2e1fcc.pf_cb5d7c as te_fa29d8, mod(r_ce6a80.s_floor_space, r_2e1fcc.pf_bcd35f) / r_2e1fcc.cd_dep_count / r_2e1fcc.pf_30de19 as te_23b0a0, r_ce6a80.s_state as te_c2a91b FROM
    CTE_3dd994 AS r_432d7b 
RIGHT JOIN
    (SELECT
        * 
    FROM
        db1.customer_demographics PIVOT(count(cd_marital_status) AS pa_0c00f8 FOR cd_purchase_estimate IN ((72) AS pf_30de19,
        (10) AS pf_bcd35f,
        (77) AS pf_cb5d7c))) AS r_2e1fcc 
        ON r_432d7b.te_181e2f NOT ILIKE r_2e1fcc.cd_credit_rating,
    db1.customer_address AS r_a900fa 
INNER JOIN
    db1.store AS r_ce6a80 
        ON r_a900fa.ca_state NOT ILIKE r_ce6a80.s_city 
WHERE
    r_2e1fcc.cd_education_status LIKE r_ce6a80.s_city 
    AND r_2e1fcc.pf_30de19 < r_2e1fcc.pf_cb5d7c 
GROUP BY
    3, 1, 2 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST) SELECT
        r_aace14.up_9e7320 as te_9f872c 
    FROM
        CTE_a078c2 AS r_271dd2 
    INNER JOIN
        (
            SELECT
                * 
            FROM
                db1.store UNPIVOT EXCLUDE NULLS ((up_aa82ea,
                up_34850c,
                up_d930b1,
                up_9e7320,
                up_9be5ac) FOR upn_f69ea2 IN ((s_gmt_offset,
                s_company_id,
                s_hours,
                s_store_id,
                s_rec_start_date) AS UPF_fbc703))
        ) AS r_aace14 
            ON r_271dd2.te_fa29d8 >= r_aace14.s_division_id 
    WHERE
        r_aace14.s_street_number ILIKE r_aace14.s_market_desc 
        OR r_aace14.s_suite_number NOT LIKE 'px6kg7x2S' 
        AND r_aace14.s_division_id > 60 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    r_77e557.sr_fee as te_bcf7d8,
    r_77e557.sr_store_credit as te_e9e7d6,
    date_from_unix_date(r_77e557.sr_hdemo_sk) as te_a62a70 
FROM
    db1.store_returns AS r_77e557,
    (SELECT
        r_b63237.i_brand_id as te_4e10fe,
        r_b63237.i_manufact as te_1d4f5d,
        r_b63237.i_item_id as te_b8891d 
    FROM
        db1.item AS r_b63237,
        (SELECT
            r_163cc5.cc_company_name as te_53431f 
        FROM
            db1.call_center AS r_163cc5 
        RIGHT JOIN
            db1.household_demographics AS r_bc5707 
                ON r_163cc5.cc_open_date_sk >= r_bc5707.hd_dep_count 
        WHERE
            r_bc5707.hd_vehicle_count = 25 
            OR (
                NOT r_bc5707.hd_dep_count < r_163cc5.cc_call_center_sk 
                OR r_163cc5.cc_company_name LIKE r_163cc5.cc_street_number
            ) 
        ORDER BY
            1 DESC NULLS LAST) AS r_4faadf 
    WHERE
        r_b63237.i_product_name NOT LIKE r_4faadf.te_53431f 
        OR r_b63237.i_wholesale_cost = 73.1303951 
    GROUP BY
        3, 2, 1 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS LAST) AS r_a88e09 
    LEFT JOIN
        db1.reason AS r_2db198 
            ON r_a88e09.te_4e10fe >= r_2db198.r_reason_sk 
    WHERE
        r_77e557.sr_reason_sk = r_2db198.r_reason_sk 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC 
    OFFSET 67;
----------->
SELECT
    r_2a2f02.sr_cdemo_sk as te_13472a 
FROM
    db1.store AS r_9318d1 
INNER JOIN
    db1.store_returns AS r_2a2f02 
        ON r_9318d1.s_closed_date_sk != r_2a2f02.sr_item_sk 
ORDER BY
    1 
LIMIT 12;
----------->
SELECT
    r_f856f6.web_gmt_offset * 53 as te_db0b8d,
    r_f856f6.web_site_id as te_f74cfa,
    r_f856f6.web_company_id as te_2c5da7 
FROM
    db1.web_site AS r_f856f6,
    db1.ship_mode AS r_1d380f 
WHERE
    r_f856f6.web_site_sk > r_1d380f.sm_ship_mode_sk 
ORDER BY
    1 DESC, 2 DESC, 3 NULLS LAST;
----------->
SELECT
    r_e887d9.c_current_addr_sk as te_08f82c,
    r_e887d9.c_email_address as te_f34317 
FROM
    db1.household_demographics AS r_d1ad15 
INNER JOIN
    (
        SELECT
            r_f551e7.web_zip as te_51052f,
            r_f551e7.web_site_id as te_efedf1 
        FROM
            (SELECT
                r_428c5c.sm_ship_mode_sk as te_542aa7 
            FROM
                db1.ship_mode AS r_428c5c 
            WHERE
                r_428c5c.sm_contract NOT ILIKE r_428c5c.sm_ship_mode_id 
                OR r_428c5c.sm_type ILIKE 'C0lezmSRI' 
                AND r_428c5c.sm_carrier NOT ILIKE 'DXX4QHZgG' 
            ORDER BY
                1 ASC NULLS LAST 
            OFFSET 85) AS r_1a2183 RIGHT JOIN
            db1.time_dim AS r_1ffd32 
                ON r_1a2183.te_542aa7 < r_1ffd32.t_second,
            db1.reason AS r_215566 
        INNER JOIN
            db1.web_site AS r_f551e7 
                ON r_215566.r_reason_sk >= r_f551e7.web_site_sk 
        WHERE
            r_1ffd32.t_time_sk > r_f551e7.web_open_date_sk 
            AND r_f551e7.web_site_id NOT ILIKE r_f551e7.web_state 
            OR r_f551e7.web_class NOT LIKE 'ZgG' 
            AND r_1ffd32.t_time = 72 
            AND r_1ffd32.t_minute = r_f551e7.web_open_date_sk 
        ORDER BY
            1 DESC NULLS LAST, 2 NULLS LAST) AS r_c63a84 
                ON r_d1ad15.hd_buy_potential LIKE r_c63a84.te_51052f, db1.customer AS r_e887d9 
        WHERE
            r_c63a84.te_51052f NOT ILIKE r_e887d9.c_last_name 
            AND r_c63a84.te_51052f NOT ILIKE 'gD' 
            OR r_c63a84.te_efedf1 NOT ILIKE r_e887d9.c_customer_id 
            AND r_c63a84.te_51052f NOT LIKE 'v7R' 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC NULLS LAST 
        OFFSET 19;
----------->
SELECT
    r_888f68.web_mkt_id / 30.99143398D as te_ad4cd4 
FROM
    db1.customer AS r_67bdf8 
RIGHT JOIN
    db1.web_site AS r_888f68 
        ON r_67bdf8.c_customer_sk != r_888f68.web_close_date_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_36e42b.cc_tax_percentage as te_bcb967,
    r_098033.c_last_review_date_sk as te_40a3cd 
FROM
    db1.customer AS r_098033 
INNER JOIN
    db1.call_center AS r_36e42b 
        ON r_098033.c_birth_year != r_36e42b.cc_division,
    db1.warehouse AS r_eb71c4 
WHERE
    r_36e42b.cc_sq_ft != r_eb71c4.w_warehouse_sk 
    AND r_36e42b.cc_rec_end_date = r_36e42b.cc_rec_start_date 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_80c8c3.t_sub_shift as te_69c64a,
    r_a4689e.d_current_day as te_4577ba,
    r_a4689e.d_current_quarter as te_2486d6 
FROM
    db1.customer_demographics AS r_9dd399 
RIGHT JOIN
    db1.time_dim AS r_80c8c3 
        ON r_9dd399.cd_education_status ILIKE r_80c8c3.t_am_pm,
    db1.date_dim AS r_a4689e 
WHERE
    r_9dd399.cd_credit_rating NOT LIKE r_a4689e.d_current_month 
    AND r_a4689e.d_quarter_name <= '7' 
    OR r_a4689e.d_following_holiday LIKE 'hv9' 
ORDER BY
    1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS FIRST;
----------->
SELECT
    r_ed52e3.wp_rec_start_date as te_84fea1,
    r_ed52e3.wp_url as te_fbf461 
FROM
    db1.web_page AS r_ed52e3 
INNER JOIN
    (
        SELECT
            now() as te_12af32 
        FROM
            db1.store_returns AS r_779654 
        INNER JOIN
            db1.item AS r_cb47c5 
                ON r_779654.sr_customer_sk = r_cb47c5.i_wholesale_cost 
        WHERE
            r_cb47c5.i_manager_id != 40 
            OR r_779654.sr_refunded_cash = 56.00725973 
        ORDER BY
            1 ASC NULLS LAST 
        LIMIT 5
) AS r_e8aa0a 
    ON chr(character_length(r_ed52e3.wp_type)) = chr(char_length(string(r_e8aa0a.te_12af32))),
db1.date_dim AS r_01ef6f WHERE
    r_ed52e3.wp_rec_start_date != r_01ef6f.d_date 
    AND r_ed52e3.wp_link_count < r_01ef6f.d_week_seq 
    AND r_01ef6f.d_week_seq <= 10 
    OR r_01ef6f.d_date_id NOT LIKE 'Gv' 
    AND r_ed52e3.wp_url NOT ILIKE r_ed52e3.wp_type 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_a507b2.t_minute as te_104b35 
FROM
    db1.time_dim AS r_a507b2 
WHERE
    r_a507b2.t_time_id >= 'l' 
    OR r_a507b2.t_am_pm NOT LIKE r_a507b2.t_shift 
ORDER BY
    1 DESC;
----------->
SELECT
    try_add(48,
    r_23af77.d_month_seq) as te_62534a,
    r_23af77.d_date as te_875e64,
    r_75092e.sm_type as te_97d669 
FROM
    db1.ship_mode AS r_75092e,
    db1.date_dim AS r_23af77 
RIGHT JOIN
    db1.household_demographics AS r_27c4bf 
        ON r_23af77.d_quarter_seq > r_27c4bf.hd_dep_count 
WHERE
    r_75092e.sm_ship_mode_id LIKE r_23af77.d_quarter_name 
    OR r_23af77.d_current_month ILIKE 'SRI' 
    OR r_23af77.d_weekend ILIKE 'y7I' 
    OR r_23af77.d_current_day <= 'BRGC0' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_06d846.ib_upper_bound as te_ddb2fe 
FROM
    db1.reason AS r_81d007 
LEFT JOIN
    db1.income_band AS r_06d846 
        ON r_81d007.r_reason_sk <= r_06d846.ib_upper_bound 
WHERE
    r_81d007.r_reason_id NOT LIKE 'P' 
    OR r_06d846.ib_upper_bound < 13 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_2b2e55.wp_customer_sk as te_201972,
    r_2b2e55.wp_web_page_id as te_807ce3,
    r_2b2e55.wp_autogen_flag as te_a09b2f 
FROM
    db1.income_band AS r_0f88f4 
LEFT JOIN
    db1.customer AS r_42c0d8 
        ON r_0f88f4.ib_upper_bound < r_42c0d8.c_customer_sk,
    db1.web_page AS r_2b2e55 
WHERE
    r_42c0d8.c_login ILIKE r_2b2e55.wp_url 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_18bc1f.cc_rec_end_date as te_d2eb26 
FROM
    db1.call_center AS r_18bc1f 
ORDER BY
    1 DESC 
LIMIT 28;
----------->
SELECT
    try_add(r_fd1071.s_number_employees,
    r_8b17e2.web_rec_start_date) as te_177537,
    r_fd1071.s_tax_percentage as te_32425f 
FROM
    db1.store AS r_fd1071 
RIGHT JOIN
    db1.ship_mode AS r_cd79f6 
        ON r_fd1071.s_store_sk >= r_cd79f6.sm_ship_mode_sk,
    db1.web_site AS r_8b17e2 
WHERE
    r_fd1071.s_gmt_offset = r_8b17e2.web_mkt_id 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST;
----------->
WITH CTE_577bff(te_cc43b4, te_a41d14) AS (SELECT
    chr(32.50285769 + r_4aa548.hd_income_band_sk) as te_cc43b4,
    (91 + r_4aa548.hd_vehicle_count) * (r_4aa548.hd_vehicle_count + 99) as te_a41d14 
FROM
    (SELECT
        r_c6bd1f.sm_contract as te_8026dd 
    FROM
        db1.ship_mode AS r_c6bd1f 
    WHERE
        r_c6bd1f.sm_type ILIKE r_c6bd1f.sm_ship_mode_id 
        AND r_c6bd1f.sm_contract ILIKE r_c6bd1f.sm_ship_mode_id 
        AND r_c6bd1f.sm_code = 'XpRQiizz2' 
    ORDER BY
        1 ASC NULLS LAST) AS r_ecb07b, db1.household_demographics AS r_4aa548 
WHERE
    r_ecb07b.te_8026dd LIKE r_4aa548.hd_buy_potential 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST), CTE_ee198c(te_dcda9b) AS (SELECT
        r_33efa1.te_c40115 as te_dcda9b 
    FROM
        (SELECT
            r_404f2f.ca_location_type as te_8e7bbe,
            r_404f2f.ca_location_type as te_c40115 
        FROM
            db1.customer_address AS r_404f2f,
            db1.income_band AS r_234094 
        WHERE
            r_404f2f.ca_address_sk != r_234094.ib_lower_bound 
            OR r_404f2f.ca_street_type LIKE 'lstn' 
            OR r_404f2f.ca_address_id ILIKE r_404f2f.ca_zip 
            OR r_404f2f.ca_county NOT ILIKE r_404f2f.ca_street_name 
        ORDER BY
            1 NULLS LAST, 2 DESC 
        OFFSET 31) AS r_33efa1 WHERE
        r_33efa1.te_8e7bbe NOT ILIKE '7R' 
        OR r_33efa1.te_c40115 NOT LIKE 'sU' 
        OR r_33efa1.te_c40115 NOT ILIKE 'O' 
        OR r_33efa1.te_8e7bbe NOT ILIKE 'QH' 
    ORDER BY
        1 ASC NULLS LAST) SELECT
            e() as te_c10ddb 
        FROM
            (SELECT
                r_e3a988.wp_link_count as te_86ba37,
                r_aa01d9.t_shift || r_e9a420.wp_autogen_flag as te_25dd2e 
            FROM
                (SELECT
                    reverse(r_dd8980.te_1490e0) as te_59c68e 
                FROM
                    (SELECT
                        r_5e2626.web_market_manager as te_b5e4b5,
                        r_59fc81.cd_credit_rating as te_1490e0,
                        r_5e2626.web_rec_end_date as te_0deee6 
                    FROM
                        db1.customer_demographics AS r_59fc81 
                    INNER JOIN
                        db1.web_site AS r_5e2626 
                            ON r_59fc81.cd_credit_rating LIKE r_5e2626.web_city,
                        db1.store_returns AS r_37d6c9 
                    WHERE
                        r_5e2626.web_tax_percentage >= r_37d6c9.sr_store_credit 
                        OR r_5e2626.web_country LIKE 'W4Pg' 
                        AND r_59fc81.cd_gender ILIKE 'lstn' 
                        AND r_5e2626.web_manager NOT LIKE r_5e2626.web_mkt_class) AS r_dd8980 
                WHERE
                    reverse(r_dd8980.te_1490e0) NOT LIKE chr(unix_seconds(timestamp(r_dd8980.te_0deee6))) 
                    OR r_dd8980.te_b5e4b5 NOT ILIKE 'O' 
                    OR string(r_dd8980.te_1490e0) ILIKE r_dd8980.te_b5e4b5
                ) AS r_c82d2a 
            INNER JOIN
                db1.web_page AS r_e3a988 
                    ON r_c82d2a.te_59c68e LIKE r_e3a988.wp_url,
                (SELECT
                    * 
                FROM
                    db1.time_dim PIVOT(max(t_time_sk) AS pa_d84306 FOR t_hour IN ((8) AS pf_9cfbd8,
                    (30) AS pf_1c7bab,
                    (68) AS pf_dc1083))) AS r_aa01d9 
            RIGHT JOIN
                db1.web_page AS r_e9a420 
                    ON r_aa01d9.t_second = r_e9a420.wp_access_date_sk 
            WHERE
                r_c82d2a.te_59c68e ILIKE r_e9a420.wp_web_page_id 
                AND r_e3a988.wp_customer_sk = 30 
                OR r_e3a988.wp_autogen_flag LIKE 'GIH' 
                OR r_e3a988.wp_max_ad_count = 11 
            ORDER BY
                1 ASC NULLS LAST, 2 DESC NULLS FIRST) AS r_be30cf 
            LEFT JOIN
                CTE_577bff AS r_5f9fd9 
                    ON r_be30cf.te_86ba37 >= r_5f9fd9.te_a41d14 
            WHERE
                chr(r_5f9fd9.te_a41d14) >= r_be30cf.te_25dd2e 
            ORDER BY
                1 DESC NULLS LAST;
----------->
SELECT
    r_36ad54.web_mkt_id as te_044b9f 
FROM
    db1.web_site AS r_36ad54 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.web_site PIVOT(min(web_city) AS pa_980c3c FOR web_site_sk IN ((92) AS pf_9f36fa,
            (15) AS pf_66148e,
            (54) AS pf_f83fdc,
            (17) AS pf_807234,
            (25) AS pf_1cd33a))
    ) AS r_9c4e8b 
        ON r_36ad54.web_state ILIKE r_9c4e8b.web_state,
    db1.call_center AS r_30ddfe 
INNER JOIN
    db1.customer_demographics AS r_6b0bd3 
        ON r_30ddfe.cc_company > r_6b0bd3.cd_purchase_estimate 
WHERE
    r_36ad54.web_site_id NOT ILIKE r_6b0bd3.cd_education_status 
    OR r_30ddfe.cc_sq_ft <= 5 
    OR NOT r_6b0bd3.cd_marital_status != 'IvX' EXCEPT ALL (
        SELECT
            r_0a62b3.r_reason_sk as te_917145 
        FROM
            db1.reason AS r_0a62b3 
        WHERE
            r_0a62b3.r_reason_sk != 94 
            OR (
                NOT r_0a62b3.r_reason_id LIKE r_0a62b3.r_reason_desc 
                AND r_0a62b3.r_reason_desc NOT ILIKE '6'
            ) 
        ORDER BY
            1 DESC
    ) 
ORDER BY
    1 ASC NULLS FIRST;
----------->
WITH CTE_3139c9(te_910add) AS (SELECT
    r_66c789.te_9e58f0 as te_910add 
FROM
    (SELECT
        r_39251f.t_sub_shift as te_9e58f0 
    FROM
        db1.time_dim AS r_39251f 
    LEFT JOIN
        db1.income_band AS r_6d20ad 
            ON r_39251f.t_second < r_6d20ad.ib_upper_bound 
    WHERE
        r_39251f.t_meal_time = r_39251f.t_am_pm 
    ORDER BY
        1 DESC NULLS FIRST) AS r_66c789 
WHERE
    r_66c789.te_9e58f0 ILIKE r_66c789.te_9e58f0 
    OR r_66c789.te_9e58f0 LIKE 'v7RXpR' 
ORDER BY
    1 ASC NULLS FIRST), CTE_abd00e(te_292bfb, te_2e9786, te_482343) AS (SELECT
        r_749ec6.cc_rec_start_date as te_292bfb, r_749ec6.cc_rec_end_date as te_2e9786, reflect('java.util.UUID', 'randomUUID') as te_482343 
    FROM
        (SELECT
            * 
        FROM
            db1.call_center PIVOT(min(cc_closed_date_sk) AS pa_f41c15 FOR cc_gmt_offset IN ((54.32355185) AS pf_a007d7,
            (79.10397667) AS pf_a27fd8))) AS r_749ec6,
        db1.reason AS r_c76940 
    RIGHT JOIN
        db1.store_returns AS r_1b8e2f 
            ON r_c76940.r_reason_sk > r_1b8e2f.sr_return_ship_cost 
    WHERE
        r_749ec6.cc_tax_percentage > r_1b8e2f.sr_net_loss 
        AND r_1b8e2f.sr_return_amt_inc_tax < 1.00833546 
        OR r_749ec6.cc_name ILIKE r_749ec6.cc_hours 
        AND r_749ec6.cc_rec_end_date <= r_749ec6.cc_rec_start_date 
    ORDER BY
        1, 2 DESC, 3 ASC) SELECT
            current_date() as te_8e55d1 
        FROM
            CTE_3139c9 AS r_e52aeb 
        WHERE
            r_e52aeb.te_910add NOT ILIKE 'g' 
            AND r_e52aeb.te_910add NOT LIKE r_e52aeb.te_910add 
            AND r_e52aeb.te_910add LIKE 'Gk4e' 
        ORDER BY
            1 ASC NULLS LAST;
----------->
SELECT
    r_32cce3.c_preferred_cust_flag as te_3c7e09 
FROM
    db1.customer AS r_32cce3 
WHERE
    r_32cce3.c_last_name != 'DCMaL' 
ORDER BY
    1 NULLS LAST 
OFFSET 21;
----------->
SELECT
    unix_timestamp() as te_3777a2,
    chr(e()) as te_398512,
    min(r_31392b.w_county) as te_5e0265 
FROM
    db1.ship_mode AS r_b27dee,
    db1.warehouse AS r_31392b 
WHERE
    r_b27dee.sm_ship_mode_sk <= r_31392b.w_warehouse_sq_ft 
    OR r_31392b.w_gmt_offset > 82.13992734 
    AND r_b27dee.sm_ship_mode_sk != 81 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_80ae8f.c_customer_id as te_2aa1c9,
    r_c603d8.sm_ship_mode_id as te_01a802,
    r_c603d8.sm_contract as te_250b65 
FROM
    db1.customer AS r_80ae8f 
INNER JOIN
    db1.time_dim AS r_c0f05e 
        ON r_80ae8f.c_salutation LIKE r_c0f05e.t_sub_shift,
    db1.ship_mode AS r_c603d8 
INNER JOIN
    (
        SELECT
            cos(r_60f8c1.hd_demo_sk) as te_5a7309 
        FROM
            db1.household_demographics AS r_60f8c1 
        RIGHT JOIN
            db1.web_page AS r_768a0f 
                ON r_60f8c1.hd_buy_potential NOT ILIKE r_768a0f.wp_url 
        ORDER BY
            1 DESC NULLS FIRST
    ) AS r_9d24c4 
        ON r_c603d8.sm_contract LIKE chr(r_9d24c4.te_5a7309) 
WHERE
    r_c0f05e.t_time = r_c603d8.sm_ship_mode_sk 
    OR r_80ae8f.c_preferred_cust_flag LIKE r_80ae8f.c_customer_id 
ORDER BY
    1 ASC, 2 ASC NULLS LAST, 3 DESC;
----------->
SELECT
    r_03efff.sr_addr_sk as te_5996c9,
    r_2aa7af.s_store_id || r_2aa7af.s_state as te_c25f75,
    r_2aa7af.s_street_number as te_ce9b51 
FROM
    db1.store_returns AS r_03efff 
LEFT JOIN
    db1.time_dim AS r_bea874 
        ON r_03efff.sr_return_tax = r_bea874.t_second,
    db1.customer_demographics AS r_351343 
INNER JOIN
    db1.store AS r_2aa7af 
        ON r_351343.cd_demo_sk >= r_2aa7af.s_number_employees 
WHERE
    r_03efff.sr_refunded_cash != r_2aa7af.s_gmt_offset 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
(
    SELECT
        unix_timestamp() as te_01f2a7,
        unix_timestamp() / unix_timestamp() as te_c9afee,
        date_add(r_77f113.i_rec_end_date,
        r_856146.cd_dep_employed_count) as te_1836ab 
    FROM
        (SELECT
            * 
        FROM
            db1.reason UNPIVOT EXCLUDE NULLS ((up_f4f2bd,
            up_7f6c50) FOR upn_51abff IN ((r_reason_sk,
            r_reason_id) AS UPF_76e9ef))) AS r_86e9fa 
    INNER JOIN
        db1.item AS r_77f113 
            ON r_86e9fa.r_reason_desc ILIKE r_77f113.i_formulation,
        db1.customer_demographics AS r_856146 
    WHERE
        r_77f113.i_category_id < r_856146.cd_dep_count 
        AND r_77f113.i_rec_start_date < r_77f113.i_rec_end_date 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC 
    LIMIT 21) UNION
    ALL (
        SELECT
            71 as te_edce4f,
            variance(r_9a00e9.sr_item_sk) as te_7a4a28,
            make_date(r_9a00e9.sr_hdemo_sk,
            r_9a00e9.sr_ticket_number,
            r_9a00e9.sr_return_quantity) as te_657744 
        FROM
            db1.store_returns AS r_9a00e9 
        WHERE
            r_9a00e9.web_county >= r_9a00e9.sr_store_credit 
        GROUP BY
            r_9a00e9.sr_return_quantity, r_9a00e9.sr_ticket_number, r_9a00e9.sr_hdemo_sk 
        ORDER BY
            1 DESC NULLS LAST, 2, 3 NULLS LAST
    ) 
ORDER BY
    1 ASC, 2 DESC, 3 DESC NULLS LAST;
----------->
(
    SELECT
        r_1b2c1b.t_sub_shift as te_555a75 
    FROM
        db1.time_dim AS r_1b2c1b 
    ORDER BY
        1 DESC 
    OFFSET 84
) EXCEPT ALL (
SELECT
    r_40438c.i_item_desc as te_d96fe3 FROM
        db1.reason AS r_a0b7d7 
    LEFT JOIN
        db1.web_site AS r_445a55 
            ON r_445a55.web_state LIKE 'QFrTU',
        db1.item AS r_40438c 
    WHERE
        r_445a55.web_state NOT LIKE r_40438c.i_item_id 
        AND r_40438c.i_item_id IN (
            SELECT
                '7i' as te_dba813 
            FROM
                (SELECT
                    r_05d0c7.sr_reversed_charge as te_7ca202,
                    r_05d0c7.sr_customer_sk / r_05d0c7.sr_net_loss as te_746053,
                    r_05d0c7.sr_item_sk as te_31495e 
                FROM
                    db1.store_returns AS r_05d0c7 
                INNER JOIN
                    db1.customer_demographics AS r_cc4f35 
                        ON r_05d0c7.sr_store_sk = r_cc4f35.cd_dep_college_count,
                    db1.warehouse AS r_a7665a 
                WHERE
                    r_cc4f35.cd_education_status LIKE r_a7665a.w_county 
                    AND r_cc4f35.cd_dep_employed_count < 56 
                    AND r_a7665a.w_warehouse_name LIKE r_a7665a.w_zip 
                ORDER BY
                    1 DESC, 2 DESC, 3 DESC NULLS LAST) AS r_b94e69 
            ORDER BY
                1 NULLS LAST) 
                AND r_40438c.i_formulation LIKE '7x2SW4' 
            ORDER BY
                1 ASC NULLS FIRST
        ) 
    ORDER BY
        1 DESC NULLS FIRST 
    LIMIT 3 OFFSET 53;
----------->
WITH CTE_545e7f(te_7dea6b) AS (SELECT
    r_eb5487.wp_rec_end_date as te_7dea6b 
FROM
    db1.web_page AS r_eb5487 
WHERE
    r_eb5487.wp_link_count = r_eb5487.wp_char_count 
ORDER BY
    1 DESC), CTE_c458fc(te_6c4ea4, te_484067, te_cb1d03) AS (SELECT
    reflect('java.util.UUID', 'randomUUID') as te_6c4ea4, r_a90156.sr_returned_date_sk as te_484067, r_a90156.sr_store_credit as te_cb1d03 
FROM
    db1.store_returns AS r_a90156 
INNER JOIN
    (SELECT
        r_9c921a.wp_customer_sk as te_5dfa34 
    FROM
        db1.web_page AS r_9c921a) AS r_f7ee25 
        ON r_a90156.sr_return_amt_inc_tax = r_f7ee25.te_5dfa34,
    CTE_545e7f AS r_81c7b3 
WHERE
    date_from_unix_date(r_a90156.sr_store_sk) >= r_81c7b3.te_7dea6b 
    OR r_a90156.sr_reversed_charge = 28.45522559 
ORDER BY
    1 NULLS LAST, 2, 3 DESC NULLS LAST) SELECT
        r_10ee2c.te_6c4ea4 as te_99cc5d 
    FROM
        CTE_545e7f AS r_4533a2 
    INNER JOIN
        CTE_c458fc AS r_10ee2c 
            ON date_from_unix_date(char_length(string(r_4533a2.te_7dea6b))) <= date_from_unix_date(char_length(chr(r_10ee2c.te_cb1d03))) 
    WHERE
        r_10ee2c.te_6c4ea4 ILIKE 'T' 
        OR date_from_unix_date(r_10ee2c.te_484067) = r_4533a2.te_7dea6b 
        AND r_10ee2c.te_484067 < 37 
    ORDER BY
        1 DESC NULLS FIRST;
----------->
SELECT
    current_timestamp() as te_0adb06 
FROM
    db1.web_site AS r_69a648 
LEFT JOIN
    db1.household_demographics AS r_941b9b 
        ON r_69a648.web_street_number ILIKE r_941b9b.hd_buy_potential 
ORDER BY
    1 DESC;
----------->
SELECT
    r_a161bf.ca_street_type as te_4f8e23,
    r_3412db.s_gmt_offset as te_6f8a3d 
FROM
    db1.income_band AS r_bc387c,
    db1.store AS r_3412db 
LEFT JOIN
    db1.customer_address AS r_a161bf 
        ON r_3412db.s_division_id >= r_a161bf.ca_address_sk 
WHERE
    r_bc387c.ib_upper_bound >= r_3412db.s_closed_date_sk 
    AND r_3412db.s_rec_end_date >= DATE'2024-10-11' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    current_timestamp() as te_b0fdcb,
    ascii(r_c78bbc.i_color) as te_5206ab,
    r_25f69f.wp_web_page_id as te_6e5979 
FROM
    db1.web_page AS r_25f69f 
RIGHT JOIN
    db1.web_page AS r_755fc9 
        ON r_25f69f.wp_rec_end_date != r_755fc9.wp_rec_end_date,
    db1.item AS r_c78bbc 
RIGHT JOIN
    db1.item AS r_4c9e20 
        ON r_c78bbc.i_class_id < r_4c9e20.i_current_price 
WHERE
    r_755fc9.wp_rec_end_date = r_4c9e20.i_rec_end_date 
    OR (
        NOT r_755fc9.wp_autogen_flag NOT IN (
            SELECT
                r_7d6a68.up_e398ad as te_953c2c 
            FROM
                (SELECT
                    * 
                FROM
                    db1.web_site UNPIVOT INCLUDE NULLS ((up_3b942e,
                    up_5cdcaa,
                    up_9134ac,
                    up_e398ad,
                    up_cb460b) FOR upn_4637f0 IN ((web_country,
                    web_company_id,
                    web_rec_end_date,
                    web_site_id,
                    web_gmt_offset) AS UPF_eb2835,
                    (web_county,
                    web_close_date_sk,
                    web_rec_start_date,
                    web_state,
                    web_tax_percentage) AS UPF_ca08c1))) AS r_7d6a68 
            WHERE
                r_7d6a68.web_street_name LIKE r_7d6a68.web_mkt_desc 
                OR r_7d6a68.web_open_date_sk = 80 
                AND (
                    NOT NOT r_7d6a68.web_city ILIKE r_7d6a68.web_class 
                    OR r_7d6a68.web_zip ILIKE 'ntb'
                ) 
            ORDER BY
                1 ASC NULLS LAST) 
                OR r_4c9e20.i_wholesale_cost = r_4c9e20.i_current_price 
                AND r_4c9e20.i_size LIKE 'kGk4' 
                AND r_25f69f.wp_url ILIKE 'ntb2Gv7RX'
        ) 
    ORDER BY
        1 ASC, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_3da94a.d_weekend as te_1faed5,
    r_0eabc8.ca_country as te_1773bf,
    r_a356eb.wp_rec_end_date as te_b41a59 
FROM
    db1.customer_address AS r_0eabc8,
    db1.date_dim AS r_3da94a 
INNER JOIN
    db1.web_page AS r_a356eb 
        ON r_3da94a.d_dow != r_a356eb.wp_creation_date_sk 
WHERE
    r_0eabc8.ca_street_number NOT ILIKE r_3da94a.d_date_id 
    OR r_0eabc8.ca_suite_number NOT LIKE r_0eabc8.ca_street_number 
    AND r_3da94a.d_following_holiday IN (
        SELECT
            r_716057.i_item_id as te_2c561c 
        FROM
            db1.item AS r_716057 
        WHERE
            r_716057.i_manufact_id <= 76 
        ORDER BY
            1 ASC NULLS FIRST 
        LIMIT 52
) 
AND r_a356eb.wp_url NOT ILIKE 'gDDXX4QHZg' 
OR r_a356eb.wp_access_date_sk < 14 ORDER BY
    1 NULLS LAST, 2, 3 ASC;
----------->
SELECT
    r_2634ce.wp_autogen_flag as te_3228de,
    r_2634ce.wp_creation_date_sk as te_aca8ba 
FROM
    db1.web_page AS r_2634ce,
    db1.web_page AS r_c99f61 
WHERE
    r_2634ce.wp_creation_date_sk < r_c99f61.wp_link_count 
    AND r_c99f61.wp_rec_end_date >= DATE'2024-03-26' 
GROUP BY
    2, 1 
ORDER BY
    1, 2 NULLS LAST 
LIMIT 99;
----------->
SELECT
    chr(r_549d5b.sr_cdemo_sk) as te_667e37 
FROM
    db1.income_band AS r_2d75c4 
LEFT JOIN
    db1.store_returns AS r_549d5b 
        ON r_2d75c4.ib_lower_bound < r_549d5b.sr_store_sk 
WHERE
    r_549d5b.sr_refunded_cash = r_549d5b.sr_store_credit 
    OR r_549d5b.sr_return_quantity < 33 
    AND r_549d5b.sr_return_ship_cost != 2.37309292 
    OR r_549d5b.sr_return_time_sk < r_2d75c4.ib_upper_bound 
ORDER BY
    1 
OFFSET 48;
----------->
SELECT
    r_986744.ca_gmt_offset as te_c39651 
FROM
    db1.household_demographics AS r_7952e9 
RIGHT JOIN
    db1.customer_address AS r_986744 
        ON r_7952e9.hd_demo_sk >= r_986744.ca_address_sk 
WHERE
    r_7952e9.hd_vehicle_count != r_7952e9.hd_dep_count 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_ef5459.web_county as te_75ca9d,
    chr(bigint(r_ef5459.web_mkt_id / 82.39036331D)) as te_63b457 
FROM
    db1.web_site AS r_ef5459 
RIGHT JOIN
    db1.call_center AS r_ee7bcb 
        ON r_ef5459.web_gmt_offset <= r_ee7bcb.cc_tax_percentage,
    db1.time_dim AS r_fafa6b 
RIGHT JOIN
    db1.warehouse AS r_8b5fa6 
        ON r_fafa6b.t_second > r_8b5fa6.w_warehouse_sk 
WHERE
    r_ef5459.web_open_date_sk != r_fafa6b.t_minute 
    OR r_ee7bcb.cc_country IS NULL 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_fdd451.sm_contract as te_a2771f,
    now() as te_42e552,
    r_a4d95b.wp_autogen_flag as te_de073d 
FROM
    db1.web_page AS r_9159b8,
    db1.ship_mode AS r_fdd451 
LEFT JOIN
    db1.web_page AS r_a4d95b 
        ON r_fdd451.sm_ship_mode_sk < r_a4d95b.wp_image_count 
WHERE
    r_9159b8.wp_rec_start_date > r_a4d95b.wp_rec_end_date 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS FIRST 
LIMIT 58;
----------->
SELECT
    chr(r_ce12d6.web_mkt_id) as te_b27fc8 
FROM
    db1.web_site AS r_ce12d6 
WHERE
    r_ce12d6.web_street_name LIKE 'ir' 
    AND r_ce12d6.web_rec_end_date <= DATE'2024-10-11' 
ORDER BY
    1 DESC 
OFFSET 92;
----------->
SELECT
    r_60c26e.r_reason_sk as te_85634d 
FROM
    db1.reason AS r_60c26e 
WHERE
    r_60c26e.r_reason_desc NOT IN (
        SELECT
            r_20a2d3.ca_street_name as te_ea4cd9 
        FROM
            db1.customer_address AS r_20a2d3 
        LEFT JOIN
            db1.store_returns AS r_ba8d8b 
                ON r_20a2d3.ca_address_sk != r_ba8d8b.sr_reversed_charge 
        WHERE
            r_ba8d8b.sr_reason_sk = r_ba8d8b.sr_return_quantity 
        ORDER BY
            1 NULLS LAST
    ) 
    OR r_60c26e.r_reason_desc NOT ILIKE '2' 
    OR r_60c26e.r_reason_desc <= '7IG7ir98h' 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_72f21c.c_current_hdemo_sk as te_38b38d 
FROM
    db1.customer AS r_72f21c 
WHERE
    r_72f21c.c_preferred_cust_flag NOT ILIKE r_72f21c.c_customer_id 
    OR r_72f21c.c_customer_id ILIKE r_72f21c.c_preferred_cust_flag 
    OR r_72f21c.c_customer_id NOT ILIKE r_72f21c.c_preferred_cust_flag 
ORDER BY
    1;
----------->
SELECT
    hash(month(current_timestamp()),
    true) as te_ab377e 
FROM
    (SELECT
        r_03b444.ca_country as te_16b5bd,
        r_03b444.ca_gmt_offset as te_e5bc0d,
        r_03b444.ca_city as te_294467 
    FROM
        db1.customer_demographics AS r_f6af72,
        db1.customer_address AS r_03b444 
    WHERE
        r_f6af72.cd_education_status = r_03b444.ca_zip 
    ORDER BY
        1 NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST 
    OFFSET 22) AS r_b5acd7 WHERE
    r_b5acd7.te_16b5bd NOT ILIKE r_b5acd7.te_294467 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_cc7432.t_sub_shift as te_682300 
FROM
    db1.time_dim AS r_cc7432 
LEFT JOIN
    (
        SELECT
            r_31849f.d_quarter_seq as te_361a27,
            pi() as te_8d2325 
        FROM
            db1.date_dim AS r_31849f,
            db1.reason AS r_3941d4 
        WHERE
            r_31849f.d_same_day_ly < r_3941d4.r_reason_sk 
            OR r_31849f.d_current_month LIKE 'Z'
    ) AS r_63ee6f 
        ON r_cc7432.t_time_sk = r_63ee6f.te_361a27 
WHERE
    r_cc7432.t_shift NOT LIKE r_cc7432.t_am_pm 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_80e3c4.web_state as te_926ed8,
    current_date() as te_228efe 
FROM
    db1.web_site AS r_80e3c4,
    (SELECT
        r_368a91.web_rec_start_date as te_de0080,
        r_368a91.web_tax_percentage as te_3371ae 
    FROM
        db1.web_site AS r_368a91 
    LEFT JOIN
        db1.income_band AS r_e2d5ac 
            ON r_368a91.web_open_date_sk < r_e2d5ac.ib_upper_bound,
        db1.reason AS r_534ed5 
    WHERE
        r_368a91.web_mkt_id = r_534ed5.r_reason_sk 
        OR r_e2d5ac.ib_upper_bound > 35 
        AND r_368a91.web_site_id NOT ILIKE r_368a91.web_state 
        AND r_368a91.web_tax_percentage >= 94.54229995 
        OR NOT r_368a91.web_street_name NOT ILIKE 'TU' 
    ORDER BY
        1, 2 DESC NULLS LAST) AS r_080fc4 
LEFT JOIN
    db1.call_center AS r_341896 
        ON r_080fc4.te_de0080 != r_341896.cc_rec_start_date 
WHERE
    r_80e3c4.web_rec_end_date != r_341896.cc_rec_start_date 
    AND r_341896.cc_call_center_id NOT ILIKE r_80e3c4.web_site_id 
    AND r_080fc4.te_3371ae <= r_80e3c4.web_gmt_offset 
    OR r_341896.cc_state ILIKE r_341896.cc_street_number 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC NULLS FIRST;
----------->
SELECT
    make_date(r_693e57.web_open_date_sk,
    r_693e57.web_close_date_sk,
    r_693e57.web_open_date_sk) as te_321e00 
FROM
    db1.web_site AS r_693e57 
WHERE
    r_693e57.web_rec_end_date BETWEEN r_693e57.web_rec_start_date AND DATE'2024-10-11' 
    OR r_693e57.web_county LIKE 'GC0lezmSR' 
    OR r_693e57.web_gmt_offset >= r_693e57.web_tax_percentage 
    OR r_693e57.web_mkt_id NOT IN (
        WITH CTE_f420c8(te_f0221c) AS (SELECT
            r_411d39.r_reason_sk as te_f0221c 
        FROM
            db1.reason AS r_411d39 
        WHERE
            r_411d39.r_reason_id NOT LIKE r_411d39.r_reason_desc 
            AND r_411d39.r_reason_desc ILIKE 'RQiizz2kG' 
            OR r_411d39.r_reason_desc ILIKE r_411d39.r_reason_id 
            OR r_411d39.r_reason_desc NOT LIKE r_411d39.r_reason_id 
        GROUP BY
            1 
        ORDER BY
            1 DESC NULLS FIRST) SELECT
            try_add(96, r_beb710.te_f0221c) as te_32574a 
        FROM
            CTE_f420c8 AS r_beb710 
        WHERE
            r_beb710.te_f0221c < 12 
            OR r_beb710.te_f0221c != r_beb710.te_f0221c 
            AND r_beb710.te_f0221c > r_beb710.te_f0221c 
            OR r_beb710.te_f0221c > r_beb710.te_f0221c 
        ORDER BY
            1 DESC
    ) 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_47b09e.web_state as te_7538ef 
FROM
    db1.time_dim AS r_237242 
INNER JOIN
    db1.web_site AS r_47b09e 
        ON r_237242.t_minute >= r_47b09e.web_close_date_sk 
WHERE
    r_47b09e.web_rec_start_date != r_47b09e.web_rec_end_date 
    AND r_47b09e.web_company_id != 94 
    OR r_47b09e.web_mkt_class ILIKE r_47b09e.web_manager 
    AND r_237242.t_hour >= r_237242.t_minute 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_1647ee.s_rec_start_date as te_255ebb 
FROM
    db1.store AS r_1647ee 
WHERE
    r_1647ee.s_suite_number NOT ILIKE 'tntb' 
    OR r_1647ee.s_street_type NOT ILIKE '4elBy7IG7i' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_c89e00.w_warehouse_sk as te_c7423d 
FROM
    db1.warehouse AS r_c89e00 
WHERE
    r_c89e00.w_county NOT LIKE ((SELECT
        'ntb2' as te_59982e 
    FROM
        db1.income_band AS r_a56651 
    ORDER BY
        1 NULLS FIRST 
    LIMIT 1)) 
OR r_c89e00.w_suite_number LIKE 'G' ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_696391.s_store_id as te_8cec74 
FROM
    db1.store AS r_696391 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.reason PIVOT(sum(r_reason_sk) AS pa_d30fad FOR r_reason_id IN (('Uulstn') AS pf_3a5fd0,
            ('ntb2G') AS pf_61cc5c,
            ('mSRIv') AS pf_f36dbb,
            ('v7RXpRQiiz') AS pf_8ca555,
            ('Vb') AS pf_4ee742,
            ('OVbBRG') AS pf_c714ad))
    ) AS r_226c88 
        ON r_696391.s_zip LIKE r_226c88.r_reason_desc 
WHERE
    r_696391.s_gmt_offset > 84.0915645 
    AND r_696391.s_gmt_offset != r_696391.s_tax_percentage 
    OR r_696391.s_rec_start_date <= r_696391.s_rec_end_date 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 39;
----------->
SELECT
    make_timestamp(r_036732.i_category_id,
    r_61d26d.i_manager_id,
    r_036732.i_class_id,
    r_4f2f37.cd_demo_sk,
    r_4f2f37.cd_dep_employed_count,
    r_61d26d.i_current_price) as te_e6846a,
    substring(r_61d26d.i_category,
    78) as te_b9fb94,
    r_036732.i_item_sk as te_39149b 
FROM
    db1.customer_demographics AS r_4f2f37 
INNER JOIN
    db1.reason AS r_85dd2b 
        ON r_4f2f37.cd_dep_college_count != r_85dd2b.r_reason_sk,
    db1.item AS r_036732 
RIGHT JOIN
    db1.item AS r_61d26d 
        ON r_036732.i_manager_id >= r_61d26d.i_manufact_id 
WHERE
    r_4f2f37.cd_dep_employed_count >= r_61d26d.i_category_id 
    AND r_036732.i_item_sk != r_4f2f37.cd_dep_college_count 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    current_timestamp() as te_c78448,
    r_030639.w_gmt_offset as te_02c2a0 
FROM
    db1.warehouse AS r_030639 
INNER JOIN
    db1.customer_demographics AS r_be0378 
        ON r_030639.w_county NOT ILIKE r_be0378.cd_gender,
    db1.customer_address AS r_fb3eda 
WHERE
    r_be0378.cd_dep_college_count < r_fb3eda.ca_gmt_offset 
    AND r_030639.w_street_name NOT ILIKE 'el' 
    OR r_030639.w_zip NOT ILIKE r_030639.w_country 
    OR r_030639.w_warehouse_sk BETWEEN r_fb3eda.ca_gmt_offset * 87 AND 84 
    OR r_030639.w_street_type != 'elBy7IG7ir' 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_7c0002.w_county as te_f5c77d,
    r_de98d1.web_rec_end_date as te_6d969e 
FROM
    db1.income_band AS r_5c61af 
INNER JOIN
    db1.warehouse AS r_7c0002 
        ON r_5c61af.ib_lower_bound = r_7c0002.w_gmt_offset,
    db1.time_dim AS r_c31d7e 
RIGHT JOIN
    db1.web_site AS r_de98d1 
        ON r_c31d7e.t_sub_shift LIKE r_de98d1.web_state 
WHERE
    (
        NOT r_7c0002.w_warehouse_name ILIKE r_de98d1.web_country 
        OR r_de98d1.web_tax_percentage = r_7c0002.w_gmt_offset 
        OR r_7c0002.w_city NOT ILIKE 'kGk4elB'
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    r_a850da.ib_income_band_sk as te_291117 
FROM
    db1.income_band AS r_a850da 
ORDER BY
    1 DESC;
----------->
SELECT
    r_9d64cc.w_warehouse_sk * r_f859bd.te_373295 as te_77cf3b,
    current_timestamp() as te_f78180 
FROM
    db1.customer_address AS r_36f0c5 
LEFT JOIN
    db1.warehouse AS r_9d64cc 
        ON r_36f0c5.ca_country ILIKE r_9d64cc.w_street_number,
    (SELECT
        r_71f602.s_rec_end_date as te_eaa44d,
        mod(hash(r_71f602.s_store_id,
        current_timestamp()),
        mod(r_1b7f78.hd_dep_count,
        96)) as te_373295 
    FROM
        db1.store AS r_71f602 
    RIGHT JOIN
        (
            SELECT
                r_60b78c.t_minute as te_fac033,
                r_60b78c.t_second as te_f5e7b0 
            FROM
                (SELECT
                    r_ca1217.w_gmt_offset as te_d48440 
                FROM
                    db1.warehouse AS r_ca1217 
                INNER JOIN
                    db1.income_band AS r_fdfd0f 
                        ON r_ca1217.w_warehouse_sk > r_fdfd0f.ib_upper_bound 
                WHERE
                    r_fdfd0f.ib_income_band_sk < r_fdfd0f.ib_lower_bound 
                    OR r_fdfd0f.ib_upper_bound > r_ca1217.w_warehouse_sk 
                    AND NOT EXISTS (
                        SELECT
                            r_d2806c.sm_carrier as te_edae07,
                            r_478aa5.cd_demo_sk as te_aaebd3 
                        FROM
                            db1.time_dim AS r_18c72b 
                        LEFT JOIN
                            db1.customer_demographics AS r_478aa5 
                                ON r_18c72b.t_second = r_478aa5.cd_demo_sk,
                            db1.ship_mode AS r_d2806c 
                        WHERE
                            r_478aa5.cd_dep_college_count >= r_d2806c.sm_ship_mode_sk 
                            AND r_478aa5.cd_gender ILIKE r_478aa5.cd_credit_rating 
                            AND r_478aa5.cd_dep_employed_count = 43 
                        ORDER BY
                            1 DESC NULLS FIRST, 2 DESC NULLS FIRST 
                        OFFSET 84
                ) 
                AND r_ca1217.w_country NOT IN (
                    SELECT
                        r_221143.ca_street_number || r_c7874e.sm_contract as te_6fe79f FROM
                            db1.store_returns AS r_ee2691 
                        INNER JOIN
                            (
                                SELECT
                                    * 
                                FROM
                                    db1.customer_address PIVOT(min(ca_city) AS pa_f29908 FOR ca_location_type IN (('IvXU') AS pf_793292,
                                    ('kGk') AS pf_3aa1fa,
                                    ('6sUg') AS pf_d69ddc))
                            ) AS r_221143 
                                ON r_ee2691.sr_customer_sk > r_221143.ca_address_sk,
                            db1.ship_mode AS r_c7874e 
                        INNER JOIN
                            db1.reason AS r_bb8634 
                                ON r_c7874e.sm_carrier LIKE r_bb8634.r_reason_id 
                        WHERE
                            r_ee2691.sr_store_sk < r_c7874e.sm_ship_mode_sk 
                            OR r_221143.ca_state NOT LIKE r_221143.ca_street_number 
                            AND r_221143.ca_street_type LIKE r_221143.ca_suite_number 
                        ORDER BY
                            1 NULLS FIRST) 
                        ORDER BY
                            1 NULLS LAST
                    ) AS r_e5740f, db1.time_dim AS r_60b78c 
                LEFT JOIN
                    db1.reason AS r_49d30d 
                        ON r_60b78c.t_meal_time LIKE r_49d30d.r_reason_id 
                WHERE
                    r_e5740f.te_d48440 > decimal(r_60b78c.t_minute) 
                    AND r_49d30d.r_reason_id ILIKE 'rTUOVbBRG' 
                    AND r_49d30d.r_reason_desc ILIKE r_60b78c.t_time_id 
                    AND r_49d30d.r_reason_sk IS NULL 
                    OR r_60b78c.t_meal_time NOT LIKE r_60b78c.t_time_id 
                ORDER BY
                    1 NULLS LAST, 2 NULLS LAST) AS r_984202 
                        ON r_71f602.s_store_sk = r_984202.te_f5e7b0, db1.household_demographics AS r_1b7f78 
                WHERE
                    r_71f602.s_street_number ILIKE r_1b7f78.hd_buy_potential 
                    OR r_71f602.s_street_name NOT ILIKE '5G6sUg' 
                    AND r_71f602.s_rec_start_date = DATE'2024-10-11' 
                    AND r_71f602.s_tax_percentage > r_71f602.s_gmt_offset 
                    OR r_71f602.s_store_name NOT ILIKE 'z2kG' 
                ORDER BY
                    1 DESC, 2 NULLS FIRST) AS r_f859bd 
                INNER JOIN
                    db1.time_dim AS r_1d2906 
                        ON r_f859bd.te_373295 < bigint(r_1d2906.t_time) 
                WHERE
                    r_36f0c5.ca_address_sk != r_1d2906.t_second 
                    OR r_9d64cc.w_warehouse_id NOT ILIKE '0lezmSRIvX' 
                    AND r_36f0c5.ca_gmt_offset = r_9d64cc.w_gmt_offset 
                    AND r_f859bd.te_373295 != 11 
                ORDER BY
                    1 NULLS LAST, 2 DESC NULLS LAST 
                OFFSET 73;
----------->
SELECT
    stddev(r_11df5c.sr_refunded_cash) as te_efa9f2 
FROM
    db1.store_returns AS r_11df5c 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    try_add(r_d1adb2.sr_cdemo_sk,
    r_d78f78.te_5ee67d) as te_bab926,
    date_sub(r_d78f78.te_5ee67d,
    r_d1adb2.sr_item_sk) as te_f3948d,
    r_d78f78.te_5ee67d as te_169cad 
FROM
    (SELECT
        r_7d515d.web_rec_start_date as te_5ee67d,
        make_timestamp(r_7d515d.web_open_date_sk,
        r_e94c89.sr_customer_sk,
        r_e94c89.sr_ticket_number,
        r_e94c89.sr_reason_sk,
        r_e94c89.sr_addr_sk,
        r_e94c89.web_county) as te_864c76 
    FROM
        db1.customer_demographics AS r_2464a4 
    INNER JOIN
        db1.web_site AS r_7d515d 
            ON r_2464a4.cd_dep_employed_count >= r_7d515d.web_company_id,
        db1.store_returns AS r_e94c89 
    WHERE
        r_7d515d.web_company_id = r_e94c89.sr_return_amt 
        AND r_e94c89.sr_customer_sk != 96 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS FIRST) AS r_d78f78 
RIGHT JOIN
    db1.store_returns AS r_d1adb2 
        ON date_from_unix_date(hash(string(r_d78f78.te_5ee67d))) >= date_from_unix_date(character_length(string(r_d1adb2.sr_reversed_charge))),
    db1.reason AS r_7aae88 
WHERE
    r_d1adb2.sr_return_time_sk = r_7aae88.r_reason_sk 
    OR r_d1adb2.sr_hdemo_sk > 85 
    AND r_d1adb2.sr_store_credit != 35.04838982 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    int(positive(r_9019d1.cc_sq_ft * 35) - r_25e8b0.s_store_sk * 49) as te_9e61d8 
FROM
    db1.call_center AS r_9019d1 
LEFT JOIN
    db1.store AS r_25e8b0 
        ON r_9019d1.cc_closed_date_sk != r_25e8b0.s_floor_space 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_fd9a70.sr_reversed_charge as te_c9a313 
FROM
    db1.store_returns AS r_fd9a70 
WHERE
    r_fd9a70.sr_store_sk IS NULL 
    OR r_fd9a70.sr_return_quantity = 44 
    OR r_fd9a70.sr_return_amt >= r_fd9a70.sr_return_ship_cost 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_f8e489.wp_char_count as te_a45c1e 
FROM
    db1.web_page AS r_f8e489 
WHERE
    r_f8e489.wp_link_count BETWEEN r_f8e489.wp_creation_date_sk AND 38 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_353fbe.d_date_id as te_36924f 
FROM
    db1.date_dim AS r_353fbe 
WHERE
    r_353fbe.d_dom != 80 
    OR r_353fbe.d_last_dom != 71 
    OR r_353fbe.d_day_name <= r_353fbe.d_current_year 
    AND r_353fbe.d_fy_year BETWEEN 63 AND r_353fbe.d_year 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_3c735e.i_rec_end_date as te_3bde9a 
FROM
    db1.item AS r_3c735e 
LEFT JOIN
    db1.item AS r_aed314 
        ON r_3c735e.i_item_id ILIKE r_aed314.i_item_id 
WHERE
    r_aed314.i_category ILIKE 'DCMaLQFrT' 
    AND r_3c735e.i_rec_end_date > DATE'2024-10-11' 
    OR r_aed314.i_item_id NOT LIKE r_3c735e.i_item_id 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    unix_seconds(current_timestamp()) as te_548c27 
FROM
    db1.ship_mode AS r_43d978 
LEFT JOIN
    db1.store AS r_171e0c 
        ON r_43d978.sm_ship_mode_sk < r_171e0c.s_tax_percentage 
WHERE
    r_43d978.sm_contract NOT ILIKE 'B' 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        r_18f199.wp_web_page_id as te_d77236 
    FROM
        db1.web_page AS r_18f199,
        db1.income_band AS r_e4c55b 
    RIGHT JOIN
        db1.household_demographics AS r_199f65 
            ON r_e4c55b.ib_lower_bound >= r_199f65.hd_income_band_sk 
    WHERE
        r_18f199.wp_url ILIKE r_199f65.hd_buy_potential 
        AND r_e4c55b.ib_lower_bound > 19 
        AND r_18f199.wp_web_page_id <= 'Fr' 
        OR r_199f65.hd_income_band_sk < 22 
        OR r_18f199.wp_rec_end_date != r_18f199.wp_rec_start_date 
    ORDER BY
        1 DESC
) 
UNION
(
SELECT
    r_7e018d.c_preferred_cust_flag as te_f2a023 
FROM
    db1.customer AS r_7e018d 
INNER JOIN
    db1.ship_mode AS r_75460a 
        ON r_7e018d.c_customer_sk != r_75460a.sm_ship_mode_sk 
WHERE
    r_7e018d.c_first_shipto_date_sk < 13 
ORDER BY
    1 DESC NULLS LAST
) 
ORDER BY
1 NULLS FIRST;
----------->
SELECT
    r_225d77.ib_lower_bound as te_a84b93 
FROM
    db1.income_band AS r_225d77 
WHERE
    r_225d77.ib_upper_bound <= 31 
    AND r_225d77.ib_lower_bound <= 39 
    AND r_225d77.ib_upper_bound <= 5 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_538181.w_zip as te_b850ee,
    r_c22dc9.te_6be9d7 as te_50c1a3,
    r_538181.w_suite_number as te_5544ff 
FROM
    db1.customer_address AS r_4e2c9f 
INNER JOIN
    (
        SELECT
            r_535180.sr_return_amt as te_6be9d7 
        FROM
            db1.reason AS r_d1ee52 
        INNER JOIN
            db1.store_returns AS r_535180 
                ON r_d1ee52.r_reason_sk <= r_535180.sr_cdemo_sk 
        WHERE
            r_535180.sr_return_ship_cost > 18.43400801 
            OR r_535180.sr_returned_date_sk = 67 
            OR r_d1ee52.r_reason_id < r_d1ee52.r_reason_desc 
        ORDER BY
            1 ASC NULLS LAST
    ) AS r_c22dc9 
        ON r_4e2c9f.ca_gmt_offset != r_c22dc9.te_6be9d7,
    db1.customer_demographics AS r_099448 
INNER JOIN
    db1.warehouse AS r_538181 
        ON r_099448.cd_credit_rating NOT LIKE r_538181.w_suite_number 
WHERE
    r_4e2c9f.ca_gmt_offset > r_538181.w_gmt_offset 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST;
----------->
(
    SELECT
        character_length(r_6e378a.cc_county) as te_a0875d,
        r_6e378a.cc_call_center_id as te_0374d6 
    FROM
        db1.call_center AS r_b97953,
        db1.call_center AS r_6e378a 
    WHERE
        r_b97953.cc_zip <= r_6e378a.cc_state 
        AND r_b97953.cc_call_center_id NOT ILIKE r_6e378a.cc_call_center_id 
        OR r_6e378a.cc_sq_ft < 23 
        OR r_6e378a.cc_call_center_id NOT ILIKE r_b97953.cc_call_center_id 
        OR r_6e378a.cc_call_center_id = 'CMaL' 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS FIRST
) MINUS  (SELECT
    r_8b0e32.r_reason_sk as te_b4fa01, chr(26 - r_8b0e32.r_reason_sk) as te_f91fc6 
FROM
    db1.reason AS r_8b0e32 
RIGHT JOIN
    (SELECT
        r_a16f22.d_date as te_af90af 
    FROM
        db1.date_dim AS r_a16f22 
    WHERE
        r_a16f22.d_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
        OR r_a16f22.d_same_day_ly <= r_a16f22.d_year 
        OR r_a16f22.d_current_week NOT ILIKE 'ntb') AS r_099c28 
        ON date_from_unix_date(r_8b0e32.r_reason_sk) != r_099c28.te_af90af 
WHERE
    r_8b0e32.r_reason_desc ILIKE r_8b0e32.r_reason_id 
    OR EXISTS (SELECT
        r_2d5afd.up_fb4459 as te_e7111a 
    FROM
        (SELECT
            * 
        FROM
            db1.web_site UNPIVOT INCLUDE NULLS ((up_f15c61,
            up_3f54bc,
            up_fb4459,
            up_d282a8,
            up_979207) FOR upn_c4ff13 IN ((web_site_id,
            web_rec_start_date,
            web_tax_percentage,
            web_site_sk,
            web_city) AS UPF_3c205f,
            (web_state,
            web_rec_end_date,
            web_gmt_offset,
            web_mkt_id,
            web_class) AS UPF_45c633))) AS r_2d5afd 
    INNER JOIN
        db1.income_band AS r_e48973 
            ON r_2d5afd.web_company_id <= r_e48973.ib_income_band_sk 
    WHERE
        r_2d5afd.web_suite_number LIKE 'IvXUulstn' 
        OR NOT r_2d5afd.up_d282a8 < 90 
        OR r_2d5afd.web_zip ILIKE r_2d5afd.web_street_type 
        OR r_e48973.ib_income_band_sk != 89 
    ORDER BY
        1 DESC NULLS FIRST) 
    ORDER BY
        1 DESC, 2 DESC 
    LIMIT 67) ORDER BY
    1 DESC, 2 NULLS LAST;
----------->
(
    SELECT
        reverse(chr(pi())) as te_13a947,
        pi() as te_8957e0 
    FROM
        (WITH CTE_a02d4c(te_ae27f5,
        te_98c32b) AS (SELECT
            r_1f823e.wp_rec_start_date as te_ae27f5,
            r_1f823e.wp_rec_end_date as te_98c32b 
        FROM
            db1.reason AS r_4aec61,
            db1.web_page AS r_1f823e 
        WHERE
            r_4aec61.r_reason_desc NOT ILIKE r_1f823e.wp_autogen_flag 
            OR (NOT r_4aec61.r_reason_desc NOT LIKE 'ZgG' 
            AND r_1f823e.wp_image_count = 86) 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC), CTE_168f6e(te_7ffa40, te_6f1aa0) AS (SELECT
            unix_seconds(timestamp_millis(r_a6c4fc.d_moy)) as te_7ffa40, r_a6c4fc.d_following_holiday as te_6f1aa0 
        FROM
            db1.warehouse AS r_732027 
        INNER JOIN
            db1.date_dim AS r_a6c4fc 
                ON r_732027.w_warehouse_sq_ft <= r_a6c4fc.d_same_day_lq,
            db1.reason AS r_3e9379 
        WHERE
            r_732027.w_country NOT LIKE r_3e9379.r_reason_id 
            OR r_a6c4fc.d_weekend LIKE r_a6c4fc.d_current_quarter 
            AND r_a6c4fc.d_fy_week_seq != 20 
            AND r_732027.w_state NOT ILIKE 'g7x2S' 
            AND r_732027.w_county LIKE r_a6c4fc.d_following_holiday 
        ORDER BY
            1 NULLS FIRST, 2 DESC NULLS LAST) SELECT
            r_dded0a.te_870d24 as te_d60b21, add_months(r_f456cf.te_98c32b, 96) as te_a53bc3 
        FROM
            CTE_a02d4c AS r_f456cf,
            (SELECT
                r_5e59bc.te_325c62 as te_870d24 
            FROM
                (SELECT
                    r_ce5a65.s_rec_end_date as te_202f20,
                    date_from_unix_date(r_b58da0.sr_store_sk) as te_325c62 
                FROM
                    db1.customer_address AS r_ba7cd9 
                LEFT JOIN
                    db1.store_returns AS r_b58da0 
                        ON r_ba7cd9.ca_address_sk >= r_b58da0.sr_hdemo_sk,
                    db1.ship_mode AS r_ee5574 
                RIGHT JOIN
                    db1.store AS r_ce5a65 
                        ON r_ee5574.sm_ship_mode_sk = r_ce5a65.s_closed_date_sk 
                WHERE
                    r_b58da0.sr_return_tax < r_ce5a65.s_gmt_offset 
                    OR r_ce5a65.s_gmt_offset > 51.71337842 
                ORDER BY
                    1 DESC NULLS LAST, 2 NULLS FIRST 
                OFFSET 9) AS r_5e59bc WHERE
                r_5e59bc.te_325c62 != DATE'2024-10-11' 
                OR r_5e59bc.te_325c62 < DATE'2024-03-26' 
                OR r_5e59bc.te_202f20 = DATE'2024-03-26'
            ) AS r_dded0a 
        WHERE
            r_f456cf.te_ae27f5 != r_dded0a.te_870d24 
            AND r_f456cf.te_ae27f5 != DATE'2024-10-11' 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC) AS r_e81117 
    WHERE
        r_e81117.te_a53bc3 > r_e81117.te_d60b21 
        AND r_e81117.te_d60b21 <= DATE'2024-03-26' 
        AND r_e81117.te_a53bc3 <= r_e81117.te_d60b21 
        AND r_e81117.te_a53bc3 BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC) 
    UNION
    ALL SELECT
        r_267576.cd_marital_status as te_033ca5,
        r_6c1571.sr_return_amt_inc_tax as te_c0c286 
    FROM
        db1.customer_demographics AS r_267576,
        db1.store_returns AS r_6c1571 
    WHERE
        r_267576.cd_dep_college_count = r_6c1571.sr_return_quantity 
        AND r_6c1571.sr_fee < 86.35602053 
    ORDER BY
        1 DESC, 2 ASC NULLS FIRST 
    LIMIT 36;
----------->
SELECT
    date_diff(r_b02c60.web_rec_start_date,
    r_b02c60.web_rec_start_date) as te_c71690,
    r_b02c60.web_rec_start_date as te_b68c6d,
    r_6c9c88.ca_county as te_a69b0f 
FROM
    db1.customer AS r_015741 
RIGHT JOIN
    db1.customer_address AS r_6c9c88 
        ON r_015741.c_last_review_date_sk < r_6c9c88.ca_address_sk,
    db1.customer AS r_e55cff 
LEFT JOIN
    db1.web_site AS r_b02c60 
        ON r_e55cff.c_salutation NOT ILIKE r_b02c60.web_suite_number 
WHERE
    r_015741.c_current_addr_sk != r_e55cff.c_last_review_date_sk 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3;
----------->
WITH CTE_ce5b54(te_22f43f) AS (SELECT
    r_80b712.c_email_address as te_22f43f 
FROM
    db1.customer AS r_80b712 
LEFT JOIN
    (SELECT
        r_cba429.t_time_id as te_dfd31c 
    FROM
        (SELECT
            r_2d4845.pf_365705 as te_a067fb 
        FROM
            (SELECT
                * 
            FROM
                db1.call_center PIVOT(avg(cc_open_date_sk) AS pa_ccee8e FOR cc_rec_end_date IN ((DATE'2024-03-25') AS pf_b8f708,
                (DATE'2024-10-11') AS pf_e59b4a,
                (DATE'2024-03-25') AS pf_365705,
                (DATE'2024-03-25') AS pf_d6bb3d))) AS r_2d4845 
        WHERE
            r_2d4845.pf_d6bb3d <= 41.36196434D 
        ORDER BY
            1 NULLS FIRST) AS r_46021d 
        INNER JOIN
            db1.time_dim AS r_cba429 
                ON r_46021d.te_a067fb = r_cba429.t_time 
        WHERE
            r_cba429.t_shift ILIKE 'vXUulst' 
            OR r_cba429.t_minute != 34 
        ORDER BY
            1 DESC NULLS LAST) AS r_8202bc 
            ON r_80b712.c_email_address LIKE r_8202bc.te_dfd31c 
    WHERE
        r_80b712.c_last_name NOT ILIKE 'QHZg' 
    ORDER BY
        1 DESC NULLS LAST), CTE_e0a3c3(te_073fd2) AS (SELECT
            r_dd5de8.c_last_review_date_sk as te_073fd2 
        FROM
            db1.customer AS r_dd5de8 
        WHERE
            r_dd5de8.c_last_review_date_sk <= r_dd5de8.c_birth_day 
        ORDER BY
            1 NULLS FIRST) SELECT
            r_97f408.i_class as te_bfdf12, r_97f408.i_rec_end_date as te_c4f278, r_97f408.i_wholesale_cost as te_5203ac 
        FROM
            CTE_ce5b54 AS r_87f5a1 
        INNER JOIN
            CTE_e0a3c3 AS r_56063b 
                ON r_87f5a1.te_22f43f < chr(r_56063b.te_073fd2),
            db1.item AS r_97f408 
        WHERE
            r_56063b.te_073fd2 <= r_97f408.i_manufact_id 
            AND r_97f408.i_brand_id = 78 
            OR r_97f408.i_category_id >= 70 
        ORDER BY
            1 DESC, 2 DESC, 3 
        OFFSET 20;
----------->
SELECT
    r_82786d.w_gmt_offset as te_4de837 
FROM
    db1.warehouse AS r_82786d 
LEFT JOIN
    db1.warehouse AS r_e79eab 
        ON r_82786d.w_warehouse_sk = r_e79eab.w_warehouse_sq_ft 
WHERE
    r_e79eab.w_street_name LIKE 'px' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_80d2b0.t_am_pm as te_beae85 
FROM
    db1.time_dim AS r_80d2b0 
WHERE
    r_80d2b0.t_am_pm NOT LIKE r_80d2b0.t_shift 
    AND r_80d2b0.t_am_pm NOT IN (
        SELECT
            r_a23ec2.w_zip as te_4e2536 
        FROM
            db1.warehouse AS r_a23ec2,
            db1.store_returns AS r_63b84a 
        WHERE
            r_a23ec2.w_gmt_offset != r_63b84a.sr_return_ship_cost 
            AND r_63b84a.sr_reversed_charge > (
                (
                    SELECT
                        decimal(sin(r_5d9e6c.s_number_employees)) as te_002371 
                    FROM
                        db1.store AS r_5d9e6c 
                    LEFT JOIN
                        db1.store AS r_591b39 
                            ON r_5d9e6c.s_rec_end_date = r_591b39.s_rec_end_date,
                        db1.item AS r_04b5dc 
                    WHERE
                        r_591b39.s_rec_end_date = r_04b5dc.i_rec_end_date 
                        OR r_591b39.s_floor_space != 58 
                    ORDER BY
                        1 DESC NULLS LAST 
                    LIMIT 1
            )
        ) ORDER BY
            1) 
            AND r_80d2b0.t_time_sk >= 87 
        ORDER BY
            1 NULLS FIRST 
        OFFSET 21;
----------->
SELECT
    r_777368.d_date as te_597f43,
    r_865148.cd_dep_employed_count as te_ab4f0e 
FROM
    db1.date_dim AS r_777368,
    db1.customer_demographics AS r_865148 
WHERE
    NOT r_777368.d_date_id NOT LIKE r_865148.cd_marital_status 
    OR r_777368.d_date_id ILIKE r_865148.cd_gender 
    OR r_777368.d_qoy > 13 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    now() as te_0dfea7 
FROM
    db1.income_band AS r_039d98 
WHERE
    r_039d98.ib_upper_bound != r_039d98.ib_lower_bound 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_46e4ea.t_second as te_c448d9,
    pi() as te_967bc7 
FROM
    db1.time_dim AS r_46e4ea,
    db1.reason AS r_6c25dc 
WHERE
    r_46e4ea.t_second BETWEEN 19 AND 19 
    OR r_6c25dc.r_reason_id NOT ILIKE r_46e4ea.t_shift 
    OR r_46e4ea.t_meal_time NOT ILIKE 'lpx' 
    OR r_46e4ea.t_time_id NOT LIKE 'FrTU' 
    OR r_46e4ea.t_second <= r_46e4ea.t_time 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS LAST 
OFFSET 90;
----------->
SELECT
    r_254fbc.c_birth_country as te_6be71e,
    r_4318f3.sr_refunded_cash as te_46cc9b 
FROM
    db1.customer AS r_254fbc,
    db1.store_returns AS r_4318f3 
WHERE
    NOT r_254fbc.c_birth_year > r_4318f3.web_county 
    OR r_4318f3.sr_ticket_number = 3 
    AND r_4318f3.sr_returned_date_sk > 25 
    AND r_254fbc.c_customer_id NOT ILIKE 'XX4QH' 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_aa0906.sm_ship_mode_sk as te_4d4432 
FROM
    db1.ship_mode AS r_aa0906 
LEFT JOIN
    db1.customer_address AS r_519e2a 
        ON r_aa0906.sm_type LIKE r_519e2a.ca_county 
WHERE
    r_aa0906.sm_code >= 'IHl' 
    OR r_519e2a.ca_country NOT LIKE 'GC0lezmS' 
    AND r_519e2a.ca_zip ILIKE 'v95G' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_fa8738.cd_credit_rating as te_16653e 
FROM
    db1.customer_demographics AS r_fa8738 
WHERE
    r_fa8738.cd_dep_college_count <= 9 
    OR (
        NOT r_fa8738.cd_dep_college_count >= r_fa8738.cd_dep_employed_count 
        OR r_fa8738.cd_dep_employed_count > 80
    ) 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_8d9e93.wp_web_page_id as te_1b0a04 
FROM
    db1.web_page AS r_8d9e93 
WHERE
    r_8d9e93.wp_image_count = 58 
ORDER BY
    1 NULLS LAST 
OFFSET 86;
----------->
SELECT
    r_6f39f4.ca_zip as te_3b593e,
    add_months(DATE'2024-10-11',
    r_98a088.c_last_review_date_sk) as te_027cee 
FROM
    db1.customer_address AS r_6f39f4,
    db1.customer AS r_98a088 
WHERE
    r_6f39f4.ca_street_name ILIKE r_98a088.c_birth_country 
    OR r_98a088.c_salutation <= 'lBy7' 
    AND r_98a088.c_first_sales_date_sk > 19 
    OR r_6f39f4.ca_address_id LIKE r_6f39f4.ca_street_number 
ORDER BY
    1 ASC, 2 DESC NULLS LAST;
----------->
SELECT
    r_848682.ca_gmt_offset as te_ff0da3 
FROM
    db1.customer_address AS r_848682 
WHERE
    r_848682.ca_location_type NOT ILIKE 'H' 
    OR r_848682.ca_county ILIKE r_848682.ca_country 
ORDER BY
    1 ASC 
OFFSET 37;
----------->
WITH CTE_427048(te_ab6f36, te_058128) AS (SELECT
    mod(r_7e240a.cc_sq_ft,
    70) as te_ab6f36,
    pi() as te_058128 
FROM
    db1.call_center AS r_7e240a,
    db1.call_center AS r_42159f 
RIGHT JOIN
    db1.store_returns AS r_469074 
        ON r_42159f.cc_mkt_id >= r_469074.sr_returned_date_sk 
WHERE
    r_7e240a.cc_call_center_sk > r_42159f.cc_division 
    OR r_7e240a.cc_company BETWEEN 28 AND 28 
    OR r_42159f.cc_street_number ILIKE 'g7x2SW4P' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST) SELECT
    r_b8b397.te_058128 as te_1169d1 
FROM
    CTE_427048 AS r_b8b397 
WHERE
    r_b8b397.te_058128 != 75.0613969D 
    OR NOT EXISTS (
        SELECT
            r_ed823f.d_dom as te_f3f6aa 
        FROM
            db1.date_dim AS r_ed823f 
        WHERE
            r_ed823f.d_dom <= 27 
        ORDER BY
            1 DESC NULLS LAST
    ) 
    AND sin(r_b8b397.te_ab6f36) < r_b8b397.te_058128 
    AND r_b8b397.te_ab6f36 = r_b8b397.te_ab6f36 
ORDER BY
    1 DESC NULLS FIRST 
OFFSET 34;
----------->
(
    SELECT
        r_059cef.t_minute as te_0a0809,
        r_bc1184.cd_marital_status as te_9c581a,
        r_bc1184.cd_purchase_estimate as te_1bfb65 
    FROM
        db1.customer_demographics AS r_bc1184,
        db1.time_dim AS r_059cef 
    WHERE
        r_bc1184.cd_marital_status ILIKE r_059cef.t_shift 
        OR r_bc1184.cd_gender < 'UO' 
        OR NOT r_bc1184.cd_dep_employed_count = r_059cef.t_second 
    ORDER BY
        1 NULLS FIRST, 2 NULLS LAST, 3 DESC NULLS LAST
) MINUS ALL (
    SELECT
        r_fbdfb2.te_579d2e as te_9388ab, r_39090e.cd_gender as te_837df1, r_39090e.cd_dep_employed_count as te_ba4f0e 
    FROM
        (SELECT
            r_81faf8.sr_addr_sk as te_579d2e 
        FROM
            db1.store_returns AS r_81faf8 
        RIGHT JOIN
            db1.time_dim AS r_dcc8a1 
                ON r_81faf8.sr_return_ship_cost > r_dcc8a1.t_minute 
        ORDER BY
            1 NULLS LAST) AS r_fbdfb2, db1.customer_demographics AS r_39090e 
    INNER JOIN
        db1.ship_mode AS r_845d4f 
            ON r_39090e.cd_dep_count != r_845d4f.sm_ship_mode_sk 
    WHERE
        r_fbdfb2.te_579d2e >= r_39090e.cd_purchase_estimate 
        OR r_845d4f.sm_ship_mode_id ILIKE 'G' 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS FIRST) 
    ORDER BY
        1 NULLS LAST, 2 NULLS LAST, 3 ASC NULLS FIRST;
----------->
WITH CTE_a6e1a6(te_4b36de, te_b48905) AS (SELECT
    r_4eda4a.s_closed_date_sk as te_4b36de,
    r_678712.d_date as te_b48905 
FROM
    db1.date_dim AS r_678712,
    db1.store AS r_4eda4a 
WHERE
    r_678712.d_date >= r_4eda4a.s_rec_end_date 
    AND r_4eda4a.s_division_id = 1 
GROUP BY
    2, 1 
ORDER BY
    1 DESC, 2 NULLS LAST) SELECT
    r_df08ea.w_gmt_offset as te_697bd2, unix_timestamp() as te_2f8d7f 
FROM
    CTE_a6e1a6 AS r_023176,
    db1.warehouse AS r_df08ea 
WHERE
    r_023176.te_4b36de != r_df08ea.w_gmt_offset 
    AND r_df08ea.w_warehouse_name LIKE r_df08ea.w_street_type 
    OR r_df08ea.w_street_name NOT ILIKE 'b2Gv7' 
    OR r_df08ea.w_zip ILIKE 'mSRIvX' 
    OR NOT EXISTS (
        SELECT
            r_7e4ebd.s_gmt_offset as te_c8fcd6,
            btrim(r_416971.cd_marital_status) as te_942566,
            r_141d01.up_7d441d as te_29b2c1 
        FROM
            (SELECT
                * 
            FROM
                db1.web_site UNPIVOT INCLUDE NULLS ((up_6f7b04,
                up_a29c89,
                up_cac663,
                up_b5401f,
                up_7d441d) FOR upn_0b45dd IN ((web_state,
                web_class,
                web_company_id,
                web_tax_percentage,
                web_rec_end_date) AS UPF_a60f61,
                (web_site_id,
                web_suite_number,
                web_open_date_sk,
                web_gmt_offset,
                web_rec_start_date) AS UPF_658a92))) AS r_141d01 
        LEFT JOIN
            db1.customer_demographics AS r_416971 
                ON r_141d01.web_close_date_sk > r_416971.cd_dep_college_count,
            db1.store AS r_7e4ebd 
        RIGHT JOIN
            db1.reason AS r_24906a 
                ON r_7e4ebd.s_gmt_offset < r_24906a.r_reason_sk 
        WHERE
            r_416971.cd_dep_count = r_24906a.r_reason_sk 
            OR r_7e4ebd.s_rec_start_date != r_141d01.up_7d441d 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST) 
        ORDER BY
            1, 2;
----------->
SELECT
    r_986537.r_reason_sk as te_89ae82 
FROM
    db1.reason AS r_986537 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_8ad18d(te_cedd44) AS (SELECT
    r_0c10a8.hd_demo_sk as te_cedd44 
FROM
    (SELECT
        * 
    FROM
        db1.household_demographics UNPIVOT ((up_45474f,
        up_e165b3) FOR upn_65457a IN ((hd_income_band_sk,
        hd_buy_potential) AS UPF_df1dfe))) AS r_0c10a8 
WHERE
    r_0c10a8.hd_dep_count >= 78 
    OR r_0c10a8.hd_dep_count = 42 
    OR r_0c10a8.up_45474f = 90 
    AND r_0c10a8.hd_vehicle_count = 88 
ORDER BY
    1 DESC NULLS LAST), CTE_a11822(te_3f2564) AS (SELECT
        hash(r_48d24e.s_tax_percentage, TIMESTAMP'2024-03-26 00:14:47.568') as te_3f2564 
    FROM
        db1.customer AS r_449151 
    LEFT JOIN
        db1.store AS r_48d24e 
            ON r_449151.c_customer_id LIKE r_48d24e.s_store_id) SELECT
            r_777516.t_second as te_df0a51 
    FROM
        db1.time_dim AS r_777516 
    WHERE
        r_777516.t_time_id LIKE r_777516.t_sub_shift 
        OR r_777516.t_meal_time = 'kG' 
        OR r_777516.t_second <= r_777516.t_hour 
    ORDER BY
        1 NULLS LAST;
----------->
SELECT
    r_97e72f.wp_rec_end_date as te_601191,
    51.38865297 / r_e4ba27.d_moy / 3 * r_97e72f.wp_customer_sk as te_78fb5a 
FROM
    db1.date_dim AS r_e4ba27,
    db1.web_page AS r_97e72f 
WHERE
    r_e4ba27.d_last_dom = r_97e72f.wp_web_page_sk 
    OR r_97e72f.wp_rec_end_date = DATE'2024-10-11' 
    AND r_e4ba27.d_week_seq != 36 
    OR (
        NOT r_97e72f.wp_rec_start_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
        AND r_e4ba27.d_current_day ILIKE 'sUg'
    ) 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_261898.hd_buy_potential as te_8948e7,
    r_261898.hd_dep_count as te_017436,
    timestamp_millis(r_261898.hd_vehicle_count) as te_77df3a 
FROM
    db1.income_band AS r_2211bb,
    db1.household_demographics AS r_261898 
WHERE
    r_2211bb.ib_income_band_sk = r_261898.hd_vehicle_count 
    OR r_261898.hd_buy_potential NOT LIKE 'v95G6sUg' 
    OR r_2211bb.ib_upper_bound >= 48 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 NULLS LAST;
----------->
SELECT
    r_041970.wp_autogen_flag as te_1e3fb5,
    r_0d8093.s_market_manager as te_25d5f8 
FROM
    db1.store_returns AS r_dfc90b 
LEFT JOIN
    db1.web_page AS r_041970 
        ON r_dfc90b.sr_return_amt >= r_041970.wp_max_ad_count,
    (SELECT
        * 
    FROM
        (SELECT
            r_205500.i_rec_start_date as te_fe2f86,
            r_205500.i_container as te_bdafb1 
        FROM
            db1.time_dim AS r_db7d7d,
            db1.item AS r_205500 
        INNER JOIN
            db1.time_dim AS r_7f908f 
                ON r_205500.i_current_price != r_7f908f.t_minute 
        WHERE
            r_db7d7d.t_second = r_205500.i_category_id 
            OR r_205500.i_color NOT ILIKE r_db7d7d.t_sub_shift 
            AND r_205500.i_category_id >= 49) PIVOT(max(te_fe2f86) AS pa_f6d14d FOR te_bdafb1 IN (('vXUu') AS pf_cd4214,
        ('Qiizz2kG') AS pf_20cafa,
        ('ZgGI') AS pf_ff5ffa,
        ('ulstntb2Gv') AS pf_4c5640,
        ('zz') AS pf_e2e4a8))) AS r_e35f15 
    RIGHT JOIN
        db1.store AS r_0d8093 
            ON r_e35f15.pf_ff5ffa > r_0d8093.s_rec_start_date 
    WHERE
        r_041970.wp_image_count <= r_0d8093.s_division_id 
    ORDER BY
        1 DESC NULLS LAST, 2;
----------->
SELECT
    r_1076a5.cd_credit_rating as te_530036,
    r_766b6a.cc_rec_end_date as te_2b0e20,
    r_f50be7.i_rec_end_date as te_a4c545 
FROM
    db1.item AS r_f50be7 
RIGHT JOIN
    db1.customer_demographics AS r_1076a5 
        ON r_f50be7.i_item_id ILIKE r_1076a5.cd_credit_rating,
    db1.call_center AS r_766b6a 
WHERE
    r_1076a5.cd_gender NOT LIKE r_766b6a.cc_call_center_id 
GROUP BY
    1, 2, 3 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS FIRST 
LIMIT 74;
----------->
SELECT
    r_2572c0.c_birth_month as te_dc8368 
FROM
    db1.customer AS r_2572c0 
RIGHT JOIN
    db1.date_dim AS r_01ea57 
        ON r_2572c0.c_preferred_cust_flag LIKE r_01ea57.d_date_id 
WHERE
    r_01ea57.d_date_sk >= 42 
    OR (
        NOT r_01ea57.d_fy_quarter_seq > 96 
        OR r_2572c0.c_first_shipto_date_sk != 39 
        AND r_01ea57.d_current_quarter LIKE 'Hlp'
    ) 
ORDER BY
    1 NULLS LAST 
LIMIT 3;
----------->
SELECT
    now() as te_2196f7,
    r_dc2026.web_county as te_9855d3 
FROM
    db1.reason AS r_4b034e 
LEFT JOIN
    db1.store_returns AS r_dc2026 
        ON r_4b034e.r_reason_sk < r_dc2026.sr_addr_sk,
    db1.web_page AS r_9e9b7d 
WHERE
    r_dc2026.sr_customer_sk <= r_9e9b7d.wp_max_ad_count 
ORDER BY
    1 NULLS FIRST, 2 ASC 
LIMIT 81;
----------->
SELECT
    r_bf67ec.cd_marital_status as te_fef9bb 
FROM
    db1.customer_demographics AS r_bf67ec 
WHERE
    r_bf67ec.cd_marital_status NOT LIKE 'Hlp' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_aebd40.cd_purchase_estimate as te_6ee80b,
    r_89a2f6.t_second as te_1fc7aa 
FROM
    db1.web_page AS r_adf1b6 
RIGHT JOIN
    db1.customer_demographics AS r_97d125 
        ON r_adf1b6.wp_image_count = r_97d125.cd_dep_employed_count,
    db1.time_dim AS r_89a2f6 
INNER JOIN
    db1.customer_demographics AS r_aebd40 
        ON r_89a2f6.t_hour <= r_aebd40.cd_dep_count 
WHERE
    r_adf1b6.wp_image_count <= r_aebd40.cd_dep_employed_count 
    AND r_89a2f6.t_time_sk > 24 
    OR r_adf1b6.wp_web_page_sk > r_adf1b6.wp_access_date_sk 
    OR r_89a2f6.t_sub_shift NOT LIKE '0lezm' 
    OR r_97d125.cd_gender NOT ILIKE r_adf1b6.wp_autogen_flag 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST;
----------->
(
    SELECT
        r_3e0ce1.cd_dep_employed_count as te_75174c,
        r_ebd478.wp_web_page_sk + 83 as te_80ad0f,
        r_3e0ce1.cd_purchase_estimate * 68 as te_0c15bf 
    FROM
        db1.income_band AS r_8afec7 
    RIGHT JOIN
        (
            SELECT
                hour(current_timestamp()) as te_ac83e8,
                r_637da2.w_street_type as te_8bd00a 
            FROM
                db1.warehouse AS r_637da2 
            INNER JOIN
                db1.warehouse AS r_b2511e 
                    ON r_637da2.w_gmt_offset != r_b2511e.w_gmt_offset,
                db1.customer AS r_307e01 
            WHERE
                r_637da2.w_county NOT LIKE r_307e01.c_preferred_cust_flag 
                AND r_637da2.w_state NOT ILIKE 'lpx6kg' 
                AND r_637da2.w_street_type LIKE 'tb2Gv7' 
            ORDER BY
                1 ASC NULLS FIRST, 2 DESC NULLS LAST
        ) AS r_767f3a 
            ON r_8afec7.ib_income_band_sk >= r_767f3a.te_ac83e8,
        db1.web_page AS r_ebd478 
    INNER JOIN
        db1.customer_demographics AS r_3e0ce1 
            ON r_ebd478.wp_image_count != r_3e0ce1.cd_dep_count 
    WHERE
        r_ebd478.wp_rec_end_date IS NULL 
        AND r_ebd478.wp_web_page_sk <= 66 
        AND r_3e0ce1.cd_purchase_estimate >= 31 
        AND r_3e0ce1.cd_dep_employed_count < 82 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 ASC NULLS LAST) EXCEPT ALL (SELECT
            r_541b6d.w_warehouse_sq_ft as te_2c4575, 97 as te_46d5cd, 74 as te_a6ba3a 
        FROM
            db1.item AS r_ed59cc 
        RIGHT JOIN
            db1.warehouse AS r_541b6d 
                ON r_ed59cc.i_brand_id BETWEEN r_ed59cc.i_brand_id + 50 AND 0,
            db1.customer_demographics AS r_d6b064 
        WHERE
            r_ed59cc.i_item_id NOT ILIKE r_d6b064.cd_education_status 
            OR r_ed59cc.i_manufact_id != 46 
            AND r_541b6d.w_county < r_d6b064.cd_gender 
        ORDER BY
            1 NULLS FIRST, 2 ASC NULLS FIRST, 3 ASC) 
    ORDER BY
        1 DESC, 2 NULLS LAST, 3 NULLS LAST 
    LIMIT 74;
----------->
SELECT
    r_e804de.te_d344a3 - r_eb5e24.ib_income_band_sk - r_e804de.te_d344a3 as te_c169c1,
    r_e804de.te_bfbbbd as te_90e327 
FROM
    (SELECT
        current_date() as te_b9ca28,
        r_cfa559.s_street_name as te_7fa289 
    FROM
        db1.reason AS r_073174 
    INNER JOIN
        db1.household_demographics AS r_630112 
            ON r_073174.r_reason_id LIKE r_630112.hd_buy_potential,
        db1.store AS r_cfa559 
    INNER JOIN
        db1.customer_demographics AS r_ec9e89 
            ON r_cfa559.s_hours ILIKE 'QFrTUOVb' 
    WHERE
        r_630112.hd_dep_count < r_ec9e89.cd_dep_employed_count 
        AND r_cfa559.s_street_name LIKE 'hv95G6sU' 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_2796ae 
INNER JOIN
    (
        SELECT
            r_ce1efc.te_41c1d2 as te_d344a3,
            current_timestamp() as te_bfbbbd,
            now() as te_24f126 
        FROM
            (SELECT
                r_76e485.w_gmt_offset as te_41c1d2,
                r_6ae46c.c_customer_id as te_ac06fb,
                add_months(try_add(DATE'2024-03-25',
                r_3f492d.t_minute),
                mod(r_4cce25.t_second + 93,
                unix_timestamp())) as te_487885 
            FROM
                db1.time_dim AS r_3f492d 
            INNER JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.time_dim PIVOT(min(t_minute) AS pa_86ae9d FOR t_am_pm IN (('ir98') AS pf_c578de,
                        ('2SW4') AS pf_dbd42e,
                        ('BR') AS pf_0aef8d,
                        ('IvX') AS pf_2c4b9d))
                ) AS r_4cce25 
                    ON r_3f492d.t_minute != r_4cce25.t_hour,
                db1.warehouse AS r_76e485 
            RIGHT JOIN
                db1.customer AS r_6ae46c 
                    ON r_76e485.w_warehouse_sq_ft < r_6ae46c.c_customer_sk 
            WHERE
                r_3f492d.t_am_pm NOT LIKE r_6ae46c.c_customer_id 
                AND r_6ae46c.c_last_name LIKE 'y7IG' 
                OR r_3f492d.t_time_sk <= r_4cce25.t_time 
                OR r_4cce25.t_hour = 52 
            ORDER BY
                1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS LAST) AS r_ce1efc, db1.customer AS r_0a5b69 
            RIGHT JOIN
                db1.call_center AS r_b9127a 
                    ON r_0a5b69.c_customer_id NOT ILIKE r_b9127a.cc_mkt_class 
            WHERE
                r_ce1efc.te_ac06fb NOT LIKE r_b9127a.cc_zip 
                OR r_b9127a.cc_gmt_offset < r_b9127a.cc_tax_percentage 
                OR r_0a5b69.c_salutation <= 'DDXX' 
                OR r_0a5b69.c_current_addr_sk >= r_b9127a.cc_open_date_sk 
                AND r_b9127a.cc_sq_ft != r_b9127a.cc_call_center_sk) AS r_e804de 
                ON r_2796ae.te_b9ca28 < date(r_e804de.te_bfbbbd),
                db1.income_band AS r_eb5e24 
        WHERE
            r_e804de.te_d344a3 != r_eb5e24.ib_upper_bound 
            AND r_e804de.te_bfbbbd != r_e804de.te_24f126 
            OR r_eb5e24.ib_upper_bound < 66 
            OR r_2796ae.te_7fa289 LIKE 'zm' 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC;
----------->
SELECT
    r_415924.pf_34a591 as te_5d7050,
    r_34a160.cc_call_center_id as te_56ff86 
FROM
    db1.call_center AS r_34a160 
LEFT JOIN
    (
        WITH CTE_67ea69(te_7a2577, te_5b5b38, te_5d5084) AS (SELECT
            current_date() as te_7a2577,
            r_dbd174.i_rec_start_date as te_5b5b38,
            r_dbd174.i_item_id as te_5d5084 
        FROM
            db1.item AS r_dbd174,
            db1.customer AS r_32d831 
        LEFT JOIN
            db1.item AS r_1121c1 
                ON r_32d831.c_current_hdemo_sk = r_1121c1.i_manager_id 
        WHERE
            r_dbd174.i_class_id = r_32d831.c_birth_month 
            AND r_32d831.c_salutation != 'k4elBy7' 
            OR NOT NOT EXISTS (SELECT
                r_23022d.c_customer_sk / 72.59420342D * r_23022d.c_customer_sk / r_23022d.c_current_cdemo_sk as te_2450c6,
                make_timestamp(r_23022d.c_first_sales_date_sk,
                r_23022d.c_first_sales_date_sk,
                r_23022d.c_birth_day,
                r_23022d.c_birth_year,
                r_23022d.c_birth_month,
                r_23022d.c_birth_month) as te_aabece 
            FROM
                (SELECT
                    r_aaffd8.d_same_day_ly as te_ec45e3 
                FROM
                    db1.date_dim AS r_aaffd8 
                GROUP BY
                    1 
                ORDER BY
                    1 ASC NULLS LAST 
                LIMIT 86) AS r_73af2f, db1.customer AS r_23022d INNER JOIN
                db1.reason AS r_5197cc 
                    ON r_23022d.c_birth_country NOT ILIKE r_5197cc.r_reason_id 
            WHERE
                r_73af2f.te_ec45e3 < r_23022d.c_first_shipto_date_sk 
                AND r_23022d.c_first_sales_date_sk != 84 
                AND NOT r_23022d.c_customer_id LIKE 'QHZ' 
            ORDER BY
                1 ASC NULLS LAST, 2 ASC NULLS LAST) 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC 
            OFFSET 33), CTE_284809(te_897c88) AS (SELECT
            r_743b25.ib_upper_bound as te_897c88 FROM
                db1.income_band AS r_743b25 
            WHERE
                r_743b25.ib_income_band_sk <= 35 
                AND r_743b25.ib_income_band_sk != 4 
            ORDER BY
                1 NULLS LAST) SELECT
                r_4ec099.i_rec_start_date as te_8c477d, r_06d38c.cd_dep_employed_count - r_039039.te_897c88 + 22 as te_503c58, unix_timestamp() as te_999af0 
            FROM
                CTE_67ea69 AS r_e08c07 
            INNER JOIN
                CTE_284809 AS r_039039 
                    ON r_e08c07.te_5d5084 NOT LIKE chr(r_039039.te_897c88),
                db1.item AS r_4ec099 
            LEFT JOIN
                db1.customer_demographics AS r_06d38c 
                    ON r_4ec099.i_class LIKE r_06d38c.cd_marital_status 
            WHERE
                r_e08c07.te_7a2577 != r_4ec099.i_rec_start_date 
                AND r_4ec099.i_wholesale_cost <= r_4ec099.i_current_price 
            GROUP BY
                2, 1, 3 
            ORDER BY
                1 ASC NULLS FIRST, 2 ASC NULLS FIRST, 3 ASC NULLS LAST
        ) AS r_2dba07 
            ON r_34a160.cc_rec_start_date >= r_2dba07.te_8c477d,
        (SELECT
            * 
        FROM
            db1.item PIVOT(count(i_units) AS pa_9b7490 FOR i_color IN (('tb2') AS pf_34a591,
            ('HZg') AS pf_1213d8,
            ('tb2Gv7') AS pf_4367e9))) AS r_415924 
    WHERE
        r_34a160.cc_open_date_sk <= r_415924.i_class_id 
        AND r_34a160.cc_class < 'g' 
    ORDER BY
        1 ASC, 2 ASC;
----------->
SELECT
    r_d26391.d_first_dom as te_0a2b89 
FROM
    db1.date_dim AS r_d26391 
RIGHT JOIN
    (
        SELECT
            r_73d9d6.c_birth_month as te_52b327 
        FROM
            db1.customer AS r_73d9d6 
        WHERE
            r_73d9d6.c_current_hdemo_sk >= r_73d9d6.c_birth_day 
            AND r_73d9d6.c_customer_sk >= 24 
            AND (
                NOT r_73d9d6.c_first_name NOT LIKE ((SELECT
                    r_e51208.web_name as te_b4c1dc 
                FROM
                    db1.customer AS r_190f03 
                LEFT JOIN
                    db1.web_site AS r_e51208 
                        ON r_190f03.c_current_cdemo_sk <= r_e51208.web_tax_percentage,
                    db1.customer_demographics AS r_02fc99 
                WHERE
                    r_190f03.c_current_hdemo_sk > r_02fc99.cd_dep_employed_count 
                    AND r_190f03.c_current_cdemo_sk <= 57 
                    OR r_e51208.web_city NOT ILIKE 'mSRIvXUu' 
                    OR r_190f03.c_birth_month < 5 
                ORDER BY
                    1 ASC NULLS FIRST 
                LIMIT 1)) 
            OR r_73d9d6.c_first_sales_date_sk BETWEEN 24 AND r_73d9d6.c_current_addr_sk * 50
        ) ORDER BY
            1 DESC NULLS LAST) AS r_a9b476 
                ON r_d26391.d_fy_week_seq <= r_a9b476.te_52b327 
        WHERE
            r_d26391.d_weekend NOT ILIKE 'ezm' 
        ORDER BY
            1 DESC NULLS LAST;
----------->
SELECT
    r_af4817.w_gmt_offset as te_061d59,
    skewness(99.90746622D + r_af4817.w_warehouse_sk) as te_44cace 
FROM
    db1.household_demographics AS r_1f011d,
    db1.warehouse AS r_af4817 
INNER JOIN
    db1.customer_demographics AS r_f1ad4a 
        ON r_af4817.w_county NOT LIKE r_f1ad4a.cd_credit_rating 
WHERE
    r_1f011d.hd_income_band_sk > r_f1ad4a.cd_dep_employed_count 
    OR r_f1ad4a.cd_demo_sk <= 16 
    OR r_af4817.w_gmt_offset < 33.92443229 
    OR r_af4817.w_county <= r_f1ad4a.cd_education_status 
GROUP BY
    r_af4817.w_gmt_offset 
ORDER BY
    1 DESC NULLS LAST, 2 ASC;
----------->
SELECT
    r_4e511d.d_current_year as te_9126ae,
    chr(r_4e511d.d_fy_week_seq - 57) as te_4e8db0 
FROM
    db1.household_demographics AS r_9bca73 
LEFT JOIN
    db1.store_returns AS r_858afe 
        ON r_9bca73.hd_demo_sk <= r_858afe.sr_hdemo_sk,
    db1.household_demographics AS r_132591 
INNER JOIN
    db1.date_dim AS r_4e511d 
        ON r_132591.hd_demo_sk = r_4e511d.d_dow 
WHERE
    r_9bca73.hd_buy_potential LIKE r_4e511d.d_following_holiday 
    AND (
        NOT r_4e511d.d_current_day LIKE r_4e511d.d_weekend 
        AND r_9bca73.hd_vehicle_count < 93
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_645bdf.wp_rec_start_date as te_08c667,
    r_5a9e56.w_gmt_offset * r_5a9e56.w_warehouse_sk as te_1cf95a,
    r_645bdf.wp_rec_end_date as te_be9ded 
FROM
    db1.customer AS r_7a650b 
RIGHT JOIN
    db1.ship_mode AS r_d0d487 
        ON r_7a650b.c_salutation <= r_d0d487.sm_code,
    db1.warehouse AS r_5a9e56 
RIGHT JOIN
    db1.web_page AS r_645bdf 
        ON r_5a9e56.w_warehouse_sq_ft < r_645bdf.wp_creation_date_sk 
WHERE
    r_d0d487.sm_carrier ILIKE r_5a9e56.w_street_number 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 ASC NULLS FIRST;
----------->
SELECT
    r_1700c3.d_quarter_name as te_99a312 
FROM
    db1.call_center AS r_f54d00 
LEFT JOIN
    db1.date_dim AS r_1700c3 
        ON r_f54d00.cc_employees != r_1700c3.d_year 
WHERE
    r_1700c3.d_fy_quarter_seq <= r_f54d00.cc_employees 
    OR r_1700c3.d_current_week ILIKE r_1700c3.d_current_quarter 
    AND NOT r_1700c3.d_day_name ILIKE 'gGIHl' 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 38;
----------->
SELECT
    r_fd1e32.t_hour as te_4c2f2d,
    e() as te_21e77b 
FROM
    db1.time_dim AS r_0e87d0 
INNER JOIN
    db1.time_dim AS r_fd1e32 
        ON r_0e87d0.t_meal_time LIKE r_fd1e32.t_time_id,
    db1.household_demographics AS r_8016d2 
WHERE
    r_fd1e32.t_shift LIKE r_8016d2.hd_buy_potential 
    AND r_fd1e32.t_time_sk <= 22 
    AND r_0e87d0.t_time = 60 
    OR r_fd1e32.t_second >= 84 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST 
OFFSET 55;
----------->
SELECT
    now() as te_60d507,
    try_add(94 * r_cbd08f.i_item_sk,
    r_cbd08f.i_class_id) as te_6d418a 
FROM
    db1.item AS r_cbd08f,
    db1.reason AS r_edfb2b 
WHERE
    r_cbd08f.i_manager_id = r_edfb2b.r_reason_sk 
    OR r_cbd08f.i_item_desc LIKE 'Vb' 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_33dddc.cd_education_status as te_efc853,
    r_da5eaa.i_current_price as te_bf2b9e,
    r_33dddc.cd_marital_status as te_3952b2 
FROM
    db1.reason AS r_ad8ad7 
INNER JOIN
    db1.customer_demographics AS r_33dddc 
        ON r_ad8ad7.r_reason_sk = r_33dddc.cd_demo_sk,
    db1.item AS r_da5eaa 
INNER JOIN
    db1.warehouse AS r_647122 
        ON r_da5eaa.i_item_id LIKE r_647122.w_county 
WHERE
    r_33dddc.cd_marital_status ILIKE r_647122.w_city 
    AND r_da5eaa.i_rec_start_date <= r_da5eaa.i_rec_end_date 
    AND r_da5eaa.i_size ILIKE 'v' 
    AND r_da5eaa.i_manager_id < 82 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_c01f72.ca_gmt_offset as te_bec86a,
    r_c01f72.ca_state as te_822ac3,
    r_1d20f8.r_reason_id as te_2cb155 
FROM
    db1.reason AS r_1d20f8 
LEFT JOIN
    db1.store_returns AS r_93f5ce 
        ON r_1d20f8.r_reason_sk <= r_93f5ce.sr_return_quantity,
    db1.customer_address AS r_c01f72 
WHERE
    r_93f5ce.sr_store_credit <= r_c01f72.ca_gmt_offset 
    AND r_c01f72.ca_county NOT ILIKE r_c01f72.ca_city 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC NULLS FIRST;
----------->
SELECT
    '2SW' as te_8c61dc 
FROM
    db1.household_demographics AS r_bdb669 
ORDER BY
    1 DESC NULLS FIRST 
OFFSET 99;
----------->
SELECT
    r_2c5186.w_suite_number as te_c27260 
FROM
    db1.warehouse AS r_2c5186 
WHERE
    r_2c5186.w_zip NOT LIKE 'B' 
    AND r_2c5186.w_warehouse_sq_ft < 89 
    OR r_2c5186.w_warehouse_sk <= r_2c5186.w_warehouse_sq_ft 
    AND r_2c5186.w_city NOT LIKE 'rTUOVbB' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_88ef50.c_first_shipto_date_sk as te_c2378a 
FROM
    db1.web_page AS r_6641db 
INNER JOIN
    db1.customer AS r_88ef50 
        ON r_6641db.wp_creation_date_sk >= r_88ef50.c_current_addr_sk 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_0a35a3.c_customer_sk as te_f1fdd6,
    r_fac3e2.d_weekend as te_76cbef,
    r_fac3e2.d_current_year as te_734b5e 
FROM
    db1.customer AS r_0a35a3 
RIGHT JOIN
    db1.time_dim AS r_39b52f 
        ON r_0a35a3.c_current_cdemo_sk >= r_39b52f.t_minute,
    db1.date_dim AS r_fac3e2 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.web_page UNPIVOT EXCLUDE NULLS ((up_034388,
            up_058cb5,
            up_61a188,
            up_1a221e) FOR upn_7eb9d3 IN ((wp_rec_start_date,
            wp_url,
            wp_web_page_id,
            wp_char_count) AS UPF_57712b,
            (wp_rec_end_date,
            wp_type,
            wp_autogen_flag,
            wp_image_count) AS UPF_b2f5b0))
    ) AS r_a0529f 
        ON r_fac3e2.d_following_holiday <= r_a0529f.upn_7eb9d3 
WHERE
    r_39b52f.t_am_pm NOT LIKE r_fac3e2.d_current_week 
    AND r_0a35a3.c_last_review_date_sk >= 22 
    OR r_fac3e2.d_date < r_a0529f.up_034388 
    OR r_fac3e2.d_fy_year >= r_a0529f.wp_max_ad_count 
    AND r_fac3e2.d_current_month NOT LIKE '8hv' 
ORDER BY
    1 ASC NULLS LAST, 2 DESC, 3 ASC NULLS LAST;
----------->
SELECT
    mod(r_7b1c8b.s_division_id,
    14) as te_7d249b 
FROM
    db1.store AS r_7b1c8b 
WHERE
    r_7b1c8b.s_hours NOT ILIKE 'vXUul' 
    AND r_7b1c8b.s_division_name ILIKE '4e' 
    OR r_7b1c8b.s_store_sk = (
        (
            SELECT
                r_63f75e.c_customer_sk as te_be5ebe 
            FROM
                (SELECT
                    r_3f7bbd.cc_sq_ft as te_e9837a,
                    r_3f7bbd.cc_rec_end_date as te_99650e 
                FROM
                    db1.call_center AS r_3f7bbd 
                RIGHT JOIN
                    db1.customer AS r_e27467 
                        ON r_3f7bbd.cc_street_type NOT LIKE r_e27467.c_email_address,
                    db1.ship_mode AS r_c89f09 
                WHERE
                    r_e27467.c_salutation LIKE r_c89f09.sm_code 
                    AND r_3f7bbd.cc_tax_percentage != r_3f7bbd.cc_gmt_offset 
                    AND r_3f7bbd.cc_division_name NOT LIKE 'By7IG7' 
                    AND r_3f7bbd.cc_street_number != r_c89f09.sm_code 
                ORDER BY
                    1, 2 DESC NULLS LAST) AS r_4d629a 
            INNER JOIN
                db1.warehouse AS r_826b02 
                    ON r_4d629a.te_e9837a < r_826b02.w_warehouse_sk,
                db1.customer AS r_93aa97 
            LEFT JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.customer PIVOT(min(c_last_name) AS pa_bba8be FOR c_first_sales_date_sk IN ((78) AS pf_640c41,
                        (40) AS pf_69b104,
                        (32) AS pf_1726c1,
                        (96) AS pf_343e96,
                        (19) AS pf_3968cc))
                ) AS r_63f75e 
                    ON r_93aa97.c_current_addr_sk = r_63f75e.c_current_cdemo_sk 
            WHERE
                (
                    NOT r_826b02.w_warehouse_sq_ft <= r_93aa97.c_birth_month 
                    AND r_63f75e.c_login LIKE 'px6kg7x' 
                    OR r_93aa97.c_customer_id LIKE r_63f75e.c_preferred_cust_flag 
                    OR r_4d629a.te_99650e = DATE'2024-03-26' 
                    AND r_826b02.w_warehouse_id ILIKE '4elBy'
                ) 
            ORDER BY
                1 DESC NULLS LAST 
            LIMIT 1)) 
            AND r_7b1c8b.s_tax_percentage > 51.83939107 ORDER BY
                1 ASC NULLS LAST;
----------->
(
    SELECT
        current_date() as te_3be7b7,
        DATE'2024-03-26' as te_685a05 
    FROM
        db1.customer_demographics AS r_85821c 
    INNER JOIN
        db1.customer AS r_f24b90 
            ON r_85821c.cd_credit_rating ILIKE r_f24b90.c_customer_id 
    ORDER BY
        1, 2 DESC NULLS FIRST
) 
UNION
(
SELECT
    r_0eaa70.i_rec_start_date as te_1c330e,
    r_0eaa70.i_rec_start_date as te_3244b1 
FROM
    db1.call_center AS r_961d70,
    (SELECT
        * 
    FROM
        db1.item PIVOT(max(i_color) AS pa_3a7fc6 FOR i_category IN (('G6sUgD') AS pf_907630,
        ('IvXUuls') AS pf_b9f42a,
        ('4elBy7I') AS pf_8ab01c,
        ('RQiizz2') AS pf_609a7a,
        ('tntb2Gv7R') AS pf_cf0df5,
        ('CMaLQFr') AS pf_b5268f))) AS r_0eaa70 
WHERE
    r_961d70.cc_company = r_0eaa70.i_wholesale_cost 
ORDER BY
    1 DESC, 2 NULLS LAST) 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
WITH CTE_0e1c53(te_7dfd2f) AS (SELECT
    r_d8130c.hd_buy_potential as te_7dfd2f 
FROM
    db1.household_demographics AS r_d8130c 
RIGHT JOIN
    db1.income_band AS r_0dce68 
        ON r_d8130c.hd_vehicle_count = r_0dce68.ib_lower_bound 
WHERE
    r_d8130c.hd_demo_sk != ((SELECT
        r_0c85dc.r_reason_sk as te_946578 
    FROM
        db1.household_demographics AS r_70b6e1 
    INNER JOIN
        db1.reason AS r_0c85dc 
            ON r_70b6e1.hd_dep_count = r_0c85dc.r_reason_sk,
        db1.ship_mode AS r_22d107 
    WHERE
        r_70b6e1.hd_demo_sk > r_22d107.sm_ship_mode_sk 
    ORDER BY
        1 DESC NULLS LAST 
    LIMIT 1)) 
AND NOT r_d8130c.hd_income_band_sk <= 92 ORDER BY
    1 DESC 
OFFSET 78), CTE_26256b(te_05ceda) AS (SELECT
    r_9e03ea.w_warehouse_name as te_05ceda FROM
        (SELECT
            r_b4f42d.sm_ship_mode_sk as te_f66f26 
        FROM
            db1.customer_address AS r_1a8a79 
        LEFT JOIN
            db1.ship_mode AS r_b4f42d 
                ON r_1a8a79.ca_country LIKE r_b4f42d.sm_ship_mode_id 
        WHERE
            r_b4f42d.sm_ship_mode_id NOT ILIKE 'Uulstntb2' 
            AND r_1a8a79.ca_street_type <= r_1a8a79.ca_suite_number 
            AND r_b4f42d.sm_ship_mode_sk IN (SELECT
                hash(r_97fdbf.cd_demo_sk,
                false) as te_1414ee 
            FROM
                db1.customer_demographics AS r_97fdbf,
                db1.reason AS r_819532 
            WHERE
                (NOT NOT r_97fdbf.cd_purchase_estimate != r_819532.r_reason_sk 
                AND NOT EXISTS (SELECT
                    r_0256b7.te_54d947 + r_3d3c9a.r_reason_sk as te_e03974,
                    unix_timestamp() as te_92db16 
                FROM
                    db1.reason AS r_3d3c9a 
                LEFT JOIN
                    (SELECT
                        r_a185f0.sr_store_credit as te_54d947,
                        r_a916ec.r_reason_desc as te_369fb1 
                    FROM
                        db1.store_returns AS r_a185f0,
                        db1.reason AS r_a916ec 
                    WHERE
                        r_a185f0.sr_returned_date_sk <= r_a916ec.r_reason_sk 
                    ORDER BY
                        1 DESC NULLS LAST, 2) AS r_0256b7 
                        ON r_3d3c9a.r_reason_desc ILIKE r_0256b7.te_369fb1,
                    db1.reason AS r_b5b7fe 
                WHERE
                    r_3d3c9a.r_reason_sk < r_b5b7fe.r_reason_sk 
                    OR r_b5b7fe.r_reason_desc ILIKE r_b5b7fe.r_reason_id 
                    OR r_b5b7fe.r_reason_sk <= r_3d3c9a.r_reason_sk 
                    AND r_0256b7.te_369fb1 ILIKE 'Gv7RX' 
                    AND r_b5b7fe.r_reason_id ILIKE r_3d3c9a.r_reason_id 
                ORDER BY
                    1 ASC NULLS LAST, 2 DESC NULLS FIRST)) 
            GROUP BY
                1 
            ORDER BY
                1 ASC NULLS FIRST) 
            ORDER BY
                1 ASC 
            LIMIT 23) AS r_12c0d5 LEFT JOIN
                db1.warehouse AS r_9e03ea 
                    ON r_12c0d5.te_f66f26 = r_9e03ea.w_gmt_offset 
            WHERE
                r_12c0d5.te_f66f26 = r_9e03ea.w_warehouse_sq_ft 
            ORDER BY
                1 NULLS LAST 
            OFFSET 80) SELECT
            hash(r_2ce75f.wp_rec_start_date, r_2ce75f.wp_rec_end_date) as te_974d35, make_timestamp(r_2ce75f.wp_creation_date_sk, r_2ce75f.wp_creation_date_sk, r_2ce75f.wp_web_page_sk, r_2ce75f.wp_image_count, r_2ce75f.wp_link_count, r_80ab41.hd_income_band_sk) as te_b8e4fb FROM
                db1.household_demographics AS r_80ab41 
            INNER JOIN
                CTE_0e1c53 AS r_f209b6 
                    ON r_80ab41.hd_buy_potential ILIKE r_f209b6.te_7dfd2f,
                CTE_26256b AS r_e07056 
            LEFT JOIN
                db1.web_page AS r_2ce75f 
                    ON r_e07056.te_05ceda <= r_2ce75f.wp_type 
            WHERE
                r_80ab41.hd_dep_count != r_2ce75f.wp_customer_sk 
                OR r_2ce75f.wp_rec_start_date != r_2ce75f.wp_rec_end_date 
                OR r_2ce75f.wp_char_count = 50 
                OR r_2ce75f.wp_rec_end_date > r_2ce75f.wp_rec_start_date 
                OR NOT r_2ce75f.wp_access_date_sk <= 77 
            ORDER BY
                1 NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_a1380d.ca_gmt_offset as te_ab47e7,
    r_c34257.s_rec_start_date as te_db025a 
FROM
    db1.customer_address AS r_a1380d,
    db1.time_dim AS r_f4d33f 
LEFT JOIN
    db1.store AS r_c34257 
        ON r_c34257.s_rec_start_date BETWEEN DATE'2024-03-25' AND r_c34257.s_rec_end_date 
WHERE
    r_a1380d.ca_address_sk < r_c34257.s_closed_date_sk 
    AND r_a1380d.ca_gmt_offset != r_c34257.s_gmt_offset 
    OR r_c34257.s_store_name ILIKE 'b2Gv7RXp' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_d938ba.s_tax_percentage as te_52998f 
FROM
    db1.store AS r_d938ba 
WHERE
    r_d938ba.s_store_id ILIKE 'lpx6k' 
    AND r_d938ba.s_market_manager NOT ILIKE 'sUgDDXX' 
    OR r_d938ba.s_rec_start_date = r_d938ba.s_rec_end_date 
    OR r_d938ba.s_street_name LIKE 'D' 
ORDER BY
    1 NULLS FIRST 
OFFSET 54;
----------->
SELECT
    r_639736.ca_location_type as te_38940e,
    r_639736.ca_gmt_offset as te_96a133,
    r_639736.ca_gmt_offset as te_8ceb3f 
FROM
    db1.customer_address AS r_639736,
    db1.customer_demographics AS r_d6d9c7 
RIGHT JOIN
    db1.income_band AS r_30cbc5 
        ON r_d6d9c7.cd_demo_sk < r_30cbc5.ib_lower_bound 
WHERE
    r_639736.ca_gmt_offset >= r_d6d9c7.cd_purchase_estimate 
    AND r_30cbc5.ib_upper_bound != 5 
    AND r_639736.ca_county = 'QF' 
    OR r_639736.ca_county ILIKE r_639736.ca_street_name 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST, 3;
----------->
SELECT
    r_1dfe14.t_shift as te_f32cec,
    r_d11a3c.c_customer_id as te_ddc10d 
FROM
    db1.time_dim AS r_1dfe14 
INNER JOIN
    db1.time_dim AS r_dca6f1 
        ON r_1dfe14.t_time = r_dca6f1.t_second,
    db1.customer AS r_d11a3c 
WHERE
    r_dca6f1.t_time_sk >= r_d11a3c.c_birth_day 
    OR r_dca6f1.t_hour = 19 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST 
LIMIT 22;
----------->
SELECT
    r_f1917d.i_rec_start_date as te_b9ea6c,
    r_1299f8.d_date as te_c6543f 
FROM
    db1.date_dim AS r_1299f8 
INNER JOIN
    db1.item AS r_f1917d 
        ON r_1299f8.d_fy_quarter_seq < r_f1917d.i_manufact_id,
    db1.customer_address AS r_e89618 
INNER JOIN
    db1.warehouse AS r_a816e9 
        ON r_e89618.ca_zip ILIKE r_a816e9.w_street_name 
WHERE
    r_1299f8.d_first_dom > r_a816e9.w_warehouse_sq_ft 
    OR r_f1917d.i_wholesale_cost != r_e89618.ca_gmt_offset 
    OR r_f1917d.i_rec_end_date <= r_f1917d.i_rec_start_date 
    OR r_1299f8.d_current_week NOT ILIKE r_e89618.ca_street_type 
    OR r_f1917d.i_rec_end_date IS NULL 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_2c6d1f.te_51b43d as te_e08838,
    r_9e4339.cd_gender as te_602af2,
    r_9e4339.cd_gender as te_a136dd 
FROM
    (SELECT
        * 
    FROM
        db1.income_band PIVOT(max(ib_income_band_sk) AS pa_8948c4 FOR ib_upper_bound IN ((89) AS pf_7f66a8,
        (45) AS pf_52ed96,
        (86) AS pf_0eddcc,
        (33) AS pf_66978e))) AS r_536de7,
    (SELECT
        r_b6d1d1.ca_location_type as te_df21b6,
        r_9e05fd.s_rec_end_date as te_51b43d 
    FROM
        db1.store AS r_5287ba 
    LEFT JOIN
        db1.store AS r_9e05fd 
            ON r_5287ba.s_store_name LIKE r_9e05fd.s_zip,
        db1.ship_mode AS r_1d88d9 
    INNER JOIN
        db1.customer_address AS r_b6d1d1 
            ON r_1d88d9.sm_code LIKE r_b6d1d1.ca_county 
    WHERE
        r_9e05fd.s_store_id ILIKE r_b6d1d1.ca_address_id 
        AND r_9e05fd.s_street_name LIKE 'RXpRQi' 
        OR (
            NOT r_5287ba.s_division_name LIKE 's' 
            AND r_9e05fd.s_company_id >= 44
        ) 
        OR r_9e05fd.s_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    ORDER BY
        1 ASC, 2 DESC NULLS LAST 
    LIMIT 34) AS r_2c6d1f LEFT JOIN
    db1.customer_demographics AS r_9e4339 
        ON r_2c6d1f.te_df21b6 NOT ILIKE r_9e4339.cd_credit_rating 
WHERE
    r_536de7.pf_52ed96 > r_9e4339.cd_purchase_estimate 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC;
----------->
(
    SELECT
        cos(r_3c5436.r_reason_sk) as te_03fc90 
    FROM
        db1.reason AS r_3c5436 
    WHERE
        r_3c5436.r_reason_sk > 85 
        AND (
            NOT r_3c5436.r_reason_desc ILIKE r_3c5436.r_reason_id 
            AND r_3c5436.r_reason_id NOT LIKE r_3c5436.r_reason_desc
        ) 
    ORDER BY
        1 DESC NULLS LAST
) EXCEPT ALL (
    SELECT
        double(hash(r_b93d1c.ca_street_type, TIMESTAMP'2024-03-26 03:37:07.36')) as te_1947c8 
    FROM
        (SELECT
            * 
        FROM
            db1.customer_address PIVOT(max(ca_street_name) AS pa_adc22e FOR ca_address_sk IN ((88) AS pf_4c3cb0,
            (96) AS pf_442c69,
            (60) AS pf_b500b8,
            (32) AS pf_61572a,
            (47) AS pf_631edf))) AS r_b93d1c 
    WHERE
        r_b93d1c.ca_city NOT ILIKE r_b93d1c.pf_61572a 
    ORDER BY
        1 DESC NULLS LAST) 
    ORDER BY
        1 DESC 
    OFFSET 38;
----------->
SELECT
    r_604fcc.i_rec_start_date as te_da7872,
    r_604fcc.i_size as te_b78651 
FROM
    (SELECT
        r_97e37a.sr_store_sk as te_642693 
    FROM
        (SELECT
            * 
        FROM
            db1.store_returns PIVOT(sum(web_county) AS pa_ba7f5f FOR sr_reason_sk IN ((53) AS pf_e7d59c,
            (43) AS pf_a9d155,
            (89) AS pf_5c3467,
            (90) AS pf_7d5406))) AS r_97e37a 
    LEFT JOIN
        (
            SELECT
                r_0004ea.sr_customer_sk as te_1179bf 
            FROM
                db1.store_returns AS r_0004ea 
            WHERE
                r_0004ea.sr_return_amt_inc_tax != 21.58858805 
                AND r_0004ea.sr_net_loss != 17.24996681 
                AND r_0004ea.sr_return_amt_inc_tax < r_0004ea.sr_reversed_charge 
            ORDER BY
                1 ASC NULLS LAST
        ) AS r_e591a2 
            ON r_97e37a.sr_return_quantity >= r_e591a2.te_1179bf 
    WHERE
        r_97e37a.sr_fee > 18.66098392 
        OR r_97e37a.sr_store_sk != 54 
        AND r_97e37a.sr_returned_date_sk > r_97e37a.sr_customer_sk
    ) AS r_af396f 
RIGHT JOIN
    db1.time_dim AS r_f95284 
        ON r_af396f.te_642693 <= r_f95284.t_second,
    db1.income_band AS r_4fa401 
RIGHT JOIN
    db1.item AS r_604fcc 
        ON r_4fa401.ib_upper_bound != r_604fcc.i_manager_id 
WHERE
    r_f95284.t_shift <= r_604fcc.i_color 
    AND r_604fcc.i_units NOT ILIKE 'XpRQi' 
    AND r_604fcc.i_size LIKE r_604fcc.i_class 
    AND r_604fcc.i_rec_end_date > r_604fcc.i_rec_start_date 
    OR r_604fcc.i_wholesale_cost != 96.02593626 
ORDER BY
    1 DESC, 2 DESC;
----------->
SELECT
    93.9228766D - r_9912d8.i_category_id as te_bb7e89 
FROM
    db1.item AS r_9912d8 
WHERE
    r_9912d8.i_formulation LIKE r_9912d8.i_size 
    AND NOT r_9912d8.i_color LIKE 'SRIvXUu' 
    AND r_9912d8.i_rec_end_date IN (
        SELECT
            date_from_unix_date(negative(r_ad75b6.web_company_id)) as te_30f9e2 
        FROM
            db1.web_site AS r_ad75b6 
        LEFT JOIN
            db1.reason AS r_456547 
                ON r_ad75b6.web_country NOT ILIKE r_456547.r_reason_desc 
        WHERE
            r_456547.r_reason_id NOT ILIKE 'IvXUulstnt' 
            OR r_ad75b6.web_open_date_sk = r_ad75b6.web_close_date_sk 
        ORDER BY
            1 DESC 
        OFFSET 17
) 
AND r_9912d8.i_formulation NOT LIKE '2SW' ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_15124d.w_gmt_offset * 18 / r_92724a.c_current_cdemo_sk as te_d72df7 
FROM
    db1.customer AS r_92724a 
RIGHT JOIN
    db1.warehouse AS r_15124d 
        ON r_92724a.c_current_addr_sk < r_15124d.w_gmt_offset EXCEPT ALL (
            SELECT
                decimal(r_6433aa.sr_ticket_number / r_6433aa.sr_hdemo_sk) as te_2985e9 
        FROM
            (SELECT
                r_b9d5a0.wp_autogen_flag as te_2daed1,
                r_b9d5a0.wp_image_count as te_34aeed 
            FROM
                db1.item AS r_8a9eab 
            RIGHT JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.ship_mode UNPIVOT ((up_095319,
                        up_cb5464) FOR upn_09b630 IN ((sm_carrier,
                        sm_ship_mode_sk) AS UPF_885bc1))
                ) AS r_bc0b27 
                    ON r_8a9eab.i_item_desc LIKE r_bc0b27.sm_contract,
                db1.web_page AS r_b9d5a0 
            WHERE
                r_8a9eab.i_manager_id > r_b9d5a0.wp_image_count 
                AND r_8a9eab.i_rec_start_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
            ORDER BY
                1 ASC, 2 DESC NULLS LAST) AS r_8969b3 
            INNER JOIN
                db1.store_returns AS r_6433aa 
                    ON r_8969b3.te_34aeed <= r_6433aa.sr_return_ship_cost,
                db1.customer_demographics AS r_17d466 
            RIGHT JOIN
                db1.customer_demographics AS r_35ed20 
                    ON r_17d466.cd_dep_count > r_35ed20.cd_purchase_estimate 
            WHERE
                r_6433aa.sr_customer_sk != r_35ed20.cd_dep_count 
                AND (
                    NOT r_6433aa.sr_net_loss >= r_6433aa.sr_return_ship_cost 
                    OR r_6433aa.sr_return_amt <= 53.64314114
                ) 
                OR r_17d466.cd_dep_employed_count != 1 
                AND r_6433aa.sr_return_amt = 42.23890679 
            ORDER BY
                1 DESC NULLS FIRST) 
        ORDER BY
            1 DESC NULLS FIRST;
----------->
SELECT
    r_d50281.c_current_cdemo_sk as te_452ba1 
FROM
    db1.customer AS r_d50281 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_c1ecad.i_rec_start_date as te_b3f04d,
    mod(r_632c60.i_brand_id,
    51.10635044D) as te_3c4ea2,
    bigint(r_c1ecad.i_item_sk) as te_e046d1 
FROM
    db1.item AS r_c1ecad,
    db1.item AS r_632c60 
WHERE
    r_c1ecad.i_manager_id <= r_632c60.i_brand_id 
    AND r_c1ecad.i_item_id NOT LIKE r_632c60.i_item_id 
    OR r_c1ecad.i_manager_id = 22 
    OR r_632c60.i_brand_id <= r_c1ecad.i_brand_id 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST, 3 NULLS LAST;
----------->
SELECT
    r_04ac12.ca_address_sk as te_3a5992 
FROM
    db1.customer_address AS r_04ac12 
WHERE
    r_04ac12.ca_city NOT LIKE ((SELECT
        r_9e9594.c_last_name as te_e6a61e 
    FROM
        db1.customer AS r_9e9594,
        db1.store AS r_7112bc 
    WHERE
        r_9e9594.c_current_addr_sk < r_7112bc.s_closed_date_sk 
        AND NOT r_7112bc.s_tax_percentage != r_7112bc.s_gmt_offset 
        OR r_9e9594.c_first_sales_date_sk = 55 
    GROUP BY
        1 
    ORDER BY
        1 DESC NULLS LAST 
    LIMIT 1)) 
OR r_04ac12.ca_address_id < r_04ac12.ca_zip 
AND r_04ac12.ca_country NOT ILIKE r_04ac12.ca_county 
AND r_04ac12.ca_gmt_offset BETWEEN 69.35813938 AND 69.35813938 GROUP BY
    1 
ORDER BY
    1;
----------->
SELECT
    r_7d5b6f.web_open_date_sk as te_6ee761,
    reverse(r_7d5b6f.web_site_id) as te_9f5e85 
FROM
    db1.customer_address AS r_ee91f6 
RIGHT JOIN
    db1.web_site AS r_7d5b6f 
        ON r_ee91f6.ca_gmt_offset < r_7d5b6f.web_gmt_offset,
    db1.store AS r_7d8c79 
WHERE
    r_ee91f6.ca_address_id ILIKE r_7d8c79.s_store_id 
    AND r_ee91f6.ca_location_type ILIKE r_ee91f6.ca_address_id 
    OR r_7d5b6f.web_tax_percentage <= r_7d5b6f.web_gmt_offset 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_ed72f3.sr_return_quantity as te_1da9a5,
    r_3d8f81.sr_item_sk as te_bc302f,
    r_3d8f81.sr_return_quantity as te_42f5aa 
FROM
    db1.reason AS r_0e6894,
    db1.store_returns AS r_3d8f81 
LEFT JOIN
    db1.store_returns AS r_ed72f3 
        ON r_3d8f81.sr_return_quantity >= r_ed72f3.sr_cdemo_sk 
WHERE
    r_0e6894.r_reason_sk != r_ed72f3.sr_hdemo_sk 
    OR r_0e6894.r_reason_desc LIKE r_0e6894.r_reason_id 
    OR NOT r_3d8f81.sr_return_amt_inc_tax != r_ed72f3.sr_return_amt 
    AND r_ed72f3.sr_return_tax >= 49.77045681 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_d8abc1.c_birth_country as te_0da2e8,
    r_5e0d6a.w_county as te_e51a46,
    e() as te_9b3b9e 
FROM
    db1.web_page AS r_75c2bc 
RIGHT JOIN
    db1.customer AS r_d8abc1 
        ON r_75c2bc.wp_type NOT ILIKE r_d8abc1.c_salutation,
    db1.warehouse AS r_5e0d6a 
WHERE
    r_75c2bc.wp_rec_end_date IS NOT NULL 
    OR r_5e0d6a.w_zip LIKE 'zz' 
    AND r_75c2bc.wp_customer_sk = 73 
    AND r_d8abc1.c_customer_sk = r_5e0d6a.w_warehouse_sq_ft 
    OR r_d8abc1.c_customer_id != 'RXpRQ' 
ORDER BY
    1 NULLS LAST, 2 ASC, 3 ASC NULLS LAST;
----------->
SELECT
    r_026b2a.cc_call_center_id as te_a5aaac 
FROM
    db1.call_center AS r_026b2a 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 58;
----------->
SELECT
    r_9804e2.d_current_year as te_454f16,
    reverse(r_9804e2.d_current_quarter) as te_3e279d,
    make_date(r_9804e2.d_fy_week_seq,
    r_9804e2.d_same_day_lq,
    r_8fa69b.i_item_sk) as te_efc1a8 
FROM
    db1.date_dim AS r_9804e2 
LEFT JOIN
    db1.ship_mode AS r_1b769e 
        ON r_9804e2.d_quarter_seq >= r_1b769e.sm_ship_mode_sk,
    db1.item AS r_8fa69b 
WHERE
    r_9804e2.d_current_year NOT ILIKE r_8fa69b.i_item_id 
    OR r_9804e2.d_same_day_ly <= 87 
    OR r_9804e2.d_day_name < r_9804e2.d_quarter_name 
    OR r_8fa69b.i_rec_start_date BETWEEN r_8fa69b.i_rec_end_date AND DATE'2024-03-25' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_695126.i_wholesale_cost * r_695126.i_current_price as te_765f52 
FROM
    db1.item AS r_695126 
INNER JOIN
    db1.date_dim AS r_63383e 
        ON r_695126.i_item_desc LIKE r_63383e.d_date_id 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_e34a0d.te_161b28 as te_92d060 
FROM
    (SELECT
        r_8dd8d6.wp_rec_start_date as te_161b28 
    FROM
        db1.customer AS r_1a9b12 
    LEFT JOIN
        db1.web_page AS r_8dd8d6 
            ON r_1a9b12.c_current_cdemo_sk >= r_8dd8d6.wp_customer_sk 
    WHERE
        r_8dd8d6.wp_web_page_id LIKE 'g7x' 
        AND r_1a9b12.c_birth_day >= 26 
        AND r_8dd8d6.wp_image_count != r_8dd8d6.wp_customer_sk 
    ORDER BY
        1 NULLS LAST) AS r_e34a0d 
WHERE
    r_e34a0d.te_161b28 = DATE'2024-10-11' 
    AND r_e34a0d.te_161b28 < r_e34a0d.te_161b28 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_852dcd.web_mkt_desc as te_bcdb7a,
    character_length(r_852dcd.web_zip) as te_2c830d,
    r_363141.ib_upper_bound as te_4a193c 
FROM
    db1.income_band AS r_363141,
    db1.web_site AS r_852dcd 
WHERE
    r_363141.ib_upper_bound < r_852dcd.web_close_date_sk 
    AND r_852dcd.web_name ILIKE 'hv9' 
    OR r_363141.ib_upper_bound != 75 
    OR r_852dcd.web_market_manager ILIKE 'aLQF' 
    AND NOT r_852dcd.web_suite_number NOT ILIKE 'XX4QH' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 NULLS FIRST;
----------->
SELECT
    r_2e3e45.ca_gmt_offset as te_3ba318 
FROM
    db1.customer_address AS r_2e3e45 
LEFT JOIN
    db1.item AS r_2a9a10 
        ON r_2e3e45.ca_address_sk < r_2a9a10.i_item_sk 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_efc0c8.ib_income_band_sk as te_97e850 
FROM
    (SELECT
        substring(r_4fcce2.sm_code,
        r_4fcce2.sm_ship_mode_sk) as te_845d64,
        r_18cc29.cc_closed_date_sk as te_6a8e09,
        r_18cc29.cc_tax_percentage as te_2527a8 
    FROM
        db1.call_center AS r_18cc29,
        db1.ship_mode AS r_4fcce2 
    WHERE
        r_18cc29.cc_market_manager LIKE r_4fcce2.sm_code 
        OR r_4fcce2.sm_ship_mode_id LIKE 'ir98hv' 
        AND r_18cc29.cc_rec_start_date >= r_18cc29.cc_rec_end_date) AS r_c0c487 
LEFT JOIN
    db1.income_band AS r_efc0c8 
        ON r_c0c487.te_2527a8 > r_efc0c8.ib_lower_bound 
WHERE
    r_efc0c8.ib_upper_bound <= 2 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    chr(r_0ab882.cc_division) as te_5e0e67,
    r_0ab882.cc_tax_percentage as te_125000 
FROM
    db1.income_band AS r_0eda27,
    db1.call_center AS r_0ab882 
RIGHT JOIN
    (
        SELECT
            r_200636.ca_county as te_a99a8f 
        FROM
            db1.date_dim AS r_2e2528 
        INNER JOIN
            db1.customer_address AS r_200636 
                ON r_2e2528.d_week_seq >= r_200636.ca_gmt_offset 
        WHERE
            r_200636.ca_street_name LIKE r_200636.ca_country 
            AND r_2e2528.d_fy_quarter_seq > 80 
        ORDER BY
            1 DESC NULLS LAST
    ) AS r_a775f7 
        ON r_0ab882.cc_name ILIKE r_a775f7.te_a99a8f 
WHERE
    r_0eda27.ib_upper_bound != r_0ab882.cc_open_date_sk 
    OR r_0ab882.cc_gmt_offset >= r_0ab882.cc_tax_percentage 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
WITH CTE_9366b6(te_8363ba) AS (SELECT
    r_ffa321.te_12b791 as te_8363ba 
FROM
    (SELECT
        r_d25689.web_open_date_sk as te_12b791,
        r_d25689.web_mkt_class as te_b4e242,
        chr(r_d25689.web_mkt_id) as te_08fa99 
    FROM
        db1.household_demographics AS r_911fd7 
    LEFT JOIN
        db1.time_dim AS r_29cb46 
            ON r_911fd7.hd_buy_potential LIKE r_29cb46.t_shift,
        db1.web_site AS r_d25689 
    WHERE
        r_29cb46.t_shift NOT ILIKE r_d25689.web_class 
        OR r_29cb46.t_time_id NOT LIKE '5G6s' 
    ORDER BY
        1 ASC, 2 ASC, 3 NULLS FIRST) AS r_ffa321 
WHERE
    r_ffa321.te_b4e242 NOT ILIKE chr(r_ffa321.te_12b791) 
    OR NOT r_ffa321.te_12b791 = r_ffa321.te_12b791 
    OR r_ffa321.te_b4e242 NOT LIKE 'b2Gv7RX' 
    OR r_ffa321.te_08fa99 LIKE r_ffa321.te_08fa99 
GROUP BY
    1 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 88), CTE_6ed5e5(te_e89ebf) AS (SELECT
    string(current_date()) as te_e89ebf FROM
        (SELECT
            r_816a0b.i_brand_id as te_170f91,
            sin(mod(bigint(mod(r_816a0b.i_item_sk,
            34.05947657D)),
            r_816a0b.i_manufact_id / 95)) as te_0fb1fd 
        FROM
            db1.ship_mode AS r_b5dea2 
        LEFT JOIN
            (SELECT
                r_4f1f23.web_rec_start_date as te_731ecc,
                r_4f1f23.web_gmt_offset as te_14d7c3,
                r_4f1f23.web_state as te_e668fb 
            FROM
                db1.web_site AS r_4f1f23,
                db1.time_dim AS r_b0ec71 
            WHERE
                r_4f1f23.web_name < r_b0ec71.t_am_pm 
                OR r_b0ec71.t_time >= r_b0ec71.t_hour 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS FIRST) AS r_ecdec0 
                ON date_from_unix_date(r_b5dea2.sm_ship_mode_sk) >= r_ecdec0.te_731ecc,
            db1.item AS r_816a0b 
        RIGHT JOIN
            db1.warehouse AS r_ed6827 
                ON r_816a0b.i_item_id < r_ed6827.w_county 
        WHERE
            r_ecdec0.te_14d7c3 != r_816a0b.i_current_price 
            OR r_816a0b.i_item_id LIKE 'z2k' 
            AND r_816a0b.i_wholesale_cost = r_ed6827.w_gmt_offset 
            AND r_816a0b.i_item_id ILIKE r_ecdec0.te_e668fb 
        ORDER BY
            1 ASC NULLS LAST, 2 NULLS FIRST) AS r_b41d3a 
        WHERE
            (NOT r_b41d3a.te_0fb1fd > r_b41d3a.te_0fb1fd 
            OR r_b41d3a.te_0fb1fd > 11.75326551D 
            AND r_b41d3a.te_0fb1fd < 52.18425353D) 
        ORDER BY
            1 DESC NULLS LAST 
        OFFSET 81) SELECT
        r_844ab8.te_8363ba as te_18b20f FROM
            CTE_9366b6 AS r_844ab8 
        ORDER BY
            1 ASC NULLS FIRST;
----------->
SELECT
    r_c567c5.ca_location_type as te_7bdcc4,
    r_10c151.sr_net_loss as te_202b72,
    r_f58fea.d_date as te_18f5db 
FROM
    db1.time_dim AS r_9ab8e1 
LEFT JOIN
    db1.customer_address AS r_c567c5 
        ON r_9ab8e1.t_hour = r_c567c5.ca_address_sk,
    db1.date_dim AS r_f58fea 
INNER JOIN
    db1.store_returns AS r_10c151 
        ON r_f58fea.d_same_day_ly <= r_10c151.sr_return_time_sk 
WHERE
    r_c567c5.ca_street_number ILIKE r_f58fea.d_holiday 
    AND r_c567c5.ca_city ILIKE r_9ab8e1.t_sub_shift 
ORDER BY
    1 NULLS FIRST, 2, 3 DESC NULLS FIRST;
----------->
SELECT
    r_156b59.sr_return_amt_inc_tax as te_0c44aa 
FROM
    db1.store_returns AS r_156b59 
ORDER BY
    1 ASC NULLS LAST;
----------->
WITH CTE_8059a3(te_72006b) AS (SELECT
    r_845865.w_county as te_72006b 
FROM
    db1.warehouse AS r_845865 
ORDER BY
    1 DESC NULLS LAST) SELECT
    r_e1a088.te_72006b as te_3931ce 
FROM
    CTE_8059a3 AS r_e1a088 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 53;
----------->
SELECT
    r_0e0329.i_wholesale_cost as te_9fe2a9,
    r_e330c0.web_gmt_offset as te_8ad7f4,
    r_e298d1.web_zip as te_83bdb9 
FROM
    (SELECT
        r_d78c61.cc_call_center_id as te_74fd7c,
        r_d78c61.cc_gmt_offset as te_4fae45,
        r_f435f9.s_rec_end_date as te_8f4ce6 
    FROM
        (WITH CTE_1b4f48(te_54ba4b,
        te_2b09d8) AS (SELECT
            r_24f603.r_reason_desc as te_54ba4b,
            r_0eb0c8.wp_url as te_2b09d8 
        FROM
            db1.web_page AS r_0eb0c8,
            db1.reason AS r_24f603 
        WHERE
            r_0eb0c8.wp_url LIKE r_24f603.r_reason_desc 
        GROUP BY
            1, 2 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST 
        LIMIT 33) SELECT
        r_2bde8a.te_2b09d8 as te_3bf461 FROM
            CTE_1b4f48 AS r_2bde8a 
        WHERE
            NOT r_2bde8a.te_54ba4b NOT ILIKE '2SW4' 
            OR r_2bde8a.te_2b09d8 ILIKE r_2bde8a.te_54ba4b 
            AND r_2bde8a.te_54ba4b NOT LIKE r_2bde8a.te_2b09d8 
        ORDER BY
            1 ASC NULLS LAST) AS r_9cf651, db1.store AS r_f435f9 
    RIGHT JOIN
        db1.call_center AS r_d78c61 
            ON r_f435f9.s_company_id > r_d78c61.cc_gmt_offset 
    WHERE
        r_9cf651.te_3bf461 NOT LIKE r_d78c61.cc_state 
        OR r_f435f9.s_rec_start_date >= DATE'2024-10-11' 
        OR r_f435f9.s_division_name NOT LIKE r_f435f9.s_zip 
    ORDER BY
        1 ASC NULLS FIRST, 2 NULLS LAST, 3) AS r_36b7f4 
    RIGHT JOIN
        (
            SELECT
                * 
            FROM
                db1.web_site UNPIVOT INCLUDE NULLS ((up_304969,
                up_e89ff5,
                up_60592e,
                up_198797,
                up_6e618e) FOR upn_c9efc4 IN ((web_site_id,
                web_rec_end_date,
                web_close_date_sk,
                web_gmt_offset,
                web_market_manager) AS UPF_7cbb6b,
                (web_state,
                web_rec_start_date,
                web_site_sk,
                web_tax_percentage,
                web_street_number) AS UPF_ad34f9))
        ) AS r_e298d1 
            ON r_36b7f4.te_4fae45 != r_e298d1.up_198797,
        db1.web_site AS r_e330c0 
    RIGHT JOIN
        db1.item AS r_0e0329 
            ON r_e330c0.web_open_date_sk != r_0e0329.i_brand_id 
    WHERE
        r_e298d1.web_company_id > r_0e0329.i_class_id 
        OR (
            NOT r_e298d1.web_country LIKE '7IG7ir9' 
            AND r_e330c0.web_zip NOT ILIKE 'hv95G6' 
            OR r_e298d1.web_mkt_id >= r_e298d1.web_open_date_sk 
            AND r_0e0329.i_item_sk > (
                (
                    SELECT
                        r_aa5ebd.c_birth_month as te_59c57f 
                    FROM
                        db1.customer_address AS r_4a935b 
                    RIGHT JOIN
                        (
                            SELECT
                                * 
                            FROM
                                db1.customer UNPIVOT INCLUDE NULLS ((up_9939b3,
                                up_61060c,
                                up_a4cdcc) FOR upn_1c7cc9 IN ((c_first_name,
                                c_customer_id,
                                c_customer_sk) AS UPF_42f2e5,
                                (c_last_name,
                                c_preferred_cust_flag,
                                c_last_review_date_sk) AS UPF_3aef37))
                        ) AS r_aa5ebd 
                            ON r_4a935b.ca_country NOT ILIKE r_aa5ebd.up_9939b3 
                    ORDER BY
                        1 NULLS LAST 
                    LIMIT 1)
            )
        ) ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_a3e19b.sr_store_sk as te_d650c9,
    hour(make_timestamp(r_a3e19b.sr_return_time_sk,
    r_a3e19b.sr_hdemo_sk,
    r_a3e19b.sr_store_sk,
    r_a3e19b.sr_returned_date_sk,
    r_a3e19b.sr_ticket_number,
    r_a3e19b.sr_cdemo_sk)) as te_7c9624,
    r_1bf3ab.cd_credit_rating as te_bc2c25 
FROM
    db1.customer_demographics AS r_1bf3ab 
RIGHT JOIN
    db1.reason AS r_e50542 
        ON r_1bf3ab.cd_dep_count <= r_e50542.r_reason_sk,
    db1.ship_mode AS r_e4d477 
RIGHT JOIN
    db1.store_returns AS r_a3e19b 
        ON r_e4d477.sm_ship_mode_sk > r_a3e19b.sr_store_credit 
WHERE
    r_1bf3ab.cd_dep_college_count != r_e4d477.sm_ship_mode_sk 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST 
LIMIT 23;
----------->
(
    SELECT
        r_4d1253.t_sub_shift as te_a6e7ae,
        DATE'2024-03-26' as te_72af51,
        skewness(r_fc3197.cd_dep_college_count * 30.66724538) as te_23f2cb 
    FROM
        db1.time_dim AS r_4d1253,
        db1.customer_demographics AS r_fc3197 
    WHERE
        r_4d1253.t_meal_time LIKE r_fc3197.cd_credit_rating 
        AND r_4d1253.t_second != 44 
    GROUP BY
        r_4d1253.t_sub_shift 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS FIRST
) INTERSECT  (SELECT
    hex(r_64b51f.hd_dep_count) as te_5045d9, make_date(r_c7b730.sr_item_sk, r_0d43c9.hd_demo_sk, r_0d43c9.hd_vehicle_count) as te_d9ea74, r_c7b730.sr_return_amt as te_9239f3 
FROM
    db1.household_demographics AS r_64b51f 
RIGHT JOIN
    db1.store_returns AS r_c7b730 
        ON r_64b51f.hd_demo_sk > r_c7b730.sr_return_tax,
    db1.warehouse AS r_ab52d0 
INNER JOIN
    db1.household_demographics AS r_0d43c9 
        ON r_ab52d0.w_warehouse_sk != r_0d43c9.hd_demo_sk 
WHERE
    r_64b51f.hd_buy_potential LIKE r_ab52d0.w_zip 
    AND r_c7b730.sr_store_credit = r_c7b730.sr_return_ship_cost 
    OR r_0d43c9.hd_income_band_sk = 95 
    AND r_c7b730.sr_return_time_sk != r_c7b730.sr_ticket_number 
    AND r_ab52d0.w_city LIKE r_ab52d0.w_country 
GROUP BY
    2, 3, 1 
ORDER BY
    1 ASC NULLS LAST, 2 DESC, 3 DESC) 
ORDER BY
1, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_d8d478.web_county as te_e01ee1,
    r_d8d478.sr_refunded_cash as te_08843f,
    r_d8d478.sr_ticket_number as te_ec606e 
FROM
    db1.store_returns AS r_d8d478,
    db1.reason AS r_abe09f 
WHERE
    r_d8d478.sr_return_ship_cost > r_abe09f.r_reason_sk 
    OR r_d8d478.sr_customer_sk >= r_d8d478.sr_return_time_sk 
    OR r_d8d478.sr_return_amt <= r_d8d478.sr_net_loss 
    AND r_d8d478.sr_store_sk BETWEEN 66 AND r_d8d478.sr_fee 
    AND r_d8d478.sr_return_quantity > r_d8d478.sr_reason_sk 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_cca3e9.te_d645f7 as te_0555d2 
FROM
    (SELECT
        r_a28384.hd_demo_sk as te_d645f7,
        hex(r_a28384.hd_dep_count) as te_69cebf 
    FROM
        db1.income_band AS r_807923,
        db1.reason AS r_50fb24 
    INNER JOIN
        db1.household_demographics AS r_a28384 
            ON r_50fb24.r_reason_id NOT ILIKE r_a28384.hd_buy_potential 
    WHERE
        r_807923.ib_upper_bound = r_a28384.hd_income_band_sk 
        OR r_50fb24.r_reason_desc NOT LIKE 'G6sUgD' 
        AND r_807923.ib_lower_bound <= 75 
        OR r_50fb24.r_reason_id NOT LIKE r_50fb24.r_reason_desc 
    ORDER BY
        1 DESC, 2) AS r_cca3e9 
ORDER BY
    1 DESC;
----------->
SELECT
    91.95156957D / r_1350c9.r_reason_sk + 2.38206722D - r_1350c9.r_reason_sk as te_319353 
FROM
    db1.reason AS r_1350c9 
WHERE
    r_1350c9.r_reason_sk != 88 
    AND r_1350c9.r_reason_id ILIKE 'RIv' 
    OR r_1350c9.r_reason_sk >= 93 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_15a41d.hd_buy_potential as te_a880b4 
FROM
    db1.household_demographics AS r_15a41d 
WHERE
    r_15a41d.hd_income_band_sk > 82 
    OR r_15a41d.hd_vehicle_count > 0 
    AND r_15a41d.hd_demo_sk < r_15a41d.hd_income_band_sk 
    OR r_15a41d.hd_buy_potential ILIKE 'ZgGIHlp' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_ee20ed.c_last_review_date_sk as te_96de49,
    r_915aed.s_company_name as te_c30805 
FROM
    db1.store AS r_915aed 
INNER JOIN
    db1.customer_address AS r_917441 
        ON r_915aed.s_gmt_offset <= r_917441.ca_gmt_offset,
    db1.customer AS r_ee20ed 
WHERE
    r_917441.ca_gmt_offset >= r_ee20ed.c_birth_day 
    OR r_915aed.s_city IS NULL 
    OR r_917441.ca_address_sk < 43 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    try_add(DATE'2024-10-11',
    r_687d1d.t_hour) as te_0b5464,
    r_bf4970.sr_reversed_charge as te_55de51,
    unix_timestamp() as te_97974f 
FROM
    db1.store_returns AS r_bf4970,
    db1.income_band AS r_842d2f 
RIGHT JOIN
    db1.time_dim AS r_687d1d 
        ON r_842d2f.ib_lower_bound <= r_687d1d.t_time 
WHERE
    r_bf4970.sr_addr_sk > r_687d1d.t_second 
    AND r_bf4970.sr_return_quantity <= 88 
ORDER BY
    1 NULLS FIRST, 2 NULLS LAST, 3 ASC NULLS FIRST;
----------->
WITH CTE_014b72(te_043df9, te_d20150) AS (SELECT
    r_73ab41.sr_customer_sk as te_043df9,
    r_9367a9.s_rec_end_date as te_d20150 
FROM
    db1.customer_demographics AS r_9fc3cd 
INNER JOIN
    db1.store AS r_9367a9 
        ON r_9fc3cd.cd_education_status LIKE r_9367a9.s_store_id,
    (SELECT
        r_ac67b1.c_birth_year + r_345f9d.wp_access_date_sk as te_93a56f,
        int(e()) as te_c28624 
    FROM
        db1.customer AS r_ac67b1 
    RIGHT JOIN
        db1.customer AS r_ca1234 
            ON r_ac67b1.c_salutation NOT ILIKE r_ca1234.c_birth_country,
        db1.web_page AS r_345f9d 
    WHERE
        r_ca1234.c_email_address ILIKE r_345f9d.wp_url 
        OR r_ac67b1.c_salutation NOT LIKE r_ca1234.c_last_name 
        AND r_345f9d.wp_max_ad_count >= 20 
        AND r_345f9d.wp_autogen_flag LIKE r_ca1234.c_customer_id 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_702916 
LEFT JOIN
    db1.store_returns AS r_73ab41 
        ON r_702916.te_93a56f >= r_73ab41.sr_addr_sk 
WHERE
    r_9fc3cd.cd_demo_sk = r_73ab41.sr_net_loss 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST) (SELECT
        r_48311e.i_wholesale_cost as te_da6bba, r_158217.ca_street_name || r_8da2f0.d_quarter_name as te_9fd15c 
    FROM
        db1.date_dim AS r_8da2f0 
    INNER JOIN
        db1.item AS r_48311e 
            ON r_8da2f0.d_fy_quarter_seq != r_48311e.i_category_id,
        db1.customer_address AS r_158217 
    WHERE
        r_48311e.i_manager_id <= r_158217.ca_address_sk 
        OR r_48311e.i_manufact ILIKE 'By7IG7ir9' 
        AND r_158217.ca_address_sk = 30 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS FIRST 
    LIMIT 5) INTERSECT ALL (SELECT
    97.55928237 as te_a5f6ba, r_d99a84.sm_code as te_1ef86c FROM
        db1.ship_mode AS r_d99a84 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST;
----------->
(
    SELECT
        r_5ed42a.te_76ed65 - r_5c2e49.ca_address_sk as te_7a2bfb 
    FROM
        (SELECT
            hash(r_a6131c.i_item_id,
            r_d97caf.w_city) as te_aeae0f,
            overlay(r_a6131c.i_color PLACING r_c056fb.sr_addr_sk 
        FROM
            r_a6131c.i_brand_id) as te_a7da60,
            cos(r_c056fb.sr_net_loss) as te_76ed65 
        FROM
            db1.customer AS r_171a16 
        LEFT JOIN
            db1.item AS r_a6131c 
                ON r_171a16.c_customer_id ILIKE r_a6131c.i_item_id,
            db1.warehouse AS r_d97caf 
        LEFT JOIN
            db1.store_returns AS r_c056fb 
                ON r_d97caf.w_gmt_offset <= r_c056fb.sr_store_credit 
        WHERE
            r_a6131c.i_item_id <= r_d97caf.w_county 
            AND r_c056fb.sr_returned_date_sk IN (
                SELECT
                    r_40005d.c_current_hdemo_sk as te_bc6760 
                FROM
                    db1.customer AS r_40005d,
                    db1.warehouse AS r_08c456 
                WHERE
                    r_40005d.c_current_cdemo_sk < r_08c456.w_warehouse_sq_ft 
                ORDER BY
                    1 NULLS LAST
            ) 
            OR r_171a16.c_current_hdemo_sk != r_a6131c.i_class_id 
            AND r_d97caf.w_city LIKE '8hv9' 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC NULLS LAST) AS r_5ed42a 
        INNER JOIN
            db1.customer_address AS r_5c2e49 
                ON r_5ed42a.te_aeae0f != r_5c2e49.ca_address_sk 
        WHERE
            r_5c2e49.ca_city ILIKE r_5c2e49.ca_county 
            OR r_5c2e49.ca_suite_number LIKE 'g7x2S' 
            AND r_5c2e49.ca_street_name >= r_5ed42a.te_a7da60 
        ORDER BY
            1 DESC NULLS LAST) 
    UNION
    SELECT
        49.83013365D as te_2ebf6c 
    FROM
        (SELECT
            r_17cfe1.c_first_name as te_169180,
            r_17cfe1.c_login as te_376938 
        FROM
            db1.customer AS r_17cfe1,
            db1.household_demographics AS r_733ee7 
        WHERE
            NOT r_17cfe1.c_first_shipto_date_sk <= r_733ee7.hd_dep_count 
            AND r_17cfe1.c_email_address LIKE 'izz2kGk4' 
            AND r_17cfe1.c_customer_id LIKE 'mS' 
            OR r_733ee7.hd_income_band_sk != r_733ee7.hd_dep_count) AS r_f50234 
    WHERE
        r_f50234.te_169180 LIKE 'zz2kGk4elB' 
        OR r_f50234.te_169180 NOT ILIKE '6' 
    ORDER BY
        1 DESC NULLS FIRST;
----------->
SELECT
    r_e2b948.hd_demo_sk as te_7723f1 
FROM
    db1.household_demographics AS r_e2b948 
RIGHT JOIN
    db1.web_site AS r_924328 
        ON r_e2b948.hd_dep_count <= r_924328.web_mkt_id 
ORDER BY
    1 NULLS LAST 
OFFSET 25;
----------->
SELECT
    unix_timestamp() as te_084477,
    r_637cc0.i_container as te_d6997d,
    r_9a82a7.te_23ccd7 as te_982fc1 
FROM
    db1.item AS r_637cc0 
LEFT JOIN
    db1.store AS r_65a9ab 
        ON r_637cc0.i_product_name ILIKE r_65a9ab.s_manager,
    (SELECT
        r_f94e4a.cc_call_center_id as te_a5ac94,
        current_timestamp() as te_23ccd7 
    FROM
        db1.call_center AS r_f94e4a,
        db1.time_dim AS r_21845a 
    INNER JOIN
        (
            SELECT
                * 
            FROM
                db1.income_band PIVOT(max(ib_upper_bound) AS pa_0e65eb FOR ib_lower_bound IN ((40) AS pf_cb8e94,
                (45) AS pf_27170c,
                (15) AS pf_602cf3,
                (51) AS pf_fc789d))
        ) AS r_4e1a0c 
            ON r_21845a.t_minute != r_4e1a0c.pf_cb8e94 
    WHERE
        r_f94e4a.cc_market_manager < r_21845a.t_shift 
        OR r_f94e4a.cc_company BETWEEN 76 AND r_21845a.t_time - 62
    ) AS r_9a82a7 
WHERE
    r_637cc0.i_item_id NOT ILIKE r_9a82a7.te_a5ac94 
    OR r_637cc0.i_current_price = 86.59927966 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS FIRST, 3 ASC NULLS LAST;
----------->
(
    SELECT
        hash(r_77b9a5.t_time) as te_9faf0f,
        r_9dcc8b.d_current_year as te_bc335f,
        1.04632772 * r_0f05e1.d_dow as te_d15883 
    FROM
        db1.time_dim AS r_77b9a5,
        db1.date_dim AS r_9dcc8b 
    INNER JOIN
        db1.date_dim AS r_0f05e1 
            ON r_9dcc8b.d_weekend LIKE r_0f05e1.d_holiday 
    WHERE
        r_77b9a5.t_second <= r_0f05e1.d_week_seq 
        AND r_9dcc8b.d_year = r_0f05e1.d_last_dom 
        AND r_77b9a5.t_shift NOT ILIKE r_77b9a5.t_time_id 
        OR r_9dcc8b.d_date <= DATE'2024-10-11' 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC, 3 DESC NULLS LAST
) 
UNION
ALL (
SELECT
    r_2f5032.w_warehouse_sq_ft as te_a7b144,
    r_1aab00.d_date_id as te_b21a6e,
    unix_timestamp() + r_ee1278.cc_gmt_offset as te_9370ed 
FROM
    db1.call_center AS r_ee1278,
    db1.date_dim AS r_1aab00 
INNER JOIN
    db1.warehouse AS r_2f5032 
        ON r_1aab00.d_first_dom > r_2f5032.w_gmt_offset 
WHERE
    r_ee1278.cc_suite_number LIKE r_2f5032.w_warehouse_name 
    OR r_1aab00.d_last_dom > r_ee1278.cc_sq_ft 
    OR r_2f5032.w_gmt_offset = r_ee1278.cc_tax_percentage 
    AND (
        NOT r_2f5032.w_street_name NOT LIKE 'C0lezmSRIv' 
        OR r_ee1278.cc_class NOT LIKE 'mSRIv'
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 ASC NULLS FIRST
) 
ORDER BY
1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_52c57c.web_close_date_sk / r_52c57c.web_tax_percentage as te_8d7ec2 
FROM
    db1.web_site AS r_52c57c 
WHERE
    r_52c57c.web_tax_percentage = r_52c57c.web_gmt_offset 
    AND r_52c57c.web_company_id != 82 
    OR r_52c57c.web_rec_end_date <= r_52c57c.web_rec_start_date 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 50;
----------->
SELECT
    r_610868.d_following_holiday as te_33f54a,
    r_610868.d_weekend as te_753d2c 
FROM
    db1.date_dim AS r_610868,
    db1.income_band AS r_1ebecf 
WHERE
    r_610868.d_dow != r_1ebecf.ib_upper_bound 
    AND (
        NOT r_610868.d_date_id ILIKE 'ezmS' 
        AND r_610868.d_moy != 91
    ) 
ORDER BY
    1 ASC NULLS LAST, 2 DESC 
LIMIT 81;
----------->
SELECT
    r_3ba7ea.web_company_id as te_c352bf 
FROM
    db1.web_site AS r_3ba7ea 
WHERE
    r_3ba7ea.web_company_id = 51 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_5b71de.hd_buy_potential as te_ca101a 
FROM
    db1.date_dim AS r_5d5144 
INNER JOIN
    db1.household_demographics AS r_5b71de 
        ON r_5d5144.d_day_name ILIKE r_5b71de.hd_buy_potential 
WHERE
    r_5d5144.d_year >= 90 
    AND EXISTS (
        SELECT
            date_from_unix_date(r_768a24.sm_ship_mode_sk) as te_edaa3c 
        FROM
            db1.ship_mode AS r_768a24 
        WHERE
            r_768a24.sm_ship_mode_id NOT LIKE r_768a24.sm_code 
        ORDER BY
            1 ASC NULLS FIRST
    ) 
    OR r_5d5144.d_current_year NOT ILIKE r_5d5144.d_current_month 
ORDER BY
    1 DESC;
----------->
(
    WITH CTE_3eb72c(te_353441, te_e77ebe, te_71a33a) AS (SELECT
        r_d02af5.wp_rec_start_date as te_353441,
        r_0405ad.c_current_cdemo_sk as te_e77ebe,
        r_dfc6ac.hd_income_band_sk as te_71a33a 
    FROM
        db1.web_page AS r_d02af5 
    INNER JOIN
        db1.customer AS r_0405ad 
            ON r_d02af5.wp_web_page_id NOT LIKE r_0405ad.c_preferred_cust_flag,
        db1.household_demographics AS r_dfc6ac 
    WHERE
        r_0405ad.c_email_address NOT LIKE r_dfc6ac.hd_buy_potential 
        AND r_0405ad.c_current_cdemo_sk != 38 
        OR r_d02af5.wp_rec_start_date = r_d02af5.wp_rec_end_date 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC 
    OFFSET 47) SELECT
    r_62ee12.pf_7a22c8 as te_991a2a FROM
        CTE_3eb72c AS r_8ad627 
    LEFT JOIN
        (
            SELECT
                * 
            FROM
                db1.household_demographics PIVOT(count(hd_dep_count) AS pa_7d2006 FOR hd_buy_potential IN (('mSRIvXU') AS pf_746bf9,
                ('5G6sUgDD') AS pf_7a22c8,
                ('6kg7x2SW4') AS pf_2d95af,
                ('2kGk4el') AS pf_905c75,
                ('7IG7i') AS pf_9258b2,
                ('Gk4elBy7I') AS pf_23b4ae))
        ) AS r_62ee12 
            ON r_8ad627.te_71a33a = r_62ee12.hd_income_band_sk 
    ORDER BY
        1 DESC NULLS LAST) EXCEPT  (SELECT
            99 as te_c9d0da 
        FROM
            db1.time_dim AS r_ac74d6 
        INNER JOIN
            db1.call_center AS r_204614 
                ON r_ac74d6.t_meal_time ILIKE r_204614.cc_street_type,
            db1.date_dim AS r_29ad73 
        WHERE
            r_204614.cc_open_date_sk > r_29ad73.d_same_day_ly 
            OR r_204614.cc_tax_percentage < r_204614.cc_gmt_offset 
            AND r_204614.cc_rec_end_date = DATE'2024-10-11' 
            OR r_204614.cc_county LIKE r_204614.cc_company_name 
        ORDER BY
            1 NULLS FIRST 
        LIMIT 65) ORDER BY
        1 DESC NULLS FIRST;
----------->
SELECT
    r_fe1a6e.i_rec_start_date as te_b5ffc7,
    r_fe1a6e.i_item_id as te_1e80ce,
    make_timestamp(r_fe1a6e.i_category_id,
    r_3c503c.hd_income_band_sk,
    r_fe1a6e.i_category_id,
    r_fe1a6e.i_brand_id,
    r_3c503c.hd_demo_sk,
    0.69550188D) as te_3e04c7 
FROM
    db1.household_demographics AS r_46fd75 
INNER JOIN
    db1.household_demographics AS r_3c503c 
        ON r_46fd75.hd_income_band_sk = r_3c503c.hd_demo_sk,
    (SELECT
        overlay('rTUOVbBRG' PLACING r_5409e5.sr_cdemo_sk 
    FROM
        r_5409e5.sr_store_sk) as te_94832d 
    FROM
        db1.store_returns AS r_5409e5 
    ORDER BY
        1 ASC) AS r_195491 
RIGHT JOIN
    db1.item AS r_fe1a6e 
        ON r_195491.te_94832d LIKE r_fe1a6e.i_size 
WHERE
    r_3c503c.hd_income_band_sk > r_fe1a6e.i_category_id 
    OR r_fe1a6e.i_rec_start_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    OR r_fe1a6e.i_current_price >= r_fe1a6e.i_wholesale_cost 
    AND r_46fd75.hd_income_band_sk != 70 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 ASC;
----------->
SELECT
    r_367598.ca_zip as te_dd2178,
    r_367598.ca_county as te_df1ec7,
    r_935097.wp_rec_start_date as te_6277ff 
FROM
    db1.customer_address AS r_367598 
LEFT JOIN
    (
        SELECT
            mod(r_1d611c.d_first_dom,
            68.36928737) as te_b6f5c5 
        FROM
            db1.reason AS r_213111 
        LEFT JOIN
            db1.date_dim AS r_1d611c 
                ON r_213111.r_reason_sk = r_1d611c.d_moy 
        WHERE
            r_1d611c.d_current_year NOT ILIKE 'vX' 
        ORDER BY
            1 NULLS LAST
    ) AS r_afacfb 
        ON r_367598.ca_gmt_offset >= r_afacfb.te_b6f5c5,
    db1.web_page AS r_935097 
LEFT JOIN
    db1.customer AS r_98f73e 
        ON r_935097.wp_image_count != r_98f73e.c_current_hdemo_sk 
WHERE
    r_367598.ca_suite_number LIKE r_98f73e.c_customer_id 
    AND r_367598.ca_gmt_offset = r_afacfb.te_b6f5c5 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST 
OFFSET 59;
----------->
SELECT
    r_90101c.wp_autogen_flag as te_c01cde 
FROM
    db1.web_page AS r_90101c 
WHERE
    r_90101c.wp_url NOT LIKE r_90101c.wp_type 
    AND r_90101c.wp_char_count > 9 
    OR r_90101c.wp_url LIKE 'Uulstntb2' 
    AND r_90101c.wp_char_count >= 2 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    bigint(r_f102f6.wp_image_count) + mod(r_f102f6.wp_access_date_sk,
    28.26697049D) as te_e1171b 
FROM
    db1.web_page AS r_f102f6 
ORDER BY
    1 DESC;
----------->
SELECT
    r_9fc454.sm_type as te_a807a6 
FROM
    db1.time_dim AS r_92d9e1 
INNER JOIN
    db1.ship_mode AS r_9fc454 
        ON r_92d9e1.t_time_sk > r_9fc454.sm_ship_mode_sk 
WHERE
    r_9fc454.sm_ship_mode_sk <= r_92d9e1.t_hour 
    OR r_9fc454.sm_carrier NOT LIKE 'kGk4elB' 
    OR r_92d9e1.t_time > r_92d9e1.t_minute 
    AND r_92d9e1.t_time_sk = 84 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_92e80a.t_meal_time || r_603cea.w_zip as te_8eda9c,
    to_char(r_92e80a.t_time,
    '999') as te_38c896,
    r_603cea.w_suite_number as te_2ffd11 
FROM
    db1.warehouse AS r_603cea 
RIGHT JOIN
    db1.time_dim AS r_92e80a 
        ON r_603cea.w_street_number NOT LIKE r_92e80a.t_shift,
    (SELECT
        r_8520f5.i_formulation as te_a3bb58,
        r_8520f5.i_wholesale_cost as te_fc8c3f 
    FROM
        (SELECT
            btrim(r_c50737.web_mkt_class) as te_bad6bc 
        FROM
            db1.web_site AS r_c50737 
        WHERE
            r_c50737.web_zip NOT ILIKE 'vXUu' 
            OR r_c50737.web_gmt_offset BETWEEN 1.17268612 AND 1.17268612 
            OR NOT r_c50737.web_company_id BETWEEN 75 AND r_c50737.web_mkt_id 
            OR r_c50737.web_tax_percentage >= r_c50737.web_gmt_offset 
        ORDER BY
            1 DESC NULLS LAST 
        OFFSET 34) AS r_d6ae5b INNER JOIN
        db1.income_band AS r_2a92f7 
            ON r_d6ae5b.te_bad6bc NOT ILIKE chr(r_2a92f7.ib_income_band_sk),
        db1.web_page AS r_f834a6 
    INNER JOIN
        db1.item AS r_8520f5 
            ON r_f834a6.wp_rec_start_date = r_8520f5.i_rec_start_date 
    WHERE
        r_2a92f7.ib_lower_bound < r_f834a6.wp_char_count 
        AND r_f834a6.wp_url LIKE 'Gk4' 
    ORDER BY
        1 ASC NULLS LAST, 2 NULLS LAST) AS r_9cdefe 
    WHERE
        r_603cea.w_state NOT LIKE r_9cdefe.te_a3bb58 
        AND r_92e80a.t_time_id ILIKE r_9cdefe.te_a3bb58 
        OR r_603cea.w_country NOT ILIKE 'X4Q' 
    ORDER BY
        1 DESC, 2, 3 NULLS LAST;
----------->
SELECT
    ascii(r_e3ab33.d_current_quarter) as te_2b412b 
FROM
    db1.date_dim AS r_e3ab33 
LEFT JOIN
    db1.time_dim AS r_38e161 
        ON r_e3ab33.d_month_seq >= r_38e161.t_hour 
WHERE
    r_38e161.t_hour = 90 
    OR NOT r_e3ab33.d_quarter_name ILIKE r_e3ab33.d_date_id 
    OR r_e3ab33.d_week_seq = r_38e161.t_time_sk 
    OR r_38e161.t_hour <= 50 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_b92d28.web_tax_percentage as te_933c4d,
    e() - decimal(r_34a8e9.cd_demo_sk - 80) as te_cdd199 
FROM
    db1.time_dim AS r_4acd1d 
RIGHT JOIN
    db1.reason AS r_42dbfa 
        ON r_4acd1d.t_minute = r_42dbfa.r_reason_sk,
    (SELECT
        * 
    FROM
        db1.customer_demographics PIVOT(min(cd_dep_count) AS pa_e325d3 FOR cd_marital_status IN (('SRIvX') AS pf_bbb4f0,
        ('DDXX') AS pf_c6b16d,
        ('g7x') AS pf_327d72))) AS r_34a8e9 
LEFT JOIN
    db1.web_site AS r_b92d28 
        ON r_34a8e9.cd_purchase_estimate >= r_b92d28.web_open_date_sk 
WHERE
    r_42dbfa.r_reason_id NOT ILIKE r_b92d28.web_mkt_desc 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC 
LIMIT 95;
----------->
SELECT
    timestamp_seconds((r_c4eaec.cd_purchase_estimate - 57) * (50 - r_c4eaec.cd_dep_employed_count)) as te_f1894d 
FROM
    db1.customer_demographics AS r_c4eaec 
ORDER BY
    1 DESC NULLS FIRST 
LIMIT 50;
----------->
SELECT
    positive(pi()) / r_4d565a.t_minute / 3.9312707D as te_b516b4 
FROM
    db1.time_dim AS r_4d565a 
WHERE
    r_4d565a.t_meal_time LIKE r_4d565a.t_time_id 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        r_99e554.d_current_week as te_c43207 
    FROM
        db1.date_dim AS r_99e554 
    WHERE
        NOT r_99e554.d_quarter_name NOT ILIKE 'zmSR' 
        AND r_99e554.d_current_day NOT LIKE 'Qiizz' 
        AND r_99e554.d_fy_week_seq != 80 
    ORDER BY
        1 DESC NULLS FIRST
) 
UNION
(
SELECT
    r_de28f2.cd_education_status as te_dd1aed 
FROM
    db1.customer_demographics AS r_de28f2 
WHERE
    r_de28f2.cd_dep_count = 41 
ORDER BY
    1 ASC NULLS LAST
) 
ORDER BY
1 NULLS FIRST;
----------->
SELECT
    r_868a7f.web_class as te_d3aebb,
    r_868a7f.web_company_id as te_93ea59 
FROM
    db1.web_site AS r_868a7f,
    db1.web_page AS r_bf2bd3 
WHERE
    r_868a7f.web_market_manager NOT ILIKE r_bf2bd3.wp_type 
    AND r_868a7f.web_suite_number LIKE 'e' 
    OR r_868a7f.web_gmt_offset = r_868a7f.web_tax_percentage 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_a5fc1b.cd_purchase_estimate as te_002bfa 
FROM
    db1.customer_demographics AS r_a5fc1b 
WHERE
    r_a5fc1b.cd_dep_count = r_a5fc1b.cd_demo_sk 
    OR r_a5fc1b.cd_gender ILIKE 'vXUu' 
    OR r_a5fc1b.cd_dep_college_count BETWEEN 60 AND r_a5fc1b.cd_dep_employed_count 
    AND r_a5fc1b.cd_dep_employed_count >= 38 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_91b4fc.wp_url as te_c03cea,
    r_91b4fc.wp_autogen_flag as te_72e16a 
FROM
    db1.customer AS r_5a0eb1 
INNER JOIN
    db1.web_page AS r_91b4fc 
        ON r_5a0eb1.c_current_hdemo_sk != r_91b4fc.wp_link_count,
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(count(sm_ship_mode_sk) AS pa_83823d FOR sm_code IN (('IHl') AS pf_88feca,
        ('X4QHZgGIHl') AS pf_e12fa7,
        ('7RXp') AS pf_d949cf,
        ('vXUulstn') AS pf_2168a2,
        ('VbBRGC0lez') AS pf_e856d7,
        ('ZgG') AS pf_ffb647))) AS r_85a61d 
WHERE
    r_5a0eb1.c_last_name ILIKE r_85a61d.sm_ship_mode_id 
    OR r_91b4fc.wp_web_page_sk != 19 
    OR r_91b4fc.wp_web_page_id ILIKE r_91b4fc.wp_autogen_flag 
ORDER BY
    1 DESC NULLS FIRST, 2 
OFFSET 37;
----------->
SELECT
    r_deb179.w_warehouse_sq_ft as te_2b09c9 
FROM
    db1.warehouse AS r_deb179 
INNER JOIN
    db1.web_site AS r_359bd7 
        ON r_deb179.w_warehouse_name NOT LIKE r_359bd7.web_city 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 12;
----------->
SELECT
    r_6e9e86.web_company_id as te_4052a0 
FROM
    db1.web_site AS r_6e9e86 
WHERE
    r_6e9e86.web_city ILIKE r_6e9e86.web_company_name 
    AND r_6e9e86.web_open_date_sk IN (
        SELECT
            r_01846f.i_item_sk as te_e5d6bc 
        FROM
            db1.item AS r_01846f 
        WHERE
            r_01846f.i_current_price IS NOT NULL 
        ORDER BY
            1 DESC NULLS LAST
    ) 
    OR r_6e9e86.web_company_id < 73 
    AND r_6e9e86.web_name LIKE '7ir98' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    chr(r_121d16.s_tax_percentage) as te_2b934b 
FROM
    db1.store AS r_121d16 
ORDER BY
    1 
OFFSET 84;
----------->
SELECT
    r_e88658.w_city as te_a3e6a4 
FROM
    db1.warehouse AS r_e88658 
WHERE
    r_e88658.w_warehouse_sk = r_e88658.w_warehouse_sq_ft 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_39cc64.web_open_date_sk as te_40b6a2 
FROM
    db1.web_site AS r_39cc64 
WHERE
    r_39cc64.web_suite_number ILIKE '0lezmS' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    sin(r_a3370b.d_quarter_seq) as te_eab268 
FROM
    db1.customer_address AS r_483682 
LEFT JOIN
    db1.date_dim AS r_a3370b 
        ON r_483682.ca_zip NOT ILIKE r_a3370b.d_following_holiday 
WHERE
    r_a3370b.d_week_seq < 35 
    OR r_a3370b.d_weekend NOT LIKE 'DDXX' 
    OR r_a3370b.d_last_dom BETWEEN 42 AND r_a3370b.d_week_seq 
    OR r_a3370b.d_moy <= 6 
ORDER BY
    1 DESC 
OFFSET 11;
----------->
WITH CTE_98abd6(te_96183e) AS (SELECT
    current_date() as te_96183e 
FROM
    db1.warehouse AS r_b8cf5b 
ORDER BY
    1 ASC 
OFFSET 7) SELECT
r_a64886.cd_marital_status as te_0143f0, r_a64886.cd_credit_rating as te_5b8e8b, decimal(63 + r_ac3ee0.s_company_id) as te_42e5bf FROM
    CTE_98abd6 AS r_cbf1c3 
RIGHT JOIN
    db1.customer_demographics AS r_a64886 
        ON chr(day(r_cbf1c3.te_96183e)) > reverse(r_a64886.cd_education_status),
    db1.store AS r_ac3ee0 
LEFT JOIN
    db1.ship_mode AS r_f02159 
        ON r_ac3ee0.s_division_id > r_f02159.sm_ship_mode_sk 
WHERE
    r_cbf1c3.te_96183e = r_ac3ee0.s_rec_start_date 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS LAST 
OFFSET 47;
----------->
SELECT
    r_c2a16b.c_customer_id as te_51f3c1,
    r_9a290c.sr_return_ship_cost - r_c2a16b.c_first_sales_date_sk as te_06fcd9,
    r_0fe66e.cc_rec_end_date as te_318741 
FROM
    db1.call_center AS r_0fe66e,
    db1.customer AS r_c2a16b 
RIGHT JOIN
    db1.store_returns AS r_9a290c 
        ON r_c2a16b.c_first_shipto_date_sk >= r_9a290c.sr_item_sk 
WHERE
    r_0fe66e.cc_call_center_id NOT LIKE r_c2a16b.c_customer_id 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 ASC NULLS LAST 
OFFSET 93;
----------->
SELECT
    r_f2cfaf.wp_web_page_id as te_2dbcf8,
    r_f2cfaf.wp_char_count / 26.18011611 as te_8d601f 
FROM
    db1.web_page AS r_f2cfaf INTERSECT ALL (SELECT
        r_16b124.ca_location_type as te_325f45,
        r_060220.sr_refunded_cash as te_1464e1 
    FROM
        db1.store_returns AS r_060220,
        (SELECT
            * 
        FROM
            db1.customer_address PIVOT(count(ca_street_number) AS pa_529676 FOR ca_street_type IN (('T') AS pf_7d9975,
            ('RX') AS pf_81a53f,
            ('UOV') AS pf_3175db,
            ('a') AS pf_e5a738,
            ('px6') AS pf_147ae7,
            ('x6') AS pf_184543))) AS r_16b124 
    WHERE
        r_060220.sr_customer_sk < r_16b124.ca_address_sk 
        OR r_060220.sr_store_sk <= 89 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC 
    OFFSET 85) ORDER BY
        1 NULLS LAST, 2 DESC;
----------->
SELECT
    r_caeeca.web_rec_end_date as te_b18b71,
    chr(r_d510ea.t_hour) as te_7947ca,
    r_caeeca.web_mkt_id as te_109ae9 
FROM
    db1.web_site AS r_caeeca 
RIGHT JOIN
    db1.web_page AS r_ff6f0c 
        ON r_caeeca.web_gmt_offset >= r_ff6f0c.wp_max_ad_count,
    db1.store AS r_3beb5f 
RIGHT JOIN
    db1.time_dim AS r_d510ea 
        ON r_3beb5f.s_closed_date_sk < r_d510ea.t_time 
WHERE
    r_ff6f0c.wp_char_count = r_3beb5f.s_number_employees 
    OR r_caeeca.web_rec_start_date = r_ff6f0c.wp_rec_end_date 
    AND NOT r_caeeca.web_class LIKE 'u' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS FIRST;
----------->
SELECT
    chr(52.7389794D - r_d8d039.sm_ship_mode_sk) as te_e3e778 
FROM
    db1.ship_mode AS r_d8d039 
LEFT JOIN
    db1.customer AS r_8e42a4 
        ON r_d8d039.sm_type ILIKE r_8e42a4.c_email_address 
WHERE
    r_8e42a4.c_customer_id LIKE r_8e42a4.c_preferred_cust_flag 
ORDER BY
    1 DESC;
----------->
(
    SELECT
        33 + r_f10c29.sr_customer_sk as te_de5524 
    FROM
        db1.store_returns AS r_f10c29 
    INNER JOIN
        db1.customer AS r_b31af9 
            ON r_f10c29.sr_customer_sk >= r_b31af9.c_first_sales_date_sk 
    WHERE
        r_b31af9.c_first_sales_date_sk BETWEEN 91 AND r_f10c29.sr_fee + 80 
    ORDER BY
        1 DESC NULLS FIRST
) EXCEPT ALL (
    SELECT
        r_0ca8b9.pf_2fdf94 as te_d66e9c 
    FROM
        db1.household_demographics AS r_93d1fa 
    RIGHT JOIN
        (
            SELECT
                * 
            FROM
                db1.reason PIVOT(count(r_reason_desc) AS pa_ced073 FOR r_reason_id IN (('z2kGk4elBy') AS pf_2fdf94,
                ('G') AS pf_0c7bd1,
                ('e') AS pf_a6ab61))
        ) AS r_0ca8b9 
            ON r_93d1fa.hd_vehicle_count <= r_0ca8b9.r_reason_sk 
    WHERE
        r_0ca8b9.pf_2fdf94 = 36 
        AND r_0ca8b9.pf_2fdf94 > 36 
        AND r_93d1fa.hd_demo_sk < 34 
        AND r_93d1fa.hd_vehicle_count != r_93d1fa.hd_income_band_sk 
    ORDER BY
        1 DESC) 
    ORDER BY
        1 ASC;
----------->
SELECT
    r_0a26ea.wp_web_page_id || r_0a26ea.wp_autogen_flag as te_67de03,
    r_0a26ea.wp_web_page_id as te_831d3e 
FROM
    db1.customer AS r_8e39ef,
    db1.web_page AS r_0a26ea 
WHERE
    r_8e39ef.c_preferred_cust_flag LIKE r_0a26ea.wp_web_page_id 
    OR r_0a26ea.wp_customer_sk = 87 
    OR r_0a26ea.wp_web_page_sk > 50 
ORDER BY
    1 NULLS FIRST, 2;
----------->
SELECT
    r_86af0b.wp_url as te_efcdb4,
    r_86af0b.wp_rec_end_date as te_7a2673,
    hex(pi()) as te_7a9dbc 
FROM
    db1.income_band AS r_c397f9,
    db1.web_page AS r_86af0b 
WHERE
    r_c397f9.ib_income_band_sk >= r_86af0b.wp_image_count 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST, 3 DESC;
----------->
SELECT
    r_e1d0cc.wp_rec_start_date as te_956356,
    r_e1d0cc.wp_autogen_flag as te_9cc9b9,
    r_e1d0cc.wp_url as te_90a6c7 
FROM
    db1.web_page AS r_e1d0cc,
    db1.household_demographics AS r_e818ee 
WHERE
    NOT r_e1d0cc.wp_char_count < r_e818ee.hd_demo_sk 
    AND r_e818ee.hd_buy_potential NOT LIKE 'TUO' 
ORDER BY
    1 DESC, 2 DESC NULLS FIRST, 3 NULLS LAST 
OFFSET 97;
----------->
SELECT
    r_070638.cc_rec_end_date as te_faf28c,
    r_070638.cc_call_center_sk as te_8af5a5 
FROM
    db1.reason AS r_da885d,
    db1.store_returns AS r_620330 
RIGHT JOIN
    db1.call_center AS r_070638 
        ON r_620330.sr_fee < r_070638.cc_mkt_id 
WHERE
    r_da885d.r_reason_sk != r_070638.cc_mkt_id 
ORDER BY
    1, 2 
OFFSET 12;
----------->
SELECT
    r_53851e.hd_income_band_sk as te_e0170a,
    r_fc67b1.web_state as te_8d19b3,
    r_fc67b1.web_site_id as te_987393 
FROM
    db1.household_demographics AS r_53851e 
RIGHT JOIN
    db1.web_site AS r_fc67b1 
        ON r_53851e.hd_buy_potential NOT ILIKE r_fc67b1.web_county,
    db1.store AS r_08a847 
WHERE
    r_fc67b1.web_rec_end_date > r_08a847.s_rec_start_date 
    AND r_53851e.hd_income_band_sk >= r_08a847.s_store_sk 
    OR r_08a847.s_floor_space != 48 
ORDER BY
    1, 2 NULLS FIRST, 3 ASC NULLS LAST;
----------->
WITH CTE_7fe32d(te_77fa7a, te_f73edf, te_f59aa1) AS (SELECT
    r_0c7ba5.i_item_id as te_77fa7a,
    r_1c7462.te_0644cf as te_f73edf,
    hash(58.9653486D,
    r_0c7ba5.i_rec_start_date) as te_f59aa1 
FROM
    (SELECT
        r_387965.s_gmt_offset / positive(positive(r_5dee63.w_warehouse_sq_ft * 78)) / bigint(r_387965.s_store_sk) as te_7eaaea,
        now() as te_cec8c6,
        r_387965.s_rec_end_date as te_0644cf 
    FROM
        db1.customer_address AS r_20d7e1 
    RIGHT JOIN
        db1.warehouse AS r_5dee63 
            ON r_20d7e1.ca_address_sk < r_5dee63.w_gmt_offset,
        db1.store AS r_387965 
    WHERE
        r_5dee63.w_zip NOT LIKE r_387965.s_company_name 
        AND r_20d7e1.ca_suite_number LIKE 'vXU' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS LAST) AS r_1c7462 
LEFT JOIN
    db1.reason AS r_ca6ddc 
        ON r_1c7462.te_0644cf <= date_from_unix_date(r_ca6ddc.r_reason_sk),
    db1.item AS r_0c7ba5 
WHERE
    r_ca6ddc.r_reason_desc ILIKE r_0c7ba5.i_color 
    AND r_0c7ba5.i_class_id != 29 
    OR r_0c7ba5.i_units LIKE 'UgDDXX4QHZ' 
    AND r_1c7462.te_7eaaea != 69.98148883 
    OR r_0c7ba5.i_manufact NOT LIKE r_0c7ba5.i_size 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST) SELECT
        mod(r_6893db.te_f59aa1, 36) as te_dc7eea 
    FROM
        CTE_7fe32d AS r_6893db 
    ORDER BY
        1 ASC NULLS FIRST;
----------->
SELECT
    r_8f3938.web_mkt_id / 47.33850087D as te_9ec031,
    r_e61e04.cc_call_center_id as te_e5b070 
FROM
    db1.store AS r_86db09 
RIGHT JOIN
    db1.web_site AS r_8f3938 
        ON r_86db09.s_company_name NOT ILIKE r_8f3938.web_country,
    db1.call_center AS r_e61e04 
WHERE
    r_8f3938.web_rec_end_date != r_e61e04.cc_rec_end_date 
    OR r_8f3938.web_rec_start_date != DATE'2024-10-11' 
    AND r_e61e04.cc_division > r_e61e04.cc_company 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_060f8a.d_dow / 56.33179203 as te_54bb41 
FROM
    db1.date_dim AS r_060f8a 
WHERE
    r_060f8a.d_fy_quarter_seq != 56 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_004f78.sm_ship_mode_id as te_637bb5,
    r_22fc32.t_sub_shift as te_086264,
    r_22fc32.t_hour as te_aae7ed 
FROM
    db1.time_dim AS r_22fc32,
    db1.income_band AS r_45269e 
RIGHT JOIN
    db1.ship_mode AS r_004f78 
        ON r_45269e.ib_lower_bound = r_004f78.sm_ship_mode_sk 
WHERE
    r_22fc32.t_time_sk != r_45269e.ib_income_band_sk 
ORDER BY
    1 DESC, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_e0119c.i_brand_id as te_0df383,
    to_char(r_e0119c.i_wholesale_cost,
    '000D00') as te_7a1c5d,
    r_d7e8ef.up_a609fd as te_080a44 
FROM
    db1.item AS r_e0119c 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.ship_mode UNPIVOT ((up_a609fd,
            up_68ca3b) FOR upn_5dd210 IN ((sm_ship_mode_sk,
            sm_carrier) AS UPF_1ea0bf))
    ) AS r_d7e8ef 
        ON r_e0119c.i_container NOT ILIKE r_d7e8ef.sm_contract,
    (SELECT
        now() as te_ac6b60,
        r_46fb7b.ca_country as te_afa45c,
        r_46fb7b.ca_zip as te_d0c7fe 
    FROM
        db1.customer_address AS r_46fb7b,
        (SELECT
            r_e418aa.sm_code as te_d97a15,
            r_e9e1e6.cd_credit_rating as te_5cecf1,
            r_e9e1e6.cd_dep_college_count as te_1e6fa2 
        FROM
            db1.ship_mode AS r_e418aa,
            db1.time_dim AS r_3bb810 
        RIGHT JOIN
            db1.customer_demographics AS r_e9e1e6 
                ON r_3bb810.t_time_sk <= r_e9e1e6.cd_demo_sk 
        WHERE
            r_e418aa.sm_ship_mode_sk > r_e9e1e6.cd_dep_count 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 NULLS FIRST) AS r_e634a8 
    WHERE
        r_46fb7b.ca_address_sk != r_e634a8.te_1e6fa2 
    ORDER BY
        1 DESC, 2 NULLS FIRST, 3) AS r_d60a94 
    WHERE
        r_e0119c.i_brand ILIKE r_d60a94.te_afa45c 
        OR r_e0119c.i_units LIKE 'lez' 
        AND r_d7e8ef.upn_5dd210 ILIKE 'G' 
    ORDER BY
        1 NULLS LAST, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_c06fc3.s_floor_space as te_017591 
FROM
    db1.warehouse AS r_b89f08 
RIGHT JOIN
    db1.store AS r_c06fc3 
        ON r_b89f08.w_warehouse_sq_ft <= r_c06fc3.s_closed_date_sk 
WHERE
    r_c06fc3.s_street_name ILIKE 'g' 
    AND r_c06fc3.s_zip ILIKE 't' 
    OR r_c06fc3.s_store_name LIKE 'Qiizz2k' 
    AND r_b89f08.w_street_type LIKE 'QHZgGIHl' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_5ecb6c.w_gmt_offset as te_7c796f,
    r_6d6366.wp_rec_end_date as te_2f3588 
FROM
    (SELECT
        * 
    FROM
        db1.web_page PIVOT(count(wp_rec_start_date) AS pa_6ed048 FOR wp_web_page_sk IN ((92) AS pf_b7a79d,
        (22) AS pf_864d4c,
        (84) AS pf_e4f60c,
        (20) AS pf_62172e))) AS r_6d6366 
LEFT JOIN
    db1.customer_address AS r_bbb52c 
        ON r_6d6366.wp_url NOT ILIKE r_bbb52c.ca_country,
    db1.warehouse AS r_5ecb6c 
WHERE
    r_bbb52c.ca_county NOT LIKE r_5ecb6c.w_warehouse_id 
    OR NOT EXISTS (
        SELECT
            r_1803d4.cd_purchase_estimate as te_6da01a 
        FROM
            db1.customer_demographics AS r_1803d4 
        WHERE
            r_1803d4.cd_dep_college_count >= 90 
            AND r_1803d4.cd_purchase_estimate > r_1803d4.cd_dep_employed_count 
            AND r_1803d4.cd_dep_employed_count != r_1803d4.cd_purchase_estimate 
            OR r_1803d4.cd_credit_rating ILIKE '4'
    ) 
    OR NOT r_bbb52c.ca_city NOT ILIKE 'RXpRQiizz2' 
    OR r_6d6366.wp_url >= '7IG7ir9' 
    OR r_6d6366.pf_b7a79d > r_6d6366.pf_e4f60c 
ORDER BY
    1 DESC, 2 ASC NULLS FIRST;
----------->
SELECT
    pi() as te_3e3ceb,
    r_6e0c8b.w_country as te_0207ea,
    r_2e4647.i_rec_start_date as te_1cef8f 
FROM
    db1.item AS r_2e4647,
    db1.warehouse AS r_6e0c8b 
WHERE
    r_2e4647.i_category NOT LIKE r_6e0c8b.w_state 
    AND r_2e4647.i_manufact NOT LIKE r_2e4647.i_item_desc 
    OR r_2e4647.i_rec_end_date != r_2e4647.i_rec_start_date 
    OR r_2e4647.i_formulation <= '98hv95' 
    AND r_2e4647.i_manufact ILIKE '7RXp' 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_c83b47.s_tax_percentage / mod(r_8b1ea9.t_minute,
    98) as te_7fc289,
    r_c83b47.s_number_employees as te_ae4dd2,
    r_c83b47.s_company_id as te_9b727b 
FROM
    db1.store AS r_c83b47 
LEFT JOIN
    db1.customer AS r_0795d8 
        ON r_c83b47.s_street_number < r_0795d8.c_last_name,
    db1.time_dim AS r_8b1ea9 
WHERE
    r_c83b47.s_number_employees = r_8b1ea9.t_minute 
ORDER BY
    1 NULLS FIRST, 2 NULLS FIRST, 3 DESC NULLS LAST 
OFFSET 11;
----------->
SELECT
    r_8ca365.cc_tax_percentage as te_c88acf,
    string(r_8ca365.cc_gmt_offset) as te_95e062 
FROM
    db1.call_center AS r_8ca365,
    db1.ship_mode AS r_54ced9 
INNER JOIN
    (
        SELECT
            mod(r_0c8550.hd_income_band_sk,
            45.24631355) as te_a00111 
        FROM
            db1.household_demographics AS r_0c8550 
        WHERE
            r_0c8550.hd_buy_potential NOT ILIKE 'lB' 
            OR r_0c8550.hd_vehicle_count > 31 
            AND EXISTS (
                SELECT
                    r_49cce2.cd_education_status as te_44e71d,
                    r_49cce2.cd_education_status as te_ab5dd6 
                FROM
                    db1.customer_demographics AS r_49cce2,
                    db1.income_band AS r_3b04db 
                WHERE
                    r_49cce2.cd_dep_count != r_3b04db.ib_upper_bound 
                    AND r_49cce2.cd_marital_status ILIKE 'pRQ' 
                    OR r_49cce2.cd_dep_count <= 8 
                    OR r_49cce2.cd_education_status NOT LIKE 'lstnt' 
                    AND r_49cce2.cd_demo_sk <= 76 
                ORDER BY
                    1 DESC NULLS LAST, 2 DESC NULLS FIRST
            ) 
        ORDER BY
            1 DESC NULLS LAST) AS r_a90eb5 
                ON r_54ced9.sm_ship_mode_sk > r_a90eb5.te_a00111 
        WHERE
            r_8ca365.cc_tax_percentage = r_a90eb5.te_a00111 
            AND r_8ca365.cc_gmt_offset < r_a90eb5.te_a00111 
            AND r_8ca365.cc_state NOT ILIKE 'bB' 
            OR r_8ca365.cc_street_number ILIKE 'k' 
        ORDER BY
            1 DESC NULLS FIRST, 2 ASC;
----------->
SELECT
    r_2924bb.c_customer_id as te_bb8307,
    r_2924bb.c_first_name as te_710211 
FROM
    db1.store AS r_b13d42,
    db1.time_dim AS r_b2765a 
RIGHT JOIN
    db1.customer AS r_2924bb 
        ON r_b2765a.t_time_sk > r_2924bb.c_current_cdemo_sk 
WHERE
    r_b13d42.s_rec_start_date BETWEEN r_b13d42.s_rec_end_date AND DATE'2024-10-11' 
    AND r_b13d42.s_street_number LIKE 'r98hv' 
    OR r_b13d42.s_number_employees != 82 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_1b1cda.web_tax_percentage as te_bb426f,
    21 * r_e384cf.s_number_employees / (pi() - r_e384cf.s_company_id) as te_260d80,
    mod(r_e384cf.s_store_sk,
    r_e384cf.s_floor_space) as te_fb6dcf 
FROM
    db1.store AS r_e384cf 
RIGHT JOIN
    db1.customer_address AS r_ec0aff 
        ON r_e384cf.s_floor_space < r_ec0aff.ca_gmt_offset,
    db1.web_site AS r_1b1cda 
INNER JOIN
    (
        SELECT
            r_bfcc42.s_country as te_f318bd,
            r_bfcc42.s_rec_start_date as te_5813e0,
            r_bfcc42.s_market_desc as te_68929e 
        FROM
            db1.income_band AS r_24d0e4 
        RIGHT JOIN
            db1.time_dim AS r_604698 
                ON r_24d0e4.ib_income_band_sk != r_604698.t_hour,
            db1.store AS r_bfcc42 
        LEFT JOIN
            db1.customer_demographics AS r_1dc169 
                ON r_bfcc42.s_floor_space <= r_1dc169.cd_dep_count 
        WHERE
            r_604698.t_meal_time LIKE r_bfcc42.s_suite_number
    ) AS r_08c065 
        ON r_1b1cda.web_mkt_class NOT ILIKE r_08c065.te_f318bd 
WHERE
    r_e384cf.s_rec_start_date = r_1b1cda.web_rec_end_date 
    AND r_ec0aff.ca_city NOT ILIKE 'Qiizz2kGk4' 
    AND r_1b1cda.web_rec_end_date <= r_1b1cda.web_rec_start_date 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_53412b.s_tax_percentage as te_8aaf8d,
    r_d2fb18.hd_vehicle_count as te_926ec3,
    r_53412b.s_county as te_da90b4 
FROM
    db1.store AS r_53412b,
    db1.income_band AS r_a00baa 
RIGHT JOIN
    db1.household_demographics AS r_d2fb18 
        ON r_a00baa.ib_lower_bound <= r_d2fb18.hd_dep_count 
WHERE
    r_53412b.s_store_id NOT LIKE r_d2fb18.hd_buy_potential 
    OR NOT r_53412b.s_division_name NOT LIKE 'lpx6kg7' 
    AND r_53412b.s_tax_percentage != r_53412b.s_gmt_offset 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_4a144e.te_993b29 as te_960874,
    cos(r_53c68c.sm_ship_mode_sk) as te_a90725 
FROM
    (SELECT
        19.52915586D + r_51a1d8.t_second as te_9bbb7f,
        r_90c487.sr_reversed_charge as te_993b29,
        r_51a1d8.t_am_pm as te_9af282 
    FROM
        db1.time_dim AS r_51a1d8 
    INNER JOIN
        db1.store_returns AS r_90c487 
            ON r_51a1d8.t_time_sk != r_90c487.sr_ticket_number,
        db1.time_dim AS r_1c9636 
    INNER JOIN
        db1.income_band AS r_95564b 
            ON r_1c9636.t_time != r_95564b.ib_upper_bound 
    WHERE
        r_51a1d8.t_sub_shift ILIKE r_1c9636.t_am_pm 
        AND r_90c487.sr_return_quantity < 51 
        AND r_51a1d8.t_shift LIKE r_51a1d8.t_time_id 
        OR r_90c487.sr_cdemo_sk < 5 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_4a144e 
LEFT JOIN
    db1.ship_mode AS r_53c68c 
        ON r_4a144e.te_9af282 NOT LIKE r_53c68c.sm_type,
    db1.warehouse AS r_7663e3 
WHERE
    r_4a144e.te_993b29 > r_7663e3.w_warehouse_sq_ft 
    AND (
        NOT r_7663e3.w_street_name ILIKE 'Uulstntb2' 
        OR (
            NOT r_7663e3.w_warehouse_sk = r_53c68c.sm_ship_mode_sk 
            OR r_4a144e.te_993b29 != r_7663e3.w_gmt_offset 
            AND r_7663e3.w_warehouse_id NOT LIKE 'l'
        )
    ) 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST;
----------->
WITH CTE_f175d5(te_b06f07) AS (WITH CTE_67327b(te_aec3d9) AS (SELECT
    r_06c29a.wp_char_count as te_aec3d9 
FROM
    db1.web_page AS r_06c29a 
WHERE
    r_06c29a.wp_url NOT ILIKE '2SW4P' 
ORDER BY
    1 DESC NULLS FIRST) SELECT
    r_748301.te_aec3d9 as te_b06f07 
FROM
    CTE_67327b AS r_748301 
WHERE
    r_748301.te_aec3d9 = 64 
    OR r_748301.te_aec3d9 != r_748301.te_aec3d9 
ORDER BY
    1 ASC 
LIMIT 24) SELECT
r_fe6648.ca_gmt_offset as te_6bf417 FROM
    CTE_f175d5 AS r_d0ef19 
RIGHT JOIN
    db1.customer_address AS r_fe6648 
        ON r_d0ef19.te_b06f07 = r_fe6648.ca_address_sk 
WHERE
    r_fe6648.ca_county NOT LIKE 'elBy' 
    OR r_fe6648.ca_city LIKE 'Gv7RXpRQi' 
    OR r_fe6648.ca_state NOT LIKE 'gD' 
    AND r_fe6648.ca_suite_number = 'i' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_02281e.wp_autogen_flag as te_1afe2a 
FROM
    db1.time_dim AS r_c0506c 
INNER JOIN
    db1.web_page AS r_02281e 
        ON r_c0506c.t_second = r_02281e.wp_creation_date_sk 
WHERE
    r_02281e.wp_creation_date_sk = 25 
    OR r_02281e.wp_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
    AND r_02281e.wp_url LIKE 'F' 
    OR r_c0506c.t_time_sk > 7 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_5c0fc4.s_suite_number as te_1b9f6a 
FROM
    db1.store AS r_5c0fc4 
WHERE
    r_5c0fc4.s_market_id != 0 
    OR r_5c0fc4.s_manager NOT LIKE 'RX' 
    AND r_5c0fc4.s_rec_start_date != r_5c0fc4.s_rec_end_date 
    AND r_5c0fc4.s_market_id <= 45 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_6245a2.cc_company_name as te_8fa556,
    now() as te_5c7e51,
    r_6245a2.cc_name as te_7f1ef0 
FROM
    db1.item AS r_8d2e6d 
INNER JOIN
    db1.call_center AS r_6245a2 
        ON r_8d2e6d.i_size LIKE r_6245a2.cc_class,
    db1.income_band AS r_5688f4 
LEFT JOIN
    db1.time_dim AS r_c1b777 
        ON r_5688f4.ib_upper_bound > r_c1b777.t_time 
WHERE
    r_6245a2.cc_employees >= r_c1b777.t_time_sk 
    OR r_8d2e6d.i_wholesale_cost <= r_8d2e6d.i_current_price 
    OR r_c1b777.t_shift NOT LIKE r_6245a2.cc_state 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_8d76b1.r_reason_sk as te_dc5a83,
    r_807e01.c_login as te_759357,
    r_e14691.c_preferred_cust_flag as te_f07c33 
FROM
    db1.customer AS r_807e01 
INNER JOIN
    db1.customer_demographics AS r_a882cc 
        ON r_807e01.c_email_address NOT LIKE r_a882cc.cd_marital_status,
    db1.customer AS r_e14691 
RIGHT JOIN
    db1.reason AS r_8d76b1 
        ON r_e14691.c_customer_id ILIKE r_8d76b1.r_reason_desc 
WHERE
    r_807e01.c_birth_day = r_e14691.c_birth_month 
    AND r_e14691.c_customer_sk = 98 
    OR r_e14691.c_customer_sk >= r_e14691.c_last_review_date_sk 
    OR r_e14691.c_first_name LIKE 'lstntb' 
    AND r_807e01.c_login != 'st' 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC 
OFFSET 74;
----------->
SELECT
    r_e42b7c.i_wholesale_cost as te_f25bb3 
FROM
    (SELECT
        * 
    FROM
        db1.time_dim PIVOT(count(t_sub_shift) AS pa_ddbadb FOR t_shift IN (('Uulstntb2') AS pf_5d73ae,
        ('DXX4QHZg') AS pf_ad0c94,
        ('ntb2Gv7R') AS pf_0a0b09,
        ('x6') AS pf_95986a))) AS r_43d1f5 
INNER JOIN
    db1.item AS r_e42b7c 
        ON r_43d1f5.t_hour = r_e42b7c.i_item_sk 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_3018b4.w_gmt_offset as te_004592 
FROM
    db1.warehouse AS r_3018b4 
RIGHT JOIN
    db1.customer_address AS r_a4d1b8 
        ON r_3018b4.w_warehouse_sk != r_a4d1b8.ca_address_sk 
WHERE
    r_3018b4.w_warehouse_id NOT ILIKE ((SELECT
        r_a70ba6.i_color as te_60b34d 
    FROM
        db1.income_band AS r_4d6275 
    INNER JOIN
        db1.item AS r_a70ba6 
            ON r_4d6275.ib_income_band_sk < r_a70ba6.i_category_id,
        db1.store_returns AS r_37d521 
    WHERE
        r_a70ba6.i_item_sk < r_37d521.sr_return_ship_cost 
        OR r_a70ba6.i_manager_id = 92 
        AND r_a70ba6.i_class ILIKE 'S' 
        OR r_37d521.sr_addr_sk <= r_a70ba6.i_category_id 
        OR r_a70ba6.i_rec_end_date != r_a70ba6.i_rec_start_date 
    ORDER BY
        1 ASC NULLS LAST 
    LIMIT 1)) 
OR r_a4d1b8.ca_suite_number NOT LIKE r_a4d1b8.ca_location_type 
AND r_3018b4.w_warehouse_sk <= r_3018b4.w_warehouse_sq_ft ORDER BY
    1 DESC 
OFFSET 27;
----------->
SELECT
    r_d1e23d.w_county as te_af9dcb,
    r_d1e23d.w_warehouse_sq_ft as te_dcdbff 
FROM
    db1.warehouse AS r_d1e23d,
    db1.ship_mode AS r_f25290 
WHERE
    r_d1e23d.w_gmt_offset = r_f25290.sm_ship_mode_sk 
    AND r_f25290.sm_ship_mode_sk != r_d1e23d.w_warehouse_sk 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_e7c9fd.ca_street_name as te_8cebeb 
FROM
    db1.customer_address AS r_e7c9fd 
WHERE
    r_e7c9fd.ca_city NOT ILIKE 'st' 
    OR r_e7c9fd.ca_city LIKE 'elB' 
    OR r_e7c9fd.ca_state NOT LIKE r_e7c9fd.ca_address_id 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_f6231a.c_first_sales_date_sk as te_93008c,
    r_7fa0e8.i_color as te_b989f9 
FROM
    db1.customer AS r_f6231a 
LEFT JOIN
    db1.store_returns AS r_e4fe85 
        ON r_f6231a.c_birth_day > r_e4fe85.sr_hdemo_sk,
    db1.item AS r_7fa0e8 
WHERE
    r_f6231a.c_preferred_cust_flag NOT LIKE r_7fa0e8.i_item_id 
    AND r_7fa0e8.i_category_id >= r_f6231a.c_current_addr_sk 
    AND r_7fa0e8.i_rec_start_date != DATE'2024-03-25' 
    OR r_f6231a.c_customer_id NOT LIKE r_7fa0e8.i_item_id 
ORDER BY
    1 DESC NULLS FIRST, 2;
----------->
SELECT
    r_856d09.c_birth_country as te_bca2c4 
FROM
    db1.customer AS r_856d09 
ORDER BY
    1 NULLS LAST;
----------->
(
    SELECT
        r_c3c0d0.ib_upper_bound as te_a37833,
        'R' as te_f52062 
    FROM
        db1.income_band AS r_c3c0d0 
    RIGHT JOIN
        db1.reason AS r_ce0194 
            ON r_c3c0d0.ib_income_band_sk >= r_ce0194.r_reason_sk,
        db1.reason AS r_a2e626 
    WHERE
        r_ce0194.r_reason_id = r_a2e626.r_reason_desc 
        AND r_c3c0d0.ib_income_band_sk = 30 
        AND r_a2e626.r_reason_sk < 43 
    ORDER BY
        1 ASC NULLS LAST, 2 NULLS FIRST
) MINUS  (SELECT
    r_14753b.cd_dep_college_count as te_5759ea, r_14753b.cd_credit_rating as te_86ff30 
FROM
    db1.customer_address AS r_248951,
    db1.household_demographics AS r_883b82 
LEFT JOIN
    db1.customer_demographics AS r_14753b 
        ON r_883b82.hd_dep_count < r_14753b.cd_demo_sk 
WHERE
    r_248951.ca_suite_number LIKE r_14753b.cd_education_status 
    AND r_14753b.cd_demo_sk > 72 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST 
OFFSET 22) ORDER BY
1 DESC NULLS FIRST, 2 DESC NULLS LAST 
OFFSET 56;
----------->
SELECT
    r_ac5a72.web_tax_percentage as te_d70341,
    r_ac5a72.web_state as te_b1a481 
FROM
    db1.web_site AS r_ac5a72 
RIGHT JOIN
    db1.customer AS r_e02615 
        ON r_ac5a72.web_company_name > r_e02615.c_birth_country,
    db1.customer_address AS r_a72b8a 
WHERE
    r_ac5a72.web_state LIKE r_a72b8a.ca_suite_number 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_e0d2ef.up_4bccae as te_aec318,
    r_e0d2ef.ca_address_id as te_df151f 
FROM
    db1.income_band AS r_2b3e60,
    (SELECT
        * 
    FROM
        db1.customer_address UNPIVOT INCLUDE NULLS ((up_009e47,
        up_4bccae,
        up_49c5c1,
        up_48b74d) FOR upn_b2839f IN ((ca_street_name,
        ca_gmt_offset,
        ca_street_number,
        ca_address_sk) AS UPF_8f3de8))) AS r_e0d2ef 
INNER JOIN
    db1.customer AS r_00746e 
        ON r_e0d2ef.ca_county NOT LIKE r_00746e.c_email_address 
WHERE
    r_2b3e60.ib_upper_bound > r_00746e.c_current_hdemo_sk 
    OR r_e0d2ef.up_009e47 LIKE r_00746e.c_salutation 
    OR r_00746e.c_last_name NOT ILIKE r_00746e.c_email_address 
ORDER BY
    1 DESC, 2 DESC NULLS LAST 
OFFSET 78;
----------->
SELECT
    r_308f9c.c_birth_month as te_187e5a 
FROM
    (SELECT
        * 
    FROM
        db1.customer PIVOT(min(c_current_addr_sk) AS pa_a9007d FOR c_customer_sk IN ((11) AS pf_ef153d,
        (85) AS pf_aae55b,
        (60) AS pf_726eaf,
        (9) AS pf_834bf8))) AS r_308f9c 
ORDER BY
    1 ASC 
OFFSET 57;
----------->
SELECT
    r_dd5c62.ca_country as te_69e4fc,
    r_751258.sm_code as te_5e95df,
    r_d6c34e.c_first_sales_date_sk as te_c2d59a 
FROM
    db1.customer AS r_d6c34e,
    db1.customer_address AS r_dd5c62 
LEFT JOIN
    db1.ship_mode AS r_751258 
        ON r_dd5c62.ca_address_sk <= r_751258.sm_ship_mode_sk 
WHERE
    r_d6c34e.c_customer_sk <= r_dd5c62.ca_address_sk 
    OR r_d6c34e.c_birth_country NOT LIKE 'QFrTUO' 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC, 3 NULLS LAST;
----------->
SELECT
    timestamp_millis(r_e1ff45.sr_customer_sk) as te_007679,
    r_e84581.sr_return_quantity as te_404455 
FROM
    (SELECT
        r_e91196.t_hour as te_c50c1c,
        r_e91196.t_second as te_500d12,
        r_e91196.t_sub_shift as te_eefd0b 
    FROM
        db1.reason AS r_8d4878,
        db1.time_dim AS r_e91196 
    WHERE
        r_8d4878.r_reason_sk > r_e91196.t_second 
    ORDER BY
        1 NULLS FIRST, 2 ASC NULLS LAST, 3 ASC 
    OFFSET 98) AS r_37ff55 LEFT JOIN
    db1.store_returns AS r_e1ff45 
        ON r_37ff55.te_c50c1c != r_e1ff45.sr_addr_sk,
    db1.store_returns AS r_e84581 
RIGHT JOIN
    db1.call_center AS r_105d97 
        ON r_e84581.sr_hdemo_sk >= r_105d97.cc_company 
WHERE
    r_37ff55.te_eefd0b LIKE r_105d97.cc_call_center_id 
ORDER BY
    1 NULLS FIRST, 2 NULLS FIRST;
----------->
SELECT
    r_07a3e3.wp_rec_end_date as te_10a4fe 
FROM
    db1.web_page AS r_07a3e3 
INNER JOIN
    (
        SELECT
            r_9b398f.d_holiday as te_245221 
        FROM
            db1.date_dim AS r_9b398f 
        LEFT JOIN
            db1.store_returns AS r_772fa8 
                ON r_9b398f.d_qoy < r_772fa8.sr_return_time_sk 
        WHERE
            r_9b398f.d_fy_year != 94 
            AND r_9b398f.d_moy < 6 
            OR r_772fa8.sr_fee != 29.85544224 
        ORDER BY
            1 DESC NULLS LAST
    ) AS r_04db3e 
        ON r_07a3e3.wp_autogen_flag ILIKE r_04db3e.te_245221 
WHERE
    r_07a3e3.wp_type NOT ILIKE 'RGC0lezm' 
    OR r_04db3e.te_245221 NOT ILIKE r_07a3e3.wp_web_page_id 
    OR r_07a3e3.wp_rec_end_date = DATE'2024-10-11' 
    AND r_07a3e3.wp_access_date_sk != r_07a3e3.wp_image_count 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_7cc822.d_date as te_6d6dc2 
FROM
    db1.date_dim AS r_7cc822 
RIGHT JOIN
    db1.customer_address AS r_f061ad 
        ON r_7cc822.d_following_holiday LIKE r_f061ad.ca_location_type 
WHERE
    r_f061ad.ca_address_sk = 55 
    AND r_7cc822.d_same_day_ly <= 46 
ORDER BY
    1 DESC;
----------->
SELECT
    r_d3d0b8.cc_call_center_id as te_02f2d3,
    r_d3d0b8.cc_employees as te_923798,
    r_754dce.web_tax_percentage as te_bc1567 
FROM
    db1.call_center AS r_d3d0b8,
    db1.web_site AS r_754dce 
WHERE
    r_d3d0b8.cc_call_center_id NOT ILIKE r_754dce.web_country 
    AND r_d3d0b8.cc_zip LIKE 'X4QHZgGIH' 
ORDER BY
    1, 2 DESC NULLS LAST, 3 NULLS FIRST 
LIMIT 60;
----------->
SELECT
    r_0c156d.cd_education_status as te_ee7af3 
FROM
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(min(sm_ship_mode_sk) AS pa_186401 FOR sm_code IN (('By7IG7i') AS pf_a17210,
        ('ZgGI') AS pf_770f41,
        ('RGC0lezmSR') AS pf_6823ea,
        ('hv9') AS pf_7b3563))) AS r_03bac9 
INNER JOIN
    db1.customer_demographics AS r_0c156d 
        ON r_03bac9.sm_contract NOT ILIKE r_0c156d.cd_gender 
WHERE
    r_0c156d.cd_demo_sk <= 99 
    AND r_03bac9.sm_carrier LIKE 'BRGC0lezmS' 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 45;
----------->
SELECT
    r_f40fa0.web_rec_start_date as te_e57643,
    r_f40fa0.web_tax_percentage as te_92c45c 
FROM
    db1.web_site AS r_f40fa0 
INNER JOIN
    db1.customer_address AS r_d37c7e 
        ON r_f40fa0.web_county <= r_d37c7e.ca_country,
    (WITH CTE_a0a380(te_cefdf5) AS (SELECT
        r_c57eee.te_8b0e50 as te_cefdf5 
    FROM
        db1.call_center AS r_2f4b70 
    LEFT JOIN
        (SELECT
            r_12df64.sr_reversed_charge as te_7ab1e5,
            r_12df64.sr_net_loss / r_da5a48.c_first_shipto_date_sk as te_8b0e50 
        FROM
            db1.store_returns AS r_12df64 
        INNER JOIN
            db1.customer AS r_da5a48 
                ON r_12df64.sr_store_credit < r_da5a48.c_customer_sk,
            db1.store AS r_f26cab 
        RIGHT JOIN
            db1.customer_demographics AS r_d1e469 
                ON r_f26cab.s_store_id <= r_d1e469.cd_marital_status 
        WHERE
            r_12df64.sr_ticket_number > r_f26cab.s_floor_space) AS r_c57eee 
            ON r_2f4b70.cc_gmt_offset > r_c57eee.te_8b0e50 
    ORDER BY
        1 DESC NULLS LAST), CTE_76b849(te_cd6b4b, te_c86b41, te_e4015d) AS (SELECT
            r_23b935.cc_rec_start_date as te_cd6b4b, mod(r_23b935.cc_employees, r_23b935.cc_division + 40) as te_c86b41, r_23b935.cc_employees as te_e4015d 
        FROM
            db1.call_center AS r_23b935,
            CTE_a0a380 AS r_cff5eb 
        WHERE
            r_23b935.cc_gmt_offset > r_cff5eb.te_cefdf5 
            AND EXISTS (SELECT
                r_cda461.w_street_number as te_2de86d,
                r_18b4cb.te_70316f as te_c6b98c,
                r_cda461.w_county as te_df8c36 
            FROM
                (SELECT
                    r_d7cfb8.te_cefdf5 as te_70316f 
                FROM
                    CTE_a0a380 AS r_d7cfb8 
                WHERE
                    r_d7cfb8.te_cefdf5 < r_d7cfb8.te_cefdf5 
                    AND r_d7cfb8.te_cefdf5 >= r_d7cfb8.te_cefdf5 
                ORDER BY
                    1 DESC NULLS LAST) AS r_18b4cb, (SELECT
                    r_95c6ca.cc_gmt_offset as te_c1f751 
                FROM
                    db1.call_center AS r_95c6ca 
                RIGHT JOIN
                    db1.income_band AS r_3957e0 
                        ON r_95c6ca.cc_mkt_id <= r_3957e0.ib_lower_bound 
                WHERE
                    r_95c6ca.cc_employees <= r_3957e0.ib_lower_bound 
                    OR r_95c6ca.cc_rec_start_date != r_95c6ca.cc_rec_end_date 
                    OR r_95c6ca.cc_division = 37 
                ORDER BY
                    1 DESC NULLS FIRST 
                LIMIT 70) AS r_846824 RIGHT JOIN
                db1.warehouse AS r_cda461 
                    ON r_846824.te_c1f751 = r_cda461.w_gmt_offset 
            WHERE
                (NOT r_18b4cb.te_70316f >= r_cda461.w_gmt_offset 
                AND (NOT r_cda461.w_warehouse_sk < 1 
                AND r_cda461.w_city NOT LIKE r_cda461.w_warehouse_name)) 
            ORDER BY
                1 DESC NULLS FIRST, 2 NULLS FIRST, 3 DESC NULLS FIRST) 
            ORDER BY
                1 NULLS FIRST, 2 DESC NULLS FIRST, 3 ASC NULLS FIRST 
            LIMIT 17) SELECT
                r_900e96.s_store_id as te_4911d2, r_8e1cc0.te_cefdf5 as te_c919c1, try_add(r_97b723.t_hour, r_3a53e3.te_c86b41) as te_522713 FROM
                    CTE_a0a380 AS r_8e1cc0 
                INNER JOIN
                    CTE_76b849 AS r_3a53e3 
                        ON date_from_unix_date(bigint(cos(r_8e1cc0.te_cefdf5))) < date_from_unix_date(hash(r_3a53e3.te_cd6b4b)),
                    db1.time_dim AS r_97b723 
                RIGHT JOIN
                    db1.store AS r_900e96 
                        ON r_97b723.t_time_id LIKE r_900e96.s_county 
                WHERE
                    r_8e1cc0.te_cefdf5 < r_900e96.s_gmt_offset 
                    OR NOT r_900e96.s_gmt_offset != 87.29206366 
                    AND r_900e96.s_gmt_offset != 30.4138943 
                    OR r_900e96.s_gmt_offset NOT IN (
                        SELECT
                            r_39c7dc.d_same_day_lq - r_4bc934.ca_gmt_offset as te_14e495 
                        FROM
                            db1.customer_address AS r_4bc934 
                        INNER JOIN
                            db1.date_dim AS r_39c7dc 
                                ON r_4bc934.ca_gmt_offset != r_39c7dc.d_week_seq 
                        WHERE
                            r_4bc934.ca_county NOT ILIKE r_4bc934.ca_street_name 
                        ORDER BY
                            1 DESC NULLS FIRST
                    ) 
                    OR r_97b723.t_hour = 54 
                ORDER BY
                    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS FIRST) AS r_f65c83 
                LEFT JOIN
                    db1.reason AS r_ac7965 
                        ON r_f65c83.te_c919c1 <= r_ac7965.r_reason_sk 
                WHERE
                    r_f40fa0.web_manager NOT ILIKE r_ac7965.r_reason_desc 
                    AND r_d37c7e.ca_address_id = r_f40fa0.web_site_id 
                    AND r_f65c83.te_4911d2 NOT ILIKE r_d37c7e.ca_street_type 
                ORDER BY
                    1 ASC NULLS FIRST, 2 ASC NULLS LAST;
----------->
SELECT
    r_2ff05d.c_customer_sk as te_729687,
    r_2ff05d.c_first_sales_date_sk * 11.69391453D as te_5d71ee,
    r_fc3419.ca_gmt_offset as te_bd067f 
FROM
    db1.customer AS r_2ff05d,
    db1.customer_address AS r_fc3419 
WHERE
    r_2ff05d.c_login ILIKE r_fc3419.ca_street_number 
    OR r_fc3419.ca_zip NOT LIKE 'vXUu' 
    AND r_fc3419.ca_address_id NOT LIKE '7RX' 
    AND r_2ff05d.c_preferred_cust_flag ILIKE r_fc3419.ca_state 
    AND r_fc3419.ca_state ILIKE 'ez' 
ORDER BY
    1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    positive(bigint(e() - unix_timestamp())) as te_8d053d 
FROM
    db1.call_center AS r_cff2a1 
WHERE
    r_cff2a1.cc_street_name LIKE '7' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_8a2749.ib_income_band_sk as te_efcf9d,
    13.9243866D + r_8a2749.ib_upper_bound as te_4ceea9,
    r_29ad97.hd_buy_potential as te_e466d6 
FROM
    db1.household_demographics AS r_29ad97,
    db1.income_band AS r_8a2749 
WHERE
    r_29ad97.hd_income_band_sk != r_8a2749.ib_income_band_sk 
    OR r_29ad97.hd_vehicle_count >= 77 
    OR r_29ad97.hd_vehicle_count = 1 
    AND r_29ad97.hd_buy_potential NOT ILIKE 'U' 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC;
----------->
SELECT
    r_c7a893.d_date_id as te_654c9a,
    hash(r_c7a893.d_year,
    r_c7a893.d_date) as te_58df51,
    r_35f3d5.cc_manager as te_72c328 
FROM
    db1.call_center AS r_35f3d5,
    db1.date_dim AS r_c7a893 
WHERE
    r_35f3d5.cc_rec_end_date >= r_c7a893.d_date 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
WITH CTE_1a9089(te_bfa255) AS (SELECT
    r_890baa.hd_dep_count as te_bfa255 
FROM
    db1.household_demographics AS r_890baa 
RIGHT JOIN
    db1.reason AS r_3e5861 
        ON r_890baa.hd_vehicle_count BETWEEN 66 AND 66 
WHERE
    r_890baa.hd_buy_potential ILIKE r_3e5861.r_reason_id 
    OR r_3e5861.r_reason_desc LIKE r_3e5861.r_reason_id 
    AND r_890baa.hd_dep_count >= 96 
ORDER BY
    1 DESC NULLS LAST) SELECT
    r_41ab00.te_bfa255 as te_7d8359 
FROM
    CTE_1a9089 AS r_41ab00 
WHERE
    (
        NOT r_41ab00.te_bfa255 < 74 
        AND r_41ab00.te_bfa255 <= 95
    ) 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    66.0751175D + r_03f9e9.d_same_day_ly - r_d148db.ca_gmt_offset as te_66d41d,
    r_03f9e9.d_date_id as te_a19d7a,
    r_03f9e9.d_last_dom as te_61e611 
FROM
    db1.date_dim AS r_03f9e9,
    (SELECT
        year(now()) as te_b1a240 
    FROM
        db1.web_site AS r_5f9b56 
    RIGHT JOIN
        (
            SELECT
                r_646210.wp_link_count as te_1059db 
            FROM
                db1.web_page AS r_646210 
            RIGHT JOIN
                (
                    SELECT
                        r_26d3b9.web_mkt_id as te_1d997c 
                    FROM
                        (SELECT
                            * 
                        FROM
                            db1.web_site PIVOT(min(web_country) AS pa_3bde2d FOR web_state IN (('MaLQ') AS pf_30be6b,
                            ('LQF') AS pf_fb47e3,
                            ('98hv') AS pf_a0c8ec,
                            ('lstnt') AS pf_233f02))) AS r_26d3b9 
                    WHERE
                        r_26d3b9.web_tax_percentage NOT IN (
                            SELECT
                                r_a4d0ba.sr_reversed_charge as te_7d4472 
                            FROM
                                db1.store_returns AS r_a4d0ba 
                            ORDER BY
                                1 NULLS LAST
                        ) 
                    ORDER BY
                        1 DESC NULLS FIRST) AS r_22ee32 
                            ON r_646210.wp_creation_date_sk > r_22ee32.te_1d997c 
                    WHERE
                        r_646210.wp_url NOT LIKE 'CMa' 
                        OR r_22ee32.te_1d997c >= 40 
                        OR r_646210.wp_web_page_id >= 'UOV' 
                    ORDER BY
                        1 ASC NULLS LAST 
                    LIMIT 85) AS r_6301b8 
                        ON r_5f9b56.web_mkt_id < r_6301b8.te_1059db WHERE
                            r_5f9b56.web_mkt_desc NOT LIKE 'VbBRG' 
                            AND r_5f9b56.web_mkt_id = 91 
                            OR r_5f9b56.web_city >= r_5f9b56.web_zip 
                    ORDER BY
                        1 NULLS LAST
                ) AS r_fdd8f0 
            LEFT JOIN
                db1.customer_address AS r_d148db 
                    ON r_fdd8f0.te_b1a240 >= r_d148db.ca_gmt_offset 
            WHERE
                r_03f9e9.d_year >= r_d148db.ca_gmt_offset 
                AND r_d148db.ca_location_type NOT ILIKE r_d148db.ca_street_type 
                OR r_d148db.ca_country LIKE 'IvX' 
                OR r_fdd8f0.te_b1a240 BETWEEN 37 AND 37 
                OR r_d148db.ca_state NOT LIKE 'R' 
            ORDER BY
                1 DESC NULLS LAST, 2 ASC, 3 DESC;
----------->
SELECT
    r_a63c6c.d_quarter_seq as te_5d50bc,
    r_a63c6c.d_date as te_f4c460,
    timestamp_seconds(4 * r_a63c6c.d_year) as te_a92840 
FROM
    db1.date_dim AS r_a63c6c,
    db1.customer_demographics AS r_716c70 
WHERE
    r_a63c6c.d_current_quarter NOT ILIKE r_716c70.cd_credit_rating 
    AND r_a63c6c.d_first_dom = r_716c70.cd_purchase_estimate 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_8eb795.c_birth_month as te_6c0f4a,
    r_8eb795.c_customer_id as te_377de7,
    r_5c51e9.w_gmt_offset / r_8eb795.c_current_addr_sk / 24 as te_afe325 
FROM
    db1.customer AS r_8eb795 
INNER JOIN
    db1.web_page AS r_34ed49 
        ON r_8eb795.c_last_name LIKE r_34ed49.wp_url,
    db1.warehouse AS r_5c51e9 
WHERE
    r_8eb795.c_email_address LIKE r_5c51e9.w_county 
    AND r_34ed49.wp_url NOT LIKE r_5c51e9.w_street_name 
    OR r_5c51e9.w_warehouse_name ILIKE r_8eb795.c_last_name 
    AND r_8eb795.c_email_address ILIKE ((SELECT
        try_add(r_210fe3.hd_buy_potential,
        r_210fe3.hd_demo_sk) as te_53dce9 
    FROM
        db1.household_demographics AS r_210fe3 
    RIGHT JOIN
        db1.income_band AS r_f22709 
            ON r_210fe3.hd_vehicle_count = r_f22709.ib_income_band_sk 
    WHERE
        r_210fe3.hd_dep_count = r_210fe3.hd_vehicle_count 
        AND r_210fe3.hd_income_band_sk BETWEEN 5 AND 5 
        AND r_f22709.ib_lower_bound > r_f22709.ib_income_band_sk 
    ORDER BY
        1 
    LIMIT 1)) 
AND r_34ed49.wp_rec_start_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' ORDER BY
    1 ASC NULLS LAST, 2, 3 ASC NULLS FIRST;
----------->
(
    SELECT
        r_1aedb3.te_daa6e9 as te_cd7598,
        DATE'2024-03-25' as te_aae767 
    FROM
        (SELECT
            r_b30d38.cc_mkt_id as te_daa6e9,
            r_b30d38.cc_tax_percentage as te_ca1778 
        FROM
            db1.call_center AS r_b30d38 
        LEFT JOIN
            db1.customer_demographics AS r_6cf717 
                ON r_b30d38.cc_closed_date_sk <= r_6cf717.cd_dep_college_count,
            db1.customer_demographics AS r_27f127 
        WHERE
            r_b30d38.cc_closed_date_sk = r_27f127.cd_purchase_estimate 
            AND r_b30d38.cc_gmt_offset < r_b30d38.cc_tax_percentage 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST) AS r_1aedb3 
    WHERE
        r_1aedb3.te_ca1778 != to_char(r_1aedb3.te_daa6e9, '999') 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST) MINUS  (SELECT
            r_6530f7.sr_ticket_number as te_0d76ed, make_date(r_6530f7.sr_addr_sk, r_6530f7.sr_return_quantity, r_6530f7.sr_hdemo_sk) as te_1564c0 
        FROM
            db1.store_returns AS r_6530f7 
        LEFT JOIN
            db1.income_band AS r_a3b065 
                ON r_6530f7.sr_addr_sk <= r_a3b065.ib_upper_bound,
            db1.reason AS r_afda8c 
        INNER JOIN
            db1.customer_address AS r_9f7557 
                ON r_afda8c.r_reason_id NOT LIKE r_9f7557.ca_country 
        WHERE
            r_6530f7.sr_item_sk != r_9f7557.ca_address_sk 
            OR r_a3b065.ib_upper_bound = 22 
            AND NOT r_afda8c.r_reason_desc ILIKE r_9f7557.ca_country 
            OR r_9f7557.ca_street_number NOT ILIKE r_9f7557.ca_suite_number 
            AND r_9f7557.ca_address_id NOT ILIKE 'XX4Q' 
        ORDER BY
            1 ASC NULLS LAST, 2 DESC NULLS LAST 
        LIMIT 1) ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_87cda9.c_customer_id as te_9a9bb5,
    r_96db60.i_wholesale_cost - r_96db60.i_wholesale_cost as te_880c76 
FROM
    db1.reason AS r_209510 
RIGHT JOIN
    db1.customer_address AS r_53523c 
        ON r_209510.r_reason_sk != r_53523c.ca_address_sk,
    db1.item AS r_96db60 
LEFT JOIN
    db1.customer AS r_87cda9 
        ON r_96db60.i_category NOT ILIKE r_87cda9.c_preferred_cust_flag 
WHERE
    r_209510.r_reason_sk = r_96db60.i_class_id 
    OR r_53523c.ca_country ILIKE 'lst' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_b9b89a.up_527c3a as te_405e7e 
FROM
    (SELECT
        r_80bef5.r_reason_sk as te_2b8682 
    FROM
        db1.reason AS r_80bef5 
    WHERE
        r_80bef5.r_reason_sk BETWEEN 62 AND 62 
        OR NOT EXISTS (
            SELECT
                (66 * r_918cf2.web_close_date_sk + unix_timestamp()) * r_918cf2.web_close_date_sk as te_8f6056,
                r_918cf2.web_market_manager as te_668dbf,
                r_a0fa73.ca_address_sk as te_7c1a01 
            FROM
                db1.customer_address AS r_a0fa73,
                db1.web_site AS r_918cf2 
            WHERE
                r_a0fa73.ca_gmt_offset <= r_918cf2.web_tax_percentage 
                OR r_918cf2.web_site_sk <= r_918cf2.web_company_id 
            ORDER BY
                1 DESC, 2 ASC, 3 DESC NULLS FIRST
        ) 
        AND r_80bef5.r_reason_desc NOT LIKE r_80bef5.r_reason_id 
    ORDER BY
        1 ASC) AS r_4e8422 
    RIGHT JOIN
        (
            SELECT
                * 
            FROM
                db1.customer UNPIVOT EXCLUDE NULLS ((up_527c3a,
                up_f00a43,
                up_b4046e) FOR upn_8c5302 IN ((c_customer_id,
                c_login,
                c_last_review_date_sk) AS UPF_2dafb9,
                (c_preferred_cust_flag,
                c_last_name,
                c_current_cdemo_sk) AS UPF_3a73f1))
        ) AS r_b9b89a 
            ON r_4e8422.te_2b8682 != r_b9b89a.c_first_sales_date_sk 
    ORDER BY
        1 NULLS LAST;
----------->
SELECT
    r_e76654.c_first_sales_date_sk as te_fa300a 
FROM
    db1.customer AS r_e76654 
INNER JOIN
    db1.customer_demographics AS r_21a50c 
        ON r_e76654.c_customer_id ILIKE r_21a50c.cd_credit_rating 
WHERE
    r_e76654.c_customer_sk < 78 
    AND r_e76654.c_customer_id NOT LIKE 'Qiiz' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_424840.i_rec_end_date as te_794330,
    r_0913ef.w_county as te_f0e7ad 
FROM
    db1.warehouse AS r_0913ef 
RIGHT JOIN
    db1.customer_demographics AS r_f7bff8 
        ON r_0913ef.w_warehouse_sk != r_f7bff8.cd_purchase_estimate,
    db1.item AS r_424840 
RIGHT JOIN
    db1.time_dim AS r_be0262 
        ON r_424840.i_brand LIKE r_be0262.t_shift 
WHERE
    r_f7bff8.cd_gender LIKE r_424840.i_item_id 
    OR r_f7bff8.cd_gender NOT LIKE r_0913ef.w_county 
    AND r_0913ef.w_warehouse_name NOT LIKE 'FrT' 
    OR r_424840.i_size NOT ILIKE '0lezm' 
    AND r_424840.i_rec_start_date > r_424840.i_rec_end_date 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_c8c6cd.te_c2bd08 as te_a01e02,
    r_c8c6cd.te_fc0b6c as te_3445d9,
    chr(r_89c48c.i_current_price) as te_cc4c98 
FROM
    (SELECT
        r_c83a0b.cd_dep_college_count * 0 as te_fc0b6c,
        r_cb565e.i_units as te_c2bd08 
    FROM
        db1.item AS r_cb565e,
        db1.customer_demographics AS r_c83a0b 
    INNER JOIN
        db1.household_demographics AS r_f9b176 
            ON r_c83a0b.cd_dep_count != r_f9b176.hd_dep_count 
    WHERE
        r_cb565e.i_class_id <= r_c83a0b.cd_dep_count 
        AND r_cb565e.i_size LIKE r_cb565e.i_category 
        AND r_cb565e.i_class NOT ILIKE r_cb565e.i_item_desc 
        OR r_cb565e.i_rec_end_date = r_cb565e.i_rec_start_date 
        AND r_cb565e.i_rec_end_date != r_cb565e.i_rec_start_date 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST) AS r_c8c6cd 
RIGHT JOIN
    db1.date_dim AS r_c26b7a 
        ON r_c8c6cd.te_c2bd08 LIKE r_c26b7a.d_holiday,
    db1.item AS r_89c48c 
WHERE
    r_c26b7a.d_date <= r_89c48c.i_rec_end_date 
    OR r_89c48c.i_brand NOT LIKE 'C' 
    OR r_c26b7a.d_moy != 24 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC NULLS LAST;
----------->
WITH CTE_013bab(te_b37c17) AS (SELECT
    r_3fdf9a.s_rec_end_date as te_b37c17 
FROM
    (SELECT
        * 
    FROM
        db1.store PIVOT(min(s_manager) AS pa_e5fea7 FOR s_street_type IN (('izz') AS pf_6ba6b1,
        ('ezmSRI') AS pf_8976bd,
        ('C0l') AS pf_a92d7d,
        ('5G6sUgD') AS pf_b69873))) AS r_3fdf9a 
LEFT JOIN
    db1.web_site AS r_72ee05 
        ON r_3fdf9a.s_store_sk >= r_72ee05.web_open_date_sk 
WHERE
    r_72ee05.web_manager LIKE 's' 
ORDER BY
    1 NULLS LAST 
LIMIT 26), CTE_2a5a91(te_c446a1, te_638932) AS (SELECT
    r_8fb219.s_rec_end_date as te_c446a1, r_8fb219.s_suite_number as te_638932 FROM
        (SELECT
            * 
        FROM
            db1.store PIVOT(min(s_floor_space) AS pa_7ba370 FOR s_gmt_offset IN ((70.47797808) AS pf_068d5a,
            (87.98135573) AS pf_19f71b,
            (25.27559958) AS pf_8bd69c))) AS r_8fb219 
    INNER JOIN
        db1.store AS r_2864df 
            ON r_8fb219.s_company_name LIKE r_2864df.s_street_type,
        db1.warehouse AS r_84abe0 
    WHERE
        r_2864df.s_division_id = r_84abe0.w_warehouse_sk 
        OR r_2864df.s_rec_end_date > r_2864df.s_rec_start_date 
        OR r_2864df.s_geography_class != 'RQiizz2k' 
        OR r_84abe0.w_suite_number ILIKE '98h' 
    ORDER BY
        1 DESC, 2 DESC NULLS FIRST 
    LIMIT 99) SELECT
        r_a16cee.hd_buy_potential as te_09833a, r_fe5983.te_62b1ae / r_a16cee.hd_demo_sk * 3.61846261 as te_dbb13d, r_fe5983.te_fdacfe as te_4a10d1 FROM
            (SELECT
                r_78f1cc.wp_web_page_id as te_00eac9,
                r_78f1cc.wp_rec_end_date as te_fdacfe,
                r_d6db42.pf_2c2239 as te_62b1ae 
            FROM
                db1.web_page AS r_78f1cc 
            LEFT JOIN
                (
                    SELECT
                        * 
                    FROM
                        db1.store PIVOT(variance(s_market_id) AS pa_bce76e FOR s_company_name IN (('DCMaLQ') AS pf_2c2239,
                        ('z2kGk4el') AS pf_465ff6,
                        ('lBy7IG7i') AS pf_abae18,
                        ('Gv7RXpRQi') AS pf_e6d09a,
                        ('G6s') AS pf_363beb,
                        ('RQiizz2kG') AS pf_06b971))
                ) AS r_d6db42 
                    ON r_78f1cc.wp_rec_end_date < r_d6db42.s_rec_end_date,
                db1.time_dim AS r_f493d0 
            WHERE
                r_d6db42.s_division_id = r_f493d0.t_second 
                AND r_78f1cc.wp_web_page_id ILIKE r_d6db42.s_store_id 
                OR r_d6db42.pf_abae18 >= 8.72525879D 
                AND r_f493d0.t_minute <= r_78f1cc.wp_creation_date_sk 
                OR r_d6db42.s_store_id ILIKE r_78f1cc.wp_web_page_id 
            ORDER BY
                1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC 
            LIMIT 95) AS r_fe5983 INNER JOIN
                db1.household_demographics AS r_a16cee 
                    ON r_fe5983.te_fdacfe != date_from_unix_date(r_a16cee.hd_demo_sk),
                CTE_013bab AS r_390041 
            INNER JOIN
                (
                    SELECT
                        r_bd48cf.r_reason_sk as te_c971c6 
                    FROM
                        db1.reason AS r_bd48cf 
                    ORDER BY
                        1 NULLS LAST
                ) AS r_47727f 
                    ON r_390041.te_b37c17 != date_from_unix_date(r_47727f.te_c971c6) 
            WHERE
                r_fe5983.te_fdacfe <= r_390041.te_b37c17 
            ORDER BY
                1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_8044da.hd_buy_potential as te_0a541c 
FROM
    db1.household_demographics AS r_8044da 
WHERE
    r_8044da.hd_dep_count BETWEEN r_8044da.hd_income_band_sk AND 50 
ORDER BY
    1 DESC NULLS FIRST 
OFFSET 71;
----------->
SELECT
    r_a58dc8.sr_fee as te_d460a6 
FROM
    db1.time_dim AS r_e3de90 
INNER JOIN
    db1.store_returns AS r_a58dc8 
        ON r_e3de90.t_minute != r_a58dc8.sr_fee 
WHERE
    r_a58dc8.sr_return_tax < 41.0099152 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_0f75cc.wp_autogen_flag as te_413617,
    r_ef22e5.s_store_id as te_165912 
FROM
    db1.web_page AS r_0f75cc 
LEFT JOIN
    db1.store AS r_ef22e5 
        ON r_0f75cc.wp_link_count <= r_ef22e5.s_number_employees,
    db1.call_center AS r_50d8f2 
WHERE
    r_ef22e5.s_division_id != r_50d8f2.cc_call_center_sk 
    AND (
        NOT r_ef22e5.s_store_sk < r_50d8f2.cc_sq_ft 
        OR r_ef22e5.s_company_id < r_50d8f2.cc_employees
    ) 
    OR r_50d8f2.cc_mkt_id <= 90 
    OR r_ef22e5.s_store_id NOT LIKE 'FrTU' 
ORDER BY
    1, 2 NULLS LAST;
----------->
SELECT
    to_char(r_90fd56.t_hour,
    '999') as te_8a7e42,
    r_90fd56.t_time as te_ff8a11,
    r_90fd56.t_time / r_90fd56.t_second * to_char(r_90fd56.t_minute / 29,
    '000D00') as te_f209a2 
FROM
    db1.time_dim AS r_90fd56,
    db1.reason AS r_4d65bc 
WHERE
    r_90fd56.t_sub_shift NOT LIKE r_4d65bc.r_reason_id 
    AND r_90fd56.t_time_sk = (
        (
            SELECT
                r_58d1fc.d_date_sk as te_c25e75 
            FROM
                db1.item AS r_fc0d20,
                db1.date_dim AS r_58d1fc 
            INNER JOIN
                db1.warehouse AS r_9a009e 
                    ON r_58d1fc.d_qoy = r_9a009e.w_warehouse_sq_ft 
            WHERE
                r_fc0d20.i_container LIKE r_9a009e.w_state 
                AND r_fc0d20.i_manufact NOT LIKE 'XpRQiizz' 
            ORDER BY
                1 ASC NULLS LAST 
            LIMIT 1
    )
) ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_bc0289.ca_gmt_offset as te_3dec29,
    r_987dd3.cc_gmt_offset as te_afe229 
FROM
    db1.call_center AS r_987dd3 
INNER JOIN
    db1.customer_address AS r_bc0289 
        ON r_987dd3.cc_city NOT ILIKE r_bc0289.ca_street_number,
    (SELECT
        r_f8fe5f.hd_buy_potential as te_f0ccca 
    FROM
        db1.household_demographics AS r_f8fe5f 
    WHERE
        r_f8fe5f.hd_income_band_sk = 17 
        AND r_f8fe5f.hd_buy_potential NOT ILIKE 'px6kg7x2S' 
        OR r_f8fe5f.hd_dep_count >= 82 
        AND r_f8fe5f.hd_demo_sk != 54) AS r_887af8 
INNER JOIN
    db1.date_dim AS r_c45e86 
        ON r_887af8.te_f0ccca LIKE r_c45e86.d_weekend 
WHERE
    r_987dd3.cc_county ILIKE r_887af8.te_f0ccca 
    OR r_987dd3.cc_country NOT ILIKE r_987dd3.cc_name 
ORDER BY
    1 DESC, 2 ASC NULLS LAST;
----------->
SELECT
    r_e7de57.cc_closed_date_sk * 72.57682118D as te_1e780a 
FROM
    db1.call_center AS r_e7de57 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_2d2ed2(te_d9d1bf) AS (SELECT
    r_5f5383.s_gmt_offset as te_d9d1bf 
FROM
    db1.time_dim AS r_19e15b 
LEFT JOIN
    db1.store AS r_5f5383 
        ON r_19e15b.t_am_pm NOT LIKE r_5f5383.s_company_name 
WHERE
    r_19e15b.t_sub_shift ILIKE 'BRGC0l' 
    OR r_5f5383.s_company_id <= r_19e15b.t_time 
ORDER BY
    1 NULLS LAST), CTE_2eb22e(te_d91836, te_0cb266, te_fcb0f7) AS (SELECT
    r_923080.sr_return_amt_inc_tax as te_d91836, reflect('java.util.UUID', 'randomUUID') as te_0cb266, r_342385.pf_893e60 as te_fcb0f7 
FROM
    (SELECT
        * 
    FROM
        db1.call_center PIVOT(min(cc_rec_start_date) AS pa_2f86a8 FOR cc_division_name IN (('GIHlpx6kg') AS pf_893e60,
        ('ir98hv9') AS pf_a477df))) AS r_342385 
INNER JOIN
    db1.item AS r_97b114 
        ON r_342385.cc_division <= r_97b114.i_class_id,
    db1.reason AS r_56df24 
LEFT JOIN
    db1.store_returns AS r_923080 
        ON r_923080.sr_item_sk BETWEEN 66 AND r_923080.sr_return_tax + 61 
WHERE
    r_342385.cc_tax_percentage <= r_923080.sr_store_credit 
    OR r_923080.sr_ticket_number <= 45 
    OR r_97b114.i_item_desc NOT LIKE 'zz2kGk4e' 
    OR r_923080.sr_customer_sk IS NOT NULL 
    AND r_97b114.i_item_id >= r_342385.cc_call_center_id 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST) SELECT
        r_7d3287.ca_address_sk as te_f2a505, r_e40783.c_first_name as te_431726, r_7d3287.ca_city as te_51148c 
    FROM
        CTE_2d2ed2 AS r_166233 
    RIGHT JOIN
        CTE_2eb22e AS r_ab8bfc 
            ON r_166233.te_d9d1bf > r_ab8bfc.te_d91836,
        db1.customer AS r_e40783 
    LEFT JOIN
        db1.customer_address AS r_7d3287 
            ON r_e40783.c_first_shipto_date_sk != r_7d3287.ca_address_sk 
    WHERE
        r_e40783.c_last_review_date_sk IS NOT NULL 
        AND r_166233.te_d9d1bf > r_7d3287.ca_gmt_offset 
        AND r_e40783.c_customer_sk < 34 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC, 3 NULLS FIRST 
    LIMIT 29;
----------->
SELECT
    r_f95f2a.cd_dep_employed_count as te_a60239,
    r_f95f2a.cd_dep_count as te_242bf4 
FROM
    db1.customer_demographics AS r_f95f2a,
    db1.income_band AS r_0acc27 
WHERE
    r_f95f2a.cd_dep_employed_count > r_0acc27.ib_lower_bound 
    AND r_0acc27.ib_upper_bound < 10 
    AND r_f95f2a.cd_gender ILIKE 'IHl' 
    AND NOT r_0acc27.ib_upper_bound <= r_0acc27.ib_lower_bound 
    AND r_f95f2a.cd_dep_college_count < 66 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST 
OFFSET 96;
----------->
SELECT
    r_b9633c.s_tax_percentage as te_0c3045,
    r_b9633c.s_rec_end_date as te_4ab988,
    r_b9633c.s_gmt_offset as te_7fbd4d 
FROM
    db1.warehouse AS r_21cb7c,
    db1.income_band AS r_7186a3 
INNER JOIN
    db1.store AS r_b9633c 
        ON r_7186a3.ib_upper_bound != r_b9633c.s_floor_space 
WHERE
    r_21cb7c.w_warehouse_name NOT ILIKE r_b9633c.s_company_name 
    OR r_21cb7c.w_county NOT LIKE 'SW4Pg' 
    OR r_b9633c.s_market_id >= 88 
    AND r_b9633c.s_store_id ILIKE r_21cb7c.w_county 
    AND r_b9633c.s_market_id != 87 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
WITH CTE_af1f7c(te_ada946, te_57d9fa) AS (SELECT
    r_276faf.d_year as te_ada946,
    string(r_533a72.c_current_addr_sk) as te_57d9fa 
FROM
    db1.date_dim AS r_276faf,
    db1.customer AS r_533a72 
WHERE
    (NOT r_276faf.d_month_seq > r_533a72.c_current_addr_sk 
    OR r_276faf.d_quarter_seq > 66 
    AND r_533a72.c_preferred_cust_flag NOT ILIKE r_276faf.d_following_holiday 
    AND r_533a72.c_birth_year >= 38)), CTE_fd8bff(te_d6c023, te_7e5795, te_f1192f) AS (SELECT
    r_7a27ad.web_site_id as te_d6c023,
    r_93b599.s_gmt_offset as te_7e5795,
    unix_timestamp() as te_f1192f 
FROM
    db1.web_site AS r_7a27ad 
LEFT JOIN
    db1.store AS r_93b599 
        ON r_7a27ad.web_site_id NOT LIKE r_93b599.s_store_id,
    db1.reason AS r_58082c 
WHERE
    r_7a27ad.web_street_name LIKE r_58082c.r_reason_desc 
    AND r_7a27ad.web_street_name NOT ILIKE 'ntb2Gv7RX' 
    AND r_93b599.s_division_id = 58 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC) SELECT
    r_0d261c.te_f1192f as te_4f1b09 
FROM
    CTE_af1f7c AS r_2acb1c 
LEFT JOIN
    CTE_fd8bff AS r_0d261c 
        ON r_2acb1c.te_ada946 > r_0d261c.te_7e5795 
WHERE
    r_0d261c.te_f1192f <= 58 
    OR r_0d261c.te_7e5795 = 94.50335265 
    OR r_0d261c.te_7e5795 < 16.7367686 
    OR chr(r_0d261c.te_f1192f) NOT ILIKE r_0d261c.te_d6c023 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_f51c73.c_current_cdemo_sk as te_26e899 
FROM
    db1.customer AS r_f51c73 
WHERE
    r_f51c73.c_first_sales_date_sk = 41 
ORDER BY
    1 ASC;
----------->
SELECT
    r_45df62.sm_type as te_8e2fd6,
    try_add(r_136879.r_reason_id,
    r_d0da40.d_moy) as te_9a6cec,
    r_136879.r_reason_id as te_9c95fe 
FROM
    db1.ship_mode AS r_45df62,
    db1.reason AS r_136879 
RIGHT JOIN
    db1.date_dim AS r_d0da40 
        ON r_136879.r_reason_sk <= r_d0da40.d_first_dom 
WHERE
    r_45df62.sm_ship_mode_sk != r_d0da40.d_quarter_seq 
    AND r_136879.r_reason_id NOT LIKE r_45df62.sm_type 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC;
----------->
SELECT
    r_0114f8.sm_ship_mode_id as te_42514a 
FROM
    db1.reason AS r_f958e8 
INNER JOIN
    db1.ship_mode AS r_0114f8 
        ON r_f958e8.r_reason_sk < r_0114f8.sm_ship_mode_sk 
WHERE
    r_0114f8.sm_ship_mode_sk > 29 
ORDER BY
    1 NULLS LAST;
----------->
(
    SELECT
        hash(r_59767c.sm_carrier) as te_fb73f9,
        r_b9bcea.cd_credit_rating as te_ac235e 
    FROM
        db1.customer_demographics AS r_b9bcea,
        db1.ship_mode AS r_59767c 
    LEFT JOIN
        (
            SELECT
                r_3a9ae2.s_state as te_f10b32,
                r_43c737.wp_rec_end_date as te_bae23f 
            FROM
                db1.web_page AS r_43c737,
                db1.store AS r_3a9ae2 
            INNER JOIN
                db1.web_page AS r_62d9af 
                    ON r_3a9ae2.s_company_id != r_62d9af.wp_customer_sk 
            WHERE
                r_43c737.wp_web_page_id LIKE r_3a9ae2.s_store_id 
                AND r_3a9ae2.s_geography_class != 'ntb2G' 
                OR r_62d9af.wp_image_count != 61 
            ORDER BY
                1 NULLS LAST, 2 ASC
        ) AS r_db80c8 
            ON r_59767c.sm_carrier LIKE r_db80c8.te_f10b32 
    WHERE
        r_b9bcea.cd_dep_employed_count >= r_59767c.sm_ship_mode_sk 
        AND r_b9bcea.cd_dep_employed_count <= 13 
        AND r_b9bcea.cd_demo_sk = 39 
        AND r_59767c.sm_type NOT LIKE r_59767c.sm_carrier 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) 
    UNION
    ALL (
        SELECT
            r_bb3b6e.sr_cdemo_sk as te_745540,
            r_540a27.w_county as te_3a5d3c 
        FROM
            db1.reason AS r_21d361,
            db1.store_returns AS r_bb3b6e 
        LEFT JOIN
            db1.warehouse AS r_540a27 
                ON r_bb3b6e.sr_refunded_cash <= r_540a27.w_gmt_offset 
        WHERE
            r_21d361.r_reason_sk >= r_bb3b6e.sr_return_quantity 
            AND r_540a27.w_street_type LIKE r_540a27.w_zip 
            OR r_bb3b6e.sr_cdemo_sk != 50 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST
    ) 
ORDER BY
    1 NULLS LAST, 2 DESC;
----------->
SELECT
    r_7eb7a1.d_year as te_d6e48e 
FROM
    db1.income_band AS r_388600 
RIGHT JOIN
    db1.date_dim AS r_7eb7a1 
        ON r_388600.ib_income_band_sk < r_7eb7a1.d_same_day_lq 
WHERE
    r_388600.ib_lower_bound <= r_7eb7a1.d_fy_quarter_seq 
    AND r_7eb7a1.d_day_name ILIKE r_7eb7a1.d_holiday 
    AND r_7eb7a1.d_moy = r_7eb7a1.d_fy_week_seq 
    OR r_7eb7a1.d_current_year NOT ILIKE 'RGC0' 
ORDER BY
    1 DESC;
----------->
SELECT
    abs(positive(unix_timestamp())) as te_78e029,
    hash(r_7249a1.ca_state,
    false) as te_7090e7 
FROM
    (SELECT
        hash(try_add(r_867e90.cc_division,
        r_867e90.cc_rec_start_date),
        timestamp_seconds(mod(r_867e90.cc_mkt_id,
        83))) as te_c8e02b 
    FROM
        db1.call_center AS r_867e90 
    WHERE
        r_867e90.cc_company_name ILIKE ((SELECT
            r_cce1ed.i_product_name as te_e22bdc 
        FROM
            db1.item AS r_cce1ed 
        LEFT JOIN
            db1.web_page AS r_b97558 
                ON r_cce1ed.i_rec_start_date >= r_b97558.wp_rec_start_date,
            db1.income_band AS r_b4d671 
        INNER JOIN
            db1.household_demographics AS r_5b020a 
                ON r_b4d671.ib_income_band_sk <= r_5b020a.hd_vehicle_count 
        WHERE
            r_cce1ed.i_brand_id != r_b4d671.ib_lower_bound 
            AND r_b97558.wp_web_page_id NOT ILIKE r_cce1ed.i_item_id 
            AND r_cce1ed.i_category NOT ILIKE 'IvX' 
        ORDER BY
            1 DESC NULLS LAST 
        LIMIT 1)) 
    OR NOT EXISTS (
        SELECT
            r_088e66.cc_rec_end_date as te_c0c501 FROM
                db1.call_center AS r_088e66 
            INNER JOIN
                db1.store AS r_2af9a9 
                    ON r_088e66.cc_state NOT ILIKE r_2af9a9.s_division_name 
            WHERE
                r_2af9a9.s_country > 'zmS' 
                OR r_2af9a9.s_rec_start_date IN (
                    SELECT
                        DATE'2024-10-11' as te_ea550b 
                    FROM
                        db1.customer AS r_916210 
                    WHERE
                        (
                            NOT r_916210.c_preferred_cust_flag NOT LIKE 'BRG' 
                            AND r_916210.c_email_address ILIKE 'k4elBy'
                        ) 
                        AND r_916210.c_preferred_cust_flag ILIKE r_916210.c_customer_id 
                        AND r_916210.c_first_shipto_date_sk <= 82
                ) 
                AND r_2af9a9.s_state NOT ILIKE '2Gv' 
            GROUP BY
                1 
            ORDER BY
                1 ASC NULLS FIRST) 
                OR r_867e90.cc_company >= r_867e90.cc_mkt_id 
            GROUP BY
                1 
            ORDER BY
                1 DESC NULLS FIRST 
            OFFSET 31
    ) AS r_27495d, db1.customer_address AS r_7249a1 WHERE
        r_27495d.te_c8e02b > r_7249a1.ca_address_sk 
        OR r_7249a1.ca_street_number ILIKE 'U' 
        OR NOT r_7249a1.ca_state NOT ILIKE 'v95G6' 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
WITH CTE_f29f5e(te_7949de, te_4bfc4c, te_16c3b0) AS (SELECT
    r_41d943.cc_name as te_7949de,
    r_2cb835.wp_creation_date_sk as te_4bfc4c,
    r_b2c9ef.c_preferred_cust_flag as te_16c3b0 
FROM
    db1.customer AS r_b2c9ef 
RIGHT JOIN
    db1.call_center AS r_41d943 
        ON r_b2c9ef.c_last_name NOT ILIKE r_41d943.cc_hours,
    db1.web_page AS r_2cb835 
WHERE
    r_41d943.cc_rec_start_date <= r_2cb835.wp_rec_start_date 
GROUP BY
    1, 3, 2 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 NULLS LAST 
LIMIT 45) SELECT
r_f50bc4.sr_item_sk as te_40b630 FROM
    db1.store_returns AS r_f50bc4 
WHERE
    r_f50bc4.sr_return_time_sk != 18 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_17d5be.sr_refunded_cash as te_d32c62 
FROM
    (SELECT
        * 
    FROM
        db1.income_band PIVOT(stddev(ib_lower_bound) AS pa_d58c51 FOR ib_upper_bound IN ((47) AS pf_dee11e,
        (50) AS pf_81d3dc,
        (93) AS pf_34602a,
        (76) AS pf_cb4f38,
        (71) AS pf_a8c407,
        (89) AS pf_7a0289))) AS r_964dc2 
RIGHT JOIN
    db1.store_returns AS r_17d5be 
        ON r_964dc2.ib_income_band_sk = r_17d5be.sr_addr_sk 
ORDER BY
    1 ASC NULLS FIRST;
----------->
(
    SELECT
        r_8d160e.r_reason_id as te_bff6b6 
    FROM
        db1.income_band AS r_148883,
        db1.reason AS r_8d160e 
    LEFT JOIN
        (
            SELECT
                * 
            FROM
                db1.time_dim UNPIVOT ((up_868811,
                up_cb0ad6) FOR upn_5898ae IN ((t_time_id,
                t_time) AS UPF_9ee53b,
                (t_shift,
                t_time_sk) AS UPF_e25f96,
                (t_am_pm,
                t_minute) AS UPF_9f7800,
                (t_sub_shift,
                t_hour) AS UPF_6e8af1,
                (t_meal_time,
                t_second) AS UPF_52d555))
        ) AS r_5331bc 
            ON r_8d160e.r_reason_sk = r_5331bc.up_cb0ad6 
    WHERE
        r_148883.ib_upper_bound = r_8d160e.r_reason_sk 
    ORDER BY
        1 DESC NULLS LAST) EXCEPT ALL (SELECT
            r_ac69c0.w_street_name as te_291a9a 
        FROM
            db1.warehouse AS r_ac69c0 
        WHERE
            r_ac69c0.w_warehouse_name ILIKE 'RGC0lezmS' 
            AND r_ac69c0.w_street_type NOT ILIKE r_ac69c0.w_country 
        ORDER BY
            1 NULLS FIRST) 
    ORDER BY
        1 NULLS FIRST;
----------->
SELECT
    r_a36c9d.w_gmt_offset as te_b19942 
FROM
    db1.household_demographics AS r_7d659e 
INNER JOIN
    db1.warehouse AS r_a36c9d 
        ON r_7d659e.hd_dep_count > r_a36c9d.w_warehouse_sk 
WHERE
    r_a36c9d.w_city NOT LIKE r_a36c9d.w_country 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    character_length(r_3c28b5.s_state) as te_af411f,
    reflect('java.util.UUID',
    'randomUUID') as te_fa300a,
    r_3341fc.wp_char_count as te_1513fa 
FROM
    (SELECT
        r_210888.w_warehouse_sq_ft as te_d10c69,
        r_abb96e.t_time_id as te_a3dbbb 
    FROM
        db1.warehouse AS r_210888 
    RIGHT JOIN
        db1.time_dim AS r_abb96e 
            ON r_210888.w_street_number NOT LIKE r_abb96e.t_sub_shift,
        db1.household_demographics AS r_27ac69 
    WHERE
        r_210888.w_state LIKE r_27ac69.hd_buy_potential 
        OR r_210888.w_gmt_offset > 71.23264089 
        OR r_abb96e.t_minute != 12 
        OR r_abb96e.t_time_sk != 69 
    ORDER BY
        1 ASC NULLS LAST, 2) AS r_bbe5b7 
LEFT JOIN
    db1.web_page AS r_3341fc 
        ON r_bbe5b7.te_a3dbbb <= r_3341fc.wp_url,
    db1.store AS r_3c28b5 
RIGHT JOIN
    db1.income_band AS r_91f8eb 
        ON r_3c28b5.s_division_id != r_91f8eb.ib_upper_bound 
WHERE
    r_bbe5b7.te_a3dbbb ILIKE r_3c28b5.s_division_name 
    AND (
        NOT r_3341fc.wp_rec_end_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
        AND r_3c28b5.s_store_id LIKE r_3341fc.wp_autogen_flag
    ) 
    AND r_3341fc.wp_rec_start_date > DATE'2024-10-11' 
    OR r_3c28b5.s_division_name LIKE 'FrTUOVb' 
ORDER BY
    1, 2 NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    current_timestamp() as te_5f8c6d,
    sin(r_2439fb.sr_return_amt_inc_tax) as te_064990,
    r_49fd23.sr_refunded_cash as te_da9607 
FROM
    db1.store_returns AS r_2439fb,
    db1.store_returns AS r_49fd23 
WHERE
    r_2439fb.sr_return_ship_cost != r_49fd23.sr_refunded_cash 
    OR r_2439fb.web_county != r_49fd23.sr_return_ship_cost 
    AND r_49fd23.sr_addr_sk != 8 
    AND r_2439fb.sr_item_sk < r_49fd23.sr_return_quantity 
    OR r_2439fb.sr_refunded_cash = 82.83794019 
ORDER BY
    1, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    chr(r_fe3b49.w_warehouse_sk) as te_2b606c,
    r_fe3b49.w_city as te_1e7a25 
FROM
    db1.ship_mode AS r_7290a0,
    (SELECT
        now() as te_06646f 
    FROM
        db1.item AS r_c5a89f 
    WHERE
        r_c5a89f.i_item_sk <= r_c5a89f.i_class_id 
    ORDER BY
        1 DESC) AS r_90ef9c 
RIGHT JOIN
    db1.warehouse AS r_fe3b49 
        ON chr(char_length(string(r_90ef9c.te_06646f))) LIKE chr(character_length(r_fe3b49.w_street_name)) 
WHERE
    r_7290a0.sm_ship_mode_sk > r_fe3b49.w_warehouse_sq_ft 
    OR r_fe3b49.w_warehouse_sk = r_fe3b49.w_warehouse_sq_ft 
    OR (
        NOT r_fe3b49.w_warehouse_sq_ft != 40 
        AND r_fe3b49.w_street_number LIKE 'hv95'
    ) 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    unix_timestamp() as te_dbe3a9 
FROM
    db1.web_site AS r_1d5e62 
LEFT JOIN
    (
        SELECT
            current_timestamp() as te_f47437,
            r_a65261.pf_20c24a as te_86a1f2 
        FROM
            (SELECT
                * 
            FROM
                db1.customer_demographics PIVOT(sum(cd_demo_sk) AS pa_5cfb24 FOR cd_dep_college_count IN ((83) AS pf_20c24a,
                (77) AS pf_dfc6bd))) AS r_a65261 
        INNER JOIN
            db1.store_returns AS r_7fead3 
                ON r_a65261.cd_purchase_estimate < r_7fead3.sr_addr_sk,
            db1.web_site AS r_9a2a07 
        RIGHT JOIN
            (
                SELECT
                    r_84ca84.te_4b7dab as te_441215 
                FROM
                    (SELECT
                        r_16db51.i_wholesale_cost as te_4b7dab 
                    FROM
                        db1.item AS r_16db51 
                    RIGHT JOIN
                        (
                            SELECT
                                r_8670f1.w_warehouse_sk as te_159ed0 
                            FROM
                                db1.warehouse AS r_8670f1 
                            WHERE
                                r_8670f1.w_state NOT LIKE r_8670f1.w_warehouse_name 
                            GROUP BY
                                1 
                            ORDER BY
                                1
                        ) AS r_3d4553 
                            ON r_16db51.i_brand_id <= r_3d4553.te_159ed0 
                    WHERE
                        r_16db51.i_container LIKE 'ntb2' 
                        AND r_16db51.i_rec_end_date < r_16db51.i_rec_start_date 
                        OR r_16db51.i_rec_start_date = DATE'2024-10-11' 
                    ORDER BY
                        1 DESC NULLS LAST) AS r_84ca84 
                    WHERE
                        r_84ca84.te_4b7dab < 32.44441419 
                        OR r_84ca84.te_4b7dab <= 3.30578852 
                    ORDER BY
                        1 ASC NULLS FIRST) AS r_166719 
                        ON r_9a2a07.web_tax_percentage = r_166719.te_441215 
                WHERE
                    r_7fead3.sr_return_amt = r_9a2a07.web_tax_percentage 
                ORDER BY
                    1 DESC NULLS FIRST, 2 ASC) AS r_fc029d 
                        ON bigint(r_1d5e62.web_company_id) < r_fc029d.te_86a1f2 
                GROUP BY
                    1 
                ORDER BY
                    1 ASC NULLS FIRST 
                LIMIT 34;
----------->
(
    SELECT
        reverse(r_1e8247.cd_gender) as te_04959f 
    FROM
        db1.customer_demographics AS r_1e8247 
    WHERE
        r_1e8247.cd_dep_count >= r_1e8247.cd_dep_college_count 
        AND r_1e8247.cd_education_status ILIKE 'IG7ir' 
    ORDER BY
        1
) EXCEPT  (SELECT
    chr(r_81b3f9.ib_income_band_sk) as te_4e647a 
FROM
    (SELECT
        r_7a91e9.w_street_name as te_f1c8f8,
        r_3d4a5a.cd_gender as te_2be0e0,
        cos(r_3d4a5a.cd_demo_sk) as te_096333 
    FROM
        db1.customer_demographics AS r_3d4a5a,
        db1.warehouse AS r_7a91e9 
    LEFT JOIN
        db1.web_site AS r_7f80f1 
            ON r_7a91e9.w_zip ILIKE r_7f80f1.web_street_type 
    WHERE
        r_3d4a5a.cd_dep_employed_count < r_7f80f1.web_mkt_id 
        AND r_7f80f1.web_tax_percentage != r_7a91e9.w_gmt_offset 
        AND r_7f80f1.web_tax_percentage <= 83.14145643 
    ORDER BY
        1 ASC NULLS FIRST, 2 NULLS LAST, 3) AS r_23906c 
RIGHT JOIN
    db1.income_band AS r_81b3f9 
        ON r_23906c.te_2be0e0 NOT ILIKE chr(r_81b3f9.ib_upper_bound) 
WHERE
    r_81b3f9.ib_income_band_sk = r_81b3f9.ib_upper_bound 
    OR r_23906c.te_096333 >= 19.72857184D 
    AND r_81b3f9.ib_lower_bound < r_81b3f9.ib_income_band_sk 
    AND r_81b3f9.ib_upper_bound != r_81b3f9.ib_income_band_sk 
ORDER BY
    1 ASC) 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_69defc.i_container as te_6450bd 
FROM
    db1.item AS r_69defc 
RIGHT JOIN
    db1.customer_demographics AS r_5d6cc8 
        ON r_69defc.i_class_id >= r_5d6cc8.cd_dep_employed_count 
WHERE
    r_69defc.i_manager_id = r_69defc.i_category_id 
    OR r_69defc.i_class ILIKE 'r' 
    AND r_5d6cc8.cd_marital_status >= r_5d6cc8.cd_education_status 
    AND r_69defc.i_product_name NOT ILIKE r_69defc.i_color 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_48274e.sr_return_tax as te_7e0367,
    r_48274e.sr_return_amt as te_e2014d 
FROM
    db1.store_returns AS r_48274e,
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(count(sm_ship_mode_id) AS pa_e6b3a2 FOR (sm_carrier,
        sm_code) IN ((('4QH',
        'pRQi')) AS pf_30608e,
        (('px6kg',
        '4elBy7IG7')) AS pf_0cac23,
        (('v95G6sUg',
        'RIv')) AS pf_b17e26))) AS r_0b24d6 
RIGHT JOIN
    db1.customer AS r_1f129a 
        ON r_0b24d6.sm_ship_mode_sk = r_1f129a.c_current_hdemo_sk 
WHERE
    r_48274e.sr_item_sk > r_1f129a.c_first_sales_date_sk 
    OR r_48274e.sr_return_ship_cost > 66.38197489 
    AND r_1f129a.c_preferred_cust_flag NOT LIKE 'gD' 
    AND r_48274e.sr_cdemo_sk = 12 
    OR r_1f129a.c_first_shipto_date_sk >= 63 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC 
OFFSET 40;
----------->
SELECT
    r_2924b7.w_gmt_offset as te_8394a2,
    r_2924b7.w_warehouse_name as te_246e56,
    r_7cf621.c_customer_id as te_c58847 
FROM
    db1.warehouse AS r_2924b7,
    db1.customer AS r_7cf621 
WHERE
    r_2924b7.w_warehouse_id = r_7cf621.c_salutation 
ORDER BY
    1 NULLS FIRST, 2 ASC NULLS FIRST, 3 ASC NULLS LAST;
----------->
(
    SELECT
        2 as te_75acd9,
        mod(82,
        r_d5cad4.d_fy_week_seq) as te_ac21be,
        r_d5cad4.d_first_dom as te_ecce33 
    FROM
        db1.reason AS r_af2af3,
        db1.date_dim AS r_d5cad4 
    WHERE
        r_af2af3.r_reason_sk = r_d5cad4.d_week_seq 
        AND r_d5cad4.d_following_holiday = 'ir' 
        OR r_d5cad4.d_following_holiday LIKE 'kg' 
        OR r_d5cad4.d_fy_week_seq BETWEEN 38 AND 38 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS FIRST, 3 ASC NULLS FIRST
) 
UNION
ALL (
SELECT
    96 + r_5f6724.wp_creation_date_sk as te_d0b53b,
    r_506604.te_e2e60b as te_47c63d,
    r_5f6724.wp_web_page_sk as te_d4287d 
FROM
    (SELECT
        r_dc82e8.sm_ship_mode_sk as te_e2e60b 
    FROM
        db1.ship_mode AS r_dc82e8 
    WHERE
        r_dc82e8.sm_carrier >= 'SW' 
        AND r_dc82e8.sm_contract NOT LIKE 'z' 
    ORDER BY
        1 ASC NULLS LAST 
    OFFSET 45) AS r_506604 RIGHT JOIN
    db1.web_page AS r_5f6724 
        ON r_506604.te_e2e60b < r_5f6724.wp_image_count,
    db1.customer_demographics AS r_60e0e3 
RIGHT JOIN
    db1.ship_mode AS r_67901e 
        ON r_60e0e3.cd_credit_rating NOT ILIKE r_67901e.sm_type 
WHERE
    r_5f6724.wp_url ILIKE r_67901e.sm_carrier 
GROUP BY
    1, 3, 2 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST) 
ORDER BY
    1 DESC NULLS LAST, 2 ASC, 3 NULLS LAST;
----------->
SELECT
    r_9f9346.t_time_sk * r_1056b8.r_reason_sk as te_83140c 
FROM
    db1.reason AS r_1056b8 
LEFT JOIN
    db1.time_dim AS r_9f9346 
        ON r_1056b8.r_reason_desc NOT LIKE r_9f9346.t_am_pm 
ORDER BY
    1 DESC;
----------->
SELECT
    r_d571cc.sr_reversed_charge as te_df0c82 
FROM
    db1.store_returns AS r_d571cc 
WHERE
    r_d571cc.sr_fee < r_d571cc.sr_return_amt_inc_tax 
    OR r_d571cc.sr_return_amt < 21.72489971 
ORDER BY
    1 DESC 
LIMIT 27;
----------->
SELECT
    r_8ea53d.te_576563 as te_55f817,
    mod(r_19b66f.r_reason_sk + 1,
    r_8ea53d.te_576563 * r_8ea53d.te_576563 / r_8ea53d.te_576563) * sin(r_19b66f.r_reason_sk + 37) as te_0f12ce 
FROM
    (SELECT
        decimal(unix_timestamp()) as te_d056e7,
        r_1bee8e.w_gmt_offset as te_149080,
        r_70476e.d_fy_week_seq as te_576563 
    FROM
        (SELECT
            r_91488f.hd_buy_potential as te_77f918 
        FROM
            db1.household_demographics AS r_91488f 
        WHERE
            r_91488f.hd_demo_sk != 17 
            OR r_91488f.hd_income_band_sk <= r_91488f.hd_dep_count 
            AND r_91488f.hd_income_band_sk != 94 
            OR r_91488f.hd_demo_sk < 7 
        ORDER BY
            1 ASC NULLS FIRST) AS r_fa826b 
    INNER JOIN
        db1.warehouse AS r_1bee8e 
            ON r_fa826b.te_77f918 LIKE r_1bee8e.w_country,
        db1.date_dim AS r_70476e 
    WHERE
        r_1bee8e.w_warehouse_sq_ft > r_70476e.d_date_sk 
        OR r_70476e.d_quarter_seq > 37 
        AND r_70476e.d_holiday LIKE 'U' 
        AND r_70476e.d_moy = r_70476e.d_dom 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 NULLS LAST) AS r_8ea53d, db1.reason AS r_19b66f 
    WHERE
        r_8ea53d.te_576563 >= r_19b66f.r_reason_sk 
        AND r_8ea53d.te_d056e7 <= 53.15416554 
        AND r_19b66f.r_reason_id = '7RXpR' 
    ORDER BY
        1, 2 DESC NULLS FIRST;
----------->
SELECT
    r_aaa3f9.ib_income_band_sk as te_56e5bd,
    r_aaa3f9.ib_upper_bound as te_aeb3c8,
    r_99394a.up_04d473 as te_a75b75 
FROM
    db1.income_band AS r_aaa3f9,
    (SELECT
        * 
    FROM
        db1.reason UNPIVOT INCLUDE NULLS ((up_55a03b,
        up_04d473) FOR upn_405669 IN ((r_reason_sk,
        r_reason_id) AS UPF_08883b))) AS r_99394a 
WHERE
    r_aaa3f9.ib_upper_bound >= r_99394a.up_55a03b 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3;
----------->
SELECT
    r_d2d483.web_site_id as te_4afe07 
FROM
    db1.time_dim AS r_23e24f 
RIGHT JOIN
    db1.web_site AS r_d2d483 
        ON r_23e24f.t_shift NOT LIKE r_d2d483.web_mkt_desc 
WHERE
    r_d2d483.web_rec_start_date = r_d2d483.web_rec_end_date 
    OR r_d2d483.web_tax_percentage > r_d2d483.web_gmt_offset 
    AND r_d2d483.web_open_date_sk = r_d2d483.web_mkt_id 
    OR r_d2d483.web_street_number LIKE 'zz2kGk4' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    chr(r_810f93.cc_tax_percentage) as te_197da9,
    r_810f93.cc_rec_end_date as te_3ad045,
    r_6974dc.wp_image_count as te_11ccff 
FROM
    db1.call_center AS r_810f93 
INNER JOIN
    db1.customer_demographics AS r_9db0fd 
        ON r_810f93.cc_employees = r_9db0fd.cd_dep_college_count,
    db1.web_page AS r_6974dc 
WHERE
    r_810f93.cc_street_type NOT ILIKE r_6974dc.wp_web_page_id 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_38d7f6.cc_tax_percentage as te_559b79,
    r_2af62c.s_market_desc as te_f9bb9f,
    r_97e087.hd_buy_potential as te_6ea6d3 
FROM
    db1.call_center AS r_38d7f6 
INNER JOIN
    db1.store AS r_2af62c 
        ON r_38d7f6.cc_call_center_id ILIKE r_2af62c.s_store_id,
    db1.household_demographics AS r_97e087 
WHERE
    r_38d7f6.cc_employees < r_97e087.hd_dep_count 
    OR r_38d7f6.cc_call_center_id LIKE r_2af62c.s_store_id 
    OR r_38d7f6.cc_division >= 81 
ORDER BY
    1, 2 ASC NULLS LAST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_d027bc.i_size as te_11fdbf,
    count(r_92e74b.te_53b968) as te_430a50 
FROM
    db1.web_page AS r_890e5e 
RIGHT JOIN
    (
        SELECT
            r_de69ca.i_wholesale_cost as te_53b968,
            r_de69ca.i_current_price as te_262971,
            make_date(r_de69ca.i_class_id,
            r_d8c871.te_385312,
            r_d8c871.te_385312) as te_413569 
        FROM
            (SELECT
                r_b9beb1.d_week_seq as te_385312 
            FROM
                db1.date_dim AS r_b9beb1 
            WHERE
                r_b9beb1.d_current_year LIKE r_b9beb1.d_current_day 
                OR r_b9beb1.d_month_seq < 8) AS r_d8c871,
            db1.item AS r_de69ca 
        WHERE
            r_d8c871.te_385312 = r_de69ca.i_category_id 
            OR r_de69ca.i_wholesale_cost != r_de69ca.i_current_price 
            OR r_de69ca.i_category NOT LIKE '6kg7x2' 
            AND r_de69ca.i_category_id > r_de69ca.i_manufact_id 
            OR r_de69ca.i_brand_id != 56 
        ORDER BY
            1, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_92e74b 
                ON r_890e5e.wp_rec_start_date <= r_92e74b.te_413569, db1.item AS r_d027bc 
        INNER JOIN
            (
                SELECT
                    28.29836249 - r_7f15b2.te_bcb136 as te_f01fe7 
                FROM
                    (SELECT
                        r_a6422a.c_preferred_cust_flag as te_e79263,
                        r_a6422a.c_current_cdemo_sk as te_bcb136 
                    FROM
                        db1.customer AS r_a6422a,
                        db1.ship_mode AS r_7b7d3e 
                    WHERE
                        r_a6422a.c_current_hdemo_sk < r_7b7d3e.sm_ship_mode_sk 
                        AND r_a6422a.c_first_sales_date_sk < r_a6422a.c_birth_month 
                        OR r_7b7d3e.sm_type NOT ILIKE 'e' 
                    ORDER BY
                        1 ASC NULLS LAST, 2 ASC 
                    OFFSET 86) AS r_7f15b2 WHERE
                    chr(r_7f15b2.te_bcb136) LIKE r_7f15b2.te_e79263 
                    AND r_7f15b2.te_e79263 NOT LIKE 'aLQ' 
                    AND r_7f15b2.te_e79263 ILIKE 'Q' 
                ORDER BY
                    1 DESC NULLS LAST) AS r_8ac5c4 
                        ON r_d027bc.i_current_price >= r_8ac5c4.te_f01fe7 
                WHERE
                    r_890e5e.wp_type LIKE r_d027bc.i_brand 
                    AND r_8ac5c4.te_f01fe7 > r_92e74b.te_262971 
                    AND r_890e5e.wp_access_date_sk != 7 
                GROUP BY
                    r_d027bc.i_size 
                ORDER BY
                    1 ASC, 2 NULLS LAST;
----------->
SELECT
    r_bb48be.cc_rec_start_date as te_93038d,
    r_bb48be.cc_call_center_id as te_a7adee 
FROM
    db1.time_dim AS r_819af4,
    db1.call_center AS r_bb48be 
INNER JOIN
    db1.customer_demographics AS r_e8ec36 
        ON r_bb48be.cc_call_center_id NOT LIKE r_e8ec36.cd_marital_status 
WHERE
    r_819af4.t_time_sk > r_e8ec36.cd_dep_employed_count 
    OR r_bb48be.cc_suite_number LIKE r_819af4.t_time_id 
    OR r_819af4.t_meal_time NOT LIKE 'XpRQiiz' 
    AND (
        NOT r_819af4.t_shift ILIKE 'stntb2' 
        AND r_bb48be.cc_gmt_offset >= r_bb48be.cc_tax_percentage
    ) 
GROUP BY
    2, 1 
ORDER BY
    1 DESC, 2 ASC NULLS FIRST;
----------->
SELECT
    r_03bee7.cc_division as te_42b26d 
FROM
    db1.item AS r_359588 
INNER JOIN
    db1.call_center AS r_03bee7 
        ON r_359588.i_rec_end_date = r_03bee7.cc_rec_start_date 
WHERE
    r_03bee7.cc_gmt_offset < r_359588.i_current_price 
    OR r_03bee7.cc_rec_end_date < r_359588.i_rec_end_date 
    AND r_03bee7.cc_closed_date_sk = r_03bee7.cc_mkt_id 
ORDER BY
    1 NULLS LAST 
OFFSET 13;
----------->
SELECT
    r_cf0b11.ca_zip as te_9f1a5b,
    r_cf0b11.ca_street_name as te_fc742b 
FROM
    db1.income_band AS r_bdc4c6 
RIGHT JOIN
    db1.income_band AS r_621698 
        ON r_bdc4c6.ib_upper_bound < r_621698.ib_lower_bound,
    db1.customer_address AS r_cf0b11 
INNER JOIN
    db1.warehouse AS r_5b3fb5 
        ON r_cf0b11.ca_country ILIKE r_5b3fb5.w_zip 
WHERE
    r_621698.ib_lower_bound <= r_5b3fb5.w_warehouse_sk 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_65c8ea.ca_gmt_offset as te_1f0820 
FROM
    db1.customer_address AS r_65c8ea 
ORDER BY
    1 NULLS LAST 
LIMIT 45;
----------->
SELECT
    r_9a82e7.c_customer_id as te_3c5ad9,
    pi() as te_b9097b 
FROM
    db1.store AS r_28a9e8,
    (SELECT
        r_80512c.te_bddc53 as te_5c39d5 
    FROM
        db1.reason AS r_073ea5 
    LEFT JOIN
        (
            SELECT
                r_b42cc9.s_store_id as te_bddc53 
            FROM
                db1.store AS r_b42cc9 
            WHERE
                r_b42cc9.s_street_type NOT LIKE 'ls' 
                AND r_b42cc9.s_hours NOT LIKE 'Vb' 
            ORDER BY
                1 DESC 
            OFFSET 83
    ) AS r_80512c 
        ON r_073ea5.r_reason_id NOT ILIKE string(r_80512c.te_bddc53) WHERE
            r_80512c.te_bddc53 LIKE 'gDDXX' 
            AND r_073ea5.r_reason_id NOT ILIKE r_073ea5.r_reason_desc 
            AND r_073ea5.r_reason_id NOT ILIKE r_073ea5.r_reason_desc 
            AND r_073ea5.r_reason_id NOT LIKE 'g7x2SW4' 
    ORDER BY
        1 NULLS FIRST) AS r_e6f0a2 
    LEFT JOIN
        db1.customer AS r_9a82e7 
            ON r_e6f0a2.te_5c39d5 NOT LIKE r_9a82e7.c_preferred_cust_flag 
    WHERE
        r_28a9e8.s_market_manager NOT ILIKE r_9a82e7.c_last_name 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    negative(unix_timestamp()) as te_c7a236,
    r_818833.wp_rec_end_date as te_739e93 
FROM
    db1.web_page AS r_818833 
LEFT JOIN
    db1.warehouse AS r_dc058a 
        ON r_818833.wp_type NOT LIKE r_dc058a.w_country,
    db1.customer_address AS r_54fc65 
WHERE
    r_dc058a.w_county ILIKE r_54fc65.ca_location_type 
    AND r_54fc65.ca_street_type LIKE 'XpR' 
    OR r_818833.wp_web_page_id LIKE 'r9' 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_cd2244.d_moy as te_d74713,
    r_8fb6cd.web_class as te_fc0952 
FROM
    db1.web_site AS r_8fb6cd 
INNER JOIN
    db1.date_dim AS r_cd2244 
        ON r_8fb6cd.web_rec_end_date = r_cd2244.d_date,
    db1.web_page AS r_bdcb19 
INNER JOIN
    db1.customer_demographics AS r_f8df99 
        ON r_bdcb19.wp_web_page_id LIKE r_f8df99.cd_gender 
WHERE
    r_cd2244.d_date = r_bdcb19.wp_rec_start_date 
ORDER BY
    1 ASC, 2 DESC NULLS LAST;
----------->
SELECT
    r_a74ac1.cc_rec_start_date as te_c72333,
    date_add(r_a74ac1.cc_rec_end_date,
    r_a74ac1.cc_closed_date_sk) as te_366086,
    hash(53 + r_a74ac1.cc_open_date_sk) as te_8c885a 
FROM
    db1.call_center AS r_a74ac1,
    db1.household_demographics AS r_f5640f 
WHERE
    r_a74ac1.cc_hours LIKE r_f5640f.hd_buy_potential 
    OR r_a74ac1.cc_manager ILIKE r_a74ac1.cc_country 
    OR r_a74ac1.cc_mkt_id >= r_f5640f.hd_income_band_sk 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_ed4e34.te_72d49e as te_2ae8fa 
FROM
    (SELECT
        r_492744.wp_web_page_id || r_492744.wp_type as te_72d49e 
    FROM
        db1.web_page AS r_492744 
    WHERE
        r_492744.wp_rec_start_date >= DATE'2024-03-25' 
        AND r_492744.wp_autogen_flag LIKE r_492744.wp_web_page_id 
        AND r_492744.wp_web_page_sk = r_492744.wp_max_ad_count 
    ORDER BY
        1 NULLS FIRST 
    LIMIT 40) AS r_ed4e34 WHERE
    r_ed4e34.te_72d49e NOT ILIKE 'HZgGIHlp' 
    OR r_ed4e34.te_72d49e ILIKE r_ed4e34.te_72d49e 
    OR r_ed4e34.te_72d49e LIKE 'X4QHZgGIHl' 
    AND r_ed4e34.te_72d49e LIKE 'ulst' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    hash(r_defd35.i_wholesale_cost,
    true) as te_9fc7ff,
    r_defd35.i_item_id as te_c67d48 
FROM
    db1.household_demographics AS r_c594bb,
    db1.item AS r_defd35 
WHERE
    r_c594bb.hd_buy_potential LIKE r_defd35.i_class 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    chr(r_142b0f.sr_customer_sk) as te_61d2c0 
FROM
    db1.store_returns AS r_142b0f 
WHERE
    r_142b0f.sr_reason_sk < r_142b0f.sr_return_time_sk 
    OR r_142b0f.sr_reason_sk != 27 
    AND r_142b0f.sr_net_loss > r_142b0f.sr_refunded_cash 
    OR r_142b0f.sr_hdemo_sk > 93 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    (45 + r_ca49ca.cd_dep_employed_count) / (r_ca49ca.cd_demo_sk - 48.07372159D) as te_f8ba65,
    r_871d2d.te_b1f6e2 as te_7d97c5,
    r_871d2d.te_b1f6e2 as te_3da38a 
FROM
    (SELECT
        make_date(r_494c28.c_customer_sk,
        r_c9d5e1.i_class_id,
        r_494c28.c_customer_sk) as te_b1f6e2,
        r_c9d5e1.i_manufact_id as te_7db692,
        r_e476d8.t_minute as te_6471cc 
    FROM
        db1.item AS r_c9d5e1 
    INNER JOIN
        db1.time_dim AS r_e476d8 
            ON r_c9d5e1.i_units LIKE r_e476d8.t_meal_time,
        db1.customer AS r_494c28 
    WHERE
        r_c9d5e1.i_manufact_id <= r_494c28.c_current_hdemo_sk 
        AND r_494c28.c_preferred_cust_flag LIKE '2SW4P' 
        AND r_494c28.c_login ILIKE 'zmSR' 
        OR r_c9d5e1.i_rec_end_date >= r_c9d5e1.i_rec_start_date 
        AND r_e476d8.t_meal_time NOT LIKE '7ir98hv95' 
    ORDER BY
        1, 2 DESC NULLS LAST, 3 DESC) AS r_871d2d, db1.customer_demographics AS r_ca49ca 
WHERE
    r_871d2d.te_6471cc < r_ca49ca.cd_dep_college_count 
    OR r_ca49ca.cd_education_status NOT LIKE 'V' 
    AND r_ca49ca.cd_purchase_estimate >= r_ca49ca.cd_dep_count 
ORDER BY
    1 ASC, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_c6f8a7.cd_education_status as te_a513a6,
    cos(r_71dd96.w_gmt_offset) + 12 + r_c6f8a7.cd_dep_employed_count as te_ab3599,
    r_c6f8a7.cd_demo_sk as te_b5e236 
FROM
    db1.customer_demographics AS r_c6f8a7,
    db1.time_dim AS r_d1f6d1 
INNER JOIN
    db1.warehouse AS r_71dd96 
        ON r_d1f6d1.t_time != r_71dd96.w_warehouse_sq_ft 
WHERE
    r_c6f8a7.cd_gender NOT LIKE r_71dd96.w_county 
    OR r_c6f8a7.cd_purchase_estimate > 51 
    AND (
        NOT r_71dd96.w_city NOT ILIKE 'SW4' 
        OR r_c6f8a7.cd_marital_status LIKE r_71dd96.w_county
    ) 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 DESC;
----------->
SELECT
    r_e5ba32.up_b7c7d0 as te_13a6d7 
FROM
    db1.reason AS r_4d5e66 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.store UNPIVOT INCLUDE NULLS ((up_61e626,
            up_b7c7d0,
            up_7a87b6,
            up_f5e29f,
            up_0b1051) FOR upn_883f82 IN ((s_gmt_offset,
            s_store_id,
            s_geography_class,
            s_company_id,
            s_rec_end_date) AS UPF_41a5ba))
    ) AS r_e5ba32 
        ON r_4d5e66.r_reason_desc = r_e5ba32.s_manager 
WHERE
    r_e5ba32.s_hours NOT LIKE r_e5ba32.s_store_name 
    AND r_e5ba32.up_61e626 < 8.04156431 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 23;
----------->
SELECT
    r_f0700f.ca_address_id as te_69ed7c 
FROM
    db1.customer_address AS r_f0700f 
WHERE
    r_f0700f.ca_county NOT LIKE r_f0700f.ca_city 
    AND r_f0700f.ca_location_type NOT ILIKE 'x2SW4' 
    OR r_f0700f.ca_address_sk < 25 
ORDER BY
    1 DESC NULLS LAST 
LIMIT 62;
----------->
SELECT
    r_025ec2.cd_dep_college_count as te_7ae4fd 
FROM
    db1.customer_demographics AS r_025ec2 
LEFT JOIN
    db1.customer_address AS r_9cc15e 
        ON r_025ec2.cd_dep_count != r_9cc15e.ca_address_sk 
WHERE
    (
        NOT r_9cc15e.ca_street_type NOT LIKE 'aLQ' 
        OR r_025ec2.cd_credit_rating NOT ILIKE 'RXp' 
        OR r_9cc15e.ca_address_id LIKE 'QHZg' 
        AND r_9cc15e.ca_city NOT ILIKE '4QHZgG'
    ) 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_d44141.c_email_address as te_77564c,
    r_b720a7.d_current_month as te_5a30f6 
FROM
    db1.customer AS r_d44141 
INNER JOIN
    db1.date_dim AS r_b720a7 
        ON r_d44141.c_customer_id NOT ILIKE r_b720a7.d_date_id,
    (WITH CTE_7307e7(te_fd0bc2,
    te_fb611a,
    te_64f749) AS (SELECT
        r_a81404.cc_mkt_class as te_fd0bc2,
        r_306929.sr_net_loss as te_fb611a,
        r_306929.sr_return_amt as te_64f749 
    FROM
        db1.store_returns AS r_306929 
    RIGHT JOIN
        db1.warehouse AS r_2df710 
            ON r_306929.sr_return_amt_inc_tax > r_2df710.w_gmt_offset,
        db1.call_center AS r_a81404 
    LEFT JOIN
        db1.customer_demographics AS r_065807 
            ON r_a81404.cc_call_center_id NOT ILIKE r_065807.cd_marital_status 
    WHERE
        r_306929.sr_store_sk = r_065807.cd_dep_employed_count 
        AND r_a81404.cc_rec_end_date > r_a81404.cc_rec_start_date 
        OR r_065807.cd_dep_college_count != 55),
    CTE_d594a5(te_47745e) AS (SELECT
        r_384f27.te_1a09f7 as te_47745e 
    FROM
        (SELECT
            sin(r_c9da12.s_store_sk) as te_1a09f7 
        FROM
            db1.store AS r_c9da12 
        WHERE
            r_c9da12.s_rec_end_date = r_c9da12.s_rec_start_date 
        ORDER BY
            1 ASC NULLS FIRST) AS r_384f27 
    WHERE
        r_384f27.te_1a09f7 <= r_384f27.te_1a09f7 
        OR r_384f27.te_1a09f7 IS NULL 
        OR r_384f27.te_1a09f7 != r_384f27.te_1a09f7 
    ORDER BY
        1 NULLS FIRST) SELECT
            r_44fc2d.i_brand_id as te_a361ff, current_date() as te_535c04, r_44fc2d.i_rec_start_date as te_b8771d 
        FROM
            CTE_7307e7 AS r_445e4c 
        RIGHT JOIN
            db1.item AS r_44fc2d 
                ON r_445e4c.te_fd0bc2 ILIKE r_44fc2d.i_category,
            CTE_d594a5 AS r_4b15d4 
        INNER JOIN
            db1.web_page AS r_e4740e 
                ON chr(r_4b15d4.te_47745e) ILIKE r_e4740e.wp_url 
        WHERE
            r_44fc2d.i_wholesale_cost >= r_e4740e.wp_image_count 
        ORDER BY
            1 DESC, 2 NULLS LAST, 3 DESC NULLS FIRST) AS r_3063a0 
    WHERE
        r_d44141.c_current_cdemo_sk = r_3063a0.te_a361ff 
        OR r_b720a7.d_holiday LIKE 'V' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_4ceeea.sr_addr_sk as te_71b519 
FROM
    db1.store_returns AS r_4ceeea 
WHERE
    r_4ceeea.sr_store_credit > 75.73145965 
    AND r_4ceeea.sr_returned_date_sk != r_4ceeea.sr_hdemo_sk 
    AND r_4ceeea.sr_fee != r_4ceeea.sr_refunded_cash 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        r_71cc34.sm_type as te_6bb85f 
    FROM
        db1.ship_mode AS r_71cc34 
    RIGHT JOIN
        db1.reason AS r_80b55c 
            ON r_71cc34.sm_ship_mode_sk = r_80b55c.r_reason_sk 
    WHERE
        r_71cc34.sm_ship_mode_sk < 32 
        OR r_71cc34.sm_ship_mode_id LIKE 'v7' 
        AND r_80b55c.r_reason_sk <= r_71cc34.sm_ship_mode_sk 
        AND r_71cc34.sm_ship_mode_id LIKE 'UOVbBRG' 
    ORDER BY
        1 NULLS LAST
) 
UNION
SELECT
'izz2kGk4' as te_f7380f 
FROM
db1.store_returns AS r_89d217 
WHERE
r_89d217.web_county > 94.07810456 
OR r_89d217.sr_hdemo_sk < 74 
AND r_89d217.sr_return_amt_inc_tax < 87.98442931 
OR r_89d217.sr_refunded_cash != r_89d217.sr_fee 
ORDER BY
1 ASC NULLS LAST 
OFFSET 3;
----------->
SELECT
    r_9d870a.c_last_review_date_sk - 2 as te_05a46a,
    current_timestamp() as te_049125,
    r_11d4cf.cd_dep_employed_count as te_ee2059 
FROM
    db1.customer_demographics AS r_11d4cf 
LEFT JOIN
    (
        SELECT
            r_b69cc5.c_first_sales_date_sk as te_4fa0d7,
            r_67c0d9.wp_autogen_flag as te_2c70cf,
            r_789606.w_warehouse_name as te_cc5600 
        FROM
            db1.web_page AS r_67c0d9,
            db1.customer AS r_b69cc5 
        INNER JOIN
            db1.warehouse AS r_789606 
                ON r_b69cc5.c_preferred_cust_flag LIKE r_789606.w_county 
        WHERE
            r_67c0d9.wp_char_count > r_b69cc5.c_birth_month 
            OR r_789606.w_warehouse_id LIKE '4elB' 
            AND NOT r_789606.w_city LIKE r_789606.w_zip 
            OR r_b69cc5.c_birth_month >= 24 
            OR r_b69cc5.c_customer_id NOT ILIKE '6sUgD' 
        ORDER BY
            1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST
    ) AS r_310a53 
        ON r_11d4cf.cd_marital_status LIKE r_310a53.te_cc5600,
    db1.customer AS r_9d870a 
WHERE
    r_11d4cf.cd_dep_count = r_9d870a.c_birth_day 
    AND r_9d870a.c_first_sales_date_sk = r_310a53.te_4fa0d7 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_cd652d.ca_city as te_b2ce69 
FROM
    db1.item AS r_a3b8aa 
INNER JOIN
    db1.customer_address AS r_cd652d 
        ON r_a3b8aa.i_size ILIKE r_cd652d.ca_country 
GROUP BY
    1 
ORDER BY
    1 NULLS FIRST 
LIMIT 81;
----------->
SELECT
    r_e8a680.cc_rec_end_date as te_53bb59,
    current_date() as te_f52d4d,
    r_e8a680.cc_gmt_offset + ((SELECT
        84 as te_768b38 
    FROM
        db1.web_site AS r_5dcc23 
    WHERE
        r_5dcc23.web_name LIKE 'zmSRIvX' 
    ORDER BY
        1 ASC 
    LIMIT 1)) - r_e8a680.cc_closed_date_sk as te_927aea FROM
    db1.time_dim AS r_770881 
LEFT JOIN
    db1.call_center AS r_e8a680 
        ON r_770881.t_time_sk > r_e8a680.cc_mkt_id,
    (SELECT
        chr(r_efc6d8.r_reason_sk) as te_f60bfa,
        r_9955cb.i_container as te_a3cdda 
    FROM
        db1.item AS r_9955cb 
    LEFT JOIN
        db1.income_band AS r_46c25b 
            ON r_9955cb.i_brand_id > r_46c25b.ib_upper_bound,
        db1.reason AS r_efc6d8 
    LEFT JOIN
        (
            SELECT
                r_c53b4f.ca_address_id as te_29186f 
            FROM
                db1.customer_address AS r_5907a8 
            INNER JOIN
                db1.customer_address AS r_c53b4f 
                    ON r_5907a8.ca_address_sk = r_c53b4f.ca_address_sk 
            WHERE
                r_c53b4f.ca_address_sk BETWEEN r_c53b4f.ca_address_sk - 20 AND 38 
            ORDER BY
                1 ASC NULLS LAST
        ) AS r_1cbdba 
            ON chr(r_efc6d8.r_reason_sk) LIKE r_1cbdba.te_29186f 
    WHERE
        r_46c25b.ib_upper_bound > r_efc6d8.r_reason_sk 
        OR r_46c25b.ib_lower_bound = r_9955cb.i_item_sk 
        AND r_9955cb.i_manufact_id = 95 
        OR r_9955cb.i_category_id < 71 
        OR r_9955cb.i_rec_start_date < r_9955cb.i_rec_end_date 
    ORDER BY
        1 DESC, 2 ASC) AS r_4826b0 
    LEFT JOIN
        db1.customer AS r_e3fbd9 
            ON r_4826b0.te_a3cdda ILIKE r_e3fbd9.c_customer_id 
    WHERE
        r_770881.t_time_sk != r_e3fbd9.c_birth_day 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_63170b.cd_purchase_estimate as te_f77c87 
FROM
    db1.customer_demographics AS r_63170b 
INNER JOIN
    db1.warehouse AS r_1cebb2 
        ON r_63170b.cd_education_status ILIKE r_1cebb2.w_county 
ORDER BY
    1 DESC;
----------->
SELECT
    bigint(double(r_d43c6b.web_close_date_sk)) + bigint(r_d43c6b.web_mkt_id / 42.70224977D) as te_f9ad26 
FROM
    db1.customer AS r_a35b29 
RIGHT JOIN
    db1.web_site AS r_d43c6b 
        ON r_a35b29.c_preferred_cust_flag ILIKE r_d43c6b.web_state 
WHERE
    r_d43c6b.web_rec_start_date = r_d43c6b.web_rec_end_date 
    AND r_a35b29.c_birth_month != 61 
    OR r_d43c6b.web_company_id <= r_a35b29.c_birth_day 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_20e6a1.ca_street_name as te_4c0326,
    r_195237.cd_education_status as te_e1a3a9,
    r_20e6a1.ca_gmt_offset * r_20e6a1.pf_8aa93e as te_ebc710 
FROM
    db1.customer_demographics AS r_195237 
RIGHT JOIN
    db1.income_band AS r_13486d 
        ON r_195237.cd_dep_count >= r_13486d.ib_lower_bound,
    (SELECT
        * 
    FROM
        db1.customer_address PIVOT(skewness(ca_address_sk) AS pa_7c18cf FOR ca_county IN (('Gv7') AS pf_d4a86e,
        ('Uulstntb') AS pf_8aa93e,
        ('4P') AS pf_85ba55,
        ('2kG') AS pf_42f591,
        ('6kg7') AS pf_688f6f,
        ('tb2Gv7RXp') AS pf_4917a1))) AS r_20e6a1 
RIGHT JOIN
    db1.date_dim AS r_6f125a 
        ON r_20e6a1.ca_address_id NOT LIKE r_6f125a.d_quarter_name 
WHERE
    r_195237.cd_purchase_estimate < r_6f125a.d_dom 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST, 3 NULLS LAST;
----------->
WITH CTE_dbf186(te_723698, te_3174fb, te_b2bc79) AS (SELECT
    5.76695477 * r_76cda9.wp_image_count as te_723698,
    r_76cda9.wp_web_page_id as te_3174fb,
    make_date(r_76cda9.wp_max_ad_count,
    r_76cda9.wp_image_count,
    r_76cda9.wp_link_count) as te_b2bc79 
FROM
    db1.time_dim AS r_661116 
RIGHT JOIN
    (SELECT
        make_date(hash(r_945884.w_warehouse_sq_ft,
        false),
        r_f0983f.i_item_sk,
        r_f0983f.i_manager_id) as te_644f9d,
        r_f0983f.i_rec_start_date as te_6f7882 
    FROM
        db1.warehouse AS r_945884,
        db1.item AS r_f0983f 
    WHERE
        r_945884.w_warehouse_sq_ft != r_f0983f.i_class_id 
        OR r_f0983f.i_item_desc LIKE 'HZgGIHlpx' 
        AND r_945884.w_county ILIKE 'aLQ' 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST) AS r_71de2f 
        ON chr(character_length(r_661116.t_meal_time)) NOT ILIKE chr(year(r_71de2f.te_644f9d)),
    db1.web_page AS r_76cda9 
WHERE
    r_661116.t_minute >= r_76cda9.wp_image_count 
    AND r_71de2f.te_6f7882 > r_76cda9.wp_rec_end_date 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC) SELECT
        r_40fcd6.c_preferred_cust_flag as te_975f7f, r_d812c3.te_3174fb as te_e2a3cb 
    FROM
        CTE_dbf186 AS r_d812c3,
        db1.item AS r_ba632b 
    LEFT JOIN
        db1.customer AS r_40fcd6 
            ON r_ba632b.i_class_id < r_40fcd6.c_current_cdemo_sk 
    WHERE
        r_d812c3.te_3174fb <= r_ba632b.i_formulation 
        AND (
            NOT r_40fcd6.c_current_cdemo_sk > 26 
            OR r_ba632b.i_category != 'T' 
            OR r_ba632b.i_wholesale_cost = r_ba632b.i_current_price
        ) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_fdcbe8.cd_marital_status as te_6ebdf2,
    r_72b950.sm_ship_mode_id as te_d4c8c4,
    hash(r_72b950.sm_carrier) as te_04bf4d 
FROM
    db1.ship_mode AS r_72b950,
    db1.customer_demographics AS r_02c62b 
INNER JOIN
    db1.customer_demographics AS r_fdcbe8 
        ON r_02c62b.cd_dep_college_count = r_fdcbe8.cd_dep_count 
WHERE
    r_72b950.sm_ship_mode_sk > r_02c62b.cd_dep_employed_count 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 ASC;
----------->
SELECT
    reverse(r_2ddce4.cd_education_status) as te_92dd3d,
    timestamp_millis(r_812eea.i_manager_id) as te_3fdf4c,
    r_812eea.i_item_id as te_fd5e17 
FROM
    db1.customer_demographics AS r_2ddce4,
    db1.time_dim AS r_b92c90 
RIGHT JOIN
    db1.item AS r_812eea 
        ON r_b92c90.t_second >= r_812eea.i_class_id 
WHERE
    r_2ddce4.cd_demo_sk > r_812eea.i_brand_id 
    AND r_2ddce4.cd_gender LIKE 'v' 
    OR r_812eea.i_rec_start_date = r_812eea.i_rec_end_date 
    OR NOT r_2ddce4.cd_dep_employed_count <= 27 
    OR r_b92c90.t_hour BETWEEN r_2ddce4.cd_demo_sk * 54 AND 48 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS FIRST 
OFFSET 17;
----------->
WITH CTE_030412(te_679781) AS (SELECT
    r_073c8e.i_item_id as te_679781 
FROM
    db1.reason AS r_0b5ad8 
RIGHT JOIN
    db1.item AS r_073c8e 
        ON r_0b5ad8.r_reason_sk > r_073c8e.i_class_id 
WHERE
    r_073c8e.i_rec_start_date != r_073c8e.i_rec_end_date 
    AND r_073c8e.i_brand LIKE 'px6kg7x' 
    AND (NOT r_073c8e.i_color NOT LIKE r_073c8e.i_size 
    OR r_073c8e.i_item_desc NOT ILIKE 'sUgD') 
ORDER BY
    1 ASC NULLS LAST) SELECT
    r_e998d4.c_login as te_92f15c, r_e998d4.c_email_address as te_f7517d, r_e998d4.c_last_review_date_sk as te_8b3ef8 
FROM
    CTE_030412 AS r_a2eee6,
    db1.customer AS r_e998d4 
WHERE
    r_a2eee6.te_679781 ILIKE r_e998d4.c_preferred_cust_flag 
    OR r_e998d4.c_first_sales_date_sk <= 66 
    OR r_e998d4.c_first_sales_date_sk <= 0 
    OR r_e998d4.c_salutation LIKE 'b2Gv7RX' 
    OR r_a2eee6.te_679781 LIKE r_e998d4.c_customer_id 
ORDER BY
    1, 2 ASC NULLS LAST, 3 NULLS LAST 
OFFSET 61;
----------->
SELECT
    r_9e25b5.web_street_number as te_51ab2b 
FROM
    db1.web_page AS r_10c893 
LEFT JOIN
    db1.web_site AS r_9e25b5 
        ON r_10c893.wp_customer_sk != r_9e25b5.web_close_date_sk 
WHERE
    r_9e25b5.web_suite_number LIKE 'UgDDX' 
    OR r_9e25b5.web_suite_number != r_9e25b5.web_street_type 
    OR r_10c893.wp_customer_sk <= 16 
    OR r_9e25b5.web_site_id NOT ILIKE r_10c893.wp_autogen_flag 
ORDER BY
    1 ASC NULLS FIRST;
----------->
WITH CTE_c93b9f(te_6b0f8d, te_431b58) AS (SELECT
    r_a15322.ca_address_id as te_6b0f8d,
    r_aaea62.web_open_date_sk as te_431b58 
FROM
    db1.web_site AS r_aaea62,
    db1.customer_address AS r_a15322 
WHERE
    r_aaea62.web_site_sk != r_a15322.ca_gmt_offset 
    OR r_aaea62.web_street_name ILIKE r_aaea62.web_manager 
ORDER BY
    1 NULLS FIRST, 2 ASC NULLS FIRST), CTE_2ea4f1(te_177bcd) AS (SELECT
    r_bf2bb0.up_9e62d4 as te_177bcd 
FROM
    db1.time_dim AS r_81b27e 
LEFT JOIN
    (SELECT
        * 
    FROM
        db1.web_page UNPIVOT INCLUDE NULLS ((up_c313a1,
        up_18b733,
        up_9e62d4,
        up_a74981) FOR upn_b7542d IN ((wp_char_count,
        wp_rec_start_date,
        wp_autogen_flag,
        wp_type) AS UPF_4e390a,
        (wp_link_count,
        wp_rec_end_date,
        wp_web_page_id,
        wp_url) AS UPF_fd8e87))) AS r_bf2bb0 
        ON r_81b27e.t_sub_shift ILIKE r_bf2bb0.upn_b7542d 
WHERE
    r_81b27e.t_am_pm ILIKE r_81b27e.t_time_id 
    OR r_bf2bb0.wp_customer_sk >= 85 
ORDER BY
    1 ASC NULLS FIRST) SELECT
        r_5e7c2d.te_431b58 as te_46c3f1, r_5e7c2d.te_6b0f8d as te_6267b4 
    FROM
        CTE_c93b9f AS r_5e7c2d 
    RIGHT JOIN
        CTE_2ea4f1 AS r_227786 
            ON r_5e7c2d.te_6b0f8d ILIKE r_227786.te_177bcd,
        db1.household_demographics AS r_0ac562 
    WHERE
        r_5e7c2d.te_431b58 < r_0ac562.hd_income_band_sk 
        AND r_5e7c2d.te_6b0f8d NOT IN (
            SELECT
                r_17c093.ca_suite_number as te_7ef9cb 
            FROM
                db1.household_demographics AS r_11c7cd 
            RIGHT JOIN
                db1.call_center AS r_74c9b8 
                    ON r_11c7cd.hd_income_band_sk != r_74c9b8.cc_call_center_sk,
                db1.web_page AS r_e4e0f3 
            INNER JOIN
                db1.customer_address AS r_17c093 
                    ON r_e4e0f3.wp_url LIKE r_17c093.ca_city 
            WHERE
                r_74c9b8.cc_gmt_offset = r_17c093.ca_gmt_offset 
                AND r_74c9b8.cc_gmt_offset <= 44.44712034 
            ORDER BY
                1 NULLS LAST
        ) 
        AND r_5e7c2d.te_6b0f8d LIKE r_227786.te_177bcd 
        AND r_0ac562.hd_income_band_sk != r_0ac562.hd_demo_sk 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC 
    OFFSET 3;
----------->
WITH CTE_665e62(te_0b1cd0) AS (SELECT
    '4P' as te_0b1cd0 
FROM
    db1.ship_mode AS r_9b8aff 
WHERE
    r_9b8aff.sm_ship_mode_id NOT LIKE '4QHZgGI' 
    OR r_9b8aff.sm_ship_mode_sk >= 4 
ORDER BY
    1) SELECT
    r_75abd6.sr_item_sk as te_1e8f59, r_75abd6.sr_ticket_number as te_1e5fc3 
FROM
    CTE_665e62 AS r_e4f387,
    db1.store_returns AS r_75abd6 
WHERE
    r_e4f387.te_0b1cd0 ILIKE chr(r_75abd6.web_county) 
    AND r_75abd6.sr_ticket_number <= r_75abd6.sr_store_sk 
    AND r_75abd6.sr_net_loss < 92.93514983 
ORDER BY
    1 NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    r_d64fd7.sr_store_sk as te_508582,
    r_d64fd7.sr_reason_sk as te_c32f32,
    current_timestamp() as te_557641 
FROM
    db1.store_returns AS r_d64fd7 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.web_site UNPIVOT ((up_7c9e2d,
            up_75c368,
            up_cc5241,
            up_ccdd54,
            up_670698) FOR upn_2cd7e4 IN ((web_rec_end_date,
            web_state,
            web_close_date_sk,
            web_tax_percentage,
            web_suite_number) AS UPF_ec51b6,
            (web_rec_start_date,
            web_site_id,
            web_company_id,
            web_gmt_offset,
            web_name) AS UPF_2aa8e0))
    ) AS r_0598fc 
        ON EXISTS (
            SELECT
                r_4d8155.w_country as te_80028c,
                r_be10ee.ca_county as te_4a53b8,
                current_timestamp() as te_8c20e1 
        FROM
            db1.warehouse AS r_4d8155,
            db1.customer_address AS r_be10ee 
        WHERE
            r_4d8155.w_warehouse_id LIKE r_be10ee.ca_state 
            AND r_4d8155.w_street_number ILIKE r_4d8155.w_state 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC, 3 NULLS FIRST
    ), db1.customer_demographics AS r_c3e6f1 
WHERE
    r_0598fc.web_market_manager NOT ILIKE r_c3e6f1.cd_education_status 
    OR NOT r_0598fc.web_zip ILIKE 'TUO' 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS FIRST 
OFFSET 95;
----------->
SELECT
    r_f7bbf3.t_time_sk as te_603006 
FROM
    db1.time_dim AS r_f7bbf3 
WHERE
    r_f7bbf3.t_time_sk != 28 
    AND r_f7bbf3.t_second != r_f7bbf3.t_minute 
    OR r_f7bbf3.t_minute >= 65 
    OR r_f7bbf3.t_time_sk < 13 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    current_timestamp() as te_4c5436,
    r_e515af.w_gmt_offset as te_021c70 
FROM
    db1.warehouse AS r_e515af,
    db1.time_dim AS r_32368a 
WHERE
    NOT r_e515af.w_street_type LIKE r_32368a.t_shift 
    AND NOT EXISTS (
        SELECT
            r_bfff95.w_county as te_3f4bff,
            chr(61 - r_bfff95.w_warehouse_sq_ft) as te_d9d098,
            r_bfff95.w_warehouse_sk as te_d65cab 
        FROM
            db1.warehouse AS r_bfff95 
        INNER JOIN
            db1.reason AS r_5a97fc 
                ON r_bfff95.w_state NOT LIKE r_5a97fc.r_reason_desc,
            db1.ship_mode AS r_5e2051 
        WHERE
            r_bfff95.w_city NOT LIKE r_5e2051.sm_code 
        GROUP BY
            2, 1, 3 
        ORDER BY
            1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 ASC NULLS LAST 
        OFFSET 37
) 
AND r_32368a.t_second = r_32368a.t_time ORDER BY
    1 ASC NULLS FIRST, 2 DESC;
----------->
SELECT
    current_timestamp() as te_1091c0 
FROM
    db1.ship_mode AS r_c38eea 
WHERE
    r_c38eea.sm_carrier NOT ILIKE '98hv9' 
    AND r_c38eea.sm_contract NOT LIKE 'gDDX' 
    AND r_c38eea.sm_contract NOT LIKE r_c38eea.sm_carrier 
ORDER BY
    1 DESC 
OFFSET 97;
----------->
SELECT
    r_d6ec6b.s_floor_space as te_dcfb68 
FROM
    db1.store AS r_d6ec6b 
WHERE
    r_d6ec6b.s_market_manager NOT ILIKE r_d6ec6b.s_street_number 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_d976d9.s_suite_number as te_823a06 
FROM
    db1.store AS r_d976d9 
RIGHT JOIN
    db1.income_band AS r_02778c 
        ON r_d976d9.s_division_id >= r_02778c.ib_lower_bound 
WHERE
    r_d976d9.s_city NOT LIKE 's' 
    AND r_d976d9.s_tax_percentage >= r_d976d9.s_gmt_offset 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_5b4ebd.w_warehouse_sk * 46 as te_b7f18f 
FROM
    db1.warehouse AS r_5b4ebd 
WHERE
    r_5b4ebd.w_warehouse_sk BETWEEN 40 AND 40 
ORDER BY
    1 DESC;
----------->
SELECT
    r_a83e78.i_item_id as te_8b42a9,
    r_df2557.cc_company as te_003a17 
FROM
    db1.web_page AS r_79b8f0 
INNER JOIN
    db1.call_center AS r_df2557 
        ON r_79b8f0.wp_url NOT LIKE r_df2557.cc_street_number,
    (SELECT
        hash(r_7ccba8.i_manager_id) as te_745a7c 
    FROM
        db1.item AS r_7ccba8 
    WHERE
        r_7ccba8.i_brand != '4elBy7' 
        AND r_7ccba8.i_rec_end_date <= r_7ccba8.i_rec_start_date 
        AND r_7ccba8.i_formulation NOT ILIKE 'XUulstn' 
        OR r_7ccba8.i_item_sk >= 19 
    ORDER BY
        1 DESC NULLS LAST) AS r_1e7bea 
LEFT JOIN
    db1.item AS r_a83e78 
        ON r_1e7bea.te_745a7c < r_a83e78.i_manufact_id 
WHERE
    r_df2557.cc_class LIKE r_a83e78.i_formulation 
    AND r_df2557.cc_company_name LIKE 'RGC0' 
    OR r_df2557.cc_county NOT ILIKE r_df2557.cc_street_number 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST 
LIMIT 32;
----------->
WITH CTE_f3c9ca(te_dc09a0, te_2a0c41, te_15dd8f) AS (SELECT
    r_889876.w_suite_number as te_dc09a0,
    chr(r_487ab0.sr_refunded_cash) || chr(r_e26e54.ib_lower_bound + 77) as te_2a0c41,
    r_487ab0.sr_return_ship_cost as te_15dd8f 
FROM
    db1.store_returns AS r_487ab0,
    db1.warehouse AS r_889876 
INNER JOIN
    db1.income_band AS r_e26e54 
        ON r_889876.w_warehouse_sk = r_e26e54.ib_income_band_sk 
WHERE
    r_487ab0.sr_reversed_charge >= r_889876.w_gmt_offset 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 NULLS LAST) SELECT
    r_2df898.cd_gender as te_7f70d2, r_7073df.te_15dd8f as te_7200ba, r_2df898.cd_credit_rating as te_0392ed 
FROM
    CTE_f3c9ca AS r_7073df,
    (SELECT
        character_length(btrim(r_639ae2.wp_web_page_id)) as te_f79792,
        r_a26224.sr_returned_date_sk as te_ccb38a,
        r_a26224.sr_item_sk as te_1d3bce 
    FROM
        db1.ship_mode AS r_bc754d,
        (SELECT
            * 
        FROM
            db1.store_returns PIVOT(max(web_county) AS pa_418d77 FOR (sr_net_loss,
            sr_fee) IN (((10.13212207,
            19.37497026)) AS pf_eab95e,
            ((60.07078333,
            77.82649309)) AS pf_688553,
            ((1.70068885,
            59.30066694)) AS pf_2285f5,
            ((89.65030326,
            84.12483232)) AS pf_750427,
            ((79.14154009,
            70.4874396)) AS pf_7647e2))) AS r_a26224 
    RIGHT JOIN
        db1.web_page AS r_639ae2 
            ON r_a26224.sr_reversed_charge != r_639ae2.wp_image_count 
    WHERE
        r_bc754d.sm_ship_mode_sk >= r_a26224.sr_return_ship_cost 
        OR r_bc754d.sm_code LIKE r_bc754d.sm_type 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC, 3 DESC) AS r_768610 
    LEFT JOIN
        db1.customer_demographics AS r_2df898 
            ON r_768610.te_f79792 >= r_2df898.cd_dep_count 
    WHERE
        r_7073df.te_2a0c41 < r_2df898.cd_credit_rating 
        AND r_2df898.cd_purchase_estimate >= 48 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_3b6066.cc_call_center_sk as te_e69c9c 
FROM
    db1.ship_mode AS r_9093f1 
INNER JOIN
    db1.call_center AS r_3b6066 
        ON r_9093f1.sm_ship_mode_sk != r_3b6066.cc_division 
WHERE
    r_3b6066.cc_tax_percentage > r_3b6066.cc_gmt_offset 
    OR r_3b6066.cc_county LIKE 'gDDXX4QHZ' 
ORDER BY
    1;
----------->
SELECT
    r_4c6f4e.cd_marital_status as te_88ec88,
    r_4c6f4e.cd_dep_college_count as te_710406,
    r_28392f.te_852856 as te_0d2b61 
FROM
    db1.ship_mode AS r_2fbe4d 
INNER JOIN
    (
        SELECT
            r_9b9044.web_rec_end_date as te_7e973b,
            current_date() as te_852856,
            abs(unix_seconds(make_timestamp(r_9b9044.web_site_sk,
            r_9b9044.web_open_date_sk,
            r_9b9044.web_close_date_sk,
            r_9b9044.web_open_date_sk,
            r_26279d.sm_ship_mode_sk,
            r_dd5baf.w_gmt_offset))) as te_4144e3 
        FROM
            db1.ship_mode AS r_26279d,
            db1.warehouse AS r_dd5baf 
        LEFT JOIN
            db1.web_site AS r_9b9044 
                ON r_dd5baf.w_gmt_offset < r_9b9044.web_tax_percentage 
        WHERE
            r_26279d.sm_ship_mode_sk >= r_9b9044.web_close_date_sk 
            AND r_26279d.sm_code ILIKE 'ulstntb' 
            OR r_dd5baf.w_warehouse_name NOT ILIKE 'DDX' 
        ORDER BY
            1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 NULLS LAST
    ) AS r_28392f 
        ON r_2fbe4d.sm_ship_mode_sk <= r_28392f.te_4144e3,
    db1.customer AS r_9cb27c 
RIGHT JOIN
    db1.customer_demographics AS r_4c6f4e 
        ON r_9cb27c.c_first_name NOT ILIKE r_4c6f4e.cd_education_status 
WHERE
    r_28392f.te_4144e3 = r_9cb27c.c_current_hdemo_sk 
    AND r_9cb27c.c_login NOT LIKE r_9cb27c.c_last_name 
    AND r_4c6f4e.cd_dep_college_count > 61 
    AND r_28392f.te_852856 BETWEEN DATE'2024-10-11' AND r_28392f.te_7e973b 
    AND r_9cb27c.c_current_addr_sk >= r_9cb27c.c_customer_sk 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    min('OV') as te_939840 
FROM
    (WITH CTE_fda01f(te_449ced,
    te_89c136,
    te_d5d06a) AS (SELECT
        chr(mod(mod(r_81f8f3.i_brand_id,
        25) + r_81f8f3.i_item_sk,
        mod(r_81f8f3.i_item_sk,
        5.40317405D))) as te_449ced,
        r_998b99.wp_rec_start_date as te_89c136,
        r_81f8f3.i_item_id as te_d5d06a 
    FROM
        db1.web_page AS r_998b99,
        db1.item AS r_81f8f3 
    LEFT JOIN
        db1.income_band AS r_a35a16 
            ON r_81f8f3.i_current_price = r_a35a16.ib_lower_bound 
    WHERE
        r_998b99.wp_rec_end_date != r_81f8f3.i_rec_end_date 
    ORDER BY
        1, 2 NULLS FIRST, 3 ASC), CTE_32efc3(te_62cf82, te_95979c, te_580fc3) AS (SELECT
        r_ca0ed4.web_gmt_offset as te_62cf82, r_ca0ed4.web_site_id as te_95979c, r_916e7c.t_minute as te_580fc3 
    FROM
        (SELECT
            * 
        FROM
            db1.time_dim PIVOT(min(t_meal_time) AS pa_15af72 FOR t_hour IN ((18) AS pf_dfb444,
            (71) AS pf_c7f154))) AS r_916e7c 
    LEFT JOIN
        db1.store AS r_f4ab84 
            ON r_916e7c.t_shift LIKE r_f4ab84.s_market_desc,
        db1.web_site AS r_ca0ed4 
    RIGHT JOIN
        db1.customer_address AS r_f56945 
            ON r_ca0ed4.web_site_id LIKE r_f56945.ca_zip 
    WHERE
        r_f4ab84.s_rec_start_date < r_ca0ed4.web_rec_start_date 
        AND r_f56945.ca_gmt_offset > r_f4ab84.s_tax_percentage 
    ORDER BY
        1 NULLS FIRST, 2 NULLS LAST, 3 DESC) SELECT
            substring(r_e4cfc1.te_449ced, r_d7b2ce.te_580fc3) as te_68d6f1 
        FROM
            CTE_fda01f AS r_e4cfc1 
        RIGHT JOIN
            CTE_32efc3 AS r_d7b2ce 
                ON r_e4cfc1.te_449ced LIKE r_d7b2ce.te_95979c 
        WHERE
            r_e4cfc1.te_d5d06a LIKE r_d7b2ce.te_95979c 
            AND r_e4cfc1.te_449ced NOT ILIKE 'DXX4QHZ' 
        ORDER BY
            1 DESC NULLS LAST) AS r_155701 
    WHERE
        r_155701.te_68d6f1 ILIKE 'DCMa' 
    ORDER BY
        1 DESC NULLS FIRST;
----------->
SELECT
    hex(r_99262d.cd_dep_college_count) as te_0abdcc,
    r_b891a1.cd_education_status as te_98531d 
FROM
    db1.customer_demographics AS r_b891a1 
RIGHT JOIN
    db1.store_returns AS r_0caeab 
        ON r_b891a1.cd_dep_employed_count < r_0caeab.sr_return_tax,
    db1.customer_demographics AS r_99262d 
RIGHT JOIN
    db1.ship_mode AS r_bfaa51 
        ON r_99262d.cd_demo_sk <= r_bfaa51.sm_ship_mode_sk 
WHERE
    r_0caeab.sr_reason_sk >= r_bfaa51.sm_ship_mode_sk 
    OR r_0caeab.sr_refunded_cash BETWEEN 36.34342825 AND r_0caeab.sr_net_loss 
    AND r_99262d.cd_education_status ILIKE 'ezmS' 
    AND r_0caeab.sr_returned_date_sk BETWEEN r_0caeab.sr_return_tax AND 31 
ORDER BY
    1, 2 DESC 
OFFSET 87;
----------->
SELECT
    r_f2290a.cc_gmt_offset as te_163a7a,
    r_ed9b62.i_manufact as te_c81f6a 
FROM
    db1.item AS r_ed9b62,
    db1.customer AS r_5e9c3a 
RIGHT JOIN
    db1.call_center AS r_f2290a 
        ON r_5e9c3a.c_email_address NOT ILIKE r_f2290a.cc_company_name 
WHERE
    r_ed9b62.i_product_name NOT ILIKE r_f2290a.cc_company_name 
    OR r_f2290a.cc_name NOT ILIKE '4QHZgGIHl' 
ORDER BY
    1 ASC, 2;
----------->
SELECT
    r_75196b.cc_call_center_id as te_7de86e 
FROM
    db1.call_center AS r_75196b 
INNER JOIN
    db1.time_dim AS r_fb307f 
        ON r_75196b.cc_county NOT ILIKE 'UgD' 
WHERE
    r_75196b.cc_rec_start_date >= DATE'2024-03-26' 
ORDER BY
    1 ASC 
OFFSET 51;
----------->
SELECT
    r_72dcf6.c_customer_id as te_83ebc1,
    mod(r_72dcf6.c_first_shipto_date_sk,
    95.57761633) as te_f8bcc5,
    r_036d81.wp_rec_start_date as te_64aa15 
FROM
    db1.customer AS r_72dcf6,
    db1.web_page AS r_036d81 
WHERE
    r_72dcf6.c_customer_sk < r_036d81.wp_max_ad_count 
    AND r_036d81.wp_rec_end_date != r_036d81.wp_rec_start_date 
    AND (
        NOT r_72dcf6.c_customer_sk > r_72dcf6.c_last_review_date_sk 
        OR r_036d81.wp_type != r_72dcf6.c_salutation 
        AND r_72dcf6.c_current_cdemo_sk != r_72dcf6.c_first_shipto_date_sk
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_05fdf6.s_rec_end_date as te_42419e,
    r_05fdf6.s_floor_space as te_7abf72,
    r_05fdf6.s_division_name as te_1bd2cc 
FROM
    db1.store AS r_05fdf6,
    db1.warehouse AS r_5ed4a6 
WHERE
    r_05fdf6.s_state NOT LIKE r_5ed4a6.w_zip 
    AND r_05fdf6.s_manager NOT LIKE 'ez' 
    AND r_05fdf6.s_closed_date_sk > 54 
    OR r_05fdf6.s_market_desc LIKE 'iizz2k' 
GROUP BY
    1, 2, 3 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 DESC;
----------->
SELECT
    r_05e166.d_current_quarter as te_468ee4,
    r_05e166.d_current_year as te_2fcc3a 
FROM
    db1.web_page AS r_b12bf5,
    db1.date_dim AS r_05e166 
WHERE
    r_b12bf5.wp_type NOT ILIKE r_05e166.d_current_day 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST 
LIMIT 98;
----------->
SELECT
    r_acb3f8.te_99fff8 as te_5c940e 
FROM
    (SELECT
        r_b8fe88.c_birth_month as te_13f284,
        r_b8fe88.c_email_address as te_99fff8 
    FROM
        db1.customer AS r_b8fe88 
    LEFT JOIN
        db1.call_center AS r_83def1 
            ON r_b8fe88.c_preferred_cust_flag NOT LIKE r_83def1.cc_call_center_id,
        db1.store_returns AS r_73d57b 
    WHERE
        r_83def1.cc_mkt_id = r_73d57b.sr_customer_sk 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_acb3f8 
WHERE
    (
        NOT r_acb3f8.te_99fff8 ILIKE r_acb3f8.te_99fff8 
        OR r_acb3f8.te_13f284 >= r_acb3f8.te_13f284 
        OR r_acb3f8.te_13f284 BETWEEN 89 AND 89 
        AND r_acb3f8.te_13f284 BETWEEN 16 AND 16
    ) 
ORDER BY
    1 ASC;
----------->
SELECT
    r_720fb8.d_current_quarter as te_8a1e67,
    r_720fb8.d_date as te_eb4cbc,
    r_720fb8.d_current_month as te_a82a6e 
FROM
    db1.reason AS r_3816fb,
    db1.date_dim AS r_720fb8 
INNER JOIN
    db1.customer AS r_0a607a 
        ON r_720fb8.d_year != r_0a607a.c_first_sales_date_sk 
WHERE
    r_0a607a.c_login IN (
        SELECT
            string(chr(positive(unix_timestamp()))) as te_139f34
    ) 
    AND (
        NOT r_720fb8.d_year >= 85 
        AND r_3816fb.r_reason_desc NOT LIKE r_0a607a.c_birth_country
    ) 
    OR (
        NOT NOT r_720fb8.d_fy_quarter_seq > 25 
        OR r_0a607a.c_login ILIKE 'elBy7I'
    ) 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_efa779.i_rec_end_date as te_0793b5 
FROM
    db1.item AS r_efa779 
RIGHT JOIN
    (
        SELECT
            r_651b83.te_016aa7 as te_2c5c47,
            r_741e63.sr_store_credit as te_f16724,
            now() as te_430126 
        FROM
            (SELECT
                r_34ab54.s_rec_end_date as te_016aa7,
                to_char(sin(r_1909fc.cc_open_date_sk + 5),
                '000D00') as te_f22148 
            FROM
                db1.call_center AS r_1909fc 
            RIGHT JOIN
                db1.customer_address AS r_500368 
                    ON r_1909fc.cc_call_center_id ILIKE r_500368.ca_street_number,
                db1.store AS r_34ab54 
            INNER JOIN
                db1.ship_mode AS r_c06a49 
                    ON r_34ab54.s_store_sk = r_c06a49.sm_ship_mode_sk 
            WHERE
                r_1909fc.cc_mkt_id != r_34ab54.s_closed_date_sk 
                OR r_34ab54.s_hours NOT ILIKE 'ntb2Gv7RX' 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC NULLS LAST 
            OFFSET 43) AS r_651b83, db1.store_returns AS r_741e63 INNER JOIN
            db1.income_band AS r_241274 
                ON r_741e63.sr_reason_sk = r_241274.ib_upper_bound 
        WHERE
            NOT r_651b83.te_f22148 <= r_741e63.sr_store_credit 
            OR r_741e63.sr_fee >= 33.80433518 
        ORDER BY
            1 DESC, 2 DESC NULLS FIRST, 3 NULLS FIRST) AS r_5afdf0 
                ON r_efa779.i_wholesale_cost != r_5afdf0.te_f16724 
        WHERE
            r_efa779.i_wholesale_cost < r_efa779.i_current_price 
        ORDER BY
            1 NULLS LAST;
----------->
SELECT
    r_a97e91.i_category as te_7d9648 
FROM
    db1.item AS r_a97e91 
LEFT JOIN
    db1.call_center AS r_336163 
        ON r_a97e91.i_units ILIKE r_336163.cc_company_name 
WHERE
    r_336163.cc_market_manager LIKE 'VbB' 
    OR r_a97e91.i_rec_end_date != DATE'2024-03-25' 
    AND r_a97e91.i_color >= 's' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_bc4675.sr_refunded_cash as te_34536d,
    r_3e7601.te_de7cf8 as te_62614a 
FROM
    db1.store_returns AS r_bc4675 
INNER JOIN
    db1.income_band AS r_15237d 
        ON r_bc4675.sr_ticket_number != r_15237d.ib_income_band_sk,
    (SELECT
        r_7f2f30.hd_buy_potential as te_de7cf8 
    FROM
        db1.reason AS r_48967a 
    LEFT JOIN
        db1.household_demographics AS r_7f2f30 
            ON r_48967a.r_reason_desc NOT LIKE r_7f2f30.hd_buy_potential 
    WHERE
        r_48967a.r_reason_sk != 68 
        OR r_48967a.r_reason_id NOT LIKE 'ir98hv95' 
        AND r_7f2f30.hd_dep_count < 27 
    ORDER BY
        1 DESC NULLS LAST) AS r_3e7601 
WHERE
    chr(r_bc4675.sr_net_loss) NOT ILIKE r_3e7601.te_de7cf8 
    AND r_bc4675.sr_customer_sk != 44 
    AND r_bc4675.sr_cdemo_sk <= r_bc4675.sr_hdemo_sk 
    OR r_bc4675.sr_store_credit BETWEEN r_bc4675.sr_cdemo_sk AND 15.0222752 
    OR r_bc4675.sr_store_sk < 88 
ORDER BY
    1 DESC NULLS LAST, 2 ASC;
----------->
SELECT
    char_length(chr(r_52c439.sr_ticket_number)) as te_eeb572 
FROM
    db1.store_returns AS r_52c439 
WHERE
    r_52c439.web_county NOT IN (
        SELECT
            r_5ad69e.s_tax_percentage * 86.83397012D as te_a0f8f0 
        FROM
            db1.store AS r_5ad69e 
        INNER JOIN
            db1.ship_mode AS r_a7041e 
                ON r_5ad69e.s_hours >= r_a7041e.sm_ship_mode_id 
        WHERE
            r_5ad69e.s_store_name NOT ILIKE 'RGC0l' 
        ORDER BY
            1 ASC NULLS LAST
    ) 
    OR r_52c439.sr_return_ship_cost = 66.79629153 
    AND r_52c439.sr_ticket_number = r_52c439.sr_store_sk 
    OR r_52c439.sr_customer_sk <= r_52c439.sr_hdemo_sk 
ORDER BY
    1 ASC NULLS FIRST 
OFFSET 1;
----------->
(
    SELECT
        r_697fcc.w_county as te_415e69 
    FROM
        db1.warehouse AS r_697fcc 
    RIGHT JOIN
        db1.store_returns AS r_b4f570 
            ON r_697fcc.w_warehouse_sk <= r_b4f570.sr_item_sk 
    WHERE
        r_b4f570.sr_fee <= 65.87081365 
        OR r_697fcc.w_county IN (
            WITH CTE_396775(te_bc5d2d, te_ee47b9) AS (SELECT
                r_714eb0.cd_demo_sk as te_bc5d2d,
                r_bdcdfe.cc_rec_start_date as te_ee47b9 
            FROM
                db1.web_site AS r_ca3f04 
            INNER JOIN
                db1.call_center AS r_bdcdfe 
                    ON r_ca3f04.web_site_sk <= r_bdcdfe.cc_closed_date_sk,
                db1.customer_demographics AS r_714eb0 
            LEFT JOIN
                db1.web_page AS r_9ce949 
                    ON r_714eb0.cd_demo_sk <= r_9ce949.wp_customer_sk 
            WHERE
                r_ca3f04.web_rec_end_date < r_9ce949.wp_rec_end_date 
            ORDER BY
                1 DESC NULLS FIRST, 2 DESC NULLS FIRST) SELECT
                r_b7ec7c.te_31e9ff as te_976b7f 
            FROM
                CTE_396775 AS r_23601a 
            RIGHT JOIN
                (
                    WITH CTE_08fc2a(te_4c1711, te_964ecb) AS (SELECT
                        r_b43eb1.te_efc0c1 as te_4c1711,
                        r_b43eb1.te_efc0c1 as te_964ecb 
                    FROM
                        db1.store AS r_15a0a8 
                    LEFT JOIN
                        (SELECT
                            88.08099183D / r_883311.web_close_date_sk as te_efc0c1,
                            ascii(r_883311.web_mkt_class) as te_a6ad16,
                            r_883311.web_state as te_e6571a 
                        FROM
                            db1.web_site AS r_883311 
                        RIGHT JOIN
                            db1.web_page AS r_8796a7 
                                ON r_883311.web_rec_start_date < r_8796a7.wp_rec_start_date,
                            db1.household_demographics AS r_e03c3e 
                        WHERE
                            r_883311.web_tax_percentage != r_e03c3e.hd_dep_count) AS r_b43eb1 
                            ON r_15a0a8.s_closed_date_sk < r_b43eb1.te_a6ad16,
                        db1.web_site AS r_8f786b 
                    WHERE
                        r_15a0a8.s_market_desc != r_8f786b.web_suite_number 
                        OR r_8f786b.web_manager LIKE '2kGk4elBy7' 
                        AND r_15a0a8.s_rec_start_date = r_15a0a8.s_rec_end_date 
                        AND NOT r_b43eb1.te_e6571a ILIKE r_15a0a8.s_store_id 
                    GROUP BY
                        2, 1 
                    ORDER BY
                        1 DESC NULLS LAST, 2 DESC 
                    OFFSET 26), CTE_92c2e0(te_1e7520, te_d13f86, te_6ff966) AS (SELECT
                        r_a90440.wp_rec_start_date as te_1e7520, r_a90440.wp_rec_end_date as te_d13f86, hash(pi(), make_timestamp(r_0141dd.sm_ship_mode_sk, r_a90440.wp_link_count, r_a90440.wp_image_count, r_a90440.wp_customer_sk, r_a90440.wp_access_date_sk, decimal(r_a90440.wp_customer_sk))) as te_6ff966 FROM
                            db1.ship_mode AS r_0141dd 
                        INNER JOIN
                            db1.web_page AS r_a90440 
                                ON r_0141dd.sm_carrier <= r_a90440.wp_type,
                            db1.ship_mode AS r_436f95 
                        WHERE
                            r_a90440.wp_access_date_sk <= r_436f95.sm_ship_mode_sk 
                            OR NOT r_a90440.wp_rec_end_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
                            AND r_a90440.wp_rec_end_date >= r_a90440.wp_rec_start_date 
                            AND r_436f95.sm_ship_mode_sk >= 39 
                        ORDER BY
                            1 ASC NULLS LAST, 2 DESC, 3 ASC NULLS LAST 
                        LIMIT 6) SELECT
                        r_851ada.ib_income_band_sk - r_707c76.te_964ecb as te_55b913, chr(r_851ada.ib_upper_bound) as te_31e9ff, r_851ada.ib_lower_bound as te_0ecd29 FROM
                            CTE_08fc2a AS r_707c76 
                        RIGHT JOIN
                            CTE_92c2e0 AS r_53c5bb 
                                ON date_from_unix_date(bigint(r_707c76.te_4c1711)) != date(timestamp(r_53c5bb.te_1e7520)),
                            db1.household_demographics AS r_90b976 
                        INNER JOIN
                            db1.income_band AS r_851ada 
                                ON r_90b976.hd_income_band_sk <= r_851ada.ib_income_band_sk 
                        WHERE
                            r_707c76.te_4c1711 > r_90b976.hd_income_band_sk 
                            OR r_707c76.te_4c1711 < r_707c76.te_964ecb 
                            OR r_851ada.ib_upper_bound = 74 
                            AND r_707c76.te_964ecb = r_707c76.te_4c1711 
                        ORDER BY
                            1 ASC, 2 NULLS LAST, 3 DESC NULLS LAST) AS r_b7ec7c 
                            ON r_23601a.te_bc5d2d > r_b7ec7c.te_55b913 
                    WHERE
                        r_b7ec7c.te_31e9ff LIKE 'U' 
                    ORDER BY
                        1 NULLS LAST) 
                        AND r_b4f570.sr_reversed_charge BETWEEN 1.17707596 AND 1.17707596 
                    ORDER BY
                        1 DESC NULLS LAST
                ) MINUS ALL (
                    SELECT
                        chr(unix_seconds(make_timestamp(r_b2d419.d_fy_quarter_seq, r_b2d419.d_fy_quarter_seq, r_b2d419.d_fy_year, r_26cb25.i_item_sk, r_b2d419.d_month_seq, r_26cb25.i_current_price))) as te_37866e 
                    FROM
                        db1.item AS r_26cb25 
                    LEFT JOIN
                        db1.date_dim AS r_b2d419 
                            ON r_26cb25.i_item_id NOT ILIKE r_b2d419.d_day_name 
                    WHERE
                        r_26cb25.i_size ILIKE r_26cb25.i_product_name 
                    ORDER BY
                        1 DESC NULLS LAST
                ) 
            ORDER BY
                1 DESC 
            LIMIT 69;
----------->
SELECT
    current_timestamp() as te_f8dd20,
    r_fcd0c0.ca_street_name as te_7facc7 
FROM
    db1.customer_address AS r_fcd0c0 
RIGHT JOIN
    db1.ship_mode AS r_85e646 
        ON r_fcd0c0.ca_county < r_85e646.sm_ship_mode_id,
    db1.item AS r_9d8a74 
WHERE
    r_85e646.sm_contract NOT ILIKE r_9d8a74.i_brand 
ORDER BY
    1, 2 DESC NULLS FIRST;
----------->
WITH CTE_e67568(te_ec0bee) AS (SELECT
    29 - r_0e820c.i_class_id + 24 + r_130010.ib_lower_bound as te_ec0bee 
FROM
    db1.item AS r_0e820c 
LEFT JOIN
    db1.income_band AS r_130010 
        ON r_0e820c.i_manager_id <= r_130010.ib_income_band_sk 
WHERE
    r_0e820c.i_class_id < r_0e820c.i_item_sk 
    AND r_0e820c.i_current_price != r_0e820c.i_wholesale_cost 
    OR r_0e820c.i_rec_end_date > DATE'2024-03-26' 
    OR r_0e820c.i_item_id NOT ILIKE '0lez' 
ORDER BY
    1 NULLS LAST) SELECT
    r_c8fcf4.s_country as te_f452e0 
FROM
    CTE_e67568 AS r_3fd60e 
INNER JOIN
    db1.store AS r_c8fcf4 
        ON r_3fd60e.te_ec0bee >= r_c8fcf4.s_store_sk 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_6951e8.ca_gmt_offset as te_987926,
    mod(r_0ff760.cc_division * 43,
    unix_timestamp()) as te_0694a7,
    r_bbfd95.web_state as te_5aa267 
FROM
    db1.household_demographics AS r_78e450 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.call_center UNPIVOT EXCLUDE NULLS ((up_17bebc,
            up_740b48,
            up_4c259d,
            up_c79f69,
            up_67a6df) FOR upn_951795 IN ((cc_mkt_class,
            cc_gmt_offset,
            cc_call_center_id,
            cc_rec_start_date,
            cc_call_center_sk) AS UPF_c6d664))
    ) AS r_0ff760 
        ON r_78e450.hd_buy_potential = r_0ff760.cc_street_type,
    db1.web_site AS r_bbfd95 
RIGHT JOIN
    db1.customer_address AS r_6951e8 
        ON r_bbfd95.web_tax_percentage >= r_6951e8.ca_gmt_offset 
WHERE
    r_0ff760.up_4c259d NOT ILIKE r_bbfd95.web_site_id 
    OR r_0ff760.up_c79f69 != r_0ff760.cc_rec_end_date 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_731341.cc_company as te_690f9f,
    r_e78c58.d_current_week as te_e51692,
    r_265ac5.cc_open_date_sk as te_554b03 
FROM
    db1.warehouse AS r_1199fa 
INNER JOIN
    db1.call_center AS r_731341 
        ON r_1199fa.w_suite_number NOT LIKE r_731341.cc_county,
    db1.call_center AS r_265ac5 
INNER JOIN
    db1.date_dim AS r_e78c58 
        ON r_265ac5.cc_rec_start_date < r_e78c58.d_date 
WHERE
    r_731341.cc_division <= r_e78c58.d_last_dom 
    AND r_1199fa.w_warehouse_sq_ft <= r_e78c58.d_week_seq 
    OR r_731341.cc_rec_start_date > r_731341.cc_rec_end_date 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC 
LIMIT 97;
----------->
SELECT
    chr(r_c345b3.c_last_review_date_sk) as te_9c5d0f,
    r_2b5330.c_customer_sk / 7 as te_7c3d16,
    r_c345b3.c_customer_id as te_ec6bc3 
FROM
    db1.customer AS r_c345b3,
    db1.customer AS r_2b5330 
WHERE
    r_c345b3.c_preferred_cust_flag NOT LIKE r_2b5330.c_preferred_cust_flag 
    AND r_c345b3.c_birth_month >= r_2b5330.c_current_cdemo_sk 
    OR r_c345b3.c_first_shipto_date_sk < r_2b5330.c_last_review_date_sk 
ORDER BY
    1 ASC, 2 NULLS FIRST, 3;
----------->
SELECT
    r_f57f66.i_item_id as te_c6e3bc 
FROM
    db1.reason AS r_4f2325 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.item PIVOT(min(i_current_price) AS pa_2776ed FOR i_container IN (('QHZgGIH') AS pf_ae4739,
            ('HZgGIHlp') AS pf_2370cb,
            ('IvXUulstnt') AS pf_45d73a,
            ('7') AS pf_8b34e6,
            ('4QHZg') AS pf_03a105))
    ) AS r_f57f66 
        ON r_4f2325.r_reason_sk != r_f57f66.i_class_id 
ORDER BY
    1 DESC;
----------->
SELECT
    r_a7547f.cc_gmt_offset + r_a7547f.cc_mkt_id as te_7a46ac,
    r_f72dd1.te_fc3e92 as te_eb09a1 
FROM
    (SELECT
        89 + r_b68245.i_class_id as te_fc3e92 
    FROM
        db1.warehouse AS r_551aad 
    RIGHT JOIN
        db1.item AS r_b68245 
            ON r_551aad.w_gmt_offset > r_b68245.i_current_price 
    WHERE
        r_b68245.i_rec_end_date >= r_b68245.i_rec_start_date 
        AND r_b68245.i_container NOT LIKE 'RIvXU' 
        OR r_551aad.w_gmt_offset <= 43.9256513 
    ORDER BY
        1 NULLS LAST) AS r_f72dd1 
INNER JOIN
    db1.call_center AS r_a7547f 
        ON r_f72dd1.te_fc3e92 != r_a7547f.cc_open_date_sk,
    db1.customer AS r_e7c76c 
WHERE
    r_a7547f.cc_open_date_sk >= r_e7c76c.c_current_hdemo_sk 
    OR r_e7c76c.c_current_cdemo_sk >= 12 
    OR r_e7c76c.c_preferred_cust_flag NOT ILIKE r_e7c76c.c_customer_id 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_dd6599.cc_open_date_sk as te_e8b53d,
    r_98fbce.cc_rec_end_date as te_e49c29,
    r_98fbce.cc_gmt_offset as te_32f757 
FROM
    db1.household_demographics AS r_1240d6 
INNER JOIN
    db1.call_center AS r_98fbce 
        ON r_1240d6.hd_buy_potential LIKE r_98fbce.cc_street_number,
    db1.call_center AS r_dd6599 
LEFT JOIN
    (
        SELECT
            r_b98b23.i_brand as te_f591e9,
            add_months(r_04b31b.d_date,
            93) as te_f23cad,
            try_add(r_04b31b.d_same_day_ly,
            23) as te_55dd47 
        FROM
            db1.date_dim AS r_04b31b,
            db1.ship_mode AS r_80c15e 
        RIGHT JOIN
            (
                SELECT
                    * 
                FROM
                    db1.item UNPIVOT EXCLUDE NULLS ((up_83fef0,
                    up_ae03cf,
                    up_9b5f9c,
                    up_bba9b9,
                    up_956750) FOR upn_331d69 IN ((i_formulation,
                    i_wholesale_cost,
                    i_manager_id,
                    i_item_id,
                    i_rec_start_date) AS UPF_fb3520))
            ) AS r_b98b23 
                ON r_80c15e.sm_type >= r_b98b23.i_brand 
        WHERE
            r_04b31b.d_week_seq = r_80c15e.sm_ship_mode_sk 
            AND r_04b31b.d_last_dom >= 2 
            AND NOT r_04b31b.d_current_day NOT ILIKE r_04b31b.d_current_quarter 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST) AS r_e2677c 
                ON r_dd6599.cc_call_center_id LIKE r_e2677c.te_f591e9 
        WHERE
            r_1240d6.hd_income_band_sk != r_dd6599.cc_mkt_id 
            OR NOT r_98fbce.cc_state NOT ILIKE r_dd6599.cc_zip 
        GROUP BY
            3, 1, 2 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_b8550c.w_gmt_offset as te_aaa0b4 
FROM
    db1.warehouse AS r_b8550c 
LEFT JOIN
    db1.household_demographics AS r_ef3b9f 
        ON r_b8550c.w_warehouse_sk > r_ef3b9f.hd_income_band_sk 
WHERE
    r_ef3b9f.hd_demo_sk = 65 
    AND r_b8550c.w_street_type ILIKE '7x2SW4P' 
    AND r_ef3b9f.hd_income_band_sk != r_ef3b9f.hd_demo_sk 
    OR r_b8550c.w_suite_number LIKE r_ef3b9f.hd_buy_potential 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 1;
----------->
(
    SELECT
        r_694e5a.sm_ship_mode_sk as te_fc9ef1 
    FROM
        db1.ship_mode AS r_694e5a 
    WHERE
        r_694e5a.sm_ship_mode_id NOT ILIKE '0' 
    ORDER BY
        1 DESC NULLS LAST 
    LIMIT 54
) MINUS ALL (
SELECT
    r_bb24b2.c_birth_month as te_48d997 FROM
        db1.customer AS r_bb24b2 
    LEFT JOIN
        (
            SELECT
                * 
            FROM
                db1.customer PIVOT(min(c_salutation) AS pa_4abcb3 FOR c_email_address IN (('ntb2') AS pf_e9d715,
                ('lstntb2Gv7') AS pf_5b3584))
        ) AS r_923b88 
            ON r_bb24b2.c_birth_month > r_923b88.c_current_cdemo_sk,
        db1.income_band AS r_3fab74 
    INNER JOIN
        db1.store AS r_937498 
            ON r_3fab74.ib_upper_bound < r_937498.s_company_id 
    WHERE
        r_923b88.c_birth_month <= r_3fab74.ib_income_band_sk 
    ORDER BY
        1 DESC NULLS LAST) 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    r_6368c1.sr_return_amt_inc_tax as te_3a3791 
FROM
    db1.store_returns AS r_6368c1 
LEFT JOIN
    db1.income_band AS r_c743fc 
        ON r_6368c1.sr_customer_sk > r_c743fc.ib_upper_bound 
ORDER BY
    1 ASC NULLS LAST;
----------->
WITH CTE_af5574(te_388c4a, te_6e1690, te_7db8e9) AS (SELECT
    r_24fd73.s_store_sk as te_388c4a,
    r_240ac2.hd_demo_sk as te_6e1690,
    r_24fd73.s_store_id as te_7db8e9 
FROM
    db1.store AS r_24fd73,
    db1.store_returns AS r_1b5969 
INNER JOIN
    db1.household_demographics AS r_240ac2 
        ON r_1b5969.sr_cdemo_sk = r_240ac2.hd_vehicle_count 
WHERE
    r_24fd73.s_company_id >= r_1b5969.sr_cdemo_sk 
    OR r_24fd73.s_suite_number ILIKE '7x2SW4' 
    AND r_24fd73.s_closed_date_sk >= r_1b5969.sr_customer_sk 
    AND r_24fd73.s_rec_start_date != r_24fd73.s_rec_end_date 
    OR r_1b5969.sr_item_sk = r_24fd73.s_store_sk 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 NULLS LAST), CTE_b0aa7e(te_09e232, te_337d45) AS (SELECT
    r_88baf0.web_site_id as te_09e232, r_9fa677.te_4bb17c as te_337d45 
FROM
    db1.web_site AS r_88baf0,
    db1.ship_mode AS r_56ca51 
INNER JOIN
    (SELECT
        r_bf3d0c.ca_address_id as te_4bb17c 
    FROM
        db1.customer_address AS r_bf3d0c 
    WHERE
        r_bf3d0c.ca_street_type LIKE 'gGIHl' 
        AND r_bf3d0c.ca_suite_number NOT LIKE 'IvXUu' 
    ORDER BY
        1 ASC) AS r_9fa677 
        ON chr(r_56ca51.sm_ship_mode_sk) NOT ILIKE r_9fa677.te_4bb17c 
WHERE
    r_88baf0.web_mkt_id > r_56ca51.sm_ship_mode_sk 
    AND r_88baf0.web_city ILIKE 'C0le' 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST) SELECT
        r_f1e5f9.s_rec_end_date as te_8334b6, r_f1e5f9.s_gmt_offset as te_5856f4 
    FROM
        db1.store AS r_f1e5f9,
        CTE_af5574 AS r_42bacc 
    WHERE
        r_f1e5f9.s_closed_date_sk <= r_42bacc.te_388c4a 
        AND (
            NOT r_f1e5f9.s_hours ILIKE 'IvXUu' 
            OR r_f1e5f9.s_tax_percentage <= r_f1e5f9.s_gmt_offset
        ) 
        AND EXISTS (
            SELECT
                r_24b04f.web_tax_percentage as te_e736a6,
                r_24b04f.web_gmt_offset as te_fab651,
                to_char(mod(r_8b0ee9.c_current_cdemo_sk,
                22.67051001D),
                '000D00') as te_ea9033 
            FROM
                db1.customer AS r_8b0ee9,
                db1.web_site AS r_24b04f 
            WHERE
                r_8b0ee9.c_first_sales_date_sk < r_24b04f.web_mkt_id 
                AND r_8b0ee9.c_first_sales_date_sk > 0 
                OR r_24b04f.web_gmt_offset < r_24b04f.web_tax_percentage 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC
        ) 
        AND r_f1e5f9.s_gmt_offset != r_f1e5f9.s_tax_percentage 
    ORDER BY
        1 DESC NULLS LAST, 2 ASC;
----------->
SELECT
    r_ae0601.w_street_type as te_5c79a7,
    r_0e5be3.te_c3afe7 as te_a786e0,
    hash(r_ae0601.w_county,
    r_0e5be3.te_c3afe7) as te_39c85e 
FROM
    (SELECT
        make_date(r_c76912.cd_dep_employed_count,
        r_c76912.cd_demo_sk,
        r_c76912.cd_demo_sk) as te_c3afe7,
        r_4594f9.sm_ship_mode_sk as te_ae6e75,
        timestamp_millis(r_776b0d.sr_hdemo_sk) as te_43c94e 
    FROM
        db1.customer_demographics AS r_c76912,
        db1.ship_mode AS r_4594f9 
    RIGHT JOIN
        db1.store_returns AS r_776b0d 
            ON r_4594f9.sm_ship_mode_sk >= r_776b0d.sr_addr_sk 
    WHERE
        r_c76912.cd_dep_college_count >= r_776b0d.sr_return_quantity 
        OR r_4594f9.sm_ship_mode_id NOT ILIKE 'F' 
        OR r_c76912.cd_dep_employed_count != 63 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST 
    LIMIT 94) AS r_0e5be3 INNER JOIN
    db1.warehouse AS r_ae0601 
        ON r_0e5be3.te_ae6e75 != r_ae0601.w_warehouse_sk,
    db1.income_band AS r_8a6bee 
WHERE
    r_ae0601.w_gmt_offset = r_8a6bee.ib_lower_bound 
    OR r_ae0601.w_state LIKE r_ae0601.w_street_name 
    AND r_ae0601.w_county NOT LIKE 'tb2G' 
    OR r_8a6bee.ib_lower_bound != r_ae0601.w_warehouse_sk 
ORDER BY
    1, 2 DESC NULLS FIRST, 3;
----------->
SELECT
    timestamp_millis(r_d042a0.ib_lower_bound) as te_31f429,
    pi() as te_5829e1,
    r_d042a0.ib_lower_bound as te_2fcbf2 
FROM
    db1.customer_demographics AS r_b6c921,
    db1.reason AS r_d11664 
INNER JOIN
    db1.income_band AS r_d042a0 
        ON r_d11664.r_reason_sk > r_d042a0.ib_income_band_sk 
WHERE
    r_b6c921.cd_purchase_estimate >= r_d042a0.ib_upper_bound 
ORDER BY
    1 NULLS FIRST, 2 NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_2dc900.web_rec_end_date as te_e42bb3 
FROM
    db1.web_site AS r_2dc900 
WHERE
    r_2dc900.web_street_type LIKE 'M' 
    AND r_2dc900.web_gmt_offset NOT IN (
        SELECT
            92.565621 * r_2095e8.te_ec4e88 as te_d2f090 
        FROM
            (SELECT
                unix_timestamp() as te_ec4e88 
            FROM
                db1.store AS r_204cbc 
            INNER JOIN
                (
                    SELECT
                        r_7baf22.t_time_id as te_e5e903 
                    FROM
                        db1.time_dim AS r_7baf22 
                    WHERE
                        r_7baf22.t_time_id LIKE r_7baf22.t_shift 
                        OR r_7baf22.t_time_sk BETWEEN 3 AND r_7baf22.t_hour
                ) AS r_d49378 
                    ON r_204cbc.s_zip ILIKE r_d49378.te_e5e903 
            WHERE
                r_204cbc.s_division_id BETWEEN 32 AND 32 
                OR r_204cbc.s_rec_start_date < r_204cbc.s_rec_end_date 
                OR r_204cbc.s_street_type ILIKE r_204cbc.s_geography_class 
            ORDER BY
                1 DESC NULLS LAST) AS r_2095e8 
            LEFT JOIN
                db1.income_band AS r_73b14c 
                    ON r_2095e8.te_ec4e88 != r_73b14c.ib_income_band_sk 
            WHERE
                r_73b14c.ib_income_band_sk <= r_73b14c.ib_upper_bound 
                OR (
                    NOT r_73b14c.ib_upper_bound >= r_73b14c.ib_income_band_sk 
                    AND r_73b14c.ib_income_band_sk >= r_73b14c.ib_upper_bound
                )) 
            OR r_2dc900.web_rec_end_date <= r_2dc900.web_rec_start_date 
        ORDER BY
            1 ASC;
----------->
SELECT
    r_b9ed5a.ca_address_sk as te_b4d4fb 
FROM
    db1.reason AS r_d3c15e 
INNER JOIN
    db1.customer_address AS r_b9ed5a 
        ON r_d3c15e.r_reason_id LIKE r_b9ed5a.ca_city 
WHERE
    r_b9ed5a.ca_location_type NOT ILIKE 'bBRG' 
    OR r_b9ed5a.ca_address_sk = r_d3c15e.r_reason_sk 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 95;
----------->
SELECT
    r_c32033.cd_purchase_estimate as te_e1045c 
FROM
    db1.customer_demographics AS r_c32033 
WHERE
    r_c32033.cd_demo_sk <= r_c32033.cd_dep_count 
    AND r_c32033.cd_marital_status NOT ILIKE '4QH' 
    OR r_c32033.cd_dep_count < 0 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_74f2be.te_7d45d4 as te_0049fb,
    r_8ea9db.hd_dep_count as te_fcfeb6 
FROM
    db1.household_demographics AS r_8ea9db,
    (SELECT
        r_3a41ab.c_login as te_18d7b6,
        r_3a41ab.c_preferred_cust_flag as te_7d45d4 
    FROM
        db1.store_returns AS r_36c56b 
    LEFT JOIN
        db1.date_dim AS r_774baf 
            ON r_36c56b.sr_reversed_charge > r_774baf.d_year,
        db1.customer_demographics AS r_5481af 
    LEFT JOIN
        db1.customer AS r_3a41ab 
            ON r_5481af.cd_marital_status ILIKE r_3a41ab.c_customer_id 
    WHERE
        r_774baf.d_day_name NOT LIKE r_3a41ab.c_last_name 
        OR r_774baf.d_date_id NOT ILIKE 'gDD' 
        AND r_774baf.d_week_seq > r_774baf.d_quarter_seq 
        AND r_36c56b.sr_fee >= 14.39277637 
        OR r_5481af.cd_dep_college_count != 38 
    ORDER BY
        1 ASC NULLS FIRST, 2 DESC NULLS LAST) AS r_74f2be 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.ship_mode UNPIVOT EXCLUDE NULLS ((up_08be83,
            up_09056e) FOR upn_8ab4d6 IN ((sm_ship_mode_sk,
            sm_type) AS UPF_cbe379))
    ) AS r_ba53f9 
        ON r_74f2be.te_18d7b6 LIKE r_ba53f9.sm_code 
WHERE
    r_8ea9db.hd_vehicle_count > r_ba53f9.up_08be83 
    OR r_8ea9db.hd_demo_sk != r_8ea9db.hd_income_band_sk 
    AND r_8ea9db.hd_income_band_sk <= r_8ea9db.hd_vehicle_count 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_f12e2a.cc_mkt_class as te_4b6875,
    timestamp_millis(r_5e9dc9.hd_demo_sk) as te_a4c178 
FROM
    db1.call_center AS r_f12e2a 
LEFT JOIN
    db1.household_demographics AS r_5e9dc9 
        ON r_f12e2a.cc_company > r_5e9dc9.hd_dep_count,
    (SELECT
        'lezm' as te_cebd63 
    FROM
        (SELECT
            string(sin(r_cf6816.r_reason_sk)) as te_da2c83 
        FROM
            db1.reason AS r_cf6816 
        WHERE
            r_cf6816.r_reason_id LIKE 'GC0lezmSRI' 
            OR r_cf6816.r_reason_desc NOT ILIKE 'mSRI' 
            AND r_cf6816.r_reason_id NOT ILIKE r_cf6816.r_reason_desc 
            OR r_cf6816.r_reason_desc NOT ILIKE r_cf6816.r_reason_id) AS r_a5c297 
    WHERE
        r_a5c297.te_da2c83 NOT LIKE 'iizz2kGk4e' 
        OR r_a5c297.te_da2c83 NOT ILIKE r_a5c297.te_da2c83 
        AND r_a5c297.te_da2c83 NOT LIKE r_a5c297.te_da2c83 
    ORDER BY
        1 ASC NULLS FIRST) AS r_8ebe9e 
    LEFT JOIN
        db1.date_dim AS r_515063 
            ON r_8ebe9e.te_cebd63 ILIKE r_515063.d_holiday 
    WHERE
        r_f12e2a.cc_rec_end_date < r_515063.d_date 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_8563cd.c_last_name as te_1d19d6 
FROM
    db1.customer_demographics AS r_8751b2 
LEFT JOIN
    db1.customer AS r_8563cd 
        ON r_8751b2.cd_demo_sk >= r_8563cd.c_current_hdemo_sk 
WHERE
    r_8563cd.c_login NOT LIKE r_8563cd.c_first_name 
    AND r_8563cd.c_customer_id NOT ILIKE r_8751b2.cd_gender 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    date_diff(r_e48682.wp_rec_start_date,
    r_e48682.wp_rec_end_date) as te_b03c77 
FROM
    db1.income_band AS r_bf418b 
LEFT JOIN
    db1.web_page AS r_e48682 
        ON r_bf418b.ib_lower_bound > r_e48682.wp_access_date_sk 
WHERE
    r_e48682.wp_creation_date_sk = 21 
    OR r_e48682.wp_creation_date_sk > 27 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    chr(r_3bb664.hd_income_band_sk) as te_9577db,
    r_182cf8.d_day_name as te_45bef3 
FROM
    db1.date_dim AS r_182cf8,
    (SELECT
        * 
    FROM
        db1.household_demographics UNPIVOT EXCLUDE NULLS ((up_377e7d,
        up_6d35f5) FOR upn_bdc92d IN ((hd_vehicle_count,
        hd_buy_potential) AS UPF_25c5f9))) AS r_3bb664 
WHERE
    r_182cf8.d_quarter_name LIKE r_3bb664.upn_bdc92d 
    AND NOT r_182cf8.d_date_id NOT ILIKE 'RX' 
    AND r_3bb664.upn_bdc92d LIKE r_3bb664.up_6d35f5 
    OR r_182cf8.d_quarter_seq != 46 
    AND r_182cf8.d_day_name <= r_182cf8.d_current_month 
ORDER BY
    1 DESC, 2 ASC 
OFFSET 24;
----------->
SELECT
    r_94a73c.sm_ship_mode_sk as te_80f7c4 
FROM
    db1.store AS r_3bd0ba 
RIGHT JOIN
    db1.ship_mode AS r_94a73c 
        ON r_3bd0ba.s_number_employees != r_94a73c.sm_ship_mode_sk 
ORDER BY
    1 DESC;
----------->
SELECT
    r_95b5bf.te_99dd1f as te_28aec5,
    r_234916.cc_rec_end_date as te_ff591a,
    r_4e5dfa.ca_gmt_offset as te_9d7e5a 
FROM
    (SELECT
        unix_timestamp() as te_99dd1f,
        r_68cc5c.wp_rec_end_date as te_75f363,
        r_68cc5c.wp_creation_date_sk as te_6a40cb 
    FROM
        db1.ship_mode AS r_69f36d,
        db1.customer_address AS r_8cdc55 
    LEFT JOIN
        db1.web_page AS r_68cc5c 
            ON r_8cdc55.ca_location_type LIKE r_68cc5c.wp_autogen_flag 
    WHERE
        r_69f36d.sm_contract LIKE r_8cdc55.ca_suite_number 
        OR r_68cc5c.wp_web_page_sk = 40 
        AND r_68cc5c.wp_autogen_flag ILIKE r_8cdc55.ca_zip 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_95b5bf, db1.call_center AS r_234916 
RIGHT JOIN
    db1.customer_address AS r_4e5dfa 
        ON r_234916.cc_call_center_id NOT LIKE r_4e5dfa.ca_state 
WHERE
    r_95b5bf.te_75f363 != r_234916.cc_rec_start_date 
    OR r_4e5dfa.ca_address_sk < r_95b5bf.te_6a40cb 
    OR r_4e5dfa.ca_state NOT ILIKE r_4e5dfa.ca_street_type 
ORDER BY
    1, 2 DESC NULLS FIRST, 3 
OFFSET 18;
----------->
SELECT
    r_6e4fc2.w_county as te_70141f 
FROM
    db1.reason AS r_04f7a4 
LEFT JOIN
    db1.warehouse AS r_6e4fc2 
        ON r_04f7a4.r_reason_desc NOT ILIKE r_6e4fc2.w_warehouse_name 
WHERE
    NOT r_6e4fc2.w_gmt_offset BETWEEN 17.89534985 AND 17.89534985 
    AND NOT EXISTS (
        SELECT
            r_f0f674.cc_call_center_id as te_d913d1 
        FROM
            db1.call_center AS r_f0f674 
        INNER JOIN
            db1.ship_mode AS r_497f10 
                ON r_f0f674.cc_mkt_id >= r_497f10.sm_ship_mode_sk 
        WHERE
            r_f0f674.cc_street_name LIKE 'ir98hv95G6' 
            OR r_f0f674.cc_rec_end_date IS NULL 
        GROUP BY
            1 
        ORDER BY
            1 DESC
    ) 
    AND r_04f7a4.r_reason_sk <= 86 
ORDER BY
    1 NULLS LAST 
OFFSET 62;
----------->
SELECT
    add_months(r_b0cc09.s_rec_start_date,
    unix_timestamp()) as te_09f8c1 
FROM
    db1.store AS r_b0cc09 
LEFT JOIN
    db1.store AS r_b9168e 
        ON r_b0cc09.s_gmt_offset != r_b9168e.s_gmt_offset 
WHERE
    r_b9168e.s_street_number NOT LIKE 'aLQFrTUOV' 
    AND r_b9168e.s_rec_end_date > DATE'2024-03-25' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_cef6f0.web_gmt_offset as te_9aa1d6,
    r_77e569.cd_marital_status as te_6d95cb,
    r_cef6f0.web_rec_end_date as te_0f9211 
FROM
    db1.web_site AS r_cef6f0,
    db1.customer_demographics AS r_77e569 
WHERE
    r_cef6f0.web_site_id >= r_77e569.cd_credit_rating 
    AND r_cef6f0.web_gmt_offset IS NOT NULL 
ORDER BY
    1 DESC, 2 DESC, 3 DESC NULLS FIRST;
----------->
SELECT
    r_0e1059.s_store_sk as te_4e5282 
FROM
    db1.store AS r_0e1059 
RIGHT JOIN
    (
        WITH CTE_deff27(te_22dfc2, te_634c0b, te_daf02a) AS (SELECT
            r_7ccccc.t_meal_time as te_22dfc2,
            r_7ccccc.t_am_pm as te_634c0b,
            r_7ccccc.t_time as te_daf02a 
        FROM
            db1.time_dim AS r_7ccccc 
        INNER JOIN
            db1.time_dim AS r_3c4852 
                ON r_7ccccc.t_sub_shift ILIKE r_3c4852.t_time_id,
            db1.income_band AS r_f47796 
        WHERE
            r_f47796.ib_lower_bound <= 97 
            AND r_7ccccc.t_second != r_7ccccc.t_time 
            OR r_7ccccc.t_time_id >= r_7ccccc.t_meal_time) SELECT
            timestamp_seconds(48 - r_d20b66.i_brand_id) as te_f0dc88,
            sin(r_d20b66.i_current_price) as te_4106e2 
        FROM
            (SELECT
                * 
            FROM
                CTE_deff27 PIVOT(min(te_22dfc2) AS pa_dc819e FOR te_daf02a IN ((20) AS pf_b2481f,
                (84) AS pf_40517a,
                (31) AS pf_c6ac10))) AS r_ac6ef9,
            db1.item AS r_d20b66 
        WHERE
            r_ac6ef9.pf_b2481f LIKE r_d20b66.i_units 
            AND r_d20b66.i_manager_id = r_d20b66.i_category_id 
        ORDER BY
            1 ASC NULLS LAST, 2 ASC NULLS LAST) AS r_34569c 
                ON chr(length(r_0e1059.s_company_name)) LIKE chr(month(r_34569c.te_f0dc88)) 
        WHERE
            r_0e1059.s_street_number LIKE 'zm' 
        ORDER BY
            1 DESC NULLS LAST 
        OFFSET 10;
----------->
SELECT
    r_eafcf8.i_item_sk as te_04874e 
FROM
    db1.item AS r_eafcf8 
WHERE
    r_eafcf8.i_rec_end_date BETWEEN DATE'2024-03-25' AND r_eafcf8.i_rec_end_date 
    OR r_eafcf8.i_manufact_id > 97 
    AND r_eafcf8.i_color ILIKE r_eafcf8.i_category 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_c3e39b.wp_customer_sk + 47 as te_8bffb7,
    r_c3e39b.wp_rec_end_date as te_a8b984,
    date_from_unix_date(r_12ce42.hd_income_band_sk) as te_7d5b00 
FROM
    db1.household_demographics AS r_12ce42 
INNER JOIN
    db1.web_page AS r_c3e39b 
        ON r_12ce42.hd_vehicle_count >= r_c3e39b.wp_link_count,
    db1.income_band AS r_e94a9d 
WHERE
    r_c3e39b.wp_char_count < r_e94a9d.ib_income_band_sk 
ORDER BY
    1 ASC NULLS FIRST, 2, 3 DESC;
----------->
SELECT
    r_eda941.i_rec_start_date as te_53e5a9 
FROM
    db1.item AS r_eda941 
INNER JOIN
    db1.household_demographics AS r_cd6306 
        ON r_eda941.i_size NOT ILIKE r_cd6306.hd_buy_potential 
ORDER BY
    1 NULLS FIRST;
----------->
(
    SELECT
        r_961799.sr_return_amt as te_d7b869,
        year(r_804de8.cc_rec_start_date) as te_cb4cb2,
        r_804de8.cc_name as te_28cfeb 
    FROM
        db1.call_center AS r_804de8,
        db1.store_returns AS r_961799 
    WHERE
        NOT r_804de8.cc_open_date_sk = r_961799.sr_returned_date_sk 
        AND r_804de8.cc_rec_end_date = r_804de8.cc_rec_start_date 
        OR r_804de8.cc_open_date_sk != 97 
        AND r_804de8.cc_mkt_id != r_961799.sr_reason_sk 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 NULLS LAST
) INTERSECT  (SELECT
    r_e26b8c.w_gmt_offset as te_564c25, r_6f68f2.hd_demo_sk as te_fa2930, r_e26b8c.w_state as te_8d75e9 
FROM
    db1.warehouse AS r_e26b8c 
RIGHT JOIN
    (SELECT
        * 
    FROM
        db1.customer_address PIVOT(max(ca_address_id) AS pa_56b2b7 FOR ca_street_name IN (('gGIHlp') AS pf_02fe9c,
        ('6kg7x2SW4') AS pf_1a654d))) AS r_ec3748 
        ON r_e26b8c.w_zip ILIKE r_ec3748.ca_location_type,
    db1.income_band AS r_6ffbaa 
LEFT JOIN
    db1.household_demographics AS r_6f68f2 
        ON r_6ffbaa.ib_lower_bound >= r_6f68f2.hd_dep_count 
WHERE
    r_e26b8c.w_warehouse_sk > r_6f68f2.hd_income_band_sk 
    AND r_6f68f2.hd_buy_potential NOT LIKE '4' 
    AND r_6ffbaa.ib_income_band_sk > 24 
    OR r_e26b8c.w_street_number LIKE 'z2kGk4' 
ORDER BY
    1 NULLS LAST, 2 ASC, 3 DESC) 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST, 3 DESC;
----------->
SELECT
    r_922426.ca_state as te_9245fb,
    r_922426.ca_gmt_offset as te_0d89d0,
    make_date(r_36bf20.cd_purchase_estimate,
    r_790ff9.t_time_sk,
    r_790ff9.t_second) as te_509264 
FROM
    db1.customer_address AS r_922426 
INNER JOIN
    db1.customer_demographics AS r_36bf20 
        ON r_36bf20.cd_demo_sk IS NOT NULL,
    db1.time_dim AS r_790ff9 
WHERE
    r_36bf20.cd_dep_college_count >= r_790ff9.t_minute 
    AND r_922426.ca_gmt_offset <= 13.00565411 
    OR r_36bf20.cd_dep_college_count >= 34 
    AND r_922426.ca_street_number NOT LIKE r_922426.ca_zip 
    OR r_36bf20.cd_gender ILIKE 'aLQF' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    chr(r_7bb9df.sr_cdemo_sk) as te_0fdd2d 
FROM
    db1.store_returns AS r_7bb9df 
RIGHT JOIN
    (
        SELECT
            bigint(negative(sin(48.40946289D + r_913d25.cd_demo_sk))) as te_946594 
        FROM
            db1.customer_demographics AS r_913d25 
        LEFT JOIN
            db1.reason AS r_7c7936 
                ON r_913d25.cd_education_status = r_7c7936.r_reason_id 
        WHERE
            r_7c7936.r_reason_desc NOT LIKE r_7c7936.r_reason_id
    ) AS r_d180e3 
        ON r_7bb9df.sr_fee >= r_d180e3.te_946594 
ORDER BY
    1 NULLS LAST 
OFFSET 76;
----------->
SELECT
    r_a8484a.i_manufact_id as te_f4343e 
FROM
    db1.item AS r_a8484a 
ORDER BY
    1 ASC NULLS LAST 
OFFSET 90;
----------->
SELECT
    unix_timestamp() - r_7af31a.te_efc51c + r_b9a177.t_minute + 39 as te_ce0b9d 
FROM
    db1.time_dim AS r_b9a177 
LEFT JOIN
    (
        SELECT
            (9.49303255D - r_2022f0.sm_ship_mode_sk) / (r_3c30bd.cd_dep_college_count + r_2022f0.sm_ship_mode_sk - 15) as te_efc51c 
        FROM
            db1.customer_demographics AS r_3c30bd 
        INNER JOIN
            db1.ship_mode AS r_2022f0 
                ON r_3c30bd.cd_dep_employed_count < r_2022f0.sm_ship_mode_sk 
        WHERE
            r_3c30bd.cd_education_status LIKE r_3c30bd.cd_credit_rating 
        ORDER BY
            1 DESC NULLS FIRST
    ) AS r_7af31a 
        ON r_b9a177.t_time != r_7af31a.te_efc51c 
WHERE
    r_b9a177.t_meal_time LIKE r_b9a177.t_shift 
    AND r_b9a177.t_hour < 14 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_5d520b.ca_gmt_offset as te_f5090f,
    r_5d520b.ca_gmt_offset as te_8260b2 
FROM
    db1.customer_address AS r_5d520b 
INNER JOIN
    (
        SELECT
            date_add(r_01ac9f.i_rec_end_date,
            r_01ac9f.i_item_sk) as te_f4f293 
        FROM
            (SELECT
                r_f83f96.wp_creation_date_sk as te_0b902e 
            FROM
                (SELECT
                    character_length(r_c9cd5a.cc_class) as te_dd7e5c 
                FROM
                    db1.call_center AS r_c9cd5a 
                ORDER BY
                    1 DESC NULLS LAST 
                LIMIT 68) AS r_bb8999 LEFT JOIN
                db1.web_page AS r_f83f96 
                    ON r_bb8999.te_dd7e5c = r_f83f96.wp_char_count 
            WHERE
                r_f83f96.wp_rec_end_date != DATE'2024-03-25' 
            ORDER BY
                1) AS r_065655 
            INNER JOIN
                db1.item AS r_01ac9f 
                    ON r_065655.te_0b902e != r_01ac9f.i_manufact_id 
            ORDER BY
                1 DESC NULLS LAST) AS r_20520b 
                ON chr(length(r_5d520b.ca_city)) != chr(year(r_20520b.te_f4f293)), db1.customer AS r_e4bac1 
        WHERE
            r_5d520b.ca_address_sk = r_e4bac1.c_current_hdemo_sk 
            OR (
                NOT r_e4bac1.c_salutation ILIKE r_5d520b.ca_city 
                AND r_e4bac1.c_birth_day <= 77
            ) 
        ORDER BY
            1, 2 NULLS LAST;
----------->
SELECT
    r_406a8d.w_gmt_offset - 86 - r_4c4918.hd_income_band_sk as te_2b358e 
FROM
    db1.household_demographics AS r_4c4918 
INNER JOIN
    db1.warehouse AS r_406a8d 
        ON r_4c4918.hd_income_band_sk = r_406a8d.w_warehouse_sk 
WHERE
    r_4c4918.hd_income_band_sk > 37 
    OR r_406a8d.w_zip NOT LIKE 'zz2' 
ORDER BY
    1 NULLS LAST 
OFFSET 35;
----------->
SELECT
    r_5f8a3c.sm_ship_mode_sk as te_8ad545 
FROM
    db1.reason AS r_6e1a81 
INNER JOIN
    db1.ship_mode AS r_5f8a3c 
        ON r_6e1a81.r_reason_id LIKE r_5f8a3c.sm_code 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    mod(71,
    r_4f0f38.ib_lower_bound) as te_58187d,
    r_4f0f38.ib_upper_bound * r_a011da.c_first_sales_date_sk as te_da01a8,
    chr(r_a011da.c_last_review_date_sk) as te_503bd6 
FROM
    (SELECT
        r_137d01.c_login as te_25f989 
    FROM
        db1.customer AS r_137d01 
    ORDER BY
        1 DESC NULLS LAST) AS r_1ec6e4, db1.income_band AS r_4f0f38 
LEFT JOIN
    db1.customer AS r_a011da 
        ON r_4f0f38.ib_income_band_sk = r_a011da.c_first_shipto_date_sk 
WHERE
    r_1ec6e4.te_25f989 NOT LIKE r_a011da.c_salutation 
    AND r_a011da.c_customer_id LIKE r_a011da.c_preferred_cust_flag 
    AND r_a011da.c_current_addr_sk <= 0 
ORDER BY
    1 NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_f5a0da.s_market_manager as te_afcbca 
FROM
    db1.ship_mode AS r_14834a 
INNER JOIN
    db1.store AS r_f5a0da 
        ON r_14834a.sm_code ILIKE r_f5a0da.s_zip 
WHERE
    r_f5a0da.s_gmt_offset != 19.03770464 
    OR r_f5a0da.s_country NOT LIKE 'RX' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_7c423d.i_rec_start_date as te_e31528 
FROM
    db1.call_center AS r_12fedc 
INNER JOIN
    db1.item AS r_7c423d 
        ON r_12fedc.cc_hours NOT ILIKE r_7c423d.i_brand 
WHERE
    r_12fedc.cc_division = r_12fedc.cc_mkt_id 
    AND r_7c423d.i_wholesale_cost > r_12fedc.cc_tax_percentage 
    AND NOT r_7c423d.i_manufact_id >= 69 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    current_date() as te_948428 
FROM
    db1.web_site AS r_ed1ccf 
WHERE
    r_ed1ccf.web_tax_percentage = r_ed1ccf.web_gmt_offset 
    OR r_ed1ccf.web_open_date_sk > 21 
    OR r_ed1ccf.web_mkt_id = 16 
    OR r_ed1ccf.web_mkt_id BETWEEN 81 AND r_ed1ccf.web_open_date_sk 
GROUP BY
    1 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    try_add(r_057669.cc_open_date_sk,
    r_057669.cc_rec_end_date) as te_4f3e09 
FROM
    db1.call_center AS r_057669 
WHERE
    r_057669.cc_rec_end_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
    OR r_057669.cc_rec_end_date != r_057669.cc_rec_start_date 
    AND r_057669.cc_sq_ft < 2 
    OR r_057669.cc_company < 0 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_8e7b17.t_time_sk as te_7f3ae3 
FROM
    db1.time_dim AS r_8e7b17 
WHERE
    r_8e7b17.t_minute <= r_8e7b17.t_second 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    string(r_4c7083.s_gmt_offset) as te_3d4158 
FROM
    db1.store AS r_4c7083 
WHERE
    r_4c7083.s_floor_space != 32 
    AND r_4c7083.s_rec_start_date < r_4c7083.s_rec_end_date 
    OR r_4c7083.s_rec_end_date > r_4c7083.s_rec_start_date 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_0d13eb.sr_return_tax as te_f7580c,
    r_1bec0c.cc_mkt_class as te_7f44f1 
FROM
    db1.call_center AS r_1bec0c 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.store_returns PIVOT(stddev(sr_return_amt) AS pa_d2031a FOR sr_net_loss IN ((12.65310847) AS pf_2b6353,
            (38.38422369) AS pf_335863))
    ) AS r_0d13eb 
        ON r_1bec0c.cc_call_center_sk <= r_0d13eb.sr_item_sk,
    db1.customer_demographics AS r_d16ec0 
WHERE
    r_1bec0c.cc_gmt_offset = r_d16ec0.cd_dep_employed_count 
    AND r_1bec0c.cc_mkt_id != 47 
    AND r_1bec0c.cc_company >= 32 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_c90717.te_426338 as te_2de0f8,
    r_4cee73.web_site_id as te_ba5c71,
    r_4cee73.web_site_sk / e() as te_bb52c1 
FROM
    db1.ship_mode AS r_c37aad,
    (SELECT
        r_57849e.d_date as te_5fb104,
        mod(r_6380d1.d_qoy,
        r_6380d1.d_fy_week_seq - 60) as te_426338,
        r_6380d1.d_year as te_671d27 
    FROM
        db1.date_dim AS r_57849e,
        db1.date_dim AS r_cdffff 
    LEFT JOIN
        db1.date_dim AS r_6380d1 
            ON r_cdffff.d_first_dom >= r_6380d1.d_year 
    WHERE
        r_57849e.d_date < r_cdffff.d_date 
        OR r_cdffff.d_dow <= 69 
        AND r_57849e.d_date < r_cdffff.d_date 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC) AS r_c90717 
INNER JOIN
    db1.web_site AS r_4cee73 
        ON r_c90717.te_5fb104 != r_4cee73.web_rec_start_date 
WHERE
    r_c37aad.sm_ship_mode_sk >= r_c90717.te_671d27 
    AND r_4cee73.web_county ILIKE 'XpRQiizz2' 
    OR r_4cee73.web_gmt_offset >= r_4cee73.web_tax_percentage 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_7dbfdc.wp_web_page_id as te_40d5f1,
    hash(true,
    current_timestamp()) as te_4cfa49 
FROM
    db1.web_page AS r_7dbfdc,
    (SELECT
        r_844ba1.ib_income_band_sk as te_355411 
    FROM
        db1.income_band AS r_844ba1 
    WHERE
        r_844ba1.ib_lower_bound != 5 
        OR r_844ba1.ib_lower_bound != 68 
        OR r_844ba1.ib_income_band_sk < r_844ba1.ib_lower_bound 
    ORDER BY
        1) AS r_c4d9e6 
WHERE
    r_7dbfdc.wp_char_count >= r_c4d9e6.te_355411 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST 
LIMIT 44;
----------->
SELECT
    reverse(r_f49b1e.web_mkt_class) as te_8806cc 
FROM
    db1.web_site AS r_f49b1e 
WHERE
    r_f49b1e.web_site_sk < 94 
    OR r_f49b1e.web_mkt_class LIKE 'SW4P' 
    OR r_f49b1e.web_tax_percentage = r_f49b1e.web_gmt_offset 
    AND r_f49b1e.web_country ILIKE 'XpRQ' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_2ca050.t_time as te_5c922f 
FROM
    db1.ship_mode AS r_68d75d 
LEFT JOIN
    db1.time_dim AS r_2ca050 
        ON r_68d75d.sm_ship_mode_id ILIKE r_2ca050.t_am_pm 
WHERE
    r_2ca050.t_sub_shift NOT LIKE r_68d75d.sm_contract 
    OR r_2ca050.t_minute = r_2ca050.t_time 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 13;
----------->
SELECT
    hash(r_d082f5.sr_reason_sk,
    DATE'2024-03-25') as te_7c1e86 
FROM
    db1.time_dim AS r_991405 
RIGHT JOIN
    db1.store_returns AS r_d082f5 
        ON r_991405.t_minute = r_d082f5.sr_reason_sk 
WHERE
    r_d082f5.sr_item_sk > 81 
    AND r_d082f5.sr_item_sk >= 58 
    OR r_991405.t_meal_time NOT LIKE r_991405.t_shift 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_f3a18b.d_dom as te_087791,
    r_f3a18b.d_first_dom as te_cb9644,
    r_b335d2.w_gmt_offset as te_043d27 
FROM
    db1.warehouse AS r_3a9dd6,
    db1.warehouse AS r_b335d2 
RIGHT JOIN
    db1.date_dim AS r_f3a18b 
        ON r_b335d2.w_suite_number NOT LIKE r_f3a18b.d_holiday 
WHERE
    NOT r_3a9dd6.w_county LIKE r_b335d2.w_county 
    AND r_f3a18b.d_same_day_lq != r_f3a18b.d_fy_quarter_seq 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_ebbb20.w_warehouse_name as te_1007e1 
FROM
    db1.warehouse AS r_ebbb20 
RIGHT JOIN
    db1.date_dim AS r_a8eaad 
        ON r_ebbb20.w_street_type LIKE r_a8eaad.d_current_month 
WHERE
    r_a8eaad.d_quarter_name ILIKE 'lezmS' 
    OR r_a8eaad.d_weekend NOT LIKE r_a8eaad.d_current_month 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_06822f.w_street_type as te_9bb619 
FROM
    db1.customer_demographics AS r_ac0d8c 
LEFT JOIN
    db1.warehouse AS r_06822f 
        ON r_ac0d8c.cd_dep_employed_count <= r_06822f.w_gmt_offset 
WHERE
    r_06822f.w_county NOT ILIKE '2k' 
    AND (
        NOT r_06822f.w_warehouse_sk < 53 
        OR r_06822f.w_warehouse_id NOT LIKE r_06822f.w_street_number
    ) 
ORDER BY
    1;
----------->
WITH CTE_1b6194(te_d85d12) AS (SELECT
    r_73ca01.te_2a7e1c as te_d85d12 
FROM
    (SELECT
        r_4bac91.sr_return_ship_cost as te_2a7e1c,
        r_46d583.hd_buy_potential as te_e44bd6 
    FROM
        db1.household_demographics AS r_46d583,
        db1.store_returns AS r_4bac91 
    INNER JOIN
        db1.household_demographics AS r_c27312 
            ON r_4bac91.sr_returned_date_sk <= r_c27312.hd_dep_count 
    WHERE
        r_46d583.hd_demo_sk = r_4bac91.sr_customer_sk 
    ORDER BY
        1 DESC NULLS LAST, 2) AS r_73ca01 
LEFT JOIN
    db1.ship_mode AS r_71f357 
        ON r_73ca01.te_2a7e1c != r_71f357.sm_ship_mode_sk 
WHERE
    r_71f357.sm_type LIKE 'sUg' 
    AND r_71f357.sm_code ILIKE '6sUgDD' 
    AND r_73ca01.te_e44bd6 LIKE r_71f357.sm_carrier 
    AND r_73ca01.te_2a7e1c = ((SELECT
        93.68407379 as te_01cdd9 
    FROM
        db1.reason AS r_9cc125 
    WHERE
        r_9cc125.r_reason_id NOT LIKE 'RQiizz2' 
        OR r_9cc125.r_reason_id ILIKE 'V' 
        OR r_9cc125.r_reason_sk != ((SELECT
            r_b23857.up_4cc328 as te_18577d 
        FROM
            db1.customer_address AS r_fc4916 
        LEFT JOIN
            (SELECT
                * 
            FROM
                db1.time_dim UNPIVOT ((up_564f9b,
                up_4cc328) FOR upn_426535 IN ((t_meal_time,
                t_time_sk) AS UPF_fc8453,
                (t_sub_shift,
                t_hour) AS UPF_7bcc8b,
                (t_time_id,
                t_time) AS UPF_407b97,
                (t_shift,
                t_second) AS UPF_973743,
                (t_am_pm,
                t_minute) AS UPF_aed646))) AS r_b23857 
                ON r_fc4916.ca_address_sk != r_b23857.up_4cc328 
        ORDER BY
            1 
        LIMIT 1)) 
    OR r_9cc125.r_reason_desc LIKE 'g7x2SW4P' ORDER BY
        1 NULLS LAST 
    LIMIT 1))) SELECT
    timestamp_millis(r_eb1f40.t_time_sk) as te_462f42 FROM
        CTE_1b6194 AS r_320a89 
    INNER JOIN
        db1.time_dim AS r_eb1f40 
            ON r_320a89.te_d85d12 != r_eb1f40.t_minute 
    ORDER BY
        1 DESC NULLS LAST;
----------->
SELECT
    r_68066e.c_preferred_cust_flag as te_b819ba,
    r_d9c75b.up_0b1d79 as te_17ef17,
    r_957239.w_warehouse_sk as te_0251ea 
FROM
    (SELECT
        * 
    FROM
        db1.customer_address UNPIVOT INCLUDE NULLS ((up_6bf4c8,
        up_822e7f,
        up_0b1d79,
        up_44e03e) FOR upn_8e98ce IN ((ca_address_sk,
        ca_street_type,
        ca_gmt_offset,
        ca_county) AS UPF_c2f7bc))) AS r_d9c75b 
INNER JOIN
    db1.warehouse AS r_957239 
        ON r_d9c75b.up_0b1d79 = r_957239.w_gmt_offset,
    db1.customer AS r_68066e 
RIGHT JOIN
    db1.customer AS r_1c4fe1 
        ON r_68066e.c_preferred_cust_flag LIKE r_1c4fe1.c_customer_id 
WHERE
    r_d9c75b.ca_location_type > r_1c4fe1.c_preferred_cust_flag 
    OR r_68066e.c_last_name NOT ILIKE 'C0lezmSRIv' 
    OR r_1c4fe1.c_first_name LIKE 'XX' 
    AND r_1c4fe1.c_email_address ILIKE 'Gk4elBy' 
    AND r_1c4fe1.c_birth_month BETWEEN 54 AND r_d9c75b.up_6bf4c8 + 32 
ORDER BY
    1 NULLS LAST, 2 NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    make_timestamp(r_f4d7ba.sr_store_sk,
    r_77c9e3.cd_demo_sk,
    r_fff191.hd_dep_count,
    r_f4d7ba.sr_store_sk,
    r_f4d7ba.sr_reason_sk,
    r_f4d7ba.sr_ticket_number) as te_f6d7f3,
    r_f4d7ba.sr_return_tax as te_b8957e,
    chr(r_f4d7ba.web_county) as te_8061af 
FROM
    db1.store_returns AS r_f4d7ba 
RIGHT JOIN
    db1.customer_demographics AS r_77c9e3 
        ON r_f4d7ba.sr_store_sk != r_77c9e3.cd_dep_count,
    db1.household_demographics AS r_fff191 
WHERE
    r_77c9e3.cd_purchase_estimate != r_fff191.hd_income_band_sk 
    AND r_f4d7ba.sr_store_sk > 53 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 
OFFSET 77;
----------->
SELECT
    r_dc45ff.web_gmt_offset as te_bc6a9c 
FROM
    db1.ship_mode AS r_7da8ac 
LEFT JOIN
    db1.web_site AS r_dc45ff 
        ON r_7da8ac.sm_ship_mode_sk > r_dc45ff.web_close_date_sk 
WHERE
    r_dc45ff.web_state LIKE r_dc45ff.web_site_id 
    AND r_dc45ff.web_site_id LIKE r_dc45ff.web_state 
ORDER BY
    1 ASC NULLS LAST;
----------->
WITH CTE_b6d205(te_5e9b97) AS (SELECT
    r_7791b3.r_reason_sk as te_5e9b97 
FROM
    db1.reason AS r_7791b3 
WHERE
    r_7791b3.r_reason_desc NOT LIKE r_7791b3.r_reason_id 
ORDER BY
    1 NULLS FIRST), CTE_b87238(te_a5eccd) AS (SELECT
    decimal(r_56512d.sr_store_sk) as te_a5eccd 
FROM
    db1.store_returns AS r_56512d 
INNER JOIN
    (SELECT
        r_6ae368.ca_gmt_offset as te_e218b0,
        r_b3b169.hd_income_band_sk as te_92479c,
        r_6ae368.ca_county as te_6963d2 
    FROM
        db1.household_demographics AS r_b3b169 
    RIGHT JOIN
        db1.customer_address AS r_6ae368 
            ON r_b3b169.hd_buy_potential NOT LIKE r_6ae368.ca_location_type,
        db1.ship_mode AS r_ff54d7 
    WHERE
        r_b3b169.hd_dep_count < r_ff54d7.sm_ship_mode_sk 
        AND r_6ae368.ca_street_type ILIKE 'rTUOV' 
        AND r_6ae368.ca_state NOT ILIKE 'hv95G' 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_07a6ca 
        ON r_56512d.sr_returned_date_sk > r_07a6ca.te_e218b0 
WHERE
    r_56512d.sr_customer_sk <= 57 
    OR r_07a6ca.te_92479c < r_56512d.sr_store_sk 
ORDER BY
    1 NULLS LAST) SELECT
        r_be250f.te_5e9b97 as te_833ecd, r_941d60.te_a5eccd as te_32af8a, r_941d60.te_a5eccd as te_22ca55 
    FROM
        CTE_b6d205 AS r_be250f,
        CTE_b87238 AS r_941d60 
    WHERE
        to_char(r_be250f.te_5e9b97, '999') <= r_941d60.te_a5eccd 
        AND r_941d60.te_a5eccd > 37.91871843 
        OR r_be250f.te_5e9b97 = 52 
        OR r_941d60.te_a5eccd < 5.43641366 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST 
    LIMIT 3;
----------->
SELECT
    r_d3566f.hd_buy_potential as te_236657,
    r_6a07b3.d_month_seq as te_30686f,
    r_6a07b3.d_date as te_ad416a 
FROM
    (SELECT
        reverse(chr(r_c3dad0.i_manager_id)) as te_d3d54a,
        r_61cabc.web_state as te_a85ab8 
    FROM
        db1.customer AS r_73067e 
    RIGHT JOIN
        db1.web_site AS r_61cabc 
            ON r_73067e.c_preferred_cust_flag ILIKE r_61cabc.web_class,
        db1.item AS r_c3dad0 
    WHERE
        r_61cabc.web_rec_start_date = r_c3dad0.i_rec_end_date 
        AND (
            NOT r_61cabc.web_site_id >= r_61cabc.web_state 
            AND r_61cabc.web_site_id LIKE r_c3dad0.i_item_id 
            AND r_c3dad0.i_units ILIKE r_c3dad0.i_product_name
        ) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST) AS r_3f427b 
LEFT JOIN
    db1.ship_mode AS r_251c48 
        ON r_3f427b.te_d3d54a NOT ILIKE r_251c48.sm_code,
    db1.household_demographics AS r_d3566f 
RIGHT JOIN
    db1.date_dim AS r_6a07b3 
        ON r_d3566f.hd_buy_potential LIKE r_6a07b3.d_current_month 
WHERE
    r_3f427b.te_a85ab8 LIKE r_6a07b3.d_current_day 
    OR r_6a07b3.d_fy_year BETWEEN 29 AND r_6a07b3.d_fy_quarter_seq / 61 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC 
OFFSET 17;
----------->
SELECT
    r_7ff0da.web_city as te_ff5c16,
    r_f14595.d_first_dom as te_6e565a,
    chr(unix_timestamp() + count(e() * (r_f14595.d_quarter_seq - 69.70251111D))) as te_123036 
FROM
    db1.web_site AS r_7ff0da 
RIGHT JOIN
    db1.date_dim AS r_f14595 
        ON r_7ff0da.web_open_date_sk != r_f14595.d_dom,
    (SELECT
        r_010e73.cd_education_status as te_9268a6 
    FROM
        db1.income_band AS r_a23120 
    INNER JOIN
        db1.customer_demographics AS r_010e73 
            ON r_a23120.ib_upper_bound >= r_010e73.cd_dep_college_count 
    ORDER BY
        1 DESC 
    OFFSET 83) AS r_7169ec WHERE
    r_f14595.d_current_quarter NOT ILIKE r_7169ec.te_9268a6 
    OR r_f14595.d_weekend LIKE r_7ff0da.web_site_id 
    AND r_7ff0da.web_rec_start_date != r_f14595.d_date 
GROUP BY
    r_7ff0da.web_city, r_f14595.d_first_dom 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS LAST;
----------->
SELECT
    r_1a25ec.t_time_id as te_300d03 
FROM
    db1.time_dim AS r_1a25ec 
WHERE
    (
        NOT r_1a25ec.t_second BETWEEN 9 AND 9 
        OR r_1a25ec.t_hour != 50 
        OR r_1a25ec.t_second > 86
    ) 
ORDER BY
    1 DESC NULLS LAST;
----------->
(
    SELECT
        btrim(r_a575fb.i_item_id) as te_424063 
    FROM
        (SELECT
            make_date(r_a35727.c_birth_day,
            r_a35727.c_first_shipto_date_sk,
            r_a35727.c_birth_year) as te_f57d91,
            r_f3868b.w_gmt_offset * r_a35727.c_first_shipto_date_sk as te_97fa31,
            e() as te_976331 
        FROM
            db1.customer AS r_a35727,
            db1.warehouse AS r_f3868b 
        WHERE
            r_a35727.c_first_shipto_date_sk < r_f3868b.w_warehouse_sq_ft 
            AND r_f3868b.w_street_number NOT LIKE r_a35727.c_email_address 
            AND r_a35727.c_last_name NOT ILIKE 'IvX' 
            OR r_a35727.c_first_shipto_date_sk != r_f3868b.w_warehouse_sk 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC, 3 DESC NULLS LAST) AS r_b74977, db1.item AS r_a575fb 
    WHERE
        r_b74977.te_f57d91 >= r_a575fb.i_rec_end_date 
    ORDER BY
        1 DESC NULLS LAST) 
    UNION
    ALL (
        SELECT
            r_0fa04d.w_county as te_82ebbc 
        FROM
            db1.warehouse AS r_0fa04d 
        WHERE
            r_0fa04d.w_warehouse_sq_ft < r_0fa04d.w_warehouse_sk 
        ORDER BY
            1 DESC NULLS LAST
    ) 
ORDER BY
    1 ASC NULLS LAST 
LIMIT 22;
----------->
SELECT
    r_1e1e9e.cc_state as te_1ee1c3,
    current_date() as te_675af2 
FROM
    db1.customer_address AS r_be7d0e 
LEFT JOIN
    db1.store_returns AS r_5fa883 
        ON r_be7d0e.ca_address_sk >= r_5fa883.sr_ticket_number,
    db1.call_center AS r_1e1e9e 
RIGHT JOIN
    db1.customer_demographics AS r_12bab4 
        ON r_1e1e9e.cc_open_date_sk >= r_12bab4.cd_dep_employed_count 
WHERE
    r_be7d0e.ca_city NOT LIKE r_1e1e9e.cc_street_number 
    OR r_5fa883.sr_hdemo_sk >= 56 
    AND r_5fa883.sr_return_amt < 30.50586377 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_244093.up_dffcef as te_dbe6cb,
    r_244093.sr_return_amt_inc_tax as te_c796b8 
FROM
    db1.customer_address AS r_04f59d 
RIGHT JOIN
    db1.customer AS r_abaad1 
        ON r_04f59d.ca_location_type LIKE r_abaad1.c_preferred_cust_flag,
    (SELECT
        * 
    FROM
        db1.store_returns UNPIVOT EXCLUDE NULLS ((up_dffcef,
        up_9a4d11) FOR upn_0e0476 IN ((sr_store_sk,
        sr_return_ship_cost) AS UPF_9c323b,
        (sr_addr_sk,
        sr_return_tax) AS UPF_509dcb,
        (sr_return_quantity,
        sr_store_credit) AS UPF_a2b149,
        (sr_customer_sk,
        sr_reversed_charge) AS UPF_745a80,
        (sr_ticket_number,
        sr_refunded_cash) AS UPF_25273b,
        (sr_reason_sk,
        web_county) AS UPF_cdab5f))) AS r_244093 
WHERE
    r_04f59d.ca_gmt_offset < r_244093.up_9a4d11 
    AND r_04f59d.ca_street_number NOT ILIKE r_04f59d.ca_street_type 
    OR r_abaad1.c_last_review_date_sk != 89 
    AND r_04f59d.ca_suite_number ILIKE r_04f59d.ca_street_number 
    OR r_04f59d.ca_zip NOT LIKE r_04f59d.ca_suite_number 
ORDER BY
    1, 2 
OFFSET 12;
----------->
SELECT
    r_4ff5b1.pf_43e19f as te_6fb7aa,
    17 + r_4ff5b1.hd_vehicle_count + 95.23048344 + r_4f21ac.te_347a61 as te_f28b06,
    r_c0419d.hd_demo_sk as te_21bdeb 
FROM
    (SELECT
        * 
    FROM
        db1.household_demographics PIVOT(min(hd_dep_count) AS pa_c7e266 FOR hd_income_band_sk IN ((91) AS pf_b14af0,
        (0) AS pf_a1f8a8,
        (44) AS pf_43e19f))) AS r_4ff5b1 
LEFT JOIN
    db1.household_demographics AS r_c0419d 
        ON r_4ff5b1.pf_a1f8a8 < r_c0419d.hd_income_band_sk,
    (SELECT
        r_b24ab9.w_county as te_ec1dbf,
        r_c53330.s_market_id as te_347a61 
    FROM
        db1.household_demographics AS r_b8edce 
    LEFT JOIN
        db1.store AS r_c53330 
            ON r_b8edce.hd_demo_sk >= r_c53330.s_company_id,
        db1.warehouse AS r_b24ab9 
    WHERE
        r_c53330.s_company_name > r_b24ab9.w_zip 
        OR r_c53330.s_market_desc LIKE '6sUgDDXX4Q' 
        OR r_c53330.s_rec_start_date = r_c53330.s_rec_end_date 
        AND r_c53330.s_hours ILIKE '5G6' 
    ORDER BY
        1 ASC NULLS LAST, 2) AS r_4f21ac 
WHERE
    r_4ff5b1.pf_43e19f < r_4f21ac.te_347a61 
    AND r_4ff5b1.hd_buy_potential NOT LIKE r_c0419d.hd_buy_potential 
    OR r_c0419d.hd_demo_sk < r_c0419d.hd_dep_count 
    OR r_c0419d.hd_demo_sk >= 3 
    AND r_c0419d.hd_buy_potential LIKE r_4ff5b1.hd_buy_potential 
ORDER BY
    1 DESC NULLS LAST, 2 ASC, 3 DESC NULLS FIRST;
----------->
SELECT
    r_eb3130.d_week_seq as te_1ead46 
FROM
    db1.warehouse AS r_ae9dd5 
LEFT JOIN
    db1.date_dim AS r_eb3130 
        ON r_ae9dd5.w_warehouse_sq_ft < r_eb3130.d_date_sk 
WHERE
    r_eb3130.d_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
    AND r_ae9dd5.w_warehouse_sk != 66 
    AND r_eb3130.d_quarter_seq <= 60 
    AND r_eb3130.d_following_holiday ILIKE 'IHl' 
ORDER BY
    1 DESC NULLS FIRST 
LIMIT 11;
----------->
(
    SELECT
        r_15c4ef.hd_buy_potential as te_d63343 
    FROM
        db1.household_demographics AS r_15c4ef 
    WHERE
        r_15c4ef.hd_vehicle_count != r_15c4ef.hd_income_band_sk 
        AND r_15c4ef.hd_demo_sk = 92 
        AND r_15c4ef.hd_vehicle_count BETWEEN 12 AND 12 
    GROUP BY
        1 
    ORDER BY
        1 NULLS FIRST
) MINUS ALL (
    SELECT
        r_ce8743.w_street_name as te_f1415e 
    FROM
        db1.warehouse AS r_ce8743 
    INNER JOIN
        (
            SELECT
                current_timestamp() as te_0da219,
                r_817e1c.r_reason_id || r_bbe158.cd_credit_rating as te_b8e0fb 
            FROM
                db1.customer_demographics AS r_bbe158,
                db1.reason AS r_817e1c 
            WHERE
                r_bbe158.cd_purchase_estimate < r_817e1c.r_reason_sk 
                AND r_bbe158.cd_dep_college_count > r_bbe158.cd_dep_count 
                AND r_bbe158.cd_dep_employed_count <= 24 
                AND r_817e1c.r_reason_sk = 68 
                OR r_bbe158.cd_dep_college_count < 20 
            ORDER BY
                1 ASC NULLS FIRST, 2 DESC NULLS FIRST
        ) AS r_672063 
            ON r_ce8743.w_warehouse_name != r_672063.te_b8e0fb 
    WHERE
        r_ce8743.w_warehouse_sk = r_ce8743.w_warehouse_sq_ft 
        OR r_ce8743.w_warehouse_sq_ft = r_ce8743.w_warehouse_sk 
    ORDER BY
        1 DESC) 
    ORDER BY
        1 DESC;
----------->
WITH CTE_29df22(te_412761) AS (SELECT
    r_2ca415.ca_address_sk as te_412761 
FROM
    db1.customer_address AS r_2ca415 
WHERE
    r_2ca415.ca_address_sk > 72 
    OR r_2ca415.ca_street_type LIKE r_2ca415.ca_street_number 
    AND r_2ca415.ca_gmt_offset = 59.41671328 
GROUP BY
    1 
ORDER BY
    1 NULLS FIRST), CTE_086217(te_644a8f) AS (SELECT
    r_c5f007.i_category as te_644a8f 
FROM
    db1.store_returns AS r_e5c953 
LEFT JOIN
    db1.item AS r_c5f007 
        ON r_e5c953.sr_store_credit != r_c5f007.i_manager_id 
ORDER BY
    1 NULLS FIRST) SELECT
    r_fd28d3.te_6347c8 as te_861df6 
FROM
    (SELECT
        r_db5ed1.te_412761 as te_6347c8,
        r_26963a.t_time_sk as te_446cd1 
    FROM
        db1.time_dim AS r_5545b4,
        CTE_29df22 AS r_db5ed1 
    RIGHT JOIN
        db1.time_dim AS r_26963a 
            ON r_db5ed1.te_412761 > r_26963a.t_hour 
    WHERE
        r_5545b4.t_time < r_26963a.t_minute 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST) AS r_fd28d3 
INNER JOIN
    CTE_29df22 AS r_d87bd4 
        ON r_fd28d3.te_446cd1 != r_d87bd4.te_412761 
WHERE
    r_d87bd4.te_412761 = 2 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_c184dd.cd_dep_count as te_7cba0d 
FROM
    db1.customer_demographics AS r_c184dd 
LEFT JOIN
    db1.ship_mode AS r_0f16b9 
        ON r_c184dd.cd_dep_college_count = r_0f16b9.sm_ship_mode_sk 
WHERE
    r_c184dd.cd_dep_employed_count >= 79 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    hash(r_163a84.wp_autogen_flag,
    TIMESTAMP'2024-10-11 04:07:44.521') as te_b04650,
    make_timestamp(r_7ac304.sm_ship_mode_sk,
    r_163a84.wp_max_ad_count,
    r_163a84.wp_image_count,
    r_7ac304.sm_ship_mode_sk,
    r_163a84.wp_image_count,
    r_02d43d.r_reason_sk) as te_e206a8 
FROM
    db1.ship_mode AS r_7ac304,
    db1.web_page AS r_163a84 
RIGHT JOIN
    db1.reason AS r_02d43d 
        ON r_163a84.wp_char_count != r_02d43d.r_reason_sk 
WHERE
    r_7ac304.sm_ship_mode_sk >= r_163a84.wp_char_count 
    OR r_7ac304.sm_type NOT ILIKE r_02d43d.r_reason_desc 
    OR r_02d43d.r_reason_sk >= 67 
    OR r_163a84.wp_rec_start_date < r_163a84.wp_rec_end_date 
    AND r_163a84.wp_customer_sk > r_7ac304.sm_ship_mode_sk 
GROUP BY
    1, 2 
ORDER BY
    1 DESC NULLS LAST, 2 DESC;
----------->
SELECT
    r_2ef21c.i_color as te_5cf717 
FROM
    db1.item AS r_2ef21c 
WHERE
    r_2ef21c.i_current_price > 83.54499212 
    OR r_2ef21c.i_brand_id <= r_2ef21c.i_manager_id 
    OR r_2ef21c.i_rec_end_date = r_2ef21c.i_rec_start_date 
    AND r_2ef21c.i_item_sk >= r_2ef21c.i_class_id 
ORDER BY
    1 NULLS LAST;
----------->
(
    SELECT
        date_from_unix_date(r_6f75ce.pf_8b2180) as te_f8b6ac 
    FROM
        db1.household_demographics AS r_9560e5,
        (SELECT
            * 
        FROM
            db1.income_band PIVOT(sum(ib_upper_bound) AS pa_454d79 FOR ib_lower_bound IN ((69) AS pf_5b2661,
            (62) AS pf_86466f,
            (8) AS pf_8b2180))) AS r_6f75ce 
    WHERE
        r_9560e5.hd_demo_sk = r_6f75ce.pf_8b2180 
        OR r_9560e5.hd_dep_count = r_9560e5.hd_income_band_sk 
        AND r_6f75ce.ib_income_band_sk >= r_9560e5.hd_vehicle_count 
        OR r_6f75ce.pf_5b2661 <= 88 
    ORDER BY
        1 DESC NULLS LAST) EXCEPT ALL (SELECT
            r_7d54b9.web_rec_start_date as te_f823bd 
        FROM
            db1.web_site AS r_7d54b9 
        ORDER BY
            1 DESC NULLS FIRST) 
    ORDER BY
        1 DESC NULLS LAST;
----------->
(
    SELECT
        r_127bf4.c_last_name as te_6ab898 
    FROM
        db1.customer AS r_127bf4 
    LEFT JOIN
        db1.household_demographics AS r_765b6f 
            ON r_127bf4.c_current_cdemo_sk > r_765b6f.hd_vehicle_count 
    WHERE
        NOT r_127bf4.c_last_name LIKE r_127bf4.c_login 
        OR r_127bf4.c_birth_day != r_765b6f.hd_dep_count 
        OR r_127bf4.c_last_review_date_sk >= 69 
        AND r_127bf4.c_email_address NOT LIKE r_765b6f.hd_buy_potential 
    ORDER BY
        1
) MINUS  (SELECT
    r_ce432c.c_email_address as te_57cc49 
FROM
    db1.date_dim AS r_fa2e12,
    db1.customer AS r_ce432c 
INNER JOIN
    db1.time_dim AS r_265b52 
        ON r_ce432c.c_login NOT LIKE r_265b52.t_meal_time 
WHERE
    r_fa2e12.d_fy_year < r_ce432c.c_last_review_date_sk 
ORDER BY
    1 DESC 
LIMIT 29) ORDER BY
1 ASC NULLS LAST;
----------->
SELECT
    r_c2e689.i_current_price as te_c3b4a4 
FROM
    db1.item AS r_c2e689 
LEFT JOIN
    db1.household_demographics AS r_ede3e4 
        ON r_c2e689.i_class_id <= r_ede3e4.hd_income_band_sk 
WHERE
    NOT r_c2e689.i_rec_end_date = r_c2e689.i_rec_start_date 
    AND r_c2e689.i_wholesale_cost != r_c2e689.i_current_price 
    OR r_c2e689.i_rec_start_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
    OR r_c2e689.i_wholesale_cost = r_c2e689.i_current_price 
ORDER BY
    1 DESC;
----------->
SELECT
    r_612953.w_warehouse_sq_ft as te_103cf3,
    r_f7c19a.sr_store_credit as te_1b8b15,
    string(r_612953.w_warehouse_sq_ft) as te_1c00ed 
FROM
    db1.store_returns AS r_f7c19a,
    db1.warehouse AS r_612953 
WHERE
    r_f7c19a.sr_return_ship_cost < r_612953.w_warehouse_sq_ft 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 DESC 
OFFSET 29;
----------->
SELECT
    r_fb84ec.i_item_id as te_4c0149,
    r_fb84ec.i_item_sk as te_a539ed 
FROM
    db1.store_returns AS r_2d75d6 
INNER JOIN
    (
        SELECT
            r_25a12b.d_fy_year as te_82b8cb 
        FROM
            db1.date_dim AS r_25a12b 
        WHERE
            r_25a12b.d_current_week NOT ILIKE 'aLQF' 
            OR r_25a12b.d_quarter_name NOT ILIKE r_25a12b.d_current_week 
        ORDER BY
            1 ASC NULLS LAST
    ) AS r_49df80 
        ON r_2d75d6.sr_return_time_sk < r_49df80.te_82b8cb,
    db1.item AS r_fb84ec 
INNER JOIN
    db1.item AS r_9f0353 
        ON r_fb84ec.i_item_desc LIKE r_9f0353.i_formulation 
WHERE
    r_2d75d6.sr_reversed_charge >= r_9f0353.i_current_price 
    AND r_fb84ec.i_class_id != 61 
    OR r_9f0353.i_wholesale_cost = 87.96863684 
    AND r_9f0353.i_category NOT ILIKE r_fb84ec.i_item_desc 
ORDER BY
    1 NULLS LAST, 2 ASC NULLS FIRST 
LIMIT 25;
----------->
SELECT
    r_6b731d.w_gmt_offset as te_45e6a4,
    r_6b731d.w_county as te_05774b 
FROM
    db1.store_returns AS r_c29106,
    db1.web_site AS r_24c56c 
LEFT JOIN
    db1.warehouse AS r_6b731d 
        ON r_24c56c.web_suite_number LIKE r_6b731d.w_warehouse_id 
WHERE
    r_c29106.sr_store_credit < r_24c56c.web_tax_percentage 
    AND r_24c56c.web_country ILIKE 'XX4QHZgGIH' 
    AND r_24c56c.web_gmt_offset = r_c29106.sr_return_amt_inc_tax 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_82c723.sm_carrier as te_ad6ea2 
FROM
    db1.ship_mode AS r_82c723 
WHERE
    r_82c723.sm_code ILIKE r_82c723.sm_contract 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_a03cd1.cd_dep_employed_count as te_b5736e 
FROM
    db1.customer_demographics AS r_a03cd1 
WHERE
    r_a03cd1.cd_education_status NOT ILIKE r_a03cd1.cd_credit_rating 
    OR r_a03cd1.cd_dep_count != 71 
    AND r_a03cd1.cd_gender LIKE r_a03cd1.cd_education_status 
    AND r_a03cd1.cd_education_status LIKE 'z2kG' 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    38.52755031D * r_6d08a9.sr_return_amt_inc_tax as te_31ea46 
FROM
    db1.store_returns AS r_6d08a9 
WHERE
    r_6d08a9.sr_reversed_charge = 33.19710325 
    OR r_6d08a9.sr_hdemo_sk != 95 
    OR (
        NOT r_6d08a9.sr_return_amt < 63.16911433 
        OR r_6d08a9.sr_addr_sk = r_6d08a9.sr_store_sk
    ) 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    make_date(r_2ca58e.sr_cdemo_sk,
    r_2ca58e.sr_reason_sk,
    r_8f3e01.cd_dep_employed_count) as te_75c212,
    bigint(r_200c13.cd_demo_sk) as te_e7f73b 
FROM
    db1.customer_demographics AS r_200c13 
RIGHT JOIN
    db1.customer_demographics AS r_8f3e01 
        ON r_200c13.cd_dep_college_count >= 6,
    db1.customer AS r_81ec86 
LEFT JOIN
    db1.store_returns AS r_2ca58e 
        ON r_81ec86.c_birth_month <= r_2ca58e.sr_store_sk 
WHERE
    r_8f3e01.cd_purchase_estimate <= r_2ca58e.sr_hdemo_sk 
    OR r_8f3e01.cd_dep_count != 26 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_f0cc40.i_item_desc as te_0c49d1 
FROM
    db1.item AS r_f0cc40 
WHERE
    r_f0cc40.i_rec_end_date != DATE'2024-03-25' 
    AND (
        NOT r_f0cc40.i_rec_start_date != r_f0cc40.i_rec_end_date 
        AND r_f0cc40.i_wholesale_cost < r_f0cc40.i_current_price 
        AND r_f0cc40.i_brand_id < 97
    ) 
ORDER BY
    1 ASC;
----------->
SELECT
    r_99aae8.wp_web_page_sk as te_8ba07b,
    r_99aae8.wp_url as te_a9285d 
FROM
    db1.web_page AS r_99aae8,
    db1.date_dim AS r_e04b2e 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.income_band PIVOT(variance(ib_upper_bound) AS pa_4f9415 FOR (ib_lower_bound,
            ib_income_band_sk) IN (((48,
            73)) AS pf_670835,
            ((22,
            25)) AS pf_043195))
    ) AS r_493aa3 
        ON r_e04b2e.d_current_day ILIKE chr(r_493aa3.pf_043195) 
WHERE
    r_99aae8.wp_char_count >= r_e04b2e.d_qoy 
    OR r_e04b2e.d_current_month NOT LIKE 'RXp' 
    OR r_99aae8.wp_link_count = 20 
    OR r_e04b2e.d_current_quarter NOT LIKE 'BR' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_9dac5f.cc_gmt_offset as te_2d6050 
FROM
    db1.call_center AS r_9dac5f 
LEFT JOIN
    db1.customer_demographics AS r_6a811b 
        ON r_9dac5f.cc_call_center_id NOT ILIKE r_6a811b.cd_marital_status 
WHERE
    r_6a811b.cd_purchase_estimate >= r_9dac5f.cc_sq_ft 
    AND r_6a811b.cd_education_status IS NULL 
    AND r_9dac5f.cc_mkt_id <= r_6a811b.cd_dep_college_count 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_4dc3ba.s_gmt_offset as te_cc9201,
    btrim(r_3082da.cd_marital_status) as te_9cee3b 
FROM
    db1.store AS r_4dc3ba,
    db1.customer_demographics AS r_3082da 
WHERE
    r_4dc3ba.s_number_employees > r_3082da.cd_purchase_estimate 
ORDER BY
    1, 2 ASC;
----------->
WITH CTE_cddb85(te_ba24d2, te_65e722) AS (SELECT
    date(timestamp_millis(r_fda43f.c_last_review_date_sk)) as te_ba24d2,
    pi() as te_65e722 
FROM
    db1.web_page AS r_bbe91e 
RIGHT JOIN
    db1.customer AS r_fda43f 
        ON r_bbe91e.wp_access_date_sk >= r_fda43f.c_last_review_date_sk,
    db1.customer_demographics AS r_4e268e 
WHERE
    r_bbe91e.wp_max_ad_count >= r_4e268e.cd_dep_employed_count 
    OR r_fda43f.c_email_address ILIKE r_bbe91e.wp_url 
    OR r_bbe91e.wp_url ILIKE '2kGk4e' 
    AND NOT r_fda43f.c_first_name NOT LIKE 'z2' 
    AND r_fda43f.c_birth_day = 82 
ORDER BY
    1, 2 ASC NULLS FIRST) SELECT
    r_2109d7.te_861706 as te_8eeed5 
FROM
    (SELECT
        r_a1483e.d_date as te_861706 
    FROM
        db1.income_band AS r_32fa19 
    RIGHT JOIN
        db1.date_dim AS r_a1483e 
            ON r_32fa19.ib_upper_bound != r_a1483e.d_fy_week_seq 
    ORDER BY
        1 ASC NULLS FIRST) AS r_2109d7 
WHERE
    r_2109d7.te_861706 <= DATE'2024-03-26' 
    AND r_2109d7.te_861706 != r_2109d7.te_861706 
ORDER BY
    1 NULLS FIRST;
----------->
WITH CTE_b6263c(te_afb4a7) AS (SELECT
    r_1e585f.web_gmt_offset as te_afb4a7 
FROM
    db1.web_site AS r_1e585f 
WHERE
    NOT r_1e585f.web_open_date_sk >= 66 
    AND r_1e585f.web_street_number LIKE 'tb2' 
    AND r_1e585f.web_company_id < r_1e585f.web_open_date_sk 
ORDER BY
    1 DESC NULLS FIRST) SELECT
    decimal(sum(r_cc9d54.c_first_shipto_date_sk) - r_cc9d54.c_last_review_date_sk) as te_f392fb 
FROM
    CTE_b6263c AS r_12d86b 
LEFT JOIN
    db1.customer AS r_cc9d54 
        ON r_12d86b.te_afb4a7 != decimal(r_cc9d54.c_birth_month) 
GROUP BY
    r_cc9d54.c_last_review_date_sk 
ORDER BY
    1 DESC;
----------->
WITH CTE_397521(te_ba77ac, te_c8bc0d) AS (SELECT
    current_date() as te_ba77ac,
    r_2fb581.te_f6baac as te_c8bc0d 
FROM
    (SELECT
        r_75a35e.d_fy_year as te_2e2d8a 
    FROM
        (SELECT
            * 
        FROM
            db1.date_dim PIVOT(min(d_current_month) AS pa_7188b3 FOR d_dom IN ((96) AS pf_ae31a8,
            (43) AS pf_ed403d))) AS r_75a35e 
    WHERE
        r_75a35e.d_date_id ILIKE r_75a35e.d_following_holiday 
    ORDER BY
        1 ASC NULLS LAST) AS r_5fcfdb, (SELECT
            r_dfd74c.upn_5b4911 as te_f6baac, r_a3228c.sm_carrier as te_db3fb1, r_a1c460.ib_lower_bound as te_2ea685 
        FROM
            (SELECT
                * 
            FROM
                db1.household_demographics UNPIVOT EXCLUDE NULLS ((up_c059ac,
                up_e56904) FOR upn_5b4911 IN ((hd_buy_potential,
                hd_vehicle_count) AS UPF_9e1cbb))) AS r_dfd74c,
            db1.income_band AS r_a1c460 
        LEFT JOIN
            db1.ship_mode AS r_a3228c 
                ON r_a1c460.ib_income_band_sk = r_a3228c.sm_ship_mode_sk 
        WHERE
            r_dfd74c.up_e56904 = r_a1c460.ib_income_band_sk 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS FIRST) AS r_2fb581 
        WHERE
            r_5fcfdb.te_2e2d8a > r_2fb581.te_2ea685 
            AND (NOT r_2fb581.te_db3fb1 LIKE 'RQiizz2k' 
            OR r_2fb581.te_f6baac ILIKE 'LQFrTU' 
            AND r_2fb581.te_f6baac NOT ILIKE r_2fb581.te_db3fb1) 
        ORDER BY
            1 ASC NULLS FIRST, 2 DESC NULLS LAST), CTE_7e56c9(te_aac53b, te_c2f8cb) AS (SELECT
            chr(r_a240f9.c_birth_day) as te_aac53b, r_75d8d5.wp_autogen_flag as te_c2f8cb 
        FROM
            db1.reason AS r_0d8920,
            db1.web_page AS r_75d8d5 
        INNER JOIN
            db1.customer AS r_a240f9 
                ON r_75d8d5.wp_char_count < r_a240f9.c_birth_month 
        WHERE
            r_0d8920.r_reason_sk > r_a240f9.c_current_hdemo_sk 
            AND r_a240f9.c_preferred_cust_flag NOT LIKE r_a240f9.c_customer_id 
            OR r_a240f9.c_birth_country LIKE 'zm' 
        ORDER BY
            1, 2 DESC NULLS LAST 
        LIMIT 69) SELECT
        r_ff0f9a.te_6c7099 as te_990783, r_4d50cc.te_ba77ac as te_955d90 FROM
            CTE_397521 AS r_4d50cc,
            db1.household_demographics AS r_3b958f 
        RIGHT JOIN
            (
                SELECT
                    r_a8cf44.t_sub_shift as te_6c7099,
                    r_33385e.ib_upper_bound as te_3c9a4a 
                FROM
                    db1.customer_address AS r_cc4625 
                LEFT JOIN
                    db1.time_dim AS r_a8cf44 
                        ON r_cc4625.ca_gmt_offset <= r_a8cf44.t_hour,
                    db1.income_band AS r_33385e 
                LEFT JOIN
                    CTE_397521 AS r_676baf 
                        ON chr(r_33385e.ib_lower_bound) >= r_676baf.te_c8bc0d 
                WHERE
                    r_a8cf44.t_time_sk >= r_33385e.ib_upper_bound 
                    AND r_cc4625.ca_suite_number >= r_cc4625.ca_street_number 
                    AND r_a8cf44.t_time <= 12 
                GROUP BY
                    2, 1 
                ORDER BY
                    1 ASC, 2 DESC NULLS LAST
            ) AS r_ff0f9a 
                ON r_3b958f.hd_buy_potential NOT ILIKE r_ff0f9a.te_6c7099 
        WHERE
            r_4d50cc.te_c8bc0d NOT LIKE r_ff0f9a.te_6c7099 
        ORDER BY
            1 NULLS LAST, 2 DESC;
----------->
WITH CTE_177d34(te_446803) AS (SELECT
    r_277958.ib_upper_bound as te_446803 
FROM
    db1.income_band AS r_277958 
WHERE
    r_277958.ib_upper_bound = 43 
ORDER BY
    1 NULLS LAST) SELECT
    hash(r_25d730.te_446803, true) as te_c98741, r_418725.te_fa1be4 as te_0acbe3 
FROM
    (SELECT
        r_a0b268.d_same_day_lq as te_fa1be4,
        r_4d1d40.sr_reason_sk as te_1b2cde 
    FROM
        db1.store_returns AS r_4d1d40 
    RIGHT JOIN
        db1.income_band AS r_8ccfd8 
            ON r_4d1d40.sr_ticket_number = r_8ccfd8.ib_upper_bound,
        db1.call_center AS r_bd84b7 
    INNER JOIN
        db1.date_dim AS r_a0b268 
            ON r_bd84b7.cc_market_manager ILIKE r_a0b268.d_day_name 
    WHERE
        r_4d1d40.sr_hdemo_sk != r_a0b268.d_dom 
    ORDER BY
        1 DESC NULLS FIRST, 2 DESC NULLS LAST) AS r_418725, CTE_177d34 AS r_25d730 
WHERE
    r_418725.te_fa1be4 = r_25d730.te_446803 
    OR r_418725.te_1b2cde < 80 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS FIRST;
----------->
SELECT
    pi() as te_9d7172,
    r_fa1f1a.cc_rec_start_date as te_8f5bf8 
FROM
    db1.call_center AS r_fa1f1a 
LEFT JOIN
    db1.household_demographics AS r_98dcdd 
        ON r_fa1f1a.cc_name NOT ILIKE r_98dcdd.hd_buy_potential,
    db1.item AS r_5f076b 
LEFT JOIN
    (
        SELECT
            r_364cb0.wp_web_page_id as te_d3c38e,
            r_356349.hd_dep_count as te_b5f8a4 
        FROM
            db1.ship_mode AS r_3dd6ae 
        LEFT JOIN
            db1.web_page AS r_364cb0 
                ON r_3dd6ae.sm_ship_mode_id LIKE r_364cb0.wp_type,
            db1.ship_mode AS r_7e6aa2 
        INNER JOIN
            db1.household_demographics AS r_356349 
                ON r_7e6aa2.sm_ship_mode_sk = r_356349.hd_income_band_sk 
        WHERE
            r_3dd6ae.sm_carrier LIKE r_7e6aa2.sm_code 
            OR r_364cb0.wp_access_date_sk > 28 
            OR r_364cb0.wp_rec_start_date BETWEEN DATE'2024-10-11' AND DATE'2024-10-11' 
        ORDER BY
            1 DESC, 2 DESC
    ) AS r_4075d8 
        ON r_5f076b.i_item_sk = r_4075d8.te_b5f8a4 
WHERE
    r_fa1f1a.cc_call_center_id LIKE r_5f076b.i_item_id 
    OR r_fa1f1a.cc_state LIKE r_5f076b.i_class 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST 
LIMIT 13;
----------->
SELECT
    r_22fcee.ca_state as te_dd80bc,
    r_f2d0ca.cc_rec_start_date as te_cfa642,
    r_f2d0ca.cc_company as te_43e1ae 
FROM
    db1.customer_address AS r_22fcee 
RIGHT JOIN
    db1.call_center AS r_f2d0ca 
        ON r_22fcee.ca_address_id ILIKE r_f2d0ca.cc_call_center_id,
    db1.web_page AS r_cd8278 
WHERE
    (
        NOT r_22fcee.ca_location_type ILIKE 'lBy' 
        OR r_f2d0ca.cc_open_date_sk != 96 
        AND NOT r_22fcee.ca_street_type NOT LIKE 'bBRG'
    ) 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    make_date(r_659891.sr_ticket_number,
    r_659891.sr_hdemo_sk,
    r_659891.sr_returned_date_sk) as te_5e3bbc 
FROM
    db1.store_returns AS r_659891 
WHERE
    r_659891.sr_fee != 66.40044291 
ORDER BY
    1;
----------->
SELECT
    r_45bc80.r_reason_sk as te_0f4d7d 
FROM
    db1.reason AS r_45bc80 
WHERE
    r_45bc80.r_reason_desc NOT LIKE r_45bc80.r_reason_id 
    AND r_45bc80.r_reason_id LIKE r_45bc80.r_reason_desc 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_04dc98.te_c3ccd1 as te_011c7d 
FROM
    (SELECT
        r_c27ed9.te_f051d8 as te_c3ccd1 
    FROM
        (SELECT
            unix_timestamp() as te_f051d8 
        FROM
            db1.call_center AS r_5fcf93 
        WHERE
            r_5fcf93.cc_street_type LIKE 'LQFrTUOVbB' 
            OR r_5fcf93.cc_tax_percentage <= 28.01773068) AS r_c27ed9 
    INNER JOIN
        db1.web_site AS r_6abefb 
            ON chr(r_c27ed9.te_f051d8) LIKE r_6abefb.web_zip 
    WHERE
        r_6abefb.web_zip LIKE r_6abefb.web_mkt_desc 
        AND r_6abefb.web_tax_percentage <= r_6abefb.web_gmt_offset 
        AND r_6abefb.web_rec_start_date != r_6abefb.web_rec_end_date 
        AND r_6abefb.web_street_name LIKE 'v7' 
    ORDER BY
        1 ASC NULLS LAST) AS r_04dc98 
    INNER JOIN
        (
            SELECT
                r_7eb715.sm_carrier as te_4d4f70 
            FROM
                db1.ship_mode AS r_7eb715 
            WHERE
                r_7eb715.sm_contract NOT ILIKE r_7eb715.sm_type 
                OR r_7eb715.sm_code LIKE 'gGIHlpx' 
            ORDER BY
                1 NULLS LAST 
            LIMIT 59
    ) AS r_77aaa2 
        ON chr(r_04dc98.te_c3ccd1) LIKE r_77aaa2.te_4d4f70 WHERE
            r_77aaa2.te_4d4f70 LIKE 'VbBRG' 
            OR r_77aaa2.te_4d4f70 NOT LIKE 'sUgD' 
            AND r_04dc98.te_c3ccd1 != r_04dc98.te_c3ccd1 
            OR r_77aaa2.te_4d4f70 IS NULL 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    r_65fcd2.web_rec_start_date as te_3edf91,
    date(current_timestamp()) as te_8520b0,
    r_65fcd2.web_tax_percentage as te_0c4e3d 
FROM
    db1.web_site AS r_65fcd2 
RIGHT JOIN
    db1.household_demographics AS r_5c1f29 
        ON r_65fcd2.web_city NOT ILIKE r_5c1f29.hd_buy_potential,
    db1.date_dim AS r_3a666e 
LEFT JOIN
    db1.warehouse AS r_9b60b6 
        ON r_3a666e.d_fy_quarter_seq > r_9b60b6.w_warehouse_sq_ft 
WHERE
    r_5c1f29.hd_vehicle_count <= r_3a666e.d_week_seq 
    AND r_65fcd2.web_zip NOT ILIKE 'kG' 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_86d2ce.sr_hdemo_sk as te_0072d4 
FROM
    db1.store_returns AS r_86d2ce 
RIGHT JOIN
    db1.ship_mode AS r_69780d 
        ON r_86d2ce.sr_item_sk <= r_69780d.sm_ship_mode_sk 
WHERE
    r_86d2ce.sr_return_ship_cost BETWEEN 5.12780233 AND r_86d2ce.sr_hdemo_sk 
    AND r_69780d.sm_contract LIKE r_69780d.sm_ship_mode_id 
ORDER BY
    1 ASC;
----------->
SELECT
    r_09ffe5.pf_d0173d as te_a9c53f,
    now() as te_b31c04,
    r_2bb50c.i_current_price as te_2922f0 
FROM
    (SELECT
        * 
    FROM
        db1.item PIVOT(count(i_manager_id) AS pa_1baab3 FOR i_item_id IN (('SRI') AS pf_840eb2,
        ('RXp') AS pf_fba37c,
        ('l') AS pf_d54034,
        ('GC0l') AS pf_a9d300))) AS r_2bb50c 
INNER JOIN
    (
        SELECT
            * 
        FROM
            db1.household_demographics PIVOT(stddev(hd_demo_sk) AS pa_9ee365 FOR hd_dep_count IN ((49) AS pf_754168,
            (64) AS pf_d0173d))
    ) AS r_09ffe5 
        ON r_2bb50c.i_class_id >= r_09ffe5.hd_vehicle_count,
    db1.customer_demographics AS r_d630a9 
RIGHT JOIN
    db1.web_page AS r_0f8120 
        ON r_d630a9.cd_dep_employed_count >= r_0f8120.wp_web_page_sk 
WHERE
    r_09ffe5.hd_income_band_sk >= r_0f8120.wp_access_date_sk 
    AND r_0f8120.wp_autogen_flag ILIKE 'x' 
    OR NOT r_0f8120.wp_char_count > 78 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC 
LIMIT 90;
----------->
SELECT
    r_dcdf47.wp_rec_end_date as te_83a94f 
FROM
    db1.web_page AS r_dcdf47 
INNER JOIN
    db1.customer AS r_4f89cc 
        ON r_dcdf47.wp_autogen_flag NOT ILIKE r_4f89cc.c_customer_id 
WHERE
    r_4f89cc.c_first_name LIKE r_4f89cc.c_salutation 
    AND NOT r_4f89cc.c_current_cdemo_sk > 5 
    OR r_dcdf47.wp_web_page_sk > 2 
    OR r_dcdf47.wp_autogen_flag NOT ILIKE 'RI' 
ORDER BY
    1 ASC NULLS FIRST 
OFFSET 79;
----------->
SELECT
    r_0cf1de.t_am_pm as te_ab8aa2,
    35.6769678D - r_0cf1de.t_minute as te_009b82,
    r_0cf1de.t_second as te_8a9e74 
FROM
    db1.income_band AS r_8a2e64,
    db1.time_dim AS r_0cf1de 
WHERE
    r_8a2e64.ib_lower_bound > r_0cf1de.t_hour 
    AND r_0cf1de.t_time_id = r_0cf1de.t_shift 
GROUP BY
    1, 3, 2 
ORDER BY
    1 ASC NULLS LAST, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    r_d92ca1.ca_gmt_offset as te_882802 
FROM
    db1.customer_address AS r_d92ca1 
RIGHT JOIN
    db1.household_demographics AS r_665e0b 
        ON r_d92ca1.ca_gmt_offset = r_665e0b.hd_demo_sk 
WHERE
    r_d92ca1.ca_country NOT LIKE r_d92ca1.ca_street_name 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_8ea7c1(te_dfa301, te_f7162c) AS (SELECT
    btrim(r_d68149.ca_street_type) as te_dfa301,
    r_570025.ca_county as te_f7162c 
FROM
    db1.customer AS r_e82f18 
RIGHT JOIN
    db1.customer_address AS r_d68149 
        ON r_e82f18.c_birth_year = r_d68149.ca_address_sk,
    db1.customer_address AS r_570025 
LEFT JOIN
    db1.web_page AS r_c46ace 
        ON r_570025.ca_suite_number LIKE r_c46ace.wp_type 
WHERE
    r_d68149.ca_address_id ILIKE r_c46ace.wp_url 
    OR r_c46ace.wp_type ILIKE 'zz' 
    AND r_d68149.ca_gmt_offset = r_570025.ca_gmt_offset 
    OR r_e82f18.c_customer_id ILIKE r_d68149.ca_location_type 
    AND r_e82f18.c_birth_day >= r_e82f18.c_customer_sk) SELECT
    r_8896b0.te_c896e8 as te_aa2c48 
FROM
    (SELECT
        r_3323c8.t_minute as te_c896e8 
    FROM
        db1.time_dim AS r_3323c8 
    INNER JOIN
        db1.customer_demographics AS r_bcd7bc 
            ON r_3323c8.t_minute < r_bcd7bc.cd_dep_count 
    WHERE
        r_3323c8.t_am_pm LIKE 'IvX' 
        AND r_bcd7bc.cd_marital_status NOT ILIKE 'IvXU' 
    ORDER BY
        1 ASC NULLS LAST) AS r_8896b0 
WHERE
    r_8896b0.te_c896e8 = 78 
    OR r_8896b0.te_c896e8 != 71 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_2de6a0.s_street_name as te_8c3367 
FROM
    db1.store AS r_2de6a0 
WHERE
    r_2de6a0.s_gmt_offset != r_2de6a0.s_tax_percentage 
    OR r_2de6a0.s_gmt_offset < 97.90719634 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_0c2fff.s_rec_start_date as te_a96859,
    r_0c2fff.s_rec_end_date as te_cddbc0,
    r_0c2fff.s_closed_date_sk as te_340b53 
FROM
    db1.store AS r_0c2fff 
LEFT JOIN
    db1.store_returns AS r_d7f164 
        ON r_0c2fff.s_division_id = r_d7f164.sr_reversed_charge,
    db1.customer AS r_016aff 
WHERE
    r_0c2fff.s_company_name NOT ILIKE r_016aff.c_salutation 
    OR r_016aff.c_first_shipto_date_sk <= 35 
    OR r_0c2fff.s_store_id NOT LIKE r_016aff.c_preferred_cust_flag 
    AND r_d7f164.sr_return_quantity = r_d7f164.sr_return_time_sk 
    AND r_016aff.c_preferred_cust_flag ILIKE r_016aff.c_customer_id 
ORDER BY
    1 ASC, 2 DESC NULLS FIRST, 3 NULLS LAST;
----------->
SELECT
    r_4b2bf0.d_month_seq as te_dfc193 
FROM
    (SELECT
        r_010ba9.i_brand as te_d02d75,
        e() as te_d0ab62 
    FROM
        db1.web_page AS r_64470b 
    RIGHT JOIN
        db1.item AS r_010ba9 
            ON r_64470b.wp_rec_start_date >= r_010ba9.i_rec_start_date,
        db1.time_dim AS r_1b5b70 
    WHERE
        r_010ba9.i_color ILIKE r_1b5b70.t_shift 
        AND r_64470b.wp_access_date_sk >= r_64470b.wp_link_count 
        AND r_010ba9.i_item_id NOT LIKE r_64470b.wp_autogen_flag 
        OR r_64470b.wp_customer_sk <= r_64470b.wp_image_count 
        AND r_1b5b70.t_am_pm NOT LIKE 'rT' 
    ORDER BY
        1 ASC NULLS LAST, 2 ASC NULLS LAST 
    LIMIT 51) AS r_f38cd9 RIGHT JOIN
    db1.date_dim AS r_4b2bf0 
        ON date_from_unix_date(hash(r_f38cd9.te_d0ab62)) >= date_from_unix_date(length(string(r_4b2bf0.d_date))) 
WHERE
    r_4b2bf0.d_same_day_lq < 29 
    AND r_4b2bf0.d_fy_week_seq > r_4b2bf0.d_fy_year 
    AND r_4b2bf0.d_date_sk < 30 
    OR r_4b2bf0.d_fy_week_seq != 10 
ORDER BY
    1 DESC;
----------->
SELECT
    positive(mod(r_a0bf0e.sm_ship_mode_sk,
    66)) + count(r_a436e6.r_reason_desc) as te_23ff31,
    sin(r_a0bf0e.sm_ship_mode_sk) - pi() as te_8fffa7,
    current_timestamp() as te_a6604a 
FROM
    db1.reason AS r_a436e6,
    db1.ship_mode AS r_a0bf0e 
WHERE
    r_a436e6.r_reason_desc LIKE r_a0bf0e.sm_type 
    OR r_a0bf0e.sm_ship_mode_sk > r_a436e6.r_reason_sk 
    AND r_a436e6.r_reason_id NOT LIKE r_a0bf0e.sm_code 
    AND r_a0bf0e.sm_code LIKE r_a0bf0e.sm_ship_mode_id 
    OR r_a0bf0e.sm_contract NOT ILIKE r_a436e6.r_reason_id 
GROUP BY
    r_a0bf0e.sm_ship_mode_sk 
ORDER BY
    1 DESC NULLS LAST, 2 ASC NULLS LAST, 3 NULLS LAST;
----------->
WITH CTE_047cb4(te_266721) AS (SELECT
    r_8c0dc2.sr_fee as te_266721 
FROM
    db1.store_returns AS r_8c0dc2 
LEFT JOIN
    db1.customer AS r_f90beb 
        ON r_8c0dc2.sr_return_time_sk < r_f90beb.c_current_addr_sk 
WHERE
    r_8c0dc2.sr_return_amt = 18.56876208 
ORDER BY
    1 ASC NULLS LAST), CTE_b343c0(te_da4b8f, te_b19216) AS (SELECT
    r_e9c0d8.w_suite_number as te_da4b8f, r_e9c0d8.w_gmt_offset as te_b19216 
FROM
    db1.reason AS r_b3c880 
RIGHT JOIN
    db1.warehouse AS r_e9c0d8 
        ON r_b3c880.r_reason_sk >= r_e9c0d8.w_warehouse_sk,
    db1.income_band AS r_aa4820 
WHERE
    r_e9c0d8.w_gmt_offset != r_aa4820.ib_upper_bound 
    AND r_e9c0d8.w_zip NOT ILIKE 'LQFrTUO' 
    AND r_e9c0d8.w_warehouse_sq_ft = 26 
    OR r_e9c0d8.w_warehouse_sk <= 4 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST) SELECT
    r_7d8d5c.wp_url as te_0f4ea6, make_timestamp(r_7d8d5c.wp_web_page_sk, r_7d8d5c.wp_char_count, r_7d8d5c.wp_image_count, r_7d8d5c.wp_link_count, r_7d8d5c.wp_customer_sk, r_6f580e.te_b19216) as te_aa3c8e 
FROM
    CTE_047cb4 AS r_92f4e2 
LEFT JOIN
    CTE_b343c0 AS r_6f580e 
        ON r_92f4e2.te_266721 = r_6f580e.te_b19216,
    db1.web_page AS r_7d8d5c 
WHERE
    r_6f580e.te_b19216 = r_7d8d5c.wp_web_page_sk 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_dce221.s_tax_percentage as te_d1a52c,
    r_dce221.s_market_id as te_f4d23a,
    r_dce221.s_store_id as te_fad512 
FROM
    db1.store AS r_dce221,
    (SELECT
        * 
    FROM
        db1.customer_demographics PIVOT(min(cd_marital_status) AS pa_ec1aae FOR cd_gender IN (('2SW4') AS pf_1efbdc,
        ('aL') AS pf_036db3,
        ('g7x') AS pf_ddab25,
        ('tb2') AS pf_84e938,
        ('ZgGIH') AS pf_e68249))) AS r_8f11a7 
WHERE
    r_dce221.s_closed_date_sk > r_8f11a7.cd_dep_college_count 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 ASC NULLS LAST 
LIMIT 35;
----------->
WITH CTE_7380d2(te_71d7a4, te_449a1e, te_597ea3) AS (SELECT
    r_cb3a16.ca_gmt_offset as te_71d7a4,
    r_ed9133.i_manufact as te_449a1e,
    r_ed9133.i_current_price as te_597ea3 
FROM
    db1.item AS r_ed9133,
    db1.customer_address AS r_cb3a16 
WHERE
    r_ed9133.i_wholesale_cost != r_cb3a16.ca_gmt_offset 
    AND r_cb3a16.ca_street_number NOT ILIKE r_cb3a16.ca_address_id 
    OR r_cb3a16.ca_country LIKE r_ed9133.i_size 
    AND r_cb3a16.ca_state > r_cb3a16.ca_street_type 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS LAST), CTE_1ca520(te_8aca17) AS (SELECT
    r_fc69ee.c_preferred_cust_flag as te_8aca17 
FROM
    db1.customer AS r_fc69ee 
INNER JOIN
    db1.household_demographics AS r_8cf7f2 
        ON r_fc69ee.c_email_address LIKE r_8cf7f2.hd_buy_potential 
WHERE
    r_fc69ee.c_first_sales_date_sk <= r_fc69ee.c_birth_day 
    AND NOT r_fc69ee.c_birth_country NOT ILIKE r_fc69ee.c_first_name 
    OR r_fc69ee.c_email_address IS NOT NULL 
ORDER BY
    1 DESC NULLS LAST) SELECT
    r_44e8e0.te_597ea3 as te_9be0e0, 15 - r_44e8e0.te_71d7a4 as te_111826 
FROM
    CTE_7380d2 AS r_44e8e0,
    CTE_1ca520 AS r_4546ff 
WHERE
    r_44e8e0.te_449a1e NOT ILIKE r_4546ff.te_8aca17 
    AND r_44e8e0.te_71d7a4 >= 49.89846342 
    OR r_44e8e0.te_597ea3 != 55.68462588 
ORDER BY
    1 ASC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_8694a4.cc_name as te_060e69,
    now() as te_2d0e7c,
    r_210d13.web_gmt_offset - r_8694a4.cc_tax_percentage as te_91a881 
FROM
    db1.household_demographics AS r_99382b,
    db1.call_center AS r_8694a4 
INNER JOIN
    db1.web_site AS r_210d13 
        ON r_8694a4.cc_division < r_210d13.web_tax_percentage 
WHERE
    r_99382b.hd_vehicle_count > r_8694a4.cc_sq_ft 
ORDER BY
    1, 2 DESC NULLS LAST, 3 NULLS LAST 
OFFSET 21;
----------->
SELECT
    r_58cc52.d_day_name as te_bc1dd0 
FROM
    db1.date_dim AS r_58cc52 
LEFT JOIN
    db1.income_band AS r_6d8957 
        ON r_58cc52.d_fy_year > r_6d8957.ib_income_band_sk 
WHERE
    r_58cc52.d_date = DATE'2024-10-11' 
    AND r_58cc52.d_day_name NOT LIKE r_58cc52.d_quarter_name 
    OR r_58cc52.d_holiday NOT LIKE '2kGk4' 
    AND r_58cc52.d_last_dom < 7 
ORDER BY
    1 DESC 
LIMIT 79;
----------->
SELECT
    r_34a45a.cd_demo_sk as te_9c3e76 
FROM
    db1.customer_demographics AS r_34a45a 
INNER JOIN
    db1.household_demographics AS r_e1d896 
        ON r_34a45a.cd_purchase_estimate >= r_e1d896.hd_income_band_sk 
WHERE
    r_34a45a.cd_demo_sk <= 8 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_25e93a.sm_type as te_b73cf7 
FROM
    db1.ship_mode AS r_25e93a 
WHERE
    NOT r_25e93a.sm_ship_mode_id ILIKE 'XX4QHZ' 
    AND r_25e93a.sm_ship_mode_id NOT ILIKE r_25e93a.sm_type 
ORDER BY
    1 
LIMIT 70;
----------->
SELECT
    bigint(r_07b97e.cd_dep_count) as te_0a10bd 
FROM
    db1.customer_demographics AS r_9c04e5 
LEFT JOIN
    db1.customer_demographics AS r_07b97e 
        ON r_9c04e5.cd_gender ILIKE r_07b97e.cd_marital_status 
WHERE
    r_9c04e5.cd_purchase_estimate BETWEEN 92 AND r_9c04e5.cd_dep_employed_count 
    AND r_9c04e5.cd_purchase_estimate >= 68 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_fb9a1d.ca_gmt_offset as te_f874d5,
    try_add(r_56cdb6.ib_income_band_sk,
    r_56cdb6.ib_income_band_sk) as te_9dffc4,
    r_fb9a1d.ca_address_id as te_8e3587 
FROM
    db1.income_band AS r_56cdb6,
    db1.customer_address AS r_fb9a1d 
WHERE
    r_56cdb6.ib_lower_bound = r_fb9a1d.ca_address_sk 
    AND r_fb9a1d.ca_gmt_offset >= 87.00926773 
    AND r_fb9a1d.ca_city ILIKE 'gG' 
    OR NOT r_fb9a1d.ca_street_name LIKE r_fb9a1d.ca_county 
ORDER BY
    1 NULLS FIRST, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    r_d11f19.s_rec_start_date as te_6d8be1 
FROM
    db1.store AS r_d11f19 
INNER JOIN
    db1.customer_demographics AS r_53b4f1 
        ON r_d11f19.s_division_id > r_53b4f1.cd_purchase_estimate 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    r_594124.r_reason_desc as te_061520 
FROM
    db1.reason AS r_594124 
WHERE
    r_594124.r_reason_desc ILIKE r_594124.r_reason_id 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    btrim(chr(r_cad663.pf_3c7966)) as te_02a065 
FROM
    (SELECT
        * 
    FROM
        (WITH CTE_555934(te_90f419) AS (WITH CTE_4dc570(te_2d63cf) AS (SELECT
            r_6bff22.wp_rec_end_date as te_2d63cf 
        FROM
            db1.web_page AS r_6bff22 
        WHERE
            NOT r_6bff22.wp_web_page_sk > 11 
            OR r_6bff22.wp_char_count != 29 
            AND r_6bff22.wp_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25' 
        ORDER BY
            1 DESC NULLS FIRST), CTE_afa251(te_6d9704, te_c55755) AS (SELECT
            r_ec50f2.w_county as te_6d9704, 37 - r_7362d2.t_time_sk as te_c55755 
        FROM
            db1.time_dim AS r_7362d2,
            db1.warehouse AS r_ec50f2 
        WHERE
            r_7362d2.t_time = r_ec50f2.w_warehouse_sq_ft 
            OR r_ec50f2.w_warehouse_sk <= 72 
            AND r_7362d2.t_hour = 10 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS FIRST 
        OFFSET 65) SELECT
        r_8cd2f5.te_2d63cf as te_90f419 FROM
            CTE_4dc570 AS r_8cd2f5 
        INNER JOIN
            CTE_afa251 AS r_6b8e5b 
                ON r_8cd2f5.te_2d63cf != date_from_unix_date(r_6b8e5b.te_c55755) 
        WHERE
            r_6b8e5b.te_c55755 != r_6b8e5b.te_c55755 
            OR btrim(r_6b8e5b.te_6d9704) NOT LIKE chr(unix_seconds(timestamp(r_8cd2f5.te_2d63cf))) 
            OR r_6b8e5b.te_6d9704 NOT LIKE 'r9' 
            AND r_6b8e5b.te_c55755 BETWEEN 0 AND 0 
        ORDER BY
            1 DESC NULLS FIRST), CTE_340f31(te_b8bb58, te_48bf44, te_43b591) AS (SELECT
            14 * r_960b89.i_manager_id - r_8e7add.sr_hdemo_sk as te_b8bb58, pi() as te_48bf44, r_42c681.c_preferred_cust_flag as te_43b591 
        FROM
            (SELECT
                r_36b334.ca_suite_number as te_82b963,
                abs(unix_timestamp()) - ((SELECT
                    89.69454829D as te_676541 
                FROM
                    db1.reason AS r_9683c2 
                INNER JOIN
                    (SELECT
                        btrim(r_e0b494.c_preferred_cust_flag) as te_d9ea60,
                        r_d6d7df.i_brand as te_17c4dc 
                    FROM
                        db1.item AS r_d6d7df,
                        db1.customer AS r_e0b494 
                    INNER JOIN
                        db1.web_page AS r_ac3e2f 
                            ON r_e0b494.c_email_address NOT LIKE r_ac3e2f.wp_web_page_id 
                    WHERE
                        r_d6d7df.i_rec_start_date = r_ac3e2f.wp_rec_start_date 
                        AND r_ac3e2f.wp_rec_start_date <= DATE'2024-03-26' 
                        AND r_ac3e2f.wp_url LIKE 'IvXU' 
                        OR r_d6d7df.i_item_id NOT LIKE r_e0b494.c_preferred_cust_flag 
                    ORDER BY
                        1 DESC, 2 ASC NULLS FIRST) AS r_244327 
                        ON r_9683c2.r_reason_sk BETWEEN 15 AND 15,
                    db1.customer AS r_818043 
                WHERE
                    r_244327.te_d9ea60 LIKE r_818043.c_customer_id 
                ORDER BY
                    1 DESC NULLS FIRST 
                LIMIT 1)) - r_36b334.ca_address_sk - bigint(r_36b334.ca_address_sk) as te_eda857 FROM
                (SELECT
                    now() as te_6cee48 
                FROM
                    (SELECT
                        * 
                    FROM
                        db1.income_band PIVOT(skewness(ib_upper_bound) AS pa_67d1f0 FOR ib_lower_bound IN ((41) AS pf_fe95f1,
                        (64) AS pf_408e46,
                        (51) AS pf_a219ba,
                        (42) AS pf_b9dacd,
                        (97) AS pf_a7494f,
                        (28) AS pf_bdd4fb))) AS r_034003 
                LEFT JOIN
                    db1.ship_mode AS r_d37ec3 
                        ON r_034003.ib_income_band_sk > r_d37ec3.sm_ship_mode_sk 
                WHERE
                    r_d37ec3.sm_ship_mode_id NOT LIKE r_d37ec3.sm_carrier 
                    AND r_d37ec3.sm_carrier LIKE 'kGk4el' 
                ORDER BY
                    1 ASC NULLS LAST) AS r_50facc, db1.customer_address AS r_36b334 
                WHERE
                    chr(unix_seconds(r_50facc.te_6cee48)) LIKE btrim(r_36b334.ca_address_id) 
                    AND r_36b334.ca_street_type NOT LIKE r_36b334.ca_zip 
                    OR r_36b334.ca_city < 'tntb2Gv' 
                    AND r_36b334.ca_suite_number NOT LIKE r_36b334.ca_location_type) AS r_a5b4b3 
            INNER JOIN
                db1.item AS r_960b89 
                    ON r_a5b4b3.te_82b963 ILIKE r_960b89.i_manufact,
                db1.store_returns AS r_8e7add 
            INNER JOIN
                db1.customer AS r_42c681 
                    ON r_8e7add.sr_reason_sk != r_42c681.c_first_shipto_date_sk 
            WHERE
                r_960b89.i_manufact_id <= r_42c681.c_first_sales_date_sk 
                OR r_8e7add.sr_cdemo_sk != 41 
                AND r_42c681.c_birth_country NOT LIKE 'r' 
                OR r_960b89.i_product_name NOT LIKE 'C0' 
            ORDER BY
                1 ASC, 2 DESC NULLS FIRST, 3 ASC 
            OFFSET 86) SELECT
                r_1fedfe.c_email_address as te_3d6478, r_64ffef.te_b8bb58 as te_ba63be, r_64ffef.te_43b591 as te_92d61e FROM
                    CTE_555934 AS r_c25bf4 
                RIGHT JOIN
                    CTE_340f31 AS r_64ffef 
                        ON date_from_unix_date(ascii(string(r_c25bf4.te_90f419))) = date_from_unix_date(ascii(chr(r_64ffef.te_48bf44))),
                    db1.reason AS r_38c020 
                RIGHT JOIN
                    db1.customer AS r_1fedfe 
                        ON r_38c020.r_reason_desc ILIKE r_1fedfe.c_birth_country 
                WHERE
                    NOT r_64ffef.te_43b591 != r_1fedfe.c_preferred_cust_flag 
                    AND r_1fedfe.c_preferred_cust_flag LIKE 'iz' 
                    AND r_1fedfe.c_customer_id ILIKE r_64ffef.te_43b591 
                    AND r_1fedfe.c_preferred_cust_flag LIKE 'RI' 
                ORDER BY
                    1, 2 NULLS FIRST, 3 NULLS FIRST) PIVOT(max(te_ba63be) AS pa_1b6054 FOR te_92d61e IN (('C0') AS pf_05dad4, ('lstnt') AS pf_3c7966))) AS r_cad663 
            ORDER BY
                1 DESC NULLS FIRST;
----------->
SELECT
    r_e6259c.cc_call_center_id as te_a2a30f 
FROM
    db1.call_center AS r_e6259c 
WHERE
    r_e6259c.cc_tax_percentage >= 32.91251338 
    OR NOT r_e6259c.cc_gmt_offset IN (
        SELECT
            e() as te_083a95 
        FROM
            db1.customer_demographics AS r_6d7beb 
        WHERE
            r_6d7beb.cd_purchase_estimate < 33 
            AND r_6d7beb.cd_dep_count >= r_6d7beb.cd_demo_sk 
        ORDER BY
            1 ASC NULLS LAST 
        OFFSET 28
) ORDER BY
    1 DESC;
----------->
WITH CTE_f20649(te_22c7d9) AS (WITH CTE_9a9c42(te_44938d) AS (WITH CTE_797315(te_63d6cc, te_04bbd5, te_dc8313) AS (SELECT
    r_c6220a.d_week_seq as te_63d6cc,
    to_char(r_c6220a.d_last_dom,
    '999') as te_04bbd5,
    r_c6220a.d_same_day_ly / r_c6220a.d_last_dom as te_dc8313 
FROM
    db1.date_dim AS r_c6220a,
    (SELECT
        r_41f667.s_rec_end_date as te_7cb1aa 
    FROM
        db1.store AS r_41f667 
    RIGHT JOIN
        db1.customer_demographics AS r_de5084 
            ON r_41f667.s_state IS NULL 
    WHERE
        r_41f667.s_store_id <= 'zmS' 
        AND r_41f667.s_geography_class ILIKE 'IHl' 
        AND r_41f667.s_number_employees > r_41f667.s_company_id 
    ORDER BY
        1 DESC NULLS LAST) AS r_5059e4 
RIGHT JOIN
    db1.reason AS r_cc8449 
        ON chr(month(r_5059e4.te_7cb1aa)) ILIKE chr(hash(r_cc8449.r_reason_id)) 
WHERE
    r_c6220a.d_dom <= r_cc8449.r_reason_sk), CTE_e7f1b3(te_6df026, te_1e3ee8, te_362ae9) AS (SELECT
        try_add(r_f49fb1.ib_income_band_sk,
        4) as te_6df026,
        r_e69251.w_county as te_1e3ee8,
        r_e69251.w_county as te_362ae9 
    FROM
        db1.warehouse AS r_e69251,
        db1.income_band AS r_f49fb1 
    INNER JOIN
        db1.reason AS r_a476fb 
            ON r_f49fb1.ib_income_band_sk != r_a476fb.r_reason_sk 
    WHERE
        r_e69251.w_warehouse_sq_ft > r_f49fb1.ib_income_band_sk 
        AND r_e69251.w_street_type ILIKE 'C0l' 
        AND r_e69251.w_county LIKE 'v7' 
        OR r_f49fb1.ib_income_band_sk > r_a476fb.r_reason_sk 
        AND r_e69251.w_warehouse_sk < 73 
    ORDER BY
        1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC) SELECT
        r_bcc71c.te_63d6cc * 82 as te_44938d 
    FROM
        (SELECT
            r_36f9ed.s_county as te_261cb7 
        FROM
            db1.warehouse AS r_f17d02 
        RIGHT JOIN
            db1.store AS r_36f9ed 
                ON r_f17d02.w_gmt_offset >= r_36f9ed.s_market_id 
        WHERE
            r_36f9ed.s_gmt_offset = 66.08276404 
            OR r_f17d02.w_gmt_offset >= 32.06392427 
        ORDER BY
            1 NULLS LAST) AS r_b1018f 
    RIGHT JOIN
        CTE_797315 AS r_bcc71c 
            ON r_b1018f.te_261cb7 ILIKE chr(r_bcc71c.te_63d6cc) 
    WHERE
        r_bcc71c.te_63d6cc >= 58 
        OR r_b1018f.te_261cb7 ILIKE chr(r_bcc71c.te_04bbd5) 
        OR r_b1018f.te_261cb7 NOT LIKE 'ZgGIHlpx6k' 
    ORDER BY
        1 ASC NULLS LAST) SELECT
            r_3c249b.ib_upper_bound as te_22c7d9 
        FROM
            CTE_9a9c42 AS r_634530 
        INNER JOIN
            db1.income_band AS r_3c249b 
                ON r_634530.te_44938d != r_3c249b.ib_lower_bound 
        WHERE
            (NOT r_3c249b.ib_income_band_sk <= 43 
            OR r_3c249b.ib_lower_bound = r_3c249b.ib_income_band_sk 
            AND r_634530.te_44938d <= 40) 
        ORDER BY
            1 NULLS LAST) SELECT
            r_fdf27c.cd_education_status as te_0e83e2, r_865c67.i_manufact as te_8b11ce 
        FROM
            db1.item AS r_865c67 
        LEFT JOIN
            db1.customer_demographics AS r_fdf27c 
                ON r_865c67.i_wholesale_cost = r_fdf27c.cd_dep_college_count,
            CTE_f20649 AS r_1b69aa 
        LEFT JOIN
            db1.ship_mode AS r_2bdaa1 
                ON r_1b69aa.te_22c7d9 != r_2bdaa1.sm_ship_mode_sk 
        WHERE
            r_865c67.i_size NOT LIKE r_2bdaa1.sm_type 
            OR r_fdf27c.cd_demo_sk != 41 
            OR r_865c67.i_product_name ILIKE r_865c67.i_size 
            AND r_fdf27c.cd_education_status NOT ILIKE r_fdf27c.cd_credit_rating 
        ORDER BY
            1 DESC, 2 NULLS LAST 
        OFFSET 5;
----------->
SELECT
    r_7081ca.i_current_price as te_b2a71b 
FROM
    (SELECT
        r_deb4fb.te_8a25e9 as te_8fafd1 
    FROM
        (SELECT
            now() as te_1b19aa,
            r_4828b3.wp_rec_start_date as te_8a25e9 
        FROM
            db1.web_page AS r_4828b3 
        RIGHT JOIN
            db1.time_dim AS r_3ba4b9 
                ON r_4828b3.wp_url ILIKE r_3ba4b9.t_sub_shift,
            db1.store_returns AS r_ae5052 
        WHERE
            r_3ba4b9.t_second >= r_ae5052.sr_return_ship_cost 
            OR r_4828b3.wp_rec_end_date = r_4828b3.wp_rec_start_date 
            AND r_ae5052.sr_reason_sk <= 29 
            OR r_ae5052.sr_return_quantity = 98 
        ORDER BY
            1 DESC, 2) AS r_deb4fb 
    WHERE
        r_deb4fb.te_8a25e9 >= r_deb4fb.te_8a25e9 
        AND date(r_deb4fb.te_1b19aa) = r_deb4fb.te_8a25e9 
        AND r_deb4fb.te_1b19aa != TIMESTAMP'2024-10-11 12:16:31.534' 
    ORDER BY
        1 ASC NULLS FIRST 
    OFFSET 91) AS r_771251 LEFT JOIN
        db1.item AS r_7081ca 
            ON r_771251.te_8fafd1 <= r_7081ca.i_rec_end_date 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    r_22bbef.pf_a0773a / r_4936f2.i_manufact_id as te_2bb69b,
    r_22bbef.pf_a0773a as te_7a0eb9 
FROM
    db1.item AS r_4936f2,
    db1.date_dim AS r_8cbfa4 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.item PIVOT(count(i_product_name) AS pa_45d780 FOR i_wholesale_cost IN ((84.37075241) AS pf_692404,
            (69.38646466) AS pf_a0773a,
            (21.10217871) AS pf_ba9ff9))
    ) AS r_22bbef 
        ON r_8cbfa4.d_date_id ILIKE r_22bbef.i_brand 
WHERE
    r_4936f2.i_wholesale_cost != r_8cbfa4.d_dow 
    OR r_8cbfa4.d_qoy >= 65 
    AND r_22bbef.pf_a0773a = r_22bbef.pf_692404 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    r_30504c.hd_buy_potential as te_28a290 
FROM
    db1.customer_demographics AS r_cc5595 
RIGHT JOIN
    db1.household_demographics AS r_30504c 
        ON r_cc5595.cd_education_status ILIKE r_30504c.hd_buy_potential 
GROUP BY
    1 
ORDER BY
    1 
LIMIT 21;
----------->
SELECT
    r_945432.c_birth_month as te_1a4930,
    r_945432.c_last_name as te_31490f 
FROM
    db1.household_demographics AS r_95b6fa,
    db1.customer AS r_945432 
WHERE
    r_95b6fa.hd_buy_potential ILIKE r_945432.c_birth_country 
    AND r_945432.c_current_hdemo_sk < 8 
    OR r_945432.c_last_review_date_sk < r_95b6fa.hd_income_band_sk 
    AND r_945432.c_last_name LIKE 'g7x' 
    OR r_945432.c_preferred_cust_flag LIKE 'SW4Pg' 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_91381b.cc_rec_end_date as te_d1a47e 
FROM
    db1.call_center AS r_91381b 
LEFT JOIN
    db1.store_returns AS r_3287ed 
        ON r_91381b.cc_gmt_offset < r_3287ed.sr_return_quantity 
WHERE
    r_91381b.cc_sq_ft >= 42 
    AND r_91381b.cc_street_name NOT LIKE '8h' 
    OR r_91381b.cc_employees < 75 
    AND r_91381b.cc_gmt_offset != 75.26023303 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_6c7c57.c_first_name as te_80ccb2,
    r_6c7c57.c_birth_country as te_67a2fa 
FROM
    (SELECT
        r_f4fd2a.s_manager as te_400bd8 
    FROM
        db1.store AS r_f4fd2a 
    WHERE
        r_f4fd2a.s_number_employees >= r_f4fd2a.s_division_id 
    ORDER BY
        1 ASC NULLS FIRST) AS r_936cd1, db1.customer AS r_6c7c57 
INNER JOIN
    db1.store AS r_f3d89d 
        ON r_6c7c57.c_birth_year <= r_f3d89d.s_company_id 
WHERE
    r_936cd1.te_400bd8 NOT ILIKE r_f3d89d.s_market_manager 
    AND r_6c7c57.c_preferred_cust_flag = r_6c7c57.c_customer_id 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    78 - r_1c1c72.te_424f8d as te_cb2c51 
FROM
    (SELECT
        try_add(r_f87639.d_same_day_lq,
        r_f87639.d_fy_week_seq) as te_424f8d 
    FROM
        db1.date_dim AS r_f87639 
    RIGHT JOIN
        db1.item AS r_247f33 
            ON r_f87639.d_same_day_lq <= r_247f33.i_class_id 
    WHERE
        r_f87639.d_last_dom >= r_f87639.d_first_dom 
        AND r_f87639.d_current_month ILIKE 'IG7ir' 
        OR r_f87639.d_current_year NOT ILIKE r_f87639.d_current_quarter 
    ORDER BY
        1) AS r_1c1c72 
WHERE
    r_1c1c72.te_424f8d <= 69 
    AND r_1c1c72.te_424f8d > r_1c1c72.te_424f8d 
ORDER BY
    1 NULLS LAST 
OFFSET 21;
----------->
SELECT
    date_from_unix_date(r_a472ee.s_division_id) as te_417328 
FROM
    db1.store_returns AS r_61abab 
LEFT JOIN
    db1.store AS r_a472ee 
        ON r_61abab.sr_return_amt < r_a472ee.s_gmt_offset 
WHERE
    r_61abab.sr_fee > 26.42109257 
    AND r_a472ee.s_rec_end_date > DATE'2024-03-26' 
    AND r_a472ee.s_country != 'RQi' 
    OR r_61abab.sr_addr_sk < 89 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 82;
----------->
SELECT
    unix_timestamp() * r_8181a0.ca_gmt_offset as te_57b488 
FROM
    db1.customer_address AS r_8181a0 
INNER JOIN
    db1.ship_mode AS r_fb7eed 
        ON r_8181a0.ca_address_sk != r_fb7eed.sm_ship_mode_sk 
WHERE
    NOT r_8181a0.ca_country NOT ILIKE 'HZ' 
    OR r_8181a0.ca_gmt_offset >= 71.51900697 
    OR r_fb7eed.sm_ship_mode_id ILIKE 'kg7x' 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_0e571b.ca_address_sk / 1.68864508D as te_b81005 
FROM
    (SELECT
        * 
    FROM
        db1.customer_address PIVOT(count(ca_county) AS pa_46a97a FOR ca_street_number IN (('UgDDX') AS pf_2cab8d,
        ('XX4Q') AS pf_710405,
        ('Uulst') AS pf_dc8bfc,
        ('k') AS pf_36682f))) AS r_0e571b 
WHERE
    r_0e571b.ca_city < 'tb' 
    AND r_0e571b.ca_city NOT LIKE 'RI' 
    OR r_0e571b.ca_address_sk <= 84 
    AND r_0e571b.ca_street_type NOT LIKE r_0e571b.ca_suite_number 
ORDER BY
    1 DESC NULLS FIRST 
LIMIT 38;
----------->
SELECT
    double(47 + r_f223bc.ca_address_sk) as te_d54ca4 
FROM
    (SELECT
        current_timestamp() as te_345dd3 
    FROM
        db1.web_site AS r_7ad7a1 
    RIGHT JOIN
        db1.web_site AS r_f94046 
            ON r_7ad7a1.web_manager NOT ILIKE r_f94046.web_mkt_desc 
    WHERE
        r_f94046.web_company_id = r_f94046.web_open_date_sk 
        OR r_7ad7a1.web_state NOT ILIKE 'g' 
        AND r_7ad7a1.web_street_type NOT LIKE 'D' 
        AND EXISTS (
            SELECT
                r_450cb0.cc_market_manager as te_dbc739,
                add_months(r_450cb0.cc_rec_start_date,
                r_0a9f8e.d_fy_year) as te_78ca29 
            FROM
                db1.date_dim AS r_0a9f8e,
                db1.call_center AS r_450cb0 
            WHERE
                r_0a9f8e.d_dom != r_450cb0.cc_company 
                AND r_450cb0.cc_city NOT ILIKE r_450cb0.cc_zip 
                AND r_0a9f8e.d_week_seq >= r_0a9f8e.d_moy 
                AND r_450cb0.cc_market_manager NOT ILIKE r_450cb0.cc_suite_number 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC
        )
    ) AS r_f349bc 
RIGHT JOIN
    db1.customer_address AS r_f223bc 
        ON timestamp_millis(character_length(string(r_f349bc.te_345dd3))) >= timestamp_millis(length(chr(r_f223bc.ca_gmt_offset))) 
WHERE
    r_f223bc.ca_city ILIKE r_f223bc.ca_country 
    AND (
        NOT r_f223bc.ca_state NOT LIKE r_f223bc.ca_suite_number 
        OR r_f223bc.ca_zip NOT ILIKE 'x2SW'
    ) 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    chr(r_b4ee6d.web_company_id) as te_115939 
FROM
    db1.web_site AS r_b4ee6d 
RIGHT JOIN
    db1.warehouse AS r_cdb3ae 
        ON r_b4ee6d.web_mkt_id = r_cdb3ae.w_warehouse_sk 
WHERE
    r_b4ee6d.web_mkt_id != 69 
    OR r_b4ee6d.web_rec_start_date = r_b4ee6d.web_rec_end_date 
    AND r_b4ee6d.web_city LIKE 'vXUulstn' 
ORDER BY
    1 DESC 
LIMIT 29;
----------->
SELECT
    r_7b1439.r_reason_sk as te_f453eb 
FROM
    db1.reason AS r_7b1439 
WHERE
    r_7b1439.r_reason_desc NOT LIKE 'G6sUgDDXX' 
    AND r_7b1439.r_reason_sk = 17 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_ea174d.s_rec_end_date as te_f6dc9a 
FROM
    db1.call_center AS r_19a7ab 
LEFT JOIN
    db1.store AS r_ea174d 
        ON r_19a7ab.cc_rec_start_date <= r_ea174d.s_rec_start_date 
WHERE
    r_19a7ab.cc_rec_end_date > r_19a7ab.cc_rec_start_date 
    AND r_ea174d.s_store_id LIKE r_19a7ab.cc_call_center_id 
ORDER BY
    1 DESC;
----------->
SELECT
    unix_timestamp() as te_a8c085 
FROM
    db1.ship_mode AS r_e0349f 
WHERE
    (
        r_e0349f.sm_contract, r_e0349f.sm_ship_mode_sk
    ) IN (
        SELECT
            r_7b2f0e.ca_country as te_a7c4d4,
            hash(10 / r_7b2f0e.ca_address_sk,
            date_from_unix_date(mod(r_bbe637.ca_address_sk,
            unix_timestamp()) - r_bbe637.ca_address_sk)) as te_343986 
        FROM
            db1.customer_address AS r_7b2f0e 
        RIGHT JOIN
            db1.customer_address AS r_bbe637 
                ON r_7b2f0e.ca_address_sk = r_bbe637.ca_gmt_offset 
        WHERE
            r_bbe637.ca_zip NOT ILIKE 'G6sUg' 
            OR r_7b2f0e.ca_address_id LIKE r_7b2f0e.ca_suite_number 
            OR r_bbe637.ca_location_type ILIKE 'vXUul' 
            AND r_7b2f0e.ca_address_sk != 86 
        ORDER BY
            1, 2 ASC
    ) 
    OR r_e0349f.sm_ship_mode_id NOT ILIKE 'sUgDDXX4Q' 
    AND r_e0349f.sm_carrier ILIKE r_e0349f.sm_type 
    OR r_e0349f.sm_code ILIKE r_e0349f.sm_contract 
ORDER BY
    1 DESC 
LIMIT 6 OFFSET 33;
----------->
SELECT
    r_1a2d25.cc_gmt_offset as te_a23960,
    add_months(r_212c61.d_date,
    r_1a2d25.cc_call_center_sk) as te_c3d674 
FROM
    db1.customer AS r_ff22bf 
INNER JOIN
    db1.date_dim AS r_212c61 
        ON r_ff22bf.c_current_cdemo_sk = r_212c61.d_date_sk,
    db1.call_center AS r_1a2d25 
RIGHT JOIN
    (
        SELECT
            r_dc90c2.te_e217bc as te_514ac5,
            r_dc90c2.te_e217bc as te_d3a4dd,
            r_dc90c2.te_e217bc as te_29cfde 
        FROM
            db1.call_center AS r_dcced5 
        LEFT JOIN
            (
                SELECT
                    r_7e8822.ib_lower_bound * 10 as te_e217bc 
                FROM
                    db1.income_band AS r_7e8822 
                WHERE
                    (
                        NOT r_7e8822.ib_lower_bound = 37 
                        OR r_7e8822.ib_upper_bound > r_7e8822.ib_lower_bound
                    ) 
                    AND r_7e8822.ib_lower_bound < 66 
                    AND r_7e8822.ib_lower_bound = 44 
                ORDER BY
                    1 DESC NULLS LAST
            ) AS r_dc90c2 
                ON r_dcced5.cc_call_center_id NOT LIKE chr(r_dc90c2.te_e217bc),
            db1.item AS r_9ac709 
        INNER JOIN
            db1.ship_mode AS r_31b70d 
                ON r_9ac709.i_brand_id != r_31b70d.sm_ship_mode_sk 
        WHERE
            r_dcced5.cc_rec_start_date != r_9ac709.i_rec_start_date 
            OR r_31b70d.sm_code LIKE 'By7I' 
            AND r_9ac709.i_rec_start_date < DATE'2024-03-25' 
        ORDER BY
            1 DESC, 2 DESC NULLS LAST, 3 DESC NULLS LAST) AS r_c71ec9 
                ON r_1a2d25.cc_sq_ft != r_c71ec9.te_29cfde 
        WHERE
            r_212c61.d_date > r_1a2d25.cc_rec_start_date 
            OR r_1a2d25.cc_tax_percentage = r_1a2d25.cc_gmt_offset 
            OR r_ff22bf.c_birth_month != 78 
            AND r_c71ec9.te_d3a4dd >= 97 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC NULLS FIRST;
----------->
SELECT
    r_c11553.hd_buy_potential as te_bf9d11 
FROM
    db1.store_returns AS r_d0b5b6 
RIGHT JOIN
    db1.household_demographics AS r_c11553 
        ON r_d0b5b6.sr_item_sk = r_c11553.hd_income_band_sk 
WHERE
    r_d0b5b6.sr_return_time_sk > 55 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_b082f0.i_color as te_2da7c3,
    r_b082f0.i_wholesale_cost as te_0fe69a,
    r_d465fe.cc_call_center_sk as te_11c62f 
FROM
    db1.call_center AS r_d465fe 
LEFT JOIN
    (
        SELECT
            * 
        FROM
            db1.ship_mode PIVOT(min(sm_contract) AS pa_c879ba FOR sm_code IN (('7ir98h') AS pf_23eee9,
            ('y7I') AS pf_4ef7a0,
            ('l') AS pf_5c11ea))
    ) AS r_2c3fe2 
        ON r_d465fe.cc_mkt_desc LIKE r_2c3fe2.sm_carrier,
    db1.item AS r_b082f0 
WHERE
    r_d465fe.cc_gmt_offset >= r_b082f0.i_class_id 
    OR r_b082f0.i_class NOT ILIKE 'sUgDDXX4QH' 
ORDER BY
    1 NULLS FIRST, 2 DESC NULLS LAST, 3 ASC NULLS LAST;
----------->
SELECT
    r_7b8e82.c_customer_id as te_b3badc,
    r_35396e.wp_web_page_id as te_a7d4bf 
FROM
    db1.customer AS r_7b8e82,
    db1.household_demographics AS r_1f3266 
INNER JOIN
    db1.web_page AS r_35396e 
        ON r_1f3266.hd_buy_potential ILIKE r_35396e.wp_type 
WHERE
    r_7b8e82.c_first_sales_date_sk != r_35396e.wp_link_count 
    AND r_1f3266.hd_vehicle_count != r_7b8e82.c_first_shipto_date_sk 
    AND r_35396e.wp_autogen_flag NOT ILIKE 'SR' 
    OR r_7b8e82.c_last_name LIKE '7ir98hv' 
ORDER BY
    1 NULLS FIRST, 2 ASC NULLS FIRST;
----------->
SELECT
    r_7973f9.pf_586843 as te_fa9ddf,
    r_6c2a3f.hd_buy_potential as te_efc556,
    chr(r_6c2a3f.hd_income_band_sk) as te_069a76 
FROM
    db1.household_demographics AS r_6c2a3f 
RIGHT JOIN
    db1.customer AS r_cf6289 
        ON r_6c2a3f.hd_buy_potential NOT LIKE r_cf6289.c_login,
    (SELECT
        * 
    FROM
        (SELECT
            r_5eee58.hd_buy_potential as te_b8ea7c,
            r_5eee58.hd_vehicle_count as te_4b5fdd,
            r_a0a1f3.w_city as te_7de43f 
        FROM
            db1.household_demographics AS r_5eee58,
            db1.warehouse AS r_a0a1f3 
        WHERE
            r_5eee58.hd_income_band_sk <= r_a0a1f3.w_warehouse_sq_ft) PIVOT(count(te_7de43f) AS pa_942536 FOR te_b8ea7c IN (('2kGk4el') AS pf_18fcdb,
        ('x6kg7x2S') AS pf_586843,
        ('QFrTUOVb') AS pf_d3d5b0,
        ('px6kg7x2SW') AS pf_2f9c27))) AS r_7973f9 
    LEFT JOIN
        db1.household_demographics AS r_b6b43e 
            ON r_7973f9.te_4b5fdd = r_b6b43e.hd_vehicle_count 
    WHERE
        r_cf6289.c_customer_sk <= r_7973f9.te_4b5fdd 
    ORDER BY
        1 DESC NULLS FIRST, 2 NULLS LAST, 3 ASC NULLS FIRST;
----------->
SELECT
    length(r_958fd7.d_current_week) as te_8ff001 
FROM
    db1.date_dim AS r_958fd7 
WHERE
    r_958fd7.d_current_quarter LIKE 'kg7x2' 
    AND r_958fd7.d_week_seq != r_958fd7.d_fy_week_seq 
    AND r_958fd7.d_current_quarter NOT ILIKE r_958fd7.d_current_week 
    AND r_958fd7.d_fy_year = r_958fd7.d_dom 
ORDER BY
    1 DESC 
OFFSET 29;
----------->
SELECT
    r_1a7701.w_gmt_offset as te_1c7039 
FROM
    db1.warehouse AS r_1a7701 
RIGHT JOIN
    db1.household_demographics AS r_4a019d 
        ON r_1a7701.w_warehouse_sk >= r_4a019d.hd_vehicle_count 
WHERE
    r_1a7701.w_warehouse_id NOT ILIKE 'T' 
    OR r_1a7701.w_warehouse_name < 'Iv' 
ORDER BY
    1 DESC;
----------->
SELECT
    r_c53712.cd_gender as te_6aa5d0,
    r_b8b94c.c_customer_id as te_c3edb9,
    r_ad4945.c_preferred_cust_flag as te_42b5dc 
FROM
    db1.customer AS r_ad4945 
INNER JOIN
    db1.customer AS r_b8b94c 
        ON r_ad4945.c_first_sales_date_sk > r_b8b94c.c_first_shipto_date_sk,
    db1.customer_demographics AS r_c53712 
WHERE
    r_ad4945.c_current_cdemo_sk <= r_c53712.cd_dep_count 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 ASC;
----------->
SELECT
    r_b889dc.r_reason_desc as te_b3e503 
FROM
    db1.reason AS r_b889dc 
WHERE
    r_b889dc.r_reason_desc NOT ILIKE '2S' 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    hash(77 - r_f6aa19.c_current_cdemo_sk,
    r_4c72c4.wp_rec_start_date) as te_dccd18,
    e() as te_54ce5d,
    try_add(r_f6aa19.c_first_sales_date_sk,
    59) as te_94dcc9 
FROM
    db1.warehouse AS r_76831a 
RIGHT JOIN
    db1.web_page AS r_4c72c4 
        ON r_76831a.w_warehouse_sq_ft > r_4c72c4.wp_image_count,
    db1.customer AS r_f6aa19 
WHERE
    r_4c72c4.wp_web_page_id NOT LIKE r_f6aa19.c_preferred_cust_flag 
ORDER BY
    1 NULLS LAST, 2, 3 DESC NULLS LAST;
----------->
SELECT
    r_3b2cc2.ib_income_band_sk as te_42c9ed,
    hex(mod(r_3b2cc2.ib_lower_bound,
    59.83945005D)) as te_a6f4cf,
    unix_timestamp() as te_739650 
FROM
    (SELECT
        r_9d0482.t_second as te_6415f6 
    FROM
        db1.web_page AS r_0bad52 
    INNER JOIN
        db1.time_dim AS r_9d0482 
            ON r_0bad52.wp_char_count = r_9d0482.t_minute 
    WHERE
        r_0bad52.wp_web_page_sk = 60 
        OR r_0bad52.wp_rec_end_date > r_0bad52.wp_rec_start_date 
        OR r_0bad52.wp_rec_start_date = r_0bad52.wp_rec_end_date) AS r_806869,
    db1.income_band AS r_3b2cc2 
WHERE
    r_806869.te_6415f6 >= r_3b2cc2.ib_upper_bound 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST 
LIMIT 29;
----------->
SELECT
    r_c52997.sm_ship_mode_sk as te_787866,
    substring(r_c52997.sm_ship_mode_id,
    82) as te_7ed7c5 
FROM
    db1.ship_mode AS r_c52997,
    (SELECT
        r_f374cd.cd_purchase_estimate * 58.28312868D as te_e08b85,
        r_f374cd.cd_marital_status as te_745223 
    FROM
        db1.customer_demographics AS r_f374cd,
        db1.ship_mode AS r_c02a37 
    WHERE
        r_f374cd.cd_dep_employed_count >= r_c02a37.sm_ship_mode_sk) AS r_a1db14 
WHERE
    r_c52997.sm_carrier LIKE string(r_a1db14.te_745223) 
    AND r_c52997.sm_ship_mode_sk = 23 
ORDER BY
    1 DESC NULLS FIRST, 2 NULLS LAST;
----------->
SELECT
    sin(r_0453d3.pf_fd6e34) as te_00123e,
    r_86eba1.te_43f4b0 as te_5d84e1,
    r_0453d3.pf_2ecf09 * 56.10194185D as te_e191da 
FROM
    (SELECT
        * 
    FROM
        db1.reason PIVOT(count(r_reason_desc) AS pa_25a2f4 FOR r_reason_sk IN ((51) AS pf_fd6e34,
        (7) AS pf_6f48c2,
        (18) AS pf_4a767b,
        (8) AS pf_99e5bb,
        (70) AS pf_2ecf09))) AS r_0453d3 
INNER JOIN
    (
        SELECT
            r_c9a9ba.cd_marital_status as te_c1a412,
            r_646d4d.sm_ship_mode_id as te_9aea04 
        FROM
            db1.ship_mode AS r_646d4d 
        LEFT JOIN
            db1.customer_demographics AS r_c9a9ba 
                ON r_646d4d.sm_ship_mode_sk >= r_c9a9ba.cd_dep_employed_count,
            (SELECT
                r_8f788d.web_manager as te_fa4ee0,
                r_df0b04.d_fy_year as te_592dff,
                r_535368.w_warehouse_id as te_5fe483 
            FROM
                db1.web_site AS r_8f788d,
                db1.date_dim AS r_df0b04 
            INNER JOIN
                db1.warehouse AS r_535368 
                    ON r_df0b04.d_date_sk < r_535368.w_warehouse_sq_ft 
            WHERE
                r_8f788d.web_gmt_offset = r_535368.w_gmt_offset 
                OR r_df0b04.d_dow > 33 
                OR r_535368.w_warehouse_sk >= 53 
            ORDER BY
                1 DESC, 2 DESC, 3 NULLS FIRST) AS r_8338b8 
        WHERE
            r_646d4d.sm_ship_mode_sk <= r_8338b8.te_592dff 
            AND r_c9a9ba.cd_gender NOT ILIKE r_c9a9ba.cd_education_status 
        ORDER BY
            1 ASC, 2 NULLS FIRST 
        LIMIT 40) AS r_7c9a97 
            ON r_0453d3.r_reason_id LIKE r_7c9a97.te_c1a412, (SELECT
                r_67635e.te_4e2be3 as te_849f43, r_edf949.d_date as te_43f4b0, r_edf949.d_dom - 82 as te_b65ff0 FROM
                    (WITH CTE_af1ed1(te_6e6d52) AS (SELECT
                        r_5d44c8.cd_dep_count as te_6e6d52 
                FROM
                    db1.customer_demographics AS r_5d44c8 
                ORDER BY
                    1 DESC NULLS LAST), CTE_76a362(te_779d59) AS (SELECT
                    r_43cc30.sr_addr_sk as te_779d59 
                FROM
                    db1.store_returns AS r_43cc30 
                WHERE
                    r_43cc30.sr_return_amt_inc_tax != 30.01417097 
                    OR r_43cc30.sr_return_time_sk BETWEEN 80 AND r_43cc30.web_county + 66 
                ORDER BY
                    1 DESC NULLS LAST) SELECT
                    r_0d597d.ca_address_sk + r_668ef7.te_6e6d52 as te_950153, r_0d597d.ca_city as te_2612ec, r_0d597d.ca_gmt_offset + r_bc8a0f.te_779d59 as te_c8f837 
                FROM
                    CTE_af1ed1 AS r_668ef7 
                RIGHT JOIN
                    CTE_76a362 AS r_bc8a0f 
                        ON r_668ef7.te_6e6d52 > r_bc8a0f.te_779d59,
                    db1.customer_address AS r_0d597d 
                WHERE
                    r_bc8a0f.te_779d59 <= r_0d597d.ca_address_sk 
                    OR r_0d597d.ca_city LIKE 'L' 
                ORDER BY
                    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 ASC NULLS LAST 
                OFFSET 64) AS r_1e9769 LEFT JOIN
                (
                    SELECT
                        chr(r_56d69c.wp_image_count - 30) as te_4e2be3,
                        current_timestamp() as te_3e010d 
                    FROM
                        db1.income_band AS r_7da42d,
                        db1.web_page AS r_56d69c 
                    WHERE
                        r_7da42d.ib_lower_bound = r_56d69c.wp_link_count 
                        AND r_56d69c.wp_rec_start_date >= DATE'2024-10-11' 
                        OR r_56d69c.wp_autogen_flag LIKE r_56d69c.wp_web_page_id 
                        AND r_56d69c.wp_url ILIKE r_56d69c.wp_type 
                    ORDER BY
                        1 DESC NULLS FIRST, 2 NULLS LAST
                ) AS r_67635e 
                    ON timestamp_millis(r_1e9769.te_950153) <= r_67635e.te_3e010d,
                (SELECT
                    make_timestamp(32, 91, 89, 36, 30, 89) as te_e79878 
                FROM
                    db1.income_band AS r_68242f 
                WHERE
                    r_68242f.ib_income_band_sk > 53 
                    OR EXISTS (
                        SELECT
                            r_b93395.i_rec_end_date as te_182e56 
                        FROM
                            (SELECT
                                * 
                            FROM
                                db1.customer_address PIVOT(count(ca_state) AS pa_937d0c FOR ca_street_name IN (('k') AS pf_a04637,
                                ('b2Gv7RXpRQ') AS pf_030389,
                                ('2Gv7RX') AS pf_0bd243,
                                ('7x') AS pf_42a9c3,
                                ('GIHlpx6') AS pf_710b4d,
                                ('4elBy7I') AS pf_f36922))) AS r_911cfa 
                        RIGHT JOIN
                            db1.item AS r_b93395 
                                ON r_911cfa.pf_030389 BETWEEN 37 AND 37 
                        WHERE
                            r_911cfa.ca_location_type LIKE r_b93395.i_item_id 
                            OR r_b93395.i_manufact_id != 7 
                            OR r_b93395.i_brand ILIKE r_b93395.i_class 
                        ORDER BY
                            1 DESC NULLS LAST) 
                        ORDER BY
                            1 DESC NULLS LAST
                    ) AS r_2d1e1c 
                INNER JOIN
                    db1.date_dim AS r_edf949 
                        ON date(r_2d1e1c.te_e79878) != r_edf949.d_date 
                WHERE
                    r_1e9769.te_c8f837 != r_edf949.d_quarter_seq 
                    OR NOT r_edf949.d_dow > r_edf949.d_fy_week_seq 
                    AND NOT r_edf949.d_current_week LIKE 'SW4Pg' 
                    OR r_edf949.d_day_name NOT LIKE 'C0le' 
                    AND r_edf949.d_week_seq <= 42 
                ORDER BY
                    1 NULLS LAST, 2 DESC NULLS LAST, 3 DESC) AS r_86eba1 
                WHERE
                    NOT r_7c9a97.te_c1a412 LIKE r_86eba1.te_849f43 
                    OR r_0453d3.pf_fd6e34 >= 69 
                    OR r_86eba1.te_849f43 NOT ILIKE 'lB' 
                ORDER BY
                    1 DESC NULLS LAST, 2 DESC, 3 NULLS LAST;
----------->
SELECT
    r_48b892.r_reason_id as te_fd0b45,
    r_01f0cb.sr_reason_sk as te_ee1632,
    overlay(r_48b892.r_reason_id PLACING r_040e45.cd_purchase_estimate 
FROM
    r_01f0cb.sr_addr_sk) as te_f53cd9 
FROM
    db1.store_returns AS r_01f0cb 
LEFT JOIN
    db1.reason AS r_48b892 
        ON r_01f0cb.sr_return_ship_cost = r_48b892.r_reason_sk,
    db1.customer_demographics AS r_040e45 
WHERE
    r_040e45.cd_marital_status IS NULL 
    OR r_01f0cb.sr_cdemo_sk != 50 
    OR r_48b892.r_reason_id NOT ILIKE r_48b892.r_reason_desc 
    AND r_01f0cb.sr_reversed_charge >= 85.87484631 
GROUP BY
    1, 3, 2 
ORDER BY
    1 ASC NULLS LAST, 2 DESC NULLS LAST, 3 NULLS FIRST 
LIMIT 42;
----------->
SELECT
    r_58ce21.w_gmt_offset as te_66e243,
    chr(r_fc2da9.i_brand_id) as te_0c49ab,
    r_fc2da9.i_rec_end_date as te_751b4f 
FROM
    db1.warehouse AS r_58ce21 
RIGHT JOIN
    db1.store AS r_f3265b 
        ON r_58ce21.w_warehouse_sq_ft != r_f3265b.s_closed_date_sk,
    db1.item AS r_fc2da9 
RIGHT JOIN
    db1.ship_mode AS r_37907f 
        ON r_fc2da9.i_manufact_id = r_37907f.sm_ship_mode_sk 
WHERE
    r_f3265b.s_market_id != r_fc2da9.i_wholesale_cost 
    OR (
        NOT r_f3265b.s_rec_end_date != DATE'2024-03-25' 
        AND r_fc2da9.i_rec_end_date BETWEEN DATE'2024-03-25' AND DATE'2024-03-25'
    ) 
ORDER BY
    1 DESC, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_22844e.c_customer_id as te_16799b 
FROM
    db1.customer AS r_22844e 
WHERE
    r_22844e.c_birth_country NOT ILIKE 'kGk4elBy' 
    OR r_22844e.c_login ILIKE 'OVbBRG' 
    OR r_22844e.c_current_cdemo_sk < 98 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_555c1e(te_dbc7b5) AS (SELECT
    chr(r_fae0eb.s_floor_space) as te_dbc7b5 
FROM
    db1.store AS r_fae0eb 
WHERE
    (NOT r_fae0eb.s_rec_start_date >= r_fae0eb.s_rec_end_date 
    OR r_fae0eb.s_rec_end_date > r_fae0eb.s_rec_start_date) 
    AND r_fae0eb.s_county > '7IG7' 
    OR r_fae0eb.s_city NOT ILIKE '7IG7ir9' 
ORDER BY
    1) SELECT
    r_ddc071.ca_county as te_1ed8c2, r_e26cf0.c_first_shipto_date_sk - r_ddc071.ca_gmt_offset as te_9cab72 
FROM
    CTE_555c1e AS r_09b8de 
LEFT JOIN
    db1.customer_demographics AS r_df0e8d 
        ON r_09b8de.te_dbc7b5 LIKE r_df0e8d.cd_credit_rating,
    db1.customer_address AS r_ddc071 
INNER JOIN
    db1.customer AS r_e26cf0 
        ON r_ddc071.ca_street_number NOT LIKE r_e26cf0.c_customer_id 
WHERE
    r_df0e8d.cd_purchase_estimate > r_e26cf0.c_first_shipto_date_sk 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_61212c.cc_rec_end_date as te_7818d6 
FROM
    db1.reason AS r_1eb0f4 
LEFT JOIN
    db1.call_center AS r_61212c 
        ON r_1eb0f4.r_reason_desc ILIKE r_61212c.cc_zip 
WHERE
    r_61212c.cc_rec_end_date = r_61212c.cc_rec_start_date 
    OR NOT EXISTS (
        SELECT
            13.82127472D / r_85707a.d_fy_week_seq as te_35a457 
        FROM
            db1.date_dim AS r_85707a 
        LEFT JOIN
            (
                SELECT
                    add_months(make_date(r_42af2f.ib_lower_bound,
                    r_6ab39e.w_warehouse_sk,
                    hash(r_6ab39e.w_warehouse_sk + 27,
                    true)),
                    unix_timestamp()) as te_ee4ccf 
                FROM
                    db1.income_band AS r_42af2f 
                INNER JOIN
                    db1.warehouse AS r_6ab39e 
                        ON r_42af2f.ib_income_band_sk >= r_6ab39e.w_warehouse_sk 
                ORDER BY
                    1 DESC NULLS LAST
            ) AS r_f15764 
                ON r_85707a.d_date < r_f15764.te_ee4ccf 
        WHERE
            r_f15764.te_ee4ccf BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
            AND r_f15764.te_ee4ccf = r_85707a.d_date 
        ORDER BY
            1 ASC NULLS LAST) 
            OR r_61212c.cc_mkt_id < r_61212c.cc_company 
        ORDER BY
            1 DESC 
        LIMIT 37;
----------->
SELECT
    r_9ae102.d_quarter_name as te_555aa8,
    r_9ae102.d_last_dom as te_5166f5 
FROM
    db1.ship_mode AS r_072e6d 
LEFT JOIN
    db1.call_center AS r_66b179 
        ON r_072e6d.sm_carrier NOT ILIKE r_66b179.cc_street_number,
    db1.item AS r_99072c 
LEFT JOIN
    db1.date_dim AS r_9ae102 
        ON r_99072c.i_rec_end_date != r_9ae102.d_date 
WHERE
    r_66b179.cc_tax_percentage = r_99072c.i_current_price 
    OR r_66b179.cc_street_type NOT LIKE '7x2SW' 
    OR r_66b179.cc_rec_start_date NOT IN (
        SELECT
            DATE'2024-03-25' as te_891513 
        FROM
            db1.store_returns AS r_c988e6,
            db1.reason AS r_ed7f0d 
        WHERE
            r_c988e6.sr_item_sk IS NOT NULL 
            AND r_c988e6.sr_reason_sk = r_c988e6.sr_customer_sk 
            OR r_c988e6.sr_return_tax = r_c988e6.sr_reversed_charge 
            OR r_ed7f0d.r_reason_desc NOT ILIKE r_ed7f0d.r_reason_id 
        ORDER BY
            1 DESC NULLS FIRST 
        LIMIT 55
) 
AND r_9ae102.d_date <= r_66b179.cc_rec_start_date 
OR r_9ae102.d_holiday ILIKE r_9ae102.d_weekend ORDER BY
    1 ASC, 2 DESC NULLS LAST;
----------->
SELECT
    mod(r_2ccb5f.w_warehouse_sq_ft,
    42) / r_2ccb5f.w_warehouse_sk / e() as te_f849b5 
FROM
    db1.warehouse AS r_2ccb5f 
RIGHT JOIN
    (
        SELECT
            r_0b8953.s_division_name as te_4f919c 
        FROM
            db1.store AS r_0b8953 
        WHERE
            r_0b8953.s_street_type NOT LIKE 'CMaL' 
            AND r_0b8953.s_gmt_offset = r_0b8953.s_tax_percentage 
            OR r_0b8953.s_street_name NOT ILIKE 'mSRIv' 
            OR r_0b8953.s_rec_end_date < r_0b8953.s_rec_start_date 
        ORDER BY
            1 ASC
    ) AS r_a38096 
        ON r_2ccb5f.w_suite_number LIKE r_a38096.te_4f919c 
WHERE
    r_2ccb5f.w_warehouse_sq_ft > 8 
    OR EXISTS (
        SELECT
            current_timestamp() as te_f55e23 
        FROM
            db1.time_dim AS r_e271ae 
        LEFT JOIN
            db1.web_page AS r_762097 
                ON r_e271ae.t_am_pm NOT LIKE r_762097.wp_type 
        WHERE
            r_762097.wp_web_page_sk < r_e271ae.t_time 
            OR r_762097.wp_autogen_flag IS NOT NULL 
            AND r_762097.wp_char_count BETWEEN 25 AND r_762097.wp_web_page_sk - 96 
        ORDER BY
            1 DESC NULLS LAST
    ) 
    AND r_2ccb5f.w_country LIKE r_2ccb5f.w_street_type 
    OR r_2ccb5f.w_street_name NOT LIKE 'g7x2' 
ORDER BY
    1 ASC;
----------->
SELECT
    r_525f23.te_9ef363 as te_eed7b4,
    r_b4cd7a.t_shift as te_e16c04 
FROM
    (SELECT
        hex(r_0227cc.web_open_date_sk) as te_75f01e,
        r_0227cc.web_gmt_offset / r_0227cc.web_tax_percentage as te_c447cb 
    FROM
        db1.customer_address AS r_fb0ce7 
    RIGHT JOIN
        db1.web_site AS r_0227cc 
            ON r_fb0ce7.ca_zip NOT LIKE r_0227cc.web_street_type,
        db1.customer AS r_0f24f2 
    INNER JOIN
        db1.customer_demographics AS r_1227e2 
            ON r_0f24f2.c_birth_month >= r_1227e2.cd_dep_count 
    WHERE
        r_fb0ce7.ca_street_type LIKE r_0f24f2.c_preferred_cust_flag 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS LAST) AS r_86cc45 
RIGHT JOIN
    (
        SELECT
            r_daa09c.cd_gender as te_79fe7e,
            chr(r_51c7a9.c_last_review_date_sk) as te_d8c280,
            r_51c7a9.c_current_cdemo_sk as te_9ef363 
        FROM
            db1.income_band AS r_2af24e 
        LEFT JOIN
            db1.customer AS r_51c7a9 
                ON r_2af24e.ib_lower_bound = r_51c7a9.c_birth_year,
            db1.customer_demographics AS r_daa09c 
        RIGHT JOIN
            db1.ship_mode AS r_d6c7be 
                ON r_daa09c.cd_gender LIKE r_d6c7be.sm_ship_mode_id 
        WHERE
            r_2af24e.ib_upper_bound > r_d6c7be.sm_ship_mode_sk 
            AND r_51c7a9.c_first_shipto_date_sk != 36
    ) AS r_525f23 
        ON r_86cc45.te_75f01e ILIKE r_525f23.te_d8c280,
    (SELECT
        r_a73382.web_state as te_4767fa,
        r_a73382.web_market_manager as te_3b0950 
    FROM
        db1.web_site AS r_a73382 
    INNER JOIN
        db1.item AS r_855db7 
            ON r_a73382.web_gmt_offset < r_855db7.i_wholesale_cost,
        db1.reason AS r_bdcf35 
    RIGHT JOIN
        db1.web_page AS r_5ea343 
            ON r_bdcf35.r_reason_id LIKE r_5ea343.wp_type 
    WHERE
        r_a73382.web_street_name NOT LIKE r_5ea343.wp_url 
        AND r_a73382.web_state NOT ILIKE '4elBy' 
        AND r_a73382.web_site_id ILIKE r_855db7.i_item_id 
        OR r_5ea343.wp_web_page_sk BETWEEN 46 AND 46 
    ORDER BY
        1 NULLS LAST, 2 DESC NULLS LAST 
    OFFSET 94) AS r_bdca39 RIGHT JOIN
    db1.time_dim AS r_b4cd7a 
        ON r_bdca39.te_3b0950 ILIKE r_b4cd7a.t_am_pm 
WHERE
    r_86cc45.te_75f01e NOT LIKE r_b4cd7a.t_shift 
    AND r_86cc45.te_75f01e ILIKE '0lez' 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_5b5a90.d_date_id as te_9cea2d,
    unix_timestamp() as te_5808f6,
    r_5b5a90.d_current_year as te_ed5935 
FROM
    db1.date_dim AS r_5b5a90,
    db1.time_dim AS r_13a45d 
WHERE
    r_5b5a90.d_first_dom < r_13a45d.t_time_sk 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST, 3 DESC NULLS LAST 
OFFSET 96;
----------->
SELECT
    r_5d6e30.d_date as te_d64208 
FROM
    db1.household_demographics AS r_b1ff21 
RIGHT JOIN
    db1.date_dim AS r_5d6e30 
        ON r_b1ff21.hd_buy_potential ILIKE r_5d6e30.d_day_name 
WHERE
    r_5d6e30.d_dom != 6 
    OR r_5d6e30.d_fy_week_seq = 30 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_1552bb.cc_gmt_offset as te_1abb5a 
FROM
    db1.call_center AS r_1552bb 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    add_months(r_ae2743.d_date,
    r_ae2743.d_fy_year) as te_4e172a 
FROM
    db1.reason AS r_c2a524 
RIGHT JOIN
    db1.date_dim AS r_ae2743 
        ON r_c2a524.r_reason_sk = r_ae2743.d_dow 
WHERE
    r_ae2743.d_same_day_lq >= 62 
    OR r_c2a524.r_reason_desc NOT LIKE 'VbBRGC0' 
    OR r_ae2743.d_date_id >= r_ae2743.d_holiday 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_498b1c.web_street_number as te_b03aba,
    r_498b1c.web_country as te_5cbecb 
FROM
    db1.web_site AS r_498b1c 
RIGHT JOIN
    db1.household_demographics AS r_2004e8 
        ON r_498b1c.web_company_id != r_2004e8.hd_vehicle_count,
    db1.web_page AS r_0deb5e 
RIGHT JOIN
    db1.customer AS r_6076ec 
        ON r_0deb5e.wp_url NOT LIKE r_6076ec.c_email_address 
WHERE
    r_0deb5e.wp_rec_start_date BETWEEN r_0deb5e.wp_rec_end_date AND DATE'2024-10-11' 
    OR r_498b1c.web_company_id >= 59 
    OR r_0deb5e.wp_rec_start_date >= r_0deb5e.wp_rec_end_date 
    OR r_2004e8.hd_vehicle_count <= 20 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC;
----------->
SELECT
    make_date(r_2dd5d6.c_last_review_date_sk,
    r_2dd5d6.c_current_cdemo_sk,
    r_2dd5d6.c_current_hdemo_sk) as te_eed027,
    r_2dd5d6.c_birth_year + 46.73120336D as te_f01c0b,
    r_2dd5d6.c_last_name as te_a70ee7 
FROM
    (SELECT
        r_bebb1b.ca_gmt_offset as te_595d93,
        r_bebb1b.ca_state as te_783910 
    FROM
        (SELECT
            * 
        FROM
            (SELECT
                r_c38dad.d_holiday as te_5fc01c,
                r_c38dad.d_quarter_seq + 81.18007113D as te_b0fb33 
            FROM
                db1.web_page AS r_2251b0,
                db1.date_dim AS r_c38dad 
            WHERE
                r_2251b0.wp_web_page_id LIKE r_c38dad.d_weekend 
                AND r_c38dad.d_first_dom != 55 
                AND r_2251b0.wp_rec_start_date < DATE'2024-10-11' 
            ORDER BY
                1 DESC NULLS LAST, 2 DESC) PIVOT(min(te_5fc01c) AS pa_379cfb FOR te_b0fb33 IN ((17.51931673D) AS pf_1bc2d0, (40.0343493D) AS pf_12401f, (40.26129602D) AS pf_ae77b5, (35.91113861D) AS pf_4830d1, (69.30096845D) AS pf_8d740a))) AS r_2b020e 
        LEFT JOIN
            db1.customer_demographics AS r_bb54c4 
                ON r_2b020e.pf_12401f LIKE r_bb54c4.cd_marital_status,
            db1.customer_address AS r_bebb1b 
        RIGHT JOIN
            db1.household_demographics AS r_7bf8d1 
                ON r_bebb1b.ca_address_sk >= r_7bf8d1.hd_vehicle_count 
        WHERE
            r_bb54c4.cd_dep_count != r_7bf8d1.hd_income_band_sk 
            OR r_bb54c4.cd_dep_count BETWEEN 78 AND 78 
            AND (
                NOT r_7bf8d1.hd_vehicle_count < r_bb54c4.cd_purchase_estimate 
                OR r_2b020e.pf_4830d1 ILIKE 'RG' 
                OR r_7bf8d1.hd_vehicle_count >= r_7bf8d1.hd_income_band_sk
            ) 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC 
        OFFSET 17) AS r_f88f87 RIGHT JOIN
        db1.customer AS r_2dd5d6 
            ON r_f88f87.te_595d93 >= r_2dd5d6.c_birth_year,
        db1.time_dim AS r_7f21ba 
    WHERE
        r_2dd5d6.c_first_name = r_7f21ba.t_shift 
        AND NOT r_7f21ba.t_second != 0 
        AND r_2dd5d6.c_birth_country ILIKE 'i' 
        AND r_2dd5d6.c_customer_id != r_2dd5d6.c_preferred_cust_flag 
        AND r_2dd5d6.c_current_hdemo_sk < 83 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 ASC NULLS FIRST;
----------->
SELECT
    r_2755c1.w_suite_number as te_595aed,
    r_ed568f.w_warehouse_name as te_9c65ae,
    r_ed568f.w_gmt_offset as te_5d4174 
FROM
    db1.time_dim AS r_94275a 
LEFT JOIN
    db1.time_dim AS r_f7dcfe 
        ON r_94275a.t_meal_time NOT ILIKE r_f7dcfe.t_am_pm,
    db1.warehouse AS r_ed568f 
INNER JOIN
    db1.warehouse AS r_2755c1 
        ON r_ed568f.w_gmt_offset <= r_2755c1.w_gmt_offset 
WHERE
    r_f7dcfe.t_time > r_2755c1.w_warehouse_sk 
    AND r_2755c1.w_gmt_offset < r_ed568f.w_gmt_offset 
ORDER BY
    1 ASC, 2 DESC NULLS LAST, 3 NULLS LAST;
----------->
SELECT
    r_c3cb0f.i_units as te_e2d58e,
    make_date(r_c3cb0f.i_manager_id,
    r_8d245a.c_first_sales_date_sk,
    r_c3cb0f.i_class_id) as te_e12f00 
FROM
    db1.item AS r_c3cb0f,
    db1.reason AS r_f5b7cb 
LEFT JOIN
    db1.customer AS r_8d245a 
        ON r_f5b7cb.r_reason_id ILIKE r_8d245a.c_first_name 
WHERE
    r_c3cb0f.i_manager_id <= r_8d245a.c_first_shipto_date_sk 
    OR r_c3cb0f.i_class NOT LIKE r_8d245a.c_last_name 
    OR r_8d245a.c_current_hdemo_sk < 0 
    AND r_f5b7cb.r_reason_sk != 6 
ORDER BY
    1 DESC NULLS LAST, 2 ASC;
----------->
SELECT
    r_e95f07.te_ba60d1 as te_b42443 
FROM
    (SELECT
        chr(r_abc4e2.i_current_price) as te_ba60d1,
        r_56e467.wp_web_page_id as te_6d7719,
        r_abc4e2.i_item_sk as te_5084a1 
    FROM
        db1.ship_mode AS r_b1c24d 
    RIGHT JOIN
        db1.item AS r_abc4e2 
            ON r_b1c24d.sm_type NOT ILIKE r_abc4e2.i_item_desc,
        db1.income_band AS r_f3fda5 
    INNER JOIN
        db1.web_page AS r_56e467 
            ON r_f3fda5.ib_income_band_sk < r_56e467.wp_char_count 
    WHERE
        NOT r_abc4e2.i_item_id LIKE r_56e467.wp_web_page_id 
        AND r_56e467.wp_rec_end_date = DATE'2024-10-11' 
        OR r_56e467.wp_char_count > 50 
    ORDER BY
        1 DESC NULLS LAST, 2 NULLS LAST, 3 DESC) AS r_e95f07 
RIGHT JOIN
    (
        SELECT
            r_0eadc6.sr_returned_date_sk * r_0eadc6.sr_return_ship_cost as te_9fb142 
        FROM
            db1.store_returns AS r_0eadc6 
        WHERE
            r_0eadc6.sr_return_amt_inc_tax != r_0eadc6.sr_reversed_charge 
            OR r_0eadc6.sr_ticket_number <= r_0eadc6.sr_hdemo_sk 
            OR r_0eadc6.sr_return_ship_cost < 35.88289108 
            AND r_0eadc6.sr_hdemo_sk > r_0eadc6.sr_reason_sk 
        ORDER BY
            1 DESC NULLS LAST 
        LIMIT 78
) AS r_9d5324 
    ON r_e95f07.te_6d7719 ILIKE chr(r_9d5324.te_9fb142) WHERE
        r_e95f07.te_ba60d1 NOT LIKE r_e95f07.te_6d7719 
        OR r_e95f07.te_ba60d1 ILIKE r_e95f07.te_6d7719 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_a0d37e.sm_ship_mode_sk as te_565df9 
FROM
    db1.ship_mode AS r_a0d37e 
WHERE
    (
        NOT r_a0d37e.sm_ship_mode_id LIKE r_a0d37e.sm_type 
        AND r_a0d37e.sm_contract > 'lpx6kg'
    ) 
    OR r_a0d37e.sm_ship_mode_id NOT ILIKE 'Ug' 
    AND r_a0d37e.sm_ship_mode_id LIKE 'izz2kGk4el' 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_63c2aa.wp_max_ad_count / 85.14946555D as te_5196f0,
    r_63c2aa.wp_autogen_flag as te_abbc6b,
    r_63c2aa.wp_rec_start_date as te_38c81a 
FROM
    db1.household_demographics AS r_8bcefb,
    db1.household_demographics AS r_b67b0c 
LEFT JOIN
    db1.web_page AS r_63c2aa 
        ON r_b67b0c.hd_income_band_sk != r_63c2aa.wp_char_count 
WHERE
    r_8bcefb.hd_buy_potential >= r_b67b0c.hd_buy_potential 
    AND r_63c2aa.wp_access_date_sk BETWEEN 62 AND r_63c2aa.wp_creation_date_sk 
    OR r_b67b0c.hd_dep_count > 14 
    OR r_8bcefb.hd_vehicle_count >= 18 
    OR r_63c2aa.wp_image_count BETWEEN r_b67b0c.hd_vehicle_count AND 64 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    r_ed2ed5.wp_url as te_2445db 
FROM
    db1.web_page AS r_ed2ed5 
WHERE
    r_ed2ed5.wp_image_count > 71 
ORDER BY
    1 DESC NULLS FIRST;
----------->
SELECT
    r_96d38f.cc_gmt_offset as te_8cd994,
    r_81b0c8.ca_gmt_offset as te_13b10a 
FROM
    db1.call_center AS r_96d38f,
    db1.income_band AS r_405174 
RIGHT JOIN
    db1.customer_address AS r_81b0c8 
        ON r_405174.ib_income_band_sk = r_81b0c8.ca_address_sk 
WHERE
    r_96d38f.cc_mkt_id >= r_405174.ib_income_band_sk 
    OR r_81b0c8.ca_country LIKE 'CMa' 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
SELECT
    chr(51 + r_7afaee.c_first_sales_date_sk) as te_287026,
    r_7afaee.c_login as te_8a89b9 
FROM
    db1.warehouse AS r_288783 
RIGHT JOIN
    db1.customer_demographics AS r_c46229 
        ON r_288783.w_warehouse_sk > r_c46229.cd_dep_college_count,
    db1.customer AS r_7afaee 
WHERE
    r_288783.w_city ILIKE r_7afaee.c_birth_country 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
SELECT
    r_4e27af.web_manager as te_13c582,
    hash(r_4e27af.web_close_date_sk / unix_timestamp(),
    current_timestamp()) as te_565bd9,
    r_4e27af.web_tax_percentage as te_00b569 
FROM
    db1.web_site AS r_4e27af,
    (SELECT
        r_378a7e.wp_rec_start_date as te_c2d700 
    FROM
        db1.web_page AS r_378a7e 
    RIGHT JOIN
        db1.income_band AS r_17f311 
            ON r_378a7e.wp_creation_date_sk <= r_17f311.ib_lower_bound 
    WHERE
        r_378a7e.wp_access_date_sk < 50 
        AND r_378a7e.wp_type LIKE 'hv95G6s' 
    ORDER BY
        1 DESC NULLS LAST) AS r_273d03 
WHERE
    r_4e27af.web_rec_start_date <= r_273d03.te_c2d700 
    AND (
        NOT r_4e27af.web_tax_percentage = 25.31518243 
        OR r_4e27af.web_state LIKE 'LQF' 
        OR r_4e27af.web_state NOT ILIKE r_4e27af.web_site_id
    ) 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC, 3 ASC;
----------->
SELECT
    hash(r_754a45.t_am_pm,
    DATE'2024-03-26') as te_a2e529,
    r_754a45.t_minute as te_27a344,
    e() as te_640bfe 
FROM
    (SELECT
        reflect('java.util.UUID',
        'randomUUID') as te_368ae9,
        r_e0b93d.wp_web_page_id as te_500134,
        r_f39e31.web_street_number || r_e0b93d.wp_web_page_id as te_501292 
    FROM
        db1.web_site AS r_4a0358,
        db1.web_page AS r_e0b93d 
    INNER JOIN
        db1.web_site AS r_f39e31 
            ON r_e0b93d.wp_access_date_sk >= r_f39e31.web_open_date_sk 
    WHERE
        r_4a0358.web_mkt_id < r_e0b93d.wp_creation_date_sk 
        OR r_f39e31.web_company_name LIKE 'ulstntb2' 
        AND r_4a0358.web_gmt_offset = r_4a0358.web_tax_percentage 
    ORDER BY
        1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 NULLS FIRST) AS r_ab5bd9, db1.time_dim AS r_754a45 
WHERE
    r_ab5bd9.te_368ae9 NOT ILIKE r_754a45.t_sub_shift 
    AND r_754a45.t_sub_shift NOT LIKE '4QHZgGIH' 
    OR r_ab5bd9.te_501292 ILIKE r_ab5bd9.te_368ae9 
    AND r_ab5bd9.te_500134 NOT IN (
        SELECT
            chr(r_8e61f3.wp_creation_date_sk) as te_ff0218 
        FROM
            db1.web_page AS r_8e61f3 
        RIGHT JOIN
            (
                SELECT
                    mod(r_15eea6.c_birth_year,
                    65) as te_a0c261,
                    unix_timestamp() as te_218531,
                    r_15eea6.c_customer_id as te_2d50de 
                FROM
                    db1.warehouse AS r_514b4f 
                INNER JOIN
                    db1.customer_demographics AS r_37b6f3 
                        ON r_514b4f.w_warehouse_sk > r_37b6f3.cd_dep_employed_count,
                    db1.customer AS r_15eea6 
                WHERE
                    r_514b4f.w_city >= r_15eea6.c_birth_country 
                    AND r_514b4f.w_street_number NOT ILIKE 'zz2kGk4elB' 
                    AND (
                        NOT r_514b4f.w_county NOT ILIKE 'G6sUg' 
                        AND r_514b4f.w_warehouse_name ILIKE r_514b4f.w_street_type
                    ) 
                ORDER BY
                    1, 2 DESC NULLS LAST, 3 DESC NULLS LAST
            ) AS r_1134cd 
                ON r_8e61f3.wp_creation_date_sk > r_1134cd.te_a0c261,
            db1.ship_mode AS r_ad2f95 
        WHERE
            r_8e61f3.wp_char_count = r_ad2f95.sm_ship_mode_sk 
        ORDER BY
            1 DESC NULLS LAST) 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS FIRST, 3 ASC NULLS LAST 
        LIMIT 51;
----------->
SELECT
    r_f6d2c8.wp_rec_start_date as te_1ee86b 
FROM
    db1.warehouse AS r_afd09a 
RIGHT JOIN
    db1.web_page AS r_f6d2c8 
        ON r_afd09a.w_street_type LIKE r_f6d2c8.wp_type 
WHERE
    r_f6d2c8.wp_rec_end_date = r_f6d2c8.wp_rec_start_date 
ORDER BY
    1;
----------->
SELECT
    r_c6cd2d.r_reason_id as te_f4d23e 
FROM
    db1.income_band AS r_7f025a 
INNER JOIN
    db1.reason AS r_c6cd2d 
        ON r_7f025a.ib_lower_bound = r_c6cd2d.r_reason_sk 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_2a8fdb.r_reason_id as te_7cac1f,
    r_2a8fdb.r_reason_id as te_c1fff4 
FROM
    db1.income_band AS r_9c2cbe 
INNER JOIN
    db1.household_demographics AS r_83bd9a 
        ON r_9c2cbe.ib_lower_bound != r_83bd9a.hd_income_band_sk,
    db1.reason AS r_2a8fdb 
LEFT JOIN
    db1.reason AS r_05d73f 
        ON r_2a8fdb.r_reason_sk < r_05d73f.r_reason_sk 
WHERE
    r_83bd9a.hd_buy_potential LIKE r_2a8fdb.r_reason_desc 
    OR r_9c2cbe.ib_upper_bound = 89 
    AND r_83bd9a.hd_income_band_sk <= r_83bd9a.hd_dep_count 
    AND r_9c2cbe.ib_income_band_sk < r_83bd9a.hd_income_band_sk 
    OR r_05d73f.r_reason_desc ILIKE 'XX4QHZgG' 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST;
----------->
SELECT
    chr(r_24b2b3.sr_return_quantity / 18) as te_b1c917 
FROM
    db1.customer_address AS r_d7acc4 
INNER JOIN
    db1.store_returns AS r_24b2b3 
        ON r_d7acc4.ca_gmt_offset <= r_24b2b3.sr_returned_date_sk 
WHERE
    r_24b2b3.sr_return_quantity > 95 
    AND r_24b2b3.sr_cdemo_sk <= r_d7acc4.ca_address_sk 
ORDER BY
    1 ASC NULLS FIRST;
----------->
SELECT
    r_1d7af2.s_store_sk as te_275ef7,
    r_8e09a1.wp_autogen_flag as te_f9305e 
FROM
    db1.item AS r_834013 
LEFT JOIN
    db1.store AS r_0eaba4 
        ON r_834013.i_rec_end_date IN (
            SELECT
                DATE'2024-10-11' as te_4958f5 
        FROM
            (SELECT
                r_5d3a6e.sr_store_sk as te_c351f9 
            FROM
                db1.store_returns AS r_5d3a6e 
            INNER JOIN
                db1.income_band AS r_957bdf 
                    ON r_5d3a6e.sr_store_sk < r_957bdf.ib_income_band_sk 
            WHERE
                r_957bdf.ib_lower_bound < 12 
            ORDER BY
                1 ASC NULLS LAST) AS r_c3737f, db1.household_demographics AS r_b9c98f 
        WHERE
            r_c3737f.te_c351f9 <= r_b9c98f.hd_income_band_sk 
            AND r_b9c98f.hd_income_band_sk >= 9 
            OR r_b9c98f.hd_income_band_sk > 42 
            OR r_c3737f.te_c351f9 = r_b9c98f.hd_vehicle_count 
            AND r_b9c98f.hd_vehicle_count BETWEEN r_c3737f.te_c351f9 / 80 AND 97 
        ORDER BY
            1 ASC NULLS LAST), db1.store AS r_1d7af2 
        INNER JOIN
            db1.web_page AS r_8e09a1 
                ON r_1d7af2.s_hours ILIKE r_8e09a1.wp_web_page_id 
        WHERE
            r_0eaba4.s_street_number LIKE r_8e09a1.wp_url 
        ORDER BY
            1 NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    make_date(r_07e341.t_time,
    r_07e341.t_minute,
    r_be8d97.cc_sq_ft) as te_700bdf,
    r_6b9be8.web_tax_percentage as te_47cf2b 
FROM
    db1.income_band AS r_104095 
LEFT JOIN
    db1.web_site AS r_6b9be8 
        ON r_104095.ib_upper_bound != r_6b9be8.web_open_date_sk,
    db1.call_center AS r_be8d97 
RIGHT JOIN
    db1.time_dim AS r_07e341 
        ON r_be8d97.cc_call_center_sk <= r_07e341.t_second 
WHERE
    r_6b9be8.web_rec_end_date <= r_be8d97.cc_rec_start_date 
    OR r_07e341.t_time_sk >= 75 
    AND r_be8d97.cc_rec_start_date <= r_6b9be8.web_rec_end_date 
    AND r_be8d97.cc_rec_start_date != r_6b9be8.web_rec_end_date 
ORDER BY
    1 DESC NULLS LAST, 2 NULLS FIRST 
OFFSET 38;
----------->
SELECT
    btrim(btrim(chr(r_3b84f5.r_reason_sk))) as te_34fd0e 
FROM
    db1.reason AS r_3b84f5 
WHERE
    r_3b84f5.r_reason_id ILIKE r_3b84f5.r_reason_desc 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_679512.wp_char_count as te_425d77 
FROM
    db1.web_page AS r_679512 
WHERE
    r_679512.wp_image_count != 84 
    OR r_679512.wp_char_count = 93 
    AND r_679512.wp_web_page_id LIKE r_679512.wp_autogen_flag 
    AND r_679512.wp_rec_start_date <= DATE'2024-10-11' 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 89;
----------->
SELECT
    r_0626d8.i_product_name as te_59702b 
FROM
    db1.customer_address AS r_a76f41 
RIGHT JOIN
    db1.item AS r_0626d8 
        ON r_a76f41.ca_city NOT LIKE r_0626d8.i_class 
WHERE
    r_a76f41.ca_street_name NOT LIKE r_0626d8.i_product_name 
    OR r_a76f41.ca_county NOT ILIKE r_0626d8.i_product_name 
    OR r_0626d8.i_rec_end_date <= r_0626d8.i_rec_start_date 
    OR r_0626d8.i_rec_end_date >= DATE'2024-10-11' 
ORDER BY
    1 NULLS LAST;
----------->
SELECT
    negative(unix_timestamp()) as te_d96ed7 
FROM
    db1.store_returns AS r_ec55d3 
LEFT JOIN
    db1.reason AS r_5e14a9 
        ON r_ec55d3.sr_addr_sk != r_5e14a9.r_reason_sk 
WHERE
    r_5e14a9.r_reason_id ILIKE r_5e14a9.r_reason_desc 
    OR r_ec55d3.sr_fee != 19.17422713 
    OR r_ec55d3.web_county != 25.19257392 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_1b1838.c_first_sales_date_sk as te_3cdac4 
FROM
    db1.customer AS r_1b1838 
RIGHT JOIN
    (
        SELECT
            r_0fcd09.s_gmt_offset + 79.9060891D as te_febad4,
            r_0fcd09.s_market_id as te_049d1c 
        FROM
            db1.store AS r_0fcd09 
        INNER JOIN
            db1.customer AS r_32042a 
                ON r_0fcd09.s_tax_percentage < r_32042a.c_birth_month,
            (SELECT
                r_6a94db.s_store_id as te_fca86d,
                r_6a94db.s_tax_percentage as te_ce5620,
                hash(bigint(r_6a94db.s_market_id),
                r_6a94db.s_rec_end_date) as te_c855ac 
            FROM
                db1.customer_demographics AS r_db2736,
                db1.household_demographics AS r_a6109a 
            RIGHT JOIN
                db1.store AS r_6a94db 
                    ON r_a6109a.hd_buy_potential LIKE r_6a94db.s_zip 
            WHERE
                r_db2736.cd_gender NOT ILIKE r_6a94db.s_store_id 
                OR r_6a94db.s_tax_percentage > r_6a94db.s_gmt_offset 
                AND r_a6109a.hd_income_band_sk <= 51 
                OR NOT r_6a94db.s_rec_end_date < DATE'2024-03-26' 
            ORDER BY
                1 NULLS FIRST, 2 NULLS FIRST, 3 DESC NULLS FIRST) AS r_131de1 
        INNER JOIN
            db1.household_demographics AS r_ac002b 
                ON r_131de1.te_c855ac < r_ac002b.hd_income_band_sk 
        WHERE
            r_0fcd09.s_geography_class NOT LIKE r_ac002b.hd_buy_potential 
            OR r_0fcd09.s_state <= '7IG7ir98hv' 
            OR r_0fcd09.s_tax_percentage >= r_0fcd09.s_gmt_offset 
        ORDER BY
            1 DESC NULLS LAST, 2 DESC 
        LIMIT 56) AS r_792452 
            ON r_1b1838.c_current_hdemo_sk < r_792452.te_049d1c ORDER BY
                1 ASC NULLS LAST;
----------->
WITH CTE_d77011(te_db1ed0, te_184055, te_772cf8) AS (SELECT
    r_c68372.hd_buy_potential as te_db1ed0,
    r_61c7f5.hd_vehicle_count as te_184055,
    r_61c7f5.hd_vehicle_count as te_772cf8 
FROM
    db1.household_demographics AS r_61c7f5 
LEFT JOIN
    db1.reason AS r_d5cad3 
        ON r_61c7f5.hd_buy_potential NOT ILIKE r_d5cad3.r_reason_id,
    db1.household_demographics AS r_c68372 
RIGHT JOIN
    (SELECT
        * 
    FROM
        (SELECT
            r_532025.i_rec_end_date as te_71e83e,
            r_532025.i_item_id as te_f47bc2,
            r_153902.hd_demo_sk as te_8b83ef 
        FROM
            db1.item AS r_532025 
        LEFT JOIN
            db1.ship_mode AS r_7c9f83 
                ON r_532025.i_wholesale_cost < r_7c9f83.sm_ship_mode_sk,
            db1.household_demographics AS r_153902 
        WHERE
            r_532025.i_item_sk != r_153902.hd_vehicle_count 
            AND (NOT r_153902.hd_buy_potential NOT LIKE '98hv95G6sU' 
            AND r_532025.i_wholesale_cost BETWEEN 46.99411558 AND 46.99411558) 
            OR r_153902.hd_demo_sk != r_153902.hd_income_band_sk 
            AND r_532025.i_units LIKE 'UgDDXX4Q' 
        ORDER BY
            1 DESC NULLS LAST, 2 NULLS LAST, 3 DESC) PIVOT(count(te_8b83ef) AS pa_8f8576 FOR te_f47bc2 IN (('x') AS pf_e3dcba, ('v95') AS pf_8dbbef, ('I') AS pf_9b641e))) AS r_f349a6 
            ON r_c68372.hd_dep_count > r_f349a6.pf_9b641e 
    WHERE
        r_61c7f5.hd_demo_sk = r_f349a6.pf_8dbbef 
        OR r_d5cad3.r_reason_desc NOT ILIKE 'G7ir9' 
        AND r_c68372.hd_vehicle_count BETWEEN r_61c7f5.hd_income_band_sk - 79 AND 46 
        OR (NOT r_c68372.hd_demo_sk <= r_61c7f5.hd_income_band_sk 
        AND r_f349a6.pf_8dbbef IS NULL) 
    GROUP BY
        3, 2, 1 
    ORDER BY
        1 NULLS LAST, 2 DESC, 3 DESC NULLS FIRST 
    LIMIT 39), CTE_89c9f6(te_edb2fb, te_b1491d) AS (SELECT
    r_5c7584.web_state as te_edb2fb, hash(r_c6492e.r_reason_id, r_e42267.w_street_name) as te_b1491d FROM
        db1.web_site AS r_5c7584 
    RIGHT JOIN
        (SELECT
            r_d6df65.t_time_id as te_494d3e,
            r_d6df65.t_shift as te_864f59,
            r_d6df65.t_am_pm as te_9b86c6 
        FROM
            (SELECT
                r_bfdb29.d_dom * r_bfdb29.d_year as te_9c79cd 
            FROM
                db1.reason AS r_d84e9b 
            LEFT JOIN
                db1.date_dim AS r_bfdb29 
                    ON r_d84e9b.r_reason_sk >= r_bfdb29.d_last_dom 
            WHERE
                r_bfdb29.d_same_day_ly > 71 
            ORDER BY
                1 DESC NULLS LAST) AS r_3ed413, db1.time_dim AS r_d6df65 
        WHERE
            r_3ed413.te_9c79cd <= r_d6df65.t_time 
            AND r_d6df65.t_sub_shift LIKE r_d6df65.t_time_id) AS r_aa56e6 
                ON r_5c7584.web_company_name LIKE r_aa56e6.te_9b86c6,
            db1.warehouse AS r_e42267 
        RIGHT JOIN
            db1.reason AS r_c6492e 
                ON r_e42267.w_warehouse_sq_ft >= r_c6492e.r_reason_sk 
        WHERE
            r_5c7584.web_tax_percentage = r_e42267.w_gmt_offset 
            OR r_5c7584.web_rec_end_date != r_5c7584.web_rec_start_date 
        ORDER BY
            1 ASC NULLS FIRST, 2 DESC) SELECT
            r_994c68.web_street_type as te_547abe, r_994c68.web_rec_start_date as te_584b10 
        FROM
            CTE_d77011 AS r_fc172c 
        INNER JOIN
            db1.web_site AS r_994c68 
                ON r_fc172c.te_772cf8 != r_994c68.web_open_date_sk,
            (SELECT
                r_fddff7.cd_dep_college_count as te_e91ed4 
            FROM
                (SELECT
                    r_d7b3bf.wp_rec_end_date as te_e528f3 
                FROM
                    db1.web_page AS r_d7b3bf 
                WHERE
                    r_d7b3bf.wp_type = r_d7b3bf.wp_url 
                    AND r_d7b3bf.wp_rec_start_date <= DATE'2024-03-26' 
                    OR r_d7b3bf.wp_url ILIKE r_d7b3bf.wp_type) AS r_7d58c5 
            LEFT JOIN
                db1.customer_demographics AS r_fddff7 
                    ON chr(length(string(r_7d58c5.te_e528f3))) LIKE btrim(r_fddff7.cd_education_status) 
            WHERE
                r_fddff7.cd_dep_college_count <= 98 
            ORDER BY
                1 ASC NULLS FIRST) AS r_fd6e0c 
            WHERE
                r_994c68.web_company_id > r_fd6e0c.te_e91ed4 
            ORDER BY
                1 DESC NULLS LAST, 2;
----------->
SELECT
    to_char(r_fd7daa.sr_return_ship_cost,
    '000D00') as te_6aebcc,
    r_7e23ca.d_date as te_2add3c,
    timestamp_millis(r_efccb1.wp_creation_date_sk) as te_fa9064 
FROM
    db1.store AS r_820690 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.store_returns PIVOT(avg(sr_return_amt_inc_tax) AS pa_7892da FOR sr_reason_sk IN ((94) AS pf_b0e0c6,
            (60) AS pf_5ae75c,
            (54) AS pf_75c0e3,
            (87) AS pf_f0cf6a,
            (77) AS pf_e719ab))
    ) AS r_fd7daa 
        ON r_820690.s_tax_percentage >= r_fd7daa.pf_75c0e3,
    db1.web_page AS r_efccb1 
LEFT JOIN
    db1.date_dim AS r_7e23ca 
        ON r_efccb1.wp_autogen_flag NOT LIKE r_7e23ca.d_day_name 
WHERE
    r_fd7daa.pf_75c0e3 != r_efccb1.wp_char_count 
    AND r_820690.s_store_sk != 75 
    OR r_7e23ca.d_current_quarter NOT ILIKE r_820690.s_store_id 
    AND r_fd7daa.sr_refunded_cash = 18.99853712 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 NULLS FIRST;
----------->
SELECT
    r_257058.up_666ef9 as te_1bb63f,
    char_length(r_257058.w_state) as te_9fca42 
FROM
    (SELECT
        * 
    FROM
        db1.warehouse UNPIVOT INCLUDE NULLS ((up_cebbc0,
        up_666ef9,
        up_d48701,
        up_b7ab8d) FOR upn_844889 IN ((w_warehouse_sq_ft,
        w_county,
        w_street_type,
        w_gmt_offset) AS UPF_2f70e1))) AS r_257058,
    db1.income_band AS r_866615 
RIGHT JOIN
    db1.household_demographics AS r_7a056c 
        ON r_866615.ib_income_band_sk = r_7a056c.hd_income_band_sk 
WHERE
    r_257058.w_city ILIKE r_7a056c.hd_buy_potential 
ORDER BY
    1 NULLS LAST, 2 NULLS LAST;
----------->
SELECT
    r_08f91f.sm_ship_mode_id as te_e56b6b,
    r_fea98c.ca_gmt_offset as te_04a666 
FROM
    db1.ship_mode AS r_08f91f,
    db1.customer_address AS r_fea98c 
RIGHT JOIN
    db1.store_returns AS r_811f20 
        ON r_fea98c.ca_gmt_offset != r_811f20.sr_return_amt 
WHERE
    r_08f91f.sm_ship_mode_id LIKE r_fea98c.ca_country 
    OR r_fea98c.ca_address_id ILIKE r_fea98c.ca_state 
    AND r_811f20.sr_returned_date_sk BETWEEN 0 AND 0 
ORDER BY
    1 NULLS LAST, 2 DESC;
----------->
SELECT
    overlay(r_c16197.sm_type PLACING r_c16197.pf_ad53f3 
FROM
    r_c16197.pf_ad53f3) as te_f1729b 
FROM
    (SELECT
        * 
    FROM
        db1.ship_mode PIVOT(max(sm_ship_mode_sk) AS pa_c8eb16 FOR sm_code IN (('tntb2') AS pf_ad53f3,
        ('GC0l') AS pf_ace527))) AS r_c16197 
WHERE
    r_c16197.pf_ad53f3 != 56 
    AND r_c16197.sm_contract ILIKE 'VbBRGC0le' 
ORDER BY
    1 DESC NULLS LAST 
OFFSET 10;
----------->
SELECT
    r_89020b.sm_ship_mode_id as te_e6dac9,
    r_e159ea.wp_autogen_flag as te_a56bb0,
    negative(pi() * 58 * r_e159ea.wp_access_date_sk) as te_674442 
FROM
    db1.web_page AS r_e159ea 
LEFT JOIN
    db1.customer AS r_9b2ae3 
        ON r_e159ea.wp_web_page_sk > r_9b2ae3.c_current_hdemo_sk,
    db1.ship_mode AS r_89020b 
WHERE
    r_e159ea.wp_max_ad_count >= r_89020b.sm_ship_mode_sk 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS FIRST, 3 DESC;
----------->
SELECT
    r_fbca6e.d_date as te_553d99,
    r_fbca6e.d_date as te_8f9051,
    r_fbca6e.d_current_month as te_acdc91 
FROM
    db1.time_dim AS r_978643,
    db1.reason AS r_758bc7 
RIGHT JOIN
    db1.date_dim AS r_fbca6e 
        ON r_758bc7.r_reason_id NOT ILIKE r_fbca6e.d_current_month 
WHERE
    r_978643.t_hour < r_fbca6e.d_last_dom 
    AND r_fbca6e.d_month_seq != 79 
    OR r_fbca6e.d_date >= DATE'2024-10-11' 
    OR r_fbca6e.d_current_day NOT LIKE 'v9' 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS FIRST, 3 DESC NULLS FIRST;
----------->
SELECT
    r_f3e8f4.wp_type as te_6fa30c 
FROM
    (SELECT
        * 
    FROM
        db1.web_page PIVOT(max(wp_rec_start_date) AS pa_9dfb5d FOR wp_url IN (('FrTUOVbBR') AS pf_6a6ec1,
        ('G6') AS pf_498754,
        ('lBy7IG7ir') AS pf_432563,
        ('7ir98') AS pf_06d35c,
        ('bBRGC0') AS pf_f6541c,
        ('kGk4elB') AS pf_63e3a4))) AS r_f3e8f4 
WHERE
    r_f3e8f4.wp_autogen_flag NOT ILIKE 'pR' 
    AND r_f3e8f4.wp_autogen_flag NOT ILIKE 'U' 
    AND r_f3e8f4.wp_link_count >= 41 
    AND r_f3e8f4.wp_char_count > 53 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_2ee9fb.ca_address_id as te_e805d6,
    char_length(r_2ee9fb.ca_street_type) as te_14596a,
    r_2ee9fb.ca_suite_number as te_c38b00 
FROM
    db1.customer_address AS r_2ee9fb,
    db1.warehouse AS r_f79386 
WHERE
    r_2ee9fb.ca_street_type ILIKE r_f79386.w_country 
    OR r_2ee9fb.ca_street_name NOT LIKE 'GIHlpx' 
ORDER BY
    1 NULLS LAST, 2 DESC NULLS LAST, 3 ASC 
OFFSET 69;
----------->
SELECT
    reverse(r_49068e.c_customer_id) as te_814fe4,
    pi() as te_3fca83 
FROM
    db1.customer AS r_49068e 
RIGHT JOIN
    db1.warehouse AS r_5b9d2d 
        ON r_49068e.c_first_name NOT ILIKE r_5b9d2d.w_state,
    db1.item AS r_1fc280 
WHERE
    r_49068e.c_last_name NOT LIKE r_1fc280.i_container 
    OR r_1fc280.i_rec_end_date BETWEEN DATE'2024-03-26' AND DATE'2024-03-26' 
    AND r_1fc280.i_rec_end_date = DATE'2024-10-11' 
    AND r_49068e.c_current_hdemo_sk = 16 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST;
----------->
SELECT
    r_f82480.cc_mkt_id as te_5d0494 
FROM
    db1.web_site AS r_c371e2 
LEFT JOIN
    db1.call_center AS r_f82480 
        ON r_c371e2.web_tax_percentage <= r_f82480.cc_tax_percentage 
ORDER BY
    1;
----------->
SELECT
    r_dea25d.te_02d001 as te_6819e3,
    r_e4ccf8.i_item_id as te_abd3ea,
    r_e4ccf8.i_class_id as te_567531 
FROM
    db1.item AS r_e4ccf8 
INNER JOIN
    (
        SELECT
            r_3292f9.ca_gmt_offset as te_02d001,
            r_43b23b.wp_access_date_sk as te_ca05b6 
        FROM
            db1.web_site AS r_dad8b1,
            db1.customer_address AS r_3292f9 
        INNER JOIN
            db1.web_page AS r_43b23b 
                ON r_3292f9.ca_location_type >= r_43b23b.wp_web_page_id 
        WHERE
            r_dad8b1.web_site_id NOT ILIKE r_3292f9.ca_street_number 
            OR r_43b23b.wp_access_date_sk != r_dad8b1.web_company_id 
            AND r_3292f9.ca_country LIKE 'sUgDDXX' 
            OR r_dad8b1.web_zip ILIKE '98hv95G6s' 
        ORDER BY
            1 NULLS FIRST, 2 DESC NULLS FIRST 
        LIMIT 36
) AS r_dea25d 
    ON r_e4ccf8.i_brand_id < r_dea25d.te_02d001,
(SELECT
    chr(r_87b7b4.t_hour) as te_35ccb2 FROM
        db1.time_dim AS r_87b7b4 
    WHERE
        r_87b7b4.t_minute > 31 
        OR r_87b7b4.t_shift NOT ILIKE r_87b7b4.t_meal_time 
        AND r_87b7b4.t_shift NOT LIKE r_87b7b4.t_am_pm 
        AND r_87b7b4.t_time_id = r_87b7b4.t_shift 
    ORDER BY
        1) AS r_34ad1c 
RIGHT JOIN
    db1.customer_demographics AS r_b32dac 
        ON r_34ad1c.te_35ccb2 ILIKE r_b32dac.cd_education_status 
WHERE
    r_e4ccf8.i_manufact_id > r_b32dac.cd_dep_college_count 
    AND r_e4ccf8.i_item_desc ILIKE '95G6' 
    AND r_b32dac.cd_purchase_estimate <= r_e4ccf8.i_item_sk 
ORDER BY
    1 DESC NULLS LAST, 2 DESC, 3 ASC NULLS LAST 
OFFSET 48;
----------->
(
    SELECT
        r_030d52.te_bebd76 as te_fe8e52,
        r_030d52.te_ff7a3f as te_ba5287,
        r_2b066e.sr_store_credit as te_7e1e69 
    FROM
        (SELECT
            r_1bf404.ca_gmt_offset as te_b01bb9,
            r_1bf404.ca_location_type as te_ff7a3f,
            now() as te_bebd76 
        FROM
            db1.reason AS r_c51020 
        LEFT JOIN
            (
                SELECT
                    negative(r_b189f9.i_current_price) as te_a00b09 
                FROM
                    db1.item AS r_b189f9 
                INNER JOIN
                    db1.web_page AS r_a645c3 
                        ON r_b189f9.i_rec_end_date < r_a645c3.wp_rec_start_date 
                WHERE
                    r_b189f9.i_brand_id = 55 
                    AND r_b189f9.i_item_id ILIKE '7RXpR' 
                    OR r_b189f9.i_current_price != r_b189f9.i_wholesale_cost 
                GROUP BY
                    1 
                ORDER BY
                    1 DESC NULLS LAST 
                LIMIT 22
        ) AS r_88028d 
            ON to_char(r_c51020.r_reason_sk,
        '999') <= r_88028d.te_a00b09, db1.customer_address AS r_1bf404 WHERE
            r_88028d.te_a00b09 = r_1bf404.ca_address_sk 
            AND r_88028d.te_a00b09 NOT IN (
                WITH CTE_767bdd(te_400c2d, te_3f6cbf) AS (SELECT
                    chr(r_25263b.cc_closed_date_sk) as te_400c2d,
                    abs(67 * r_25263b.cc_sq_ft + unix_timestamp()) as te_3f6cbf 
                FROM
                    db1.call_center AS r_25263b 
                LEFT JOIN
                    db1.income_band AS r_433616 
                        ON r_25263b.cc_call_center_sk != r_433616.ib_income_band_sk,
                    db1.warehouse AS r_9abeca 
                WHERE
                    r_25263b.cc_call_center_id NOT ILIKE r_9abeca.w_county 
                    AND r_9abeca.w_gmt_offset = r_25263b.cc_tax_percentage 
                ORDER BY
                    1 DESC, 2 DESC NULLS LAST 
                OFFSET 87), CTE_e6050c(te_ed8d9e, te_8b5675) AS (SELECT
                r_6d6972.w_zip as te_ed8d9e, r_6d6972.w_state as te_8b5675 FROM
                    (SELECT
                        chr(r_35c097.sr_store_sk) as te_142265 
                    FROM
                        db1.item AS r_b33ff0 
                    RIGHT JOIN
                        db1.store_returns AS r_35c097 
                            ON r_b33ff0.i_manager_id = r_35c097.sr_cdemo_sk 
                    WHERE
                        r_35c097.sr_return_amt <= 75.19551985 
                        OR r_b33ff0.i_size NOT ILIKE r_b33ff0.i_brand 
                    ORDER BY
                        1 NULLS FIRST 
                    LIMIT 6) AS r_92c33b, db1.warehouse AS r_6d6972 WHERE
                    r_92c33b.te_142265 LIKE r_6d6972.w_county 
                    OR r_6d6972.w_street_type ILIKE 'R' 
                ORDER BY
                    1 DESC NULLS LAST, 2 DESC NULLS LAST) SELECT
                        r_96a184.s_gmt_offset as te_fc67ab 
                    FROM
                        CTE_767bdd AS r_5d2150,
                        CTE_e6050c AS r_08fef3 
                    LEFT JOIN
                        db1.store AS r_96a184 
                            ON r_08fef3.te_ed8d9e LIKE r_96a184.s_store_name 
                    WHERE
                        r_5d2150.te_400c2d ILIKE r_96a184.s_store_id 
                        AND r_96a184.s_gmt_offset > r_96a184.s_tax_percentage 
                    ORDER BY
                        1 ASC) 
                    OR r_88028d.te_a00b09 != r_1bf404.ca_gmt_offset 
                ORDER BY
                    1 NULLS FIRST, 2 DESC, 3 DESC
            ) AS r_030d52, db1.customer AS r_5185ec 
        RIGHT JOIN
            db1.store_returns AS r_2b066e 
                ON r_5185ec.c_birth_month >= r_2b066e.sr_return_ship_cost 
        WHERE
            r_030d52.te_ff7a3f LIKE r_5185ec.c_salutation 
            AND r_030d52.te_bebd76 < TIMESTAMP'2024-03-25 20:03:19.328' 
            OR r_2b066e.sr_fee = 54.8919208 
        ORDER BY
            1 NULLS LAST, 2 DESC, 3) 
        UNION
        (
            SELECT
                now() as te_15d411,
                r_076a57.d_date_id as te_a9b023,
                to_char(r_18a9b8.c_current_cdemo_sk,
                '999') as te_039e16 
            FROM
                db1.date_dim AS r_076a57 
            INNER JOIN
                db1.web_page AS r_9f6010 
                    ON r_076a57.d_first_dom >= r_9f6010.wp_char_count,
                (SELECT
                    * 
                FROM
                    db1.customer PIVOT(max(c_current_addr_sk) AS pa_118566 FOR c_last_review_date_sk IN ((37) AS pf_335e40,
                    (85) AS pf_718757,
                    (44) AS pf_f4be99,
                    (90) AS pf_362463,
                    (97) AS pf_44bf03,
                    (19) AS pf_81acf9))) AS r_18a9b8 
            WHERE
                r_9f6010.wp_type LIKE r_18a9b8.c_last_name 
                OR r_9f6010.wp_web_page_id NOT ILIKE r_076a57.d_quarter_name 
                AND r_9f6010.wp_url > r_18a9b8.c_birth_country 
            ORDER BY
                1 ASC NULLS LAST, 2 NULLS LAST, 3 ASC NULLS FIRST) 
            ORDER BY
                1 DESC NULLS LAST, 2 ASC NULLS FIRST, 3;
----------->
SELECT
    r_8de294.s_store_sk as te_cbfee2,
    abs(r_410634.ca_gmt_offset) as te_1633e8,
    r_8de294.s_market_desc as te_c18dd2 
FROM
    db1.customer_address AS r_410634,
    db1.store AS r_8de294 
RIGHT JOIN
    db1.store AS r_f1cdab 
        ON r_8de294.s_rec_start_date != r_f1cdab.s_rec_start_date 
WHERE
    r_410634.ca_street_name LIKE r_f1cdab.s_state 
ORDER BY
    1 NULLS LAST, 2 DESC, 3 DESC NULLS LAST;
----------->
SELECT
    r_91aa8b.s_gmt_offset as te_18fc63,
    r_91aa8b.s_street_type as te_a87668,
    r_91aa8b.s_street_number as te_e8ebd6 
FROM
    db1.income_band AS r_e20244,
    db1.store AS r_23ece1 
RIGHT JOIN
    db1.store AS r_91aa8b 
        ON r_23ece1.s_street_name ILIKE r_91aa8b.s_company_name 
WHERE
    r_e20244.ib_upper_bound != r_23ece1.s_floor_space 
    OR r_23ece1.s_company_name ILIKE '4elBy7IG7i' 
ORDER BY
    1, 2 DESC NULLS LAST, 3 ASC 
LIMIT 28;
----------->
SELECT
    77 / r_be5aac.cc_call_center_sk as te_8fdcae,
    hash(false,
    now()) as te_0a7749,
    r_be5aac.cc_tax_percentage as te_e2136d 
FROM
    db1.call_center AS r_be5aac 
LEFT JOIN
    db1.store AS r_ea6655 
        ON r_be5aac.cc_employees = r_ea6655.s_company_id,
    db1.customer_demographics AS r_0275cc 
RIGHT JOIN
    db1.store_returns AS r_987b81 
        ON r_0275cc.cd_demo_sk >= r_987b81.sr_net_loss 
WHERE
    r_ea6655.s_tax_percentage > r_987b81.sr_store_credit 
    AND r_ea6655.s_city LIKE '5G6sUgDDX' 
    AND r_0275cc.cd_marital_status LIKE r_0275cc.cd_credit_rating 
ORDER BY
    1 DESC, 2 DESC NULLS LAST, 3 NULLS FIRST;
----------->
SELECT
    r_d09199.sr_reason_sk as te_2180ed 
FROM
    db1.store_returns AS r_d09199 
WHERE
    r_d09199.sr_fee BETWEEN 4.67173313 AND r_d09199.sr_ticket_number 
    OR r_d09199.sr_ticket_number != 46 
    AND r_d09199.sr_customer_sk != r_d09199.sr_hdemo_sk 
ORDER BY
    1 NULLS FIRST;
----------->
SELECT
    r_3ff6f6.cc_rec_start_date as te_710b91,
    hash(r_3ff6f6.cc_company,
    true) as te_670c4a 
FROM
    db1.household_demographics AS r_67ebae 
LEFT JOIN
    db1.web_page AS r_cf960b 
        ON r_67ebae.hd_buy_potential ILIKE r_cf960b.wp_url,
    db1.income_band AS r_776be0 
RIGHT JOIN
    db1.call_center AS r_3ff6f6 
        ON r_776be0.ib_income_band_sk = r_3ff6f6.cc_company 
WHERE
    r_67ebae.hd_buy_potential ILIKE r_3ff6f6.cc_country 
    OR r_3ff6f6.cc_county NOT ILIKE '0' 
    AND r_3ff6f6.cc_market_manager NOT LIKE 'lpx6k' 
ORDER BY
    1 ASC, 2 DESC;
----------->
SELECT
    decimal(r_616e6c.c_current_addr_sk) as te_37b09c 
FROM
    db1.customer AS r_616e6c 
LEFT JOIN
    db1.web_site AS r_7f32b0 
        ON r_616e6c.c_preferred_cust_flag NOT LIKE r_7f32b0.web_state 
WHERE
    r_616e6c.c_birth_day >= 80 
    AND r_616e6c.c_first_shipto_date_sk < 7 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_c4adc3.sr_return_tax as te_cf6d76 
FROM
    db1.item AS r_b81c54 
LEFT JOIN
    db1.store_returns AS r_c4adc3 
        ON r_b81c54.i_manufact_id != r_c4adc3.sr_returned_date_sk 
WHERE
    r_b81c54.i_category_id < 42 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    r_c4a464.s_market_id as te_cba003,
    r_c4a464.s_market_desc as te_d68c12 
FROM
    db1.warehouse AS r_5a7e19,
    (SELECT
        * 
    FROM
        db1.store PIVOT(count(s_county) AS pa_d56dba FOR s_hours IN (('ir98hv95G6') AS pf_58b0ee,
        ('z2kGk') AS pf_d62158))) AS r_c4a464 
INNER JOIN
    (
        SELECT
            r_4f4377.d_date_sk as te_534e37 
        FROM
            db1.customer_demographics AS r_688d50 
        LEFT JOIN
            db1.date_dim AS r_4f4377 
                ON r_688d50.cd_gender LIKE r_4f4377.d_weekend 
        ORDER BY
            1 DESC NULLS LAST
    ) AS r_ebdec6 
        ON r_c4a464.s_market_id >= r_ebdec6.te_534e37 
WHERE
    r_5a7e19.w_country LIKE r_c4a464.s_zip 
    OR r_5a7e19.w_suite_number ILIKE r_5a7e19.w_street_name 
    OR r_5a7e19.w_gmt_offset = 32.74011218 
    AND r_c4a464.s_suite_number NOT LIKE '7IG7ir9' 
ORDER BY
    1 DESC NULLS LAST, 2 ASC 
OFFSET 33;
----------->
SELECT
    r_1d0355.sr_return_ship_cost as te_fc1f7f,
    r_1d0355.sr_cdemo_sk as te_bd5dd3,
    r_1d0355.sr_store_sk as te_d600e7 
FROM
    db1.customer_demographics AS r_8756bb,
    (SELECT
        r_2ea4c5.te_75c63d as te_27cda3,
        r_260d12.hd_buy_potential as te_f246d4 
    FROM
        (SELECT
            * 
        FROM
            db1.household_demographics PIVOT(avg(hd_income_band_sk) AS pa_b5596d FOR hd_dep_count IN ((25) AS pf_633fbd,
            (77) AS pf_928309,
            (17) AS pf_6b6629,
            (45) AS pf_d3c37a,
            (28) AS pf_a49c35))) AS r_260d12,
        (SELECT
            r_86a41d.cc_mkt_class as te_75c63d,
            r_86a41d.cc_gmt_offset as te_9234cc 
        FROM
            db1.web_page AS r_7be993,
            db1.call_center AS r_86a41d 
        WHERE
            r_7be993.wp_url NOT ILIKE r_86a41d.cc_hours 
            OR r_7be993.wp_max_ad_count BETWEEN 20 AND r_86a41d.cc_gmt_offset + 48 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS FIRST 
        LIMIT 29) AS r_2ea4c5 WHERE
        r_260d12.hd_buy_potential NOT ILIKE r_2ea4c5.te_75c63d 
        OR r_2ea4c5.te_75c63d != r_260d12.hd_buy_potential 
        OR EXISTS (
            SELECT
                r_be3a22.ib_lower_bound as te_d6f75f 
            FROM
                db1.income_band AS r_be3a22 
            ORDER BY
                1 ASC
        ) 
        AND r_260d12.hd_vehicle_count <= r_260d12.hd_demo_sk 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS FIRST) AS r_facdbe 
    LEFT JOIN
        db1.store_returns AS r_1d0355 
            ON r_facdbe.te_f246d4 NOT LIKE chr(r_1d0355.sr_return_tax) 
    WHERE
        r_8756bb.cd_demo_sk = r_1d0355.sr_customer_sk 
        OR r_1d0355.sr_fee < 39.59174695 
    ORDER BY
        1 DESC, 2 DESC NULLS LAST, 3 DESC;
----------->
SELECT
    r_61e1a2.ib_upper_bound as te_fbac24,
    r_62006f.w_county as te_a831a9 
FROM
    db1.income_band AS r_61e1a2,
    db1.warehouse AS r_62006f 
WHERE
    r_61e1a2.ib_income_band_sk >= r_62006f.w_warehouse_sq_ft 
    AND r_62006f.w_suite_number LIKE 'DCMaLQ' 
    AND r_62006f.w_country ILIKE '8hv95G6sU' 
    AND (
        NOT r_61e1a2.ib_lower_bound < 90 
        OR r_61e1a2.ib_lower_bound > r_62006f.w_warehouse_sk
    ) 
ORDER BY
    1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
(
    SELECT
        r_68dbe1.cc_rec_start_date as te_1582b7,
        59 + r_68dbe1.cc_company as te_db599a 
    FROM
        (SELECT
            r_1f0e8a.i_item_sk as te_e30703 
        FROM
            db1.item AS r_1f0e8a 
        WHERE
            r_1f0e8a.i_manager_id < 88 
            OR r_1f0e8a.i_current_price > r_1f0e8a.i_wholesale_cost 
        ORDER BY
            1) AS r_b36efd 
    LEFT JOIN
        db1.warehouse AS r_64ca17 
            ON r_b36efd.te_e30703 >= r_64ca17.w_warehouse_sk,
        db1.warehouse AS r_e4d115 
    INNER JOIN
        db1.call_center AS r_68dbe1 
            ON r_e4d115.w_gmt_offset BETWEEN 78.70802254 AND r_68dbe1.cc_mkt_id 
    WHERE
        r_64ca17.w_street_type LIKE r_68dbe1.cc_manager 
    ORDER BY
        1 DESC, 2 DESC NULLS FIRST) MINUS  (SELECT
            r_82d2d7.cc_rec_start_date as te_45d02a, unix_timestamp() as te_cd7bef 
        FROM
            db1.call_center AS r_82d2d7,
            db1.ship_mode AS r_18f302 
        LEFT JOIN
            (SELECT
                * 
            FROM
                (SELECT
                    r_3ff28f.ca_country as te_21ea4f,
                    r_a5694a.r_reason_desc as te_3fab07,
                    r_a5694a.r_reason_id as te_d8fc8c 
                FROM
                    db1.customer_address AS r_3ff28f,
                    db1.reason AS r_a5694a 
                WHERE
                    r_3ff28f.ca_street_type ILIKE r_a5694a.r_reason_desc 
                    OR r_a5694a.r_reason_desc LIKE 'RGC0lezmS' 
                    OR r_a5694a.r_reason_id LIKE r_3ff28f.ca_street_name 
                    OR r_3ff28f.ca_address_id LIKE '2' 
                    OR r_3ff28f.ca_address_sk > r_a5694a.r_reason_sk 
                ORDER BY
                    1 ASC NULLS FIRST, 2 ASC NULLS LAST, 3 ASC NULLS LAST) PIVOT(max(te_21ea4f) AS pa_4935c8 FOR te_d8fc8c IN (('QHZgGIH') AS pf_840966, ('QFrTU') AS pf_3fc027, ('sUg') AS pf_536d32, ('IH') AS pf_49c0b6, ('98hv95G6') AS pf_013d87))) AS r_45bd35 
                    ON r_18f302.sm_ship_mode_id ILIKE r_45bd35.pf_536d32 
            WHERE
                r_82d2d7.cc_company = r_18f302.sm_ship_mode_sk 
            ORDER BY
                1 DESC NULLS FIRST, 2 DESC NULLS LAST) 
        ORDER BY
            1 DESC, 2 DESC NULLS FIRST;
----------->
SELECT
    r_5ba5c1.i_current_price as te_5c3c47 
FROM
    db1.time_dim AS r_4e36d5 
RIGHT JOIN
    db1.item AS r_5ba5c1 
        ON r_4e36d5.t_time_sk = r_5ba5c1.i_item_sk 
WHERE
    r_5ba5c1.i_units LIKE 'UOVbBR' 
    OR r_5ba5c1.i_wholesale_cost < r_5ba5c1.i_current_price 
ORDER BY
    1 DESC;
----------->
SELECT
    r_1ca907.w_warehouse_sk as te_db8f7d,
    date_add(DATE'2024-10-11',
    r_1ca907.w_warehouse_sq_ft) as te_b4d994 
FROM
    db1.warehouse AS r_1ca907 
RIGHT JOIN
    (
        SELECT
            r_4b9ec5.te_12f6a5 as te_851fad 
        FROM
            (SELECT
                r_571619.i_formulation as te_9ff87d,
                r_42b49e.c_salutation as te_12f6a5 
            FROM
                db1.item AS r_571619,
                db1.customer AS r_42b49e 
            LEFT JOIN
                db1.reason AS r_9a1630 
                    ON r_42b49e.c_birth_month >= r_9a1630.r_reason_sk 
            WHERE
                r_571619.i_class_id < r_42b49e.c_current_addr_sk 
                AND r_571619.i_item_sk < 47 
            ORDER BY
                1, 2 DESC NULLS FIRST) AS r_4b9ec5 
        WHERE
            r_4b9ec5.te_12f6a5 NOT ILIKE r_4b9ec5.te_9ff87d 
            AND r_4b9ec5.te_9ff87d NOT ILIKE 'ZgGIHlpx' 
            AND r_4b9ec5.te_9ff87d NOT LIKE r_4b9ec5.te_12f6a5 
        ORDER BY
            1 DESC NULLS LAST) AS r_085ef6 
                ON r_1ca907.w_street_name NOT LIKE r_085ef6.te_851fad, (SELECT
                    r_964bad.w_gmt_offset as te_96d6c2 
            FROM
                db1.warehouse AS r_964bad 
            ORDER BY
                1 ASC NULLS LAST) AS r_e1afd2 
        WHERE
            r_1ca907.w_warehouse_sk != r_e1afd2.te_96d6c2 
            AND r_1ca907.w_warehouse_sq_ft <= r_1ca907.w_warehouse_sk 
            OR r_1ca907.w_warehouse_sk > r_1ca907.w_warehouse_sq_ft 
            OR r_1ca907.w_warehouse_sk <= 42 
        ORDER BY
            1 DESC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_b88ebd.sr_refunded_cash as te_f1d6c4 
FROM
    db1.store_returns AS r_b88ebd 
WHERE
    r_b88ebd.sr_net_loss > r_b88ebd.sr_fee 
    AND r_b88ebd.web_county >= 19.85291402 
    AND r_b88ebd.sr_store_credit <= r_b88ebd.sr_return_amt 
ORDER BY
    1 DESC NULLS LAST;
----------->
SELECT
    hash(unix_timestamp(),
    date_add(DATE'2024-03-25',
    r_aba047.t_time_sk)) as te_2dff7d 
FROM
    db1.time_dim AS r_aba047 
LEFT JOIN
    db1.reason AS r_1385aa 
        ON r_aba047.t_shift LIKE r_1385aa.r_reason_id 
WHERE
    r_aba047.t_time != 81 
    AND r_1385aa.r_reason_id LIKE r_aba047.t_sub_shift 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_4e3dfd.c_current_hdemo_sk as te_3a12e4,
    r_4e3dfd.c_first_name as te_c8ba36 
FROM
    db1.customer AS r_4e3dfd 
RIGHT JOIN
    db1.reason AS r_16c68c 
        ON r_4e3dfd.c_current_addr_sk <= r_16c68c.r_reason_sk,
    db1.warehouse AS r_99b20c 
WHERE
    r_4e3dfd.c_birth_day = r_99b20c.w_warehouse_sq_ft 
ORDER BY
    1 ASC NULLS FIRST, 2 DESC NULLS LAST;
----------->
SELECT
    r_a695b5.te_5b3175 as te_dfdcf6 
FROM
    (SELECT
        r_02fc9d.i_manufact as te_5b3175 
    FROM
        (SELECT
            * 
        FROM
            db1.time_dim PIVOT(count(t_shift) AS pa_40881e FOR t_minute IN ((63) AS pf_26954e,
            (69) AS pf_855d45,
            (92) AS pf_812c7c,
            (16) AS pf_6df49b,
            (38) AS pf_7e8ccf,
            (9) AS pf_3d78a1))) AS r_45e4e7 
    LEFT JOIN
        db1.item AS r_02fc9d 
            ON r_45e4e7.t_time_sk != r_02fc9d.i_class_id 
    ORDER BY
        1 NULLS LAST 
    LIMIT 27) AS r_a695b5 WHERE
        r_a695b5.te_5b3175 ILIKE r_a695b5.te_5b3175 
        AND NOT r_a695b5.te_5b3175 > 'IvXUuls' 
    ORDER BY
        1 DESC NULLS FIRST;
----------->
SELECT
    r_d816aa.c_current_addr_sk as te_8ab144,
    65 * r_49cf23.r_reason_sk as te_40e3d5 
FROM
    db1.reason AS r_49cf23,
    db1.ship_mode AS r_78ea67 
INNER JOIN
    db1.customer AS r_d816aa 
        ON r_78ea67.sm_type NOT LIKE r_d816aa.c_first_name 
WHERE
    r_49cf23.r_reason_sk > r_d816aa.c_customer_sk 
    OR r_78ea67.sm_carrier ILIKE r_78ea67.sm_type 
ORDER BY
    1 DESC, 2 DESC NULLS LAST;
----------->
SELECT
    r_adc415.te_499213 as te_0077a1 
FROM
    (SELECT
        make_timestamp(r_282c51.s_store_sk,
        r_282c51.s_closed_date_sk,
        r_282c51.s_division_id,
        r_282c51.s_closed_date_sk,
        r_282c51.s_floor_space,
        r_282c51.s_tax_percentage) as te_499213 
    FROM
        db1.store AS r_282c51 
    WHERE
        r_282c51.s_street_type NOT ILIKE 'Uulstntb' 
        AND r_282c51.s_rec_end_date >= r_282c51.s_rec_start_date 
    ORDER BY
        1 ASC NULLS LAST) AS r_adc415 
LEFT JOIN
    db1.item AS r_5a65d5 
        ON date(r_adc415.te_499213) > r_5a65d5.i_rec_start_date 
WHERE
    r_5a65d5.i_item_desc NOT LIKE 'DCMaLQ' 
ORDER BY
    1 DESC NULLS LAST;
----------->
WITH CTE_838dd1(te_db62bd, te_7ea9b7, te_df8d7e) AS (SELECT
    r_9fd88f.hd_buy_potential as te_db62bd,
    r_9fd88f.hd_buy_potential as te_7ea9b7,
    r_9a92e8.sr_return_tax as te_df8d7e 
FROM
    db1.store_returns AS r_9a92e8,
    db1.income_band AS r_4c4d53 
RIGHT JOIN
    db1.household_demographics AS r_9fd88f 
        ON r_4c4d53.ib_lower_bound < r_9fd88f.hd_income_band_sk 
WHERE
    NOT r_9a92e8.sr_return_time_sk = r_9fd88f.hd_dep_count 
    AND r_9a92e8.sr_customer_sk < 69 
ORDER BY
    1 DESC NULLS LAST, 2 ASC, 3 ASC NULLS LAST) SELECT
    r_6c2b1e.te_2c4aef as te_3c5e4c 
FROM
    (SELECT
        r_a48737.w_country as te_17a756,
        r_d6f1fe.sr_store_sk as te_2c4aef,
        try_add(r_a48737.w_country,
        r_d6f1fe.sr_ticket_number) as te_709f56 
    FROM
        db1.store_returns AS r_d6f1fe,
        CTE_838dd1 AS r_989ea1 
    LEFT JOIN
        db1.warehouse AS r_a48737 
            ON r_989ea1.te_db62bd ILIKE r_a48737.w_country 
    WHERE
        r_d6f1fe.sr_fee > r_989ea1.te_df8d7e 
        OR r_a48737.w_county NOT IN (
            SELECT
                r_2a92a5.wp_web_page_id as te_6f9548 
            FROM
                db1.time_dim AS r_c97cd4 
            INNER JOIN
                db1.web_page AS r_2a92a5 
                    ON r_c97cd4.t_time_id NOT LIKE r_2a92a5.wp_type 
            WHERE
                r_2a92a5.wp_web_page_sk <= 32 
            ORDER BY
                1 DESC NULLS LAST
        ) 
    ORDER BY
        1 DESC NULLS LAST, 2 DESC NULLS LAST, 3 DESC NULLS FIRST) AS r_6c2b1e 
    LEFT JOIN
        CTE_838dd1 AS r_550e53 
            ON r_6c2b1e.te_709f56 NOT ILIKE r_550e53.te_7ea9b7 
    WHERE
        r_550e53.te_db62bd NOT LIKE r_550e53.te_7ea9b7 
    ORDER BY
        1 ASC NULLS LAST;
----------->
SELECT
    14 + r_9a8768.t_second as te_caa09a,
    try_add(r_af1e34.sm_ship_mode_sk,
    DATE'2024-03-25') as te_0cba84,
    r_9a8768.t_minute as te_929e82 
FROM
    db1.time_dim AS r_9a8768,
    db1.ship_mode AS r_af1e34 
WHERE
    r_9a8768.t_time_sk >= r_af1e34.sm_ship_mode_sk 
ORDER BY
    1 DESC, 2 DESC, 3 DESC;
----------->
SELECT
    r_33ad7e.c_customer_id as te_c8e0f6,
    r_33ad7e.c_customer_id as te_5ad220,
    r_938a19.c_customer_id as te_b10092 
FROM
    db1.customer AS r_938a19,
    db1.customer AS r_33ad7e 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.time_dim PIVOT(min(t_am_pm) AS pa_9c331b FOR t_time IN ((54) AS pf_e99c05,
            (23) AS pf_299526,
            (85) AS pf_d9ce5e,
            (5) AS pf_7f3956,
            (53) AS pf_601217,
            (88) AS pf_1236b6))
    ) AS r_6ef5d3 
        ON r_33ad7e.c_salutation LIKE r_6ef5d3.t_meal_time 
WHERE
    r_938a19.c_current_cdemo_sk <= r_33ad7e.c_current_cdemo_sk 
    OR r_6ef5d3.t_time_sk != r_938a19.c_current_hdemo_sk 
    OR r_33ad7e.c_birth_year = 33 
    OR r_6ef5d3.t_sub_shift NOT ILIKE r_6ef5d3.pf_e99c05 
ORDER BY
    1 ASC NULLS LAST, 2 ASC NULLS LAST, 3 DESC NULLS LAST;
----------->
SELECT
    r_24ba33.s_geography_class as te_fcba75,
    r_c4dba6.wp_access_date_sk as te_bbf9d8,
    r_c4dba6.wp_web_page_id as te_d1e8ae 
FROM
    db1.web_page AS r_c4dba6 
INNER JOIN
    (
        WITH CTE_cdc1f4(te_c319aa, te_2876a7, te_fcbfa5) AS (SELECT
            r_14ad0f.cc_employees as te_c319aa,
            r_14ad0f.cc_call_center_id as te_2876a7,
            r_14ad0f.cc_rec_end_date as te_fcbfa5 
        FROM
            db1.item AS r_caeeb9,
            db1.call_center AS r_14ad0f 
        WHERE
            r_caeeb9.i_units > r_14ad0f.cc_market_manager 
            OR r_14ad0f.cc_closed_date_sk >= r_caeeb9.i_manager_id 
            AND r_caeeb9.i_current_price != r_14ad0f.cc_gmt_offset 
        ORDER BY
            1 ASC, 2 ASC NULLS LAST, 3 ASC NULLS FIRST) SELECT
            r_930a8a.te_2876a7 as te_80c07d 
        FROM
            CTE_cdc1f4 AS r_930a8a 
        WHERE
            r_930a8a.te_fcbfa5 = DATE'2024-03-26' 
            OR r_930a8a.te_fcbfa5 = date_from_unix_date(r_930a8a.te_c319aa) 
        ORDER BY
            1 DESC NULLS LAST
    ) AS r_d3e4e2 
        ON r_c4dba6.wp_web_page_id LIKE r_d3e4e2.te_80c07d,
    db1.store AS r_24ba33 
INNER JOIN
    db1.item AS r_d17507 
        ON r_24ba33.s_store_sk < r_d17507.i_class_id 
WHERE
    r_c4dba6.wp_image_count != r_d17507.i_wholesale_cost 
    AND r_24ba33.s_division_id != 2 
    AND r_d17507.i_wholesale_cost < 10.1711041 
    AND NOT r_d17507.i_current_price >= r_24ba33.s_gmt_offset 
ORDER BY
    1 DESC NULLS FIRST, 2, 3 DESC NULLS LAST;
----------->
SELECT
    r_2db0db.d_quarter_seq as te_7528b7,
    r_92c069.w_gmt_offset as te_28bcea,
    btrim(r_92c069.w_zip) as te_f7867d 
FROM
    db1.web_page AS r_41264e,
    db1.warehouse AS r_92c069 
RIGHT JOIN
    db1.date_dim AS r_2db0db 
        ON r_92c069.w_warehouse_sk = r_2db0db.d_same_day_ly 
WHERE
    r_41264e.wp_rec_start_date > r_2db0db.d_date 
    OR r_2db0db.d_date_id ILIKE 'X4Q' 
ORDER BY
    1 DESC NULLS FIRST, 2, 3 DESC NULLS LAST;
----------->
SELECT
    make_timestamp(r_7c3928.web_close_date_sk,
    r_dcf719.hd_income_band_sk,
    r_dcf719.hd_income_band_sk,
    r_7c3928.web_mkt_id,
    r_fda7cc.hd_income_band_sk,
    50.62318686D) as te_8cee2d,
    r_7c3928.web_county as te_5148e7 
FROM
    db1.household_demographics AS r_dcf719 
RIGHT JOIN
    db1.household_demographics AS r_fda7cc 
        ON r_dcf719.hd_buy_potential ILIKE r_fda7cc.hd_buy_potential,
    db1.web_site AS r_7c3928 
WHERE
    r_fda7cc.hd_buy_potential NOT ILIKE r_7c3928.web_company_name 
    OR r_dcf719.hd_income_band_sk != r_fda7cc.hd_income_band_sk 
    OR (
        NOT r_7c3928.web_company_id != 91 
        AND r_dcf719.hd_income_band_sk > r_fda7cc.hd_vehicle_count
    ) 
ORDER BY
    1 DESC, 2 NULLS LAST;
----------->
SELECT
    make_timestamp(r_efac8d.wp_max_ad_count,
    r_d9f09d.hd_dep_count,
    r_d9f09d.hd_income_band_sk,
    r_efac8d.wp_web_page_sk,
    r_efac8d.wp_customer_sk,
    63.49717223) as te_eeb7dc,
    hour(timestamp_millis(r_efac8d.wp_image_count)) as te_05f244 
FROM
    db1.web_page AS r_efac8d,
    db1.ship_mode AS r_096eae 
INNER JOIN
    db1.household_demographics AS r_d9f09d 
        ON r_096eae.sm_ship_mode_id NOT LIKE r_d9f09d.hd_buy_potential 
WHERE
    r_efac8d.wp_type ILIKE r_096eae.sm_type 
ORDER BY
    1 DESC NULLS LAST, 2 DESC NULLS LAST;
----------->
SELECT
    r_89d6fe.ib_upper_bound as te_5a42ab 
FROM
    db1.income_band AS r_89d6fe 
RIGHT JOIN
    db1.income_band AS r_d3185d 
        ON r_89d6fe.ib_lower_bound != r_d3185d.ib_upper_bound 
WHERE
    r_d3185d.ib_income_band_sk NOT IN (
        SELECT
            r_af4868.s_division_id as te_8b64cf 
        FROM
            db1.store AS r_af4868 
        ORDER BY
            1 DESC NULLS FIRST
    ) 
    OR r_89d6fe.ib_income_band_sk = 21 
ORDER BY
    1 ASC NULLS LAST;
----------->
SELECT
    r_d6f27e.cc_rec_start_date as te_9083d2,
    r_d44bb1.cd_marital_status as te_8b33f5,
    r_d6f27e.cc_rec_end_date as te_46ad97 
FROM
    db1.call_center AS r_d6f27e,
    db1.customer_demographics AS r_d44bb1 
RIGHT JOIN
    (
        SELECT
            * 
        FROM
            db1.store_returns UNPIVOT ((up_71a11d,
            up_2560eb) FOR upn_372df9 IN ((sr_customer_sk,
            web_county) AS UPF_4cbed7,
            (sr_item_sk,
            sr_return_amt_inc_tax) AS UPF_2f300f,
            (sr_cdemo_sk,
            sr_return_amt) AS UPF_c2e6cc,
            (sr_hdemo_sk,
            sr_reversed_charge) AS UPF_f5673e,
            (sr_returned_date_sk,
            sr_refunded_cash) AS UPF_867eab,
            (sr_store_sk,
            sr_fee) AS UPF_d90239,
            (sr_return_quantity,
            sr_return_tax) AS UPF_48d03d))
    ) AS r_621d76 
        ON r_d44bb1.cd_education_status NOT ILIKE r_621d76.upn_372df9 
WHERE
    r_d6f27e.cc_employees = r_621d76.sr_return_time_sk 
    AND r_d6f27e.cc_hours NOT IN (
        SELECT
            substring(r_2f2a53.s_store_name,
            r_3e7906.sm_ship_mode_sk) as te_84e083 
        FROM
            db1.store AS r_2f2a53,
            db1.ship_mode AS r_3e7906 
        RIGHT JOIN
            db1.customer_demographics AS r_64fd28 
                ON r_64fd28.cd_purchase_estimate IS NULL 
        WHERE
            r_2f2a53.s_tax_percentage <= r_3e7906.sm_ship_mode_sk 
            AND r_2f2a53.s_state >= '5G6' 
            OR r_64fd28.cd_dep_college_count = 95 
        ORDER BY
            1 NULLS LAST
    ) 
    OR r_d6f27e.cc_market_manager LIKE 'DXX4QHZg' 
    AND r_d6f27e.cc_rec_start_date < DATE'2024-10-11' 
ORDER BY
    1 ASC, 2, 3 DESC NULLS LAST;
